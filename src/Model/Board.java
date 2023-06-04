package Model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Random;

import Model.Tiles.*;

/**
 * This class is the board of the game, which contains an ArrayList of Tiles
 * @author csd4623
 * @version 1.0
 */
public class Board {
	private ArrayList<Tile> board;
	/**
	 * <b>constructor</b>: Constructs a new Board which contains: <br>
	 * <ul>
	 * <li>1 StartTile (Always the first tile)</li>
	 * <li>1 PaydayTile (Always the last tile)</li>
	 * <li>8 MessageTiles(4 of each type)(Random Position)</li>
	 * <li>5 DealTiles (Random Position)</li>
	 * <li>2 SweepStakesTiles (Random Position)</li>
	 * <li>3 LotteryTiles (Random Position)</li>
	 * <li>2 RadioTiles (Random Position)</li>
	 * <li>6 BuyerTiles (Random Position)</li>
	 * <li>2 FamilyCasinoTiles (Random Position)</li>
	 * <li>2 YardSaleTiles (Random Position)</li>
	 * </ul>
     * <b>postcondition</b>: Creates and initializes the board of the game<br>
	 */
	public Board(){
		board = new ArrayList<Tile>();
		for(int i=1; i<=6; i++) {
			board.add(new BuyerTile());
			if(i<=4) {
				board.add(new MessageTile(1));
				board.add(new MessageTile(2));
			}
			if(i<=5) {
				board.add(new DealTile());
			}
			if(i<=3) {
				board.add(new LotteryTile());
			}
			if(i<=2) {
				board.add(new SweepTakesTile());
				board.add(new FamilyCasinoTile());
				board.add(new YardSaleTile());
				board.add(new RadioTile());
			}
		}
		shuffleBoard();
		board.add(new PaydayTile());
		board.add(0, new StartTile());
		assignOrderNumbers();
		assignCoordinates();
		setThursdaysSundays();
	}
	/**
	 * <b>Accessor</b>: Returns the Tile that is in the i-th position of the ArrayList 
	 * <b>Precondition</b>: 0&lt;=i&lt;=32
	 * <b>Postcondition</b>: The Tile is returned
	 * @param i number i Tile will be returned
	 * @return i-th Tile of the ArrayList
	 */
	public Tile getTile(int i) {
		if(i<0 || i>32) {
			throw new InvalidParameterException("The board contains 32 tiles, "+i+" is out of bounds");
		}
		else {
			return board.get(i);
		}
	}
	/**
	 * <b>Accessor</b>: Returns the ArrayList that contains all the Tiles
	 * <b>Postcondition</b>: The Tile is returned
	 * @return board-the entire board (ArrayList of Tiles)
	 */
	public ArrayList<Tile> getBoard(){return board;}
	/**
	 * <b>Accessor</b>: Returns the Tile that is closest to the startingTile and
	 * is also a Deal or Buyer Tile
	 * <b>Precondition</b>: 0&lt;=startingTile.getOrderNumber()&lt;=32
	 * <b>Postcondition</b>: The Tile is returned
	 * @param startingTile The tile the player is currently on
	 * @return Tile that is closest to the startingTile and is also a Deal or Buyer Tile
	 */
	public Tile getClosestDealBuyer(Tile startingTile) {
		if(startingTile.getOrderNumber()<0 || startingTile.getOrderNumber()>32) {
			throw new InvalidParameterException("The tile: "+startingTile.toString()+", is not part of the board");
		}
		else {
			Tile left = null, right = null;
			for(int i=startingTile.getOrderNumber(); i<board.size(); i++) {
				if(board.get(i) instanceof BuyerTile||board.get(i) instanceof DealTile) {
					right = board.get(i);
					break;
				}
			}
			for(int i=startingTile.getOrderNumber(); i>0; i--) {
				if(board.get(i) instanceof BuyerTile||board.get(i) instanceof DealTile) {
					left = board.get(i);
					break;
				}
			}
			if(left==null && right!=null) return right;
			else if(left!=null && right==null) return left;
			else if(left==null && right==null) throw new NullPointerException("Cannot calculate closest Buyer/Deal tile");
			else if(startingTile.getOrderNumber()-left.getOrderNumber()<right.getOrderNumber()-startingTile.getOrderNumber()) return left;
			else return right;
		}
	}
	/**
	 * Returns the string representation of the board
	 */
	public String toString() {
		String toStr = "The board contains:";
		for(Tile tile:board) {
			toStr += "\n"+tile.toString();
		}
		return toStr;
	}
	//Assigns the order number according to the number in the arrayList
	private void assignOrderNumbers() {
		for(int i=0; i<board.size(); i++) {
			board.get(i).setOrderNumber(i);
		}
	}
	//Shuffles the tiles of the board except the first and last tile
	private void shuffleBoard() {
		for(int i=0; i<1000; i++) {
			int number1 = (java.lang.Math.abs(new Random().nextInt()))%30;
			int number2 = (java.lang.Math.abs(new Random().nextInt()))%30;
			Tile tile = board.remove(number1);
			board.add(number2, tile);
		}
	}
	//sets the isThrusday to true for every tile that happens to be Thursday (and same for Sundays)
	private void setThursdaysSundays() {
		int index=0;
		for(Tile tile: board) {
			if(index==4 || index==11 || index==18 || index==25) {
				tile.setIsThursday(true);
			}
			if(index==7 || index==14 || index==21 || index==28) {
				tile.setIsSunday(true);
			}
			index++;
		}
	}
	//sets the coordinates of the tiles according to their orderNumber
	private void assignCoordinates() {
		int index = 0;
		int x = 0;
		int y = 120;
		for(int row=0; row<4; row++) {
			x=0;
			for(int col=0; col<7; col++) {
				Tile tile = board.get(index);
				tile.setLocation(new Point(x,y));
				x+=100;
				index++;
			}
			y+=120;
		}
		x=0;
		for(int i=0; i<4; i++) {
			Tile tile = board.get(index);
			tile.setLocation(new Point(x,y));
			x+=100;
			index++;
		}
	}
}

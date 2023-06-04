package View;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.Board;
import Model.Dice;
import Model.Jackpot;
import Model.Player;
import Model.Tiles.Tile;
/**
 * The main window of the game that contains the board, the dices, the players and all the 
 * Necessary buttons that each player needs
 * @author csd4623
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GraphicUI extends JFrame {
	private JLabel p1data, dice1, pawn1;
	private JLabel p2data, dice2, pawn2;
	private JButton p1rollDice, p2rollDice, p1getloan, p2getloan, p1endTurn, p2endTurn,
			p1cards, p2cards;
	private JLabel out, jackpotMoney;
	/**
	 * <b>constructor</b>: Creates a new Window and initializes some buttons and panels <br>
	 * <b>postconditions</b>: Creates a new Window and initializes some buttons and panels
	 * starting a new game.<br>
	 */
    public GraphicUI(Board board, Player p1, Player p2, Dice p1dice, Dice p2dice) {
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setSize(1024, 768);
    	setLayout(null);
    	
    	JLabel background = new JLabel(new ImageIcon("src/resources/images2/bg_green.png"));
    	background.setBounds(0,0,1024,768);
    	
    	ImageIcon pawn1img=new ImageIcon("src/resources/images2/pawn_blue.png"); 
    	ImageIcon pawn2img=new ImageIcon("src/resources/images2/pawn_yellow.png"); 
    	pawn1img.setImage(getScaledImage(pawn1img.getImage(),50,50));
    	pawn2img.setImage(getScaledImage(pawn2img.getImage(),50,50));
    	pawn1 = new JLabel(pawn1img);
    	pawn2 = new JLabel(pawn2img);
    	pawn1.setBounds(0,0, 50,50);
    	pawn2.setBounds(50,0,50,50);
    	add(pawn1);
    	add(pawn2);
    	movePawnToTile(Pawn.p1, board.getTile(0));
    	movePawnToTile(Pawn.p2, board.getTile(0));
    	
    	ImageIcon logoImg = new ImageIcon("src/resources/images2/logo.png");
    	logoImg.setImage(getScaledImage(logoImg.getImage(), 700, 100));
    	JLabel logo = new JLabel(logoImg); 
    	logo.setBounds(0,0,700,100);
    	add(logo);
    	
    	ImageIcon dealImg = new ImageIcon("src/resources/images2/dealCard.png");
    	dealImg.setImage(getScaledImage(dealImg.getImage(), 120, 70));
    	JLabel dealCard = new JLabel(dealImg);
    	dealCard.setBounds(880, 420, 120, 70);
    	add(dealCard);
    	
    	ImageIcon mailImg = new ImageIcon("src/resources/images2/mailCard.png");
    	mailImg.setImage(getScaledImage(mailImg.getImage(), 120, 70));
    	JLabel mailCard = new JLabel(mailImg);
    	mailCard.setBounds(750, 420, 120, 70);
    	add(mailCard);
    	
    	int dayNumber=0;
    	String[] days= {"Sunday ", "Monday ", "Tuesday ", "Wednesday ", "Thursday ", "Friday ", "Saturday "};
    	for(Tile tile: board.getBoard()) {
    		ImageIcon img = new ImageIcon(tile.getImagePath());
    		img.setImage(getScaledImage(img.getImage(), 100, 100));
    		JLabel tileImg = new JLabel(img);
    		int x = tile.getLocation().getXcoordinate();
    		int y = tile.getLocation().getYcoordinate();
    		tileImg.setBounds(x,y,100,100);
    		add(tileImg);
    		JLabel day = new JLabel(days[dayNumber%days.length]+dayNumber);
    		if(dayNumber==0) {
    			day.setText("Start");
    		}
    		day.setOpaque(true);
    		day.setBackground(Color.yellow);
    		day.setBounds(x, y-20, 100, 20);
    		add(day);
    		dayNumber++;
    	}
    	
    	
    	
    	JPanel p1panel = new JPanel(); 
    	JPanel p2panel = new JPanel();
    	JPanel output = new JPanel();
    	p1panel.setBorder(BorderFactory.createLineBorder(Color.blue));
    	p1panel.setLayout(new BorderLayout());
    	p2panel.setBorder(BorderFactory.createLineBorder(Color.red));
    	p2panel.setLayout(new BorderLayout());
    	output.setBorder(BorderFactory.createLineBorder(Color.yellow));
    	output.setLayout(new GridLayout());
    	p1panel.setBounds(750, 20, 250, 200);
    	p2panel.setBounds(750, 520, 250, 200);
    	output.setBounds(750, 250, 250, 150);
    	add(p1panel);
    	add(p2panel);
    	add(output);
    	
    	jackpotMoney = new JLabel("Jackpot: 0$");
    	jackpotMoney.setBounds(525, 700, 200, 30);
    	jackpotMoney.setFont(new Font("SerifBold", Font.PLAIN, 23));
    	jackpotMoney.setForeground(Color.white);
    	add(jackpotMoney);
    	ImageIcon jackpotImg = new ImageIcon("src/resources/images2/jackpot.png");
    	jackpotImg.setImage(getScaledImage(jackpotImg.getImage(), 200, 100));
    	JLabel jackpot = new JLabel(jackpotImg);
    	jackpot.setBounds(500, 600, 200, 100);
    	add(jackpot);
    	
    	out = new JLabel("<html><u>New game has been started!</u><br>A random board has been "
    			+ "created and the players have been placed at the start of the board<br>Both "
    			+ "players have been given "+p1.getMoney()+"$ each, with 0 loans and 0 bills<br>"
    			+ ">>>It is player1's turn to play</html>"); 
    	output.add(out);
    	
    	p1data = new JLabel("<html><center>Player1<br>Money: "+p1.getMoney()+", Bills: "+p1.getBills()+
    			", Loans: "+p1.getLoans()+"</html>");
    	p2data = new JLabel("<html><center>Player2<br>Money: "+p2.getMoney()+", Bills: "+p2.getBills()+
    			", Loans: "+p2.getLoans()+"</html>");
    	JPanel data = new JPanel();
    	data.add(p1data);
    	p1panel.add(data, BorderLayout.PAGE_START);
    	data = new JPanel();
    	data.add(p2data);
    	p2panel.add(data, BorderLayout.PAGE_START);
    	
    	p1rollDice = new JButton("Roll Dice");
    	p1cards = new JButton("My Deal Cards");
    	p1getloan = new JButton("Get Loan");
    	p1endTurn = new JButton("End Turn");
    	p1panel.add(p1rollDice, BorderLayout.LINE_START);
    	JPanel buttons = new JPanel();
    	buttons.add(p1cards);
    	buttons.add(p1getloan);
    	p1panel.add(buttons, BorderLayout.PAGE_END);
    	p1panel.add(p1endTurn, BorderLayout.LINE_END);
    	p2rollDice = new JButton("Roll Dice");
    	p2cards = new JButton("My Deal Cards");
    	p2getloan = new JButton("Get Loan");
    	p2endTurn = new JButton("End Turn");
    	p2panel.add(p2rollDice, BorderLayout.LINE_START);
    	buttons = new JPanel();
    	buttons.add(p2cards);
    	buttons.add(p2getloan);
    	p2panel.add(buttons, BorderLayout.PAGE_END);
    	p2panel.add(p2endTurn, BorderLayout.LINE_END);
  
    	ImageIcon dice1img = new ImageIcon(p1dice.getImagePath());
    	ImageIcon dice2img = new ImageIcon(p2dice.getImagePath());
    	dice1img.setImage(getScaledImage(dice1img.getImage(), 60, 60));
    	dice2img.setImage(getScaledImage(dice2img.getImage(), 60, 60));
    	dice1 = new JLabel(dice1img);
    	dice2 = new JLabel(dice2img);
    	p1panel.add(dice1, BorderLayout.CENTER);
    	p2panel.add(dice2, BorderLayout.CENTER);
    	
    	add(background);
    	setVisible(true);
    }
    /**
     * <b>Transformer</b>: Moves one of the pawns to a new tile<br>
     * <b>Precondition</b>: The pawn and the tile must not be null<br>
     * <b>Postcondition</b>: The pawn has been moved<br>
     * @param pawn which of the two pawns will be moved
     * @param tile the tile that the pawn will move to
     */
    public void movePawnToTile(Pawn pawn,Tile tile) {
    	if(pawn == Pawn.p1) {
    		int x = tile.getLocation().getXcoordinate();
    		int y = tile.getLocation().getYcoordinate();
    		pawn1.setLocation(x, y+25);
    	}
    	else if(pawn == Pawn.p2) {
    		int x = tile.getLocation().getXcoordinate();
    		int y = tile.getLocation().getYcoordinate();
    		pawn2.setLocation(x+50, y+25);
    	}
    	else {
    		throw new NullPointerException("The enum Pawn cannot be null");
    	}
    }
    /**
     * <b>Transformer</b>: Changes the dice image for one of the players<br>
     * <b>Precondition</b>: The pawn and the dice must not be null<br>
     * <b>Postcondition</b>: Image has been changed<br>
     * @param p is either Pawn.p1 or Pawn.p2
     * @param dice that image will be changed to the number of the dice's last roll
     */
    public void changeDiceImage(Pawn p, Dice dice) {
    	ImageIcon diceimg = new ImageIcon(dice.getImagePath());
    	diceimg.setImage(getScaledImage(diceimg.getImage(), 60, 60));
    	if(p == Pawn.p1) {
    		dice1.setIcon(diceimg);
    	}
    	else if(p == Pawn.p2) {
    		dice2.setIcon(diceimg);
    	}
    	else {
    		throw new NullPointerException("The Pawn enum must be either p1 or"
    				+ " p2 when changing the dice image");
    	}
    }
    /**
     * <b>Transformer</b>: Changes the text of the output panel<br>
     * <b>Postcondition</b>: The text has been changed<br>
     * @param message The text that will be displayed
     */
    public void changeOutputPanelMessage(String message) {
    	out.setText(message);
    }
    /**
     * <b>observer</b>: Creates a dialog that notifies the player about the 
     * size of the loan that was automatically given to him if he doesn't have
     * enough money to pay for something<br>
     * <b>Precondition</b>: loan&gt;0<br>
     * <b>Postcondition</b> The dialog has notified the player about the loan 
     * that he was automatically given<br>
     * @param loan 
     */
    public void loanPopUp(int loan) {
    	JOptionPane.showMessageDialog(null, "You run out of money so a loan of "+loan+
    			"$ was given to you to pay what you owe");
    }
    /**
     * /**
     * <b>Transformer</b>: Changes the values of the players' money, bills and loans as well as
     * the jackpot money of each JLabel to display the most recently updated values<br>
     * <b>Postcondition</b>: The text has been changed<br>
     * @param p1 player 1
     * @param p2 player 1
     * @param jackpot the jackpot
     */
    public void refreshPlayerData(Player p1, Player p2, Jackpot jackpot) {
    	p1data.setText("<html><center>Player1<br>Money: "+p1.getMoney()+", Bills: "+p1.getBills()+
    			", Loans: "+p1.getLoans()+"</html>");
    	p2data.setText("<html><center>Player2<br>Money: "+p2.getMoney()+", Bills: "+p2.getBills()+
    			", Loans: "+p2.getLoans()+"</html>");
    	jackpotMoney.setText("Jackpot: "+jackpot.getJackpotMoney()+"$");
    }
    /**
     * Opens a dialog where the user is asked to select the months of the game (1,2 or 3)
     * @return the months that were selected
     */
    public static int startMenuPopup() {
    	int months=-1;
    	Object[] options = {"1 Month", "2 Months","3 Months"};
    	while(months<0)months = JOptionPane.showOptionDialog(null,
    			"Choose the length of the game",
    			"Month Selection",
    			JOptionPane.YES_NO_CANCEL_OPTION,
    			JOptionPane.QUESTION_MESSAGE,
    			null,
    			options,
    			-1);
    	months++;
    	return months;
    }
    
    /**
     * Called when a player arrives at a sweeptakes tile. The player rolls a dice and the roll is returned
     * @param dice a new dice
     * @return the previous roll of the dice
     */
    public int sweepstakesPopUp(Dice dice) {
    	ImageIcon diceImg = new ImageIcon(dice.getImagePath());
    	diceImg.setImage(getScaledImage(diceImg.getImage(), 75, 75));
    	JOptionPane.showMessageDialog(null, "You landed on a sweepstakes tile, roll the "
    			+ "dice and you will receive a 1000x time your roll. Press OK to roll the dice", 
    			"Sweepstakes", JOptionPane.INFORMATION_MESSAGE, diceImg);
    	dice.rollDice();
    	diceImg = new ImageIcon(dice.getImagePath());
    	diceImg.setImage(getScaledImage(diceImg.getImage(), 75, 75));
    	JOptionPane.showMessageDialog(null, "You rolled a "+dice.getPreviousRoll()+
    			" that means that you win "+dice.getPreviousRoll()*1000+"$!!!", 
    			"Sweepstakes", JOptionPane.INFORMATION_MESSAGE, diceImg);
    	return dice.getPreviousRoll();
    }
    /**
     * Called when a player arrives at a lottery tile. The players select a number between 1 and 6 and
     * then a dice is rolled and the winner is the one that selected a number that is closest to the roll
     * @param p1 the player that arrived at the tile (he will select a number first)
     * @param p2 the other player (he will select a number second)
     * @param dice a new dice
     * @return the winner
     */
    public Player lotteryPopUp(Player p1, Player p2, Dice dice) {
    	Player winner=null;
    	int p1selection=-1, p2selection=-1;
    	
    	Object[] options = {1,2,3,4,5,6};
    	while(p1selection<0) p1selection = JOptionPane.showOptionDialog(null,
    			"<html>Each player must select a number. A dice will be rolled and whoever has picked a"
    			+ "<br>number closest to the roll will win 1000$. "+p1.getName()+" select a number:",
    			"Select Number</html>",
    			JOptionPane.YES_NO_CANCEL_OPTION,
    			JOptionPane.QUESTION_MESSAGE,
    			null,
    			options,
    			-1);
    	while(p2selection<0||p2selection==p1selection) p2selection = JOptionPane.showOptionDialog(null,
    			p2.getName()+" select a number:",
    			"Select Number",
    			JOptionPane.YES_NO_CANCEL_OPTION,
    			JOptionPane.QUESTION_MESSAGE,
    			null,
    			options,
    			-1);
    	p1selection++;
    	p2selection++;
    	
    	ImageIcon diceImg=null;
    	while(winner==null) {
    		dice.rollDice();
    		diceImg = new ImageIcon(dice.getImagePath());
        	diceImg.setImage(getScaledImage(diceImg.getImage(), 75, 75));
        	if(Math.abs(dice.getPreviousRoll()-p1selection)<Math.abs(dice.getPreviousRoll()-p2selection)) {
        		winner=p1;
        	}
        	else if(Math.abs(dice.getPreviousRoll()-p1selection)>Math.abs(dice.getPreviousRoll()-p2selection)){
        		winner=p2;
        	}
        	else {
        		JOptionPane.showMessageDialog(null, "A "+dice.getPreviousRoll()+
            			" was rolled, that means it was a tie. The dice will be rolled again", 
            			"Tie", JOptionPane.INFORMATION_MESSAGE, diceImg);
        	}
    	}
    	JOptionPane.showMessageDialog(null, "A "+dice.getPreviousRoll()+
    			" was rolled, that "+winner.getName()+ " won 1000$!", 
    			"Winner", JOptionPane.INFORMATION_MESSAGE, diceImg);
    	
    	return winner;
    }
    /**
     * Called when a player arrives at a radio tile. The players roll the dice and the one with the highest 
     * roll wins. In case of a tie they roll again
     * @param p1 the player that arrived at the tile (he will select a number first)
     * @param p2 the other player (he will select a number second)
     * @param dice a new dice
     * @return the winner
     */
    public Player radioPopUp(Player p1, Player p2, Dice dice) {
    	Player winner = null;
    	JOptionPane.showMessageDialog(null, "<html>You landed on a radio tile, both players will "
    			+ "roll the dice and the player with the highest roll wins 1000$ from the bank.<br>"
    			+ "Press ok to continue:</html", 
    			"Radio contest", JOptionPane.INFORMATION_MESSAGE);
    	int p1roll=0;
    	int p2roll=0;
    	Object[] option = {"Roll Dice"};
    	ImageIcon diceImg=null;
    	while(winner==null) {
    		diceImg = new ImageIcon(dice.getImagePath());
    		JOptionPane.showOptionDialog(null,
    				""+p1.getName()+" roll the dice",
    				"Roll the dice",
    				JOptionPane.YES_NO_CANCEL_OPTION,
    				JOptionPane.QUESTION_MESSAGE,
    				diceImg,
    				option,
    				-1);
    		p1roll=dice.rollDice();
    		diceImg = new ImageIcon(dice.getImagePath());

    		JOptionPane.showOptionDialog(null,
    				""+p1.getName()+" rolled a "+p1roll+". "+p2.getName()+" roll the dice",
    				"Roll the dice",
    				JOptionPane.YES_NO_CANCEL_OPTION,
    				JOptionPane.QUESTION_MESSAGE,
    				diceImg,
    				option,
    				-1);
    		p2roll=dice.rollDice();
    		diceImg = new ImageIcon(dice.getImagePath());

    		if(p1roll>p2roll) {
    			winner=p1;
    		}
    		else if(p2roll>p1roll) {
    			winner=p2;
    		}
    		else {
    			JOptionPane.showMessageDialog(null, p2.getName()+" rolled a "+p2roll+
    					". It is a tie so press ok to roll again", 
    					"Tie", JOptionPane.INFORMATION_MESSAGE, diceImg);
    		}
    	}
    	JOptionPane.showMessageDialog(null, p2.getName()+" rolled a "+p2roll+
    			". "+winner.getName()+" won 1000$", 
    			"Winner", JOptionPane.INFORMATION_MESSAGE, diceImg);

    	return winner;
    }
    /**
     * Called when a player arrives at a family tile. It opens a dialog to notify the player whether he won
     * or lost 500$
     * @param roll the previous dice roll of the player
     */
    public void familyPopUp(int roll) {
    	if(roll%2==0) {
    		JOptionPane.showMessageDialog(null, "<html>You reached a family casino night tile by rolling"
    				+ " a "+roll+" which is an even number.<br>This means that you have to give 500$ to"
    				+ "the jackpot</html>", 
        			"Family Casino Night", JOptionPane.INFORMATION_MESSAGE);
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "<html>You reached a family casino night tile by rolling"
    				+ " a "+roll+" which is an even number.<br>This means that you won 500$ from"
    				+ " the bank</html>", 
        			"Family Casino Night", JOptionPane.INFORMATION_MESSAGE);
    	}
    }
    /**
     * Called when a player arrives at a yard tile. The player rolls the dice and the dice roll is returned
     * @param dice a new dice
     * @return the dice roll
     */
    public int yardPopUp(Dice dice) {
    	int roll = -1;
    	ImageIcon diceImg = new ImageIcon(dice.getImagePath());
    	JOptionPane.showMessageDialog(null, "<html>You have reached a yard sale tile.<br> Roll the dice"
    			+ " and you have to pay the bank x100 time your roll.<br>In return you will be"
    			+ " give a deal tile for no extra cost</html>", 
    			"Yard Sale", JOptionPane.INFORMATION_MESSAGE, diceImg);
    	roll = dice.rollDice();
    	diceImg = new ImageIcon(dice.getImagePath());
    	JOptionPane.showMessageDialog(null, "<html>You rolled a "+roll+", so you have to pay the "
    			+ "bank "+roll*100+"$.<br>Dont forget to check your card inventory, a deal card has"
    					+ " been added.</html>", 
    			"Yard Sale", JOptionPane.INFORMATION_MESSAGE, diceImg);
    	return roll;
    }
    /**
     * Called when a player arrives at a payday tile. It opens a dialog to notify the player of all the actions
     * that will be taking place and it receives as input from the player the amount of the loans that the player
     * wants to pay off (and returns it)
     * @param p the player that has reached the tile
     * @return the amount of loans that the player wants to pay off
     */
    public int paydayPopUp(Player p) {
    	int payedLoans=0;
    	int selection=-1;
    	Object[] options = {"Pay off 1000$ loans", "Close"}; 
    	while(selection!=1) {
    		selection = JOptionPane.showOptionDialog(null,
        			"<html>You have reached the payday tile. What will happen:<br>"
        			+ "1. You will receive 3500$ as payment<br>"
        			+ "2. You will pay "+p.getBills()+"$ for your bills<br>"
        			+ "3. You have to pay 10% intrest on you loans, which is "+p.getLoans()/10+"$<br>"
        			+ "4. You can pay off your loan or part of it by clicking the button below ("
        			+(p.getLoans()-(payedLoans*1000))+"$ of loans remaining)<br>"
        			+ "5. All your deal cards will be removed",
        			"Pay Day Tile",
        			JOptionPane.YES_NO_CANCEL_OPTION,
        			JOptionPane.QUESTION_MESSAGE,
        			null,
        			options,
        			-1);
    		
    		if(selection==0 && p.getMoney()-1000>=0 && p.getLoans()-((payedLoans+1)*1000)>=0) {
    			payedLoans++;
    		}
    		if(p.getLoans()-(payedLoans*1000)<=0) options[0] = "Loans already payed";
    		if(p.getMoney()-(payedLoans*1000)-1000<0) options[0] = "Not enough money to pay loans";
    	}
    	return payedLoans*1000;
    }
    /**
     * Called when a player rolls a 6. Opens a dialog to notify the player the amount of money he received from
     * the jackpot
     * @param jackpotMoney the amount of money that the player won 
     */
    public void wonJackpot(int jackpotMoney) {
    	JOptionPane.showMessageDialog(null, "<html>You rolled a six, which means you win the jackpot"
    			+ "<br>The jackpot had "+jackpotMoney+"$</html>", 
    			"Won jackpot", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Called when both players have completed all their months. Opens a dialog that displays the score of each 
     * player as well as the name of the player who is the winner of the game (has the highest score). When the
     * dialog is closed the game has finished and the program exits
     * @param p1score the score of player1
     * @param p2score the score of player2
     */
    public void winPopUp(int p1score, int p2score) {
    	if(p1score>p2score) {
    		JOptionPane.showMessageDialog(null, "<html>Game Completed!!! Score:<br>"
    				+ "Player 1: "+p1score+"<br>Player 2: "+p2score+"<br>"
    						+ "Congratulations Player 1, you won! </html>", 
        			"Game Completed", JOptionPane.INFORMATION_MESSAGE);
    	}
    	else if(p2score>p1score) {
    		JOptionPane.showMessageDialog(null, "<html>Game Completed!!! Score:<br>"
    				+ "Player 1: "+p1score+"<br>Player 2: "+p2score+"<br>"
    				+ "Congratulations Player 2, you won! </html>", 
    				"Game Completed", JOptionPane.INFORMATION_MESSAGE);
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "<html>Game Completed!!! Score:<br>"
    				+ "Player 1: "+p1score+"<br>Player 2: "+p2score+"<br>"
    				+ "It is a tie</html>", 
    				"Game Completed", JOptionPane.INFORMATION_MESSAGE);
    	}
    	System.exit(0);
    }
    /**
     * The player selects a team that he wants to bid on 500$, if his guess was correct 1 is returned, if it 
     * was wrong 2 is returned and if he didn't bid 0 is returned
     * @param dice a new dice
     * @return 0 if the player didn't bid, 1 if he won and 2 if he lost
     */
    public int sundayPopUp(Dice dice) {
    	ImageIcon match = new ImageIcon("src/resources/images2/Barcelona_Real.jpg");
    	Object[] options = { "I don't want to bid", "Barcelona win", "Tie", "Real win"}; 
    	int selection = JOptionPane.showOptionDialog(null,
    			"Bid 500$ for the Barcelona-Real match",
    			"Sunday Football Match",
    			JOptionPane.YES_NO_CANCEL_OPTION,
    			JOptionPane.QUESTION_MESSAGE,
    			match,
    			options,
    			0);
    	if(selection!=1 && selection!=2 && selection!=3) {
    		return 0;
    	}
    	
    	dice.rollDice();
    	if(selection==1 && (dice.getPreviousRoll()==1 || dice.getPreviousRoll()==2)) {
    		JOptionPane.showMessageDialog(null, "<html>Barcelona won, so you won 500$.</html>", 
        			"Win", JOptionPane.INFORMATION_MESSAGE, match);
    		return 1;
    	}
    	else if(selection==2 && (dice.getPreviousRoll()==3 || dice.getPreviousRoll()==4)) {
    		JOptionPane.showMessageDialog(null, "<html>It was a tie so you won 500$.</html>", 
        			"Win", JOptionPane.INFORMATION_MESSAGE, match);
    		return 1;
    	}
    	else if(selection==3 && (dice.getPreviousRoll()==5 || dice.getPreviousRoll()==6)) {
    		JOptionPane.showMessageDialog(null, "<html>Real won so you won 500$.</html>", 
        			"Win", JOptionPane.INFORMATION_MESSAGE, match);
    		return 1;
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "<html>Wrong prediction. You lost the 500$ you bid.</html>", 
        			"Loss", JOptionPane.INFORMATION_MESSAGE, match);
    		return 2;
    	}
    }
    /**
     * The player selects if he wants to bid 300$ on a cryptocoin, 1 if the price increased, if it 
     * dropped 2 is returned and if he didn't bid or the price remained the same 0 is returned
     * @param dice a new dice
     * @return 0 if the player didn't bid or if the price remained the same, 1 if the price increased 
     * and 2 if dropped
     */
    public int cryptoPopUp(Dice dice) {
    	ImageIcon crypto = new ImageIcon("src/resources/images2/crypto.jpg");
    	Object[] options = {"Ignore", "Buy cryptocoin"}; 
    	int selection = JOptionPane.showOptionDialog(null,
    			"Bid 300$ on the Javacoin",
    			"Crypto Thursday",
    			JOptionPane.YES_NO_CANCEL_OPTION,
    			JOptionPane.QUESTION_MESSAGE,
    			crypto,
    			options,
    			0);
    	if(selection==0) {
    		return 0;
    	}
    	dice.rollDice();
    	if(dice.getPreviousRoll()==1 || dice.getPreviousRoll()==2) {
    		JOptionPane.showMessageDialog(null, "<html>The price dropped.<br>"
    				+ " You lost the 300$ you bid</html>", 
        			"Drop", JOptionPane.INFORMATION_MESSAGE, crypto);
    		return 2;
    	}
    	if(dice.getPreviousRoll()==3 || dice.getPreviousRoll()==4) {
    		JOptionPane.showMessageDialog(null, "<html>The price remained the same.<br>"
    				+ " You get your money back</html>", 
        			"Balanced", JOptionPane.INFORMATION_MESSAGE, crypto);
    		return 0;
    	}
    	if(dice.getPreviousRoll()==5 || dice.getPreviousRoll()==6) {
    		JOptionPane.showMessageDialog(null, "<html>The price of the cryptocoin skyrocketed!!!.<br>"
    				+ " You doubled the 300$ you bid</html>", 
        			"Price skyrocketed!", JOptionPane.INFORMATION_MESSAGE, crypto);
    		return 1;
    	}
    	return 0;
    }
    /**
     * <b>Accessor</b>: returns the p1rollDice JButton<br>
     * <b>Postcondition</b>: p1rollDice has been returned<br>
	 * @return the p1rollDice JButton
	 */
	public JButton getP1rollDice() {return p1rollDice;}

	/**
	 * <b>Accessor</b>: returns the p2rollDice JButton<br>
     * <b>Postcondition</b>: p2rollDice has been returned<br>
	 * @return the p2rollDice JButton
	 */
	public JButton getP2rollDice() {return p2rollDice;}

	/**
	 * <b>Accessor</b>: returns the p1getloan JButton<br>
     * <b>Postcondition</b>: p1getloan has been returned<br>
	 * @return the p1getloan JButton
	 */
	public JButton getP1getloan() {return p1getloan;}

	/**
	 * <b>Accessor</b>: returns the p2getloan JButton<br>
     * <b>Postcondition</b>: p2getloan has been returned<br>
	 * @return the p2getloan JButton
	 */
	public JButton getP2getloan() {return p2getloan;}

	/**
	 * <b>Accessor</b>: returns the p1endTurn JButton<br>
     * <b>Postcondition</b>: p1endTurn has been returned<br>
	 * @return the p1endTurn JButton
	 */
	public JButton getP1endTurn() {return p1endTurn;}

	/**
	 * <b>Accessor</b>: returns the p2endTurn JButton<br>
     * <b>Postcondition</b>: p2endTurn has been returned<br>
	 * @return the p2endTurn JButton
	 */
	public JButton getP2endTurn() {return p2endTurn;}

	/**
	 * <b>Accessor</b>: returns the p1cards JButton<br>
     * <b>Postcondition</b>: p1cards has been returned<br>
	 * @return the p1cards JButton
	 */
	public JButton getP1cards() {return p1cards;}

	/**
	 * <b>Accessor</b>: returns the p2cards JButton<br>
     * <b>Postcondition</b>: p2cards has been returned<br>
	 * @return the p2cards JButton
	 */
	public JButton getP2cards() {return p2cards;}
    /**
     * <b>Transformer</b>: Takes an image and scales to a (w,h) size<br>
     * <b>Precondition</b>: The image is not null and the w and h are positive numbers
     * @param srcImg the image that will be scaled
     * @param w width
     * @param h height
     * @return the scaled Image
     */
    public static Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}
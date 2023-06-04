package Model;

import java.security.InvalidParameterException;
import java.util.Random;
/**
 * This is the class of a dice. Its attributes are its previous roll, the path
 * of its image and its owner.
 * It has a method that rollls a dice and returns
 * a number between 1 and 6, as well as set/get methods for its attributes
 * @author csd4623
 *
 */
public class Dice {
	private int previousRoll;
	private Player owner;
	private String imagePath;
	/**
	 * <b>constructor</b>: Constructs a new Dice<br>
     * <b>postcondition</b>: Creates and initializes a Dice with null
     * owner and one as the previous roll (and the imagePath accordingly so
     * that the image is a dice with a 1 roll) <br>
	 */
	public Dice() {
		previousRoll = 1;
		owner = null;
		imagePath = "src/resources/images2/dice-1.jpg";
	}
	/**
	 * <b>constructor</b>: Constructs a new Dice<br>
     * <b>postcondition</b>: Creates and initializes a Dice with p as the 
     * owner and one as the previous roll (and the imagePath accordingly so
     * that the image is a dice with a 1 roll) <br>
     * @param p the owner of the dice
	 */
	public Dice(Player p) {
		previousRoll = 1;
		owner = p;
		imagePath = "src/resources/images2/dice-1.jpg";
	}
	/**
	 * <b>transformer</b>: sets the imagePath of the dice<br>
	 * <b>Precondition</b>: 1&lt;=number&lt;=6
	 * <b>Postcondition</b>: the imagePath has changed<br>
	 * @param number the image changes according to the number rolled (eg if
	 * number 4 was rolled the image will change to the image of the dice that 
	 * has rolled 4) 
	 */
	private void setImagePath(int number) {
		if(number <1 || number >6) {
			throw new InvalidParameterException("The dice roll number must be between 1 and 6");
		}
		else {
			imagePath = "src/resources/images2/dice-"+number+".jpg";
		}
	}
	/**
	 * <b>accessor</b>: returns the imagePath of the dice<br>
	 * <b>Postcondition</b>: The imagePath has been returned<br>
	 * @return String path of the image
	 */
	public String getImagePath() {return imagePath;}
	/**
	 * <b>transformer</b>: sets the owner of the dice (can be null if no one
	 * owns the dice)<br>
	 * <b>Postcondition</b>: the owner has changed<br>
	 * @param owner owner of the dice (can be null if no one owns the dice)
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	/**
	 * <b>accessor</b>: returns the owner of the dice<br>
	 * <b>Postcondition</b>: The owner has been returned<br>
	 * @return player that is the owner (or null if no one owns it)
	 */
	public Player getOwner() {return this.owner;}
	/**
	 * <b>accessor</b>: returns the previousRoll of the dice<br>
	 * <b>Postcondition</b>: The previousRoll has been returned<br>
	 * @return the previous roll of the dice
	 */
	public int getPreviousRoll() {return previousRoll;};
	/**
	 * <b>transformer</b>: rolls the dice and returns the rolled number<br>
	 * <b>Postcondition</b>: the previous roll changes to the number that is 
	 * rolled now and the imagePath changes to the correct number. Then the
	 * rolled number is returned<br>
	 * @return number between 1 and 6
	 */
	public int rollDice() {
		int roll = (java.lang.Math.abs(new Random().nextInt()))%6+1;
		previousRoll=roll;
		setImagePath(roll);
		return roll;
	}
}

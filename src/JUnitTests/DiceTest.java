package JUnitTests;

import org.junit.Test;

import Model.Dice;
import Model.Player;
import junit.framework.TestCase;

public class DiceTest extends TestCase {
	@Test
	public void test1() {
		Dice dice = new Dice();
		for(int i=1; i<=1000; i++) {
			int roll=dice.rollDice();
			if(roll<1||roll>6) {
				fail();
			}
		}
	}
	
	@Test
	public void test2() {
		Dice dice = new Dice();
		while(dice.getPreviousRoll()!=1) {
			dice.rollDice();
		}
		assertEquals(dice.getImagePath(), "src/resources/images2/dice-1.jpg");
		while(dice.getPreviousRoll()!=2) {
			dice.rollDice();
		}
		assertEquals(dice.getImagePath(), "src/resources/images2/dice-2.jpg");
		while(dice.getPreviousRoll()!=3) {
			dice.rollDice();
		}
		assertEquals(dice.getImagePath(), "src/resources/images2/dice-3.jpg");
		while(dice.getPreviousRoll()!=4) {
			dice.rollDice();
		}
		assertEquals(dice.getImagePath(), "src/resources/images2/dice-4.jpg");
		while(dice.getPreviousRoll()!=5) {
			dice.rollDice();
		}
		assertEquals(dice.getImagePath(), "src/resources/images2/dice-5.jpg");
		while(dice.getPreviousRoll()!=6) {
			dice.rollDice();
		}
		assertEquals(dice.getImagePath(), "src/resources/images2/dice-6.jpg");
	}
	
	public void test3() {
		Player p1 = new Player("p1",100);
		Dice dice1 = new Dice(p1);
		assertEquals(p1, dice1.getOwner());
		Player p2 = new Player("p2",100);
		dice1.setOwner(p2);
		assertEquals(p2, dice1.getOwner());
	}
}

package JUnitTests;

import static org.junit.Assert.assertThrows;

import java.security.InvalidParameterException;

import org.junit.Test;

import Model.Jackpot;
import Model.Player;
import junit.framework.TestCase;

public class JackpotTest extends TestCase {
	@Test
	public void test1() {
		Player p1 = new Player("Player1",1000);
		assertEquals(p1.getMoney(), 1000);
		Jackpot jackpot = new Jackpot();
		jackpot.playerPaysJackpot(p1, 500);
		assertEquals(p1.getMoney(), 500);
		assertEquals(jackpot.getJackpotMoney(), 500);
		Player p2 = new Player("Player2",1000);
		assertEquals(p2.getMoney(), 1000);
		jackpot.wonJackpot(p2);
		assertEquals(jackpot.getJackpotMoney(), 0);
		assertEquals(p2.getMoney(),1500);
	}
	
	@Test
	public void test2() {
		Jackpot jackpot = new Jackpot();
		assertThrows(NullPointerException.class,()->{jackpot.playerPaysJackpot(null, 100);});
	}
	
	@Test
	public void test3() {
		Jackpot jackpot = new Jackpot();
		assertThrows(NullPointerException.class,()->{jackpot.wonJackpot(null);});
	}
	
	@Test
	public void test4() {
		Jackpot jackpot = new Jackpot();
		assertThrows(InvalidParameterException.class,()->{jackpot.playerPaysJackpot(new Player("",1000), -100);});
	}
}

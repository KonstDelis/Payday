package JUnitTests;
import junit.framework.TestCase;

import static org.junit.Assert.assertThrows;

import java.security.InvalidParameterException;

import org.junit.Test;
import Model.Player;
import Model.BankTransactions;

public class PlayerAndTransactionsTest extends TestCase{
	@Test
	public void test1() {
		Player p1 = new Player("Player1", 1000);
		Player p2 = new Player("Player2", 1000);
		assertEquals(p1.getBills(), 0);
		assertEquals(p2.getBills(), 0);
		assertEquals(p1.getMoney(), 1000);
		assertEquals(p2.getMoney(), 1000);
		assertEquals(p1.getLoans(), 0);
		assertEquals(p2.getLoans(), 0);
		assertFalse(p1.hasWon());
		assertFalse(p2.hasWon());
		assertFalse(p1.hasWon());
		assertFalse(p2.hasWon());
		p1.addMoney(500);
		assertEquals(p1.getMoney(), 1500);
		p2.addBills(1000);
		assertEquals(p2.getBills(), 1000);
		BankTransactions.bankPaysPlayer(p1, 1000);
		assertEquals(p1.getMoney(), 2500);
		BankTransactions.playerPaysPlayer(p2, p1, 1500);
		assertEquals(p1.getMoney(), 4000);
		assertEquals(p2.getMoney(), 500);
		assertEquals(p2.getLoans(), 1000);
		BankTransactions.playerPaysBank(p1, 44150);
		assertEquals(p1.getMoney(), 850);
		assertEquals(p1.getLoans(), 41000);
	}
	
	@Test
	public void test2() {
		Player p = new Player("Player", 1000);
		
		assertThrows(InvalidParameterException.class, () -> {
			p.addLoan(1500);
	    });
	}
	
	@Test
	public void test3() {
		assertThrows(NullPointerException.class, () -> {
			BankTransactions.bankPaysPlayer(null, 0);
		});
		
	}
	
	@Test
	public void test4() {
		Player p = new Player("Player", 1000);
		assertThrows(InvalidParameterException.class, () -> {
			BankTransactions.bankPaysPlayer(p, -100);
		});
	}
	
	@Test
	public void test5() {
		Player p = new Player("Player", 1000);
		assertThrows(NullPointerException.class, () -> {
			BankTransactions.playerPaysPlayer(p, null,100);
		});
	}
}

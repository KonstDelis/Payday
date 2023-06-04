package JUnitTests;

import static org.junit.Assert.assertThrows;

import java.security.InvalidParameterException;

import org.junit.Test;

import Model.Cards.MessageCard;
import View.PayDayCards;
import junit.framework.TestCase;

public class MailCardTest extends TestCase {
	@Test
	public void test1() {
		String[][] mailCardsInfo = new PayDayCards().getMailCards();
		assertEquals(48, mailCardsInfo.length);
		assertEquals(6, mailCardsInfo[0].length);
		MessageCard card = new MessageCard(mailCardsInfo[5], 5);
		assertEquals(5, card.getCardNumber());
		assertEquals(20, card.getValue());
		assertEquals("Αdvertisement", card.getType().trim());
		assertEquals(card.getInfo(), mailCardsInfo[5]);
		
		MessageCard card2 = new MessageCard(mailCardsInfo[11], 11);
		assertEquals(11, card2.getCardNumber());
		assertEquals(1500, card2.getValue());
		assertEquals("Bill", card2.getType());
		assertEquals(card2.getInfo(), mailCardsInfo[11]);
		
		MessageCard card3 = new MessageCard(mailCardsInfo[21], 21);
		assertEquals(21, card3.getCardNumber());
		assertEquals(350, card3.getValue());
		assertEquals("Charity", card3.getType());
		assertEquals(card3.getInfo(), mailCardsInfo[21]);
		
		MessageCard card4 = new MessageCard(mailCardsInfo[24], 24);
		assertEquals(24, card4.getCardNumber());
		assertEquals(60, card4.getValue());
		assertEquals("PayTheNeighbor", card4.getType());
		assertEquals(card4.getInfo(), mailCardsInfo[24]);
		
		MessageCard card5 = new MessageCard(mailCardsInfo[37], 37);
		assertEquals(37, card5.getCardNumber());
		assertEquals(120, card5.getValue());
		assertEquals("MadMoney", card5.getType());
		assertEquals(card5.getInfo(), mailCardsInfo[37]);
		
		MessageCard card6 = new MessageCard(mailCardsInfo[47], 47);
		assertEquals(47, card6.getCardNumber());
		assertEquals(0, card6.getValue());
		assertEquals("MoveToDealBuyer", card6.getType());
		assertEquals(card6.getInfo(), mailCardsInfo[47]);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void test2() {
		assertThrows(NullPointerException.class,()->{
			MessageCard card = new MessageCard(null, 5);});
		
		String[][] mailCardsInfo = new PayDayCards().getMailCards();
		MessageCard card = new MessageCard(mailCardsInfo[5], 5);
		assertThrows(NullPointerException.class,()->{
			card.setInfo(null);});
	}
	
	@SuppressWarnings("unused")
	@Test
	public void test3() {
		String[] wrong= {"wrong1", "wrong2"};
		assertThrows(InvalidParameterException.class,()->{
			MessageCard card = new MessageCard(wrong, 5);});
		
		String[][] mailCardsInfo = new PayDayCards().getMailCards();
		MessageCard card = new MessageCard(mailCardsInfo[5], 5);
		assertThrows(InvalidParameterException.class,()->{
			card.setInfo(wrong);});
	}
	
	@SuppressWarnings("unused")
	@Test
	public void test4() {
		String[][] mailCardsInfo = new PayDayCards().getMailCards();
		assertThrows(InvalidParameterException.class,()->{
			MessageCard card = new MessageCard(mailCardsInfo[0], -5);});
		assertThrows(InvalidParameterException.class,()->{
			MessageCard card = new MessageCard(mailCardsInfo[0], 48);});
		
		MessageCard card = new MessageCard(mailCardsInfo[5], 5);
		assertThrows(InvalidParameterException.class,()->{
			card.setCardNumber(-1);});
		assertThrows(InvalidParameterException.class,()->{
			card.setCardNumber(50);});
	}

}

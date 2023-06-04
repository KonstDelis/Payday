package JUnitTests;

import static org.junit.Assert.assertThrows;

import java.security.InvalidParameterException;

import org.junit.Test;

import Model.Cards.DealCard;
import View.PayDayCards;
import junit.framework.TestCase;

public class DealCardTest extends TestCase {
	@Test
	public void test1() {
		String[][] dealCardsInfo = new PayDayCards().getDealCards();
		assertEquals(20, dealCardsInfo.length);
		assertEquals(8, dealCardsInfo[0].length);
		DealCard card = new DealCard(dealCardsInfo[5], 5);
		assertEquals(5, card.getCardNumber());
		assertEquals(5000, card.getCost());
		assertEquals(9000, card.getValue());
		assertEquals("Deal", card.getType());
		assertEquals(card.getInfo(), dealCardsInfo[5]);
		
		DealCard card2 = new DealCard(dealCardsInfo[19], 19);
		assertEquals(19, card2.getCardNumber());
		assertEquals(5000, card2.getCost());
		assertEquals(9500, card2.getValue());
		assertEquals("Deal", card2.getType());
		assertEquals(card2.getInfo(), dealCardsInfo[19]);
		
		DealCard card3 = new DealCard(dealCardsInfo[12], 12);
		assertEquals(12, card3.getCardNumber());
		assertEquals(250, card3.getCost());
		assertEquals(550, card3.getValue());
		assertEquals("Deal", card3.getType());
		assertEquals(card3.getInfo(), dealCardsInfo[12]);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void test2() {
		assertThrows(NullPointerException.class,()->{
		DealCard card = new DealCard(null, 5);});
		
		String[][] dealCardsInfo = new PayDayCards().getDealCards();
		DealCard card = new DealCard(dealCardsInfo[5], 5);
		assertThrows(NullPointerException.class,()->{
			card.setInfo(null);});
	}
	
	@SuppressWarnings("unused")
	@Test
	public void test3() {
		String[] wrong= {"wrong1", "wrong2"};
		assertThrows(InvalidParameterException.class,()->{
		DealCard card = new DealCard(wrong, 5);});
		
		String[][] dealCardsInfo = new PayDayCards().getDealCards();
		DealCard card = new DealCard(dealCardsInfo[5], 5);
		assertThrows(InvalidParameterException.class,()->{
			card.setInfo(wrong);});
	}
	
	@SuppressWarnings("unused")
	@Test
	public void test4() {
		String[][] dealCardsInfo = new PayDayCards().getDealCards();
		assertThrows(InvalidParameterException.class,()->{
		DealCard card = new DealCard(dealCardsInfo[0], -5);});
		assertThrows(InvalidParameterException.class,()->{
			DealCard card = new DealCard(dealCardsInfo[0], 20);});
		
		DealCard card = new DealCard(dealCardsInfo[5], 5);
		assertThrows(InvalidParameterException.class,()->{
			card.setCardNumber(-1);});
		assertThrows(InvalidParameterException.class,()->{
			card.setCardNumber(36);});
	}
}

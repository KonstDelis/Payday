package JUnitTests;

import static org.junit.Assert.assertThrows;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import org.junit.Test;

import Model.Cards.Card;
import Model.Cards.CardDeck;
import Model.Cards.DealCard;
import Model.Cards.DeckType;
import Model.Cards.MessageCard;
import View.PayDayCards;
import junit.framework.TestCase;

public class CardDeckTest extends TestCase {
	@Test
	public void test1() {
		CardDeck mailDeck = new CardDeck(DeckType.MESSAGE);
		CardDeck dealDeck = new CardDeck(DeckType.DEAL);
		String[][] mailCardsInfo = new PayDayCards().getMailCards();
		String[][] dealCardsInfo = new PayDayCards().getDealCards();
		for(int i=0; i<48; i++) {
			MessageCard card = new MessageCard(mailCardsInfo[i], i);
			mailDeck.addCardToBottom(card);
		}
		for(int i=0; i<20; i++) {
			DealCard card = new DealCard(dealCardsInfo[i], i);
			dealDeck.addCardToBottom(card);
		}
		assertFalse(mailDeck.isEmpty());
		assertFalse(dealDeck.isEmpty());
		assertEquals(48, mailDeck.getDeckSize());
		assertEquals(20, dealDeck.getDeckSize());
		dealDeck.shuffleDeck();
		mailDeck.shuffleDeck();
		
		while(dealDeck.isEmpty()==false) {
			Card card = dealDeck.getTopCard();
			assertEquals(card.getInfo(), dealCardsInfo[card.getCardNumber()]);
		}
		while(mailDeck.isEmpty()==false) {
			Card card = mailDeck.getTopCard();
			assertEquals(card.getInfo(), mailCardsInfo[card.getCardNumber()]);
		}
		assertTrue(mailDeck.isEmpty());
		assertTrue(dealDeck.isEmpty());
	}
	
	@Test
	public void test2() {
		String[][] mailCardsInfo = new PayDayCards().getMailCards();
		String[][] dealCardsInfo = new PayDayCards().getDealCards();
		ArrayList<Card> mailcards = new ArrayList<Card>();
		ArrayList<Card> dealcards = new ArrayList<Card>();
		for(int i=0; i<48; i++) {
			MessageCard card = new MessageCard(mailCardsInfo[i], i);
			mailcards.add(card);
		}
		for(int i=0; i<20; i++) {
			DealCard card = new DealCard(dealCardsInfo[i], i);
			dealcards.add(card);
		}
		CardDeck mailDeck = new CardDeck(mailcards, DeckType.MESSAGE);
		CardDeck dealDeck = new CardDeck(dealcards, DeckType.DEAL);
		assertFalse(mailDeck.isEmpty());
		assertFalse(dealDeck.isEmpty());
		assertEquals(48, mailDeck.getDeckSize());
		assertEquals(20, dealDeck.getDeckSize());
		dealDeck.shuffleDeck();
		mailDeck.shuffleDeck();
		
		while(dealDeck.isEmpty()==false) {
			Card card = dealDeck.getTopCard();
			assertEquals(card.getInfo(), dealCardsInfo[card.getCardNumber()]);
		}
		while(mailDeck.isEmpty()==false) {
			Card card = mailDeck.getTopCard();
			assertEquals(card.getInfo(), mailCardsInfo[card.getCardNumber()]);
		}
		assertTrue(mailDeck.isEmpty());
		assertTrue(dealDeck.isEmpty());
	}
	
	@SuppressWarnings("unused")
	@Test
	public void test3() {
		CardDeck mdeck = new CardDeck(DeckType.MESSAGE);
		CardDeck ddeck = new CardDeck(DeckType.DEAL);
		assertThrows(IndexOutOfBoundsException.class, ()->{mdeck.getTopCard();});
		assertThrows(IndexOutOfBoundsException.class, ()->{ddeck.getTopCard();});
		
		String[][] mailCardsInfo = new PayDayCards().getMailCards();
		String[][] dealCardsInfo = new PayDayCards().getDealCards();
		for(int i=0; i<48; i++) {
			MessageCard card = new MessageCard(mailCardsInfo[i], i);
			mdeck.addCardToBottom(card);
		}
		for(int i=0; i<20; i++) {
			DealCard card = new DealCard(dealCardsInfo[i], i);
			ddeck.addCardToBottom(card);
		}
		
		assertThrows(IndexOutOfBoundsException.class, ()->{mdeck.addCardToBottom(new MessageCard(mailCardsInfo[0], 0));});
		assertThrows(IndexOutOfBoundsException.class, ()->{ddeck.addCardToBottom(new DealCard(dealCardsInfo[0], 0));});
		
		ArrayList<Card> mailcards = new ArrayList<Card>();
		ArrayList<Card> dealcards = new ArrayList<Card>();
		for(int i=0; i<48; i++) {
			MessageCard card = new MessageCard(mailCardsInfo[i], i);
			mailcards.add(card);
		}
		for(int i=0; i<20; i++) {
			DealCard card = new DealCard(dealCardsInfo[i], i);
			dealcards.add(card);
		}
		mailcards.add(new MessageCard(mailCardsInfo[0], 0));
		dealcards.add(new DealCard(dealCardsInfo[0], 0));
		assertThrows(InvalidParameterException.class, ()->{CardDeck mailDeck = new CardDeck(mailcards, DeckType.MESSAGE);});
		assertThrows(InvalidParameterException.class, ()->{CardDeck dealDeck = new CardDeck(dealcards, DeckType.DEAL);});
	}
	
	@SuppressWarnings("unused")
	@Test
	public void test4() {
		String[][] mailCardsInfo = new PayDayCards().getMailCards();
		String[][] dealCardsInfo = new PayDayCards().getDealCards();
		
		CardDeck mdeck = new CardDeck(DeckType.MESSAGE);
		CardDeck ddeck = new CardDeck(DeckType.DEAL);
		assertThrows(NullPointerException.class, ()->{CardDeck deck = new CardDeck(null);});
		
		ArrayList<Card> initialCards = new ArrayList<Card>();
		initialCards.add(new MessageCard(mailCardsInfo[0],0));
		
		assertThrows(NullPointerException.class, ()->{CardDeck deck = new CardDeck(initialCards, null);});
		initialCards.add(null);
		assertThrows(NullPointerException.class, ()->{CardDeck deck = new CardDeck(initialCards, DeckType.MESSAGE);});
	}
}

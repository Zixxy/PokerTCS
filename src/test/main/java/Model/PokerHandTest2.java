package test.main.java.Model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import main.java.Model.Deck;
import main.java.Model.PokerHand;
import main.java.Model.Deck.Card;

import org.junit.Test;

public class PokerHandTest2 {

	@Test
	public void test() {
		List<Deck.Card> hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SIX, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		PokerHand result = PokerHand.evaluate(hand1);
		assertTrue(result.getHandName() == PokerHand.HandNames.POKER);
	}
	@Test
	public void test1() {
		List<Deck.Card> hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.EIGHT, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SIX, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		PokerHand result = PokerHand.evaluate(hand1);
		assertTrue(result.getHandName() == PokerHand.HandNames.COLOR);
	}
	@Test
	public void test2() {
		List<Deck.Card> hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SIX, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		PokerHand result = PokerHand.evaluate(hand1);
		assertTrue(result.getHandName() == PokerHand.HandNames.STRAIGHT);
	}
	@Test
	public void test3() {
		List<Deck.Card> hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.SPADES));
		PokerHand result = PokerHand.evaluate(hand1);
		assertTrue(result.getHandName() == PokerHand.HandNames.FULL);
	}
	@Test
	public void test4() {
		List<Deck.Card> hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SIX, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SEVEN, Card.Color.SPADES));
		PokerHand result = PokerHand.evaluate(hand1);
		assertTrue(result.getHandName() == PokerHand.HandNames.HIGHCARD);
	}
	@Test
	public void test5() {
		List<Deck.Card> hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.JACK, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		PokerHand result = PokerHand.evaluate(hand1);
		assertTrue(result.getHandName() == PokerHand.HandNames.PAIR);
	}
	@Test
	public void test6() {
		List<Deck.Card> hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.JACK, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		PokerHand result = PokerHand.evaluate(hand1);
		assertTrue(result.getHandName() == PokerHand.HandNames.TWOPAIRS);
	}
	@Test
	public void test7() {
		List<Deck.Card> hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SIX, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		PokerHand result = PokerHand.evaluate(hand1);
		assertTrue(result.getHandName() == PokerHand.HandNames.POKER);
	}
	@Test
	public void test8() {
		List<Deck.Card> hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.HEARTS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		PokerHand result = PokerHand.evaluate(hand1);
		assertTrue(result.getHandName() == PokerHand.HandNames.FOUR);
	}
	@Test
	public void test9() {
		List<Deck.Card> hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SIX, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.HEARTS));
		PokerHand result = PokerHand.evaluate(hand1);
		assertTrue(result.getHandName() == PokerHand.HandNames.THREE);
	}
	@Test
	public void test10() {
		List<Deck.Card> hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SEVEN, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.HEARTS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		PokerHand result = PokerHand.evaluate(hand1);
		assertTrue(result.getHandName() == PokerHand.HandNames.COLOR);
	}
	@Test
	public void test11() {
		List<Deck.Card> hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SIX, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		PokerHand result = PokerHand.evaluate(hand1);
		assertEquals(PokerHand.HandNames.TWOPAIRS, result.getHandName() );
	}
	@Test
	public void test12() {
		List<Deck.Card> hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SIX, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		PokerHand result = PokerHand.evaluate(hand1);
		assertEquals(PokerHand.HandNames.STRAIGHT ,result.getHandName() );
	}

}

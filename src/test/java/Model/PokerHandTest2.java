package java.Model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import Model.Deck;
import Model.PokerHand;
import Model.Deck.Card;

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
	@Test
	public void test14() {
		List<Deck.Card> hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SEVEN, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		PokerHand result1 = PokerHand.evaluate(hand1);
		assertEquals(PokerHand.HandNames.POKER, result1.getHandName() );
		
		hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.HEARTS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SIX, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.CLUBS));
		PokerHand result2 = PokerHand.evaluate(hand1);
		assertEquals(PokerHand.HandNames.POKER, result2.getHandName());
		System.out.println(result1);
		System.out.println(result2);
		assertTrue(result1.compareTo(result2) < 0);
		hand1 = new ArrayList<Deck.Card>();
		
		
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SIX, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.DIAMONDS));
		PokerHand result8 = PokerHand.evaluate(hand1);
		assertTrue(result8.getHandName() == PokerHand.HandNames.POKER);
		assertTrue(result2.compareTo(result8) == 0);
		assertTrue(result2.equals(result8));
		assertTrue(result8.compareTo(result1) > 0);
		hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SIX, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		PokerHand result3 = PokerHand.evaluate(hand1);
		assertTrue(result3.getHandName() == PokerHand.HandNames.STRAIGHT);
		
		hand1 = new ArrayList<Deck.Card>();
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SEVEN, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		PokerHand result13 = PokerHand.evaluate(hand1);
		assertEquals(PokerHand.HandNames.STRAIGHT ,result13.getHandName() );
		assertTrue(result3.compareTo(result13) > 0);
		assertTrue(result1.compareTo(result3) > 0);
		hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.HEARTS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SEVEN, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.HEARTS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		PokerHand result14 = PokerHand.evaluate(hand1);
		assertEquals(PokerHand.HandNames.STRAIGHT ,result13.getHandName() );
		assertTrue(result13.compareTo(result14) == 0);
		assertTrue(result3.compareTo(result14) > 0);
		
		hand1 = new ArrayList<Deck.Card>();
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.SPADES));
		PokerHand result4 = PokerHand.evaluate(hand1);
		assertTrue(result4.getHandName() == PokerHand.HandNames.FULL);

		hand1 = new ArrayList<Deck.Card>();
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.SPADES));
		PokerHand result15 = PokerHand.evaluate(hand1);
		assertTrue(result15.getHandName() == PokerHand.HandNames.FULL);
		assertTrue(result4.compareTo(result15) > 0);
		
		
		hand1 = new ArrayList<Deck.Card>();
		hand1.add(Deck.getSpecifiedCard(Card.Value.QUEEN, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TEN, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.QUEEN, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TEN, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TEN, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.QUEEN, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.JACK, Card.Color.SPADES));
		PokerHand result16 = PokerHand.evaluate(hand1);
		assertTrue(result16.getHandName() == PokerHand.HandNames.FULL);
		
		
		hand1 = new ArrayList<Deck.Card>();
		hand1.add(Deck.getSpecifiedCard(Card.Value.JACK, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.JACK, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.JACK, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.EIGHT, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SEVEN, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		PokerHand result17 = PokerHand.evaluate(hand1);
		assertTrue(result17.getHandName() == PokerHand.HandNames.FULL);
		assertTrue(result16.compareTo(result17) > 0);
		
		hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SIX, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SEVEN, Card.Color.SPADES));
		PokerHand result5 = PokerHand.evaluate(hand1);
		assertTrue(result5.getHandName() == PokerHand.HandNames.HIGHCARD);
		
		hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.JACK, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		PokerHand result6 = PokerHand.evaluate(hand1);
		assertTrue(result6.getHandName() == PokerHand.HandNames.PAIR);

		hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.JACK, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		PokerHand result7 = PokerHand.evaluate(hand1);
		assertTrue(result7.getHandName() == PokerHand.HandNames.TWOPAIRS);
		

		hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.HEARTS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		PokerHand result9 = PokerHand.evaluate(hand1);
		assertTrue(result9.getHandName() == PokerHand.HandNames.FOUR);

		hand1 = new ArrayList<Deck.Card>();
		
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.HEARTS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SIX, Card.Color.SPADES));
		PokerHand result10 = PokerHand.evaluate(hand1);
		assertTrue(result10.getHandName() == PokerHand.HandNames.FOUR);
		assertTrue(result9.compareTo(result10) < 0);
		
		hand1 = new ArrayList<Deck.Card>();
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.TWO, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SEVEN, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.HEARTS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		PokerHand result11 = PokerHand.evaluate(hand1);
		assertTrue(result11.getHandName() == PokerHand.HandNames.COLOR);
		
		hand1 = new ArrayList<Deck.Card>();
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SEVEN, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FOUR, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.HEARTS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		PokerHand result19 = PokerHand.evaluate(hand1);
		assertTrue(result19.getHandName() == PokerHand.HandNames.COLOR);
		assertTrue(result11.compareTo(result19) < 0);
		
		hand1 = new ArrayList<Deck.Card>();
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.ACE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SIX, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		PokerHand result12 = PokerHand.evaluate(hand1);
		assertEquals(PokerHand.HandNames.TWOPAIRS, result12.getHandName() );
		
		hand1 = new ArrayList<Deck.Card>();
		hand1.add(Deck.getSpecifiedCard(Card.Value.KING, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.JACK, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.DIAMONDS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.THREE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		hand1.add(Deck.getSpecifiedCard(Card.Value.SIX, Card.Color.CLUBS));
		hand1.add(Deck.getSpecifiedCard(Card.Value.FIVE, Card.Color.SPADES));
		PokerHand result18 = PokerHand.evaluate(hand1);
		assertEquals(PokerHand.HandNames.TWOPAIRS, result18.getHandName() );
		assertTrue(result12.compareTo(result18) > 0);
		
	}
}

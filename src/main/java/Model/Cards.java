package main.java.Model;

/**
 * Created by Miron on 07.05.14.
 */
import java.util.Collections;
import java.util.List;

public class Cards {
	static class Card implements Comparable<Card>{
		private final int color; // 1 == clubs (trefl)  2 == diamonds (karo) 3 == hearts (kier) 4 == spades(piki)
		private final int value; // 11==J 12==Q 13==K 14==A
		Card(int color, int value){
			this.color = color;
			this.value = value;
		}
		int getColor() {return color;}
		int getValue() {return value;}
		@Override
		public int compareTo(Card card) {
			if(this.value < card.value) return -1;
			if(this.value > card.value) return 1;
			return 0;
		}
		@Override
		public boolean equals(Object card){                               //equals zgodny z compareTo
			if(card ==  null) return false;
			if( card.getClass() != Card.class) return false;
			if( ((Card) card).value != this.value) return false;
			return true;
		}
	}
	@SuppressWarnings("serial")
	static class IncorrectHandException extends Exception{}
	
	static int valueOfHand(List<Card> hand){
		Collections.sort(hand);
		if(hand.get(4).value == hand.get(3).value + 1 && hand.get(3).value == hand.get(2).value + 1 &&
				hand.get(2).value == hand.get(1).value && hand.get(0).value == hand.get(1).value){
			if(hand.get(0).color == hand.get(1).color && hand.get(1).color == hand.get(2).color &&
					hand.get(2).color == hand.get(3).color && hand.get(3).color == hand.get(4).color) return (3160 + hand.get(4).value);
			return 3080 +  hand.get(4).value;
		}
		if(hand.get(4).value == 14 && hand.get(0).value == 2 && hand.get(1).value == 3 && hand.get(2).value == 4 && hand.get(3).value == 5){
			if(hand.get(0).color == hand.get(1).color && hand.get(1).color == hand.get(2).color &&
					hand.get(2).color == hand.get(3).color && hand.get(3).color == hand.get(4).color) return 3160 + hand.get(3).value;
			return 3080 + hand.get(3).value;
		}
		if( hand.get(0).value == hand.get(3).value || hand.get(1).value == hand.get(4).value) return 3140 + hand.get(2).value;
		if(hand.get(0).color == hand.get(1).color && hand.get(1).color == hand.get(2).color &&
				hand.get(2).color == hand.get(3).color && hand.get(3).color == hand.get(4).color) return 3120 +hand.get(4).value;
		if(hand.get(2).value == hand.get(4).value){
			if(hand.get(0).value == hand.get(1).value) return 3100 + hand.get(2).value;
			else return 3060 + hand.get(2).value;
		}
		if(hand.get(0).value == hand.get(2).value){
			if(hand.get(3).value == hand.get(4).value) return 3100 + hand.get(2).value;
			else return 3060 + hand.get(2).value;
		}

		if(hand.get(1).value == hand.get(3).value)return 3060 + hand.get(2).value;
		if(hand.get(4).value == hand.get(3).value){
			if(hand.get(2).value == hand.get(1).value) return 14*14*hand.get(4).value + 14*hand.get(2).value + hand.get(0).value;
			if(hand.get(1).value == hand.get(0).value) return 14*14*hand.get(4).value + 14*hand.get(1).value + hand.get(2).value;
			return 14*hand.get(4).value + hand.get(2).value;
		}
		if(hand.get(4).value == hand.get(3).value){
			if(hand.get(2).value == hand.get(1).value) return 14*14*hand.get(4).value + 14*hand.get(2).value + hand.get(0).value;
			if(hand.get(1).value == hand.get(0).value) return 14*14*hand.get(4).value + 14*hand.get(1).value + hand.get(2).value;
			return 14*hand.get(4).value + hand.get(2).value;
		}
		if(hand.get(3).value == hand.get(2).value){
			if(hand.get(1).value == hand.get(0).value) return 14*14*hand.get(3).value + 14*hand.get(1).value + hand.get(4).value;
			return 14*hand.get(3).value + hand.get(4).value;
		}
		if(hand.get(2).value == hand.get(1).value) return 14*hand.get(2).value + hand.get(4).value;
		if(hand.get(1).value == hand.get(0).value) return 14*hand.get(1).value + hand.get(4).value;
		return hand.get(4).value;
	}

	static int handCmp(List<Card> hand1, List<Card> hand2) throws IncorrectHandException{ //returns - if hand1 is weaker 0 if is equal + if is stronger than hand2
		if(hand1 == null || hand2 == null) throw new IncorrectHandException();
		if(hand1.size() != 5 || hand2.size() != 5) throw new IncorrectHandException();
		return valueOfHand(hand1) - valueOfHand(hand2);
		
	}
	
	
}

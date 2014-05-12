package main.java.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerHand implements Comparable<PokerHand>{
	private PokerHand() {}
	private PokerHand(List<Deck.Card> cards, HandNames handName){
		this.cards = cards;
		this.handName = handName;
	}
	public enum HandNames{
		HIGHCARD,
		PAIR,
		TWOPAIRS,
		THREE,
		STRAIGHT,
		FULL,
		COLOR,
		FOUR,
		POKER
	}
	private HandNames handName;
	private List<Deck.Card> cards;
	public HandNames getHandName(){
        return handName;
    }
	public List<Deck.Card> asList(){
        return cards;
    }
	public Deck.Card getCard(int index){
        return cards.get(index);
    }
    public static PokerHand evaluate(List<Deck.Card> cardsIn) throws IllegalArgumentException{
        PokerHand out = null;
        if(cardsIn == null || cardsIn.size() != 7) throw new IllegalArgumentException() ;
        for(int i =0; i<7; i++) {
            for(int j = i+1; j<7; j++) {
                List<Deck.Card> cards = new ArrayList<Deck.Card>();
                for(int k =0; k<7; k++) {
                    if(k!=i && k!=j)
                        cards.add(cardsIn.get(k));
                }
                PokerHand actual = evaluateFiveCards(cards);
                if(out == null || actual.compareTo(out) > 0)
                    out = actual;
            }
        }
        return out;
    }
    @Override
    public int compareTo(PokerHand o) {
        if(this.handName == o.handName) {
            for(int i = 4; i>=0; i--)
                if(this.cards.get(i).getValue() != o.cards.get(i).getValue())
                    return this.cards.get(i).getValue().compareTo(o.cards.get(i).getValue());
        }
        return this.handName.compareTo(o.handName);
    }
    @Override
    public boolean equals(Object o) {
        if(o == null || o.getClass() != PokerHand.class) return false;
        return this.compareTo((PokerHand) o) == 0;
    }
    @Override
    public int hashCode() {
        int out = this.handName.hashCode();
        for(int i = 0; i<5; i++) {
            out*=31;
            out+=this.cards.get(i).getValue().hashCode();
        }
        return out;
    }


	private static boolean isPoker(List<Deck.Card> cardsIn){
		Collections.sort(cardsIn);
		return isColor(cardsIn) && isStraight(cardsIn);
	}
	private static boolean isFour(List<Deck.Card> cardsIn){
		Collections.sort(cardsIn);
		if( cardsIn.get(0).getValue() == cardsIn.get(3).getValue()){
			cardsIn.add(0,cardsIn.remove(4));
			return true;
		}
		return  cardsIn.get(1).getValue() == cardsIn.get(4).getValue();
	}
	private static boolean isColor(List<Deck.Card> cardsIn ){
		Collections.sort(cardsIn);
		for(int i=1; i<5; ++i){
			if( cardsIn.get(0).getColor() != cardsIn.get(i).getColor()) return false;
		}
		return true;
	}
	private static boolean isFull(List<Deck.Card> cardsIn){
		Collections.sort(cardsIn);
		if(cardsIn.get(2).getValue() != cardsIn.get(3).getValue()){
			cardsIn.add(0,cardsIn.remove(4));
			cardsIn.add(0,cardsIn.remove(4));
		}
		if(cardsIn.get(2).getValue() == cardsIn.get(4).getValue() && 
				cardsIn.get(0).getValue() == cardsIn.get(1).getValue()	) 
			return true;
		return false;
	}
	private static boolean isStraight(List<Deck.Card> cardsIn ){
		Collections.sort(cardsIn);
		for(int i=0; i < 3; ++i){
			if( cardsIn.get(i).getValue().id +1 != cardsIn.get(i+1).getValue().id) return false;
		}
		if(cardsIn.get(0).getValue() != Deck.Card.Value.TWO || cardsIn.get(4).getValue() != Deck.Card.Value.ACE) {
			if(cardsIn.get(3).getValue().id+1 != cardsIn.get(4).getValue().id)
				return false;
		}
		else {
			cardsIn.add(0,cardsIn.remove(4));
		}
		return true;
	}
	private static boolean isThree(List<Deck.Card> cardsIn){
		Collections.sort(cardsIn);
		for(int i=0; i<3; ++i){
			if(cardsIn.get(i).getValue() == cardsIn.get(i+2).getValue()){
				for (int j=0; j<3; ++j){
					cardsIn.add(4, cardsIn.remove(i));
				}
				return true;
			}
		}
		return false;
	}
	private static boolean isTwoPairs(List<Deck.Card> cardsIn){
		Collections.sort(cardsIn);
		for(int i=1; i<=4; ++i){
			if(cardsIn.get(i).getValue() != cardsIn.get(i-1).getValue() && 
					(i == 4 || cardsIn.get(i).getValue() != cardsIn.get(i+1).getValue())) {
				cardsIn.add(0, cardsIn.remove(i));
				break;
			}
		}
		return cardsIn.get(1).getValue() == cardsIn.get(2).getValue() && 
				cardsIn.get(3).getValue() == cardsIn.get(4).getValue();
	}
	private static boolean isPair(List<Deck.Card> cardsIn){
		Collections.sort(cardsIn);
		for(int i=0; i<4; ++i){
			if(cardsIn.get(i).getValue() == cardsIn.get(i+1).getValue()){
				for (int j=0; j<2; ++j){
					cardsIn.add(4, cardsIn.remove(i));
				}
				return true;
			}
		}
		return false;
	}
	private static PokerHand evaluateFiveCards( List<Deck.Card> cardsIn) throws IllegalArgumentException{
		if(cardsIn == null || cardsIn.size() != 5) throw new IllegalArgumentException() ;
		if(isPoker(cardsIn)) return new PokerHand(cardsIn, HandNames.POKER);
		if(isFour(cardsIn)) return new PokerHand(cardsIn, HandNames.FOUR);
		if(isColor(cardsIn)) return new PokerHand(cardsIn, HandNames.COLOR);
		if(isFull(cardsIn)) return new PokerHand(cardsIn, HandNames.FULL);
		if(isStraight(cardsIn)) return new PokerHand(cardsIn, HandNames.STRAIGHT);
		if(isThree(cardsIn)) return new PokerHand(cardsIn, HandNames.THREE);
		if(isTwoPairs(cardsIn)) return new PokerHand(cardsIn, HandNames.TWOPAIRS);
		if(isPair(cardsIn)) return new PokerHand(cardsIn, HandNames.PAIR);
		return new PokerHand(cardsIn, HandNames.HIGHCARD);

	}
	
}

package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dakurels on 07.05.14.
 */
public class Deck {
    public static class Card implements Comparable<Card>{
        public enum Value {
            TWO(2),
            THREE(3),
            FOUR(4),
            FIVE(5),
            SIX(6),
            SEVEN(7),
            EIGHT(8),
            NINE(9),
            TEN(10),
            JACK(11),
            QUEEN(12),
            KING(13),
            ACE(14);
            Value(int id) {
                this.id = id;
            }
            public final int id;
        }
        public enum Color {
            CLUBS(0),
            DIAMONDS(1),
            HEARTS(2),
            SPADES(3);
            Color(int id) {
                this.id = id;
            }
            public final int id;
        }
        private Value value;
        private Color color;
        public Value getValue() {
            return value;
        }
        public Color getColor() {
            return color;
        }

        private Card() {}
        private Card(Value value, Color color) {
            this.value = value;
            this.color = color;
        }

        public int getMacieksId() {
            return 20*color.id + value.id;
        }

        @Override
        public String toString() {
            String value = this.value.toString();
            String color = this.color.toString();
            return value.charAt(0) + value.substring(1).toLowerCase() + " of " + color.toLowerCase();
        }
        @Override
        public int compareTo(Card card) {
            if(this.value == card.value)
                return this.color.compareTo(card.color);
            return this.value.compareTo(card.value);
        }
        @Override
        public boolean equals(Object card) {
            if(card == null || card.getClass() != Card.class)
                return false;
            return this.compareTo((Card) card) == 0;
        }
        @Override
        public int hashCode() {
            return this.value.hashCode()*31 + this.color.hashCode();
        }
    }

    private static List<Card> standardCards;
    private List<Card> deckCards;
    static {
        standardCards = new ArrayList<Card>();
        for(Card.Color color: Card.Color.values()) {
            for(Card.Value value: Card.Value.values()) {
                standardCards.add(new Card(value, color));
            }
        }

    }

    public static Card getSpecifiedCard(Card.Value value, Card.Color color) {
        for(Card card: standardCards) {
            if(card.value == value && card.color == color)
                return card;
        }
        throw new IllegalArgumentException();
    }
    public static Card getSpecifiedCard(String sValue, String sColor) {
        String arr[] = {sValue, sColor};
        Card.Color color = null;
        Card.Value value = null;
        for(Card.Value valueTry: Card.Value.values())
            if(valueTry.toString().toLowerCase().equals(arr[0]))
                value = valueTry;
        for(Card.Color colorTry: Card.Color.values())
            if(colorTry.toString().toLowerCase().equals(arr[1]))
                color = colorTry;
        return getSpecifiedCard(value, color);
    }
    public static Card getSpecifiedCard(String fullName) {
        String arr[] = fullName.toLowerCase().split(" of ");
        return getSpecifiedCard(arr[0], arr[1]);
    }
    public Deck() {
        deckCards = new ArrayList<Card>();
        deckCards.addAll(standardCards);
        Collections.shuffle(deckCards);
    }
    public boolean isEmpty() {
        return deckCards.isEmpty();
    }
    public Card getNextCard() {
        Card out = deckCards.get(0);
        deckCards.remove(0);
        return out;
    }
}

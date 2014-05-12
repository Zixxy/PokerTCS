package main.java.View;

import main.java.Adapter.AdapterInterface;
import main.java.Model.Deck;

import java.util.Scanner;

/**
 * Created by Dakurels on 2014-05-12.
 */
public class CommandLine implements ViewInterface{

    public CommandLine(final AdapterInterface adapt) {
        Runnable run = new Runnable() {
            private AdapterInterface adapter = adapt;
            @Override
            public void run() {
                Scanner in = new Scanner(System.in);
                while(true) {
                    System.out.println("Write id x (e.g. 0 1), where id (-1 for close terminal end) is player id and x is: 0: Fold\n1: Raise\n2: Check");
                    int id = in.nextInt();
                    if(id == -1)
                        break;
                    int type = in.nextInt();
                    int cash = 0;
                    if(type == 1) {
                        System.out.println("Amount of cash:");
                        cash = in.nextInt();
                    }
                    if(type == 0)
                        adapter.fold(id);
                    else if(type == 1)
                        adapter.raise(id, ""+cash);
                    else if(type == 2)
                        adapter.check(id);
                }

            }
        };
        Thread t = new Thread(run);
        t.start();
    }

    @Override
    public void addPlayer(String name, int id) {
        System.out.println("Added player " + name + ", id: " + id);
    }

    @Override
    public void removePlayer(int id) {
        System.out.println("Removed player: " + id);
    }

    @Override
    public void updatePlayerCash(int id, int cash) {
        System.out.println("Player " + id + ": " + cash);

    }

    @Override
    public void addThreeCardsOnTable(Deck.Card firstCard, Deck.Card secondCard, Deck.Card thirdCard) {
        System.out.println("Three cards:");
        System.out.println(firstCard.toString());
        System.out.println(secondCard.toString());
        System.out.println(thirdCard.toString());

    }

    @Override
    public void addOneCard(Deck.Card card) {
        System.out.println("One card:");
        System.out.println(card.toString());

    }

    @Override
    public void clearTable() {
        System.out.println("Table cleared");
    }

    @Override
    public void updatePlayerHand(Deck.Card firstCard, Deck.Card secondCard) {
        System.out.println("Your hand :");
        System.out.println(firstCard.toString());
        System.out.println(secondCard.toString());

    }

    @Override
    public void updatePlayerLinedCash(int id, int cash) {
        System.out.println("Player " + id + " lined cash: " + cash);

    }

    @Override
    public void sendMessage(String text) {
        System.out.println(text);
    }
}

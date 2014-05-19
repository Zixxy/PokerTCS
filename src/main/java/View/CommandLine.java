package main.java.View;

import main.java.Adapter.AdapterInterface;
import main.java.Model.Deck;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Dakurels on 2014-05-12.
 */
public class CommandLine implements ViewInterface{

    private ArrayList<Integer> players;

    private AdapterInterface adapter;
    public CommandLine(final AdapterInterface adapt) {
        players = new ArrayList<Integer>();
        adapter = adapt;
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
        players.add(id);
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
    public void removePlayersLinedCash(int id) {
        System.out.println("Remove player's " + id + "lined cash");
    }

    @Override
    public void sendMessage(String text) {
        System.out.println(text);
    }

    @Override
    public void startNewRound() {
        for(Integer playerId: players) {
            Deck.Card tab[] = adapter.getHandCards(playerId);
            System.out.println("Player " + playerId + "cards: ");
            System.out.println(tab[0].toString());
            System.out.println(tab[1].toString());
        }
    }

    @Override
    public void setPot(int cash) {

    }

	@Override
	public void updateActualPlayer(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateResignedPlayer(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePlayingPlayer(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSmallBLind(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBigBlind(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDealer(int id) {
		// TODO Auto-generated method stub
		
	}
}

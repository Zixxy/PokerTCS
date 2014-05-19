package main.java.Adapter;

import main.java.Model.Deck;
import main.java.Model.ModelInterface;
import main.java.View.ViewInterface;

/**
 * Created by dakurels on 05.05.14.
 */

public interface AdapterInterface {
    public void fold(int playerId);
    public void check(int playerId);
    public void raise(int playerId, String amount);
    public void resign(int playerId);


    public void addModel(ModelInterface mod);
    public void addView(ViewInterface view);


    //public Deck.Card[] getHandCards(int playerId);

    public int getActualStage();

    public void addPlayer(String name, int id);
    public void removePlayer(int id);
    public void updatePlayerCash(int id, int cash);
    public void updatePlayerLinedCash(int id, int cash);
    public void updatePlayerHand(int playerId,Deck.Card cards[]);
    public void setPot(int pot);
    public void addThreeCards(Deck.Card cards[]);
    public void addOneCard(Deck.Card card);
    public void clearTable();
    public void sendMessage(String text);
    public void startNewRound();

}

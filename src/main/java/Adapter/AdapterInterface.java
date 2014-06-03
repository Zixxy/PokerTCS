package Adapter;

import Model.Deck;
import Model.ModelInterface;
import View.ViewInterface;

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
    public void sendMyMessageToEveryBody(String text);
    public void start();
    public void addPlayer(String name, int id);
    public void removePlayer(int id);
    public void updateActualPlayer(int id);
    public void updateResignPlayer(int id);
    public void updatePlayerCash(int id, int cash);
    public void updatePlayerLinedCash(int id, int cash);
    public void updatePlayerHand(int playerId,Deck.Card cards[]);
    public void setPot(int pot);
    public void addThreeCards(Deck.Card cards[]);
    public void addOneCard(Deck.Card card);
    public void clearTable();
    public void sendMessage(String text);
    public void startNewRound();
	public void setLastMove(int id, int move);
    public void setStartedAmount(int amount);
	public void showCards(int playerId, int firstCardNumber, int secondCardNumber);
    public void guiAddTable(int numberOfTable);
    public void guiRemoveTable(int numberOfTable);
    public void updateNumberOfPlayers(int numberOfTable, int currentNumberOfPlayers);
    public void addTable();
    public void addPlayerToTable(int tableIndex);
    public void removePlayerFromTable(int tableIndex);
    public void setPlayerId(int id);
    public void setPlayerName(String name);
    public void start(int id);
    public void guiClearTableList();
}

package main.java.View;

import main.java.Model.Deck.Card;

public interface ViewInterface {
	public void addPlayer(String name, int id);
	public void removePlayer(int id);
	public void updatePlayerCash(int id, int cash);
	public void addThreeCardsOnTable(Card firstCard, Card secondCard, Card thirdCard);
	public void addOneCard(Card card);
	/**
	 * removes cards from table. This method also for each player removes lined cash.
	 */
	public void clearTable();
	/**
	 * update Player Hand method. If nulls are given method clears player hand.
	 */
	public void updatePlayerHand(Card firstCard, Card secondCard);
	/**
	 * it should be used to update player lined cash
	 */
	public void updatePlayerLinedCash(int id, int cash);
	/**
	 * it should be used while adding each stack of money to the common pot, so that it becomes clear field.
	 */
	public void removePlayersLinedCash(int id, int cash);
    public void sendMessage(String text);
}
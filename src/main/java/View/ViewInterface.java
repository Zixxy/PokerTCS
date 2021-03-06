package View;

import Model.Deck.Card;

public interface ViewInterface extends TableListInterface{
	public void almostConstructor();
	public void addPlayer(String name, int id, int image);
	public void removePlayer(int id);
	public void updatePlayerCash(int id, int cash);
	public void updateActualPlayer(int id);  // set highlight to player with id
	public void updateNormalPlayer(int id);  //remove highlight to player with id
	public void updateResignedPlayer(int id); //remove face of player with id
	public void addThreeCardsOnTable(Card firstCard, Card secondCard, Card thirdCard);
	public void addOneCard(Card card);
	public void setPlayerId(int id);
    public void guiExitTable();
	/**
	 * Method:
	 * -removes cards from table.
	 * -for each player removes lined cash.
	 * -sets pot to $0
	 */
	public void clearTable();
	public void clearTableList();
	/**
	 * update Player Hand method. If nulls are given method clears player hand.
	 */
	public void updatePlayerHand(int id, Card firstCard, Card secondCard);
	/**
	 * it should be used to update player lined cash
	 */
	public void updatePlayerLinedCash(int id, int cash);
	/**
	 * it should be used while adding each stack of money to the common pot, so that it becomes clear field.
	 */
	public void removePlayersLinedCash(int id);
    public void sendMessage(String text);
    public void startNewRound();
    /**
     * it sets pot on table.
     */
    public void setPot(int cash);
    
    /**
     * It sets the last move of the player.
     * @param id
     * @param move
     */
	public void setLastMove(int id, int move);
	
	public void showCards(int playerId, int firstCardNumber, int secondCardNumber);
	public void guiClearTableList();


}
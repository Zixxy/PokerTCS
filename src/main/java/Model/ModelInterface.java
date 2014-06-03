package Model;

import Model.Deck.Card;

public interface ModelInterface {
	//get
	boolean isStarted(); // returns true if game has started
	int getLimit(); //returns value of limit or -1 if is no limit
	int getLimitVariant();  //returns -1 if fixed limit 0 if pot limit 1 if no limit
	int size();  //returns amount of players
	boolean isInGame(int playerId); //returns true if player is still in game
	int getSmallBlind();  //returns index of small blind
    void setSmallBlind(int small);
	int getBigBlind();    //returns index of big blind
    void setBigBlind(int big);
	int getActualPlayer();    //returns index of player, whose move is now
	int getMoney(int playerId);  //returns amount of many of player
	//set
	void setStartedAmount(int amount);
	int addPlayer(String name);
    void setPlayerName(String name);
	void removePlayer(int playerId); //host of table removes player
	void start(); //starts the game
    @Deprecated
    void start(int id);
    void setAnte(int arg);//sets Ante
   // Deck.Card[] getHandCards(int playerId);//
    int getActualStage();

	//game
	void fold(int playerId);
	void check(int playerId);
	void raise(int playerId, int amount);
	void resign(int playerId);   // player is resigning to game
	void showPlayerCards(int playerId, Card[] cards);
    void addTable();
    void removePlayerFromTable();
    void addPlayerToTable(int tableIndex);
    void sendOutMessage(String text);
}


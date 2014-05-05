package Model;

public interface Model {
	//get
	boolean isStarted() throws Exception; // returns true if game has started
	int getLimit() throws Exception; //returns value of limit or -1 if is no limit
	int getLimitVariant() throws Exception;  //returns -1 if fixed limit 0 if pot limit 1 if no limit
	int size() throws Exception;  //returns amount of players
	boolean isInGame(int playerId) throws Exception; //returns true if player is still in game
	int getSmallBlind() throws Exception;  //returns index of small blind
	int getBigBlind() throws Exception;    //returns index of big blind
	int getActualPlayer() throws Exception;    //returns index of player, whose move is now
	int getMoney(int playerId) throws Exception;  //returns amount of many of player
	//set
	void setLimitVariant(int variant)throws Exception; // -1 if fixed limit 0 if pot limit 1 if no limit
	void setLimit(int limit) throws Exception; //set limit
	void setStartedAmount(int amount) throws Exception;
	void addPlayer(String name, int playerId) throws Exception;
	void removePlayer(int playerId) throws Exception; //host of table removes player
	void start() throws Exception; //starts the game

	//game
	void fold(int playerId) throws Exception;
	void check(int playerId) throws Exception;
	void raise(int playerId, int amount) throws Exception;
	void resign(int playerId) throws Exception;   // player is resigning to game
}


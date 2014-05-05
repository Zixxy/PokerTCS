package Model;

import java.util.ArrayList;

/**
 * Created by bartek on 05.05.14.
 */
public class ModelOne implements ModelInterface {
    private boolean started;//is game started?
    private int numberOfPlayers;
    private ArrayList<Player> players;
    private int limit;
    private int currentPlayerId;
    private int numberInGame;

    public ModelOne(){
        this.started=false;
        this.numberOfPlayers=0;
        this.players= new ArrayList<Player>();
        this.limit=0;
    }
    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public int getLimitVariant() {
        return 0;
    }

    @Override
    public int size() {
        return numberOfPlayers;
    }

    @Override
    public boolean isInGame(int playerId) {
        return players.get(playerId).getInGame();
    }

    @Override
    public int getSmallBlind() {
        return 0;
    }

    @Override
    public int getBigBlind() {
        return 0;
    }

    @Override
    public int getActualPlayer() {
        return currentPlayerId;
    }

    @Override
    public int getMoney(int playerId) {
        return players.get(playerId).getMoney();
    }

    @Override
    public void setLimitVariant(int variant) {

    }

    @Override
    public void setLimit(int limit) {

    }

    @Override
    public void setStartedAmount(int amount) {

    }

    @Override
    public void addPlayer(String name) {
        players.add(new Player(name, 1000));
        numberOfPlayers=players.size();
    }

    @Override
    public void removePlayer(int playerId) {
        if(players.get(playerId).getInGame()==true) numberInGame--;
        players.remove(playerId);
        numberOfPlayers=players.size();
    }

    @Override
    public void start() {
        for(Player p:players){
            p.setMoney(p.getMoney()-10);
            p.setOffer(10);
            p.setInGame(true);
        }
        this.limit=10;
        this.numberInGame=numberOfPlayers;
    }

    @Override
    public void fold(int playerId) {
        //zabezpieczyc tak zeby ostatni gracz nie mogl pasowac - on wygrywa
        if(currentPlayerId==playerId) {
            players.get(playerId).setInGame(false);
            numberInGame--;
            if(numberInGame==1) this.won();
        }
        while(players.get(currentPlayerId).getInGame()==false) {
            currentPlayerId=(currentPlayerId+1)%numberOfPlayers;
        }

    }

    @Override
    public void check(int playerId) {
        //zabezpieczyc aby ktos kto nie ma wystarczajaco duzej ilosci gotowki nie mogl sprawdzic
        if(currentPlayerId==playerId) {
            players.get(playerId).setMoney(players.get(playerId).getMoney()-(this.limit-players.get(playerId).getOffer()));
            players.get(playerId).setOffer(this.limit);
        }
        while(players.get(currentPlayerId).getInGame()==false) {
            currentPlayerId=(currentPlayerId+1)%numberOfPlayers;
        }
    }

    @Override
    public void raise(int playerId, int amount) {
        //rowniez zabezpieczyc przed brakiem gotowki
        if(currentPlayerId==playerId) {
            players.get(playerId).setMoney(players.get(playerId).getMoney()-(amount-players.get(playerId).getOffer()));
            players.get(playerId).setOffer(amount);
            this.limit=amount;
        }
        while(players.get(currentPlayerId).getInGame()==false) {
            currentPlayerId=(currentPlayerId+1)%numberOfPlayers;
        }
    }


    @Override
    public void resign(int playerId) {

    }

    private void won(){
        //TO DO, uruchamiane gdy w grze zostal tylko jeden gracz(zwyciezca).
    }
}

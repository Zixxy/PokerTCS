package main.java.Model;


import main.java.Adapter.AdapterInterface;

import java.util.ArrayList;

/**
 * Created by bartek on 05.05.14.
 */
public class ModelOne implements ModelInterface {
    private AdapterInterface adapter;
    private boolean started;//is game started?
    private int numberOfPlayers;
    private ArrayList<Player> players;
    private int limit;
    private int onTable;
    private int currentPlayerId;
    private int numberInGame;
    private int raisingPlayerId;

    public ModelOne(AdapterInterface arg1){
        this.started=false;
        this.numberOfPlayers=0;
        this.players= new ArrayList<Player>();
        this.limit=0;
        this.adapter=arg1;
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

    public int getNumberOfPlayers(){
        return numberOfPlayers;
    }

    public int getNumberInGame(){
        return numberInGame;
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
        if(!this.started) {
            players.add(new Player(name, 1000));
            numberOfPlayers = players.size();
            adapter.addPlayer(name, numberOfPlayers - 1);
        }
    }

    @Override
    public void removePlayer(int playerId) {
        if(players.get(playerId).getInGame()==true) numberInGame--;
        players.remove(playerId);
        numberOfPlayers=players.size();
        adapter.removePlayer(playerId);
    }

    @Override
    public void start() {
        started=true;
        startRound();
    }

    @Override
    public void fold(int playerId) {
        //zabezpieczyc tak zeby ostatni gracz nie mogl pasowac - on wygrywa
        if(currentPlayerId==playerId) {
            players.get(playerId).setInGame(false);
            numberInGame--;
            adapter.sendMessage("Gracz " + players.get(playerId).getName() +" pasuje\n");
            while (players.get(currentPlayerId).getInGame() == false) {
                currentPlayerId = (currentPlayerId + 1) % numberOfPlayers;
            if (numberInGame == 1) won();
            else {
                if (currentPlayerId == raisingPlayerId) checkItAll();
                }
            }
        }


    }

    @Override
    public void check(int playerId) {
        //zabezpieczyc aby ktos kto nie ma wystarczajaco duzej ilosci gotowki nie mogl sprawdzic
        if(currentPlayerId==playerId) {
            onTable+=this.limit - players.get(playerId).getOffer();
            players.get(playerId).setMoney(players.get(playerId).getMoney() - (this.limit - players.get(playerId).getOffer()));
            players.get(playerId).setOffer(this.limit);
            adapter.updatePlayerLinedCash(playerId, players.get(playerId).getOffer());
            adapter.updatePlayerCash(playerId, players.get(playerId).getMoney());

            while (players.get(currentPlayerId).getInGame() == false) {
                currentPlayerId = (currentPlayerId + 1) % numberOfPlayers;
            }
            if(currentPlayerId==raisingPlayerId) checkItAll();
        }
    }

    @Override
    public void raise(int playerId, int amount) {
        //rowniez zabezpieczyc przed brakiem gotowki
        if(currentPlayerId==playerId) {
            onTable+=(amount - players.get(playerId).getOffer());
            players.get(playerId).setMoney(players.get(playerId).getMoney() - (amount - players.get(playerId).getOffer()));
            players.get(playerId).setOffer(amount);
            adapter.updatePlayerLinedCash(playerId, players.get(playerId).getOffer());
            adapter.updatePlayerCash(playerId, players.get(playerId).getMoney());
            this.limit = amount;
            this.raisingPlayerId = playerId;

            while (players.get(currentPlayerId).getInGame() == false) {
                currentPlayerId = (currentPlayerId + 1) % numberOfPlayers;
            }

        }
    }


    @Override
    public void resign(int playerId) {
        int temporaryCurrentPlayerId=currentPlayerId;
        currentPlayerId=playerId;
        fold(playerId);
        currentPlayerId=temporaryCurrentPlayerId;
        removePlayer(playerId);
    }

    private void won(){
        while(players.get(currentPlayerId).getInGame()==false) {
            currentPlayerId=(currentPlayerId+1)%numberOfPlayers;
        }
        adapter.sendMessage("Koniec rundy, wygrał gracz" + players.get(currentPlayerId).getName() + "\n Rozpoczynanie nowej rundy \n");
        players.get(currentPlayerId).setMoney(players.get(currentPlayerId).getMoney()+onTable);
        adapter.updatePlayerCash(currentPlayerId, players.get(currentPlayerId).getMoney());
        startRound();
    }

    private void startRound(){
        adapter.clearTable();
        int i=0;
        for(Player p:players){
            p.setMoney(p.getMoney()-10);
            adapter.updatePlayerCash(i, p.getMoney());
            p.setOffer(10);
            adapter.updatePlayerCash(i, p.getOffer());
            p.setInGame(true);

            i++;
        }
        currentPlayerId=0;
        this.limit=10;
        this.numberInGame=numberOfPlayers;
        this.onTable=10*numberInGame;
    }

    private void checkItAll(){
        int i=0;
        for(Player p:players){
            if(p.getInGame()) p.setMoney(p.getMoney()+(onTable/numberInGame));
            adapter.updatePlayerCash(i, p.getMoney());
            i++;
        }
        startRound();
    };
}

package main.java.Model;


import main.java.Adapter.AdapterInterface;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bartek on 05.05.14.
 */
public class ModelOne implements ModelInterface {
    private int actualId;
    private AdapterInterface adapter;
    private boolean started;//is game started?
    private int numberOfPlayers;
    private ArrayList<Player> players;
    private int limit;
    private int onTable;
    private int currentPlayerId;
    private int numberInGame;
    private int raisingPlayerId;
    private int ante;
    private Deck deck;
    private Deck.Card cards[];
    private int stage;
    private int resigned;
    private int startedAmount;


    public ModelOne(AdapterInterface arg1){
        actualId = 0;
        this.started=false;
        this.numberOfPlayers=0;
        this.players= new ArrayList<Player>();
        this.limit=0;
        this.adapter=arg1;
        this.resigned=0;
        this.cards = new Deck.Card[5];
        this.ante=10;
        this.startedAmount=1000;
    }//
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
    public void setAnte(int arg1){
        ante=arg1;
    }

    @Override
    public Deck.Card[] getHandCards(int playerId){
        return players.get(playerId).getCards();
    }

    @Override
    public void setStartedAmount(int amount) {
        startedAmount=amount;

    }

    @Override
    public void addPlayer(String name) {
        if(!this.started) {
            players.add(new Player(name, startedAmount));
            numberOfPlayers = players.size();
            adapter.addPlayer(name, numberOfPlayers - 1);
        }
    }

    @Override
    public void removePlayer(int playerId) {
        if(players.get(playerId).getInGame()==true) numberInGame--;
        players.get(playerId).setResigned(true);

        resigned++;
        numberOfPlayers--;
        adapter.removePlayer(playerId);
    }//

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
            if(players.get(playerId).getMoney() < this.limit - players.get(playerId).getOffer())
                return;
            adapter.sendMessage("Gracz " + players.get(playerId).getName() +" sprawdza\n");
            onTable+=this.limit - players.get(playerId).getOffer();
            players.get(playerId).setMoney(players.get(playerId).getMoney() - (this.limit - players.get(playerId).getOffer()));
            players.get(playerId).setOffer(this.limit);
            adapter.updatePlayerLinedCash(playerId, players.get(playerId).getOffer());
            adapter.updatePlayerCash(playerId, players.get(playerId).getMoney());

            currentPlayerId = (currentPlayerId + 1) % numberOfPlayers;
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
            if(amount > players.get(playerId).getMoney())
                return;
            if(amount + players.get(playerId).getOffer() < limit)
                return;
            if(amount + players.get(playerId).getOffer() == limit) {
                check(playerId);
                return;
            }
            adapter.sendMessage("Gracz " + players.get(playerId).getName() +" podbija o " + amount + "\n");
            onTable+=amount;
            players.get(playerId).setMoney(players.get(playerId).getMoney() -amount);
            players.get(playerId).setOffer(players.get(playerId).getOffer()+amount);
            adapter.updatePlayerLinedCash(playerId, players.get(playerId).getOffer());
            adapter.updatePlayerCash(playerId, players.get(playerId).getMoney());
            this.limit +=amount;
            this.raisingPlayerId = playerId;
            currentPlayerId = (currentPlayerId + 1) % numberOfPlayers;
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
        stage=0;
        for(int i=0;i<5;i++) cards[i]=null;
        deck=new Deck();

        int i=0;
        for(Player p:players){
            p.setCards(deck);
            p.setMoney(p.getMoney()- ante);
            adapter.updatePlayerCash(i, p.getMoney());
            p.setOffer(ante);
            adapter.updatePlayerLinedCash(i, p.getOffer());
            p.setInGame(true);
            i++;
        }
        adapter.startNewRound();
        currentPlayerId=0;
        this.limit= ante;
        this.numberInGame=numberOfPlayers-resigned;
        this.onTable= ante *numberInGame;
    }


    private PokerHand getPlayerHand(Player player) {
        List<Deck.Card> cards = new ArrayList<Deck.Card>();
        cards.addAll(Arrays.asList(player.getCards()));
        cards.addAll(Arrays.asList(this.cards));
        return PokerHand.evaluate(cards);
    }

    private void checkItAll(){
        if (stage==0){
            cards[0]=deck.getNextCard();
            cards[1]=deck.getNextCard();
            cards[2]=deck.getNextCard();
            adapter.addThreeCards(cards);
        }
        if (stage==1){
            cards[3]=deck.getNextCard();
            adapter.addOneCard(cards[3]);
        }
        if (stage==2){
            cards[4]=deck.getNextCard();
            adapter.addOneCard(cards[4]);
        }
        if (stage==3) {
            List<Player> inGamePlayers = new ArrayList<Player>();
            for(Player p:players) {
                if(p.getInGame()) {
                    inGamePlayers.add(p);
                }
            }
            Player max = inGamePlayers.get(0);
            for(Player player: inGamePlayers) {
                if (getPlayerHand(player).compareTo(getPlayerHand(max)) > 0)
                    max = player;
            }
             for(Player player: inGamePlayers) {
                 if (getPlayerHand(player).compareTo(getPlayerHand(max)) < 0)
                     player.setInGame(false);
             }
            won();
        }
        stage++;
        raisingPlayerId=currentPlayerId;
    }
}

package main.java.Model;


import main.java.Adapter.AdapterInterface;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bartek on 05.05.14.
 */
public class ModelOne implements ModelInterface {
    private int pot;
    private int actualId;
    private int smallBlindPosition;
    private int bigBlindPosition;
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
    private int smallBlind;
    private int bigBlind;


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
        this.smallBlindPosition = 0;
        this.smallBlind = 0;
        this.bigBlind = 0;
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
        return smallBlind;
    }

    @Override
    public void setSmallBlind(int small) {
        this.smallBlind = small;

    }

    @Override
    public int getBigBlind() {
        return bigBlind;
    }

    @Override
    public void setBigBlind(int big) {
        this.bigBlind = big;

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
    public void setAnte(int arg1){
        ante=arg1;
    }

    //@Override
    /*public Deck.Card[] getHandCards(int playerId){
        return players.get(playerId).getCards();
    }*/

    @Override
    public void setStartedAmount(int amount) {
        startedAmount=amount;

    }

    @Override
    public void addPlayer(String name) {
        if(!this.started) {
            players.add(new Player(name, startedAmount));
            numberOfPlayers++;
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
        if (!started) {
        	started=true;
        startRound();
       }
    }

    @Override
    public void fold(int playerId) {
        if (!started) return;
        if(currentPlayerId==playerId) {
            boolean raising = false;
            if(currentPlayerId == raisingPlayerId)
                raising = true;
            players.get(playerId).setInGame(false);
            numberInGame--;
            adapter.sendMessage("Gracz " + players.get(playerId).getName() +" pasuje\n");
            while (players.get(currentPlayerId).getInGame() == false)
                currentPlayerId = (currentPlayerId + 1) % players.size();
            adapter.updateActualPlayer(currentPlayerId);
            if (numberInGame == 1) won();
            else if (currentPlayerId == raisingPlayerId) checkItAll();
            if(raising)
                raisingPlayerId = currentPlayerId;
        }
    }

    @Override
    public void check(int playerId) {
        if (!started) return;
        if(currentPlayerId==playerId) {
            if(players.get(playerId).getMoney() < this.limit - players.get(playerId).getOffer())
                return;
            adapter.sendMessage("Gracz " + players.get(playerId).getName() +" sprawdza\n");
            onTable+=this.limit - players.get(playerId).getOffer();
            players.get(playerId).setMoney(players.get(playerId).getMoney() - (this.limit - players.get(playerId).getOffer()));
            players.get(playerId).setOffer(this.limit);
            adapter.updatePlayerLinedCash(playerId, players.get(playerId).getOffer());
            adapter.updatePlayerCash(playerId, players.get(playerId).getMoney());
            currentPlayerId = (currentPlayerId + 1) % players.size();
            while (players.get(currentPlayerId).getInGame() == false) {
                currentPlayerId = (currentPlayerId + 1) % players.size();
            }
            adapter.updateActualPlayer(currentPlayerId);
            if(currentPlayerId==raisingPlayerId) checkItAll();
        }
    }

    @Override
    public void raise(int playerId, int amount) {
        if (!started) return;
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
            this.limit = players.get(playerId).getOffer();
            this.raisingPlayerId = playerId;
            currentPlayerId = (currentPlayerId + 1) % players.size();
            while (players.get(currentPlayerId).getInGame() == false) {
                currentPlayerId = (currentPlayerId + 1) % players.size();
            }
            adapter.updateActualPlayer(currentPlayerId);
        }
    }


    @Override
    public void resign(int playerId) {
        int temporaryCurrentPlayerId=currentPlayerId;
        currentPlayerId=playerId;
        fold(playerId);
        currentPlayerId=temporaryCurrentPlayerId;
        adapter.updateActualPlayer(currentPlayerId);
        removePlayer(playerId);
        adapter.updateResignPlayer(playerId);
    }

    private void won(){
        int numberOfWiners=0;
        for(Player p:players) if(p.getInGame()) numberOfWiners++;
        int i=-1;
        for(Player p:players) {
            i++;
            if(!p.getInGame()) continue;
            players.get(i).setMoney(players.get(i).getMoney()+(onTable/numberOfWiners));
            adapter.sendMessage("Koniec rundy, wygrał gracz " + players.get(i).getName() + "\n ");
        }
        adapter.sendMessage("Rozpoczynanie nowej rundy \n");
        i = -1;
        for(Player p: players) {
            i++;
            if(p.getResigned()) continue;
            adapter.updatePlayerCash(i, players.get(i).getMoney());
            adapter.updatePlayerLinedCash(i, 0);
        }
        for(Player p:players) {


            if (!p.getResigned()) adapter.sendMessage("Gracz "+p.getName() + " mial "+ Arrays.deepToString(p.getCards()));
        }
        //TU TRZEBA WSTAWIC WAIT NA JAKIES 10 SEKUND

        smallBlindPosition = (smallBlindPosition + 1) % players.size();
        while (players.get(smallBlindPosition).getResigned() == true) {
            smallBlindPosition = (smallBlindPosition + 1) % players.size();
        }
        started=false;
        //startRound();
    }

    private void startRound(){
        pot = 0;
        adapter.clearTable();
        stage=0;
        System.err.println("Start bew round stage:" + stage);
        for(int i=0;i<5;i++) cards[i]=null;
        deck=new Deck();

        int i=0;
        for(Player p:players){
            p.setCards(deck);
            p.setMoney(p.getMoney()- ante);
            adapter.updatePlayerCash(i, p.getMoney());
            p.setOffer(ante);
            adapter.updatePlayerLinedCash(i, p.getOffer());
            adapter.updatePlayerHand(i,p.getCards());
            p.setInGame(true);
            i++;
        }
        while(players.get(smallBlindPosition).getInGame() == false)
            smallBlindPosition = (smallBlindPosition+1)%players.size();
        adapter.sendMessage("SmallBlindPosition: "+smallBlindPosition);
        adapter.startNewRound();
        currentPlayerId=smallBlindPosition;
        adapter.updateActualPlayer(currentPlayerId);
        this.limit= ante;
        this.numberInGame=numberOfPlayers-resigned;
        this.onTable= ante *numberInGame;


        adapter.sendMessage("Gracz " + players.get(currentPlayerId).getName() + " wpłacił małą ciemną");
        raise(currentPlayerId, Math.min(players.get(currentPlayerId).getMoney(), smallBlind));
        adapter.sendMessage("Gracz " + players.get(currentPlayerId).getName() + " wpłacił dużą ciemną");
        raise(currentPlayerId, Math.min(players.get(currentPlayerId).getMoney(), bigBlind));
        raisingPlayerId = currentPlayerId;
        adapter.setPot(pot);
    }


    private PokerHand getPlayerHand(Player player) {
        List<Deck.Card> cards = new ArrayList<Deck.Card>();
        cards.addAll(Arrays.asList(player.getCards()));
        cards.addAll(Arrays.asList(this.cards));
        return PokerHand.evaluate(cards);
    }

    private void checkItAll(){
        int x = -1;
        for(Player player: players) {
            x++;
            if(player.getResigned()) continue;
            pot += player.getOffer();
            player.setOffer(0);
            adapter.updatePlayerLinedCash(x, 0);
        }
        System.err.println("Actual round: " + stage);
        adapter.setPot(pot);
        this.limit = 0;
        currentPlayerId = smallBlindPosition;
    
        while(players.get(currentPlayerId).getInGame() == false)
            currentPlayerId = (currentPlayerId + 1)%players.size();
        adapter.updateActualPlayer(currentPlayerId);
        
        raisingPlayerId = currentPlayerId;

        if (getActualStage() ==0){
            cards[0]=deck.getNextCard();
            cards[1]=deck.getNextCard();
            cards[2]=deck.getNextCard();
            adapter.addThreeCards(cards);
            stage = getActualStage() + 1;
        }
        else if (getActualStage() ==1){
            cards[3]=deck.getNextCard();
            adapter.addOneCard(cards[3]);
            stage = getActualStage() + 1;
        }
        else if (getActualStage() ==2){
            cards[4]=deck.getNextCard();
            adapter.addOneCard(cards[4]);
            stage = getActualStage() + 1;
        }
        else if (getActualStage() ==3) {
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
    }

    @Override
    public int getActualStage() {
        return stage;
    }
}

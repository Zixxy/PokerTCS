package Model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Adapter.AdapterInterface;
import Model.Deck.Card;

/**
 * Created by bartek on 05.05.14.
 */

class ToTableSender implements Runnable {
    ModelOne mo;

    public ToTableSender(ModelOne mo) {
        this.mo = mo;
    }

    @Override
    public void run() {

        while (true) {
            for (Player p : mo.players) {
                mo.adapter.addPlayer(p.getName(), p.getId(), p.getImage());
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.err.println("InteruppedException ale cocojambo i do przodu");
            }
        }
    }
}
public class ModelOne implements ModelInterface {
    private Thread updateSender;
    private int pot;
    private int actualId;
    private int smallBlindPosition;
    private int bigBlindPosition;
    public AdapterInterface adapter;
    private boolean started;//is game started?
    private int numberOfPlayers;
    public ArrayList<Player> players;
    private int limit;
    private int onTable;
    private int currentPlayerId;
    private int numberInGame;
    private int raisingPlayerId;
    private int ante;
    private Deck deck;
    private Deck.Card cards[];
    private int stage;
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
        this.cards = new Deck.Card[5];
        this.ante=0;
        this.startedAmount=1000;
        this.smallBlindPosition = 0;
        this.smallBlind = 10;
        this.bigBlind = 20;
        this.updateSender = new Thread(new ToTableSender(this));
        this.updateSender.start();
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
    public int addPlayer(String name, int image) {
        if(!this.started) {
        	if(numberOfPlayers >= 8){
        		System.err.println("No available place");
        		return -1;
        	}
        	numberOfPlayers++;
        	for(Player p : players){
        		if(p.getResigned()){
        			p.setResigned(false);
        			adapter.addPlayer(name, p.getId(),image);
        			return p.getId();
        		}
        	}
            players.add(new Player(name, startedAmount, numberOfPlayers -1,image));
            adapter.addPlayer(name, numberOfPlayers - 1,image);
            return numberOfPlayers-1;
        }
        return -1;
    }

    @Override
    public void setPlayerName(String name) {
    }
	@Override
	public void setPlayerImage(int image) {
		// TODO Auto-generated method stub
		
	}
    @Override
    public void removePlayer(int playerId) {
        Player player = players.get(playerId);
        if(player == null || player.getResigned())
            return;
        if(started) {
            if(player.getInGame() == true) {
                int tmpCurrentPlayerId = currentPlayerId;
                currentPlayerId = playerId;
                fold(playerId);
                currentPlayerId = tmpCurrentPlayerId;
                adapter.updateActualPlayer(currentPlayerId);

            }
        }
        adapter.removePlayer(playerId);
        player.setResigned(true);
        numberOfPlayers--;
    }//


    @Override
    public void resign(int playerId) {
        removePlayer(playerId);
        /*

        if(players.get(playerId).getInGame()==true) numberInGame--;
        players.get(playerId).setResigned(true);

        numberOfPlayers--;
        adapter.removePlayer(playerId);


        if(!started){
            adapter.updateResignPlayer(playerId);
            removePlayer(playerId);
        }
        int temporaryCurrentPlayerId=currentPlayerId;
        currentPlayerId=playerId;
        fold(playerId);
        currentPlayerId=temporaryCurrentPlayerId;
        adapter.updateActualPlayer(currentPlayerId);
        removePlayer(playerId);
        adapter.updateResignPlayer(playerId);*/
    }

    private int getNextPlayerPosition(int position)
    {
        position = (position+1) % players.size();
        while(players.get(position).getInGame() == false)
            position = (position+1) % players.size();
        return position;
    }

    @Override
    public void start() {
        for(Player p:players)
            if(!p.getResigned() && !p.getReady())
                return;
        int i=0;
        for(Player p : players){
            if(!p.getResigned()) adapter.addPlayer(p.getName(),i,p.getImage());
            i++;
        }
        if (!started) {

        startRound();
       }
    }

    @Override
    public void start(int id) {
        players.get(id).setReady(true);
        start();
    }

    @Override
    public void fold(int playerId) {
        if (!started) return;
        if(currentPlayerId==playerId) {
            adapter.setLastMove(playerId, 1);
            boolean raising = false;
            if (currentPlayerId == raisingPlayerId)
                raising = true;
            players.get(playerId).setInGame(false);
            numberInGame--;
            adapter.sendMessage("Gracz " + players.get(playerId).getName() + " pasuje");
            currentPlayerId = getNextPlayerPosition(currentPlayerId - 1); //getNextPlayerPosition is moving current player, but we don't want exactly that
            adapter.updateActualPlayer(currentPlayerId);
            if (numberInGame == 1) won();
            else if (currentPlayerId == raisingPlayerId) checkItAll();
            else {
                if (raising)
                    raisingPlayerId = currentPlayerId;
            }
        }
    }

    @Override
    public void check(int playerId) {
        if (!started) return;
        if(currentPlayerId==playerId) {
            adapter.setLastMove(playerId, 2);
            if(players.get(playerId).getMoney() <= this.limit - players.get(playerId).getOffer()) {
                allIn(playerId);
                return;
            }
            adapter.sendMessage("Gracz " + players.get(playerId).getName() +" sprawdza");
            onTable+=this.limit - players.get(playerId).getOffer();
            players.get(playerId).setMoney(players.get(playerId).getMoney() - (this.limit - players.get(playerId).getOffer()));
            players.get(playerId).setOffer(this.limit);
            adapter.updatePlayerLinedCash(playerId, players.get(playerId).getOffer());
            adapter.updatePlayerCash(playerId, players.get(playerId).getMoney());

            currentPlayerId = getNextPlayerPosition(currentPlayerId);

            adapter.updateActualPlayer(currentPlayerId);
            if(currentPlayerId==raisingPlayerId) checkItAll();
        }
    }

    public void allIn(int playerId) {
        if(!started) return;

        System.out.println("Jesteśmy w allinie");
        if(currentPlayerId  != playerId) return;
    	adapter.setLastMove(playerId, 3);
        Player actualPlayer = players.get(playerId);
        actualPlayer.setOffer(actualPlayer.getOffer() + actualPlayer.getMoney());
        actualPlayer.setMoney(0);
        actualPlayer.setAllIned(true);
        if(actualPlayer.getOffer() > limit) {
            this.raisingPlayerId = playerId;
            this.limit = actualPlayer.getOffer();
        }
        adapter.updatePlayerLinedCash(playerId, actualPlayer.getOffer());
        adapter.updatePlayerCash(playerId, 0);
        actualPlayer.setInGame(false);
        numberInGame--;
        boolean raising = false;
        if(currentPlayerId == raisingPlayerId)
            raising = true;
        if (numberInGame == 0) {
            won();
            return;
        }
        currentPlayerId = getNextPlayerPosition(currentPlayerId);
        if (currentPlayerId == raisingPlayerId) checkItAll();
        else if(raising)
            raisingPlayerId = currentPlayerId;

    }

    @Override
    public void raise(int playerId, int amount) {
        if (!started) return;
        if(currentPlayerId==playerId) {
            if(amount >= players.get(playerId).getMoney()){
                allIn(playerId);
                return;
            }
            if(amount + players.get(playerId).getOffer() < limit)
                return;
            if(amount + players.get(playerId).getOffer() == limit) {
                check(playerId);
                return;
            }
            adapter.setLastMove(playerId, 0);
            adapter.sendMessage("Gracz " + players.get(playerId).getName() +" podbija o " + amount );
            onTable+=amount;
            players.get(playerId).setMoney(players.get(playerId).getMoney() -amount);
            players.get(playerId).setOffer(players.get(playerId).getOffer()+amount);
            adapter.updatePlayerLinedCash(playerId, players.get(playerId).getOffer());
            adapter.updatePlayerCash(playerId, players.get(playerId).getMoney());
            this.limit = players.get(playerId).getOffer();
            this.raisingPlayerId = playerId;

            currentPlayerId = getNextPlayerPosition(currentPlayerId);

            adapter.updateActualPlayer(currentPlayerId);
        }
    }

    private int reducePlayersRoundOffer(int x) {
        int out = 0;
        for(Player player: players) {
            out+=Math.min(x, player.getRoundCash());
            player.setRoundCash(Math.max(0, player.getRoundCash()-x));
        }
        return out;
    }

    private ArrayList<Player> bestPlayers() {
        ArrayList<Player> out = new ArrayList<Player>();
        for(Player player: players) {
            if(!player.getInGame() && !player.getAllIned())
                continue;
            if(out.isEmpty() || getPlayerHand(player).compareTo(getPlayerHand(out.get(0))) > 0) {
                out = new ArrayList<Player>();
                out.add(player);
            }
        }
        return out;
    }

    private Player removeSmallestPlayer(ArrayList<Player> list){
        int smallest = 0;
        for(int i = 1; i<list.size(); i++)
            if(list.get(smallest).getRoundCash() > list.get(i).getRoundCash()) {
                smallest = i;
            }
        return list.remove(smallest);
    }

    private void won(){
        //begin of copypaste
        if (getActualStage() ==0){
            cards[0]=deck.getNextCard();
            cards[1]=deck.getNextCard();
            cards[2]=deck.getNextCard();
            adapter.addThreeCards(cards);
            stage = getActualStage() + 1;
        }
        if (getActualStage() ==1){
            cards[3]=deck.getNextCard();
            adapter.addOneCard(cards[3]);
            stage = getActualStage() + 1;
        }
        if (getActualStage() ==2){
            cards[4]=deck.getNextCard();
            adapter.addOneCard(cards[4]);
            stage = getActualStage() + 1;
        }
        int x = -1;
        for(Player player: players) {
            x++;
            pot += player.getOffer();
            player.setOffer(0);
            if(player.getResigned()) continue;
            adapter.updatePlayerLinedCash(x, 0);
            player.setReady(false);
            player.setBeforeEndRoundCash(player.getMoney());
        }
        adapter.setPot(pot);
        //END OF COPYPASTE I FEEL GUILTY :((((((((((((
        ArrayList<Player> best;
        while(!(best = bestPlayers()).isEmpty()) {
            int cash=0;
            while(!best.isEmpty()) {
                Player smaller = removeSmallestPlayer(best);
                cash+=reducePlayersRoundOffer(smaller.getRoundCash())/(best.size() + 1);
                smaller.setMoney(smaller.getMoney() + cash);
                smaller.setInGame(false);
                smaller.setAllIned(false);
                System.out.println("Jestem w pENtli");
            }
        }
        adapter.setPot(0);
        /*int numberOfWiners=0;
        for(Player p:players) if(p.getInGame()) numberOfWiners++;
        int i=-1;
        for(Player p:players) {
            i++;
            if(!p.getInGame()) continue;
            players.get(i).setMoney(players.get(i).getMoney()+(onTable/numberOfWiners));
            adapter.sendMessage("Koniec rundy, wygrał gracz " + players.get(i).getName());
        }*/
        adapter.sendMessage("Rozpoczynanie nowej rundy ");
        int pos = -1;
        for(Player p: players) {
            pos++;
            if(p.getResigned()) continue;
            adapter.updatePlayerCash(pos, players.get(pos).getMoney());
            adapter.updatePlayerLinedCash(pos, players.get(pos).getMoney() - players.get(pos).getBeforeEndRoundCash());
        }

        for(Player p:players) {


            adapter.sendMessage(p.getName() + " miał: " + getPlayerHand(p).toString());
            adapter.sendMessage(p.getName() + " wygrał: " + (p.getMoney() - p.getBeforeEndRoundCash()));
            adapter.showCards(p.getId(), p.getCards()[0].getMacieksId(), p.getCards()[1].getMacieksId());
        }
        //TU TRZEBA WSTAWIC WAIT NA JAKIES 10 SEKUND





        started=false;
        //startRound();
    }

    private void startRound(){
        if(numberOfPlayers<2){
            for(Player p:players){
                p.setReady(false);
            }
            adapter.sendMessage("Nie mozna rozpoczac gry poniewaz, nie ma wystarczajacej liczby graczy");
            return;
        }
        started=true;
        System.out.println("Paczajka 1");
        pot = 0;
        adapter.clearTable();
        stage=0;
        System.err.println("Start bew round stage:" + stage);
        for(int i=0;i<5;i++) cards[i]=null;
        deck=new Deck();

        int i=0;
        for(Player p:players){
            adapter.updatePlayerLinedCash(p.getId(), 0);
            if(p.getMoney() == 0) {
                p.setResigned(true);
                continue;
            }
            p.setCards(deck);
            p.setMoney(p.getMoney()- ante);
            adapter.updatePlayerCash(i, players.get(i).getMoney());
            p.setOffer(ante);
            adapter.updatePlayerLinedCash(i, p.getOffer());
            adapter.updatePlayerHand(i, p.getCards());
            p.setInGame(true);
            p.setAllIned(false);
            p.setRoundCash(0);
            i++;
        }
        System.out.println("Paczajka 2");

        smallBlindPosition = getNextPlayerPosition(smallBlindPosition);
        adapter.sendMessage("SmallBlindPosition: "+smallBlindPosition);
        adapter.startNewRound();
        currentPlayerId=smallBlindPosition;
        adapter.updateActualPlayer(currentPlayerId);
        this.limit= ante;
        this.numberInGame=numberOfPlayers;
        this.onTable= ante *numberInGame;

        System.out.println("Paczajka 3");
        adapter.sendMessage("Gracz " + players.get(currentPlayerId).getName() + " wpłacił małą ciemną");
        raise(currentPlayerId, smallBlind);
        adapter.sendMessage("Gracz " + players.get(currentPlayerId).getName() + " wpłacił dużą ciemną");
        raise(currentPlayerId, bigBlind);
        raisingPlayerId = currentPlayerId;
        adapter.setPot(pot);
        System.out.println("Paczajka 4");
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
    
        currentPlayerId = getNextPlayerPosition(currentPlayerId - 1);
        adapter.updateActualPlayer(currentPlayerId);
        
        raisingPlayerId = currentPlayerId;
        if(numberInGame == 1) {
            won();
            return;
        }
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
           /* List<Player> inGamePlayers = new ArrayList<Player>();
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
             }*/
            won();
        }
    }

    @Override
    public int getActualStage() {
        return stage;
    }
	@Override
	public void showPlayerCards(int playerId, Card[] cards) {
        //JUST FOR NETWORK CONNECTION
	}

    @Override
    public void addTable() {
        //JUST FOR NETWORK CONNECTION
    }

    @Override
    public void removePlayerFromTable() {
        //JUST FOR NETWORK CONNECTION
    }

    @Override
    public void addPlayerToTable(int tableIndex) {
        //JUST FOR NETWORK CONNECTION
    }
	@Override
	public void sendOutMessage(String text) {
		// UNUSED
		
	}

}

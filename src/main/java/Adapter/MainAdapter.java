package Adapter;

import Model.Deck;
import Model.ModelInterface;
import View.TableControler;
import View.ViewInterface;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by dakurels on 05.05.14.
 */
public class MainAdapter implements AdapterInterface {
    private ModelInterface model;
    private Collection<ViewInterface> views;

    public MainAdapter() {
        model = null;
        views = new ArrayList<ViewInterface>();
    }

    @Override
    public void fold(int playerId) {
        System.out.println("Jestesmy w adapterze fold!!"+playerId+"~");
        model.fold(playerId);
    }

    @Override
    public void check(int playerId) {
        System.out.println("Jestesmy w adapterze check!!"+playerId);
        model.check(playerId);
    }

    @Override
    public void raise(int playerId, String amount){
        System.out.println("Jestesmy w adapterze raise!!"+playerId+"~"+amount);
        int cash;
        try {
            cash = Integer.valueOf(amount);
        }
        catch (NumberFormatException e) 
        {
            //Da fuq Maciek, da fuq
        	//throw new RuntimeException(e);
            return;
        }
        if(cash < 0)
            return;
        model.raise(playerId, cash);
    }

    @Override
    public void start(){
    	model.start();
    }
    @Override
    public void resign(int playerId){
        model.resign(playerId);
    }

    @Override
    public void sendMessage(String text){
        for(ViewInterface view: views) {
            view.sendMessage(text);
        }
    }
    @Override
    public void sendMyMessageToEveryBody(String text){
    	model.sendOutMessage(text);
    }
    @Override
    public void addPlayer(String name, int id, int image){
        for(ViewInterface view: views) {
            view.addPlayer(name, id, image);
        }

    }

    @Override
    public void removePlayer(int id){
        for(ViewInterface view: views) {
            view.removePlayer(id);
        }
    }

    @Override
    public void updatePlayerCash(int id, int cash){
        for(ViewInterface view: views) {
            view.updatePlayerCash(id, cash);
        }
    }

    @Override
    public void updatePlayerLinedCash(int id, int cash){
        for(ViewInterface view: views) {
            view.updatePlayerLinedCash(id, cash);
        }
    }
    @Override
    public void clearTable() {
        for(ViewInterface view: views) {
            view.clearTable();
        }
    }

    @Override
    public void addModel(ModelInterface mod){
        model = mod;

    }

    @Override
    public void addView(ViewInterface view){
        if(!views.contains(view) && view!=null) views.add(view);
        System.err.println("Dodaje view " + view + " i mam teraz ich " + views.size());
    }

    @Override
    public void addThreeCards(Deck.Card[] cards) {
        for(ViewInterface view: views) {
            view.addThreeCardsOnTable(cards[0], cards[1], cards[2]);
            //System.out.println("Dodaje view "+view+" i mam ich "+views.size());
        }
    }

    @Override
    public void addOneCard(Deck.Card card) {
        for(ViewInterface view: views) {
            view.addOneCard(card);
        }
    }

    @Override
    public void startNewRound() {
        for(ViewInterface view: views) {
            view.startNewRound();
        }
    }

  /*  @Override
    public Deck.Card[] getHandCards(int playerId) {
        return model.getHandCards(playerId);
    }*/
    @Override
    public void updatePlayerHand(int playerId,Deck.Card[] cards){
        for(ViewInterface view: views) {
            view.updatePlayerHand(playerId, cards[0], cards[1]);
        }
    }

    @Override
    public void setPot(int pot) {
        for(ViewInterface view: views) {
            view.setPot(pot);
        }
    }

    @Override
    public int getActualStage() {
        return model.getActualStage();
    }

	@Override
	public void updateActualPlayer(int id) {
		for(ViewInterface view: views){
			view.updateActualPlayer(id);
		}
		//
	}

	@Override
	public void updateResignPlayer(int id) {
		for(ViewInterface view: views){
			view.updateResignedPlayer(id);
		}
	}
	
	@Override
	public void setLastMove(int id, int move){
		for(ViewInterface view: views){
			view.setLastMove(id, move);
		}
	}

    @Override
    public void setStartedAmount(int amount) {
        model.setStartedAmount(amount);
    }
    
    public void showPlayerCards(int playerId, Deck.Card[] cards){
    	System.out.println("jestem w adapter");
    	model.showPlayerCards(playerId, cards);
    }
    
    @Override
    public void showCards(int playerId, int firstCardNumber, int secondCardNumber){
    	System.out.println("ok, powrot do adaptera");
    	for(ViewInterface view: views){
			view.showCards(playerId, firstCardNumber, secondCardNumber);
		}
    }

    @Override
    public void guiAddTable(int numberOfTable,String started) {
        for(ViewInterface view: views) {
            view.guiAddTable(numberOfTable,started);
            System.out.println("Dodaje stol do "+view);
        }
    }
    @Override
    public void guiClearTableList(){
    	for(ViewInterface view: views) {
            view.guiClearTableList();
            System.out.println("Czyszcze liste stolow w: "+view);
        }
    }

    @Override
    public void guiRemoveTable(int numberOfTable) {
        for(ViewInterface view: views) {
            view.guiRemoveTable(numberOfTable);
        }
    }

    @Override
    public void updateNumberOfPlayers(int numberOfTable, int currentNumberOfPlayers) {
        for(ViewInterface view: views) {
            view.updateNumberOfPlayers(numberOfTable, currentNumberOfPlayers);
        }

    }

    @Override
    public void addTable() {
        model.addTable();
    }

    @Override
    public void addPlayerToTable(int tableIndex) {
        model.addPlayerToTable(tableIndex);
    }

    @Override
    public void removePlayerFromTable() {
        model.removePlayerFromTable();
    }

    @Override
    public void setPlayerId(int id) {
        System.err.println("USTAWIAM MOJE ID "+id);
        for(ViewInterface view: views) {
            view.setPlayerId(id);
        }
        TableControler.playerId=id;
    }

    @Override
    public void setPlayerName(String name) {
        model.setPlayerName(name);
    }

    @Override
    public void start(int id) {
        model.start(id);
    }

    public void exchangeReference(ViewInterface from, ViewInterface to){
        System.err.println("ROBIE EXCHANGE" + from + to);
		System.out.println(views);
    	//views.remove(from);
    	if(!views.contains(to) && to!=null) addView(to);
    }
    public void removeAllViews(){
        //System.err.println("USUWAM WSZYSTKO");
        //views = new ArrayList<ViewInterface>();
    }

    @Override
    public void guiExitTable() {
        for(ViewInterface view: views) {
            System.out.println("Zamykam "+view);
            view.guiExitTable();
        }
    }
}

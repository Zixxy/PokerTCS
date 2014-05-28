package main.java.Adapter;

import main.java.Model.Deck;
import main.java.Model.ModelInterface;
import main.java.View.TableViewInterface;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by dakurels on 05.05.14.
 */
public class MainAdapter implements AdapterInterface {
    private ModelInterface model;
    private Collection<TableViewInterface> views;

    public MainAdapter() {
        model = null;
        views = new ArrayList<TableViewInterface>();
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
        catch (NumberFormatException e) { return; }
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
        for(TableViewInterface view: views) {
            view.sendMessage(text);
        }
    }

    @Override
    public void addPlayer(String name, int id){
        for(TableViewInterface view: views) {
            view.addPlayer(name, id);
        }

    }

    @Override
    public void removePlayer(int id){
        for(TableViewInterface view: views) {
            view.removePlayer(id);
        }
    }

    @Override
    public void updatePlayerCash(int id, int cash){
        for(TableViewInterface view: views) {
            view.updatePlayerCash(id, cash);
        }
    }

    @Override
    public void updatePlayerLinedCash(int id, int cash){
        for(TableViewInterface view: views) {
            view.updatePlayerLinedCash(id, cash);
        }
    }
    @Override
    public void clearTable() {
        for(TableViewInterface view: views) {
            view.clearTable();
        }
    }

    @Override
    public void addModel(ModelInterface mod){
        model = mod;

    }

    @Override
    public void addView(TableViewInterface view){
        views.add(view);
        //System.out.println("Dodaje view "+view+" i mam teraz ich "+views.size());
    }

    @Override
    public void addThreeCards(Deck.Card[] cards) {
        for(TableViewInterface view: views) {
            view.addThreeCardsOnTable(cards[0], cards[1], cards[2]);
            //System.out.println("Dodaje view "+view+" i mam ich "+views.size());
        }
    }

    @Override
    public void addOneCard(Deck.Card card) {
        for(TableViewInterface view: views) {
            view.addOneCard(card);
        }
    }

    @Override
    public void startNewRound() {
        for(TableViewInterface view: views) {
            view.startNewRound();
        }
    }

  /*  @Override
    public Deck.Card[] getHandCards(int playerId) {
        return model.getHandCards(playerId);
    }*/
    @Override
    public void updatePlayerHand(int playerId,Deck.Card[] cards){
        for(TableViewInterface view: views) {
            view.updatePlayerHand(playerId, cards[0], cards[1]);
        }
    }

    @Override
    public void setPot(int pot) {
        for(TableViewInterface view: views) {
            view.setPot(pot);
        }
    }

    @Override
    public int getActualStage() {
        return model.getActualStage();
    }

	@Override
	public void updateActualPlayer(int id) {
		for(TableViewInterface view: views){
			view.updateActualPlayer(id);
		}
		//
	}

	@Override
	public void updateResignPlayer(int id) {
		for(TableViewInterface view: views){
			view.updateResignedPlayer(id);
		}
	}
	
	@Override
	public void setLastMove(int id, int move){
		for(TableViewInterface view: views){
			view.setLastMove(id, move);
		}
	}

    @Override
    public void setStartedAmount(int amount) {
        model.setStartedAmount(amount);
    }
    
    public void showPlayerCards(int playerId, Deck.Card[] cards){
    	model.showPlayerCards(playerId, cards);
    }
    
    @Override
    public void showCards(int playerId, int firstCardNumber, int secondCardNumber){
    	for(TableViewInterface view: views){
			view.showCards(playerId, firstCardNumber, secondCardNumber);
		}
    }

    @Override
    public void guiAddTable(int numberOfTable) {
        for(TableViewInterface view: views) {
            view.guiAddTable(numberOfTable);
            System.out.println("Dodaje stol do"+view);
        }
    }

    @Override
    public void guiRemoveTable(int numberOfTable) {
        for(TableViewInterface view: views) {
            view.guiRemoveTable(numberOfTable);
        }
    }

    @Override
    public void updateNumberOfPlayers(int numberOfTable, int currentNumberOfPlayers) {
        for(TableViewInterface view: views) {
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
    public void removePlayerFromTable(int tableIndex) {
        model.removePlayerFromTable(tableIndex);
    }

    @Override
    public void setPlayerId(int id) {
        for(TableViewInterface view: views) {
            view.setPlayerId(id);
        }
    }

    @Override
    public void setPlayerName(String name) {
        model.setPlayerName(name);
    }

    public void exchangeReference(TableViewInterface from, TableViewInterface to){

		System.out.println(views);
    	views.remove(from);
        //Sylwek umrzyj za pisanie remove w ten sposob jak bylo
    	if(!views.contains(to)) addView(to);
        //Dodaje to sprawdzenie bo gdzies jest wywolywane tak, ze dodaje po raz drugi
    }
}

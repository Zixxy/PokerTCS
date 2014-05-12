package main.java.Adapter;

import main.java.Model.Deck;
import main.java.Model.ModelInterface;
import main.java.View.ViewInterface;

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
        model.fold(playerId);
    }

    @Override
    public void check(int playerId) {
        model.check(playerId);
    }

    @Override
    public void raise(int playerId, String amount){
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
    public void addPlayer(String name, int id){
        for(ViewInterface view: views) {
            view.addPlayer(name, id);
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
        views.add(view);
    }

    @Override
    public void addThreeCards(Deck.Card[] cards) {
        for(ViewInterface view: views) {
            view.addThreeCardsOnTable(cards[0], cards[1], cards[2]);
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

    @Override
    public Deck.Card[] getHandCards(int playerId) {
        return model.getHandCards(playerId);
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
}

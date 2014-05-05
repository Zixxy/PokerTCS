package Adapter;

import Model.ModelInterface;
import View.ViewInterface;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by dakurels on 05.05.14.
 */
public class MainAdapter implements AdapterInterface {
    private Collection<ModelInterface> models;
    private Collection<ViewInterface> views;

    MainAdapter() {
        models = new ArrayList<ModelInterface>();
        views = new ArrayList<ViewInterface>();
    }

    @Override
    public void fold(int playerId) throws Exception {
        for(ModelInterface model: models) {
            model.fold(playerId);
        }
    }

    @Override
    public void check(int playerId) throws Exception {
        for(ModelInterface model: models) {
            model.check(playerId);
        }
    }

    @Override
    public void raise(int playerId, int amount) throws Exception {
        for(ModelInterface model: models) {
            model.raise(playerId, amount);
        }
    }

    @Override
    public void resign(int playerId) throws Exception {
        for(ModelInterface model: models) {
            model.resign(playerId);
        }
    }

    @Override
    public void sendMessage(String text) throws Exception {
        for(ViewInterface view: views) {
            view.sendMessage(text);
        }
    }

    @Override
    public void addPlayer(String name, int id) throws Exception {
        for(ViewInterface view: views) {
            view.addPlayer(name, id);
        }

    }

    @Override
    public void removePlayer(int id) throws Exception {
        for(ViewInterface view: views) {
            view.removePlayer(id);
        }
    }

    @Override
    public void updatePlayerCash(int id, int cash) throws Exception {
        for(ViewInterface view: views) {
            view.updatePlayerCash(id, cash);
        }
    }

    @Override
    public void updatePlayerLinedCash(int id, int cash) throws Exception {
        for(ViewInterface view: views) {
            view.updatePlayerLinedCash(id, cash);
        }
    }

    @Override
    public void addModel(ModelInterface mod) throws Exception {
        models.add(mod);

    }

    @Override
    public void addView(ViewInterface view) throws Exception {
        views.add(view);
    }
}

package main.java.Adapter;

import main.java.Model.ModelInterface;
import main.java.View.ViewInterface;

/**
 * Created by dakurels on 05.05.14.
 */

public interface AdapterInterface {
    public void fold(int playerId);
    public void check(int playerId);
    public void raise(int playerId, int amount);
    public void resign(int playerId);
    public void sendMessage(String text);
    public void addPlayer(String name, int id);
    public void removePlayer(int id);
    public void updatePlayerCash(int id, int cash);
    public void updatePlayerLinedCash(int id, int cash);
    public void addModel(ModelInterface mod);
    public void addView(ViewInterface view);
}

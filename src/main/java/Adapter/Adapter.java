package Adapter;

/**
 * Created by dakurels on 05.05.14.
 */

public interface Adapter {
    public void fold(int playerId) throws Exception;
    public void check(int playerId) throws Exception;
    public void raise(int playerId, int amount) throws Exception;
    public void resign(int playerId) throws Exception;
    public void addPlayer(String name, int id) throws Exception;
    public void removePlayer(int id) throws Exception;
    public void updatePlayerCash(int id, int cash) throws Exception;
    public void updatePlayerLinedCash(int id, int cash) throws Exception;
}

package main.java.Model;

/**
 * Created by bartek on 05.05.14.
 */
public class Player{
    public Player(String arg1, Integer arg2){
        this.name=arg1;
        this.money=arg2;
    }

    private String name;
    private int money;
    private boolean inGame;
    private int offer;
    private boolean checking;

    public void setName(String arg1){
        this.name=arg1;
    }

    public void setMoney(Integer arg1){
        this.money=arg1;
    }

    public String getName(){
        return this.name;
    }

    public int getMoney(){
        return this.money;
    }

    public boolean getInGame(){
        return this.inGame;
    }
    public void setInGame(boolean arg1){
        this.inGame=arg1;
    }

    public boolean getChecking(){
        return this.checking;
    }
    public void setChecking(boolean arg1){
        this.checking=arg1;
    }

    public void setOffer(Integer arg1){
        this.offer=arg1;
    }

    public int getOffer(){
        return this.offer;
    }
}

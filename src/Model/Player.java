package Model;

/**
 * Created by bartek on 05.05.14.
 */
public class Player{
    public Player(String arg1, Integer arg2){
        this.name=arg1;
        this.money=arg2;
    }
    private String name;
    private Integer money;

    public void setName(String arg1){
        this.name=arg1;
    }

    public void setMoney(Integer arg1){
        this.money=arg1;
    }

    public String getName(){
        return this.name;
    }

    public Integer getMoney(){
        return this.money;
    }
}

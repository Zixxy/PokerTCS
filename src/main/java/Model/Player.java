package Model;

/**
 * Created by bartek on 05.05.14.
 */
public class Player{
    public Player(String arg1, Integer arg2, int id, int image){
    	this.image = image;
    	this.id = id;
        this.name=arg1;
        this.money=arg2;
        cards = new Deck.Card[2];
        resigned=false;
        ready = false;
    }
    private int image;
    private final int id;
    private String name;
    private int money;
    private boolean inGame;
    private int offer;
    private boolean resigned;
    private Deck.Card cards[];
    private boolean allIned;
    private int thisRoundOffer;
    private boolean ready;

    public int getId(){
    	return this.id;
    }
    public Deck.Card[] getCards(){
        return cards;
    }
    public void setCards(Deck arg1){
        cards[0]=arg1.getNextCard();
        cards[1]=arg1.getNextCard();
    }
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
        if(resigned) inGame=false;
        else this.inGame=arg1;
    }

    public boolean getResigned(){
        return this.resigned;
    }
    public void setResigned(boolean arg1){
        this.resigned =arg1;
    }

    public void setOffer(Integer arg1){
        if(arg1 > this.offer)
            thisRoundOffer += arg1 - this.offer;
        this.offer=arg1;
    }

    public int getOffer(){
        return this.offer;
    }

    public boolean getAllIned() {
        return allIned;
    }

    public void setAllIned(boolean arg) {
        allIned = arg;
    }

    public void setRoundCash(int x){
        this.thisRoundOffer = x;
    }

    public int getRoundCash(){
        return thisRoundOffer;
    }

    public boolean getReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
}

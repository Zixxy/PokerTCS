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
        this.beforeEndRoundCash = 0;
        this.cards = new Deck.Card[2];
        this.resigned=false;
        this.ready = false;
        this.resultMassage = "";
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
    private int beforeEndRoundCash;
    private String resultMassage;

    public void recreatePlayer(String arg1, Integer arg2, int image) {
        this.image = image;
        this.name=arg1;
        this.money=arg2;
        this.beforeEndRoundCash = 0;
        this.cards = new Deck.Card[2];
        this.resigned=false;
        this.ready = false;
        this.resultMassage = "";
    }

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

    public int getBeforeEndRoundCash() {
        return beforeEndRoundCash;
    }

    public void setBeforeEndRoundCash(int beforeEndRoundCash) {
        this.beforeEndRoundCash = beforeEndRoundCash;
    }

    public void setResultMassage(PokerHand hand) {
        String massage = "Gracz: " + getName() + " ";
        int cashChange = getMoney() - getBeforeEndRoundCash();
        if(getResigned())
            massage += "zrezygnował.";
        else if(!getInGame() && !getAllIned()) {
            massage += "spasował.";
        }
        else {
            if(getAllIned())
                massage += " wszedł all-in i ";
            if(cashChange > 0) {
                massage += "wygrał " + cashChange + "$";
            }
            else if(cashChange == 0) {
                massage += "nic nie wygrał";
            }
            else {
                massage += "przegrał " + (-cashChange) + "$";
            }
            massage += "[" + hand.toString() + "].";
        }
        resultMassage = massage;
    }

    public String getResultMassage() {
        return resultMassage;
    }
}

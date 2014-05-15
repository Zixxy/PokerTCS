package main.java.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import main.java.Adapter.MainAdapter;
import main.java.Model.Deck.Card;

public class TableControler{
	private MainAdapter adapter;
	private int playerId;
	public TableControler(){
		adapter = TableView.tempAdapter;
		TableView.latestCreatedTableControler = this;
	}
	
	@FXML
	private HBox PotBox;
	
    @FXML
    private TextField userCashTextField;
    
    @FXML
    private Button btnCheck, btnRaise, btnFold;
    
	@FXML
    private ImageView firstPlayerCard, secondPlayerCard;
    private ImageView[] playersCard;

    @FXML
    private ImageView firstCardOnTable, secondCardOnTable, thirdCardOnTable, fourthCardOnTable, fifthCardOnTable;
    private ImageView[] cardsOnTable;
    
    @FXML
    private HBox playerOneCashBox, playerTwoCashBox, playerThreeCashBox, playerFourCashBox, playerFiveCashBox,
                 playerSixCashBox, playerSevenCashBox, playerEightCashBox;
    private HBox[] playersCashBox;

    @FXML
    private HBox playerOneNameBox, playerTwoNameBox, playerThreeNameBox, playerFourNameBox, playerFiveNameBox,
                 playerSixNameBox, playerSevenNameBox, playerEightNameBox;
    private HBox[] playersNameBox;
    
    @FXML
    private HBox playerOneLinedCash, playerTwoLinedCash, playerThreeLinedCash, playerFourLinedCash, playerFiveLinedCash,
                 playerSixLinedCash, playerSevenLinedCash, playerEightLinedCash;
    private HBox[] playersLinesCash;

    volatile private boolean isConstructed = false;

    @FXML
    public void checkEvent(ActionEvent e){
    	adapter.check(playerId);
    }
    
    @FXML
    public void foldEvent(ActionEvent e){
        adapter.fold(playerId);
    }
    
    @FXML
    public void raiseEvent(ActionEvent e){
        adapter.raise(playerId, userCashTextField.getText());
        userCashTextField.clear();
    }

    @FXML
    private void intialize() {
        playersCashBox = new HBox[] {playerOneCashBox, playerTwoCashBox, playerThreeCashBox, playerFourCashBox,
                                     playerFiveCashBox, playerSixCashBox, playerSevenCashBox, playerEightCashBox};
        playersNameBox = new HBox[] {playerOneNameBox, playerTwoNameBox, playerThreeNameBox, playerFourNameBox,
                                     playerFiveNameBox, playerSixNameBox, playerSevenNameBox, playerEightNameBox};
        playersLinesCash = new HBox[] {playerOneLinedCash, playerTwoLinedCash, playerThreeLinedCash, playerFourLinedCash,
                                       playerFiveLinedCash, playerSixLinedCash, playerSevenLinedCash, playerEightLinedCash};
        cardsOnTable = new ImageView[] {firstCardOnTable, secondCardOnTable, thirdCardOnTable, fourthCardOnTable, fifthCardOnTable};
        playersCard = new ImageView[] {firstPlayerCard, secondPlayerCard};
        isConstructed = true;
    }
    
    public boolean isConstructed(){
		return isConstructed;
	}
	
    public void setPot(int cash){
        PotBox.getChildren().clear();
    	Text text = new Text("POT: $"+Integer.toString(cash));
    	text.setFill(Color.BEIGE);
    	text.setFont(Font.font(null, FontWeight.BOLD, 20));
    	PotBox.getChildren().add(text);
    	text.setTextAlignment(TextAlignment.CENTER);
    }
    
    public void addPlayer(String name, int id){
    	Text text = new Text(name);
    	DropShadow ds = new DropShadow();
    	ds.setOffsetX(4.0f);
    	ds.setOffsetY(4.0f);
    	text.setEffect(ds);
    	text.setFill(Color.BURLYWOOD);
    	text.setFont(Font.font(null, FontWeight.BOLD, 14));
        playersNameBox[id - 1].getChildren().setAll(text);
    	text.setTextAlignment(TextAlignment.CENTER);
    }
    
    public void removePlayer(int id){
        --id;
        playersNameBox[id].getChildren().clear();
        playersCashBox[id].getChildren().clear();
    }
    
    public void updatePlayerCash(int id,int cash){
    	Text text = new Text("$"+Integer.toString(cash));
    	Light.Distant light = new Light.Distant();
    	light.setAzimuth(-100.0);
    	Lighting lighting = new Lighting();
    	
    	text.setFill(Color.BEIGE);
    	text.setFont(Font.font(null, FontWeight.BOLD, 16));
    	text.setEffect(lighting);
        playersCashBox[id - 1].getChildren().setAll(text);
    	text.setTextAlignment(TextAlignment.CENTER);
    }

    private int getCardColorValue(Card.Color color) {
        switch (color) {
            case CLUBS: return 0;
            case DIAMONDS: return 1;
            case HEARTS: return 2;
            case SPADES: return 3;
            default: throw new RuntimeException("Unknown card color");
        }
    }
    
    public void addOneCard(Card card) {
    	int cardId = card.getValue().id;
    	int cardColor = getCardColorValue(card.getColor());
    	
    	Integer cardNumber = 20*cardColor + cardId;
    	String s = Integer.toString(cardNumber);
    	System.out.println("/main/java/Cards/" + s + ".png");
    	Image image = new Image(TableView.class.getResourceAsStream("/main/java/Cards/" + s + ".png"));
        for (ImageView cardImage: cardsOnTable) {
            if (cardImage == null) {
                cardImage.setImage(image);
                break;
            }
        }
    }

    public void clearTable() {
        for (ImageView cardImage: cardsOnTable) {
            cardImage.setImage(null);
        }
    }

	public void addThreeCardsOnTable(Card firstCard, Card secondCard, Card thirdCard) {
		addOneCard(firstCard);
		
		addOneCard(secondCard);
		
		addOneCard(thirdCard);
		
	}
	
	public void updatePlayerHand(Card firstCard, Card secondCard) {
        Card[] cards = new Card[] {firstCard, secondCard};
		
		if (firstCard == null && secondCard == null) {
			firstPlayerCard.setImage(null);
			secondPlayerCard.setImage(null);
		}

        for (int i = 0; i < 2; ++i) {
            int cardId = cards[i].getValue().id;
            int cardColor = getCardColorValue(cards[i].getColor());

            Integer cardNumber = 20*cardColor + cardId;
            String s = Integer.toString(cardNumber);
            System.out.println("/main/java/Cards/" + s + ".png");
            Image image = new Image(TableView.class.getResourceAsStream("/main/java/Cards/" + s + ".png"));

            playersCard[i].setImage(image);
        }
	}
	
	public void removePlayersLinedCash(int id){
		updatePlayerLinedCash(id, -1);
	}
	
	public void updatePlayerLinedCash(int id, int cash) {
		Text text = new Text("$"+Integer.toString(cash));
		text.setCache(true);
		text.setFill(Color.MAROON);
		text.setFont(Font.font(null, FontWeight.BOLD, 19));

        --id;
        if (playersLinesCash[id] != null) {
            playersLinesCash[id].getChildren().clear();
        }
        if (cash > 0) {
            playersLinesCash[id].getChildren().add(text);
        }

    	text.setTextAlignment(TextAlignment.CENTER);
	}
}

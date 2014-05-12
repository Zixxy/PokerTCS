package main.java.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
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
    private TextField userCashTextField;
    
    @FXML
    private Button btnCheck;
    
    @FXML
    private Button btnRaise;
    
    @FXML
    private Button btnFold;
    
	@FXML
    private ImageView firstPlayerCard;
	
    @FXML
    private ImageView secondPlayerCard;

    @FXML 
    private ImageView firstCardOnTable;
    
    @FXML
    private ImageView secondCardOnTable;

    @FXML
    private ImageView thirdCardOnTable;
    
    @FXML
    private ImageView fourthCardOnTable;
    
    @FXML
    private ImageView fifthCardOnTable;
    
    @FXML
    private HBox playerOneCashBox;

    @FXML
    private HBox playerTwoCashBox;
    
    @FXML
    private HBox playerThreeCashBox;
    
    @FXML
    private HBox playerFourCashBox;
    
    @FXML
    private HBox playerFiveCashBox;
    
    @FXML
    private HBox playerSixCashBox;
    
    @FXML
    private HBox playerSevenCashBox;

    @FXML
    private HBox playerEightCashBox;

    @FXML
    private HBox playerOneNameBox;

    @FXML
    private HBox playerTwoNameBox;
    
    @FXML
    private HBox playerThreeNameBox;

    @FXML
    private HBox playerFourNameBox;
    
    @FXML
    private HBox playerFiveNameBox;
    
    @FXML
    private HBox playerSixNameBox;
    
    @FXML
    private HBox playerSevenNameBox;
    
    @FXML
    private HBox playerEightNameBox;
    
    @FXML
    private HBox playerOneLinedCash;
	
    @FXML
    private HBox playerTwoLinedCash;

    @FXML
    private HBox playerThreeLinedCash;

    @FXML
    private HBox playerFourLinedCash;

    @FXML
    private HBox playerFiveLinedCash;

    @FXML
    private HBox playerSixLinedCash;

    @FXML
    private HBox playerSevenLinedCash;

    @FXML
    private HBox playerEightLinedCash;

    @FXML
    private HBox playerNineLinedCash;
    
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
    
    public boolean isConstructed(){
		if(firstCardOnTable == null
			|| btnFold == null
			|| btnRaise == null
			|| btnCheck == null
			|| userCashTextField == null
			|| secondCardOnTable == null
			|| thirdCardOnTable == null
			|| fourthCardOnTable == null
			|| fifthCardOnTable == null
			|| playerOneCashBox == null
			|| playerTwoCashBox == null
			|| playerThreeCashBox == null
			|| playerFourCashBox == null
			|| playerFiveCashBox == null
			|| playerSixCashBox == null
			|| playerSevenCashBox == null
			|| playerEightCashBox == null
			|| playerOneNameBox == null
			|| playerTwoNameBox == null
			|| playerThreeNameBox == null
			|| playerFourNameBox == null
			|| playerFiveNameBox == null
			|| playerSixNameBox == null
			|| playerSevenNameBox == null
			|| playerEightNameBox == null)
			return false;
		return true;
	}
	
    public void addPlayer(String name, int id){
    	Text text = new Text(name);
    	DropShadow ds = new DropShadow();
    	ds.setOffsetX(4.0f);
    	ds.setOffsetY(4.0f);
    	text.setEffect(ds);
    	text.setFill(Color.SANDYBROWN);
    	text.setFont(Font.font(null, FontWeight.BOLD, 14));
    	
    	switch(id){
    	case 1:
    		playerOneNameBox.getChildren().clear();
    		playerOneNameBox.getChildren().add(text);
    		break;
    	case 2:
    		playerTwoNameBox.getChildren().clear();
    		playerTwoNameBox.getChildren().add(text);
    		break;
    	case 3:
    		playerThreeNameBox.getChildren().clear();
    		playerThreeNameBox.getChildren().add(text);
    		break;
    	case 4:
    		playerFourNameBox.getChildren().clear();
    		playerFourNameBox.getChildren().add(text);
    		break;
    	case 5:
    		playerFiveNameBox.getChildren().clear();
    		playerFiveNameBox.getChildren().add(text);
    		break;
    	case 6:
    		playerSixNameBox.getChildren().clear();
    		playerSixNameBox.getChildren().add(text);
    		break;
    	case 7:
    		playerSevenNameBox.getChildren().clear();
    		playerSevenNameBox.getChildren().add(text);
    		break;
    	case 8:
    		playerEightNameBox.getChildren().clear();
    		playerEightNameBox.getChildren().add(text);
    		break;
    	}
    	text.setTextAlignment(TextAlignment.CENTER);
    }
    
    public void removePlayer(int id){
    	switch(id){
    	case 1:
    		playerOneCashBox.getChildren().clear();
    		playerOneNameBox.getChildren().clear();
    		break;
    	case 2:
    		playerTwoCashBox.getChildren().clear();
    		playerTwoNameBox.getChildren().clear();
    		break;
    	case 3:
    		playerThreeCashBox.getChildren().clear();
    		playerThreeNameBox.getChildren().clear();
    		break;
    	case 4:
    		playerFourCashBox.getChildren().clear();
    		playerFourNameBox.getChildren().clear();
    		break;
    	case 5:
    		playerFiveCashBox.getChildren().clear();
    		playerFiveNameBox.getChildren().clear();
    		break;
    	case 6:
    		playerSixCashBox.getChildren().clear();
    		playerSixNameBox.getChildren().clear();
    		break;
    	case 7:
    		playerSevenCashBox.getChildren().clear();
    		playerSevenNameBox.getChildren().clear();
    		break;
    	case 8:
    		playerEightCashBox.getChildren().clear();
    		playerEightNameBox.getChildren().clear();
    		break;
    	}
    }
    
    public void updatePlayerCash(int id,int cash){
    	Text text = new Text(Integer.toString(cash));
    	switch(id){
    	case 1:
    		playerOneCashBox.getChildren().clear();
    		playerOneCashBox.getChildren().add(text);
    		break;
    	case 2:
    		playerTwoCashBox.getChildren().clear();
    		playerTwoCashBox.getChildren().add(text);
    		break;
    	case 3:
    		playerThreeCashBox.getChildren().clear();
    		playerThreeCashBox.getChildren().add(text);
    		break;
    	case 4:
    		playerFourCashBox.getChildren().clear();
    		playerFourCashBox.getChildren().add(text);
    		break;
    	case 5:
    		playerFiveCashBox.getChildren().clear();
    		playerFiveCashBox.getChildren().add(text);
    		break;
    	case 6:
    		playerSixCashBox.getChildren().clear();
    		playerSixCashBox.getChildren().add(text);
    		break;
    	case 7:
    		playerSevenCashBox.getChildren().clear();
    		playerSevenCashBox.getChildren().add(text);
    		break;
    	case 8:
    		playerEightCashBox.getChildren().clear();
    		playerEightCashBox.getChildren().add(text);
    		break;
    	}
    	text.setTextAlignment(TextAlignment.CENTER);
    }
    
    public void addOneCard(Card card) {
    	/* 
    	 * Cards' colors:
    	 * CLUBS - 0
    	 * DIAMONDS - 1
    	 * HEARTS - 2
    	 * SPADES - 3
    	 */
    	int cardId = card.getValue().id;
    	int cardColor = 0;
    	if(card.getColor() == Card.Color.CLUBS){
    		cardColor = 0;
    	}
    	else if(card.getColor() == Card.Color.DIAMONDS){
    		cardColor = 1;
    	}
    	else if(card.getColor() == Card.Color.HEARTS){
    		cardColor = 2;
    	}
    	else if(card.getColor() == Card.Color.SPADES){
    		cardColor = 3;
    	}
    	
    	Integer cardNumber = 20*cardColor + cardId;
    	String s = Integer.toString(cardNumber);
    	System.out.println("/main/java/Cards/" + s + ".png");
    	Image image = new Image(TableView.class.getResourceAsStream("/main/java/Cards/" + s + ".png"));
    	if(firstCardOnTable.getImage() == null)
    		firstCardOnTable.setImage(image);

    	else if(secondCardOnTable.getImage() == null)
    		secondCardOnTable.setImage(image);
    	
    	else if(thirdCardOnTable.getImage() == null)
    		thirdCardOnTable.setImage(image);
    	
    	else if(fourthCardOnTable.getImage() == null)
    		fourthCardOnTable.setImage(image);
    	
    	else if(fifthCardOnTable.getImage() == null)
    		fifthCardOnTable.setImage(image);
    	
    	return;  	
    }

    public void clearTable() {
		firstCardOnTable.setImage(null);

		secondCardOnTable.setImage(null);
		
		thirdCardOnTable.setImage(null);

		fourthCardOnTable.setImage(null);

		fifthCardOnTable.setImage(null);

    	return;  
    }

	public void addThreeCardsOnTable(Card firstCard, Card secondCard, Card thirdCard) {
		addOneCard(firstCard);
		
		addOneCard(secondCard);
		
		addOneCard(thirdCard);
		
	}
	
	public void updatePlayerHand(Card firstCard, Card secondCard) {
		/* 
    	 * Cards' colors:
    	 * CLUBS - 0
    	 * DIAMONDS - 1
    	 * HEARTS - 2
    	 * SPADES - 3
    	 */
		
		if(firstCard == null && secondCard == null)
		{
			firstPlayerCard.setImage(null);

			secondPlayerCard.setImage(null);
		}
		
    	int cardId = firstCard.getValue().id;
    	int cardColor = 0;
    	if(firstCard.getColor() == Card.Color.CLUBS){
    		cardColor = 0;
    	}
    	else if(firstCard.getColor() == Card.Color.DIAMONDS){
    		cardColor = 1;
    	}
    	else if(firstCard.getColor() == Card.Color.HEARTS){
    		cardColor = 2;
    	}
    	else if(firstCard.getColor() == Card.Color.SPADES){
    		cardColor = 3;
    	}
    	
    	Integer cardNumber = 20*cardColor + cardId;
    	String s = Integer.toString(cardNumber);
    	System.out.println("/main/java/Cards/" + s + ".png");
    	Image image = new Image(TableView.class.getResourceAsStream("/main/java/Cards/" + s + ".png"));

		firstCardOnTable.setImage(image);
		
		//-----------------------------------------------------------------------------------------------------
		
		cardId = secondCard.getValue().id;
		if(secondCard.getColor() == Card.Color.CLUBS){
    		cardColor = 0;
    	}
    	else if(secondCard.getColor() == Card.Color.DIAMONDS){
    		cardColor = 1;
    	}
    	else if(secondCard.getColor() == Card.Color.HEARTS){
    		cardColor = 2;
    	}
    	else if(secondCard.getColor() == Card.Color.SPADES){
    		cardColor = 3;
    	}
    	
    	cardNumber = 20*cardColor + cardId;
    	s = Integer.toString(cardNumber);
    	System.out.println("/main/java/Cards/" + s + ".png");
    	image = new Image(TableView.class.getResourceAsStream("/main/java/Cards/" + s + ".png"));

    	
    	secondCardOnTable.setImage(image);
	}
	
	public void updatePlayerLinedCash(int id, int cash) {
    	Text text = new Text(Integer.toString(cash));
    	switch(id){
    	case 1:
    		playerOneLinedCash.getChildren().clear();
    		playerOneLinedCash.getChildren().add(text);
    		break;
    	case 2:
    		playerTwoLinedCash.getChildren().clear();
    		playerTwoLinedCash.getChildren().add(text);
    		break;
    	case 3:
    		playerThreeLinedCash.getChildren().clear();
    		playerThreeLinedCash.getChildren().add(text);
    		break;
    	case 4:
    		playerFourLinedCash.getChildren().clear();
    		playerFourLinedCash.getChildren().add(text);
    		break;
    	case 5:
    		playerFiveLinedCash.getChildren().clear();
    		playerFiveLinedCash.getChildren().add(text);
    		break;
    	case 6:
    		playerSixLinedCash.getChildren().clear();
    		playerSixLinedCash.getChildren().add(text);
    		break;
    	case 7:
    		playerSevenLinedCash.getChildren().clear();
    		playerSevenLinedCash.getChildren().add(text);
    		break;
    	case 8:
    		playerEightLinedCash.getChildren().clear();
    		playerEightLinedCash.getChildren().add(text);
    		break;
    	}
    	text.setTextAlignment(TextAlignment.CENTER);
	}
}

package main.java.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
    ImageView firstCardOnTable;
    
    @FXML
    ImageView secondCardOnTable;

    @FXML
    ImageView thirdCardOnTable;
    
    @FXML
    ImageView fourthCardOnTable;
    
    @FXML
    ImageView fifthCardOnTable;
    
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
		if(playerOneCashBox == null)
			return false;
					
		/*if(firstCardOnTable == null
			|| btnFold == null
			|| btnRaise == null
			|| btnCheck == null
			|| userCashTextField == null
			|| secondCardOnTable == null
			|| thirdCardOnTable == null
			|| fourthCardOnTable == null
			|| FifthCardOnTable == null
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
			return false;*/
		return true;
	}
	
	public void addPlayer(String name, int id){
		while(playerOneCashBox == null)
			Thread.yield();
    	Text text = new Text(name);
    	switch(id){
    	case 1:
    		playerOneNameBox.getChildren().add(text);
    		break;
    	case 2:
    		playerTwoNameBox.getChildren().add(text);
    		break;
    	case 3:
    		playerThreeNameBox.getChildren().add(text);
    		break;
    	case 4:
    		playerFourNameBox.getChildren().add(text);
    		break;
    	case 5:
    		playerFiveNameBox.getChildren().add(text);
    		break;
    	case 6:
    		playerSixNameBox.getChildren().add(text);
    		break;
    	case 7:
    		playerSevenNameBox.getChildren().add(text);
    		break;
    	case 8:
    		playerEightNameBox.getChildren().add(text);
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
		/*firstCardOnTable.setImage(null);

		secondCardOnTable.setImage(null);
		
		thirdCardOnTable.setImage(null);

		fourthCardOnTable.setImage(null);

		fifthCardOnTable.setImage(null);*/

    	return;  
    }

	public void addThreeCardsOnTable(Card firstCard, Card secondCard, Card thirdCard) {
		addOneCard(firstCard);
		
		addOneCard(secondCard);
		
		addOneCard(thirdCard);
		
	}
}

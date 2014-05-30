package main.java.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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
	
    volatile private boolean isConstructed = false;
    
	private int playerId;
	
	public Card[] thisPlayerCards;
	
	///private ExecutorService tasksExecutor = Executors.newSingleThreadExecutor();
	
	public TableControler(){
		playerId = TableView.tempPlayerId;
		adapter = TableView.tempAdapter;
		TableView.RecentlyCreatedInstanceOfTableControler = this;
	}
	
	@FXML
	private HBox PotBox;

    @FXML
    private TextArea chatTextArea;
    
    @FXML
    private TextField messageTextField;
	
    @FXML
    private TextField userCashTextField;
    
    @FXML
    private Button btnCheck, btnRaise, btnFold, startButton, showCardsButton, exitButton;
    
    @FXML
    private ImageView playerOneFace, playerTwoFace, playerThreeFace, playerFourFace, playerFiveFace,
    				playerSixFace, playerSevenFace, playerEightFace;
    private ImageView[] playersFace;
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
    private HBox[] playersLinedCash;
    
    @FXML
    private HBox playerOneLastMove, playerTwoLastMove, playerThreeLastMove, playerFourLastMove, playerFiveLastMove,
    playerSixLastMove, playerSevenLastMove, playerEightLastMove;
    private HBox[] playersLastMove;
    
    @FXML
    private ImageView playerOneFirstCard, playerOneSecondCard, playerTwoFirstCard, playerTwoSecondCard, playerThreeFirstCard,
    playerThreeSecondCard, playerFourFirstCard, playerFourSecondCard, playerFiveFirstCard, playerFiveSecondCard,
    playerSixFirstCard, playerSixSecondCard, playerSevenFirstCard, playerSevenSecondCard, playerEightFirstCard, playerEightSecondCard;
    private ImageView[] playersCards;
    
    @FXML
    public void chatTyping(ActionEvent e){
    	String message = messageTextField.getText();
    	typeMessageToUserInChat(message, false);
    	messageTextField.clear();
    }

    @FXML
    public void checkEvent(ActionEvent e){
    	javafx.application.Platform.runLater((new Runnable() {
    		@Override
    		public void run(){
    			adapter.check(playerId);
    		}
    	}));
    }
    
    @FXML
    public void foldEvent(ActionEvent e){
    	javafx.application.Platform.runLater((new Runnable() {
    		@Override
    		public void run(){
    			adapter.fold(playerId);
    		}
    	}));
    }
    
    @FXML
    public void raiseEvent(ActionEvent e){
    	javafx.application.Platform.runLater((new Runnable() {
    		@Override
    		public void run(){
    			adapter.raise(playerId, userCashTextField.getText());
    			userCashTextField.clear();
    		}
    	}));
    }
    
    @FXML
    public void showCardsEvent(ActionEvent e){
    	javafx.application.Platform.runLater((new Runnable() {
    		@Override
    		public void run(){
    			adapter.showPlayerCards(playerId, thisPlayerCards);
    		}
    	}));
    }
    
    @FXML
    public void startEvent(ActionEvent e){
    	javafx.application.Platform.runLater((new Runnable() {
    		@Override
    		public void run(){
    			adapter.start();
    		}
    	}));
    }

    @FXML
    private void initialize() {
        playersCashBox = new HBox[] {playerOneCashBox, playerTwoCashBox, playerThreeCashBox, playerFourCashBox,
                                     playerFiveCashBox, playerSixCashBox, playerSevenCashBox, playerEightCashBox};
        playersNameBox = new HBox[] {playerOneNameBox, playerTwoNameBox, playerThreeNameBox, playerFourNameBox,
                                     playerFiveNameBox, playerSixNameBox, playerSevenNameBox, playerEightNameBox};
        playersLinedCash = new HBox[] {playerOneLinedCash, playerTwoLinedCash, playerThreeLinedCash, playerFourLinedCash,
                                       playerFiveLinedCash, playerSixLinedCash, playerSevenLinedCash, playerEightLinedCash};
        cardsOnTable = new ImageView[] {firstCardOnTable, secondCardOnTable, thirdCardOnTable, fourthCardOnTable, fifthCardOnTable};
        playersCard = new ImageView[] {firstPlayerCard, secondPlayerCard};
        playersLastMove = new HBox[] {playerOneLastMove, playerTwoLastMove, playerThreeLastMove, playerFourLastMove, playerFiveLastMove,
        								playerSixLastMove, playerSevenLastMove, playerEightLastMove};
        playersFace = new ImageView[] {playerOneFace, playerTwoFace, playerThreeFace, playerFourFace, playerFiveFace,
									playerSixFace, playerSevenFace, playerEightFace};
        playersCards = new ImageView[] {playerOneFirstCard, playerOneSecondCard, playerTwoFirstCard, playerTwoSecondCard, playerThreeFirstCard,
        	    playerThreeSecondCard, playerFourFirstCard, playerFourSecondCard, playerFiveFirstCard, playerFiveSecondCard,
        	    playerSixFirstCard, playerSixSecondCard, playerSevenFirstCard, playerSevenSecondCard, playerEightFirstCard, playerEightSecondCard};
      
        isConstructed = true;
        
        synchronized(MainWindow.TableControlerSynchronizer){
        	MainWindow.TableControlerSynchronizer.notifyAll();
        }
    }
    
    public boolean isConstructed(){
		return isConstructed;
	}

    public void typeMessageToUserInChat(String message, boolean gameCommunicate){
    	StringBuilder newMessage = new StringBuilder();
    	newMessage.append(message);
    	newMessage.append("\n");
    	if(gameCommunicate)
    	{} // text will be red.
    	chatTextArea.appendText(newMessage.toString());
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
    	playersFace[id - 1].setImage(new Image(TableView.class.getResourceAsStream("/main/java/Pictures/playingPerson.gif")));
    }
    
    public void removePlayer(int id){
        --id;
        playersNameBox[id].getChildren().clear();
        playersCashBox[id].getChildren().clear();
        playersLastMove[id].getChildren().clear();
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
            if (cardImage.getImage() == null) {
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
	
	public void clearPlayerLastMove(int id){
		playersLastMove[id].getChildren().clear();
	}
	
	public void updatePlayerLinedCash(int id, int cash) {
		Text text = new Text("$"+Integer.toString(cash));
		text.setCache(true);
		text.setFill(Color.MAROON);
		text.setFont(Font.font(null, FontWeight.BOLD, 19));

        --id;
        if (playersLinedCash[id] != null) {
            playersLinedCash[id].getChildren().clear();
        }
        if (cash > 0) {
            playersLinedCash[id].getChildren().add(text);
        }

    	text.setTextAlignment(TextAlignment.CENTER);
	}
	public void updateActualPlayer(int id) {
		Image image = new Image(TableView.class.getResourceAsStream("/main/java/Pictures/playingPerson.gif"));
		for(int i = 0; i < playersFace.length; ++i){
			if(i!=id){
				if(playersFace[i] != null){
					playersFace[i].setImage(image);
				}
			}
			
		}
		playersFace[id].setImage(new Image(TableView.class.getResourceAsStream("/main/java/Pictures/actualPerson.gif"))); 
	}

	public void updateResignedPlayer(int id) {
		playersFace[id].setImage(null);
	}

	public void updateNormalPlayer(int id) {
		Image image = new Image(TableView.class.getResourceAsStream("/main/java/Pictures/playingPerson.gif"));
		playersFace[id].setImage(image);
	}

	public void setLastMove(int id, int move) {
		/*
		 * move:
		 * 0 - raise
		 * 1 - fold
		 * 2 - check
		 * 3 - all in
		 */
		Text text;
		switch(move){
			case 0:
				text = new Text("RAISE");
				break;
			case 1:
				text = new Text("FOLD");
				break;
			case 2:
				text = new Text("CHECK");
				break;
			case 3:
				text = new Text("ALL IN");
				break;
			default:
				throw new RuntimeException("Unknown operation type");
		}
    	text.setFill(Color.WHITE);
    	text.setFont(Font.font(null, FontWeight.BOLD, 14));
    	text.setTextAlignment(TextAlignment.CENTER);
    	playersLastMove[id-1].getChildren().clear();
    	playersLastMove[id-1].getChildren().add(text);		
	}
	
	public void showCards(int id, int firstCardNumber, int secondCardNumber){
		Integer[] cardsNumbers = new Integer[] { firstCardNumber, secondCardNumber };
		for (int i = 0; i < 2; ++i) {
			String s = cardsNumbers[i].toString();
            System.out.println("/main/java/Cards/" + s + ".png");
            Image image = new Image(TableView.class.getResourceAsStream("/main/java/Cards/" + s + ".png"));

            playersCards[2*(id-1) + i].setImage(image);
        }
	}
}

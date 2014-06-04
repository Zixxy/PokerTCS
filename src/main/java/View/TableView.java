package View;

import java.util.concurrent.atomic.AtomicBoolean;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Adapter.MainAdapter;
import Model.Deck.Card;


public class TableView extends Application implements ViewInterface{
	private MainAdapter adapter;
	
	public static int playerId;
	
	private TableView referenceToThis;
	
	private TableControler tableControler;
	
	private static AtomicBoolean constructionFlag = new AtomicBoolean(false);
	
	public static TableView RecentlyCreatedInstanceOfThis;
	
	public static MainAdapter tempAdapter;
	
	public static int tempPlayerId;
	
	public static TableControler RecentlyCreatedInstanceOfTableControler;

	public static String tempName;
	
	public TableView(){
		adapter = tempAdapter;
		playerId = tempPlayerId;
		referenceToThis = this;
		RecentlyCreatedInstanceOfThis = referenceToThis;
		constructionFlag.set(true);
	}

	public static ViewInterface getTableView(MainAdapter adapt, int userId, String name){
		tempAdapter = adapt;
		System.out.println("adapter:= "+adapt);
		//tempPlayerId = userId;
		tempName = name;
		return new TableView();
	}
	/*
	 * this method soon will be removed, when both gui and adapter will get used to new formation of files.
	 */
	@Override
	public void almostConstructor(){
		tableControler = RecentlyCreatedInstanceOfTableControler;
	}

	@Override
	public void setPot(final int cash){
		javafx.application.Platform.runLater(new Runnable() {

			@Override
			public void run() {
				RecentlyCreatedInstanceOfTableControler.setPot(cash);
			}
		});
	}

	@Override
	public void addPlayer(final String name,final int id, final int image) {
		javafx.application.Platform.runLater(new Runnable() {

			@Override
			public void run() {
				tableControler.addPlayer(name, id+1, image);
			}
		});
	}
	@Override
	public void removePlayer(final int id) {
		javafx.application.Platform.runLater(new Runnable() {

			@Override
			public void run() {
				tableControler.removePlayer(id+1);
			}
		});
	}
	@Override
	public void updatePlayerCash(final int id,final int cash) {
		javafx.application.Platform.runLater(new Runnable() {

			@Override
			public void run() {
				tableControler.updatePlayerCash(id+1,cash);
			}
		});
	}
	@Override
	public void addThreeCardsOnTable(final Card firstCard,final Card secondCard,final Card thirdCard) {
		javafx.application.Platform.runLater(new Runnable() {

			@Override
			public void run() {
				tableControler.addThreeCardsOnTable(firstCard, secondCard, thirdCard);
			}
		});
	}
	@Override
	public void addOneCard(final Card card) {
		javafx.application.Platform.runLater(new Runnable() {

			@Override
			public void run() {
				tableControler.addOneCard(card);
			}
		});
	}
	@Override
	public void clearTable() {
		javafx.application.Platform.runLater(new Runnable() {

			@Override
			public void run() {
				setPot(0);
				tableControler.clearTable();
				for(int i=0; i < 8; i++)
					tableControler.removePlayersLinedCash(i+1);
			}
		});
	}
	@Override
	public void updatePlayerHand(final int id, final Card firstCard, final Card secondCard) {
			javafx.application.Platform.runLater(new Runnable() {

				@Override
				public void run() {
					RecentlyCreatedInstanceOfTableControler.thisPlayerCards = new Card[] { firstCard, secondCard };
					tableControler.updatePlayerHand(firstCard, secondCard);
				}
			});
	}
	@Override
	public void updatePlayerLinedCash(final int id,final int cash) {
		javafx.application.Platform.runLater(new Runnable() {
			@Override
			public void run() {
				tableControler.updatePlayerLinedCash(id, cash);
			}
		});
	}
	
	@Override
	public void removePlayersLinedCash(final int id) {
		javafx.application.Platform.runLater(new Runnable() {
			@Override
			public void run() {
				tableControler.removePlayersLinedCash(id);
			}
		});
	}
	
	@Override
	public void sendMessage(final String text) {
		javafx.application.Platform.runLater(new Runnable(){
			@Override
			public void run() {
				tableControler.typeMessageToUserInChat(text, true);
			}
		});
	}
	
	@Override
	public void startNewRound() {
		//Card[] cards = adapter.getHandCards(playerId);
		//updatePlayerHand(cards[0],cards[1]);
	}
	
	@Override
	public void updateActualPlayer(final int id) {
		javafx.application.Platform.runLater(new Runnable() {
			@Override
			public void run() {
				tableControler.updateActualPlayer(id);
			}
		});
		
	}

	@Override
	public void updateNormalPlayer(final int id) {
		javafx.application.Platform.runLater(new Runnable() {
			@Override
			public void run() {
				tableControler.updateNormalPlayer(id);
			}
		});
		
	}

	@Override
	public void updateResignedPlayer(final int id) {
		javafx.application.Platform.runLater(new Runnable() {
			@Override
			public void run() {
				tableControler.updateResignedPlayer(id);
			}
		});
		
	}
	
	@Override
	public void setLastMove(final int id, final int move){
		javafx.application.Platform.runLater(new Runnable() {
			@Override
			public void run() {
				tableControler.setLastMove(id+1, move);
			}
		});
	}
	
	@Override
	public void showCards(final int playerId, final int firstCardNumber, final int secondCardNumber) {
		System.out.println("in tableView showCards method: "+playerId);
		javafx.application.Platform.runLater(new Runnable() {
			@Override
			public void run() {
				System.out.println("ok, tableView showCards");
				tableControler.showCards(playerId+1, firstCardNumber, secondCardNumber);
			}
		});
		
	}
	
	/*
	 * this method soon will be removed, when both gui and adapter will get used to new formation of files.
	 */
	@Deprecated
	public void start(Stage primaryStage) throws Exception {
		AnchorPane root = FXMLLoader.load(getClass().getResource("/main/java/FXML/TableView.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("PokerTCS");
		primaryStage.setScene(scene);

		primaryStage.show();

	}
//-------------------------------------------------------------------------------------------------------------------------------
	/*
	 * we do not use this methods in game.
	 */


    @Override
    public void guiAddTable(int numberOfTable, String started) {

    }

    @Override
	public void guiRemoveTable(int numberOfTable) {
	}

	@Override
	public void updateNumberOfPlayers(int numberOfTable,
			int currentNumberOfPlayers) {
	}

	@Override
	public void setPlayerId(int id) {
		tableControler.setPlayerId(id);
		playerId = id;
        tempPlayerId=id;
        TableControler.playerId=id;
        System.err.println("Jestem w setPlayerId TableView "+id);
	}

	@Override
	public void guiClearTableList() {
        // UNUSED
		
	}
}
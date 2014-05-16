package main.java.View;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.Adapter.MainAdapter;
import main.java.Model.Deck.Card;


public class TableView extends Application implements ViewInterface{
	private MainAdapter adapter;
	
	private int playerId;
	
	private TableView referenceToThis;
	
	private TableControler tableControler;
	
	private static AtomicBoolean constructionFlag = new AtomicBoolean(false);
	
	private static TableView RecentlyCreatedInstanceOfThis;
	
	public static MainAdapter tempAdapter;
	
	public static int tempPlayerId;
	
	public static TableControler RecentlyCreatedInstanceOfTableControler;

	public TableView(){
		adapter = tempAdapter;
		playerId = tempPlayerId;
		referenceToThis = this;
		RecentlyCreatedInstanceOfThis = referenceToThis;
		constructionFlag.set(true);
	}

	public static synchronized ViewInterface createTableView(final String[] args, MainAdapter a, int p){
		tempAdapter = a;
		tempPlayerId = p;
		Thread viewThread = new Thread(){
			public void run(){
				Application.launch(TableView.class, args);
			}
		};
		viewThread.start();
		while(!constructionFlag.get()){
			Thread.yield();
		}
		while(RecentlyCreatedInstanceOfTableControler == null){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		RecentlyCreatedInstanceOfThis.almostConstructor();
		while(!RecentlyCreatedInstanceOfTableControler.isConstructed()){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		constructionFlag.set(false);
		return RecentlyCreatedInstanceOfThis;
	}
	private void almostConstructor(){
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
	public void addPlayer(final String name,final int id) {
		javafx.application.Platform.runLater(new Runnable() {

			@Override
			public void run() {
				tableControler.addPlayer(name, id+1);
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
	public void updatePlayerHand(final Card firstCard, final Card secondCard) {
		javafx.application.Platform.runLater(new Runnable() {

			@Override
			public void run() {
				tableControler.updatePlayerHand(firstCard, secondCard);
			}
		});
	}
	@Override
	public void updatePlayerLinedCash(final int id,final int cash) {
		javafx.application.Platform.runLater(new Runnable() {
			@Override
			public void run() {
				tableControler.updatePlayerLinedCash(id+1, cash);
			}
		});
	}
	
	@Override
	public void removePlayersLinedCash(final int id) {
		javafx.application.Platform.runLater(new Runnable() {
			@Override
			public void run() {
				tableControler.removePlayersLinedCash(id+1);
			}
		});
	}
	
	@Override
	public void sendMessage(String text) {
		System.out.println(text);
	}
	
	@Override
	public void startNewRound() {
		Card[] cards = adapter.getHandCards(playerId);
		updatePlayerHand(cards[0],cards[1]);
	}
	
	public void start(Stage primaryStage) throws Exception {
		AnchorPane root = FXMLLoader.load(getClass().getResource("SceneGraph.fxml"));
		Scene scene = new Scene(root);
		// grid.setGridLinesVisible(true);
		primaryStage.setTitle("PokerTCS");
		primaryStage.setScene(scene);

		primaryStage.show();

	}
}
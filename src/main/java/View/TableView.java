package main.java.View;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.Adapter.MainAdapter;
import main.java.Model.Deck;


public class TableView extends Application implements ViewInterface{
	private MainAdapter adapter;
	private int playerId;
	public static MainAdapter tempAdapter;
	public static int tempPlayerId;
	private static AtomicInteger constructionCounter = new AtomicInteger(0);
	private static TableView latestCreatedTableView;
	public static TableControler latestCreatedTableControler;

	public TableView(){
		constructionCounter.incrementAndGet();
		almostConstructor();
		latestCreatedTableView = this;
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
		while(constructionCounter.get() < 1){
			Thread.yield();
		}
		while(latestCreatedTableControler == null){
			System.out.println("as!s " + latestCreatedTableControler );
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		constructionCounter = new AtomicInteger(0);
		return latestCreatedTableView;
	}
	private void almostConstructor(){
		adapter = tempAdapter;
		playerId = tempPlayerId;
	}

	@Override
    public void addPlayer(String name, int id) {
    }
    @Override
    public void removePlayer(int id) {
    }
    @Override
    public void updatePlayerCash(int id, int cash) {
    }
    @Override
    public void addThreeCardsOnTable(Deck firstCard, Deck secondCard,
    		Deck thirdCard) {
    }
    @Override
    public void addOneCard(Deck card) {
    	latestCreatedTableControler.addOneCard(card);  	
    }
    @Override
    public void clearTable() {
    	latestCreatedTableControler.clearTable();
    }
    @Override
    public void updatePlayerHand(Deck firstCard, Deck secondCard) {
    }
    @Override
    public void updatePlayerLinedCash(int id, int cash) {
    }

    public void start(Stage primaryStage) throws Exception {
    	AnchorPane root = FXMLLoader.load(getClass().getResource("Test.fxml"));
        Scene scene = new Scene(root);
        //	grid.setGridLinesVisible(true);
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    @Override
    public void sendMessage(String text) {
        System.out.println(text);
    }
}

package main.java.View;

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
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while(!latestCreatedTableControler.isConstructed()){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
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
    public void addPlayer(final String name,final int id) {
		 javafx.application.Platform.runLater(new Runnable() {

		        @Override
		        public void run() {
		        	latestCreatedTableControler.addPlayer(name, id+1);
		        }
		    });
    }
    @Override
    public void removePlayer(final int id) {
    	javafx.application.Platform.runLater(new Runnable() {

    		@Override
    		public void run() {
    			latestCreatedTableControler.removePlayer(id+1);
    		}
    	});
    }
    @Override
    public void updatePlayerCash(final int id,final int cash) {
    	javafx.application.Platform.runLater(new Runnable() {

    		@Override
    		public void run() {
    			latestCreatedTableControler.updatePlayerCash(id+1,cash);
    		}
    	});
    }
    @Override
    public void addThreeCardsOnTable(final Card firstCard,final Card secondCard,final Card thirdCard) {
    	javafx.application.Platform.runLater(new Runnable() {

    		@Override
    		public void run() {
    			latestCreatedTableControler.addThreeCardsOnTable(firstCard, secondCard, thirdCard);
    		}
    	});
    }
    @Override
    public void addOneCard(final Card card) {
    	javafx.application.Platform.runLater(new Runnable() {

    		@Override
    		public void run() {
    			latestCreatedTableControler.addOneCard(card);  	
    		}
    	});
    }
    @Override
    public void clearTable() {
    	javafx.application.Platform.runLater(new Runnable() {

    		@Override
    		public void run() {
    			latestCreatedTableControler.clearTable();
    		}
    	});
    }
    @Override
    public void updatePlayerHand(Card firstCard, Card secondCard) {
    }
    @Override
    public void updatePlayerLinedCash(int id, int cash) { // pamietaj o id +1!!!
    }

    public void start(Stage primaryStage) throws Exception {
    	AnchorPane root = FXMLLoader.load(getClass().getResource("Test.fxml"));
        Scene scene = new Scene(root);
        //	grid.setGridLinesVisible(true);
        primaryStage.setTitle("PokerTCS");
        primaryStage.setScene(scene);

        primaryStage.show();

    }
    @Override
    public void sendMessage(String text) {
        System.out.println(text);
    }
}

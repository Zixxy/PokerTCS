package main.java.View;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.Adapter.MainAdapter;


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
    public void addThreeCardsOnTable(Integer firstCard, Integer secondCard,
                                     Integer thirdCard) {
    }
    @Override
    public void addOneCard(Integer card) {
    	Image image = new Image(TableView.class.getResourceAsStream("person.gif"));
    	if(latestCreatedTableControler.firstCardOnTable.getImage() == null)
    		latestCreatedTableControler.firstCardOnTable.setImage(image);

    	else if(latestCreatedTableControler.secondCardOnTable.getImage() == null)
    		latestCreatedTableControler.secondCardOnTable.setImage(image);
    	
    	else if(latestCreatedTableControler.thirdCardOnTable.getImage() == null)
    		latestCreatedTableControler.thirdCardOnTable.setImage(image);
    	
    	else if(latestCreatedTableControler.fourthCardOnTable.getImage() == null)
    		latestCreatedTableControler.fourthCardOnTable.setImage(image);
    	
    	else if(latestCreatedTableControler.fifthCardOnTable.getImage() == null)
    		latestCreatedTableControler.fifthCardOnTable.setImage(image);
    	
    	return;  	
    }
    @Override
    public void clearTable() {
    	/*if(latestCreatedTableControler.firstCardOnTable.getImage() != null)
    		latestCreatedTableControler.firstCardOnTable.setImage(null);

    	if(latestCreatedTableControler.secondCardOnTable.getImage() != null)
    		latestCreatedTableControler.secondCardOnTable.setImage(null);
    	
    	if(latestCreatedTableControler.thirdCardOnTable.getImage() != null)
    		latestCreatedTableControler.thirdCardOnTable.setImage(null);
    	
    	if(latestCreatedTableControler.fourthCardOnTable.getImage() != null)
    		latestCreatedTableControler.fourthCardOnTable.setImage(null);
    	
    	if(latestCreatedTableControler.fifthCardOnTable.getImage() != null)
    		latestCreatedTableControler.fifthCardOnTable.setImage(null);
    	*/
    	return;  
    }
    @Override
    public void updatePlayerHand(Integer firstCardId, Integer secondCardId) {
    }
    @Override
    public void updatePlayerLinedCash(int id, int cash) {
    }

    public void start(Stage primaryStage) throws Exception {
    	AnchorPane root = FXMLLoader.load(getClass().getResource("Test.fxml"));
        Scene scene = new Scene(root);
        //	grid.setGridLinesVisible(true);
        primaryStage.setTitle("Welcome");
        addOneCard(1);
        clearTable();
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    @Override
    public void sendMessage(String text) {
        System.out.println(text);
    }
}

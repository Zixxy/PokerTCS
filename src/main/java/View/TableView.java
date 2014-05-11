package main.java.View;

import java.net.URL;
import java.util.ResourceBundle;

import main.java.Adapter.MainAdapter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class TableView extends Application implements ViewInterface, Initializable{
    private MainAdapter adapter;
    private int playerId;
    
    private static MainAdapter tempAdapter;
    private static int tempPlayerId;
    private static int constructionCounter = 0;
    
	@FXML // fx:id="userCashTextField"
    private TextField userCashTextField; // Value injected by FXMLLoader

    @FXML // fx:id="btnCheck"
    private Button btnCheck; // Value injected by FXMLLoader

    @FXML // fx:id="btnFold"
    private Button btnFold; // Value injected by FXMLLoader

    @FXML // fx:id="btnRaise"
    private Button btnRaise; // Value injected by FXMLLoader
    
    private static TableView latestCreatedTableView;
   
    public TableView(){
    	constructionCounter ++;
    	almostConstructor();
    	latestCreatedTableView = this;
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
	}
    
    public static synchronized ViewInterface createTableView(final String[] args, MainAdapter a, int p){
    	if(latestCreatedTableView != null){
    		ViewInterface out = new Table(a,p);
    		javafx.application.Platform.runLater(new Table(a,p));
    		return out;
    	}
    	tempAdapter = a;
        tempPlayerId = p;
    	Application.launch(TableView.class, args);
        while(constructionCounter < 2)
    		Thread.yield();
        constructionCounter = 0;
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
    }
    @Override
    public void clearTable() {
    }
    @Override
    public void updatePlayerHand(Integer firstCardId, Integer secondCardId) {
    }
    @Override
    public void updatePlayerLinedCash(int id, int cash) {
    }
    
    @FXML
    public void checkEvent(ActionEvent e){
    	adapter.check(playerId);
    }
    
    @FXML
    public void foldEvent(ActionEvent e){
    	System.out.println("adapter"+adapter);
        adapter.fold(playerId);
    }
    
    @FXML
    public void raiseEvent(ActionEvent e){
        adapter.raise(playerId, userCashTextField.getText());
        userCashTextField.clear();
    }
    
    public void start(Stage primaryStage) throws Exception {
    	AnchorPane root = FXMLLoader.load(getClass().getResource("Test.fxml"));
        /*btnFold.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                adapter.fold(playerId);
            }
        });

        btnRaise.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                adapter.raise(playerId, userCashTextField.getText());
                userCashTextField.clear();
            }
        });*/

       /* btnCheck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                adapter.check(playerId);
            }
        });*/

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
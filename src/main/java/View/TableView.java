package main.java.View;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.*;
import javafx.stage.Stage;
import main.java.Adapter.MainAdapter;


public class TableView extends Application implements ViewInterface{
    private MainAdapter adapter;
    private int playerId;
    
    public static MainAdapter tempAdapter;
    public static int tempPlayerId;
    private static AtomicInteger constructionCounter = new AtomicInteger(0);
    private static TableView latestCreatedTableView;
   
    public TableView(){
    	constructionCounter.incrementAndGet();
    	almostConstructor();
    	latestCreatedTableView = this;
    }
    
    public static synchronized ViewInterface createTableView(final String[] args, MainAdapter a, int p){
    	tempAdapter = a;
        tempPlayerId = p;
    	System.out.println("aww");
    //	Thread viewThread = new Thread(){
    //		public void run(){
    		Application.launch(TableView.class, args);
    //		}
    //	};
    	
        while(constructionCounter.get() < 2){
        	Thread.yield();
        }
        constructionCounter = new AtomicInteger(0);
        latestCreatedTableView.loadPlayers();
    	return latestCreatedTableView;
    }
    
    private void loadPlayers(){
    //	TextField userTextField = new TextField();
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

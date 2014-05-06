package main.java.View;

import main.java.Adapter.MainAdapter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class TableView extends Application implements ViewInterface{
    private MainAdapter adapter;
    private int playerId;
    private String cssPath;
    
    private static TableView latestCreatedTableView;
   
    public TableView(){
    	latestCreatedTableView = this;
    }
    
    public static synchronized TableView createTableView(final String[] args, MainAdapter a, int p){
    	Thread createTableViewCaller = Thread.currentThread();
    	TableView previouslyCreatedTableView = TableView.latestCreatedTableView;
    	Thread viewThread = new Thread(){
            public void run(){
            	TableView.launch(TableView.class, args);
            }
        };
        viewThread.start();
        while(TableView.latestCreatedTableView == previouslyCreatedTableView )
    		Thread.yield();
        while(!TableView.latestCreatedTableView.isConstructed()){
    		TableView.latestCreatedTableView.almostConstructor(a, p);
    		createTableViewCaller.yield();
    	}
    	return latestCreatedTableView;
    }
  
    private boolean isConstructed(){
    	if(adapter != null)
    		return true;
    	return false;
    }
    
    private void almostConstructor(MainAdapter a, int p){
        adapter = a;
        playerId = p;
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
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BOTTOM_RIGHT);
        grid.setHgap(20);
        grid.setVgap(0);
        grid.setPadding(new Insets(10, 20, 10, 200));
        final TextField userCashTextField = new TextField();
        grid.add(userCashTextField, 0, 1);
        Button btnpas = new Button("fold");
        HBox pas = new HBox(50);
        pas.setAlignment(Pos.BOTTOM_RIGHT);
        pas.getChildren().add(btnpas);
        grid.add(pas, 2, 1);
        btnpas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                adapter.fold(playerId);
            }
        });
        Button btnRaise = new Button("Raise");
        HBox Raise = new HBox(50);
        Raise.setAlignment(Pos.BOTTOM_RIGHT);
        Raise.getChildren().add(btnRaise);
        grid.add(Raise, 1, 1);
        btnRaise.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                adapter.raise(playerId, userCashTextField.getText());
                userCashTextField.clear();
            }
        });
        Button btnCheck = new Button("Check");
        btnCheck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                adapter.check(playerId);
            }
        });
        HBox Check = new HBox(50);
        Check.setAlignment(Pos.BOTTOM_RIGHT);
        Check.getChildren().add(btnCheck);
        grid.add(Check, 3, 1);
        Scene scene = new Scene(grid, 700, 420);
        //	grid.setGridLinesVisible(true);
        primaryStage.setScene(scene);
        cssPath = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(cssPath);
        primaryStage.show();

    }
    @Override
    public void sendMessage(String text) {
        System.out.println(text);
    }
}
package main.java.View;

import main.java.Adapter.MainAdapter;
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

public class TableView implements ViewInterface{
	private MainAdapter adapter;
	private int playerId;
	
	public TableView(MainAdapter a, int p){
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
		Button btnpas = new Button("pas");
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
		    	
		    }
		});
		Button btnCheck = new Button("Check");
		HBox Check = new HBox(50);
		Check.setAlignment(Pos.BOTTOM_RIGHT);
		Check.getChildren().add(btnCheck);
		grid.add(Check, 3, 1);
		TextField userCashTextField = new TextField();
	    grid.add(userCashTextField, 0, 1);
		Scene scene = new Scene(grid, 800, 600);
	//	grid.setGridLinesVisible(true);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
    @Override
    public void sendMessage(String text) {
    }

}

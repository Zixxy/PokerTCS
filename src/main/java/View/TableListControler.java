package main.java.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import main.java.Adapter.MainAdapter;
import main.java.Main.Run;
import main.java.Model.Deck.Card;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class TableListControler implements TableViewInterface{
	volatile private boolean isConstructed = false;
	private MainAdapter adapter;
	private ExecutorService tasksExecutor = Executors.newSingleThreadExecutor();
	public static TableListControler recentlyCreatedTableList;
	
	public TableListControler(){
		recentlyCreatedTableList = this;
		adapter = Run.adapter;
	}
	
	public void initialize(){
		tableNames = new Label[] {table1Started, table2Started, table3Started, table4Started, table5Started, table6Started,
    			table7Started, table8Started, table9Started, table10Started, table11Started, table12Started,
    			table13Started, table14Started, table15Started, table16Started, table17Started, table18Started,
    			table19Started, table20Started, table21Started, table22Started, table23Started};
		for(Label l : tableNames){
			l.setText(null);
		}
		tablePlayers = new Label[]{table1Players, table2Players, table3Players, table4Players, table5Players, table6Players,
    			table7Players, table8Players, table9Players, table10Players, table11Players, table12Players,
    			table13Players, table14Players, table15Players, table16Players, table17Players, table18Players,
    			table19Players, table20Players, table21Players, table22Players, table23Players};
		for(Label l : tablePlayers){
			l.setText(null);
		}
		tableStarteds = new Label[] {table1Started, table2Started, table3Started, table4Started, table5Started, table6Started,
    			table7Started, table8Started, table9Started, table10Started, table11Started, table12Started,
    			table13Started, table14Started, table15Started, table16Started, table17Started, table18Started,
    			table19Started, table20Started, table21Started, table22Started, table23Started};
		for(Label l : tableStarteds){
			l.setText(null);
		}
		tableJoins = new HBox[] {table1Join, table2Join, table3Join, table4Join, table5Join, table6Join, table7Join, table8Join,
    			table9Join, table10Join, table11Join, table12Join, table13Join, table14Join, table15Join, table16Join,
    			table17Join, table18Join, table19Join, table20Join, table21Join, table22Join, table23Join};
		for(HBox l : tableJoins){
			l.getChildren().clear();
		}
		isConstructed = true;
	}

	@Override
	public void guiAddTable(final int numberOfTable) {
		javafx.application.Platform.runLater(new Runnable() {

			@Override
			public void run() {
				tableNames[numberOfTable] = new Label("Table "+ numberOfTable);
				tablePlayers[numberOfTable] = new Label("1/" + "8");
				tableStarteds[numberOfTable] = new Label("No");
				Button b = new Button("Join");
				b.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						Thread a = new Thread(new Runnable(){
							@Override
							public void run(){
								adapter.addPlayerToTable(numberOfTable);
							}
						});
						a.start();
					}
				});
				tableJoins[numberOfTable].getChildren().clear();
				tableJoins[numberOfTable].getChildren().add(b);
			}
		});
	}

	@Override
	public void guiRemoveTable(final int numberOfTable) {
		javafx.application.Platform.runLater(new Runnable() {

			@Override
			public void run() {
				tableNames[numberOfTable].setText(null);
				tablePlayers[numberOfTable].setText(null);
				tableStarteds[numberOfTable].setText(null);
				tableJoins[numberOfTable].getChildren().clear();
			}
		});
	}

	@Override
	public void updateNumberOfPlayers(final int numberOfTable,
			final int currentNumberOfPlayers) {
		javafx.application.Platform.runLater(new Runnable() {

			@Override
			public void run() {
				tablePlayers[numberOfTable].setText(currentNumberOfPlayers + "/8");
			}
		});
	}
	
	@FXML
	private Button exit;
	
    @FXML
    private Label table1Started, table2Started, table3Started, table4Started, table5Started, table6Started,
    			table7Started, table8Started, table9Started, table10Started, table11Started, table12Started,
    			table13Started, table14Started, table15Started, table16Started, table17Started, table18Started,
    			table19Started, table20Started, table21Started, table22Started, table23Started;
    private Label [] tableStarteds;

    @FXML
    private HBox table1Join, table2Join, table3Join, table4Join, table5Join, table6Join, table7Join, table8Join,
    			table9Join, table10Join, table11Join, table12Join, table13Join, table14Join, table15Join, table16Join,
    			table17Join, table18Join, table19Join, table20Join, table21Join, table22Join, table23Join;
    private HBox [] tableJoins;

    @FXML
    private Label table1Players, table2Players, table3Players, table4Players, table5Players, table6Players,
    			table7Players, table8Players, table9Players, table10Players, table11Players, table12Players,
    			table13Players, table14Players, table15Players, table16Players, table17Players, table18Players,
    			table19Players, table20Players, table21Players, table22Players, table23Players;
    private Label [] tablePlayers;
    @FXML
    private Label table1Name, table2Name, table3Name, table4Name, table5Name, table6Name, table7Name,
    			table8Name, table9Name, table10Name, table11Name, table12Name, table13Name, table14Name,
    			table15Name, table16Name,table17Name, table18Name, table19Name, table20Name, table21Name,
    			table22Name, table23Name;
    private Label [] tableNames;
    @FXML
    public void exitEvent(ActionEvent e){
    	tasksExecutor.execute(new Runnable() {
    		@Override
    		public void run(){
    		}
    	});
    }

    
    @FXML
    public void addEvent(ActionEvent e){
    	tasksExecutor.execute(new Runnable() {
    		@Override
    		public void run(){
    			Run.mainWindow.showGame(0);
    			adapter.addTable();
    		}
    	});
    }

	@Override
	public void almostConstructor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPlayer(String name, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePlayer(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePlayerCash(int id, int cash) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateActualPlayer(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNormalPlayer(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateResignedPlayer(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addThreeCardsOnTable(Card firstCard, Card secondCard,
			Card thirdCard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addOneCard(Card card) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePlayerHand(int id, Card firstCard, Card secondCard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePlayerLinedCash(int id, int cash) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePlayersLinedCash(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMessage(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startNewRound() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPot(int cash) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLastMove(int id, int move) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showCards(int playerId, int firstCardNumber,
			int secondCardNumber) {
		// TODO Auto-generated method stub
		
	}

}

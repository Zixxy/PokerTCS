package View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import Adapter.MainAdapter;
import Main.Run;
import Model.Deck.Card;

public class TableListControler implements ViewInterface{
	@SuppressWarnings("unused")
	volatile private boolean isConstructed = false;
	private MainAdapter adapter;
	private ExecutorService tasksExecutor = Executors.newSingleThreadExecutor();
	public static TableListControler recentlyCreatedTableList;
	@SuppressWarnings("unused")
	private volatile int playerId;
	private volatile String myName;
	private String startedString;
	
	public TableListControler(){
		myName = MainWindow.myName;
		recentlyCreatedTableList = this;
		adapter = Run.adapter;
	}
	
	public void initialize(){
		tableNames = new Label[] {table1Name, table2Name, table3Name, table4Name, table5Name, table6Name,
                table7Name, table8Name, table9Name, table10Name, table11Name, table12Name,
                table13Name, table14Name, table15Name, table16Name, table17Name, table18Name,
                table19Name, table20Name, table21Name, table22Name, table23Name};
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
	public void setPlayerId(int id) {
		playerId = id;
	}

    @Override
    public void guiExitTable() {

    }

    @Override
	public void guiAddTable(final int numberOfTable,final String started) {
    	startedString=started;
        System.out.println("Uwaga - bede dodawac stol");
		final ViewInterface actual = this;
		javafx.application.Platform.runLater(new Runnable() {

			@Override
			public void run() {
				tableNames[numberOfTable].setText("Table "+(numberOfTable+1));
				tablePlayers[numberOfTable].setText("1/" + "8");
                if("true".equals(started)) {
                    tableStarteds[numberOfTable].setText("Yes");
                    tableJoins[numberOfTable].getChildren().clear();
                }
                else if(started.equals("false")) {
                    tableStarteds[numberOfTable].setText("No");
                    Button b = new Button("Join");
                    b.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {


                            Thread showGame = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Run.mainWindow.showGame(0, myName);// we are waiting for scheduling playerId-s
                                    adapter.addPlayerToTable(numberOfTable);
                                }
                            });
                            showGame.start();

                        }
                    });
                    tableJoins[numberOfTable].getChildren().clear();
                    tableJoins[numberOfTable].getChildren().add(b);
                }
			}
		});
	}
	

	@Override
	public void guiClearTableList() {
		javafx.application.Platform.runLater(new Runnable(){
			
			@Override
			public void run(){
				for(int i=0;i<23;++i){
					tableNames[i].setText(null);
					tablePlayers[i].setText(null);
					tableStarteds[i].setText(null);
					tableJoins[i].getChildren().clear();
				}		
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
                if(currentNumberOfPlayers >= 8 || startedString.equals("true")) {
                    tableJoins[numberOfTable].getChildren().clear();
                }
                if(currentNumberOfPlayers <8 && startedString.equals("false")){
                    Button b = new Button("Join");
                    b.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {


                            Thread showGame = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Run.mainWindow.showGame(0, myName);// we are waiting for scheduling playerId-s
                                    adapter.addPlayerToTable(numberOfTable);
                                }
                            });
                            showGame.start();

                        }
                    });
                    tableJoins[numberOfTable].getChildren().clear();
                    tableJoins[numberOfTable].getChildren().add(b);
                }
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
    			Run.mainWindow.showGame(0,myName);
    			adapter.addTable();
    		}
    	});
    }
    
    public void exitServer(ActionEvent actionEvent) {
        Run.mainWindow.showMainMenu();
    }

    @Override
    public void clearTableList() {
    	for(int i = 0; i<23; i++){
    	tableNames[i].setText(null);
    	tablePlayers[i].setText(null);
    	tableStarteds[i].setText(null);
    	tableJoins[i].getChildren().clear();
    	}
    }
    @Override
	public void almostConstructor() {
		// UNUSED
		
	}

	@Override
	public void addPlayer(String name, int id, int image) {
        // UNUSED
		
	}

	@Override
	public void removePlayer(int id) {
        // UNUSED
		
	}

	@Override
	public void updatePlayerCash(int id, int cash) {
        // UNUSED
		
	}

	@Override
	public void updateActualPlayer(int id) {
        // UNUSED
		
	}

	@Override
	public void updateNormalPlayer(int id) {
// UNUSED
		
	}

	@Override
	public void updateResignedPlayer(int id) {
        // UNUSED
	}

	@Override
	public void addThreeCardsOnTable(Card firstCard, Card secondCard,
			Card thirdCard) {
        // UNUSED
		
	}

	@Override
	public void addOneCard(Card card) {
        // UNUSED
		
	}

	@Override
	public void clearTable() {
        // UNUSED
		
	}

	@Override
	public void updatePlayerHand(int id, Card firstCard, Card secondCard) {
        // UNUSED
		
	}

	@Override
	public void updatePlayerLinedCash(int id, int cash) {
        // UNUSED
		
	}

	@Override
	public void removePlayersLinedCash(int id) {
        // UNUSED
		
	}

	@Override
	public void sendMessage(String text) {
        // UNUSED
		
	}

	@Override
	public void startNewRound() {
        // UNUSED
		
	}

	@Override
	public void setPot(int cash) {
        // UNUSED
		
	}

	@Override
	public void setLastMove(int id, int move) {
        // UNUSED
		
	}

	@Override
	public void showCards(int playerId, int firstCardNumber,
			int secondCardNumber) {
        // UNUSED
		
	}
}
package main.java.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import main.java.Adapter.MainAdapter;
import main.java.Main.Run;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class TableListControler implements TableListInterface{
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
			l = null;
		}
		tablePlayers = new Label[]{table1Players, table2Players, table3Players, table4Players, table5Players, table6Players,
    			table7Players, table8Players, table9Players, table10Players, table11Players, table12Players,
    			table13Players, table14Players, table15Players, table16Players, table17Players, table18Players,
    			table19Players, table20Players, table21Players, table22Players, table23Players};
		for(Label l : tablePlayers){
			l = null;
		}
		tableStarteds = new Label[] {table1Started, table2Started, table3Started, table4Started, table5Started, table6Started,
    			table7Started, table8Started, table9Started, table10Started, table11Started, table12Started,
    			table13Started, table14Started, table15Started, table16Started, table17Started, table18Started,
    			table19Started, table20Started, table21Started, table22Started, table23Started};
		for(Label l : tableStarteds){
			l = null;
		}
		tableJoins = new Button[] {table1Join, table2Join, table3Join, table4Join, table5Join, table6Join, table7Join, table8Join,
    			table9Join, table10Join, table11Join, table12Join, table13Join, table14Join, table15Join, table16Join,
    			table17Join, table18Join, table19Join, table20Join, table21Join, table22Join, table23Join};
		for(Button l : tableJoins){
			l = null;
		}
		isConstructed = true;
	}
	
	@Override
	public void guiAddTable(final int numberOfTable) {
		tableNames[numberOfTable] = new Label("Table "+ numberOfTable);
		tablePlayers[numberOfTable] = new Label("1/" + "8");
		tableStarteds[numberOfTable] = new Label("No");
		tableJoins[numberOfTable] = new Button("Join");
	 	tableJoins[numberOfTable].setOnAction(new EventHandler<ActionEvent>() {
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

	}

	@Override
	public void guiRemoveTable(int numberOfTable) {
		tableNames[numberOfTable] = null;
		tablePlayers[numberOfTable] = null;
		tableStarteds[numberOfTable] = null;
		tableJoins[numberOfTable] = null;

	}

	@Override
	public void updateNumberOfPlayers(int numberOfTable,
			int currentNumberOfPlayers) {
		tablePlayers[numberOfTable].setText(currentNumberOfPlayers + "/8");
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
    private Button table1Join, table2Join, table3Join, table4Join, table5Join, table6Join, table7Join, table8Join,
    			table9Join, table10Join, table11Join, table12Join, table13Join, table14Join, table15Join, table16Join,
    			table17Join, table18Join, table19Join, table20Join, table21Join, table22Join, table23Join;
    private Button [] tableJoins;

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
    			adapter.addTable();
    		}
    	});
    }

}

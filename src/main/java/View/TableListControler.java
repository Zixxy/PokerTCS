package main.java.View;

<<<<<<< HEAD
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TableListControler {

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
    private Label [] tableJoins;

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
    

}

=======
public class TableListControler implements TableListInterface {

	public static TableListControler recentlyCreatedTableList;
	
	TableListControler(){
		recentlyCreatedTableList = this;
	}
	@Override
	public void guiAddTable(int numberOfTable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void guiRemoveTable(int numberOfTable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNumberOfPlayers(int numberOfTable,
			int currentNumberOfPlayers) {
		// TODO Auto-generated method stub

	}

}
>>>>>>> e7a4f71866b9276385c93dc57130213a84fc9f63

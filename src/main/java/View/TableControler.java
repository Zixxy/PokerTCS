package main.java.View;

import java.net.URL;
import java.util.ResourceBundle;
import main.java.Adapter.MainAdapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TableControler{
	private MainAdapter adapter;
	private int playerId;
	TableControler(){
		adapter = TableView.tempAdapter;
		playerId = TableView.tempPlayerId;
	}
    @FXML
    private GridPane playerOneGrid;
	@FXML // fx:id="userCashTextField"
    private TextField userCashTextField; // Value injected by FXMLLoader

    @FXML // fx:id="btnCheck"
    private Button btnCheck; // Value injected by FXMLLoader

    @FXML // fx:id="btnFold"
    private Button btnFold; // Value injected by FXMLLoader

    @FXML // fx:id="btnRaise"
    private Button btnRaise; // Value injected by FXMLLoader
    
    @FXML // fx:id="firstPlayerCard"
    private Pane firstPlayerCard; // Value injected by FXMLLoader

    @FXML // fx:id="thirdCardOnTable"
    private Pane thirdCardOnTable; // Value injected by FXMLLoader

    @FXML // fx:id="secondCardOnTable"
    private Pane secondCardOnTable; // Value injected by FXMLLoader

    @FXML // fx:id="firstCardOnTable"
    private Pane firstCardOnTable; // Value injected by FXMLLoader

    @FXML // fx:id="secondPlayerCard"
    private Pane secondPlayerCard; // Value injected by FXMLLoader

    @FXML // fx:id="fourthCardOnTable"
    private Pane fourthCardOnTable; // Value injected by FXMLLoader

    @FXML // fx:id="FifthCardOnTable"
    private Pane FifthCardOnTable; // Value injected by FXMLLoader
    
    @FXML
    public void checkEvent(ActionEvent e){
    	adapter.check(playerId);
    }
    
    @FXML
    public void foldEvent(ActionEvent e){
        adapter.fold(playerId);
    }
    
    @FXML
    public void raiseEvent(ActionEvent e){
        adapter.raise(playerId, userCashTextField.getText());
        userCashTextField.clear();
    }
    
}

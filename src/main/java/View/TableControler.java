package main.java.View;

import main.java.Adapter.MainAdapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TableControler{
	private MainAdapter adapter;
	private int playerId;
	public TableControler(){
		adapter = TableView.tempAdapter;
	}

    @FXML
    private TextField userCashTextField;
    
    @FXML
    private Button btnCheck;
    
    @FXML
    private Button btnRaise;
    
    @FXML
    private Button btnFold;
    
	@FXML
    private ImageView firstPlayerCard;
	
    @FXML
    private ImageView secondPlayerCard;

    @FXML
    private ImageView firstCardOnTable;
    
    @FXML
    private ImageView secondCardOnTable;

    @FXML
    private ImageView thirdCardOnTable;
    
    @FXML
    private ImageView fourthCardOnTable;
    
    @FXML
    private ImageView FifthCardOnTable;
    
    @FXML
    private HBox playerOneCashBox;

    @FXML
    private HBox playerTwoCashBox;
    
    @FXML
    private HBox playerThreeCashBox;
    
    @FXML
    private HBox playerFourCashBox;
    
    @FXML
    private HBox playerFiveCashBox;
    
    @FXML
    private HBox playerSixCashBox;
    
    @FXML
    private HBox playerSevenCashBox;

    @FXML
    private HBox playerEightCashBox;

    @FXML
    private HBox playerOneNameBox;

    @FXML
    private HBox playerTwoNameBox;
    
    @FXML
    private HBox playerThreeNameBox;

    @FXML
    private HBox playerFourNameBox;
    
    @FXML
    private HBox playerFiveNameBox;
    
    @FXML
    private HBox playerSixNameBox;
    
    @FXML
    private HBox playerSevenNameBox;
    
    @FXML
    private HBox playerEightNameBox;
    

    
    @FXML
    public void checkEvent(ActionEvent e){
    	adapter.check(playerId);
    }
    
    @FXML
    public void foldEvent(ActionEvent e){
    	Text text = new Text("Neon Sign");
    	playerOneNameBox.getChildren().add(text);
    	text.setTextAlignment(TextAlignment.CENTER);
        adapter.fold(playerId);
    }
    
    @FXML
    public void raiseEvent(ActionEvent e){
        adapter.raise(playerId, userCashTextField.getText());
        userCashTextField.clear();
    }
    
    public void addOneCard(Integer Card){
    }
	
}

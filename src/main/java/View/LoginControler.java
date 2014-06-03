package View;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import Main.RunHereClient;
import Main.RunHereServer;

public class LoginControler {

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField ipTextField;

    @FXML
    private TextField portNumberTextField;

    @FXML
    private TextField serverPortNumberTextField;
    

    @FXML
    private Button exitButton;

    @FXML
    private Button joinButton;

    @FXML
    private Button newHostButton;
    
    @FXML
    private ImageView firstImage, secondImage, thirdImage, fourthImage, fifthImage, sixthImage;
    private ImageView[] userImage;
    private int pickedImage;
    
    @FXML
    private RadioButton firstRadioButton, secondRadioButton, thirdRadioButton, fourthRadioButton, 
                        fifthRadioButton, sixthRadioButton;
    private RadioButton[] radioButtons;
    private ToggleGroup groupRadioButtons;

    @FXML
    private void initialize(){
    	userImage = new ImageView[]{firstImage, secondImage, thirdImage, fourthImage, fifthImage, sixthImage};
    	radioButtons = new RadioButton[]{firstRadioButton, secondRadioButton, thirdRadioButton, fourthRadioButton, 
    			fifthRadioButton, sixthRadioButton};
    	groupRadioButtons = new ToggleGroup();
    	for(RadioButton i : radioButtons)
    		i.setToggleGroup(groupRadioButtons);
    	groupRadioButtons.selectedToggleProperty().addListener(
    			new ChangeListener<Toggle>(){
    				public void changed(ObservableValue<? extends Toggle> ov,
    						Toggle old_toggle, Toggle new_toggle) {
    					if (groupRadioButtons.getSelectedToggle() != null) {
    						for(int i = 0; i<6; i++)
    							if(radioButtons[i] == groupRadioButtons.getSelectedToggle())
    								pickedImage = i;
    					}
    				}
    			});
    }
    
    private void realJoin(){
    	final String ip = ipTextField.getText();
    	final String port = portNumberTextField.getText();
    	String name = "defaultName";
    	String name1 = userNameTextField.getText();
    	if(name1.length() > 1)
    		name = name1;
    	final String sendName = name;
        final int sendImage = pickedImage;
    	userNameTextField.clear();
    	ipTextField.clear();
    	portNumberTextField.clear();
    	Thread thread = new Thread(){
    		@Override
    		public void run(){
                System.err.println("sendname: " + sendName);
    			RunHereClient.runClient(ip, Integer.parseInt(port), sendName, sendImage);
    		}
    	};
    	thread.start();
    }
    
    private void makeHost(){
    	final String port = serverPortNumberTextField.getText();
    	String name = "defaultName";
    	String name1 = userNameTextField.getText();
    	if(name1.length() > 1)
    		name = name1;
    	final String res = name;
        // pickedImage should be used now.
    	userNameTextField.clear();
    	serverPortNumberTextField.clear();
    	Thread thread = new Thread(){
			@Override
			public void run(){
				RunHereServer.runServer(Integer.parseInt(port), res,pickedImage);
			}
    	};
    	thread.start();
    }
    @FXML
    public void portForHostTextField(ActionEvent e){
    	makeHost();
    }
    @FXML
    public void nameTyping(ActionEvent e){
    	realJoin();
    }
    @FXML
    public void ipTyping(ActionEvent e){
    	realJoin();
    }
    @FXML
    public void portTyping(ActionEvent e){
    	realJoin();
    }
    @FXML
    void join(ActionEvent event) {
    	realJoin();
    }

    @FXML
    void exit(ActionEvent event) {
    	Platform.exit();
    	System.exit(0);
    }

    @FXML
    void makeNewHost(ActionEvent event) {
    	makeHost();
    }
    
}
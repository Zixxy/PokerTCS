package main.java.View;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
 * im too hungry to finnish it now :]
 */

public class MainWindow extends Application implements MainViewInterface {

	private Stage mainStage;
	private Scene actualScene;
	private AnchorPane mainPane;
	private static final String sync = "synchronizer";
	
	public static MainWindow recentlyCreatedMainWindow;
	
	public MainWindow(){
		System.out.println("im here");
		recentlyCreatedMainWindow = this;
		synchronized(sync){
			sync.notifyAll();
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		mainStage = primaryStage;
		mainPane = new AnchorPane();
		mainStage.setScene(actualScene = new Scene(mainPane));
		mainStage.setTitle("TCS-Pocker-Club");
		mainStage.show();
	}
	
	public static MainViewInterface createMainView(final String[] args) {
		Thread mainViewThread = new Thread(){
			public void run(){
				Application.launch(MainWindow.class, args);
			}
		};
		mainViewThread.start();
		synchronized(sync){
			try {
			sync.wait();
			} catch (InterruptedException e) {
				System.err.println("MainViewInterface synchronization warning!");
				while(recentlyCreatedMainWindow == null)
					Thread.yield();
				System.err.println("ignore ''MainViewInterface synchronization warning!'' ");
			}
		}
		while(recentlyCreatedMainWindow.mainStage == null)
			Thread.yield();
		return recentlyCreatedMainWindow;
	}
	@Override
	public TableViewInterface showTable() {
		javafx.application.Platform.runLater(new Runnable() {
			@Override
			public void run() {
				mainStage.setHeight(620);
				mainStage.setWidth(960);
				mainPane.getChildren().clear();
				try {
					mainPane.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/main/java/FXML/TableView.fxml")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return null;
	}
	
	@Override
	public MainMenu setMainMenu() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * i need this for debuging MainWindow.
	 */
	@Deprecated
	public static void main(String[] args){

		System.out.println("im here");
		MainWindow.createMainView(args).showTable();
	}
}

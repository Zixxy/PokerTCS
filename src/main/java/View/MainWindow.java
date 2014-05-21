package main.java.View;

import java.io.IOException;

import main.java.Adapter.MainAdapter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
 * i'm too hungry to finnish it now :]
 */

public class MainWindow extends Application implements MainViewInterface {

	private Stage mainStage;
	private Scene actualScene;
	private AnchorPane mainPane;
	
	private final MainAdapter adapter;
	private int playerId;
	public static MainAdapter tempMainAdapter;
	public static MainWindow recentlyCreatedMainWindow;
	
	private static final String sync = "synchronizer";
	
    public static final String TableControlerSynchronizer = "We synchronize on this object during creating instance of TableControler";
	
	public MainWindow(){
		adapter = tempMainAdapter;
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
	
	public static MainViewInterface createMainView(MainAdapter adapt, final String[] args) {
		tempMainAdapter = adapt;
		Thread mainViewThread = new Thread(){
			@Override
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
		TableViewInterface fullyCreatedTableViewInterface = TableView.getTableView(adapter, playerId);
		javafx.application.Platform.runLater(new Runnable() {
			@Override
			public void run() {
				mainStage.setResizable(true);
				mainStage.setHeight(620);
				mainStage.setWidth(960);
				mainPane.getChildren().clear();
				try {
					mainPane.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/main/java/FXML/TableView.fxml")));
				} catch (IOException e) {
					System.err.println("We got a problem with launching TableView");
					e.printStackTrace();
				}
			}
		});
		synchronized(TableControlerSynchronizer){
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println("TabloControlerSynchronizer launching warning!");
				while(TableView.RecentlyCreatedInstanceOfTableControler == null || 
						!TableView.RecentlyCreatedInstanceOfTableControler.isConstructed())
					Thread.yield();
				System.err.println("ignore ''TabloControlerSynchronizer launching warning!'' ");
			} catch (Exception e){
				System.err.println("TabloControlerSynchronizer launching warning!");
				while(TableView.RecentlyCreatedInstanceOfTableControler == null || 
						!TableView.RecentlyCreatedInstanceOfTableControler.isConstructed())
					try {
						Thread.sleep(20);
					} catch (InterruptedException e1) {
						System.err.println("We're old so we got problem with sleeping :( ");
						e1.printStackTrace();
					}
				System.err.println("ignore ''TabloControlerSynchronizer launching warning!'' ");
			}
		}
		javafx.application.Platform.runLater(new Runnable() {
			@Override
			public void run() {
				mainStage.setResizable(false);
			}
		});
		return fullyCreatedTableViewInterface;
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
		MainWindow.createMainView(new MainAdapter(), args).showTable();
	}
}

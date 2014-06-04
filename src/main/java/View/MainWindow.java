package View;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import Adapter.MainAdapter;

/*
 * Created by sylwek 21.05.14
 */

public class MainWindow extends Application implements MainWindowInterface {

	private Stage mainStage;
	private Scene actualScene;
	private AnchorPane mainPane;
	private Node tableListObject;
	private Node lobbyObject;
	private Node tableViewObject;
	private final MainAdapter adapter;
	public static MainAdapter tempMainAdapter;
	public static MainWindow recentlyCreatedMainWindow;
	public static String myName;
	
	private static final String sync = "synchronizer";

	public static final String TableControlerSynchronizer = "We synchronize on this object during creating instance of TableControler";

	private static ViewInterface fullyCreatedTableViewInterface;
	private static volatile boolean tableViewInterfaceConstructionFlag;

	public MainWindow(){
		tableListObject = null;
		lobbyObject = null;
		tableViewObject = null;
		adapter = tempMainAdapter;
		System.out.println(adapter);
		recentlyCreatedMainWindow = this;
		synchronized(sync){
			sync.notifyAll();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		mainStage = primaryStage;
		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	        public void handle(WindowEvent e){
	        	// we need to send information about leaving player.
	        	System.out.println("We are closing game!");
	        	System.exit(0);
	        }
	    });
		mainPane = new AnchorPane();
		mainStage.setScene(actualScene = new Scene(mainPane));
		mainStage.setTitle("TCS-Pocker-Club");
		mainStage.show();
	}

	public static MainWindowInterface createMainView(MainAdapter adapt, final String[] args) {
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
	public ViewInterface showGame(int playerId, String name) {
		fullyCreatedTableViewInterface = TableView.getTableView(adapter, playerId, name);
		javafx.application.Platform.runLater(new Runnable() {
			@Override
			public void run() {
				mainStage.setResizable(true);
				mainStage.setHeight(748);
				mainStage.setWidth(1152);
				mainPane.getChildren().clear();
				try {
					if(tableViewObject == null)
						tableViewObject = (Node) FXMLLoader.load(getClass().getResource("/FXML/TableView.fxml"));
					mainPane.getChildren().add(tableViewObject);
				} catch (IOException e) {
					throw new RuntimeException("We got a problem with launching TableView", e);
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
						Thread.sleep(10);
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
		fullyCreatedTableViewInterface.almostConstructor();
		adapter.exchangeReference((ViewInterface)TableListControler.recentlyCreatedTableList, TableView.RecentlyCreatedInstanceOfThis);
		return fullyCreatedTableViewInterface;
	}
	
	@Override
	public void showMainMenu() {
		javafx.application.Platform.runLater(new Runnable() {
			@Override
			public void run() {
                adapter.removeAllViews();
				mainStage.setResizable(true);
				mainStage.setHeight(560);
				mainStage.setWidth(750);
				mainPane.getChildren().clear();
				try {
					if(lobbyObject == null)
						lobbyObject = (Node) FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
					mainPane.getChildren().add(lobbyObject);
				} catch (IOException e) {
					throw new RuntimeException("We got a problem with launching LoginControler", e);
				} catch (Exception a) {
					throw new RuntimeException("We got a huge problem with launching LoginControler", a);
				}
			}
		});
	}

	@Override
	public ViewInterface showTableList(String name) {
		myName = name;
		javafx.application.Platform.runLater(new Runnable() {
			@Override
			public void run() {
				mainStage.setResizable(true);
				mainStage.setHeight(680);
				mainStage.setWidth(602);
				mainPane.getChildren().clear();
				try {
					if(tableListObject == null){
						tableListObject = (Node) FXMLLoader.load(getClass().getResource("/FXML/TableList.fxml"));
					}
					mainPane.getChildren().add(tableListObject);
				} catch (IOException e) {
					throw new RuntimeException("We got a problem with launching TableList ", e);
				} catch (Exception a) {
					throw new RuntimeException("We got a huge problem with launching TableList", a);
				}
			}
		});
		// IT WILL BE REMOVED AS SOON AS POSSIBLE.
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		// we need to wait until this will not be null.
		return (ViewInterface) TableListControler.recentlyCreatedTableList;
	}
}

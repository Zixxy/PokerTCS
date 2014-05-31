package View;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Adapter.MainAdapter;

/*
 * Created by sylwek 21.05.14
 */

public class MainWindow extends Application implements MainWindowInterface {

	private Stage mainStage;
	private Scene actualScene;
	private AnchorPane mainPane;


	private final MainAdapter adapter;
	public static MainAdapter tempMainAdapter;
	public static MainWindow recentlyCreatedMainWindow;

	private static final String sync = "synchronizer";

	public static final String TableControlerSynchronizer = "We synchronize on this object during creating instance of TableControler";

	private static ViewInterface fullyCreatedTableViewInterface;
	private static volatile boolean tableViewInterfaceConstructionFlag;

	public MainWindow(){
		adapter = tempMainAdapter;
		System.out.println(adapter);
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
	public ViewInterface showGame(int playerId) {
		fullyCreatedTableViewInterface = TableView.getTableView(adapter, playerId);
		javafx.application.Platform.runLater(new Runnable() {
			@Override
			public void run() {
				mainStage.setResizable(true);
				mainStage.setHeight(748);
				mainStage.setWidth(1152);
				mainPane.getChildren().clear();
				try {
					mainPane.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/main/java/FXML/TableView.fxml")));
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
				mainStage.setResizable(true);
				mainStage.setHeight(560);
				mainStage.setWidth(750);
				mainPane.getChildren().clear();
				try {
					mainPane.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/main/java/FXML/Login.fxml")));
				} catch (IOException e) {
					throw new RuntimeException("We got a problem with launching LoginControler", e);
				} catch (Exception a) {
					throw new RuntimeException("We got a huge problem with launching LoginControler", a);
				}
			}
		});
	}

	@Override
	public ViewInterface showTableList() {
		javafx.application.Platform.runLater(new Runnable() {
			@Override
			public void run() {
				mainStage.setResizable(true);
				mainStage.setHeight(680);
				mainStage.setWidth(602);
				mainPane.getChildren().clear();
				try {
					mainPane.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/main/java/FXML/TableList.fxml")));// or sth...
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

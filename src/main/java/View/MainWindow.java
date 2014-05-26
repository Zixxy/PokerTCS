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
 * Created by sylwek 21.05.14
 * i'm too hungry to finish it now :]
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
	
    private static TableViewInterface fullyCreatedTableViewInterface;
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
	public TableViewInterface showGame(int playerId) {/*
		final String innerSynchronizer = "inner synchronizer";
		tableViewInterfaceConstructionFlag = false;
		Thread mainViewThread = new Thread(){
			@Override
			public void run(){*/

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
				/*synchronized(innerSynchronizer){
					innerSynchronizer.notifyAll();
				}
				tableViewInterfaceConstructionFlag = true;
			}
		};
		mainViewThread.start();
		synchronized(innerSynchronizer){
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println("We're old so we got problem with sleeping :( ");
				e.printStackTrace();
			}
		}
		while(!tableViewInterfaceConstructionFlag){
			Thread.yield(); // we cant move on.
		}*/
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
					System.err.println("We got a problem with launching LoginControler ");
					e.printStackTrace();
				} catch (Exception a) {
					System.err.println("We got a huge problem with launching LoginControler ");
				}
			}
		});
	}

	@Override
	public TableViewInterface showTableList() {
		javafx.application.Platform.runLater(new Runnable() {
			@Override
			public void run() {
			/*	mainStage.setResizable(true);
				mainStage.setHeight(560);
				mainStage.setWidth(750);
				mainPane.getChildren().clear();*/
				/*try {
					mainPane.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/main/java/FXML/TableList.fxml")));// or sth...
				} catch (IOException e) {
					System.err.println("We got a problem with launching TableList ");
					e.printStackTrace();
				} catch (Exception a) {
					System.err.println("We got a huge problem with launching TableList ");
				}*/
			}
		});
		// we need to wait until this will not be null.
		return (TableViewInterface) TableListControler.recentlyCreatedTableList;
	}
	/*
	 * i need this for debuging MainWindow.
	 */
	@Deprecated
	public static void main(String[] args){
		System.out.println("im here");
		MainWindowInterface a = MainWindow.createMainView(new MainAdapter(),args);
		a.showMainMenu();
		/*try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		a.showTable();*/
	}
}

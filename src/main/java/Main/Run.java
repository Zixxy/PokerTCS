package Main;

import Adapter.MainAdapter;
import View.MainWindow;
import View.MainWindowInterface;

public class Run {
	public static MainAdapter adapter;
	public static MainWindowInterface mainWindow;
	
	public static void main(String[] args){
		adapter = new MainAdapter();	
		mainWindow = MainWindow.createMainView(adapter, args);
		mainWindow.showMainMenu();
	}
}

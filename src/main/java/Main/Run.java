package main.java.Main;

import main.java.Adapter.MainAdapter;
import main.java.View.MainWindow;
import main.java.View.MainWindowInterface;

public class Run {
	public static MainAdapter adapter;
	public static MainWindowInterface mainWindow;
	
	public static void main(String[] args){
		adapter = new MainAdapter();	
		mainWindow = MainWindow.createMainView(adapter, args);
		mainWindow.showMainMenu();
	}
}

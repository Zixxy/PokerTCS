package Main;

import Adapter.MainAdapter;
import Communication.LostConnectionToServerException;
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

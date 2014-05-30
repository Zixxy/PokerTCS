package main.java.Main;

import java.io.IOException;
import java.util.Scanner;

import main.java.Adapter.MainAdapter;
import main.java.Communication.CommunicationView;
import main.java.Model.ModelOne;
import main.java.Server.Server;
import main.java.View.CommandLine;
import main.java.View.MainWindow;
import main.java.View.TableView;
import main.java.View.ViewInterface;

public class RunHereServer {
	private static Config config;

	public static void runServer(int port, String name){
		MainAdapter adapter = Run.adapter;
		Server server = new Server(port);
		Run.mainWindow.showTableList();
	}
}

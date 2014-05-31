package Main;

import Adapter.MainAdapter;
import Server.Server;

public class RunHereServer {
	private static Config config;

	public static void runServer(int port, String name){
		MainAdapter adapter = Run.adapter;
		Server server = new Server(port);
		Run.mainWindow.showTableList();
	}
}

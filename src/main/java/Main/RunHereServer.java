package Main;

import Adapter.MainAdapter;
import Server.Server;

public class RunHereServer {
	private static Config config;

	public static void runServer(int port, String name, int image){
		MainAdapter adapter = Run.adapter;
		Server server = new Server(port);
		//Run.mainWindow.showTableList(name);
        RunHereClient.runClient("localhost",port,name,image);
	}
}

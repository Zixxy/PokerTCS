package Main;

import java.io.IOException;

import Adapter.MainAdapter;
import Communication.CommunicationModel;
import Model.ModelInterface;
import View.CommandLine;
import View.TableView;
import View.ViewInterface;


/**
 * Created by bartek on 05.05.14.
 */
public class RunHereClient {
	private static Config config;
	public static void runClient(String ip, int port, String name, int image){
		MainAdapter adapter = Run.adapter;
		ModelInterface model = null;
		boolean connected = false;
		try {
			model = new CommunicationModel(adapter,ip,port);
			connected = true;
		} catch (IOException e) {
			throw new RuntimeException("Connection error "+ip+":"+port, e);
		}
		if(connected){
			ViewInterface view  = Run.mainWindow.showTableList(name);
			adapter.addView(view);
			ViewInterface textView = new CommandLine(adapter);
			adapter.addView(textView);
			adapter.addModel(model);
			model.setPlayerName(name);
			model.setPlayerImage(image);
		}
	}
}

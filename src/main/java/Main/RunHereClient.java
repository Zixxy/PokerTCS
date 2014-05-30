package main.java.Main;

import main.java.Adapter.MainAdapter;
import main.java.Communication.CommunicationModel;
import main.java.Model.ModelInterface;
import main.java.Model.ModelOne;
import main.java.View.CommandLine;
import main.java.View.*;
import main.java.View.ViewInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by bartek on 05.05.14.
 */
public class RunHereClient {
	private static Config config;
	public static void runClient(String ip, int port, String name){
		MainAdapter adapter = Run.adapter;
		ModelInterface model = null;
		boolean connected = false;
		try {
			model = new CommunicationModel(adapter,ip,port);
			connected = true;
		} catch (IOException e) {
			System.err.println("Connection error "+ip+":"+port);
			e.printStackTrace();
		}
		if(connected){
			ViewInterface view  = Run.mainWindow.showTableList();
			adapter.addView(view);
			ViewInterface textView = new CommandLine(adapter);
			adapter.addView(textView);
			adapter.addModel(model);
			model.setPlayerName(name);
		}
	}
    @Deprecated
    public static void main(String[] args){
        //config = new Config("config");
        //String ip="192.168.0.104";
        String ip = "127.0.0.1";
        int port=1228;
        MainAdapter adapter = new MainAdapter();
        ViewInterface view = TableView.createTableView(args, adapter, 1);
        //ViewInterface view = TableView.createTableView(args, adapter, config.getUserId());
        adapter.addView(view);
        ViewInterface textView = new CommandLine(adapter);
        adapter.addView(textView);
        ModelInterface model = null;
        try {
            model = new CommunicationModel(adapter,ip,port);
            //model = new CommunicationModel(adapter,config.getIP(),config.getPort());
        } catch (IOException e) {
            System.err.println("Super blad polaczenia na "+ip+":"+port);
            //System.err.println("Super blad polaczenia na "+config.getIP()+":"+config.getPort());
            e.printStackTrace();
        }
        adapter.addModel(model);
    }
}

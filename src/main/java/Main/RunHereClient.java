package main.java.Main;

import main.java.Adapter.MainAdapter;
import main.java.Communication.CommunicationModel;
import main.java.Model.ModelInterface;
import main.java.Model.ModelOne;
import main.java.View.CommandLine;
import main.java.View.*;
import main.java.View.TableViewInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
* Created by bartek on 05.05.14.
*/
public class RunHereClient {
    private static Config config;
    public static void runClient(String ip, int port){
    	MainAdapter adapter = Run.adapter;
    	TableViewInterface view  = Run.mainWindow.showTable();
    	adapter.addView(view);
    	TableViewInterface textView = new CommandLine(adapter);
        adapter.addView(textView);
        ModelInterface model = null;
        try {
            model = new CommunicationModel(adapter,ip,port);
        } catch (IOException e) {
            System.err.println("Super blad polaczenia na "+ip+":"+port);
            e.printStackTrace();
        }
        adapter.addModel(model);
    }
    public static void main(String[] args){
        //config = new Config("config");
        String ip="192.168.0.104";
        int port=1229;
        MainAdapter adapter = new MainAdapter();
        TableViewInterface view = TableView.createTableView(args, adapter, 1);
        //ViewInterface view = TableView.createTableView(args, adapter, config.getUserId());
        adapter.addView(view);
        TableViewInterface textView = new CommandLine(adapter);
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

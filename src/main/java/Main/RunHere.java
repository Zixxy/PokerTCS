package main.java.Main;

import java.io.IOException;

import main.java.Adapter.MainAdapter;
import main.java.Communication.CommunicationModel;
import main.java.Communication.CommunicationView;
import main.java.Model.ModelOne;
import main.java.View.CommandLine;
import main.java.View.TableView;
import main.java.View.ViewInterface;


/**
* Created by bartek on 05.05.14.
*/


public class RunHere {
   // private static Config config;
    public static void main(String[] args){
       // config = new Config("config");
        MainAdapter adapter = new MainAdapter();
        ViewInterface view = TableView.createTableView(args, adapter, 0);
 
        adapter.addView(view);
 
        CommunicationModel model = null;
		try {
			model = new CommunicationModel(adapter, "192.168.0.104", 12242);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        adapter.addModel(model);
    }
}


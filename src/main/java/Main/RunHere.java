package main.java.Main;

import main.java.Adapter.MainAdapter;
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
public class RunHere {
    private static Config config;
    public static void main(String[] args){
        config = new Config("config");
        MainAdapter adapter = new MainAdapter();
        ViewInterface view = TableView.createTableView(args, adapter, 0);
        ViewInterface textView = new CommandLine(adapter);

        adapter.addView(view);
        adapter.addView(textView);

        ModelOne model = new ModelOne(adapter);
        adapter.addModel(model);
        model.setAnte(10);
        model.setStartedAmount(2000);
        model.setSmallBlind(10);
        model.setBigBlind(20);
        model.addPlayer("Sylwek");
        model.addPlayer("Maciek");
        model.addPlayer("Bartek");
        model.addPlayer("Szymon");
        model.start();

    }
}
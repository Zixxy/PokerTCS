package main.java.Main;

import main.java.Adapter.MainAdapter;
import main.java.Model.ModelOne;
import main.java.View.CommandLine;
import main.java.View.*;
import main.java.View.ViewInterface;


/**
* Created by bartek on 05.05.14.
*/
public class RunHere {
    public static void main(String[] args){
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
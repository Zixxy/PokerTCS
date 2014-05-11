package main.java.Main;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.*;
import main.java.Adapter.MainAdapter;
import main.java.Model.ModelOne;
import main.java.View.TableView;
import main.java.View.ViewInterface;


/**
 * Created by bartek on 05.05.14.
 */
public class RunHere {
    public static void main(String[] args){
        MainAdapter adapter = new MainAdapter();
        ViewInterface view = TableView.createTableView(args, adapter, 0);
        
        adapter.addView(view);
        ModelOne model = new ModelOne(adapter);
        adapter.addModel(model);
        model.addPlayer("Sylwek");
        model.addPlayer("Maciek");
        model.addPlayer("Bartek");
        model.addPlayer("Szymon");
        model.start();
    }
}
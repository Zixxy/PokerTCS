package main.java.Main;
import javafx.stage.Stage;
import main.java.Adapter.MainAdapter;
import main.java.Model.ModelOne;
import main.java.View.TableView;
import main.java.View.ViewInterface;


/**
 * Created by bartek on 05.05.14.
 */
public class RunHere {
    public static void main(String[] args){
        MainAdapter adapter=new MainAdapter();
        TableView view = new TableView(adapter,0);
        ModelOne model = new ModelOne(adapter);
        adapter.addModel(model);
        adapter.addView(view);
        constructWindow
        Stage stage = new Stage();
        try {
            view.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addPlayer("Sylwek");
        model.addPlayer("Maciek");
        model.addPlayer("Bartek");
        model.addPlayer("Szymon");
        model.start();
    }
}

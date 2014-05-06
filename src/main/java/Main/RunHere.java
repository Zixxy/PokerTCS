package main.java.Main;
import main.java.Adapter.MainAdapter;
import main.java.Model.ModelOne;
import main.java.View.TableView;
import main.java.View.ViewInterface;


/**
 * Created by bartek on 05.05.14.
 */
public class RunHere {
    static TableView view;
    static MainAdapter adapter=new MainAdapter();
    public static void main(String[] args){
        final String[] arg=args;
     //   MainAdapter adapter=new MainAdapter();
        Thread viewThread = new Thread(){
            public void run(){
                view = new TableView();
                view.almostContructor(adapter,0);
                adapter.addView(view);
                view.constructWindow(arg);
            }
        };
        viewThread.start();




        ModelOne model = new ModelOne(adapter);
        adapter.addModel(model);

      //  adapter.addView(view);
        model.addPlayer("Sylwek");
        model.addPlayer("Maciek");
        model.addPlayer("Bartek");
        model.addPlayer("Szymon");
        System.out.println("Dodalem graczy");
        model.start();
    }
}
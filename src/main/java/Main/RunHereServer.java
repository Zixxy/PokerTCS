package main.java.Main;

import java.io.IOException;

import main.java.Adapter.MainAdapter;
import main.java.Communication.CommunicationView;
import main.java.Model.ModelOne;
import main.java.View.CommandLine;
import main.java.View.TableView;
import main.java.View.ViewInterface;

public class RunHereServer {
	private static Config config;

	public static void main(String[] args) {

		MainAdapter adapter = new MainAdapter();
		ViewInterface view;
		try {
			view = new CommunicationView(1224, adapter);
		}
		catch (IOException e) {
			System.err.println("FATAL ERROR");
			return;
		}

		adapter.addView(view);

		ViewInterface view2 = TableView.createTableView(args, adapter, 1);
		adapter.addView(view2);

		ModelOne model = new ModelOne(adapter);
		adapter.addModel(model);
		//(new Scanner(System.in)).nextInt();
		ViewInterface textView = new CommandLine(adapter);
		adapter.addView(textView);
		System.out.println("LOOOOOL");
		model.setAnte(10);
		model.setStartedAmount(2000);
		model.setSmallBlind(10);
		model.setBigBlind(20);
		//model.addPlayer("Sylwek");
		//model.addPlayer("Maciek");
		//
		model.addPlayer("Bartek");
		model.addPlayer("Szymon");
		model.start();

	}
}

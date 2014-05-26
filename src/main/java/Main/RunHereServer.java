package main.java.Main;

import java.io.IOException;
import java.util.Scanner;

import main.java.Adapter.MainAdapter;
import main.java.Communication.CommunicationView;
import main.java.Model.ModelOne;
import main.java.Server.Server;
import main.java.View.CommandLine;
import main.java.View.MainWindow;
import main.java.View.TableView;
import main.java.View.TableViewInterface;

public class RunHereServer {
	private static Config config;

	public static void runServer(int port, String name){
		MainAdapter adapter = Run.adapter;
		Server server = new Server(port);
		Run.mainWindow.showTableList();
		
		
	/*	TableViewInterface view;
		try {
			view = new CommunicationView(port, adapter);
		}
		catch (IOException e) {
			System.err.println("FATAL ERROR");
			return;
		}
		adapter.addView(view);
		//try{
		TableViewInterface view2 = Run.mainWindow.showGame(0);*/
	/*	} catch(Exception e){
			System.out.println("problems here!");
		}*/
	/*	adapter.addView(view2);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelOne model = new ModelOne(adapter);
		adapter.addModel(model);
		TableViewInterface textView = new CommandLine(adapter);
		adapter.addView(textView);
		model.setAnte(10);
		model.setStartedAmount(2000);
		model.setSmallBlind(10);
		model.setBigBlind(20);
		model.addPlayer("Maciek");
		model.addPlayer(name);*/
	}
	public static void main(String[] args) {

		MainAdapter adapter = new MainAdapter();
		TableViewInterface view;
		try {
			view = new CommunicationView(1228, adapter);
		}
		catch (IOException e) {
			System.err.println("FATAL ERROR");
			return;
		}

		adapter.addView(view);

		TableViewInterface view2 = TableView.createTableView(args, adapter, 0);
		adapter.addView(view2);

		ModelOne model = new ModelOne(adapter);
		adapter.addModel(model);
		//(new Scanner(System.in)).nextInt();
	TableViewInterface textView = new CommandLine(adapter);
		adapter.addView(textView);
		System.out.println("LOOOOOL");
		model.setAnte(10);
		model.setStartedAmount(2000);
		model.setSmallBlind(10);
		model.setBigBlind(20);
		//model.addPlayer("Sylwek");
		//model.addPlayer("Maciek");
		//
		model.addPlayer("Maciek");
		model.addPlayer("Sylwek");
		//model.start();

	}
}

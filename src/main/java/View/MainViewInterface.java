package main.java.View;

public interface MainViewInterface {
	/**
	 * while launching application this mainView should be created.
	 * 
	 * @param args PokerTCS's main method args.
	 */
	public TableViewInterface showTable();
	public MainMenu setMainMenu();
	
}

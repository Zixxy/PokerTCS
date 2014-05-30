package main.java.View;

public interface MainWindowInterface {
	/**
	 * while launching application this mainView should be created.
	 * 
	 * @param args PokerTCS's main method args.
	 */
	public ViewInterface showGame(int playerId);
	public void showMainMenu();
	public ViewInterface showTableList();
}

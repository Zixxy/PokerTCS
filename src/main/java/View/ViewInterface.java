package main.java.View;

import main.java.Model.Deck;

public interface ViewInterface {
	public void addPlayer(String name, int id); // możemy się umówić czy playera identyfikujemy imieniem czy id
	public void removePlayer(int id);
	public void updatePlayerCash(int id, int cash);
	public void addThreeCardsOnTable(Deck firstCard, Deck secondCard, Deck thirdCard);
	public void addOneCard(Deck card);
	public void clearTable();
	public void updatePlayerHand(Deck firstCard, Deck secondCard); // w razie nulli player nie ma zadnych kart
	public void updatePlayerLinedCash(int id, int cash);// ustawiamy gotówkę jaką player ma przed sobą na stole
    public void sendMessage(String text);
}
/* Umawialismy sie, ze nie bedzie takiego obiektu jak Card. Zmieniam na Integera - do pozniejszych zmian.
Chcę doprowadzić projekt do stanu kompilującego się.
Bartek.
 */
package View;

public interface ViewInterface {
	public void addPlayer(String name, int id); // możemy się umówić czy playera identyfikujemy imieniem czy id
	public void removePlayer(int id);
	public void updatePlayerCash(int id, int cash);
	public void addThreeCardsOnTable(Card firstCard, Card secondCard, Card thirdCard);
	public void addOneCard(Card card);
	public void clearTable();
	public void updatePlayerHand(Card firstCardId, Card secondCardId); // w razie nulli player nie ma zadnych kart
	public void updatePlayerLinedCash(int id, int cash);// ustawiamy gotówkę jaką player ma przed sobą na stole
}

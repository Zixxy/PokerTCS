package View;

public interface View {
	public void addPlayer(String name, int id); // możemy się umówić czy playera identyfikujemy imieniem czy id
	public void removePlayer(int id);
	public void updatePlayerCash(int id, int cash);
	public void addThreeCardsOnTable(Integer firstCard, Integer secondCard, Integer thirdCard);
	public void addOneCard(Integer card);
	public void clearTable();
	public void updatePlayerHand(Integer firstCardId, Integer secondCardId); // w razie nulli player nie ma zadnych kart
	public void updatePlayerLinedCash(int id, int cash);// ustawiamy gotówkę jaką player ma przed sobą na stole
}
/* Umawialismy sie, ze nie bedzie takiego obiektu jak Card. Zmieniam na Integera - do pozniejszych zmian.
Chcę doprowadzić projekt do stanu kompilującego się.
Bartek.
 */
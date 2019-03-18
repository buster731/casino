package setup;
import Card.Card;
import Deck.Deck;
import setup.profile;
public class actions{
	public Card hit() {
		return Deck.Deck.deal();
	}
	
}
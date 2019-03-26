package Table;

import Card.Card;
import Deck.Deck;
import setup.profile;
import hands.*;


public class table{
	
	public int chipBet;
	public static final String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	public static final String[] suits = {"Clubs", "Spades", "Hearts", "Diamonds"};
	public static final int[] values = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
	public Deck decks;
	
	public table(hand playerHand, hand dealerHand) {
		hand player = playerHand;
		hand dealer = dealerHand;
		decks =  new Deck(ranks, suits, values);
	}
	public Deck getDeck() {
		return decks;
	}
	public void showTable(hand player, hand dealer) {
		System.out.println("Player Hand: " );
		player.sortHand();
		for(Card held: player.holding) {
			System.out.print(held.toString());
		}
		System.out.println("Player Hand values at: " + player.handValue());
		System.out.println("Dealer Hand visible: " );
		dealer.sortHand();
		Card seen = dealer.holding.get(0);
		System.out.print(seen.toString());
		System.out.println("Dealer Hand visible values at: " + seen.pointValue());
	}
	
	public void hit(hand right) {
		Card c = decks.deal();
		right.holding.add(c);
		
		System.out.println("New Card: " + c.rank());
		System.out.println("Hand: " );
		right.sortHand();
		for(Card held: right.holding) {
			System.out.print(held.toString());
		}
		System.out.println("Hand values at: " + right.handValue());
	}
	
	public void stand(hand stand, hand dealer) {
		boolean dealerStop = false;
		System.out.println("Dealer Hand: ");
		for(Card held : dealer.holding) {
			System.out.println(held.rank());
		}
		System.out.println("Hand values at: " + dealer.handValue());
		while(dealerStop == false) {
			dealerStop = checkHand(dealer);
		}
		checkHand(stand, dealer);
	}
	
	public int checkHand(hand user, hand dealer) {
		int points = user.handValue();
		if(points == 21) {
			//win
			return win();
		}
		else if(points > 21) {
			//bust
			return lose();
		}
		else if(dealer.handValue() > 21) {
			//win
			return win();
		}
		else if(points > dealer.handValue()) {
			//win
			return win();
		}
		else if(dealer.handVal > points) {
			return lose();
		}
		else {
			return push();
		}
	}
	public boolean checkHand(hand dealer) {
		boolean dealerStop = false;
		if(dealer.handValue() < 17) {
			hit(dealer);
		}
		else {
			dealerStop = true;
		}
		return dealerStop;
	}

	public void start(hand person) {
		System.out.println(decks.size());
		
		for(int i = 0; i < 2; i++) {
			Card c = decks.deal();
			System.out.println(c);
			person.holding.add(c);
			System.out.println("New Card: " + c.rank());
			
		}
		System.out.println("Hand: " );
		person.sortHand();
		for(Card held: person.holding) {
			System.out.print(held.toString());
		}
		System.out.println("Hand values at: " + person.handValue());
	}
	
	public int win() {
		System.out.println("You win the hand!");
		int chipWin = chipBet *2;
		System.out.println("You won " + chipWin + " chips");
		profile.setChips(profile.getChips() + chipWin);
		System.out.println("Your new chip balance is " + profile.getChips() + " chips.");
		return 0;
	}
	public int lose() {
		System.out.println("You lose the hand!");
		System.out.println("Your new chip balance is " + profile.getChips() + " chips.");
		return 0;
	}
	public int push() {
		System.out.println("You pushed the hand");
		System.out.println("You keep your bet");
		profile.setChips(profile.getChips() + chipBet);
		System.out.println("Your new chip balance is " + profile.getChips() + " chips.");
		return 0;
	}
}
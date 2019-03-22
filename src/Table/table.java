package Table;

import Card.Card;
import Deck.Deck;
import setup.play;
import setup.profile;
import hands.*;


public class table{
	
	public static int chipBet;
	public static final String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	public static final String[] suits = {"Clubs", "Spades", "Hearts", "Diamonds"};
	public static final int[] values = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
	private static Deck decks =  new Deck(ranks, suits, values);
	
	
	
	
	public static void hit(hand right) {
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
	
	public static void stand(hand dealer) {
		boolean dealerStop = false;
		while(dealerStop == false) {
			dealerStop = checkHand(dealer);
		}
		checkHand(play.playerHand, dealer);
	}
	
	public static int checkHand(hand user, hand dealer) {
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
	public static boolean checkHand(hand dealer) {
		boolean dealerStop = false;
		if(dealer.handValue() < 17) {
			hit(dealer);
		}
		else {
			dealerStop = true;
		}
		return dealerStop;
	}

	public static void start(hand person) {
		hit(person);
		hit(person);
	}
	
	public static int win() {
		System.out.println("You win the hand!");
		int chipWin = chipBet *2;
		System.out.println("You won " + chipWin + " chips");
		profile.setChips(profile.getChips() + chipWin);
		System.out.println("Your new chip balance is " + profile.getChips() + " chips.");
		return 0;
	}
	public static int lose() {
		System.out.println("You lose the hand!");
		System.out.println("Your new chip balance is " + profile.getChips() + " chips.");
		return 0;
	}
	public static int push() {
		System.out.println("You pushed the hand");
		System.out.println("You keep your bet");
		profile.setChips(profile.getChips() + chipBet);
		System.out.println("Your new chip balance is " + profile.getChips() + " chips.");
		return 0;
	}
}
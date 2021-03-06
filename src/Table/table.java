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
//	public Deck getDeck() {
//		return decks;
//	}
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
			System.out.println(held.toString());
		}
		System.out.println("Hand values at: " + right.handValue());
	}
	
	public void stand(hand stand, hand dealer, long chipBet) {
		boolean dealerStop = false;
		System.out.println();

		System.out.println("Dealer Hand: ");
		for(Card held : dealer.holding) {
			System.out.println(held.rank());
		}
		System.out.println();

		System.out.println("Hand values at: " + dealer.handValue());
		while(dealerStop == false) {
			dealerStop = checkHand(dealer);
		}
		checkHand(stand, dealer, chipBet);
	}
	
	public int checkHand(hand user, hand dealer, long chipBet) {
		int points = user.handValue();
		if(points == 21) {
			//win
			win(chipBet);
			return 0;
		}
		else if(points > 21) {
			//bust		
			lose();
			return 0;
		}
		else if(dealer.handValue() > 21) {
			//win
			win(chipBet);
			return 0;
		}
		else if(points > dealer.handValue()) {
			//win
			win(chipBet);
			return 0;
		}
		else if(dealer.handValue() > points) {
			lose();
			return 0;
		}
		else {
			push(chipBet);
			return 0;
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
	
	public boolean checkHand(hand player, boolean bust) {
		if(player.handValue() > 21) {
			bust = true;
		}
		else{
			bust = false;
		}
		return bust;
	}

	public void playerStart(hand player) {
		System.out.println("Player: ");
		System.out.println();
		for(int i = 0; i < 2; i++) {
			Card c = decks.deal();
			System.out.println(c);
			player.holding.add(c);
			System.out.println("New Card: " + c.rank());
			
		}
		System.out.println("Player Hand: " );
		player.sortHand();
		for(Card held: player.holding) {
			System.out.println(held.toString());
		}
		System.out.println();

		System.out.println("Player Hand values at: " + player.handValue());
		System.out.println();

	}
	
	public void dealerStart(hand dealer) {
		System.out.println("Dealer: ");
		Card c = decks.deal();
		System.out.println(c);
		dealer.holding.add(c);
		System.out.println("New Card: " + c.rank());
		System.out.println("Dealer Hand: " );
		dealer.sortHand();
		for(Card held: dealer.holding) {
			System.out.println(held.toString());
		}
		System.out.println("Face Down Card");
		System.out.println();

		System.out.println("Dealer Hand values at: " + dealer.handValue() + " + ?");
		System.out.println();

	}
	
	public int win(long chipBet) {
		System.out.println("You win the hand!");
		long chipWin = chipBet *2;
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
	public int push(long chipBet) {
		System.out.println("You pushed the hand");
		System.out.println("You keep your bet");
		profile.setChips(profile.getChips() + chipBet);
		System.out.println("Your new chip balance is " + profile.getChips() + " chips.");
		return 0;
	}
}

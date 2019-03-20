package setup;
import java.util.Scanner;

import Card.Card;
import Deck.Deck;
import setup.profile;
import hands.*;



public class play{
	public static hand playerHand = new hand();
	public static hand dealerHand = new hand();
	
	private static Deck decks;
	// make sure this ^ makes a deck not just includes deck class
	public static void hit(hand right) {
		Card c = decks.deal();
		right.holding.add(c);
	}
	
	public void checkHand(hand user, hand dealer) {
		int points = user.handValue();
		if(points == 21) {
			//win
		}
		else if(points > 21) {
			//bust
		}
		else if(dealer.handValue() > 21) {
			//win
		}
		else if(points > dealer.handValue()) {
			//win
		}
		else {
			//push
		}
	}
	
	public static void start(hand person) {
		hit(person);
		hit(person);
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("What is your name?");
		profile.setName(in.nextLine());
		profile.setChips(5000);
		int cashout = -1;
		
		System.out.println("Welcome " + profile.getName() + "!");
		start(playerHand);
		start(dealerHand);
		while(cashout != 0) {
			System.out.println("There is no active hand yet. What would you like to do?");
			String action = in.nextLine();
			
			switch(action) {
			case "play":
				
				int over = -1;
				while(over != 0) {
					System.out.println("What would you like to do?");
					String hist = in.next();
					switch(hist) {
					case "hit":
						hit(playerHand);
						//print hand and stuff
						break;
					case "stand":
						//check stuff
						break;
					default:
						System.out.println("Not a valid action. Available actions are: /n/t- hit /n/t- stand /n");
						break;
					}
				}
				break;
			case "cashout":
				cashout = 0;
				break;
			default:				
				System.out.println("Not a valid action. Available actions are: /n/t- play /n/t- cashout /n");
				break;
			}
		}
		System.out.println("Thanks for playing " + profile.getName() + ".");
		in.close();
	}
}
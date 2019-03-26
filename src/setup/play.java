package setup;
import java.util.ArrayList;
import java.util.Scanner;

import Card.Card;
import Deck.Deck;
import Table.table;
import setup.profile;
import hands.*;


public class play{
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		hand playerHand = new hand(new ArrayList<Card>());
		hand dealerHand = new hand(new ArrayList<Card>());
		table newTable = new table(playerHand, dealerHand);
		System.out.println("What is your name?");
		profile.setName(in.nextLine());
		profile.setChips(5000);
		int cashout = -1;
		
		System.out.println("Welcome " + profile.getName() + "!");
		
		newTable.start(playerHand);
		newTable.start(dealerHand);
		playerHand.holding = playerHand.sortHand();
		dealerHand.holding = dealerHand.sortHand();
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
						newTable.hit(playerHand);
						over = newTable.checkHand(playerHand, dealerHand);
						//print hand and stuff
						break;
					case "stand":
						newTable.stand(playerHand, dealerHand);
						//check stuff
						break;
					case "table":
						newTable.showTable(playerHand, dealerHand);
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
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
		
		System.out.println("What is your name?");
		profile.setName(in.nextLine());
		profile.setChips(5000);
		int cashout = -1;
		
		System.out.println("Welcome " + profile.getName() + "!");
		
		
		while(cashout != 0) {
			System.out.println();
			System.out.println("There is no active hand yet. What would you like to do?");
			String action = in.nextLine();
			
			switch(action) {
			case "play":
				hand playerHand = new hand(new ArrayList<Card>());
				hand dealerHand = new hand(new ArrayList<Card>());
				table newTable = new table(playerHand, dealerHand);
				long chipBet = -1;
				System.out.println("How much would you like to bet?");
				String bet = in.nextLine();
				boolean isValid = false;
				while(isValid != true) {
					isValid = true;
					for(int i = 0; i < bet.length(); i++) {
						if(Character.isDigit(bet.charAt(i)) == false) {
							isValid = false;
						}
					}
					if(isValid == false) {
						System.out.println("Invalid bet amount");
						System.out.println("How much would you like to bet?");
						bet = in.nextLine();
					}
				}
				chipBet = Long.parseLong(bet);
				while(chipBet < 0 || chipBet > profile.getChips() || isValid == false) {
					isValid = true;
					for(int i = 0; i < bet.length(); i++) {
						if(Character.isDigit(bet.charAt(i)) == false) {
							isValid = false;
						}
					}
					System.out.println("Invalid bet amount");
					System.out.println("How much would you like to bet?");
					bet = in.nextLine();
					chipBet = Long.parseLong(bet);
				}
				long chipsRem = profile.getChips() - chipBet;
				profile.setChips(chipsRem);
				newTable.playerStart(playerHand);
				newTable.dealerStart(dealerHand);
				playerHand.holding = playerHand.sortHand();
				dealerHand.holding = dealerHand.sortHand();
				int over = -1;
				boolean bust = false;
				while(over != 0 && bust != true) {
					if(playerHand.handValue() == 21 && dealerHand.handValue() != 21) {
						System.out.println("BLACKJACK!");
						newTable.win(chipBet);
						over = 0;
						break;
					}
					System.out.println("What would you like to do?");
					String hist = in.next();
					switch(hist) {
					case "hit":
						newTable.hit(playerHand);
						bust = newTable.checkHand(playerHand, bust);
						if(bust == true) {
							newTable.lose();
						}
						//print hand and stuff
						break;
					case "stand":
						newTable.stand(playerHand, dealerHand, chipBet);
						over = 0;
						//check stuff
						break;
					case "table":
						newTable.showTable(playerHand, dealerHand);
						break;
					default:
						System.out.println("Not a valid action. Available actions are: \n\t- hit \n\t- stand \n\t- table");
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

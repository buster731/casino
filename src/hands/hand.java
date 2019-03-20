package hands;

import java.util.ArrayList;

import Card.Card;
import setup.profile;

public class hand{
	public int size;
	public int handVal;
	public int handPoints;
	public ArrayList<Card> holding;
	//make this the constructor, make dealer and player instances of it
	//public hand()
	public hand() {
		this.holding = new ArrayList<Card>();
		this.size = holding.size();
		this.handPoints = handValue();
	}
	
	public int handValue() {
		handVal = 0;
		int ace = 0;
		for(Card item : holding) {
			if(item.rank() == "ace") {
				ace++;
				handVal += 10;
			}
			handVal += item.pointValue();
		}
		if(handVal == 21) {
			return handVal;
		}
		else if(handVal > 21) {
			while(ace > 0 && handVal > 21) {
				handVal -= 10;
				ace--;
			}
			return handVal;
		}
		else {
			return handVal;
		}
	}
	
	public int getHandPoints() {
		return this.handPoints;
	}
	
}
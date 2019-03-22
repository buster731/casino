package hands;

import java.util.ArrayList;

import Card.Card;

public class hand{
	public int size;
	public int handVal;
	public int handPoints;
	public ArrayList<Card> holding;
	//make this the constructor, make dealer and player instances of it
	//public hand()
	public hand(ArrayList<Card> holding) {
		this.holding = holding;
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
	
	public ArrayList<Card> sortHand() {
		ArrayList<Card> tempHand = new ArrayList<Card>(this.holding.size());
		for(Card val : this.holding) {
			tempHand.add(val);
		}
		boolean clean = false;
		do {
			clean = true;
			for(int i = 0; i < tempHand.size() - 1; i++) {
				if(tempHand.get(i).compareTo(tempHand.get(i + 1)) > 0) {
					Card temp = tempHand.get(i);
					tempHand.set(i, tempHand.get(i + 1));
					tempHand.set(i + 1, temp);
					clean = false;
				}
			}
		}while(clean != true);
		for(int j = 0; j < tempHand.size(); j++) {
			this.holding.set(j, tempHand.get(j));
		}
		return this.holding;
	}


	
}
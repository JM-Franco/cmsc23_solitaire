package solitaire.model;

import java.util.ArrayList;

public class Deck {
	private int[] ranks = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
	
	private String[] suits = {"Heart", "Diamond", "Club", "Spade"};
	
	private int size = ranks.length * suits.length;
	
	private ArrayList<Card> deck;
	
	// creates a shuffled deck
	public Deck() {
		ArrayList<Card> Deck = new ArrayList<Card>();
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < ranks.length; j++) {
				Card k = new Card(suits[i], ranks[j]); 
				Deck.add(k);
			}
		}
		
		 for (int i = 0; i < size; i++) {
	            int r = i + (int) (Math.random() * (size-i));
	            Card temp = Deck.get(r);
	            Deck.set(r, Deck.get(i));
	            Deck.set(i, temp);
	        }
	        
		this.deck = Deck;
	}
	
	public Card get(int num) {
		return deck.get(num);
	}
	
	public int size() {
		return deck.size();
	}
	
	public static void main(String[] args) {
		Deck newDeck = new Deck();
		int count = 1;
		 for (int i = 0; i < 52; i++) {
			 System.out.println(newDeck.get(i).getRank(i) + " of " + newDeck.get(i).getSuit() + "s  " + newDeck.get(i).getColor());
			 count = i;
	     }
		 System.out.println(count + 1);
	}
	
	
	
}

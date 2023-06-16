package solitaire.model;

import javafx.scene.image.Image;

public class Card {
	private String suit;
	private String name;
	private int rank;
	private Image image;
	private boolean reveal = false;
	
	public Card(String s, int r){
		this.suit = s;
		this.rank = r;
		this.image = new Image("/images/" + getRank(r)+ "-of-" + s + "s.png", 100, 150, false, true);
	}
	
	public String getSuit() {
		return suit;
	}
	public String getName() {
		return getRank(rank) + " of " + suit + "s";
	}
	
	public int getRankValue() {
		return rank;
	}
	
	public Image getImage() {
		if (reveal == true) {
			return image;
		} else {
			Image cardBack = new Image("/images/cardback.png", 100, 150, false, true);
			return(cardBack);
		}
	}
	
	public String getColor() {
		if (suit == "Heart" || suit == "Diamond") {
			return "Red";
		} else {
			return "Black";
		}
	}
	
	
	public String getRank(int rank) {
		switch (rank) {
		case 1:
			return "Ace";
		case 2:
			return "Two";
		case 3:
			return "Three";
		case 4:
			return "Four";
		case 5:
			return "Five";
		case 6:
			return "Six";
		case 7:
			return "Seven";
		case 8:
			return "Eight";
		case 9:
			return "Nine";
		case 10:
			return "Ten";
		case 11:
			return "Jack";
		case 12:
			return "Queen";
		case 13:
			return "King";
		}
	return null;
	}
	
	public boolean isRevealed() {
		return reveal;
	}
	
	public void setReveal() {
		this.reveal = true;
	}
	
}

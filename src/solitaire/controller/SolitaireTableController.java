package solitaire.controller;


//Improvements: get the name of the highscorer and display along with the score
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import solitaire.model.Deck;
import solitaire.Main;
import solitaire.model.Card;
import java.util.ArrayList;
import java.util.Collections;

public class SolitaireTableController {
	
	Main main;
	
	@FXML
	TextField enterName;
	
	@FXML
	FlowPane cardPane1;
	
	@FXML
	FlowPane cardPane2;
	
	@FXML
	FlowPane cardPane3;
	
	@FXML
	FlowPane cardPane4;
	
	@FXML
	FlowPane cardPane5;
	
	@FXML
	FlowPane cardPane6;
	
	@FXML
	FlowPane cardPane7;
	
	@FXML
	AnchorPane foundPane1;
	
	@FXML
	AnchorPane foundPane2;
	
	@FXML
	AnchorPane foundPane3;
	
	@FXML
	AnchorPane foundPane4;
	
	@FXML
	FlowPane wastePane;
	
	@FXML
	Button stockButton;
	
	@FXML
	FlowPane handDisplay;
	
	@FXML
	Label scoreDisplay = new Label("SCORE: 0");
	
	@FXML
	Label winMessage = new Label();
	
	@FXML
	Label invalidMessage = new Label();
	
	// Variables that affect the program or the game
	String turn;
	Boolean fresh = true;
	
	// Variables that contain the scores
	ArrayList<Integer> highscores = new ArrayList<Integer>();
	int score;
	
	///	Variables responsible for transfers
	ArrayList<Card> regionContainer;
	Pane regionVisual;
	ArrayList<Card> hand = new ArrayList<Card>();
	Card cardContainer;
	Boolean isSelected = false;
	String fromLocation = null;
	
	// Variables that form the structure of the game
	Deck deck;
	ArrayList<Card>[] tableau = new ArrayList[7];
	ArrayList<Card>[] foundation = new ArrayList[4];
	ArrayList<Card> stock = new ArrayList<Card>();
	Card[] waste = new Card[3];
	int stockPtr = -1;
	int wastePtr = 0;
	int cardsInWaste = -1;
	
	
	/* 
	 * tableau[1 - 7] are the different tableaus
	 * from left to right respectively
	 * 
	 * foundation[0] is for "HEARTS"
	 * foundation[1] is for "DIAMONDS"
	 * foundation[2] is for "SPADES"
	 * foundation[3] is for "CLUBS"
	 * 
	 */
	
	
	// Methods not in-game
	
	public void turn1()
	{
		turn = "1";
		// Insert code to display "Are you sure you want to start a new game" Window
		newGame();
	}
	
	public void turn3()
	{
		turn = "3";
		// Insert code to display "Are you sure you want to start a new game" Window
		newGame();
	}
	
	@FXML
	public void newGame() 
	{
	
		foundPane1.getChildren().clear();
		foundPane2.getChildren().clear();
		foundPane3.getChildren().clear();
		foundPane4.getChildren().clear();
		cardPane1.getChildren().clear();
		cardPane2.getChildren().clear();
		cardPane3.getChildren().clear();
		cardPane4.getChildren().clear();
		cardPane5.getChildren().clear();
		cardPane6.getChildren().clear();
		cardPane7.getChildren().clear();
		handDisplay.getChildren().clear();
		wastePane.getChildren().clear();
		
		scoreDisplay.setText("SCORE: 0");
		winMessage.setText("");
		invalidMessage.setText("");
		
		score = 0;
		stockButton.setStyle("-fx-background-image: url('images/cardback.png'); -fx-background-size: 100 150;");
		
		
		if (fresh == false) 
		{
			for (ArrayList<Card> tab : tableau) 
			{
				tab.clear();
			}
			
			for (ArrayList<Card> fou : foundation) 
			{
				fou.clear();
			}
		}
		
		stock.clear();
		
		isSelected = false;
		fromLocation = null;
		regionContainer = null;
		hand.clear();
		cardContainer = null;
		regionVisual = null;
		deck = null;
		
		for (Card card : waste) 
		{
			card = null;
		}

		initializeGame();
	}
	
	@FXML
	public void closeProgram() 
	{
		Platform.exit();
	}
	
	@FXML
	public void showHighScores() 
	{
		main.showHighScores(highscores);
	}
	
	public void initializeGame()
	{
		fresh = false;
		
		//Gives out a newly shuffled deck
		Deck newDeck = new Deck();
		this.deck = newDeck;
		
		setStyle();
	
		//Initializes the Tableau
		int iTPtr = 0;
		for (int i = 0; i < tableau.length; i++) {
			ArrayList<Card> initTableau = new ArrayList<Card>();
			tableau[i] = initTableau;
			for (int j = 0; j <= i; j++) {
				Card tempCard = deck.get(iTPtr);
				if (j == i) {
					tempCard.setReveal();
				}
				tableau[i].add(tempCard);
				iTPtr++;
			}	
		}
		
		//Initializes the Tableau at the stage
		cardPane1.getChildren().add(new ImageView(tableau[0].get(0).getImage()));
		
		for (int t2 = 0; t2 < 2; t2++) {
			cardPane2.getChildren().add(new ImageView(tableau[1].get(t2).getImage()));
		}
		
		for (int t3 = 0; t3 < 3; t3++) {
			cardPane3.getChildren().add(new ImageView(tableau[2].get(t3).getImage()));
		}
		
		for (int t4 = 0; t4 < 4; t4++) {
			cardPane4.getChildren().add(new ImageView(tableau[3].get(t4).getImage()));
		}
		
		for (int t5 = 0; t5 < 5; t5++) {
			cardPane5.getChildren().add(new ImageView(tableau[4].get(t5).getImage()));
		}
		
		for (int t6 = 0; t6 < 6; t6++) {
			cardPane6.getChildren().add(new ImageView(tableau[5].get(t6).getImage()));
		}
		
		for (int t7 = 0; t7 < 7; t7++) {
			cardPane7.getChildren().add(new ImageView(tableau[6].get(t7).getImage()));
		}
		
		//Initializes the Foundation
		for (int j = 0; j < foundation.length; j++) {
			ArrayList<Card> initFoundation = new ArrayList<Card>();
			foundation[j] = initFoundation;
		}
		
		//Initializes the Stock
		for (int k = 28; k < 52; k++) {
			Card initStock = deck.get(k);
			initStock.setReveal();
			stock.add(initStock);
		}
		
		score = 0;
	}

	
	
	// Methods in-game
	public void getWaste() 
	{
		if (fresh == true) 
		{
			return;
		}
		
		if (fromLocation == "Waste") {
			resetPointers();
		}
		
		cardsInWaste = -1;
		wastePtr = 0;
		int size = stock.size();
		switch (turn)
		{
			case "1": 
				stockPtr++;
				if (stockPtr < size) 
				{
					if (stockPtr == size - 1) 
					{
						stockButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.25); -fx-effect: dropshadow(gaussian, black, 30, 0, 0, 0);");
					}
					
					waste[0] = stock.get(stockPtr);
					
					if (wastePane.getChildren().isEmpty() == false)
					{
						wastePane.getChildren().clear();
						wastePane.getChildren().add(new ImageView(waste[0].getImage()));
					}
					else
					{
						wastePane.getChildren().add(new ImageView(waste[0].getImage()));
					}
					
				}	
				else if (stockPtr == size) 
				{
					if (size == 0) 
					{
						stockButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.25); -fx-effect: dropshadow(gaussian, black, 30, 0, 0, 0);");
					} 
					else 
					{
						stockButton.setStyle("-fx-background-image: url('images/cardback.png'); -fx-background-size: 100 150;");
		
						score = score - 100;
						updateScore();			
					}
					
					for (int i = 0; i < 3; i++) 
					{
						if (waste[i] == null) 
						{
							break;
						}
						waste[i] = null;
					}
					
					wastePane.getChildren().clear();
					stockPtr = -1;
				}
				System.out.print(stockPtr + ", ");
				break;
			case "3":
				if (wastePane.getChildren().isEmpty() == false) {
					wastePane.getChildren().clear();
					for (int j = 0; j < 3; j++) 
					{
						if (waste[j] == null) 
						{
							break;
						}
						waste[j] = null;
					}
				}
				
				
				for (int i = 0; i < 3; i++)
				{
					stockPtr++;
					if (stockPtr < size)
					{
						if (size - stockPtr <= 3)
						{
							stockButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.25); -fx-effect: dropshadow(gaussian, black, 30, 0, 0, 0);");
						}
						
						cardsInWaste++;
						waste[i] = stock.get(stockPtr);
					}
					else if (stockPtr == size)
					{
						if (size <= 3) {
							stockButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.25); -fx-effect: dropshadow(gaussian, black, 30, 0, 0, 0);");
							
						}
						else if (size > 3)
						{
							stockButton.setStyle("-fx-background-image: url('images/cardback.png'); -fx-background-size: 100 150;");
						}
						
						System.out.println("Stock size: " + stock.size());
						
						for (int j = 0; j < 3; j++) 
						{
							if (waste[j] == null) 
							{
								break;
							}
							waste[j] = null;
							
						}
						
						wastePane.getChildren().clear();
						stockPtr = -1;
						break;
					}
				}
				
				for (int i = 2; i >= 0; i--) 
				{
					if (waste[i] == null) {
						continue;
					} 
					else if (waste[i] != null)
					{
						wastePane.getChildren().add(new ImageView(waste[i].getImage()));
					}
					
				}
				System.out.print(stockPtr + ", ");
				break;
		}
		
	}
	//Handles transfers to Tableaus
	public void tableauTransfer(ArrayList<Card> tableau, FlowPane tableauDisplay) 
	{	
		if (fresh == true) 
		{
			return;
		}
		
		int cardsOnTabl = tableau.size() - 1;		
		if (!isSelected) 
		{	
			if (tableau.isEmpty() == false) 
			{
				invalidMessage.setText("");
				cardContainer = tableau.get(cardsOnTabl);
				hand.add(cardContainer);
				for (int i = hand.size() - 1; i >= 0; i--) 
				{
					handDisplay.getChildren().add(new ImageView(hand.get(i).getImage()));
				}
				tableauDisplay.getChildren().remove(cardsOnTabl);
				tableau.remove(cardsOnTabl);
				
				
				regionContainer = tableau;
				regionVisual = tableauDisplay;
				fromLocation = "Tableau";
				isSelected = true;
			}
			
		} 
		else
		{
			switch (fromLocation) 
			{
				case "Tableau":
					if (tableau.isEmpty() == true) 
					{			
						if (cardContainer.getRankValue() == 13 && regionContainer != tableau) 
						{
							if(regionContainer.isEmpty() == false) 
							{
								if (regionContainer.get(regionContainer.size() - 1).isRevealed() == false) 
								{
									regionContainer.get(regionContainer.size() - 1).setReveal();
									regionVisual.getChildren().remove(regionContainer.size() - 1);
									regionVisual.getChildren().add(new ImageView(regionContainer.get(regionContainer.size() - 1).getImage()));
									
									score = score + 5;
									updateScore();
								}
							}
							for (int i = hand.size() - 1; i >= 0; i--) {
								tableau.add(hand.get(i));
								tableauDisplay.getChildren().add(new ImageView(hand.get(i).getImage()));
							}
						} 
						else 
						{
							if (regionContainer != tableau) 
							{
								invalidMessage.setText("Invalid Move.");
							}
							
							for (int i = hand.size() - 1; i >= 0; i--) 
							{
								regionVisual.getChildren().add(new ImageView(hand.get(i).getImage()));
								regionContainer.add(hand.get(i));
							}
						}

					} 
					else if (tableau.isEmpty() == false && regionContainer == tableau) 
					{
						if (tableau.get(tableau.size() - 1).isRevealed()) 
						{
							cardContainer = tableau.get(tableau.size() - 1);
							hand.add(cardContainer);
							handDisplay.getChildren().clear();
							for (int i = hand.size() - 1; i >= 0; i--) 
							{
								handDisplay.getChildren().add(new ImageView(hand.get(i).getImage()));
							}
							regionVisual.getChildren().remove(tableau.size() - 1);
							regionContainer.remove(tableau.size() - 1);
							return;
						} 
						else {
							
							for (int i = hand.size() - 1; i >= 0; i--) 
							{
								regionVisual.getChildren().add(new ImageView(hand.get(i).getImage()));
								regionContainer.add(hand.get(i));
							}
							resetPointers();
						}
					} 
					else if (tableau.isEmpty() == false && regionContainer != tableau) 
					{					
						if (tableau.get(cardsOnTabl).getRankValue() - cardContainer.getRankValue() == 1 &&
								cardContainer.getColor() != tableau.get(cardsOnTabl).getColor()) 
						{
							if(regionContainer.isEmpty() == false) 
							{
								if (regionContainer.get(regionContainer.size() - 1).isRevealed() == false) 
								{
									regionContainer.get(regionContainer.size() - 1).setReveal();
									regionVisual.getChildren().remove(regionContainer.size() - 1);
									regionVisual.getChildren().add(new ImageView(regionContainer.get(regionContainer.size() - 1).getImage()));
									
									score = score + 5;
									updateScore();
								}
							}
							for (int i = hand.size() - 1; i >= 0; i--) 
							{
								tableauDisplay.getChildren().add(new ImageView(hand.get(i).getImage()));
								tableau.add(hand.get(i));
							}
						} 
						else 
						{					
							invalidMessage.setText("Invalid Move.");
							for (int i = hand.size() - 1; i >= 0; i--) 
							{
								regionVisual.getChildren().add(new ImageView(hand.get(i).getImage()));
								regionContainer.add(hand.get(i));
							}
						}
					}
					resetPointers();
					break;
				case "Waste":
					if (tableau.isEmpty()) 
					{
						if (cardContainer.getRankValue() == 13) 
						{
							switch (turn) 
							{
								case  "1":
									for (int i = 0; i < 3; i++) 
									{
										if (waste[i] == null) 
										{
											break;
										}
										waste[i] = null;
									}
									
									tableau.add(cardContainer);
									stock.remove(stockPtr);
									stockPtr--;
									tableauDisplay.getChildren().add(new ImageView(cardContainer.getImage()));
									
									score = score + 5;
									updateScore();
									break;
									
								case "3":
									tableau.add(cardContainer);
									tableauDisplay.getChildren().add(new ImageView(cardContainer.getImage()));
									stock.remove(stockPtr - cardsInWaste);
									waste[wastePtr] = null;
									cardsInWaste--;
									stockPtr--;
									wastePtr++;
									if (wastePtr > 2) {
										wastePtr = 2;
									}
									break;
							}

						} 
						else 
						{
							invalidMessage.setText("Invalid Move.");
							wastePane.getChildren().add(new ImageView(cardContainer.getImage()));
						}
						
						resetPointers();
						return;
					} 
					else 
					{
						if (tableau.get(cardsOnTabl).getRankValue() - cardContainer.getRankValue() == 1 &&
								cardContainer.getColor() != tableau.get(cardsOnTabl).getColor()) 
						{
							
							switch (turn) 
							{
								case "1":
									for (int i = 0; i < 3; i++) 
									{
										if (waste[i] == null) 
										{
											break;
										}
										waste[i] = null;
									}
									
									tableau.add(cardContainer);
									stock.remove(stockPtr);
									stockPtr--;
									tableauDisplay.getChildren().add(new ImageView(cardContainer.getImage()));
									
									score = score + 5;
									updateScore();
									break;
								
								case "3":
									tableau.add(cardContainer);
									tableauDisplay.getChildren().add(new ImageView(cardContainer.getImage()));
									stock.remove(stockPtr - cardsInWaste);
									waste[wastePtr] = null;
									cardsInWaste--;
									stockPtr--;
									wastePtr++;
									if (wastePtr > 2) {
										wastePtr = 2;
									}
									
									score = score + 5;
									updateScore();
									break;
							}
						} 
						else 
						{
							invalidMessage.setText("Invalid Move.");
							wastePane.getChildren().add(new ImageView(cardContainer.getImage()));
						}
						
						resetPointers();
					}
					break;
				case "Foundation":
					
					/* if code breaks, remove comment brackets
					if (regionContainer.isEmpty()) {
						
					}	
					*/
					
					if (tableau.isEmpty()) 
					{
						if (cardContainer.getRankValue() == 13) 
						{
							for (int i = hand.size() - 1; i >= 0; i--) 
							{
								tableau.add(hand.get(i));
								tableauDisplay.getChildren().add(new ImageView(hand.get(i).getImage()));
								
								score = score - 15;
								updateScore();
							}
						} 
						else 
						{
							invalidMessage.setText("Invalid Move.");
							for (int i = hand.size() - 1; i >= 0; i--) 
							{
								regionVisual.getChildren().add(new ImageView(hand.get(i).getImage()));
								regionContainer.add(hand.get(i));
							}
						}
						
					} 
					else 
					{
						if (tableau.get(cardsOnTabl).getRankValue() - cardContainer.getRankValue() == 1 &&
								cardContainer.getColor() != tableau.get(cardsOnTabl).getColor()) 
						{
							tableau.add(cardContainer);
							tableauDisplay.getChildren().add(new ImageView(cardContainer.getImage()));
							score = score - 15;
							updateScore();
						} 
						else 
						{
							invalidMessage.setText("Invalid Move.");
							regionContainer.add(cardContainer);
							regionVisual.getChildren().clear();
							regionVisual.getChildren().add(new ImageView(cardContainer.getImage()));
							
						}
					}
					resetPointers();
					break;
			}
		}
			
	
	}
	
	// Handles all transfers from the waste;
	public void transferWaste() 
	{
		if (fresh == true) 
		{
			return;
		}
		
		if (!isSelected) {
			if (stockPtr != -1 && waste[wastePtr] != null) 
			{
				invalidMessage.setText("");
				
				switch (turn)
				{
					case "1":
						cardContainer = waste[0];
						hand.add(cardContainer);
						handDisplay.getChildren().clear();
						for (int i = hand.size() - 1; i >= 0; i--) 
						{
							handDisplay.getChildren().add(new ImageView(hand.get(i).getImage()));
						}
						wastePane.getChildren().clear();
						break;
						
					case "3":
						cardContainer = waste[wastePtr];
						hand.add(cardContainer);
						handDisplay.getChildren().clear();
						for (int i = hand.size() - 1; i >= 0; i--) 
						{
							handDisplay.getChildren().add(new ImageView(hand.get(i).getImage()));
						}
						wastePane.getChildren().remove(2 - wastePtr);
						break;
				}		
	
				fromLocation = "Waste";
				isSelected = true;
			}
		
		} 
		else 
		{
			if (fromLocation == "Waste") 
			{
				System.out.println("cardContainer: " + cardContainer.getName());
				wastePane.getChildren().add(new ImageView(cardContainer.getImage()));
			} 
			else if (fromLocation == "Foundation") 
			{
				invalidMessage.setText("Invalid Move.");
				regionContainer.add(cardContainer);
				regionVisual.getChildren().add(new ImageView(cardContainer.getImage()));
			} 
			else if (fromLocation == "Tableau") 
			{
				invalidMessage.setText("Invalid Move.");
				for (int i = hand.size() - 1; i >= 0; i--) 
				{
					regionVisual.getChildren().add(new ImageView(hand.get(i).getImage()));
					regionContainer.add(hand.get(i));
				}
			}
			resetPointers();
		}
	}
	
	// Handles all transfers to and from the foundation
	public void foundationTransfer(ArrayList<Card> foundation, AnchorPane foundationDisplay, String suit) 
	{
		if (fresh == true) {
			return;
		}
		
		int movableCard = foundation.size() - 1;
		if (!isSelected) 
		{
			if (foundation.isEmpty() == false) 
			{
				System.out.println("Movable Card Index: " + movableCard);
				System.out.println("Foundation Size: " + foundation.size());
				System.out.println("Number of Children in Foundation: " + foundationDisplay.getChildren().size());
				invalidMessage.setText("");
				foundationDisplay.getChildren().clear();
				if (foundation.size() > 1) 
				{
					foundationDisplay.getChildren().add(new ImageView(foundation.get(movableCard - 1).getImage()));
				} 
				else if (foundation.size() == 1) 
				{
					foundationDisplay.getChildren().add(new ImageView(new Image("images/" + suit + "Foundation.png", 100, 150, false, true)));
				}
				
			
				cardContainer = foundation.get(movableCard);
				hand.add(cardContainer);
				handDisplay.getChildren().clear();
				for (int i = hand.size() - 1; i >= 0; i--) 
				{
					handDisplay.getChildren().add(new ImageView(hand.get(i).getImage()));
				}
				foundation.remove(movableCard);
				regionContainer = foundation;
				regionVisual = foundationDisplay;
				isSelected = true;
				fromLocation = "Foundation";
				System.out.println("Card on hand :" + cardContainer.getName()); // prints out card on hand

			}
		} else {
			if (fromLocation == "Foundation") {
				if (cardContainer.getSuit() != suit) 
				{
					invalidMessage.setText("Invalid Move.");
					regionVisual.getChildren().add(new ImageView(cardContainer.getImage()));
					regionContainer.add(cardContainer);
				} 
				else 
				{
					foundationDisplay.getChildren().clear();
					foundationDisplay.getChildren().add(new ImageView(cardContainer.getImage()));
					foundation.add(cardContainer);
				}
				resetPointers();

			
			} else if (fromLocation == "Tableau") {
				if (hand.size() > 1) 
				{
					invalidMessage.setText("Invalid Move.");
					
					for (int i = hand.size() - 1; i >= 0; i--) 
					{
						regionVisual.getChildren().add(new ImageView(hand.get(i).getImage()));
						regionContainer.add(hand.get(i));
					}
					
					resetPointers();
					return;
				}
				
				if (cardContainer.getRankValue() == 1 && 
						cardContainer.getSuit() == suit && 
						foundation.isEmpty()) 
				{
					if(regionContainer.size() > 0) 
					{
						if (regionContainer.get(regionContainer.size() - 1).isRevealed() == false) 
						{
							regionContainer.get(regionContainer.size() - 1).setReveal();
							regionVisual.getChildren().remove(regionContainer.size() - 1);
							regionVisual.getChildren().add(new ImageView(regionContainer.get(regionContainer.size() - 1).getImage()));
							
							score = score + 5;
							updateScore();
						}
					}
					score = score + 10;
					updateScore();
					foundationDisplay.getChildren().clear();
					foundation.add(cardContainer);	
					foundationDisplay.getChildren().add(new ImageView(cardContainer.getImage()));
				} else if (foundation.isEmpty() == false) {
					if (cardContainer.getRankValue() - foundation.get(movableCard).getRankValue() == 1 && 
							cardContainer.getSuit() == suit) 
					{
						if(regionContainer.size() > 0) 
						{
							if (regionContainer.get(regionContainer.size() - 1).isRevealed() == false) 
							{
								regionContainer.get(regionContainer.size() - 1).setReveal();
								regionVisual.getChildren().remove(regionContainer.size() - 1);
								regionVisual.getChildren().add(new ImageView(regionContainer.get(regionContainer.size() - 1).getImage()));
								
								score = score + 5;
								updateScore();
							}
						}
						score = score + 10;
						updateScore();
						foundation.add(cardContainer);	
						foundationDisplay.getChildren().clear();
						foundationDisplay.getChildren().add(new ImageView(cardContainer.getImage()));
						isWin();
					} 
					else 
					{
						invalidMessage.setText("Invalid Move.");
						regionVisual.getChildren().add(new ImageView(cardContainer.getImage()));
						regionContainer.add(cardContainer);
					}
				} else {
					invalidMessage.setText("Invalid Move.");
					regionVisual.getChildren().add(new ImageView(cardContainer.getImage()));
					regionContainer.add(cardContainer);
				}
				resetPointers();
			} else if (fromLocation == "Waste") {
				if (cardContainer.getRankValue() == 1 && 
						cardContainer.getSuit() == suit && 
						foundation.isEmpty())
				{
					switch (turn) 
					{
						case "1":
							for (int i = 0; i < 3; i++) 
							{
								if (waste[i] == null) 
								{
									break;
								}
								waste[i] = null;
							}
							
							foundation.add(cardContainer);
							stock.remove(stockPtr);
							stockPtr--;
							foundationDisplay.getChildren().add(new ImageView(cardContainer.getImage()));
							
							score = score + 10;
							updateScore();
							break;
						
						case "3":
							foundation.add(cardContainer);
							foundationDisplay.getChildren().add(new ImageView(cardContainer.getImage()));
							stock.remove(stockPtr - cardsInWaste);
							waste[wastePtr] = null;
							cardsInWaste--;
							stockPtr--;
							wastePtr++;
							if (wastePtr > 2) {
								wastePtr = 2;
							}
							
							
							score = score + 10;
							updateScore();
							break;
					}
				} 
				else if (cardContainer.getRankValue() > 1 &&
						 cardContainer.getSuit() == suit && 
						 foundation.isEmpty() == false &&
						 cardContainer.getRankValue() - foundation.get(movableCard).getRankValue() == 1) 
				{	
					switch (turn) 
					{
						case "1":
							for (int i = 0; i < 3; i++) 
							{
								if (waste[i] == null) 
								{
									break;
								}
								waste[i] = null;
							}
							
							foundation.add(cardContainer);
							stock.remove(stockPtr);
							stockPtr--;
							foundationDisplay.getChildren().clear();
							foundationDisplay.getChildren().add(new ImageView(cardContainer.getImage()));
							
							score = score + 10;
							updateScore();
							break;
						
						case "3":
							foundation.add(cardContainer);
							foundationDisplay.getChildren().clear();
							foundationDisplay.getChildren().add(new ImageView(cardContainer.getImage()));
							stock.remove(stockPtr - cardsInWaste);
							waste[wastePtr] = null;
							cardsInWaste--;
							stockPtr--;
							wastePtr++;
							if (wastePtr > 2) {
								wastePtr = 2;
							}
							
							
							score = score + 10;
							updateScore();
							break;
					}
				} 
				else 
				{
					invalidMessage.setText("Invalid Move.");
					wastePane.getChildren().add(new ImageView(cardContainer.getImage()));
				}
			}
			resetPointers();
		}
	}
	
	public void transferTab1() 
{
		tableauTransfer(tableau[0], cardPane1);
	}
			
	public void transferTab2() 
{
		tableauTransfer(tableau[1], cardPane2);
	}
	
	public void transferTab3() 
{
		tableauTransfer(tableau[2], cardPane3);
	}
		
	public void transferTab4() 
{
		tableauTransfer(tableau[3], cardPane4);
	}
			
	public void transferTab5() 
{
		tableauTransfer(tableau[4], cardPane5);
	}
		
	public void transferTab6() 
{
		tableauTransfer(tableau[5], cardPane6);
	}
		
	public void transferTab7() 
{
		tableauTransfer(tableau[6], cardPane7);
	}

	public void transferFoundHeart() 
{
		foundationTransfer(foundation[0], foundPane1, "Heart");
	}
		
	public void transferFoundDiamond() 
{
		foundationTransfer(foundation[1], foundPane2, "Diamond");
	}
	
	public void transferFoundSpade() 
{
		foundationTransfer(foundation[2], foundPane3, "Spade");
	}
		
	public void transferFoundClub() 
{
		foundationTransfer(foundation[3],foundPane4, "Club");
	}
	
	public void updateScore() 
{
		if (score < 0) {
			score = 0;
		}
		scoreDisplay.setText("SCORE: " + String.valueOf(score));
	}
	
	public void isWin() 
{
		if (foundation[0].size() == 13 && foundation[1].size() == 13 && foundation[2].size() == 13 && foundation[3].size() == 13) {
			winMessage.setText("YOU WIN!");
			highscores.add(score);
			highscores.sort(null);
			Collections.sort(highscores, Collections.reverseOrder());
		}		
	}
	
	public void resetPointers() 
	{
		hand.clear();
		handDisplay.getChildren().clear();
		fromLocation = null;
		cardContainer = null;
		regionVisual = null;
		regionContainer = null;
		isSelected = false;
	}
	
	
	
	// Miscellaneous methods
	public void setMain(Main m) 
	{
		this.main = m;
	}
	
	public void setStyle() {
		
		foundPane1.getChildren().add(new ImageView(new Image("images/HeartFoundation.png", 100, 150, false, true)));
		foundPane2.getChildren().add(new ImageView(new Image("images/DiamondFoundation.png", 100, 150, false, true)));
		foundPane3.getChildren().add(new ImageView(new Image("images/SpadeFoundation.png", 100, 150, false, true)));
		foundPane4.getChildren().add(new ImageView(new Image("images/ClubFoundation.png", 100, 150, false, true)));
		foundPane1.getStyleClass().add("foundation");
		foundPane2.getStyleClass().add("foundation");
		foundPane3.getStyleClass().add("foundation");
		foundPane4.getStyleClass().add("foundation");
		cardPane1.getStyleClass().add("tableau");
		cardPane2.getStyleClass().add("tableau");
		cardPane3.getStyleClass().add("tableau");
		cardPane4.getStyleClass().add("tableau");
		cardPane5.getStyleClass().add("tableau");
		cardPane6.getStyleClass().add("tableau");
		cardPane7.getStyleClass().add("tableau");
		wastePane.getStyleClass().add("waste");
		handDisplay.getStyleClass().add("hand");
	}
	
	/*
	@FXML
	public void remove() {
		cardPane1.getChildren().get(0);
		//cardPane.getChildren().remove(cardPane.get);
	}
	
	@FXML
	VBox vbox = new VBox();
	
	Image img = new Image("file:cardback.png");
	
	ImageView image = new ImageView(img);
	
	
	@FXML
	public void putIn(){
		//System.out.println("hello" + numLabels);
		//for(int i = 0; i < 5; i++){
			//Label newLabel = new Label();
			//newLabel.setGraphic(image);
		Image img = new Image(getClass().getResource("cardback.png").toExternalForm());
		ImageView image = new ImageView(img);
		image.setFitHeight(100.0);
		image.setFitWidth(100.0);
			//image.setPrefSize(100.0, 150.0);
		image.setStyle("-fx-border-color: green; ");
		vbox.getChildren().add(image);
		//}
	}
	*/
	
	/*
	@FXML
	public void remove() {
		vbox1.getChildren().remove(0);
	}
	*/
	
	
	/*
	if (regionContainer == tableau && tableau.isEmpty() == false) {
		if (tableau.get(tableau.size() - 1).isRevealed()) {
			cardContainer = tableau.get(tableau.size() - 1);
			hand.add(cardContainer);
			regionContainer.remove(tableau.size() - 1);
			regionVisual.getChildren().remove(tableau.size() - 1);
		} 
		return;
	}
	
	
	*/
	/*
	if (regionContainer == tableau) {
		
		
		if (hand.size() == 1) {
			regionContainer.remove(regionContainer.size() - 1);
		}
	
		if (tableau.size() > 0) {
			if (tableau.get(tableau.size() - 1).isRevealed()) {
				cardContainer = tableau.get(tableau.size() - 1);
				hand.add(cardContainer);
				regionVisual.getChildren().remove(tableau.size() - 1);
				regionContainer.remove(regionContainer.size() - 1);
				
				// this is just for error checking
				System.out.print("Cards in hand: ");
				for (int hatdog = 0; hatdog < hand.size(); hatdog++) {
					System.out.print(hand.get(hatdog).getName() + ", ");
				}
				System.out.println();
				
				System.out.println("\nCard on cardContainer: " + cardContainer.getName()); // prints out card on hand
				
				return;
			}
		}

		if (tableau.isEmpty() == false && tableau.get(tableau.size() - 1).isRevealed() ==  true) { // will likely cause an error because if ArrayList is empty then the index (tableau.size() - 1) might be out of bounds 
			cardContainer = tableau.get(tableau.size() - 1);
			hand.add(cardContainer);
			regionVisual.getChildren().remove(tableau.size() - 1);
			regionContainer.remove(regionContainer.size() - 1);

	
		
		
		for (int i = hand.size() - 1; i >= 0; i--) {
			tableau.add(hand.get(i));
			tableauDisplay.getChildren().add(new ImageView(hand.get(i).getImage()));
		}
		
		hand.clear();
		//sameTableau = true;
		cardContainer = null;
		fromFound = false;
		fromWaste = false;
		regionVisual = null;
		regionContainer = null;
		isSelected = false;
		// this is just for error checking
		System.out.print("Cards in hand: ");
		for (int hatdog = 0; hatdog < hand.size(); hatdog++) {
			System.out.print(hand.get(hatdog).getName() + ", ");
		}
		System.out.println();
		return;
	}
	*/
	/*
	if (tableau.isEmpty()) {
		if (cardContainer.getRankValue() == 13 && regionContainer != tableau) {
			for (int i = hand.size() - 1; i >= 0; i--) {
				tableau.add(hand.get(i));
				tableauDisplay.getChildren().add(new ImageView(hand.get(i).getImage()));
			}
		} else if (regionContainer == tableau) {
			for (int i = hand.size() - 1; i >= 0; i--) {
				tableau.add(hand.get(i));
				tableauDisplay.getChildren().add(new ImageView(hand.get(i).getImage()));
			}
		} else {
			for (int i = hand.size() - 1; i >= 0; i--) {
				regionVisual.getChildren().add(new ImageView(hand.get(i).getImage()));
				regionContainer.add(hand.get(i));
			}
		}
		
		hand.clear();
		fromFound = false;
		fromWaste = false;
		cardContainer = null;
		regionVisual = null;
		regionContainer = null;
		isSelected = false;
		return;
	}
	
	if (fromFound == true) {
		if (tableau.get(cardsOnTabl).getRankValue() - cardContainer.getRankValue() == 1 &&
				cardContainer.getColor() != tableau.get(cardsOnTabl).getColor()) {
			tableau.add(cardContainer);
			tableauDisplay.getChildren().add(new ImageView(cardContainer.getImage()));
		} else {
			regionContainer.add(cardContainer);
			regionVisual.getChildren().clear();
			regionVisual.getChildren().add(new ImageView(cardContainer.getImage()));
			
		}
		hand.clear();
		fromFound = false;
		fromWaste = false;
		cardContainer = null;
		regionVisual = null;
		regionContainer = null;
		isSelected = false;
		return;
	}
		
	if (tableau.get(cardsOnTabl).getRankValue() - cardContainer.getRankValue() == 1 &&
			cardContainer.getColor() != tableau.get(cardsOnTabl).getColor()) {
		if(regionContainer.size() > 0) {
			if (regionContainer.get(regionContainer.size() - 1).isRevealed() == false) {
				regionContainer.get(regionContainer.size() - 1).setReveal();
				regionVisual.getChildren().remove(regionContainer.size() - 1);
				regionVisual.getChildren().add(new ImageView(regionContainer.get(regionContainer.size() - 1).getImage()));
				
				score = score + 5;
				updateScore();
			}
		}
		
	
	} else {
		for (int i = hand.size() - 1; i >= 0; i--) {
			regionVisual.getChildren().add(new ImageView(hand.get(i).getImage()));
			regionContainer.add(hand.get(i));
		}
	}

	
	hand.clear();
	fromFound = false;
	fromWaste = false;
	cardContainer = null;
	regionVisual = null;
	regionContainer = null;
	isSelected = false;
	
} else if (regionContainer == null) {
	if (tableau.isEmpty()) {
		if (cardContainer.getRankValue() == 13 && regionContainer != tableau) {
			waste = null;
			tableau.add(cardContainer);
			stock.remove(stockPtr);
			stockPtr--;
			tableauDisplay.getChildren().add(new ImageView(cardContainer.getImage()));
			score = score + 5;
			updateScore();
		} else {
			wastePane.getChildren().add(new ImageView(cardContainer.getImage()));
		}
		
		hand.clear();
		fromFound = false;
		fromWaste = false;
		cardContainer = null;
		regionVisual = null;
		regionContainer = null;
		isSelected = false;
		return;
	}
	
	if(tableau.get(cardsOnTabl).getRankValue() - cardContainer.getRankValue() == 1 &&
			cardContainer.getColor() != tableau.get(cardsOnTabl).getColor()) {
		waste = null;
		tableau.add(cardContainer);
		stock.remove(stockPtr);
		stockPtr--;
		tableauDisplay.getChildren().add(new ImageView(cardContainer.getImage()));
		score = score + 5;
		updateScore();
	} else {
		wastePane.getChildren().add(new ImageView(cardContainer.getImage()));
	}
	
	hand.clear();
	fromFound = false;
	fromWaste = false;
	cardContainer = null;
	regionVisual = null;
	regionContainer = null;
	isSelected = false;
} 
*/
}

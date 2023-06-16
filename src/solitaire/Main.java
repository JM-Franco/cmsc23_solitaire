package solitaire;
	

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import solitaire.controller.HighscoreController;
import solitaire.controller.SolitaireTableController;
import solitaire.model.Card;
import solitaire.model.Deck;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	public Stage primaryStage;
	public Stage secondaryStage;
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
			Deck deck = new Deck();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/solitaireTableView.fxml"));
			AnchorPane solitaireTableViewFXML = (AnchorPane) loader.load();
			
			Scene scene = new Scene(solitaireTableViewFXML);
			scene.getStylesheets().add("solitaire/application.css");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			SolitaireTableController tableController = loader.getController();
			tableController.setMain(this);
			//tableController.initializeGame();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showHighScores(ArrayList<Integer> highscores) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/highscoreView.fxml"));
			AnchorPane showhighscoreFXML = (AnchorPane) loader.load();
	
			Scene scene = new Scene(showhighscoreFXML);
			Stage secondaryStage = new Stage();
			secondaryStage.initModality(Modality.WINDOW_MODAL);
			secondaryStage.setScene(scene);
			secondaryStage.initOwner(primaryStage);
			secondaryStage.show();
			
			HighscoreController highscoreController = loader.getController();
			highscoreController.setStage(secondaryStage);
			highscoreController.displayHighScores(highscores);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	public void showAbout() {
		
	}
	*/

	public static void main(String[] args) {
		launch(args);
	}
}


package solitaire.controller;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HighscoreController {
	
	@FXML
	VBox highscoreDisplay = new VBox();
	
	@FXML
	Button closeButton;
	
	@FXML
	public void exit() {
		stage.hide();
	}
	
	Stage stage;
	
	public void displayHighScores(ArrayList<Integer> arrlist) {
		if (arrlist.isEmpty()) {
			return;
		} else {
			for(int i = 0; i < 10; i++) {
				if (arrlist.get(i) == null) {
					return;
				}
				Label hs = new Label(String.valueOf(arrlist.get(i)));
				hs.setFont(new Font("Charter", 20));
				highscoreDisplay.getChildren().add(hs);
			}
		}
		
	}
	
	public void setStage(Stage s) {
		this.stage = s;
	}
	
}

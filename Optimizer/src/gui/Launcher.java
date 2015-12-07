package gui;

import code.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Launcher implements EventHandler<ActionEvent> {

	Stage primaryStage;
	String sport;
	TextField text;
	
	public Launcher(Stage primaryStage, String sport, TextField text) {
		this.primaryStage = primaryStage;
		this.sport = sport;
		this.text = text;
	}

	@Override
	public void handle(ActionEvent event) {
		Controller.load(text.getText());
		Controller.setLineup(sport);
		Window.mainScreen(primaryStage);
	}

}

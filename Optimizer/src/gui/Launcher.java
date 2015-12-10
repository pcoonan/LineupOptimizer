package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Launcher implements EventHandler<ActionEvent> {

	Stage primaryStage;
	String sport;
	TextField text;
	CheckBox cb;
	
	public Launcher(Stage primaryStage, String sport, TextField text, CheckBox cb) {
		this.primaryStage = primaryStage;
		this.sport = sport;
		this.text = text;
		this.cb = cb;
	}

	@Override
	public void handle(ActionEvent event) {
		if(cb.isSelected())
			Window.getControl().setServer();
		else
			Window.getControl().load(text.getText());
		Window.getControl().setLineup(sport);
		Window.mainScreen(primaryStage);
	}

}

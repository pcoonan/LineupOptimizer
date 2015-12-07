package listeners;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SelectHandler implements EventHandler<ActionEvent> {

	private TextField text;
	private Stage primaryStage;

	public SelectHandler(Stage primaryStage2, TextField text2) {
		text = text2;
		primaryStage = primaryStage2;
	}

	@Override
	public void handle(ActionEvent event) {
		final FileChooser fileChooser = new FileChooser();
		final File selectedDirectory = fileChooser.showOpenDialog(primaryStage);
		if (selectedDirectory != null) {
			text.setText(selectedDirectory.getAbsolutePath());
		}
	}
}

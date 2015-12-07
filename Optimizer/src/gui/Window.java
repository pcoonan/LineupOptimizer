package gui;

import java.util.ArrayList;

import code.Controller;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import listeners.SelectHandler;
import structures.Lineup;
import structures.Player;

public class Window extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Lineup Optimizer");
		loadScreen(primaryStage);
		primaryStage.show();
	}

	private static void loadScreen(Stage primaryStage) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Text title = new Text("Lineup Optimizer");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
		grid.add(title, 0, 0, 4, 1);
		
		Label dir = new Label("Directory:");
		grid.add(dir, 0, 1);
		
		TextField text = new TextField("C:\\Users\\Patrick\\Documents\\School\\CSE 442\\DKSalariesWeek13SM.csv");
		grid.add(text, 1, 1, 3, 1);
		
		Button sel = new Button("Select folder...");
		sel.setOnAction(new SelectHandler(primaryStage, text));
		grid.add(sel, 0, 2);
		
		Button nbalaunch = new Button("NBA");
		nbalaunch.setOnAction(new Launcher(primaryStage, "NBA", text));
		grid.add(nbalaunch, 1, 2);
		
		Button nfllaunch = new Button("NFL");
		nfllaunch.setOnAction(new Launcher(primaryStage, "NFL", text));
		grid.add(nfllaunch, 2, 2);
		
		Scene scene = new Scene(grid, 400, 300);
		primaryStage.setScene(scene);
	}
	
	public static void mainScreen(Stage primaryStage){
		VBox box = new VBox();
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(5, 0, 5, 0));
		grid.setVgap(4);
		grid.setHgap(4);
		grid.setStyle("-fx-background-color: DAE6F3;");
	    
		grid.add(currentLineup(), 0, 0);
		grid.add(playerList(), 1, 0);
		
		MenuBar menu = new MenuBar();
		Menu menuFile = new Menu("File");
		MenuItem home = new MenuItem("Home");
		home.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				loadScreen(primaryStage);
			}
		});
		MenuItem create = new MenuItem("Create New Lineup");
		create.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Controller.setLineup("NFL");
				mainScreen(primaryStage);
			}
		});
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});
		menuFile.getItems().addAll(home, create, exit);
		menu.getMenus().add(menuFile);
		box.getChildren().addAll(menu, grid);
		Scene scene = new Scene(box, 1200, 500);
		primaryStage.setScene(scene);
	}

	@SuppressWarnings("unchecked")
	private static TableView<Player> playerList() {
		TableView<Player> table = new TableView<Player>();
	    table.setEditable(true);
	    TableColumn<Player, String> nameCol = new TableColumn<Player, String>("Name");
	    TableColumn<Player, String> posCol = new TableColumn<Player, String>("Position");
	    TableColumn<Player, Double> salCol = new TableColumn<Player, Double>("Salary");
	    TableColumn<Player, Double> pointsCol = new TableColumn<Player, Double>("Points");
	    
	    ObservableList<Player> data = FXCollections.observableArrayList();
	    nameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
	    posCol.setCellValueFactory(new PropertyValueFactory<Player, String>("pos"));
	    salCol.setCellValueFactory(new PropertyValueFactory<Player, Double>("salary"));
	    pointsCol.setCellValueFactory(new PropertyValueFactory<Player, Double>("projection"));
	    
	    @SuppressWarnings("rawtypes")
		TableColumn col_action = new TableColumn<>("Action");
	    TableColumn include_action = new TableColumn<>("Include");
        col_action.setSortable(false);
        include_action.setSortable(false);
        
        include_action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Player, Boolean>, 
                ObservableValue<Boolean>>() {
 
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Player, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
 
        include_action.setCellFactory(
                new Callback<TableColumn<Player, Boolean>, TableCell<Player, Boolean>>() {
 
            @Override
            public TableCell<Player, Boolean> call(TableColumn<Player, Boolean> p) {
                return new ButtonCell(table, "Include");
            }
         
        });
        
        @SuppressWarnings("rawtypes")
		TableColumn exclude_action = new TableColumn<>("Exclude");
        exclude_action.setSortable(false);
         
        exclude_action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Player, Boolean>, 
                ObservableValue<Boolean>>() {
 
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Player, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
 
        exclude_action.setCellFactory(
                new Callback<TableColumn<Player, Boolean>, TableCell<Player, Boolean>>() {
 
            @Override
            public TableCell<Player, Boolean> call(TableColumn<Player, Boolean> p) {
                return new ButtonCell(table, "Exclude");
            }
         
        });
        col_action.getColumns().addAll(include_action, exclude_action);
		ArrayList<Player> players = Controller.getPlayers();
		for(Player p: players){
	    	data.add(p);
	    }
		table.setItems(data);
		table.getColumns().addAll(nameCol, posCol, salCol, pointsCol, col_action);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setMinWidth(600);
		return table;
	}

	@SuppressWarnings("unchecked")
	private static TableView<Player> currentLineup() {
	    TableView<Player> table = new TableView<Player>();
	    table.setEditable(true);
	    
	    TableColumn<Player, String> nameCol = new TableColumn<Player, String>("Name");
	    TableColumn<Player, String> posCol = new TableColumn<Player, String>("Position");
	    TableColumn<Player, Double> salCol = new TableColumn<Player, Double>("Salary");
	    TableColumn<Player, Double> pointsCol = new TableColumn<Player, Double>("Points");
	    
	    ObservableList<Player> data = FXCollections.observableArrayList();
	    nameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
	    posCol.setCellValueFactory(new PropertyValueFactory<Player, String>("pos"));
	    salCol.setCellValueFactory(new PropertyValueFactory<Player, Double>("salary"));
	    pointsCol.setCellValueFactory(new PropertyValueFactory<Player, Double>("projection"));
	    
		Lineup line = Controller.getLineup();
		ArrayList<Player> players = line.printLineup();
		for(Player p: players){
	    	data.add(p);
	    }
		table.setItems(data);
		table.getColumns().addAll(nameCol, posCol, salCol, pointsCol);
		table.setMinWidth(500);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		return table;
	}
	
	private static class ButtonCell extends TableCell<Player, Boolean> {
        Button cellButton;
         
        ButtonCell(TableView<Player> table, String type){
            cellButton = new Button(type);
            cellButton.setOnAction(new EventHandler<ActionEvent>(){
 
                @Override
                public void handle(ActionEvent t) {
                    int selected = getTableRow().getIndex();
                    Player p = (Player) table.getItems().get(selected);
                    if(type.equalsIgnoreCase("include"))
                    	Controller.include(p);
                    else
                    	Controller.exclude(p);
                }
            });
        }
 
        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }
        }
    }

}

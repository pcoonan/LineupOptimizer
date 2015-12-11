package code;

import java.util.ArrayList;

import structures.Player;

// The "main" class, all other classes will be accessed from here. This will be the GUI launchpoint.
// Maintained by Andy Cassidy

public class Test {

	public static void main(String[] args) {
		//javax.swing.SwingUtilities.invokeLater(new MainWindow());
		Controller control = new Controller();
		control.setServer();
		control.setLineup("NFL");
		ArrayList<Player> players = control.getLineup().printLineup();
		for(Player p: players){
			if(p == null){
				System.out.println("We've got a problem");
			}
			System.out.println(p.toString());
		}
	}

}

package structures;

import java.util.ArrayList;

public interface Lineup {

	boolean addPlayer(Player p);
	void removePlayer(Player p);
	double getScore();
	int getSalary();
	ArrayList<Player> printLineup();
	Player printPlayer(Player p);
	boolean lineupCreated();
	boolean contains(Player player);
}

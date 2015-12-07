package structures;

import java.util.ArrayList;
import java.util.HashMap;

public interface Lineup {

	boolean addPlayer(Player p);
	void removePlayer(Player p);
	double getScore();
	int getSalary();
	ArrayList<Player> printLineup();
	Player printPlayer(Player p);
	boolean lineupCreated();
	boolean contains(Player player);
	double getProjection();
	void checkBetter(ArrayList<Player> players, HashMap<Player,Boolean> included, HashMap<Player,Boolean> excluded);
	boolean forceAdd(Player p);
	boolean isFlex(Player p);
	void clear();
}

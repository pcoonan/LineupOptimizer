package code;

import java.util.ArrayList;
import java.util.HashMap;

import structures.Lineup;
import structures.Player;

public class Controller {

	private Lineup line;
	private ArrayList<Player> players = new ArrayList<Player>();
	private HashMap<Player, Boolean> excluded = new HashMap<Player, Boolean>();
	private HashMap<Player, Boolean> included = new HashMap<Player, Boolean>();

	public void load(String file) {
		setPlayers(Utils.readCSV(file));
		Utils.projectionScore(players);
	}

	public boolean include(Player p) {
		if (players.contains(p)) {
			included.put(p, true);
			excluded.put(p, false);
			return true;
		}
		return false;
	}

	public boolean exclude(Player p) {
		if (players.contains(p)) {
			excluded.put(p, true);
			included.put(p, false);
			return true;
		}
		return false;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
		for (Player p : players) {
			included.put(p, false);
			excluded.put(p, false);
		}
	}

	public Lineup getLineup() {
		return line;
	}

	public void setLineup(String sport) {
		this.line = Utils.createLineup(players, sport, included, excluded);
	}

}

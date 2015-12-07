package code;

import java.util.ArrayList;
import java.util.HashMap;

import structures.Lineup;
import structures.Player;

public class Controller {

	private static Lineup line;
	private static ArrayList<Player> players = new ArrayList<Player>();
	private static HashMap<Player, Boolean> excluded = new HashMap<Player, Boolean>();
	private static HashMap<Player, Boolean> included = new HashMap<Player, Boolean>();
	
	public static void load(String file){
		setPlayers(Utils.readCSV(file));
		Utils.projectionScore(players);
	}
	
	public static boolean include(Player p){
		if(players.contains(p)){
			included.put(p, true);
			excluded.put(p, false);
			return true;
		} else return false;
	}
	
	public static boolean exclude(Player p){
		if(players.contains(p)){
			excluded.put(p, true);
			included.put(p, false);
			return true;
		} return false;
	}
	public static ArrayList<Player> getPlayers() {
		return players;
	}
	public static void setPlayers(ArrayList<Player> players) {
		Controller.players = players;
		for(Player p: players){
			included.put(p, false);
			excluded.put(p, false);
		}
	}
	public static Lineup getLineup() {
		return line;
	}
	public static void setLineup(String sport) {
		Controller.line = Utils.createLineup(players, sport, included, excluded);
	}
	
	
}

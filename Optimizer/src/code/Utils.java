package code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

import structures.DST;
import structures.NFLLineup;
import structures.Player;
import structures.QB;
import structures.RB;
import structures.TE;
import structures.WR;

// The class where the algorithm will be held, as well as any other computational methods.
// Maintained by Patrick Coonan.

public class Utils {

	private static Queue<Player> ordering = new PriorityQueue<Player>();
	private static HashMap<Player, Boolean> forbidden = new HashMap<Player, Boolean>();
	
	public static void main(String[] args) {
		// This is for testing on Patrick's computer - should not be executed as part of the program,
		// hence the local file path.
		ArrayList<Player> players = readCSV("C:\\Users\\Patrick\\Documents\\School\\CSE 442\\DKSalariesWeek9.csv");//"CSV\\DraftKings\\NFLDKSalaries.csv");
		
//		toFile(players);
//		createLineup(players);
		for(Player p: players){
			p.setPPG(0.0);
		}
		projectionScore(players, Scraper.GetTable("QB"));
		projectionScore(players, Scraper.GetTable("RB"));
		projectionScore(players, Scraper.GetTable("WR"));
		projectionScore(players, Scraper.GetTable("TE"));
		projectionScore(players, Scraper.GetTable("D/ST"));
		players.sort(new PlayerComparator());
		createLineup(players);
	}
	
	public static ArrayList<Player> readCSV(String file){
		ArrayList<Player> players = new ArrayList<Player>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(file)));
			String line = in.readLine();
			line = in.readLine();
			while(line != null){
				String[] tuple = line.split(",");
				String pos = tuple[0].substring(1, tuple[0].length()-1);
				String name = tuple[1].substring(1, tuple[1].length()-1);;
				double sal = Double.parseDouble(tuple[2]);
				double ppg = Double.parseDouble(tuple[4]);
				switch(pos){
				case "QB":
					players.add(new QB(name, pos, ppg, sal));
					break;
				case "RB":
					players.add(new RB(name, pos, ppg, sal));
					break;
				case "WR":
					players.add(new WR(name, pos, ppg, sal));
					break;
				case "TE":
					players.add(new TE(name, pos, ppg, sal));
					break;
				case "DST":
					players.add(new DST(name, pos, ppg, sal));
					break;
				}
				line = in.readLine();
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return players;
	}
	/**
	 * Algo Idea:
	 * 
	 * Creates a lineup given a sorted list of players based on PPG. Then iterates
	 * through this list and adds players to the available slots if there is 
	 * salary available. When finished, if there are any open slots, the highest
	 * salaried player is removed and placed on the forbidden list. This is repeated
	 * until a valid lineup is created.
	 */
	
	public static ArrayList<Player> createLineup(ArrayList<Player> players) {
		NFLLineup lineup = new NFLLineup();
		for(Player p : players){
			forbidden.put(p, false);
		}
		while(!lineup.lineupCreated()){
			for(Player p: players){
				if(forbidden.get(p)) continue;
				if(lineup.addPlayer(p)){
					ordering.add(p);
				}
			}
			if(lineup.lineupCreated()) break;
			else{
				Player temp = ordering.remove();
				forbidden.put(temp, true);
				lineup.removePlayer(temp);
			}
		}
		return lineup.printLineup();
	}
	
	@SuppressWarnings("unused")
	private static void toFile(ArrayList<Player> players) {
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(new File("out.txt")));
			for(Player p: players){
				out.write(p.toString());
				out.newLine();
			}
			out.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Prints the given players info to the console/returns the player. 
	 * 
	 * @param p - player to be printed.
	 * @return - given player, can be used for adding to output list
	 */
	
	public static ArrayList<Player> projectionScore(ArrayList<Player> players, String[][] projections) {
		return Scoring.rescorePlayers(players, projections);
	}
	
}

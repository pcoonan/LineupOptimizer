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

import structures.C;
import structures.DST;
import structures.Lineup;
import structures.NBALineup;
import structures.NFLLineup;
import structures.PF;
import structures.PG;
import structures.Player;
import structures.QB;
import structures.RB;
import structures.SF;
import structures.SG;
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
		ArrayList<Player> players = readCSV("C:\\Users\\Patrick\\Documents\\School\\CSE 442\\DKSalariesWeek9SM.csv");//"CSV\\DraftKings\\NFLDKSalaries.csv");
		
//		toFile(players);
//		createLineup(players);
//		for(Player p: players){
//			p.setPPG(0.0);
//		}
//		projectionScore(players);
//		players.sort(new PlayerComparator());
//		System.out.println(createLineup(players, "NBA").getScore());
		Lineup l = alternativeLineup(players, "NFL");
		l.printLineup();
		System.out.println(l.getScore());
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
				case "PG":
					players.add(new PG(name, pos, ppg, sal));
					break;
				case "SG":
					players.add(new SG(name, pos, ppg, sal));
					break;
				case "SF":
					players.add(new SF(name, pos, ppg, sal));
					break;
				case "PF":
					players.add(new PF(name, pos, ppg, sal));
					break;
				case "C":
					players.add(new C(name, pos, ppg, sal));
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
	
	public static Lineup createLineup(ArrayList<Player> players, String sport) {
		Lineup lineup = null;
		switch(sport){
		case "NFL":
			projectionScore(players);
			lineup = new NFLLineup(players);
			break;
		case "NBA":
			lineup = new NBALineup();
		}
		players.sort(new PlayerComparator());
		for(Player p : players){
			forbidden.put(p, false);
			if(p.getName().equals("Thabo Sefolosha") || p.getName().equals("Andrew Bogut")) forbidden.put(p, true);
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
		lineup.checkBetter(players);
		return lineup;
	}
	
	public static Lineup alternativeLineup(ArrayList<Player> players, String sport){
		Player kill = null;
		for(Player p : players){
			if(p.getName().equals("Andrew Bogut")){
				kill = p;
			}
		}
		if(kill != null) players.remove(kill);
		Lineup lineup = null;
		switch(sport){
		case "NFL":
			projectionScore(players);
			lineup = new NFLLineup(players);
			break;
		case "NBA":
			lineup = new NBALineup();
		}
		Queue<Player> order = new PriorityQueue<Player>();
		players.sort(new PlayerComparator());
		
		int balance = -1;
		while (balance < 0) {
			for (Player p : players){
				if(lineup.forceAdd(p))
					order.add(p);
			}
			balance = lineup.getSalary();
			if(balance < 0){
				Player temp = order.remove();
				lineup.removePlayer(temp);
				players.remove(temp);
			}
		}
		lineup.checkBetter(players);
		lineup.checkBetter(players);
		return lineup;
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
	
	public static ArrayList<Player> projectionScore(ArrayList<Player> players) {
//		for(Player p: players){
//			p.setPPG(0.0);
//		}
		Scoring.rescorePlayers(players, Scraper.GetTable("QB"));
		Scoring.rescorePlayers(players, Scraper.GetTable("RB"));
		Scoring.rescorePlayers(players, Scraper.GetTable("WR"));
		Scoring.rescorePlayers(players, Scraper.GetTable("TE"));
		Scoring.rescorePlayers(players, Scraper.GetTable("D/ST"));
		return players;
	}
	
}

package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

// The class where the algorithm will be held, as well as any other computational methods.
// Maintained by Patrick Coonan.

public class Utils {

	private static Player qb = null;
	private static Player rb1 = null;
	private static Player rb2 = null;
	private static Player wr1 = null;
	private static Player wr2 = null;
	private static Player wr3 = null;
	private static Player te = null;
	private static Player flex = null;
	private static Player dst = null;
	private static Stack<Player> lineup = new Stack<Player>();
	private static HashMap<Player, Boolean> forbidden = new HashMap<Player, Boolean>();
	private static double salary = 50000;
	
	public static void main(String[] args) {
//		ArrayList<Player> players = new ArrayList<Player>();
//		try {
//			BufferedReader in = new BufferedReader(new FileReader(new File("CSV\\DraftKings\\NFLDKSalaries.csv")));
//			String line = in.readLine();
//			line = in.readLine();
//			while(line != null){
//				String[] tuple = line.split(",");
//				String pos = tuple[0].substring(1, tuple[0].length()-1);
//				String name = tuple[1].substring(1, tuple[1].length()-1);;
//				double sal = Double.parseDouble(tuple[2]);
//				double ppg = Double.parseDouble(tuple[4]);
//				players.add(new Player(name, pos, ppg, sal));
//				line = in.readLine();
//			}
//			in.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		createLineup(readCSV("CSV\\DraftKings\\NFLDKSalaries.csv"));
	}
	
	public static ArrayList<Player> readCSV(String file){
		ArrayList<Player> players = new ArrayList<Player>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File("CSV\\DraftKings\\NFLDKSalaries.csv")));
			String line = in.readLine();
			line = in.readLine();
			while(line != null){
				String[] tuple = line.split(",");
				String pos = tuple[0].substring(1, tuple[0].length()-1);
				String name = tuple[1].substring(1, tuple[1].length()-1);;
				double sal = Double.parseDouble(tuple[2]);
				double ppg = Double.parseDouble(tuple[4]);
				players.add(new Player(name, pos, ppg, sal));
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
	 * Creates a lineup given an unsorted list of players. Calls a merge sort on this 
	 * list, ordering from players of highest to lowest "value". Runs through this list 
	 * filling positions as available and if salary permits. If run does not output a
	 * complete lineup, the latest player is removed and added to a forbidden list. The
	 * algo is run again until either a full lineup is created or no players can be added
	 * because of the forbidden list. The forbidden list is then cleared and the latest player
	 * is removed.
	 */
	
	public static ArrayList<Player> createLineup(ArrayList<Player> players) {
		sort(players);
		
		boolean newIt = false;
		for(Player p : players){
			forbidden.put(p, false);
		}
		while(!lineupCreated()){
			if(!lineup.isEmpty() && !newIt){
				Player temp = lineup.pop();
				System.out.println(temp.name);
				forbidden.put(temp, true);
				salary += temp.sal;
				remove(temp);
				newIt = false;
				System.out.println(salary);
			}
			System.out.println(salary + " " + lineupCreated());
			int stackSize = lineup.size();
			findNextPlayer(players, 0);
			newIt = false;
			if(stackSize == lineup.size() && !lineupCreated()){
				for(Player p : players){
					forbidden.put(p, false);
				}
				Player temp = lineup.pop();
				forbidden.put(temp, true);
				salary += temp.sal;
				remove(temp);
				newIt = true;
			}
		}
		return printLineup();
	}
	
	/**
	 * Removes a player from our temporary lineup.
	 * 
	 * @param temp - player to remove
	 */
	
	private static void remove(Player temp) {
		if(temp.equals(qb))
			qb = null;
		else if(temp.equals(rb1))
			rb1 = null;
		else if(temp.equals(rb2))
			rb2 = null;
		else if(temp.equals(wr1))
			wr1 = null;
		else if(temp.equals(wr2))
			wr2 = null;
		else if(temp.equals(wr3))
			wr3 = null;
		else if(temp.equals(te))
			te = null;
		else if(temp.equals(flex))
			flex = null;
		else if(temp.equals(dst))
			dst = null;
	}

	/**
	 * Given the list of players and the current index to look at, this checks to
	 * see if the player at the index can be added based on salary and position
	 * availability.
	 * 
	 * @param players - list of players
	 * @param i - current index
	 */
	
	private static void findNextPlayer(ArrayList<Player> players, int i) {
		if(i >= players.size()){
			return;
		}
		if(forbidden.get(players.get(i)) == true){
			findNextPlayer(players, i+1);
		}
		if(players.get(i).sal < salary){	// Can add player
			if(players.get(i).pos.compareTo("QB") == 0){
				if(qb == null){
					qb = players.get(i);
					lineup.push(players.get(i));
					salary -= players.get(i).sal;
					findNextPlayer(players, i+1);
				}
				else{
					findNextPlayer(players, i+1);
				}
			}
			else if(players.get(i).pos.compareTo("RB") == 0){
				if(rb1 == null){
					rb1 = players.get(i);
					lineup.push(players.get(i));
					salary -= players.get(i).sal;
					findNextPlayer(players, i+1);
				}
				else if(rb2 == null){
					rb2 = players.get(i);
					lineup.push(players.get(i));
					salary -= players.get(i).sal;
					findNextPlayer(players, i+1);
				}
				else if(flex == null){
					flex = players.get(i);
					lineup.push(players.get(i));
					salary -= players.get(i).sal;
					findNextPlayer(players, i+1);
				}
				else{
					findNextPlayer(players, i+1);
				}
			}
			else if(players.get(i).pos.compareTo("WR") == 0){
				if(wr1 == null){
					wr1 = players.get(i);
					lineup.push(players.get(i));
					salary -= players.get(i).sal;
					findNextPlayer(players, i+1);
				}
				else if(wr2 == null){
					wr2 = players.get(i);
					lineup.push(players.get(i));
					salary -= players.get(i).sal;
					findNextPlayer(players, i+1);
				}
				else if(wr3 == null){
					wr3 = players.get(i);
					lineup.push(players.get(i));
					salary -= players.get(i).sal;
					findNextPlayer(players, i+1);
				}
				else if(flex == null){
					flex = players.get(i);
					lineup.push(players.get(i));
					salary -= players.get(i).sal;
					findNextPlayer(players, i+1);
				}
				else{
					findNextPlayer(players, i+1);
				}
			}
			else if(players.get(i).pos.compareTo("TE") == 0){
				if(te == null){
					te = players.get(i);
					lineup.push(players.get(i));
					salary -= players.get(i).sal;
					findNextPlayer(players, i+1);
				}
				else if(flex == null){
					flex = players.get(i);
					lineup.push(players.get(i));
					salary -= players.get(i).sal;
					findNextPlayer(players, i+1);
				}
				else{
					findNextPlayer(players, i+1);
				}
			}
			else if(players.get(i).pos.compareTo("DST") == 0){
				if(dst == null){
					dst = players.get(i);
					lineup.push(players.get(i));
					salary -= players.get(i).sal;
					findNextPlayer(players, i+1);
				}
				else{
					findNextPlayer(players, i+1);
				}
			}
		}
		else{ // Can't add player
			findNextPlayer(players, i+1);
		}
	}

	/**
	 * Prints the given players info to the console/returns the player. 
	 * 
	 * @param p - player to be printed.
	 * @return - given player, can be used for adding to output list
	 */
	
	public static Player printPlayer(Player p){
		if(p == null){
			System.out.println("null");
		}
		else{
			System.out.println(p.name + " " + p.pos + " " + p.sal + " " + p.ppg);
		}
		return p;
	}
	
	/**
	 * A merge sort based on player value.
	 * 
	 * @param list - unsorted list of players
	 */
	
	private static void sort(ArrayList<Player> list) { // Pretty straightforward merge sort
		if (list.size() > 1) {
			int half = list.size() / 2;
			ArrayList<Player> first = new ArrayList<Player>(list.subList(0, half));
			ArrayList<Player> second = new ArrayList<Player>(list.subList(half, list.size()));

			sort(first);
			sort(second);
			int i = 0;
			int j = 0;
			int loc = 0;
			list.clear();
			while (i != first.size() || j != second.size()) {
				if (i != first.size() && j != second.size()) {
					if (first.get(i).value >= second.get(j).value) {
						list.add(loc, first.get(i));
						i++;
						loc++;
					} else {
						list.add(loc, second.get(j));
						j++;
						loc++;
					}
				} else {
					// Put the rest of the other array here
					if (i == first.size()) {
						while (j != second.size()) {
							list.add(loc, second.get(j));
							j++;
							loc++;
						}
					} else {
						while (i != first.size()) {
							list.add(loc, first.get(i));
							i++;
							loc++;
						}
					}
				}
			}
		} else {
			return;
		}
	}

	public ArrayList<Player> projectionScore(ArrayList<Player> players, String[][] projections) {
		return null;
	}
	
	/**
	 * Returns if the lineup has been created.
	 * @return - boolean value of if there is a lineup created
	 */
	
	private static boolean lineupCreated(){
		return qb != null && rb1 != null && rb2 != null && wr1 != null
				&& wr2 != null && wr3 != null && te != null && flex != null
				&& dst != null;
	}
	
	/**
	 * Prints out the projected score of a given lineup.
	 */
	
	private static void scoreLineup() {
		double points = 0;
		if(qb != null){
			points += qb.ppg;
		}
		if(rb1 != null){
			points += rb1.ppg;
		}
		if(rb2 != null){
			points += rb2.ppg;
		}
		if(wr1 != null){
			points += wr1.ppg;
		}
		if(wr2 != null){
			points += wr2.ppg;
		}
		if(wr3 != null){
			points += wr3.ppg;
		}
		if(te != null){
			points += te.ppg;
		}
		if(flex != null){
			points += flex.ppg;
		}
		if(dst != null){
			points += dst.ppg;
		}
		System.out.println(points);
	}
	/**
	 * Prints out the lineup and returns an ArrayList of the lineup.
	 * @return - ArrayList of the lineup
	 */
	
	private static ArrayList<Player> printLineup() {
		ArrayList<Player> out = new ArrayList<Player>();
		out.add(printPlayer(qb));
		out.add(printPlayer(rb1));
		out.add(printPlayer(rb2));
		out.add(printPlayer(wr1));
		out.add(printPlayer(wr2));
		out.add(printPlayer(wr3));
		out.add(printPlayer(te));
		out.add(printPlayer(flex));
		out.add(printPlayer(dst));
		scoreLineup();
		return out;
	}
}

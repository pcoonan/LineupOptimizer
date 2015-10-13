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
//		printPlayer(players.get(0));
		createLineup(players);
	}
	
	public static void createLineup(ArrayList<Player> players) {
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
				newIt = true;
			}
		}
		System.out.println(lineupCreated());
		printLineup();
	}
	
	private static void findNextPlayer(ArrayList<Player> players, int i) {
		if(i >= players.size()){
			return;
		}
		if(forbidden.get(players.get(i)) == true){
			findNextPlayer(players, i+1);
		}
		Player p = players.get(i);
		String pos = p.pos;
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
//			System.out.println("Too large a salary");
			findNextPlayer(players, i+1);
		}
	}

	public static void printPlayer(Player p){
		if(p == null){
			System.out.println("null");
		}
		else{
			System.out.println(p.name + " " + p.pos + " " + p.sal + " " + p.ppg);
		}
	}
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
	private static boolean lineupCreated(){
		return qb != null && rb1 != null && rb2 != null && wr1 != null
				&& wr2 != null && wr3 != null && te != null && flex != null
				&& dst != null;
	}
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
	private static void printLineup() {
		printPlayer(qb);
		printPlayer(rb1);
		printPlayer(rb2);
		printPlayer(wr1);
		printPlayer(wr2);
		printPlayer(wr3);
		printPlayer(te);
		printPlayer(flex);
		printPlayer(dst);
		scoreLineup();
	}
}

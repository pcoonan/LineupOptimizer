package code;

import java.util.ArrayList;
import java.util.HashMap;

import structures.Player;

public class Scoring {

	final int PYD = 4;
	final int PTD = 5;
	final int INT = 6;
	final int RUSYD = 8;
	final int RUSTD = 9;
	final int REC = 10;
	final int RECYD = 11;
	final int RECTD = 12;
	final static int PROJ = 13;
	private static Scoring scoring;
	private static HashMap<Integer, Double> rules;

	private Scoring() {
		rules = new HashMap<Integer, Double>();
		rules.put(PYD, 0.04);
		rules.put(PTD, 4.0);
		rules.put(INT, -1.0);
		rules.put(RUSYD, 0.1);
		rules.put(RUSTD, 6.0);
		rules.put(REC, 1.0);
		rules.put(RECYD, 0.1);
		rules.put(RECTD, 6.0);
	}

	public static ArrayList<Player> rescorePlayers(ArrayList<Player> players, String[][] projections, String position) {
		if (scoring == null) {
			scoring = new Scoring();
		}
		String pos = "";
		HashMap<String, Double> scores = new HashMap<String, Double>();
		for (int i = 0; i < projections.length; i++) {
			if (projections[i][0] == null)
				break; // Signifies the end of the players
			if (projections[i][1].contains("BYE"))
				break;
			double score = 0;

			String[] temp = projections[i][0].split(",");
			String name = temp[0];
//			temp = temp[1].split(" ");
//			pos = temp[1];
			if (!projections[i][1].contains("BYE")) {
				for (int k = 0; k < projections[i].length; k++) {
					// System.out.print(k + ": ");
					if (projections[i][k] != null) { // Signifies the end of the
														// attributes
//						System.out.println(projections[i][k] + " ");
						if (rules.containsKey((Integer) k)) {
							score += Double.parseDouble(projections[i][k]) * rules.get(k);
						}
					} else {
						break;
					}
				}
				if (score == 0)
					score = Double.parseDouble(projections[i][PROJ]);
			}
			if(name.contains("D/ST")){
				String[] tname = name.split(" ");
				name = tname[0] + " ";
			}
			scores.put(name, score);
//			System.out.println(name + " " + score);
		}
		updateScore(scores, pos, players, position);
		
		return players;
	}

	private static void updateScore(HashMap<String, Double> scores, String pos, ArrayList<Player> players, String position) {
		for (Player p : players) {
			if (scores.containsKey(p.getName()) && p.getPosition().equals(position)) {
//				System.out.println(scores.get(p.getName()));
				p.setProjection(scores.get(p.getName()));
//				System.out.println(p.getName() + " " + p.getProjection());
			}
		}
		
	}

}

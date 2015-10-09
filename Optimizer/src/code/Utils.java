package code;

import java.util.ArrayList;

// The class where the algorithm will be held, as well as any other computational methods.
// Maintained by Patrick Coonan.

public class Utils {

	public ArrayList<Player> createLineup(ArrayList<Player> players) {
		sort(players);
		return null;
	}

	private void sort(ArrayList<Player> list) { // Pretty straightforward merge sort
		if (list.size() > 1) {
			int half = list.size() / 2;
			ArrayList<Player> first = new ArrayList<Player>(list.subList(0, half));
			ArrayList<Player> second = new ArrayList<Player>(list.subList(half, list.size()));

			sort(first);
			sort(second);
			System.out.println(first.toString() + " " + second.toString());
			int i = 0;
			int j = 0;
			int loc = 0;
			list.clear();
			System.out.println(first.size() + " " + second.size());
			while (i != first.size() || j != second.size()) {
				if (i != first.size() && j != second.size()) {
					if (first.get(i).value <= second.get(j).value) {
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
}

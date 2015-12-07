package code;

import java.util.Comparator;

import structures.Player;

public class PlayerComparator implements Comparator<Player> {

	@Override
	public int compare(Player arg0, Player arg1) {
		final int BEFORE = -1;
		final int AFTER = 1;
		final int EQUAL = 0;
		
		if(arg0.getProjection() < arg1.getProjection()) return AFTER;
		if(arg0.getProjection() > arg1.getProjection()) return BEFORE;
		return EQUAL;
	}

}

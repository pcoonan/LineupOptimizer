package structures;

import java.util.ArrayList;
import java.util.HashMap;

public class NFLLineup implements Lineup {

	private static QB qb = null;
	private static RB rb1 = null;
	private static RB rb2 = null;
	private static WR wr1 = null;
	private static WR wr2 = null;
	private static WR wr3 = null;
	private static TE te = null;
	private static Player flex = null;
	private static DST dst = null;
	private static int salary = 50000;
	private static double score = 0;
	private static double projscore = 0;
	private static boolean projected = false;

	public NFLLineup(ArrayList<Player> players) {
		for (Player p : players) {
			if (p.getProjection() > 0) {
				projected = true;
				break;
			}
		}
	}

	public NFLLineup() {
		projected = true;
	}

	@Override
	public boolean addPlayer(Player p) {
		if (p.getSalary() > salary || contains(p) || (projected && p.getProjection() == 0))
			return false;
		if (p instanceof QB) {
			if (qb == null) {
				qb = (QB) p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			} else {
				return false;
			}
		} else if (p instanceof RB) {
			if (rb1 == null) {
				rb1 = (RB) p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			} else if (rb2 == null) {
				rb2 = (RB) p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			} else if (flex == null) {
				flex = p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			} else {
				return false;
			}
		} else if (p instanceof WR) {
			if (wr1 == null) {
				wr1 = (WR) p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			} else if (wr2 == null) {
				wr2 = (WR) p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			} else if (wr3 == null) {
				wr3 = (WR) p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			} else if (flex == null) {
				flex = p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			} else {
				return false;
			}
		} else if (p instanceof TE) {
			if (te == null) {
				te = (TE) p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			} else if (flex == null) {
				flex = p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			} else {
				return false;
			}
		} else if (p instanceof DST) {
			if (dst == null) {
				dst = (DST) p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public void removePlayer(Player p) {
		if (p.equals(qb))
			qb = null;
		else if (p.equals(rb1))
			rb1 = null;
		else if (p.equals(rb2))
			rb2 = null;
		else if (p.equals(wr1))
			wr1 = null;
		else if (p.equals(wr2))
			wr2 = null;
		else if (p.equals(wr3))
			wr3 = null;
		else if (p.equals(te))
			te = null;
		else if (p.equals(flex))
			flex = null;
		else if (p.equals(dst))
			dst = null;
		salary += p.getSalary();
		score -= p.getPPG();
		projscore -= p.getProjection();
	}

	@Override
	public double getScore() {
		return score;
	}

	@Override
	public int getSalary() {
		return salary;
	}

	@Override
	public ArrayList<Player> printLineup() {
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
		return out;
	}

	public boolean lineupCreated() {
		return qb != null && rb1 != null && rb2 != null && wr1 != null && wr2 != null && wr3 != null && te != null
				&& flex != null && dst != null;
	}

	@Override
	public Player printPlayer(Player p) {
		if (p == null) {
			System.out.println("null");
		} else {
			System.out.println(p.getName() + " " + p.getPosition() + " " + p.getSalary() + " " + p.getProjection());
		}
		return p;
	}

	@Override
	public boolean contains(Player player) {
		if (player.equals(qb))
			return true;
		else if (player.equals(rb1))
			return true;
		else if (player.equals(rb2))
			return true;
		else if (player.equals(wr1))
			return true;
		else if (player.equals(wr2))
			return true;
		else if (player.equals(wr3))
			return true;
		else if (player.equals(te))
			return true;
		else if (player.equals(flex))
			return true;
		else if (player.equals(dst))
			return true;
		return false;
	}

	@Override
	public double getProjection() {
		return projscore;
	}

	@Override
	public void checkBetter(ArrayList<Player> players, HashMap<Player,Boolean> included, HashMap<Player,Boolean> excluded) {
		Player better = null;
		Player worse = null;
		double points = 0;
		for (Player p : players) {
			if (contains(p) || excluded.get(p))
				continue;
			if (p instanceof QB) {
				if (p.getProjection() - qb.getProjection() > points && (p.getSalary() - qb.getSalary()) <= salary && !included.get(qb)) { // And
																														// can
																														// add
					better = p;
					worse = qb;
					points = p.getProjection() - qb.getProjection();
				}
			} else if (p instanceof RB) {
				if (p.getProjection() - rb1.getProjection() > points && (p.getSalary() - rb1.getSalary()) <= salary && !included.get(rb1)) { // And
																														// can
																														// add
					better = p;
					worse = rb1;
					points = p.getProjection() - rb1.getProjection();
				} else if (p.getProjection() - rb2.getProjection() > points
						&& (p.getSalary() - rb2.getSalary()) <= salary && !included.get(rb2)) { // And
																			// can
																			// add
					better = p;
					worse = rb2;
					points = p.getProjection() - rb2.getProjection();
				} else if (p.getProjection() - flex.getProjection() > points
						&& (p.getSalary() - flex.getSalary()) <= salary && !included.get(flex)) { // And
																			// can
																			// add
					better = p;
					worse = flex;
					points = p.getProjection() - flex.getProjection();
				}
			} else if (p instanceof WR) {
				if (p.getProjection() - wr1.getProjection() > points && (p.getSalary() - wr1.getSalary()) <= salary && !included.get(wr1)) { // And
																														// can
																														// add
					better = p;
					worse = wr1;
					points = p.getProjection() - wr1.getProjection();
				} else if (p.getProjection() - wr2.getProjection() > points
						&& (p.getSalary() - wr2.getSalary()) <= salary && !included.get(wr2)) { // And
																			// can
																			// add
					better = p;
					worse = wr2;
					points = p.getProjection() - wr2.getProjection();
				} else if (p.getProjection() - wr3.getProjection() > points
						&& (p.getSalary() - wr3.getSalary()) <= salary && !included.get(wr3)) { // And
																			// can
																			// add
					better = p;
					worse = wr3;
					points = p.getProjection() - wr3.getProjection();
				} else if (p.getProjection() - flex.getProjection() > points
						&& (p.getSalary() - flex.getSalary()) <= salary && !included.get(flex)) { // And
																			// can
																			// add
					better = p;
					worse = flex;
					points = p.getProjection() - flex.getProjection();
				}
			} else if (p instanceof TE) {
				if (p.getProjection() - te.getProjection() > points && (p.getSalary() - te.getSalary()) <= salary && !included.get(te)) { // And
																														// can
																														// add
					better = p;
					worse = te;
					points = p.getProjection() - te.getProjection();
				} else if (p.getProjection() - flex.getProjection() > points
						&& (p.getSalary() - flex.getSalary()) <= salary && !included.get(flex)) { // And
																			// can
																			// add
					better = p;
					worse = flex;
					points = p.getProjection() - flex.getProjection();
				}
			} else if (p instanceof DST) {
				if (p.getProjection() - dst.getProjection() > points && (p.getSalary() - dst.getSalary()) <= salary && !included.get(dst)) { // And
																														// can
																														// add
					better = p;
					worse = dst;
					points = p.getProjection() - dst.getProjection();
				}
			}
		}
		if (better != null && worse != null) {
			removePlayer(worse);
			addPlayer(better);
		} else
			System.out.println("No better options");
	}

	@Override
	public boolean forceAdd(Player p) {
		if (contains(p))
			return false;
		if (p instanceof QB) {
			if (qb == null) {
				qb = (QB) p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			}
		} else if (p instanceof RB) {
			if (rb1 == null) {
				rb1 = (RB) p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			} else if (rb2 == null) {
				rb2 = (RB) p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			} else if (flex == null) {
				flex = p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			}
		} else if (p instanceof WR) {
			if (wr1 == null) {
				wr1 = (WR) p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			} else if (wr2 == null) {
				wr2 = (WR) p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			} else if (wr3 == null) {
				wr3 = (WR) p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			} else if (flex == null) {
				flex = p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			}
		} else if (p instanceof TE) {
			if (te == null) {
				te = (TE) p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			} else if (flex == null) {
				flex = p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			}
		} else if (p instanceof DST) {
			if (dst == null) {
				dst = (DST) p;
				salary -= p.getSalary();
				score += p.getPPG();
				projscore += p.getProjection();
				return true;
			}
		}
		return false;

	}

	@Override
	public boolean isFlex(Player p) {
		return p.equals(flex);
	}

	@Override
	public void clear() {
		if (qb != null)
			removePlayer(qb);
		if (rb1 != null)
			removePlayer(rb1);
		if (rb2 != null)
			removePlayer(rb2);
		if (wr1 != null)
			removePlayer(wr1);
		if (wr2 != null)
			removePlayer(wr2);
		if (wr2 != null)
			removePlayer(wr2);
		if (wr3 != null)
			removePlayer(wr3);
		if (te != null)
			removePlayer(te);
		if (flex != null)
			removePlayer(flex);
		if (flex != null)
			removePlayer(flex);
	}

}

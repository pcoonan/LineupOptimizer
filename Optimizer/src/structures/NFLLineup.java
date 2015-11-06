package structures;

import java.util.ArrayList;

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
	private static boolean projected = false;
	
	public NFLLineup(ArrayList<Player> players) {
		for(Player p: players){
			if(p.getProjection() > 0){
				projected = true;
				break;
			}
		}
	}
	@Override
	public boolean addPlayer(Player p) {
		if(p.getSalary() > salary || contains(p) || (projected && p.getProjection() == 0)) return false;
		if(p instanceof QB){
			if(qb == null){
				qb = (QB) p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else{
				return false;
			}
		}
		else if(p instanceof RB){
			if(rb1 == null){
				rb1 = (RB) p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(rb2 == null){
				rb2 = (RB) p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(flex == null){
				flex = p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else{
				return false;
			}
		}
		else if(p instanceof WR){
			if(wr1 == null){
				wr1 = (WR) p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(wr2 == null){
				wr2 = (WR) p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(wr3 == null){
				wr3 = (WR) p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(flex == null){
				flex = p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else{
				return false;
			}
		}
		else if(p instanceof TE){
			if(te == null){
				te = (TE) p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(flex == null){
				flex = p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else{
				return false;
			}
		}
		else if(p instanceof DST){
			if(dst == null){
				dst = (DST) p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}
	@Override
	public void removePlayer(Player p) {
		if(p.equals(qb))
			qb = null;
		else if(p.equals(rb1))
			rb1 = null;
		else if(p.equals(rb2))
			rb2 = null;
		else if(p.equals(wr1))
			wr1 = null;
		else if(p.equals(wr2))
			wr2 = null;
		else if(p.equals(wr3))
			wr3 = null;
		else if(p.equals(te))
			te = null;
		else if(p.equals(flex))
			flex = null;
		else if(p.equals(dst))
			dst = null;
		salary += p.getSalary();
		score -= p.getPPG();
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
		return qb != null && rb1 != null && rb2 != null && wr1 != null
				&& wr2 != null && wr3 != null && te != null && flex != null
				&& dst != null;
	}
	
	@Override
	public Player printPlayer(Player p) {
		if(p == null){
			System.out.println("null");
		}
		else{
			System.out.println(p.getName() + " " + p.getPosition() + " " + p.getSalary() + " " + p.getProjection());
		}
		return p;
	}
	@Override
	public boolean contains(Player player) {
		if(player.equals(qb))
			return true;
		else if(player.equals(rb1))
			return true;
		else if(player.equals(rb2))
			return true;
		else if(player.equals(wr1))
			return true;
		else if(player.equals(wr2))
			return true;
		else if(player.equals(wr3))
			return true;
		else if(player.equals(te))
			return true;
		else if(player.equals(flex))
			return true;
		else if(player.equals(dst))
			return true;
		return false;
	}
	
	
}

package structures;

import java.util.ArrayList;

public class NBALineup implements Lineup {

	private static PG pg = null;
	private static SG sg = null;
	private static SF sf = null;
	private static PF pf = null;
	private static C c = null;
	private static Player g = null;
	private static Player f = null;
	private static Player util = null;
	private static int salary = 50000;
	private static double score = 0;
	
	@Override
	public boolean addPlayer(Player p) {
		if(p.getSalary() >= salary || contains(p)) return false;
		if(p instanceof PG){
			if(pg == null){
				pg = (PG) p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(g == null){
				g = p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(util == null){
				util = p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else{
				return false;
			}
		}
		else if(p instanceof SG){
			if(sg == null){
				sg = (SG) p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(g == null){
				g = p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(util == null){
				util = p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else{
				return false;
			}
		}
		else if(p instanceof SF){
			if(sf == null){
				sf = (SF) p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(f == null){
				f = p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(util == null){
				util = p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else{
				return false;
			}
		}
		else if(p instanceof PF){
			if(pf == null){
				pf = (PF) p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(f == null){
				f = p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(util == null){
				util = p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else{
				return false;
			}
		}
		else if(p instanceof C){
			if(c == null){
				c = (C) p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(util == null){
				util = p;
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
		if(p.equals(pg))
			pg = null;
		else if(p.equals(sg))
			sg = null;
		else if(p.equals(sf))
			sf = null;
		else if(p.equals(pf))
			pf = null;
		else if(p.equals(c))
			c = null;
		else if(p.equals(g))
			g = null;
		else if(p.equals(f))
			f = null;
		else if(p.equals(util))
			util = null;
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
		out.add(printPlayer(pg));
		out.add(printPlayer(sg));
		out.add(printPlayer(sf));
		out.add(printPlayer(pf));
		out.add(printPlayer(c));
		out.add(printPlayer(g));
		out.add(printPlayer(f));
		out.add(printPlayer(util));
		return out;
	}

	@Override
	public Player printPlayer(Player p) {
		if(p == null){
			System.out.println("null");
		}
		else{
			System.out.println(p.getName() + " " + p.getPosition() + " " + p.getSalary() + " " + p.getPPG());
		}
		return p;
	}

	@Override
	public boolean lineupCreated() {
		return pg != null && sg != null && g != null && sf != null
				&& f != null && pf != null && util != null
				&& c != null;
	}

	@Override
	public boolean contains(Player player) {
		if(player.equals(pg))
			return true;
		else if(player.equals(sg))
			return true;
		else if(player.equals(sf))
			return true;
		else if(player.equals(pf))
			return true;
		else if(player.equals(c))
			return true;
		else if(player.equals(g))
			return true;
		else if(player.equals(f))
			return true;
		else if(player.equals(util))
			return true;
		return false;
	}

	@Override
	public double getProjection() {
		return score;
	}

	@Override
	public void checkBetter(ArrayList<Player> players) {
		Player better = null;
		Player worse = null;
		double points = 0;
		for(Player p: players){
			if(contains(p)) continue;
			if(p instanceof PG){
				if(p.getPPG() - pg.getPPG() > points && (p.getSalary() - pg.getSalary()) <= salary){ // And can add
					better = p;
					worse = pg;
					points = p.getPPG() - pg.getPPG();
				}
			}
			else if(p instanceof SG){
				if(p.getPPG() - sg.getPPG() > points && (p.getSalary() - sg.getSalary()) <= salary){ // And can add
					better = p;
					worse = sg;
					points = p.getPPG() - sg.getPPG();
				}
				else if(p.getPPG() - g.getPPG() > points && (p.getSalary() - g.getSalary()) <= salary){ // And can add
					better = p;
					worse = g;
					points = p.getPPG() - g.getPPG();
				}
				else if(p.getPPG() - util.getPPG() > points && (p.getSalary() - util.getSalary()) <= salary){ // And can add
					better = p;
					worse = util;
					points = p.getPPG() - util.getPPG();
				}
			}
			else if(p instanceof SF){
				if(p.getPPG() - sf.getPPG() > points && (p.getSalary() - sf.getSalary()) <= salary){ // And can add
					better = p;
					worse = sf;
					points = p.getPPG() - sf.getPPG();
				}
				else if(p.getPPG() - f.getPPG() > points && (p.getSalary() - f.getSalary()) <= salary){ // And can add
					better = p;
					worse = f;
					points = p.getPPG() - f.getPPG();
				}
				else if(p.getPPG() - util.getPPG() > points && (p.getSalary() - util.getSalary()) <= salary){ // And can add
					better = p;
					worse = util;
					points = p.getPPG() - util.getPPG();
				}
			}
			else if(p instanceof PF){
				if(p.getPPG() - pf.getPPG() > points && (p.getSalary() - pf.getSalary()) <= salary){ // And can add
					better = p;
					worse = pf;
					points = p.getPPG() - pf.getPPG();
				}
				else if(p.getPPG() - f.getPPG() > points && (p.getSalary() - f.getSalary()) <= salary){ // And can add
					better = p;
					worse = f;
					points = p.getPPG() - f.getPPG();
				}
				else if(p.getPPG() - util.getPPG() > points && (p.getSalary() - util.getSalary()) <= salary){ // And can add
					better = p;
					worse = util;
					points = p.getPPG() - util.getPPG();
				}
			}
			else if(p instanceof C){
				if(p.getPPG() - c.getPPG() > points && (p.getSalary() - c.getSalary()) <= salary){ // And can add
					better = p;
					worse = c;
					points = p.getPPG() - c.getPPG();
				}
				else if(p.getPPG() - util.getPPG() > points && (p.getSalary() - util.getSalary()) <= salary){ // And can add
					better = p;
					worse = util;
					points = p.getPPG() - util.getPPG();
				}
			}
		}
		if(better != null && worse != null){
			removePlayer(worse);
			addPlayer(better);
		}
		else System.out.println("No better options");
	}

	@Override
	public boolean forceAdd(Player p) {
		if(contains(p)) return false;
		if(p instanceof PG){
			if(pg == null){
				pg = (PG) p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(g == null){
				g = p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(util == null){
				util = p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else{
				return false;
			}
		}
		else if(p instanceof SG){
			if(sg == null){
				sg = (SG) p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(g == null){
				g = p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(util == null){
				util = p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else{
				return false;
			}
		}
		else if(p instanceof SF){
			if(sf == null){
				sf = (SF) p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(f == null){
				f = p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(util == null){
				util = p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else{
				return false;
			}
		}
		else if(p instanceof PF){
			if(pf == null){
				pf = (PF) p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(util == null){
				util = p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else{
				return false;
			}
		}
		else if(p instanceof C){
			if(c == null){
				c = (C) p;
				salary -= p.getSalary();
				score += p.getPPG();
				return true;
			}
			else if(util == null){
				util = p;
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

}

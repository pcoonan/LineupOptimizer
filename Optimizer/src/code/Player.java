package code;

public class Player {

	public String name;
	public String pos;
	public double ppg;
	public double sal;
	public double value;
	
	public Player(String iname, String ipos, double ippg, double isal){
		name = iname;
		pos = ipos;
		ppg = ippg;
		sal = isal;
		value = ippg/isal;			// Working idea for the algo
	}
}

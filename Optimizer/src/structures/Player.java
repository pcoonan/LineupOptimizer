package structures;

public interface Player extends Comparable<Player> {
	double getSalary();
	void setSalary(double sal);
	String getPosition();
	void setPosition(String pos);
	String getName();
	void setName(String name);
	double getPPG();
	void setPPG(double ppg);
	void setProjection(double ppg);
	double getProjection();
	public String getPos();
	public void setPos(String pos);
}

package structures;

public class DST implements Player {

	private double ppg;
	private String name;
	private String pos;
	private double sal;
	private double projection;

	public DST(String name, String pos, double ppg, double sal) {
		this.name = name;
		this.pos = pos;
		this.ppg = ppg;
		this.sal = sal;
		this.projection = 0;
	}
	
	public String toString(){
		return getName() + "," + getPosition() + "," + getSalary() + "," + getPPG() + "," + getProjection();
	}
	
	@Override
	public int compareTo(Player arg0) {
		final int BEFORE = -1;
		final int AFTER = 1;
		final int EQUAL = 0;
		
//		if(this.getSalary() < arg0.getSalary()) return AFTER;
//		if(this.getSalary() > arg0.getSalary()) return BEFORE;
		
		if(this.getProjection()/this.getSalary() > arg0.getProjection()/arg0.getSalary()) return AFTER;
		if(this.getProjection()/this.getSalary() < arg0.getProjection()/arg0.getSalary()) return BEFORE;
		return EQUAL;
	}

	@Override
	public double getSalary() {
		return sal;
	}

	@Override
	public void setSalary(double sal) {
		this.sal = sal;
	}

	@Override
	public String getPosition() {
		return pos;
	}

	@Override
	public void setPosition(String pos) {
		this.pos = pos;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public double getPPG() {
		return ppg;
	}

	@Override
	public void setPPG(double ppg) {
		this.ppg = ppg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		long temp;
		temp = Double.doubleToLongBits(ppg);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(sal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DST))
			return false;
		DST other = (DST) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pos == null) {
			if (other.pos != null)
				return false;
		} else if (!pos.equals(other.pos))
			return false;
		if (Double.doubleToLongBits(ppg) != Double.doubleToLongBits(other.ppg))
			return false;
		if (Double.doubleToLongBits(sal) != Double.doubleToLongBits(other.sal))
			return false;
		return true;
	}

	@Override
	public void setProjection(double ppg) {
		this.projection = ppg;
	}

	@Override
	public double getProjection() {
		return this.projection;
	}

	@Override
	public String getPos() {
		return this.pos;
	}

	@Override
	public void setPos(String pos) {
		this.pos = pos;
	}
}

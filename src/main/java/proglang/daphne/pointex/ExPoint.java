package proglang.daphne.pointex;


public class ExPoint {

	private final double points;
	private final double max;

	public ExPoint(double points, double max) {
		super();
		this.points = points;
		this.max = max;
	}

	public double getPoints() {
		return points;
	}

	public double getMax() {
		return max;
	}

	@Override
	public String toString() {
		return points + "/" + max;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(max);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(points);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExPoint other = (ExPoint) obj;
		if (Double.doubleToLongBits(max) != Double.doubleToLongBits(other.max))
			return false;
		if (Double.doubleToLongBits(points) != Double
				.doubleToLongBits(other.points))
			return false;
		return true;
	}
}

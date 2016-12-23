package proglang.students.daphne;

import java.util.Optional;


public class ExPoint {

	private final Optional<Double> points;
	private final double max;
    public ExPoint(double points, double max) {
    	this(Optional.of(points), max);
    }
	public ExPoint(Optional<Double> points, double max) {
		super();
		this.points = points;
		this.max = max;
	}

	public Optional<Double> getPoints() {
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
		result = prime * result + ((points == null) ? 0 : points.hashCode());
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
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		return true;
	}
}

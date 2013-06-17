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
}

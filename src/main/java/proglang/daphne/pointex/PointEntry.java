/**
 * 
 */
package proglang.daphne.pointex;

/**
 * @author jakobro
 *
 */
public class PointEntry {

	public final String user;
	public final int ex;
	public final double points;
	public final double maxpoints;
	
	public PointEntry(String user, int ex, double points, double maxpoints) {
		super();
		this.user = user;
		this.ex = ex;
		this.points = points;
		this.maxpoints = maxpoints;
	}
}

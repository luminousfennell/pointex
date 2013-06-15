package proglang.daphne.pointex;

import java.util.List;

public class Student {

	private final String username;
	private final String tutor;
	private final List<ExPoint> points;

	public Student(String username, String tutor, List<ExPoint> parsedPoints) {
		super();
		this.username = username;
		this.tutor = tutor;
		this.points = parsedPoints;
	}

	public String getUsername() {
		return username;
	}

	public String getTutor() {
		return tutor;
	}

	@Override
	public String toString() {
		return "Student [username=" + username + ", tutor=" + tutor
				+ ", points=" + points + "]";
	}

	public List<ExPoint> getPoints() {
		return points;
	}
}

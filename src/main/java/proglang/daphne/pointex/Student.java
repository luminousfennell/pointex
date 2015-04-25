package proglang.daphne.pointex;

import java.util.ArrayList;
import java.util.List;

public class Student {

	private final String username;
	private final String tutor;
	
	private ArrayList<ExPoint> points;

	public Student(String username, String tutor, ArrayList<ExPoint> parsedPoints) {
		super();
		this.username = username;
		this.tutor = tutor;
		this.points = parsedPoints;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		result = prime * result + ((tutor == null) ? 0 : tutor.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		Student other = (Student) obj;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		if (tutor == null) {
			if (other.tutor != null)
				return false;
		} else if (!tutor.equals(other.tutor))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
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

package proglang.students.daphne;

import java.util.ArrayList;
import java.util.List;

public class Student {
	
	// Some utility methods for student lists (for lack of a better place).
	// TODO: move this to Java 8
	public static List<Student> excludeStudents(List<Student> students, List<String> usernames) {
		List<Student> result = new ArrayList<Student>();
		for (Student s : students) {
			if (!usernames.contains(s.getUsername())) {
				result.add(s);
			}
		}
		return result;
	}

	// Real implementation of Student objects
	private final String username;
	private final String realname;
	private final String tutor;
	
	private List<ExPoint> points;

	public Student(String username, String realname, String tutor, List<ExPoint> parsedPoints) {
		super();
		this.username = username;
		this.realname = realname;
		this.tutor = tutor;
		this.points = new ArrayList<>(parsedPoints);
	}

	public String getUsername() {
		return username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		result = prime * result
				+ ((realname == null) ? 0 : realname.hashCode());
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
		if (realname == null) {
			if (other.realname != null)
				return false;
		} else if (!realname.equals(other.realname))
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
	
	public String getRealname() {
		return realname;
	}

	public String getTutor() {
		return tutor;
	}

	@Override
	public String toString() {
		return "Student [username=" + username + ", realname=" + realname + ", tutor=" + tutor
				+ ", points=" + points + "]";
	}

	public List<ExPoint> getPoints() {
		return points;
	}
}

package proglang.students.daphne;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import proglang.students.daphne.ExPoint;
import proglang.students.daphne.Student;

public class StudentTest {

	// TODO: factor example students into their own class, so that they can be shared by tests
	private List<Student> exampleStudents;
	private List<ExPoint> zeros;
	private List<ExPoint> somePoints;

	@Before
	public void setUp() {
		zeros = Arrays.asList(new ExPoint(0, 0));
		somePoints = Arrays.asList(new ExPoint(1, 9), new ExPoint(3, 10));
		exampleStudents = Arrays.asList(new Student("aa11", "Alpha S. Eleven",
				null, zeros), new Student("ab12", "Alpha Beta", "tt99", zeros),
				new Student("cd25", "Senn Heiser", null, zeros), new Student(
						"xy666", "Devil S. Advocate", null, somePoints));
	}

	@Test
	public void testExcludeStudents_some() {
		List<String> excluded = Arrays.asList("aa11", "xy666");
		List<Student> expected = Arrays.asList(new Student("ab12", "Alpha Beta", "tt99", zeros),
				new Student("cd25", "Senn Heiser", null, zeros));
		assertEquals(expected, Student.excludeStudents(exampleStudents, excluded));
	}

	@Test
	public void testExcludeStudents_none() {
		List<String> excluded = Arrays.asList("ab112", "xy665");
		assertEquals(exampleStudents, Student.excludeStudents(exampleStudents, excluded));
	}
}

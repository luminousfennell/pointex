package proglang.daphne.pointex;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class TutorsTest {

	private List<Student> exampleStudents;
	private List<ExPoint> zeros;
	private List<ExPoint> somePoints;

	@Before
	public void setUp() {
		zeros = Arrays.asList(new ExPoint(0, 0));
		somePoints = Arrays.asList(new ExPoint(1, 9), new ExPoint(3, 10));
		exampleStudents = Arrays.asList(new Student("aa11", "Alpha S. Eleven",
				null, zeros), new Student("ab12", "Alpha Beta", "tt99", zeros),
				new Student("cd25", "Senn Heiser", "tt99", zeros), new Student(
						"xy666", "Devil S. Advocate", "ss98", somePoints));
	}

	@Test
	public void testSort() {
		Map<String, List<Student>> expected = new HashMap<>();
		expected.put("tt99", Arrays.asList(new Student("ab12", "Alpha Beta",
				"tt99", zeros), new Student("cd25", "Senn Heiser", "tt99",
				zeros)));
		expected.put("ss98", Arrays.asList(new Student("xy666",
				"Devil S. Advocate", "ss98", somePoints)));
		assertEquals(expected, Tutors.sort(exampleStudents));
	}

	@Test
	public void testWrite() throws IOException {
		String expected = "ss98, 1, xy666, \n"
				+ "tt99, 2, ab12, cd25, \n";
		StringBuffer result = new StringBuffer();
		Tutors.write(result, Tutors.sort(exampleStudents));
		assertEquals(expected, result.toString());
	}
}

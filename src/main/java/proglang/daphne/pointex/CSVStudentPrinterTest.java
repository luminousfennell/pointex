package proglang.daphne.pointex;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CSVStudentPrinterTest {

	@Test
	public void test() {
		List<Student> students = Arrays.asList(new Student("aa11",
				"Alpha S. Eleven", null, Arrays.asList(new ExPoint(0, 0),
						new ExPoint(3, 10))), new Student("ab12", "Alpha Beta", "tt99", Arrays.asList(new ExPoint(3, 10))) );
		String expected = "aa11,Alpha S. Eleven,null,0.0,3.0\nab12,Alpha Beta,tt99,3.0\n";
		assertEquals(expected, new CSVStudentPrinter().getStringFrom(students));
	}
}

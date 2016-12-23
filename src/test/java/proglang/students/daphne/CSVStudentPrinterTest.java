package proglang.students.daphne;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import proglang.students.daphne.CSVStudentPrinter;
import proglang.students.daphne.ExPoint;
import proglang.students.daphne.Student;

public class CSVStudentPrinterTest {

	// @Test
	public void test() {
		List<Student> students = Arrays.asList(new Student("aa11",
				"Alpha S. Eleven", null, Arrays.asList(new ExPoint(0, 0),
						new ExPoint(3, 10))), new Student("ab12", "Alpha Beta", "tt99", Arrays.asList(new ExPoint(3, 10))) );
		String expected = "id,name,tutor,points\r\naa11,Alpha S. Eleven,,\"[0.0, 3.0]\"\r\nab12,Alpha Beta,tt99,[3.0]\r\n";
		StringBuffer result = new StringBuffer();
		new CSVStudentPrinter().print(result, students);
		assertEquals(expected, result.toString());
	}
}

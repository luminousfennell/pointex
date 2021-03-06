package proglang.students.daphne;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;


public class CSVStudentPrinter implements StudentPrinter {

	public void print(Appendable in, final List<Student> students) {
		try 
		(CSVPrinter printer = new CSVPrinter(in, CSVFormat.DEFAULT.withHeader("id", "name", "tutor", "points")))
		{
		for (Student st : students) {
			String points = st.getPoints().stream()
									      .map(p -> p.getPoints().map(s -> String.format("%.1f", s)).orElse(null))
					.collect(Collectors.toList()).toString();
			printer.printRecord(st.getUsername(), st.getRealname(), st.getTutor(), points);
		}
		} catch (IOException e) {
			System.err.println(String.format("Error writing students csv: %s", e.getMessage()));
			e.printStackTrace();
		}
	}
}

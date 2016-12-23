package proglang.students.daphne;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * Prints a results table for a list of students.
 * 
 * @author fennell
 *
 */
public class ResultPrinter {

	public void print(Appendable in, Collection<Student> students)
			throws IOException {
		try (CSVPrinter printer = new CSVPrinter(in,
				CSVFormat.DEFAULT.withHeader("student", "name", "number",
						"points", "max_points"))) {
			for (Student st : students) {
				List<ExPoint> points = st.getPoints();
				int[] indices = IntStream.range(0, points.size()).toArray();
				List<String> names = IntStream
						.of(indices)
						// TODO: do the renaming of exercises via an external file
						.mapToObj(
								i -> { return i < 4? formatEx(i+1) : i == 4? "klausur1"
                                                                           : formatEx(i+2);
										})
						.collect(Collectors.toList());
				for (int i : indices) {
					printer.printRecord(st.getUsername(), names.get(i), i,
							points.get(i).getPoints().orElse(null), points.get(i).getMax());
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("Error printing results: " + e.getMessage());
		}
	}

	private static String formatEx(int i) {
        return i < 10 ? String.format("uebung0%d", i) : String.format("uebung%d", i);
    }

}

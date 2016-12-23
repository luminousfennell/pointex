package proglang.students.his;

import java.io.IOException;
import java.util.Collection;
import java.util.OptionalInt;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class HisStudentPrinter {

	public void print(Appendable in, Collection<HisStudent> students) {
		try (CSVPrinter printer = new CSVPrinter(in,
				CSVFormat.DEFAULT.withHeader("name", "program", "category",
						"exgroup"))) {
			for (HisStudent st : students) {
				OptionalInt group = st.getGroup();
				printer.printRecord(st.getRealname(), st.getProgram()
						.getDescription(), st.getProgram().getCategory(), group
						.isPresent() ? group.getAsInt() : null);
			}
		} catch (IOException e) {
			throw new RuntimeException("Error printing HisStudents: "
					+ e.getMessage());
		}
		;
	}

}

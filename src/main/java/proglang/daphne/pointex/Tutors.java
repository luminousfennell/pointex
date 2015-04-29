package proglang.daphne.pointex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Tutors {

	public enum Fields {
		SIZE, SIZE_AND_USERNAMES
	}

	public static Map<String, List<Student>> sort(List<Student> students) {
		Map<String, List<Student>> result = new HashMap<>();
		for (Student s : students) {
			String tutor = s.getTutor();
			if (result.containsKey(tutor)) {
				result.get(tutor).add(s);
			} else if (tutor != null) {
				result.put(tutor, new ArrayList<>(Arrays.asList(s)));
			}
		}
		return result;
	}
	public static void write(Appendable out,
			Map<String, List<Student>> tutorInfo) throws IOException {
		write(out, tutorInfo, Fields.SIZE);
	}

	// TODO: use a CSV library... also for CSVStudentPrinter
	public static void write(Appendable out,
			Map<String, List<Student>> tutorInfo, Fields fields)
			throws IOException {
		for (String tutor : new TreeSet<>(tutorInfo.keySet())) {
			List<Student> students = tutorInfo.get(tutor);
			out.append(tutor + ", ");
			out.append(students.size() + ", ");
			if (fields == Fields.SIZE_AND_USERNAMES) {
				for (Student s : students) {
					out.append(s.getUsername() + ", ");
				}
			}
			out.append("\n");
		}
	}

}

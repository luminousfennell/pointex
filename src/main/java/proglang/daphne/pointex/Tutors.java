package proglang.daphne.pointex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;


public class Tutors {
	
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
	
	// TODO: use a CSV library... also for CSVStudentPrinter
	public static void write(Appendable out, Map<String, List<Student>> tutorInfo) throws IOException {
		for (String tutor : new TreeSet<>(tutorInfo.keySet())) {
			List<Student> students = tutorInfo.get(tutor);
			out.append(tutor + ", ");
			out.append(students.size() + ", ");
			for (Student s: students) {
				out.append(s.getUsername() + ", ");
			}
			out.append("\n");
		}
	}

}

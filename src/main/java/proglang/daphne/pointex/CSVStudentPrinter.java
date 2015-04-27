package proglang.daphne.pointex;

import java.util.List;


public class CSVStudentPrinter implements StudentPrinter {

	public String getStringFrom(final List<Student> students) {
		StringBuffer sb = new StringBuffer();

		for (Student st : students) {
			sb.append(st.getUsername() + ",");
			sb.append(st.getRealname() + ",");
			sb.append(st.getTutor() + ",");

			for (ExPoint p : st.getPoints()) {
				sb.append(p.getPoints() + ",");
			}

			sb.deleteCharAt(sb.length() - 1);
			sb.append("\n");
		}

		return sb.toString();
	}
}

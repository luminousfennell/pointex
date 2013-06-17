package proglang.daphne.pointex;

import java.util.Collection;

public class CSVPrinter {

    public static String toCSVString(final Collection<Student> students) {
        StringBuffer sb = new StringBuffer();

        for (Student st : students) {
            sb.append(st.getUsername() + ",");
            sb.append(st.getTutor() + ",");
            
            for (ExPoint p : st.getPoints()) {
                sb.append(p.getPoints() + ",");
            }

            sb.deleteCharAt(sb.length()-1);
            sb.append("\n");
        }

        return sb.toString();
    }
}

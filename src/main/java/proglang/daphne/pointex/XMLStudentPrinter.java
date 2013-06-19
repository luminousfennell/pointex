package proglang.daphne.pointex;

import java.util.List;

import com.thoughtworks.xstream.XStream;

public class XMLStudentPrinter implements StudentPrinter {

	@Override
	public String getStringFrom(List<Student> students) {
		XStream xstream = new XStream();
		xstream.alias("student", Student.class);
		xstream.alias("point", ExPoint.class);
		return xstream.toXML(students);
	}

}

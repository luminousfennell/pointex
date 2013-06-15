package proglang.daphne.pointex;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.xml.sax.SAXException;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) throws IOException, SAXException {

		// -Dorg.slf4j.simpleLogger.defaultLogLevel=debug
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY,
				"TRACE");

		InputStream in = new BufferedInputStream(new FileInputStream(
				"/tmp/daphne.xml"));
		Collection<Student> students = PointExtractor.extract(in);
		for (Student student : students) {
			System.out.println(student);
		}
	}
}

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

        if (args.length == 0) {
            System.err.println("Please supply daphne overview page as file");
            return;
        }

		// -Dorg.slf4j.simpleLogger.defaultLogLevel=debug
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY,
				"TRACE");

		InputStream in = new BufferedInputStream(new FileInputStream(
				args[0]));
		Collection<Student> students = PointExtractor.extract(in);
		/*for (Student student : students) {
			System.out.println(student);
		}*/
        System.out.println(CSVPrinter.toCSVString(students));
	}
}

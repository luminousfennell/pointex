package proglang.students.his;

import static org.junit.Assert.*;
import static proglang.TestUtils.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import proglang.students.his.DegreeProgram;
import proglang.students.his.HisStudent;
import proglang.students.his.StudentExtractor;

public class StudentExtractorTest {

		private HisStudent alpha = new HisStudent(
				"Beta, Alpha",
				new DegreeProgram(
						"Diplom, Mathematik, Hauptfach, PO 1994 (20. Fachsemester, Hauptfach)"),
				OptionalInt.of(1));

	@Test
	public void testParseStudent() throws IOException {
		Document doc = Jsoup.parse(loadTestFile("his-student-row.html"),
				Charset.defaultCharset().name(), "");
		Elements rows = doc.select("tbody > tr");
		if (rows.isEmpty()) { throw new RuntimeException("Row was empty!"); }

		assertEquals(alpha, StudentExtractor.parseStudent(rows.get(0)));
	}
	
	@Test
	public void testExtract() throws IOException {
		InputStream in = loadTestFile("his-test.html");
		List<HisStudent> expected = Arrays.asList(alpha);
		assertEquals(expected, StudentExtractor.extract(in));

		
	}

}

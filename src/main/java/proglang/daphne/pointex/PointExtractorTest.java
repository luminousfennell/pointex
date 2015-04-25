package proglang.daphne.pointex;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

public class PointExtractorTest {

	InputStream in;

	@Before
	public void setup() throws FileNotFoundException {
		String testHtmlFName = "index-test.html";
		in = this.getClass().getClassLoader()
				.getResourceAsStream(testHtmlFName);
		if (in == null) {
			throw new FileNotFoundException(testHtmlFName);
		}
	}

	@Test
	public void testExampleFile() throws IOException {
		List<ExPoint> zeros = Arrays.asList(new ExPoint(0, 0));
		//TODO: the expected expoint results are WRONG! They need to be fixed when refactoring is complete
		List<Student> expected = Arrays.asList(new Student("aa11", null, zeros), new Student("ab12", "tt99", zeros), new Student("cd25", null, zeros), new Student("xy666", null, zeros));
		List<Student> result = PointExtractor.extract(in);
		assertEquals(expected, result);

// TODO: write this test when the parse* method is written
//      String aRow = "<table><tr><td class=\" \"><a href=\"https://daphne.informatik.uni-freiburg.de/ss2015/ProgrammierenJavaSS2015/accounts/aa213/\">aa213</a></td> <td class=\" \">Anselm Josef Gabriel Auer</td></tr></table>";
//	  Document d = Jsoup.parse(aRow);
//	  Element r = d.select("tbody > tr").get(0);
//	  String[][] table = new String[1][5];
//	  PointExtractor.parseName(table, 0, r.select("td"));
//	  assertEquals("Anselm Josef Gabriel Auer", table[0][1]);
	}
}

package proglang.students.daphne;

import static org.junit.Assert.assertEquals;
import static proglang.TestUtils.loadTestFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import proglang.students.daphne.ExPoint;
import proglang.students.daphne.StudentExtractor;
import proglang.students.daphne.Student;

public class StudentExtractorTest {



	// @Test
	public void regressionIndexTest() throws IOException {
		List<ExPoint> zeros = Arrays.asList(new ExPoint(0, 0));
		//TODO: the expected expoint results are WRONG! They need to be fixed when refactoring is complete
		List<Student> expected = Arrays.asList(new Student("aa11", "Alpha S. Eleven", null, zeros), new Student("ab12", "Alpha Beta", "tt99", zeros), new Student("cd25", "Senn Heiser",null, zeros), new Student("xy666", "Devil S. Advocate", null, zeros));
		List<Student> result = StudentExtractor.extract(loadTestFile("index-test.html"));
		assertEquals(expected, result);

// TODO: write this test when the parse* method is written
//      String aRow = "<table><tr><td class=\" \"><a href=\"https://daphne.informatik.uni-freiburg.de/ss2015/ProgrammierenJavaSS2015/accounts/aa213/\">aa213</a></td> <td class=\" \">Anselm Josef Gabriel Auer</td></tr></table>";
//	  Document d = Jsoup.parse(aRow);
//	  Element r = d.select("tbody > tr").get(0);
//	  String[][] table = new String[1][5];
//	  PointExtractor.parseName(table, 0, r.select("td"));
//	  assertEquals("Anselm Josef Gabriel Auer", table[0][1]);
	}

	// @Test
	public void regressionIndexTestBroken() throws IOException {
		List<Student> result = StudentExtractor.extract(loadTestFile("index-test-broken.html"));
// TODO: what would one expect here?

	}
	
	private static Elements parseRow(String rowString) {
	  Document d = Jsoup.parse("<table><tr>" + rowString + "</tr></table>");
	  Elements row = d.select("tbody > tr").get(0).select("td");
	  return row;
	}
	
	// @Test
	public void testParseStudentAA11() {
      String rowString = "<td class=\" \"><a href=\"https://daphne.informatik.uni-freiburg.de/test/accounts/aa11/\">aa11</a></td> <td class=\" \">Alpha S. Eleven</td> <td class=\" \"><a href=\"https://daphne.informatik.uni-freiburg.de/test/groups/assign/\">*</a> </td> <td class=\"textright \">3 / 10</td> <td class=\"textright \"><a href=\"https://daphne.informatik.uni-freiburg.de/test/solutions/12345/\">- - -</a> / 0</td><td>3 / 10</td>";
	  Student result = StudentExtractor.parseStudent(parseRow(rowString));
	  assertEquals(new Student("aa11", "Alpha S. Eleven", null, Arrays.asList(new ExPoint(3, 10),new ExPoint(0, 0))), result);
	}

	// @Test
	public void testParseStudentAB12() {
      String rowString = "<td class=\" \"><a href=\"https://daphne.informatik.uni-freiburg.de/test/accounts/ab12/\">ab12</a></td> <td class=\" \">Alpha Beta</td> <td class=\" \"><a href=\"https://daphne.informatik.uni-freiburg.de/test/groups/12345/assign/\">*</a> <a href=\"https://daphne.informatik.uni-freiburg.de/ss2015/ProgrammierenJavaSS2015/accounts/tt99/\">tt99</a> </td> <td class=\"textright \">3 / 10</td> <td>3 / 10</td>";
	  Student result = StudentExtractor.parseStudent(parseRow(rowString));
	  assertEquals(new Student("ab12", "Alpha Beta", "tt99", Arrays.asList(new ExPoint(3, 10))), result);
	}
	
	@Test
	public void testParseZeroPointsWithoutParsing() {
		/*
		 * <td class="textright "><a href="https://daphne.informatik.uni-freiburg.de/ss2015/ProgrammierenJavaSS2015/solutions/balablabla">- - -</a> / 10</td>
		 */
		String pointsEntry = "- - - / 10";
		List<ExPoint> result = StudentExtractor.parsePoints(Arrays.asList(pointsEntry));
		assertEquals(Arrays.asList(new ExPoint(Optional.empty(), 10)), result);
	}

	@Test
	public void testParseZeroPointsWithParsing() {
		String cellString = "<td class=\"textright \"><a href=\"https://daphne.informatik.uni-freiburg.de/ss2015/ProgrammierenJavaSS2015/solutions/balablabla\">- - -</a> / 10</td>";
		String rowString = String.format("<table><tr>%s</tr></table>", cellString);
		Document doc = Jsoup.parse(rowString);
		String pointsEntry = doc.select("tbody > tr").get(0).select("td").get(0).text();
		List<ExPoint> result = StudentExtractor.parsePoints(Arrays.asList(pointsEntry));
		assertEquals(Arrays.asList(new ExPoint(Optional.empty(), 10)), result);
	}
}

package proglang.students.his;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**	
 * Extract students from the html downloaded from a HIS "ManualPlacementAllocation" form.
 * @author fennell
 *
 */
public class StudentExtractor {
	
	public static final int COL_NAME = 0;
	public static final int COL_PROGRAM = 1;

	private static org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(StudentExtractor.class);
	
	public static List<proglang.students.his.HisStudent> extract(final InputStream in) throws IOException {
		Document doc = Jsoup.parse(in, Charset.defaultCharset().name(), "");
		String tbodyId = "switchAdmissions:switchAdmissionFieldset:enrollmentTable:enrollmentTableTable:tbody_element";
		Elements tables = doc.getElementsByAttributeValue("id", tbodyId);
		if (tables.isEmpty()) {
			throw new IllegalArgumentException("Not table found with body-id: " + tbodyId);
		}
		if (tables.size() > 1) {
			log.warn(String.format("Found more than one table with body-id `%s'. Something's wrong!", tbodyId));
		}
		Elements rows = doc.select(String.format("*[id=%s] > tr", tbodyId));
		return rows.stream().map(r -> parseStudent(r)).collect(Collectors.toList());
	}
	
	
	static proglang.students.his.HisStudent parseStudent(Element row) {
		List<Element> fields = row.select("td").subList(0, 2);
		Elements inputs = row.select("input");
		if (fields.size() < 2 || inputs.isEmpty()) {
			throw new IllegalArgumentException(String.format("This student-row has an unexpected format: \n%s", row.toString()));
		}
		String name = fields.get(COL_NAME).ownText();
		DegreeProgram prog = new DegreeProgram(fields.get(COL_PROGRAM).ownText());
		
		OptionalInt group = IntStream.range(0, inputs.size())
							.filter((i) -> isChecked(inputs.get(i))).map(i -> i+1).findFirst();
		return new HisStudent(name, prog, group);
	}
	
	static boolean isChecked(Element input) {
		return input.attributes().get("checked").equals("checked");
	}

}

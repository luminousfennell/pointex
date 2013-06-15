package proglang.daphne.pointex;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PointExtractor {

	private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PointExtractor.class); 
	
	public static Collection<Student> extract(final InputStream in) throws IOException {
		String[] tableHeaders = null;
		String[][] table = null;
		
		Document doc = Jsoup.parse(in, Charset.defaultCharset().name(), "");
		Elements tables = doc.select("table");
		log.debug("Number of tables found: " + tables.size());
		for (Element element : tables) {

			// Extract header
			Elements headers = element.select("thead > tr > th");
			tableHeaders = new String[headers.size()];
			for (int i = 0; i < headers.size(); i++) {
				Element header = headers.get(i);
				tableHeaders[i] = header.text().trim();
			}
			
			log.debug("Headers: " + Arrays.toString(tableHeaders));

			// Extract content
			Elements rows = tables.select("tbody > tr.even, tbody > tr.odd");

			table = new String[rows.size()][tableHeaders.length];

			for (int currRow = 0; currRow < rows.size(); currRow++) {
				Elements cols = rows.get(currRow).select("td");

				for (int currCol = 0; currCol < cols.size(); currCol++) {
					Element col = cols.get(currCol);
					table[currRow][currCol] = col.text();
				}
			}
		}
		
		log.debug("Entries: " + table.length);

//		System.out.println(Arrays.toString(tableHeaders));
//		for (int i = 0; i < table.length; i++) {
//			for (int j = 0; j < table[i].length; j++) {
//				System.out.print(table[i][j] + "|");
//			}
//			System.out.println();
//		}
		
		return null; 
	}
}

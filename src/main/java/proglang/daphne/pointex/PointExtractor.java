package proglang.daphne.pointex;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PointExtractor {

	private static final int COL_USERNAME = 0;
	private static final int COL_REALNAME = 1;
	private static final int COL_TUTOR = 2;
	// private static final int COL_POINTS = 2;
	// private static final int COL_MARK = 3;
	private static final int COL_LATEST_EX = 4;

	private static org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(PointExtractor.class);

	public static List<Student> extract(final InputStream in)
			throws IOException {
		String[] tableHeaders = null;

		List<Student> students = new ArrayList<>();

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

			for (int currRow = 0; currRow < rows.size(); currRow++) {
				Elements cols = rows.get(currRow).select("td");

				Student student = parseStudent(cols);
				students.add(student);
			}
		}

		log.debug("Entries: " + students.size());

		return students;
	}

	static Student parseStudent(Elements row) {
		List<String> points = new ArrayList<>();
		for (Element e : row.subList(COL_LATEST_EX, row.size())) {
			points.add(e.text());
		}

		List<ExPoint> parsedPoints = parsePoints(points);
		String tutor = parseTutor(row.get(COL_TUTOR).text());
		return new Student(row.get(COL_USERNAME).text(), row.get(COL_REALNAME).text(), tutor, parsedPoints);
	}

	private static ArrayList<ExPoint> parsePoints(List<String> points) {
		ArrayList<ExPoint> coll = new ArrayList<>();

		for (String entry : points) {
			// Decimal conversion
			entry = entry.replace(",", ".");

			double received = 0;
			double max = -1;

			try (Scanner sc = new Scanner(entry);) {

				if (sc.hasNextDouble()) {
					received = sc.nextDouble();
				}
				sc.skip(".*/");
				max = sc.nextDouble();
			}

			coll.add(new ExPoint(received, max));
		}

		return coll;
	}

	private static String parseTutor(String tutor) {
		if (tutor.equals("*")) {
			return null;
		} else {
			String[] f = tutor.split(" ");
			if (f.length == 2) {
				return f[1];
			} else {
				return null; // TODO Or throw exception?!
			}
		}
	}
}

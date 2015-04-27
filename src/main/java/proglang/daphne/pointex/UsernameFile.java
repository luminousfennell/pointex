package proglang.daphne.pointex;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsernameFile {

	static public List<String> parseExcludedStudents(BufferedReader r) throws IOException, IllegalArgumentException {
		String rawLine = null;
		List<String> result = new ArrayList<>();
		while ((rawLine = r.readLine()) != null) {
			String line = rawLine.trim();
			if (!ignored(line)) {
				checkValidity(line);
				result.add(line);
			}
		}
		return result;
	}
	
	/**
	 * Empty lines and lines starting with `#' are ignored.
	 * @param line The line to test
	 * @return whether the line is ignored
	 */
	static boolean ignored(String line) {
		return line.isEmpty() || line.startsWith("#");
	}
	
	/**	
	 * Throws an IllegalArgumentException if the line is likely not a myAccount username
	 * @param line The line to test
	 */
	static void checkValidity(String line) {

		boolean valid = line.length() >= 3 && Character.isAlphabetic(line.toCharArray()[0]) && Character.isAlphabetic(line.toCharArray()[1]) && allNumeric(line.substring(2));
		if (!valid) {
			throw new IllegalArgumentException(String.format("Illegal username `%s'; usernames start with two initials, followed by a number (e.g., ab124)", line));
		}
	}
	
	static private boolean allNumeric(String s) {
		boolean result = true;
		for (char c : s.toCharArray()) {
			result &= Character.isDigit(c);
		}
		return result;
	}
}

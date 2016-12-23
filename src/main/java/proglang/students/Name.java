package proglang.students;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Name {

	private static String normalize(String n) {
		return n.trim().replace("ü","ue")
				.replace("ä", "ae")
				.replace("ö", "oe")
				.replace("ß", "ss")
				.replace("ó", "o")
				;

	}
	
	
	private final String first;
	private final String last;

	/**
	 * Create a name from a string, performing all kinds of normalizations to
	 * ensure that his names and daphne names can match.
	 */
	public Name(String name) {
		if (name.contains(",")) {
			List<String> components = Arrays.asList(name.split(","));
			if (components.size() != 2) {
				throw new IllegalArgumentException("Unable to parse name: "
						+ name);
			}
			name = components.get(1) + " " + components.get(0);

		} 
		List<String> components = Arrays.asList(name.split(" +"))
										.stream()
										.filter(s -> !s.isEmpty())
										.map(s -> normalize(s)).collect(Collectors.toList());
			int count = components.size();
			if (count == 0) {
				throw new IllegalArgumentException("Unable to parse name: "
						+ name);
			}
			this.last = components.get(count - 1).trim();
			this.first = components.subList(0, count - 1).stream()
					.map(s -> s.trim()).collect(Collectors.joining(" "));
	}

	@Override
	public String toString() {
		return String.format("%s %s", this.first, this.last);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Name other = (Name) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		return true;
	}

}

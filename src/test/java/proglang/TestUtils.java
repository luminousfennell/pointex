package proglang;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class TestUtils {

	public static InputStream loadTestFile(String resource) throws FileNotFoundException {
		InputStream in = TestUtils.class.getClassLoader()
				.getResourceAsStream(resource);
		if (in == null) {
			throw new FileNotFoundException(resource);
		}
		return in;
	}
}

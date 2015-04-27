package proglang.daphne.pointex;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UsernameFileTest {
	
	private BufferedReader testInput;
	
	@Before
	public void setUp() {
		testInput = new BufferedReader(new StringReader(
				"# Some usernames: \n"
				+ "  \n"
				+ "ab123 \n"
				+ "xy666\n  \n"));
	}

	@Test
	public void testCheckValidity_valid() {
		UsernameFile.checkValidity("ab123");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckValidity_invalid_space() {
		UsernameFile.checkValidity(" ab123");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckValidity_tooManyInitials() {
		UsernameFile.checkValidity("abc123");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckValidity_invalid2_numbersInFront() {
		UsernameFile.checkValidity("123ab");
	}
	
	@Test
	public void testCheckValidity_valid2() {
		UsernameFile.checkValidity("s8lumfen");
	}
	
	@Test
	public void testParseUsername() throws IOException {
		List<String> expected = Arrays.asList("ab123", "xy666");
		assertEquals(expected, UsernameFile.parseExcludedStudents(testInput));
	}

}

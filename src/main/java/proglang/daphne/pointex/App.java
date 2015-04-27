package proglang.daphne.pointex;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.xml.sax.SAXException;

/**
 * Hello world!
 * 
 */
public class App {
	
	private static final int EXIT_STATUS_ERROR = -1;
	private static final String OPT_EXCLUDED = "exclude";
	
	public static void main(String[] args) throws IOException, SAXException {

		Option outfile = OptionBuilder.withLongOpt("outfile")
				.withArgName("arg").hasArg().withDescription("Output file")
				.create("o");

		Option outtype = OptionBuilder.withLongOpt("type").withArgName("arg")
				.hasArg().withDescription("Type of the output: xml,csv")
				.create("t");

		Option optVerbose = new Option("v", "Verbose");

		Options options = new Options();
		options.addOption("h", "help", false, "Prints a help text");
		options.addOption(optVerbose);
		options.addOption(outfile);
		options.addOption(outtype);
		options.addOption(OptionBuilder.withLongOpt(OPT_EXCLUDED).withArgName("file").hasArg().withDescription("File with one excluded username per line").create());

		BasicParser parser = new BasicParser();

		CommandLine commandLine;
		try {
			commandLine = parser.parse(options, args);

			if (commandLine.hasOption("h")) {
				new HelpFormatter().printHelp("pointex", options);
				return;
			}

			if (commandLine.hasOption("v")) {
				// -Dorg.slf4j.simpleLogger.defaultLogLevel=debug
				System.setProperty(
						org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY,
						"TRACE");
			}

			String[] argsList = commandLine.getArgs();

			if (argsList.length == 0) {
				System.err.println("You did not provide a daphne html file.");
				new HelpFormatter().printHelp("pointex [options] filename",
						options);
				return;
			}
			
			List<String> excludedUsernames = new ArrayList<>();
			if (commandLine.hasOption(OPT_EXCLUDED)) {
				File excludedFile = new File(commandLine.getOptionValue(OPT_EXCLUDED));
				try  {
					BufferedReader r = new BufferedReader(new FileReader(excludedFile));
					excludedUsernames.addAll(UsernameFile.parseExcludedStudents(r));
				} catch (IOException e) {
					System.err.println(String.format("Error reading file of excluded students `%s': %s", excludedFile.getAbsolutePath(), e.getMessage()));
					System.exit(EXIT_STATUS_ERROR);
				} catch (IllegalArgumentException e) {
					System.err.println(String.format("Error parsing file of excluded students `%s': %s", excludedFile.getAbsolutePath(), e.getMessage()));
					System.exit(EXIT_STATUS_ERROR);
				}
			}

			InputStream in = new BufferedInputStream(new FileInputStream(
					argsList[0]));
			
			List<Student> students = Student.excludeStudents(PointExtractor.extract(in), excludedUsernames);

			// Fallback is CSV printer.
			StudentPrinter printer = new CSVStudentPrinter();

			if (commandLine.hasOption("t")) {
				switch (commandLine.getOptionValue("t")) {
				case "xml":
					printer = new XMLStudentPrinter();
					break;
				case "csv":
					printer = new CSVStudentPrinter();
					break;
				}
			}

			String studentsString = printer.getStringFrom(students);

			PrintStream ps = System.out;

			if (commandLine.hasOption("o")) {

				String file = commandLine.getOptionValue("o");

				ps = new PrintStream(file);
			}

			ps.println(studentsString);
			ps.close();

		} catch (ParseException e) {
			System.out.println("Unexpected exception:" + e.getMessage());

		}
	}
}

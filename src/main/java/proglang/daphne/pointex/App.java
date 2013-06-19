package proglang.daphne.pointex;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
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

			InputStream in = new BufferedInputStream(new FileInputStream(
					argsList[0]));
			List<Student> students = PointExtractor.extract(in);

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

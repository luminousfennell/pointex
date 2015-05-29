package proglang.students;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.xml.sax.SAXException;

import proglang.students.daphne.CSVStudentPrinter;
import proglang.students.daphne.ResultPrinter;
import proglang.students.daphne.StudentExtractor;
import proglang.students.daphne.Student;
import proglang.students.daphne.StudentPrinter;
import proglang.students.daphne.Tutors;
import proglang.students.daphne.UsernameFile;
import proglang.students.his.HisStudent;
import proglang.students.his.HisStudentPrinter;

/**
 * Hello world!
 * 
 */
public class App {

	private static final int EXIT_STATUS_ERROR = -1;
	private static final String OPT_EXCLUDED = "exclude";
	private static final String OPT_TUTOR_SUMMARY = "tutor-summary";
	private static final String OPT_DAPHNE_INPUT = "daphne-input";
	private static final String OPT_HIS_INPUT = "his-input";
	private static final String OPT_DAPHNE_STUDENTS = "daphne-students";
	private static final String OPT_DAPHNE_EX = "daphne-exercises";
	private static final String OPT_HIS = "his";

	public static void main(String[] args) throws IOException, SAXException {

		Option optVerbose = new Option("v", "Verbose");

		Options options = new Options()
				.addOption("h", "help", false, "Prints a help text")
				.addOption(optVerbose)
				.addOption(
						Option.builder()
								.longOpt(OPT_EXCLUDED)
								.argName("file")
								.hasArgs()
								.valueSeparator(',')
								.desc(
										"File with one excluded username per line")
								.build())
				;
		OptionGroup commands = new OptionGroup()
			.addOption(Option.builder()
						.longOpt(OPT_DAPHNE_STUDENTS)
						.desc("Extract students from a Daphne html file").build())
			.addOption(Option.builder()
						.longOpt(OPT_DAPHNE_EX)
						.desc("Extract exercise results from a Daphne html file").build())
			.addOption(Option.builder()
						.longOpt(OPT_HIS)
						.desc("Extract students from a HIS-\"Change student affiliation\" html file")
						.build())
			.addOption(Option.builder().longOpt(OPT_TUTOR_SUMMARY)
								.desc("Print a tutor summary")
								.build())
								;
		options.addOptionGroup(commands);


		CommandLineParser parser = new DefaultParser();

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
				System.err.println("You did not provide an input file.");
				new HelpFormatter().printHelp("pointex [options] filename",
						options);
				return;
			}

			List<String> excludedUsernames = new ArrayList<>();
			if (commandLine.hasOption(OPT_EXCLUDED)) {
					for (String fname : commandLine.getOptionValues(OPT_EXCLUDED)) {
					readExcluded(excludedUsernames, fname);
					}
			}

			InputStream in = new BufferedInputStream(new FileInputStream(
					argsList[0]));

			IOSupplier<List<Student>> students = 
					() -> Student.excludeStudents(StudentExtractor.extract(in), excludedUsernames);

			IOSupplier<List<HisStudent>> hisStudents = 
					() -> proglang.students.his.StudentExtractor.extract(in);

			if (commandLine.hasOption(OPT_TUTOR_SUMMARY)) {
				Tutors.write(System.out, Tutors.sort(students.get()));
			} else if (commandLine.hasOption(OPT_DAPHNE_EX)) {
				new ResultPrinter().print(System.out, students.get());
			} else if (commandLine.hasOption(OPT_HIS)) {
				new HisStudentPrinter().print(System.out, hisStudents.get());
			} else {
				// default OPT_DAPHNE_STUDENTS

				StudentPrinter printer = new CSVStudentPrinter();
				printer.print(System.out, students.get());
			}
		} catch (ParseException e) {
			System.out.println("Unexpected exception:" + e.getMessage());
		} 
	}
	
    private static void readExcluded(List<String> excludedUsernames, String fname) {
				File excludedFile = new File(fname);
				try {
					BufferedReader r = new BufferedReader(new FileReader(
							excludedFile));
					excludedUsernames.addAll(UsernameFile
							.parseExcludedStudents(r));
				} catch (IOException e) {
					System.err.println(String.format(
							"Error reading file of excluded students `%s': %s",
							excludedFile.getAbsolutePath(), e.getMessage()));
					System.exit(EXIT_STATUS_ERROR);
				} catch (IllegalArgumentException e) {
					System.err.println(String.format(
							"Error parsing file of excluded students `%s': %s",
							excludedFile.getAbsolutePath(), e.getMessage()));
					System.exit(EXIT_STATUS_ERROR);
				}
    }
}

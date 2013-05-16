/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@unico-group.com
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.herold;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.option.BooleanOption;
import org.dbdoclet.option.FileOption;
import org.dbdoclet.option.Option;
import org.dbdoclet.option.OptionException;
import org.dbdoclet.option.OptionList;
import org.dbdoclet.option.SelectOption;
import org.dbdoclet.option.StringOption;
import org.dbdoclet.service.FileServices;
import org.dbdoclet.service.ResourceServices;
import org.dbdoclet.service.StringServices;
import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.TrafoExceptionHandler;
import org.dbdoclet.trafo.TrafoScriptManager;
import org.dbdoclet.trafo.html.docbook.HtmlDocBookTrafo;
import org.dbdoclet.trafo.internal.html.docbook.DbtConstants;
import org.dbdoclet.trafo.script.Script;

/**
 * The class <code>Herold</code> implements a console converter to transform
 * HTML code to DocBook XML.
 * 
 * @author <a href ="mailto:mfuchs@unico-consulting.com">Michael Fuchs</a>
 * @version 1.0
 */
public class Herold {

	private static Log logger = LogFactory.getLog(Herold.class);

	private static FileOption optIn;
	private static FileOption optOut;
	private static ResourceBundle res = ResourceBundle
			.getBundle("org/dbdoclet/herold/Resources");
	private boolean verbose = false;
	private static boolean systemExitEnabled = true;

	private static int exitCode;

	public static void main(String[] args) {

		OptionList options = null;
		exitCode = 0;

		try {

			Option<?> option;
			options = new OptionList(args);

			// help
			option = new BooleanOption("help", "h");
			option.setMediumName("?");
			options.add(option);

			// license
			option = new BooleanOption("license", "L");
			options.add(option);

			// version
			option = new BooleanOption("version", "V");
			options.add(option);

			options.validate(true);

			if (options.getFlag("help", false)) {
				printUsage();
				return;
			}

			if (options.getFlag("version", false)) {
				printVersion();
				return;
			}

			if (options.getFlag("license", false)) {
				printLicense();
				exit(0);
			}

			options = createOptionList(args);

			if (options.validate() == false) {
				throw new OptionException(options.getError());
			}

			Herold converter = new Herold();

			StringOption optProfile = (StringOption) options
					.getOption("profile");
			File profileFile = findProfile(optProfile);
			Script script;

			TrafoScriptManager mgr = new TrafoScriptManager();
			script = mgr.parseScript(profileFile);
			logger.info(String.format("Using profile file %s.",
					profileFile.getAbsolutePath()));

			if (logger.isTraceEnabled()) {
				StringWriter buffer = new StringWriter();
				mgr.writeScript(script, buffer);
				logger.trace("Script: " + buffer.toString());
			}

			File outFile = converter.processCommandLineOptions(options, script);

			if (outFile != null) {
				logger.info(String.format("Output file is %s.",
						outFile.getPath()));
			}

			InputStream in = null;
			OutputStream out = null;

			if (optIn.isUnset()) {
				in = System.in;
			} else {
				in = new FileInputStream(optIn.getValue());
			}

			out = createOutputStream();

			converter.convert(in, out, script);

		} catch (OptionException oops) {

			if ((options != null) && (options.getFlag("help", false) == false)) {

				printUsage();

				String msg = oops.getMessage();

				if (msg != null) {
					System.err.println(msg);
				}

			} else {
				oops.printStackTrace();
				printUsage();
			}

			exitCode = 1;

		} catch (TrafoException oops) {

			logger.fatal("TrafoException", TrafoExceptionHandler.getCause(oops));
			exitCode = 2;

		} catch (FileNotFoundException oops) {

			logger.fatal(oops.getMessage());
			exitCode = 3;

		} catch (IOException oops) {

			oops.printStackTrace();
			exitCode = 4;

		} catch (Exception oops) {

			oops.printStackTrace();
			exitCode = 5;
		}

		exit(exitCode);
	}

	private static OutputStream createOutputStream()
			throws FileNotFoundException {

		OutputStream out;

		if (optOut.isUnset()) {

			out = System.out;

		} else {

			File outDir = optOut.getValue().getParentFile();

			if (outDir != null && outDir.exists() == false) {
				outDir.mkdir();
			}

			out = new FileOutputStream(optOut.getValue());
		}

		return out;
	}

	private static File findProfile(StringOption optProfile)
			throws FileNotFoundException {

		String profileName = "default.her";

		if (optProfile.isUnset() == false) {
			profileName = optProfile.getValue();
		}

		if (profileName == null || profileName.trim().length() == 0) {
			profileName = "default.her";
		}

		File profile = new File(profileName);

		if (profile.exists() == true) {
			return profile;
		}

		String homePath = System.getProperty("herold.home");

		if (homePath != null) {

			File homeDir = new File(homePath);

			if (homeDir.exists() == false) {
				throw new FileNotFoundException(String.format(
						"Home directory %s does not exist!", homePath));
			}

			File profilesDir = new File(homeDir, "profiles");

			if (profilesDir.exists() == false) {
				throw new FileNotFoundException(String.format(
						"Profiles directory %s does not exist!",
						profilesDir.getAbsolutePath()));
			}

			File file = new File(profilesDir, profileName);

			if (file.exists() == true) {
				return file;
			}

			file = new File(profilesDir, profileName + ".her");

			if (file.exists() == true) {
				return file;
			}
		}

		throw new FileNotFoundException(String.format(
				"Profile %s does not exist!", profileName));
	}

	private static void exit(int exitCode) {

		Herold.exitCode = exitCode;

		if (systemExitEnabled == true) {
			System.exit(exitCode);
		}
	}

	private static OptionList createOptionList(String[] args) {

		Option<?> option;
		SelectOption selopt;
		BooleanOption bopt;
		StringOption sopt;

		OptionList options = new OptionList(args);

		// docbook-add-index
		bopt = new BooleanOption(DbtConstants.SECTION_DOCBOOK.toLowerCase()
				+ "-" + DbtConstants.PARAM_ADD_INDEX, "x");
		bopt.setValue(false);
		options.add(bopt);

		// docbook-encoding
		sopt = new StringOption(DbtConstants.SECTION_DOCBOOK.toLowerCase()
				+ "-" + DbtConstants.PARAM_ENCODING, "d");
		sopt.setDefault("UTF-8");
		options.add(sopt);

		// decompose-tables
		bopt = new BooleanOption(DbtConstants.SECTION_DOCBOOK.toLowerCase()
				+ "-" + DbtConstants.PARAM_DECOMPOSE_TABLES, "T");
		bopt.setDefault(false);
		options.add(bopt);

		// document-element
		selopt = new SelectOption(DbtConstants.SECTION_DOCBOOK.toLowerCase()
				+ "-" + DbtConstants.PARAM_DOCUMENT_ELEMENT, "r");

		String[] optv2 = { "article", "book", "reference", "part", "chapter",
				"section" };
		selopt.setList(optv2);
		selopt.setDefault("article");
		options.add(selopt);

		// title
		sopt = new StringOption(DbtConstants.SECTION_DOCBOOK.toLowerCase()
				+ "-" + DbtConstants.PARAM_TITLE, "t");
		sopt.setDefault("http://www.dbdoclet.org/herold");
		options.add(sopt);

		// source-encoding
		sopt = new StringOption(DbtConstants.SECTION_HTML.toLowerCase() + "-"
				+ DbtConstants.PARAM_HTML_SOURCE_ENCODING, "s");
		sopt.setDefault("UTF-8");
		options.add(sopt);

		// in
		optIn = new FileOption("in", "i");
		optIn.isExisting(true);
		options.add(optIn);

		// loglevel
		SelectOption optLogLevel = new SelectOption("logging-level", "l");
		optLogLevel.setList(new String[] { "debug5", "debug4", "debug3",
				"debug2", "debug", "info", "warn", "error", "fatal" });
		optLogLevel.setDefault("error");
		options.add(optLogLevel);

		// out
		optOut = new FileOption("out", "o");
		options.add(optOut);

		StringOption optProfile = new StringOption("profile", "p");
		options.add(optProfile);

		// verbose
		option = new BooleanOption("verbose", "v");
		options.add(option);

		return options;
	}

	private static String getVersion() {

		Package p = Herold.class.getPackage();
		return p.getImplementationVersion();
	}

	private static void printLicense() throws IOException {

		URL url = ResourceServices
				.getResourceAsUrl("/org/dbdoclet/herold/COPYING");

		if (url != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String line = reader.readLine();

			while (line != null) {
				println(line);
				line = reader.readLine();
			}

			reader.close();
		} else {
			logger.fatal("Can't find resource for license!");
		}
	}

	private static void println(String str) {
		System.out.println(str);
	}

	private static void printUsage() {

		try {

			String resname = ResourceServices.getString(res, "C_USAGE");
			String buffer = ResourceServices.getResourceAsString(resname);

			println(MessageFormat.format(buffer, getVersion()));

		} catch (IOException oops) {

			logger.fatal("Printing usage message failed!", oops);
		}
	}

	private static void printVersion() {

		println("herold version \"" + getVersion() + "\"");
	}

	public void convert(InputStream in, OutputStream out, Script script)
			throws TrafoException {

		HtmlDocBookTrafo trafo = new HtmlDocBookTrafo();
		trafo.setInputStream(in);
		trafo.setOutputStream(out);
		
		if (verbose == true) {
			trafo.transform(script, new ConsoleProgressListener(false));
		} else {
			trafo.transform(script, null);
		}

		if (verbose == true) {
			System.out.println();
		}
	}

	public boolean isCanceled() {
		return false;
	}

	public File processCommandLineOptions(OptionList options, Script script) {

		for (Option<?> option : options) {

			String name = option.getLongName();

			if (name.startsWith(DbtConstants.SECTION_DOCBOOK.toLowerCase())) {
				script.selectSection(DbtConstants.SECTION_DOCBOOK);
				name = StringServices.cutPrefix(name,
						DbtConstants.SECTION_DOCBOOK.toLowerCase());

			} else if (name.startsWith(DbtConstants.SECTION_HTML.toLowerCase())) {
				script.selectSection(DbtConstants.SECTION_HTML);
				name = StringServices.cutPrefix(name,
						DbtConstants.SECTION_HTML.toLowerCase());
			}

			name = StringServices.cutPrefix(name, "-");

			if (option.isUnset() == false) {
				switch (option.getType()) {

				case BOOLEAN:
					script.setBoolParameter(name, (Boolean) option.getValue());
					break;

				default:
					script.setTextParameter(name, option.getValue().toString());
					break;
				}
			}
		}

		setVerbose(options.getFlag("verbose", false));

		File outFile;

		if (optOut.isUnset() && optIn.isUnset() == false) {

			String outFileName = FileServices.getFileBase(optIn.getValue())
					+ ".xml";
			// outFile = FileServices.createUniqueFile(new File(outFileName));
			outFile = new File(outFileName);

		} else {
			outFile = optOut.getValue();
		}

		return outFile;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public void convert(File htmlFile, File xmlFile) throws TrafoException,
			FileNotFoundException {

		Script script = new Script();
		convert(new FileInputStream(htmlFile), new FileOutputStream(xmlFile),
				script);
	}

	public static void setSystemExitEnabled(boolean systemExitEnabled) {
		Herold.systemExitEnabled = systemExitEnabled;
	}

	public static int getExitCode() {
		return exitCode;
	}
}

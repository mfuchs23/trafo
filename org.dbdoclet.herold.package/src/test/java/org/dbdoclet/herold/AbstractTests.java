package org.dbdoclet.herold;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.dbdoclet.trafo.TrafoScriptManager;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.XmlServices;
import org.dbdoclet.xiphias.XmlValidationResult;
import org.junit.Before;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class AbstractTests {

	protected static String NS_DOCBOOK = "http://docbook.org/ns/docbook";
	protected URL docbookSchemaUrl;

	public AbstractTests() {
		super();
	}

	@Before
	public void startUp() {
		try {
			docbookSchemaUrl = new File(
					"/usr/share/dbdoclet/docbook/xsd/5.0/docbook.xsd").toURI()
					.toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/** Ausführen einer Transformation via Kommandozeile */
	protected int executeHeroldCommandLine(String[] cmd) throws IOException,
			SAXException, ParserConfigurationException {

		File testDir = new File("./build/test");
		if (testDir.exists() == false) {
			testDir.mkdirs();
		}

		System.setProperty("herold.home", "./root");
		Herold.setSystemExitEnabled(false);
		Herold.main(cmd);
		return Herold.getExitCode();
	}

	protected Document herold(File htmlFile, File xmlFile) {
		return herold(htmlFile, xmlFile, "default");
	}

	protected Document herold(File htmlFile, File xmlFile, String profile) {
		try {

			TrafoScriptManager mgr = new TrafoScriptManager();
			Script script = mgr.parseScript(new File(
					"./src/test/resources/profile/" + profile + ".her"));

			StringWriter buffer = new StringWriter();
			mgr.writeScript(script, buffer);
			System.out.println(buffer);

			Herold herold = new Herold();
			herold.setVerbose(false);

			File outDir = xmlFile.getParentFile();

			if (outDir.exists() == false) {
				outDir.mkdirs();
			}

			herold.convert(new FileInputStream(htmlFile), new FileOutputStream(
					xmlFile), script);

			return validateAndParse(xmlFile);

		} catch (Exception oops) {

			oops.printStackTrace();
			fail();
		}

		return null;
	}

	protected Document validateAndParse(File xmlFile)
			throws ParserConfigurationException, SAXException, IOException {

		XmlValidationResult result = XmlServices.validate(xmlFile,
				docbookSchemaUrl);
		if (result.failed()) {
			System.out.println(result.createTextReport());
			fail();
		}

		return XmlServices.parse(xmlFile);
	}

	protected Document herold(String id) {
		File htmlFile = new File(
				String.format("src/test/resources/%s.html", id));
		File xmlFile = new File(String.format("build/test/%s.xml", id));
		return herold(htmlFile, xmlFile);
	}

}
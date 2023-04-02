package org.dbdoclet.herold;

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.catalog.CatalogFeatures;
import javax.xml.catalog.CatalogManager;
import javax.xml.catalog.CatalogResolver;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.dbdoclet.herold.Herold.OutputFormat;
import org.dbdoclet.trafo.TrafoResult;
import org.dbdoclet.trafo.TrafoScriptManager;
import org.dbdoclet.trafo.TrafoService;
import org.dbdoclet.trafo.html.docbook.HtmlDocBookTrafo;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.XmlServices;
import org.dbdoclet.xiphias.XmlValidationResult;
import org.junit.Before;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class AbstractTests {

	protected static String NS_DOCBOOK = "http://docbook.org/ns/docbook";
	protected URL docbookSchemaUrl;
	protected URL ditaTopicSchemaUrl;
	private String  ditaCatalog;

	public AbstractTests() {
		super();
	}

	@Before
	public void startUp() {
		try {
			
			docbookSchemaUrl = new File(
					"/usr/share/dodo/docbook/xsd/5.0/docbook.xsd").toURI()
					.toURL();
			
			ditaTopicSchemaUrl = new File(
					"/usr/share/dita-ot/schema/base/xsd/basetopic.xsd").toURI()
					.toURL();
			
			ditaCatalog = "/usr/share/dita-ot/schema/catalog.xml";
			
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
		
		Herold herold = new Herold();
		herold.setSystemExitEnabled(false);
		herold.execute(cmd);
		return herold.getExitCode();
	}

	protected Document html2docbook(File htmlFile, File xmlFile) {
		return herold(htmlFile, xmlFile, OutputFormat.DocBook, "default");
	}

	protected Document html2docbook(File htmlFile, File xmlFile, String profile) {
		return herold(htmlFile, xmlFile, OutputFormat.DocBook, profile);
	}

	protected Document html2dita(File htmlFile, File xmlFile) {
		return herold(htmlFile, xmlFile, OutputFormat.DITA, "default");
	}

	protected Document html2dita(File htmlFile, File xmlFile, String profile) {
		return herold(htmlFile, xmlFile, OutputFormat.DITA, profile);
	}

	protected Document herold(File htmlFile, File xmlFile, OutputFormat outputFormat, String profile) {
		try {

			TrafoScriptManager mgr = new TrafoScriptManager();
			
			String pathname = "./src/test/resources/profile/" + profile;
			if (pathname.endsWith(".her") == false) {
				pathname += ".her";
			}
			
			Script script = mgr.parseScript(new File(
					pathname));

			StringWriter buffer = new StringWriter();
			mgr.writeScript(script, buffer);
			System.out.println(buffer);

			Herold herold = new Herold();
			herold.setVerbose(false);
			herold.setOutputFormat(outputFormat);
			File outDir = xmlFile.getParentFile();

			if (outDir.exists() == false) {
				outDir.mkdirs();
			}

			herold.convert(new FileInputStream(htmlFile), new FileOutputStream(
					xmlFile), xmlFile, script);

			if (outputFormat == OutputFormat.DITA) {
				return validateAndParseDita(xmlFile);
			}
			
			return validateAndParseDocBook(xmlFile);

		} catch (Exception oops) {

			oops.printStackTrace();
			fail();
		}

		return null;
	}

	protected Document validateAndParseDocBook(File xmlFile)
			throws ParserConfigurationException, SAXException, IOException {

		XmlValidationResult result = XmlServices.validate(xmlFile,
				docbookSchemaUrl);
		if (result.failed()) {
			System.out.println(result.createTextReport());
			fail();
		}

		return XmlServices.parse(xmlFile);
	}


	protected Document validateAndParseDita(File xmlFile)
			throws ParserConfigurationException, SAXException, IOException {

		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		CatalogResolver resolver =
				CatalogManager.catalogResolver(CatalogFeatures.defaults(), URI.create(ditaCatalog));
		factory.setResourceResolver(resolver);
		Schema schema = factory.newSchema(ditaTopicSchemaUrl);
		Validator validator = schema.newValidator();
		validator.validate(new StreamSource(xmlFile));

		return XmlServices.parse(xmlFile);
	}

	protected Document html2docbook(String id) {
		File htmlFile = new File(
				String.format("src/test/resources/%s.html", id));
		File xmlFile = new File(String.format("build/test/%s.xml", id));
		return html2docbook(htmlFile, xmlFile);
	}

	protected Document html2dita(String id) {
		File htmlFile = new File(
				String.format("src/test/resources/%s.html", id));
		File xmlFile = new File(String.format("build/test/%s.dita", id));
		return html2dita(htmlFile, xmlFile);
	}

	protected String transform(String htmlCode) {
		return transform(htmlCode, new Script());
	}
	
	protected String transform(String htmlCode, Script script) {

		if (htmlCode == null) {
			throw new IllegalArgumentException("Der Parameter htmlCode darf nicht null sein!");
		}
		
		try {
			
			TrafoService trafo = new HtmlDocBookTrafo();
			trafo.setInputStream(new ByteArrayInputStream(htmlCode.getBytes()));
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			trafo.setOutputStream(buffer);
			TrafoResult result = trafo.transform(script);
			
			if (result.isFailed()) {
				fail(result.toString());
			}
			
			return buffer.toString("UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fail();
			return null;
		}
	}

}
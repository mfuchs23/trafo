package org.dbdoclet.trafo.html.docbook;

import java.io.File;
import java.io.IOException;

import org.dbdoclet.service.FileServices;
import org.dbdoclet.service.ResourceServices;
import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.TrafoResult;
import org.dbdoclet.trafo.internal.html.docbook.DocBookTransformer;
import org.junit.Test;

public class TableTests {

	@Test
	public void testMultipleTbody_1() throws IOException, TrafoException {

		String htmlCode = "<table><tbody><tr><td>Zeile 1</td></tr></tbody><tbody><tr><td>Zeile 2</td></tr></tbody></table>";

		DocBookTransformer transformer = new DocBookTransformer();

		TrafoResult result = new TrafoResult();
		String xmlCode = transformer.transformFragment(htmlCode, result);
		System.out.println(xmlCode);
	}

	@Test
	public void testNestedTables_1() throws IOException, TrafoException {

		String htmlCode = "<table><tr><td>"
				+ "<table><tr><th>FIRST TABLE</th></tr></table>"
				+ "<table><tr><th>SECOND TABLE</th></tr></table>"
				+ "</td></tr></table>";

		DocBookTransformer transformer = new DocBookTransformer();

		TrafoResult result = new TrafoResult();
		String xmlCode = transformer.transformFragment(htmlCode, result);
		System.out.println(xmlCode);
	}

	@Test
	public void testNestedTables_2() throws IOException, TrafoException {

		String htmlCode = "<table><tr><td>" + "<p>PARAGRAPH</p>"
				+ "<table><tr><th>FIRST TABLE</th></tr></table>"
				+ "</td></tr></table>";

		DocBookTransformer transformer = new DocBookTransformer();

		TrafoResult result = new TrafoResult();
		String xmlCode = transformer.transformFragment(htmlCode, result);
		System.out.println(xmlCode);
	}

	@Test
	public void testNestedTables_3() throws IOException, TrafoException {

		String htmlCode = "<table><tr><td>"
				+ "<table><tr><th>EMBEDDED TABLE</th></tr></table>"
				+ "</td></tr></table>";

		DocBookTransformer transformer = new DocBookTransformer();

		TrafoResult result = new TrafoResult();
		String xmlCode = transformer.transformFragment(htmlCode, result);
		System.out.println(xmlCode);
	}

	@Test
	public void testTable_1() throws IOException, TrafoException {

		String htmlCode = ResourceServices
				.getResourceAsString("html/Table_1.html");

		File destinationFile = new File("build/xml/Table_1.xml");
		DocBookTransformer transformer = new DocBookTransformer();

		TrafoResult result = new TrafoResult();
		String xmlCode = transformer.transformDocument(htmlCode,
				DocumentElementType.BOOK, result);
		FileServices.writeFromString(destinationFile, xmlCode);
	}
}

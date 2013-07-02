package org.dbdoclet.trafo.html.docbook;

import java.io.IOException;

import org.dbdoclet.service.ResourceServices;
import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.html.AbstractTests;
import org.junit.Test;

public class TableTests extends AbstractTests {

	@Test
	public void testMultipleTbody_1() throws IOException, TrafoException {

		String htmlCode = "<table><tbody><tr><td>Zeile 1</td></tr></tbody><tbody><tr><td>Zeile 2</td></tr></tbody></table>";
		transform(htmlCode);
	}

	@Test
	public void testNestedTables_1() throws IOException, TrafoException {

		String htmlCode = "<table><tr><td>"
				+ "<table><tr><th>FIRST TABLE</th></tr></table>"
				+ "<table><tr><th>SECOND TABLE</th></tr></table>"
				+ "</td></tr></table>";

		transform(htmlCode);
	}

	@Test
	public void testNestedTables_2() throws IOException, TrafoException {

		String htmlCode = "<table><tr><td>" + "<p>PARAGRAPH</p>"
				+ "<table><tr><th>FIRST TABLE</th></tr></table>"
				+ "</td></tr></table>";
		transform(htmlCode);
	}

	@Test
	public void testNestedTables_3() throws IOException, TrafoException {

		String htmlCode = "<table><tr><td>"
				+ "<table><tr><th>EMBEDDED TABLE</th></tr></table>"
				+ "</td></tr></table>";
		transform(htmlCode);
	}

	@Test
	public void testTable_1() throws IOException, TrafoException {

		String htmlCode = ResourceServices
				.getResourceAsString("html/Table_1.html");
		transform(htmlCode);
	}
}

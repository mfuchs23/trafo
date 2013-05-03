package org.dbdoclet.trafo.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.dbdoclet.trafo.html.tokenizer.parser.HtmlTokenizer;
import org.dbdoclet.trafo.html.tokenizer.Token;
import org.junit.Test;

public class AttributeParserTests {

	@Test
	public void test_1() {
		parse("", 0);
	}

	@Test
	public void test_2() {
		parse("<br/>", 1);
	}

	@Test
	public void test_3() {
		parse("<br>", 1);
	}

	@Test
	public void test_4() {
		parse("<img src=\"url\">", 1);
	}

	@Test
	public void test_5() {
		parse("<a href=\"url target=\"_blank\">Referenz auf etwas</a>", 2);
	}

	@Test
	public void test_6() {
		parse("<a href='url target='_blank'>", 1);
	}

	@Test
	public void test_7() {
		parse("<überschrift weight='bold'>", 1);
	}

	@Test
	public void test_8() {
		parse("<text:überschrift weight='bold'>", 1);
	}

	@Test
	public void test_9() {
		parse("<heading-level-2 weight='bold'>", 1);
	}

	@Test
	public void test_10() {
		parse("<ooo:heading-level.2 weight='bold'>", 1);
	}

	@Test
	public void test_11() {
		parse("Text mit <b>Tags</b> und <a href='http://www.dbdoclet.org target='_blank'>Links</a>. ",
				7);
	}

	@Test
	public void test_12() {
		parse("<!-- Comment -1 --><h1>Überschrift 1</h1><!-- En-de -->", 5);
	}

	@Test
	public void test_13() {
		parse("Geht's noch?", 3);
	}

	@Test
	public void test_14() {
		parse(" range > 0", 1);
	}

	@Test
	public void test_15() {
		parse("linespacing < 0", 1);
	}

	@Test
	public void test_16() {
		parse("for (int i = 0; i < values.length; i++) {", 1);
	}

	@Test
	public void test_17() {
		parse("Invalid escape character h found", 1);
	}

	private void parse(String code, int numTokens) {

		HtmlTokenizer parser = new HtmlTokenizer(new StringReader(code));
		HashMap<String, String> attrMap = new HashMap<String, String>();

		try {

			ArrayList<Token> tokenList = parser.parse();

			for (Token token : tokenList) {
				System.out.println("Token: " + token.toString());
			}

			assertEquals(numTokens, tokenList.size());

		} catch (Throwable oops) {

			if (oops instanceof NullPointerException) {
				oops.printStackTrace();
			} else {
				System.out.println(code + ":" + oops.getClass().getName() + " "
						+ oops.getMessage());
			}

			fail();
		}

		for (String name : attrMap.keySet()) {
			System.out.println(name + "='" + attrMap.get(name) + "'");
		}
	}
}

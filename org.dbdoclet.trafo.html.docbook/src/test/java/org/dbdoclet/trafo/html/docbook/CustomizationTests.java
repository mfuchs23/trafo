package org.dbdoclet.trafo.html.docbook;

import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class CustomizationTests {

	@Test
	public void testSectionNumberingPattern() {
		
		Pattern pattern = Pattern.compile("(((\\d\\.)+)?\\d?\\.?\\p{Z}*).*", Pattern.DOTALL);
		Matcher matcher = pattern.matcher("3.1.2Text\nText");
		assertTrue(matcher.matches());
	}
}

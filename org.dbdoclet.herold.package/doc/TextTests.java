package org.dbdoclet.herold;

import static org.junit.Assert.*;

import org.junit.Test;

public class TextTests extends AbstractTests {

	@Test
	public void pureTextFragment() {
		String xmlCode = transform("String");
		assertEquals("String", xmlCode);
	}

	@Test
	public void multipleTextFragments() {
		String xmlCode = transform("Lorem ipsum\n\n dolor sit amet.");
		assertEquals("Lorem ipsum dolor sit amet.", xmlCode);
	}

	@Test
	public void multipleParagraphs() {
		String xmlCode = transform("<p>Lorem ipsum<p>dolor sit amet.");
		assertEquals("<para>Lorem ipsum</para>\n<para>dolor sit amet.</para>\n", xmlCode);
	}
}
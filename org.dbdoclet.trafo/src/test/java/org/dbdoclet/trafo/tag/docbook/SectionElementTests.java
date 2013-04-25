package org.dbdoclet.trafo.tag.docbook;

import static org.junit.Assert.assertEquals;

import org.dbdoclet.trafo.SectionNumberRemover;
import org.junit.Test;

public class SectionElementTests {

	private static final String REGEXP = "((\\d+\\.)+)?\\d*\\.?\\p{Z}*";

	@Test
	public void testStripSectionNumber_1() {

		String stripped = SectionNumberRemover.stripSectionNumber(
				"1.2.3 Einführung", REGEXP);
		assertEquals("Einführung", stripped);
	}

	@Test
	public void testStripSectionNumber_2() {

		String stripped = SectionNumberRemover.stripSectionNumber(
				"10.2.3 Einführung", REGEXP);
		assertEquals("Einführung", stripped);
	}

	@Test
	public void testStripSectionNumber_3() {

		String stripped = SectionNumberRemover.stripSectionNumber(
				"1.20.3 Einführung", REGEXP);
		assertEquals("Einführung", stripped);
	}

	@Test
	public void testStripSectionNumber_4() {

		String stripped = SectionNumberRemover.stripSectionNumber(
				"1.2.30 Einführung", REGEXP);
		assertEquals("Einführung", stripped);
	}

	@Test
	public void testStripSectionNumber_5() {

		String stripped = SectionNumberRemover.stripSectionNumber(
				"10.20.30 Einführung", REGEXP);
		assertEquals("Einführung", stripped);
	}
}

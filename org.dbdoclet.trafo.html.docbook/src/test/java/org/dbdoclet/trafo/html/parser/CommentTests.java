package org.dbdoclet.trafo.html.parser;

import java.io.IOException;

import org.dbdoclet.service.ResourceServices;
import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.html.AbstractTests;
import org.junit.Ignore;
import org.junit.Test;

public class CommentTests extends AbstractTests {

	@Test
	public void testComment_1() throws TrafoException {
		transform("<!-- Kommentar -->");
	}

	@Test
	@Ignore
	public void testComment_2() throws TrafoException {
		transform("<!---------- -->");
	}

	@Test
	public void testInvalidEscapeCharacter() throws TrafoException, IOException {

		String buffer = ResourceServices
				.getResourceAsString("InvalidEscapeCharacterInComment.html");
		transform(buffer);
	}
}

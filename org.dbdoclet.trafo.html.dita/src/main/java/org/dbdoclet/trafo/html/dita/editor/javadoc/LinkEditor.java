/* 
 * ### Copyright (C) 2001-2007 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@unico-group.com
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.html.dita.editor.javadoc;

import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.html.dita.editor.DitaEditor;

/**
 * The class <code>LinkEditor</code> is reponsible for transforming @link tags
 * into DocBook code.
 * 
 * @author <a href="mailto:michael.fuchs@unico-group.com">Michael Fuchs</a>
 * @version $Revision$
 */
public class LinkEditor extends DitaEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values) {

		setValues(values);
		getTagFactory();
		return finalizeValues();
	}
}

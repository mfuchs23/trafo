/* 
 * ### Copyright (C) 2001-2007 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@unico-group.com
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.dita.editor.javadoc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.tag.dita.DitaTagFactory;
import org.dbdoclet.tag.docbook.Emphasis;
import org.dbdoclet.tag.docbook.Literal;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.internal.html.dita.editor.DitaEditor;
import org.dbdoclet.xiphias.dom.NodeImpl;

/**
 * The class <code>LinkEditor</code> is reponsible for transforming @link tags
 * into DocBook code.
 * 
 * @author <a href="mailto:michael.fuchs@unico-group.com">Michael Fuchs</a>
 * @version $Revision$
 */
public class LinkEditor extends DitaEditor {

	private static Log logger = LogFactory.getLog(LinkEditor.class);

	@Override
	public EditorInstruction edit(EditorInstruction values) {

		setValues(values);
		DitaTagFactory tagFactory = getTagFactory();
		return finalizeValues();
	}
}

/* 
 * ### Copyright (C) 2001-2007 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@unico-group.com
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor.javadoc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Emphasis;
import org.dbdoclet.tag.docbook.Literal;
import org.dbdoclet.trafo.internal.html.docbook.editor.Editor;
import org.dbdoclet.trafo.internal.html.docbook.editor.EditorInstruction;

/**
 * The class <code>LinkEditor</code> is reponsible for transforming @link tags
 * into DocBook code.
 * 
 * @author <a href="mailto:michael.fuchs@unico-group.com">Michael Fuchs</a>
 * @version $Revision$
 */
public class LinkEditor extends Editor {

	private static Log logger = LogFactory.getLog(LinkEditor.class);

	@Override
	public EditorInstruction edit(EditorInstruction values) {

		setValues(values);
		DocBookTagFactory dbfactory = values.getTagFactory();
		DocBookElement parent = getParent();

		org.dbdoclet.tag.javadoc.Link link = (org.dbdoclet.tag.javadoc.Link) getHtmlElement();

		String ref = link.getRef();
		logger.debug("ref=" + ref);

		if ((ref != null) && (ref.length() > 0)) {

			String name = link.getName();
			String label = link.getTextContent();

			Literal elem;

			if (parent instanceof Literal) {

				elem = (Literal) parent;

			} else {

				elem = dbfactory.createLiteral();
				setCurrent(elem);
				parent.appendChild(getCurrent());
			}

			if ((label != null) && (label.length() > 0)) {
				elem.appendChild(dbfactory.createLink(label, ref));
			} else {
				elem.appendChild(dbfactory.createLink(name, ref));
			}

		} else {

			Emphasis elem = dbfactory.createEmphasis(link.getName());
			setCurrent(elem);
			parent.appendChild(getCurrent());
		}

		traverse(false);

		return finalizeValues();
	}
}

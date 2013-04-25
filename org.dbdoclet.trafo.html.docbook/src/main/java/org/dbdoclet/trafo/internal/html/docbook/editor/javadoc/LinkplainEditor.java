/*
 * ### Copyright (C) 2001-2003 Michael Fuchs ###
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * Author: Michael Fuchs
 * E-Mail: mfuchs@unico-consulting.com
 *
 * RCS Information:
 * ---------------
 * Id.........: $Id: LinkplainEditor.java,v 1.1.1.1 2004/12/21 14:01:14 mfuchs Exp $
 * Author.....: $Author: mfuchs $
 * Date.......: $Date: 2004/12/21 14:01:14 $
 * Revision...: $Revision: 1.1.1.1 $
 * State......: $State: Exp $
 */
package org.dbdoclet.trafo.internal.html.docbook.editor.javadoc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Emphasis;
import org.dbdoclet.tag.docbook.Link;
import org.dbdoclet.tag.docbook.XRef;
import org.dbdoclet.tag.javadoc.Linkplain;
import org.dbdoclet.trafo.internal.html.docbook.editor.Editor;
import org.dbdoclet.trafo.internal.html.docbook.editor.EditorInstruction;

public class LinkplainEditor extends Editor {

	private static Log logger = LogFactory.getLog(LinkplainEditor.class);

	@Override
	public EditorInstruction edit(EditorInstruction values) {

		setValues(values);
		DocBookTagFactory dbfactory = values.getTagFactory();

		Linkplain linkplain = (Linkplain) getHtmlElement();

		String ref = linkplain.getRef();
		logger.debug("ref=" + ref);

		if ((ref != null) && (ref.length() > 0)) {

			String label = linkplain.getTextContent();

			if ((label != null) && (label.length() > 0)) {

				Link elem = dbfactory.createLink(label, ref);
				setCurrent(elem);

			} else {

				XRef elem = dbfactory.createXRef(ref);
				setCurrent(elem);
			}

		} else {

			Emphasis elem = dbfactory.createEmphasis(linkplain.getName());
			setCurrent(elem);
		}

		getParent().appendChild(getCurrent());

		traverse(false);

		return finalizeValues();
	}
}
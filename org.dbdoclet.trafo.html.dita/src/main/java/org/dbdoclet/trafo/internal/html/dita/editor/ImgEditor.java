/*
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.dita.editor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.service.FileServices;
import org.dbdoclet.tag.dita.DitaTagFactory;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.Emphasis;
import org.dbdoclet.tag.docbook.Entry;
import org.dbdoclet.tag.docbook.Link;
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.tag.docbook.Screen;
import org.dbdoclet.tag.docbook.Term;
import org.dbdoclet.tag.docbook.ULink;
import org.dbdoclet.tag.html.Img;
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.param.Param;
import org.dbdoclet.xiphias.dom.NodeImpl;

public class ImgEditor extends DitaEditor {

	private static Log logger = LogFactory.getLog(ImgEditor.class);

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));

		DitaTagFactory tagFactory = getTagFactory();
		return finalizeValues();
	}
}

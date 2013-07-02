/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Row;
import org.dbdoclet.tag.html.Tr;
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.script.Script;

public class TrEditor extends DocBookEditor {

    @Override
    public EditorInstruction edit(EditorInstruction values) throws EditorException {

        setValues(super.edit(values));
        DocBookTagFactory dbfactory = getTagFactory();

		boolean decomposeTables = script.isParameterOn(
				TrafoConstants.SECTION_DOCBOOK,
				TrafoConstants.PARAM_DECOMPOSE_TABLES,
				TrafoConstants.DEFAULT_DECOMPOSE_TABLES);

		if (decomposeTables || getHtmlElement().isMute()) {
            traverse(true);
            return finalizeValues();
        }

        Tr tr = (Tr) values.getHtmlElement();
        Integer height = tr.getHeight();
        
        if (height != null && height == 0) {
        	traverse(false);
        	return finalizeValues();
        }
        
        if (getParent() instanceof org.dbdoclet.tag.docbook.Tgroup
                || getParent() instanceof org.dbdoclet.tag.docbook.EntryTbl) {

            setCurrent(dbfactory.createTbody());
            getCurrent().setParentNode(getParent());
            getParent().appendChild(getCurrent());
            setParent(getCurrent());
        }

        Row row = dbfactory.createRow();
        copyCommonAttributes(getHtmlElement(), row);
        setCurrent(row);
        getParent().appendChild(getCurrent());
        traverse(true);

        return finalizeValues();
    }
}

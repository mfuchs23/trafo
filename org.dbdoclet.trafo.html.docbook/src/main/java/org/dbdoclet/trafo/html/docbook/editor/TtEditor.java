/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.html.docbook.editor;

import org.dbdoclet.tag.docbook.BaseTagFactory;
import org.dbdoclet.tag.docbook.Literal;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class TtEditor extends AbstractInlineEditor {

    @Override
    public EditorInstruction edit(EditorInstruction values) throws EditorException {

        BaseTagFactory dbfactory = getTagFactory();
        Literal literal = dbfactory.createLiteral();
        setInlineElement(literal);

        return super.edit(values);
    }
}

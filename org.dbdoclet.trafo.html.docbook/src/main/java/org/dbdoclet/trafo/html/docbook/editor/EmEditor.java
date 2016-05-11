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
import org.dbdoclet.tag.docbook.Emphasis;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class EmEditor extends AbstractInlineEditor {

    private Emphasis emphasis;

    @Override
    public EditorInstruction edit(EditorInstruction values) throws EditorException {

        BaseTagFactory dbfactory = getTagFactory();
        emphasis = dbfactory.createEmphasis();
        
        setInlineElement(emphasis);

        return super.edit(values);
    }
    
    public Emphasis getEmphasis() {
        return emphasis;
    }

}

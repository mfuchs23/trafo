/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.dita.editor;

import org.dbdoclet.tag.dita.B;
import org.dbdoclet.tag.dita.DitaTagFactory;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class EmEditor extends AbstractInlineEditor {

    private B emphasis;

    @Override
    public EditorInstruction edit(EditorInstruction values) throws EditorException {

        DitaTagFactory tagFactory = (DitaTagFactory) getTagFactory();
        emphasis = tagFactory.createB();
        setInlineElement(emphasis);
        return super.edit(values);
    }
}

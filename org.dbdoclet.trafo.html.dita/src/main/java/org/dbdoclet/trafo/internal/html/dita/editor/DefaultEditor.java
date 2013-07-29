package org.dbdoclet.trafo.internal.html.dita.editor;

import org.dbdoclet.tag.docbook.Anchor;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class DefaultEditor extends DitaEditor {

    @Override
    public EditorInstruction edit(EditorInstruction values) throws EditorException {

        EditorInstruction evo = super.edit(values);
        return evo;
    }
}

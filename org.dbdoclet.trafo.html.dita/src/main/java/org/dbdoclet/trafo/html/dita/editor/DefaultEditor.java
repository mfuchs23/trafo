package org.dbdoclet.trafo.html.dita.editor;

import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class DefaultEditor extends DitaEditor {

    @Override
    public EditorInstruction edit(EditorInstruction values) throws EditorException {

        EditorInstruction evo = super.edit(values);
        return evo;
    }
}

package org.dbdoclet.trafo.internal.html.dita.editor;

import org.dbdoclet.tag.docbook.Anchor;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;

public class DefaultEditor extends DocBookEditor {

    @Override
    public EditorInstruction edit(EditorInstruction values) throws EditorException {

        EditorInstruction evo = super.edit(values);
        
        HtmlElement html = getHtmlElement();
        String htmlId = html.getId();
        
        if (htmlId != null) {
        
            DocBookTagFactory dbf = getTagFactory();
            Anchor anchor = dbf.createAnchor();
            copyCommonAttributes(html, anchor);
            
            getParent().appendChild(anchor);
        }
        
        return evo;
    }
}

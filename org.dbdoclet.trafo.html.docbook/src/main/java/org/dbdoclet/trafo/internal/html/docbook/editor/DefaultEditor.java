package org.dbdoclet.trafo.internal.html.docbook.editor;

import org.dbdoclet.tag.docbook.Anchor;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.html.HtmlElement;

public class DefaultEditor extends Editor {

    @Override
    public EditorInstruction edit(EditorInstruction values) throws EditorException {

        EditorInstruction evo = super.edit(values);
        
        HtmlElement html = getHtmlElement();
        String htmlId = html.getId();
        
        if (htmlId != null) {
        
            DocBookTagFactory dbf = evo.getTagFactory();
            Anchor anchor = dbf.createAnchor();
            copyCommonAttributes(html, anchor);
            
            getParent().appendChild(anchor);
        }
        
        return evo;
    }
}

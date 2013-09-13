/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.ListItem;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeImpl;

public class LiEditor extends DocBookEditor {

    @Override
    public EditorInstruction edit(EditorInstruction values) throws EditorException {

        setValues(super.edit(values));

        DocBookTagFactory dbfactory = getTagFactory();

        NodeImpl parent = getParent();
        
        if (parent instanceof ListItem) {
            
            setParent((DocBookElement) getParent().getParentNode());
            parent = getParent();
        }

        DocBookElement item = dbfactory.createListItem();
        copyCommonAttributes(getHtmlElement(), item);
        
        item.setParentNode(parent);
        parent.appendChild(item);

        setCurrent(dbfactory.createPara());
        getCurrent().setParentNode(item);
        item.appendChild(getCurrent());

        traverse(true);

        return finalizeValues();
    }
}

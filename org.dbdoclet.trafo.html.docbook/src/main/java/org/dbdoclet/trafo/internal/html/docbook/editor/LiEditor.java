/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import org.dbdoclet.tag.docbook.BaseTagFactory;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.Listitem;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeImpl;

public class LiEditor extends DocBookEditor {

    @Override
    public EditorInstruction edit(EditorInstruction values) throws EditorException {

        setValues(super.edit(values));

        BaseTagFactory dbfactory = getTagFactory();

        NodeImpl parent = getParent();
        
        if (parent instanceof Listitem) {
            
            setParent((DocBookElement) getParent().getParentNode());
            parent = getParent();
        }

        DocBookElement item = dbfactory.createListitem();
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

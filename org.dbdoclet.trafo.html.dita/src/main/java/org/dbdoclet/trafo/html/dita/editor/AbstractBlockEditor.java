package org.dbdoclet.trafo.html.dita.editor;

import org.dbdoclet.tag.dita.Body;
import org.dbdoclet.tag.dita.DitaTagFactory;
import org.dbdoclet.tag.dita.Topic;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.NodeImpl;


public abstract class AbstractBlockEditor extends DitaEditor {

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		
		DitaTagFactory tagFactory = getTagFactory();
		NodeImpl parent = values.getParent();
		
		if (parent instanceof Topic) {
		
			Body body = tagFactory.createBody();
			parent.appendChild(body);
			setParent(body);
			setCurrent(body);
		}
		
		return finalizeValues();
	}
}
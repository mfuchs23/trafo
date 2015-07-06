package org.dbdoclet.trafo.html;

import org.dbdoclet.tag.TagFactory;


public interface IEditor {

	EditorInstruction edit(EditorInstruction values) throws EditorException;
	TagFactory getTagFactory();

}

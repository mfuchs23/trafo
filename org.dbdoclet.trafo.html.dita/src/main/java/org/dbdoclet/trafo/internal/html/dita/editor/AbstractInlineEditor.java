package org.dbdoclet.trafo.internal.html.dita.editor;

import org.dbdoclet.tag.dita.DitaElement;
import org.dbdoclet.tag.dita.DitaTagFactory;
import org.dbdoclet.tag.dita.P;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.xiphias.dom.ElementImpl;

public abstract class AbstractInlineEditor extends DitaEditor {

	private DitaElement inlineElement;

	public DitaElement getInlineElement() {
		return inlineElement;
	}

	public void setInlineElement(DitaElement inlineElement) {
		this.inlineElement = inlineElement;
	}

	@Override
	public EditorInstruction edit(EditorInstruction values)
			throws EditorException {

		setValues(super.edit(values));
		setCurrent(inlineElement);
		copyCommonAttributes(getHtmlElement(), inlineElement);

		DitaTagFactory tagFactory = getTagFactory();
		traverse(true);

		DitaElement parent = getDitaElementParent();

		if (parent != null && parent.isContentModel()) {

			P para = tagFactory.createP();
			para.setFormatType(ElementImpl.FORMAT_INLINE);
			parent.appendChild(para);
			setParent(para);
			parent = para;
		}

		if (getCurrent() != null && parent != null) {
			getCurrent().setParentNode(parent);
			parent.appendChild(getCurrent());
		}

		return finalizeValues();
	}

}

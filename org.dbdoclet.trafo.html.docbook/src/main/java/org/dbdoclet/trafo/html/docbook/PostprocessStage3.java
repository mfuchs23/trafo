package org.dbdoclet.trafo.html.docbook;

import java.util.HashMap;

import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Entrytbl;
import org.dbdoclet.tag.docbook.Informaltable;
import org.dbdoclet.tag.docbook.Tgroup;
import org.dbdoclet.tag.docbook.Xref;

public class PostprocessStage3 {

	private final DocBookTagFactory dbfactory;
	private final HashMap<Entrytbl, DocBookElement> subtables;

	public PostprocessStage3(DocBookTagFactory dbfactory,
			HashMap<Entrytbl, DocBookElement> subtables) {

		this.dbfactory = dbfactory;
		this.subtables = subtables;
	}

	public void process() {

		if ((subtables != null) && (subtables.size() > 0)) {

			int index = 1;

			for (Entrytbl entrytbl : subtables.keySet()) {

				String id = "subtable." + index;
				Xref xref = dbfactory.createXref(id);

				DocBookElement table = subtables.get(entrytbl);

				DocBookElement entryTblParent = (DocBookElement) entrytbl
						.getParentNode();

				entryTblParent.replaceChild(dbfactory.createEntry()
						.appendChild(xref), entrytbl);

				Informaltable it = dbfactory.createInformaltable();
				it.setId(id);
				it.setXrefLabel("#" + index);

				Tgroup tgroup = dbfactory.createTgroup();
				tgroup.setChildNodes(entrytbl.getChildNodes());
				tgroup.setTrafoAttributes(entrytbl.getAttributesAsMap());
				it.appendChild(tgroup);

				DocBookElement tableParent = (DocBookElement) table
						.getParentNode();

				if (tableParent != null) {
					tableParent.insertAfter(it, table);
				}

				index++;
			}
		}

	}
}

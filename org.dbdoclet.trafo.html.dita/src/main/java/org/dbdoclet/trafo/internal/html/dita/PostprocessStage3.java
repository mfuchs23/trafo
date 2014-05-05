package org.dbdoclet.trafo.internal.html.dita;

import java.util.HashMap;

import org.dbdoclet.tag.dita.DitaTagFactory;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.EntryTbl;
import org.dbdoclet.tag.docbook.InformalTable;
import org.dbdoclet.tag.docbook.Tgroup;
import org.dbdoclet.tag.docbook.XRef;

public class PostprocessStage3 {

	private final DitaTagFactory tagFactory;
	private final HashMap<EntryTbl, DocBookElement> subtables;

	public PostprocessStage3(DitaTagFactory tagFactory,
			HashMap<EntryTbl, DocBookElement> subtables) {

		this.tagFactory = tagFactory;
		this.subtables = subtables;
	}

	public void process() {

		DocBookTagFactory dbfactory = new DocBookTagFactory();
		
		if ((subtables != null) && (subtables.size() > 0)) {

			int index = 1;

			for (EntryTbl entrytbl : subtables.keySet()) {

				String id = "subtable." + index;
				XRef xref = dbfactory.createXRef(id);

				DocBookElement table = subtables.get(entrytbl);

				DocBookElement entryTblParent = (DocBookElement) entrytbl
						.getParentNode();

				entryTblParent.replaceChild(dbfactory.createEntry()
						.appendChild(xref), entrytbl);

				InformalTable it = dbfactory.createInformalTable();
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

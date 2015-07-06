package org.dbdoclet.trafo.internal.html.dita;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dbdoclet.progress.ProgressEvent;
import org.dbdoclet.progress.ProgressListener;
import org.dbdoclet.service.StringServices;
import org.dbdoclet.tag.dita.DitaTagFactory;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.Entrytbl;
import org.dbdoclet.tag.docbook.Informaltable;
import org.dbdoclet.tag.docbook.Itemizedlist;
import org.dbdoclet.tag.docbook.Listitem;
import org.dbdoclet.tag.docbook.Orderedlist;
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.tag.docbook.Table;
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.HtmlServices;
import org.dbdoclet.xiphias.dom.AbstractNodeVisitor;
import org.dbdoclet.xiphias.dom.ElementImpl;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class PostprocessStage1 extends AbstractNodeVisitor {

	private final ArrayList<Node> removeList;
	private final HashMap<Entrytbl, DocBookElement> subtables;
	private final Script script;

	public PostprocessStage1(DitaTagFactory tagFactory, Script script,
			ArrayList<ProgressListener> listeners) {

		super(listeners);

		this.script = script;
		removeList = new ArrayList<Node>();
		subtables = new HashMap<Entrytbl, DocBookElement>();
	}

	@Override
	public void accept(Node node) {

		fireProgressEvent(node.toString(), ProgressEvent.STAGE_ACTION);

		if (node instanceof Text) {

			replaceHtmlEntities((Text) node);
		}

		if (node instanceof ElementImpl) {

			NodeImpl elem = (NodeImpl) node;

			processPara(elem);
			processEntryTbl(elem);
			processListItem(elem);
		}
	}

	private void processListItem(NodeImpl elem) {

		if (elem == null || elem.getParentNode() == null) {
			return;
		}

		List<String> stripPrefixesList = new ArrayList<String>();
		String text = elem.getTextContent();

		if (elem instanceof Listitem
				&& elem.getParentNode() instanceof Itemizedlist) {

			stripPrefixesList = script.getTextParameterList(
					TrafoConstants.SECTION_LIST_DETECTION,
					TrafoConstants.PARAM_ITEMIZED_STRIP_PREFIX);
		}

		if (elem instanceof Listitem
				&& elem.getParentNode() instanceof Orderedlist) {

			stripPrefixesList = script.getTextParameterList(
					TrafoConstants.SECTION_LIST_DETECTION,
					TrafoConstants.PARAM_ORDERED_STRIP_PREFIX);
		}

		for (String stripPrefix : stripPrefixesList) {

			if (text.startsWith(stripPrefix)) {
				Text firstText = elem.findFirstText();
				String firstContent = firstText.getData();
				firstText.setData(StringServices.cutPrefix(firstContent,
						stripPrefix));
			}
		}
	}

	private void processEntryTbl(NodeImpl elem) {
		Node node;
		if (elem instanceof Entrytbl) {

			node = NodeImpl.findParent(elem.getParentNode(), Entrytbl.class);

			if (node != null) {

				DocBookElement parentTable = (DocBookElement) NodeImpl
						.findParent(elem, Table.class);

				if (parentTable == null) {
					parentTable = (DocBookElement) NodeImpl.findParent(elem,
							Informaltable.class);
				}

				if (parentTable != null) {
					subtables.put((Entrytbl) elem, parentTable);
				}
			}
		}
	}

	private void processPara(NodeImpl elem) {
		if (elem instanceof Para && elem.hasSiblingElements() == true
				&& elem.hasElementChildren() == false) {

			String text = elem.getTextContent();
			text = text.replace((char) 160, ' ');
			text = text.trim();

			if (text.length() == 0) {
				removeList.add(elem);
			}
		}
	}

	public void finish() {
		removeNodes(removeList);
	}

	public HashMap<Entrytbl, DocBookElement> getSubtables() {
		return subtables;
	}

	private void replaceHtmlEntities(Text child) {

		String buffer = HtmlServices.replaceEntities(child.getTextContent());
		child.setTextContent(buffer);
	}

	@Override
	public void openTag(Node node) throws Exception {
		//
	}

	@Override
	public void closeTag(Node node) throws Exception {
		//
	}
}

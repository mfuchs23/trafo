package org.dbdoclet.trafo.internal.html.docbook;

import java.util.ArrayList;
import java.util.HashMap;

import org.dbdoclet.progress.ProgressEvent;
import org.dbdoclet.progress.ProgressListener;
import org.dbdoclet.service.StringServices;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.EntryTbl;
import org.dbdoclet.tag.docbook.InformalTable;
import org.dbdoclet.tag.docbook.ItemizedList;
import org.dbdoclet.tag.docbook.ListItem;
import org.dbdoclet.tag.docbook.OrderedList;
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
	private final HashMap<EntryTbl, DocBookElement> subtables;
	private final Script script;

	public PostprocessStage1(DocBookTagFactory dbfactory, Script script,
			ArrayList<ProgressListener> listeners) {

		super(listeners);

		this.script = script;
		removeList = new ArrayList<Node>();
		subtables = new HashMap<EntryTbl, DocBookElement>();
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

		ArrayList<String> stripPrefixesList = new ArrayList<String>();
		String text = elem.getTextContent();

		if (elem instanceof ListItem
				&& elem.getParentNode() instanceof ItemizedList) {

			stripPrefixesList = script.getTextParameterList(
					TrafoConstants.SECTION_LIST_DETECTION,
					TrafoConstants.PARAM_ITEMIZED_STRIP_PREFIX);
		}

		if (elem instanceof ListItem
				&& elem.getParentNode() instanceof OrderedList) {

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
		if (elem instanceof EntryTbl) {

			node = NodeImpl.findParent(elem.getParentNode(), EntryTbl.class);

			if (node != null) {

				DocBookElement parentTable = (DocBookElement) NodeImpl
						.findParent(elem, Table.class);

				if (parentTable == null) {
					parentTable = (DocBookElement) NodeImpl.findParent(elem,
							InformalTable.class);
				}

				if (parentTable != null) {
					subtables.put((EntryTbl) elem, parentTable);
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

	public HashMap<EntryTbl, DocBookElement> getSubtables() {
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

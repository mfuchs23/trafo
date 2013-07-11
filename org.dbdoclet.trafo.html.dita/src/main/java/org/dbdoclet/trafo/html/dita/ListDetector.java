package org.dbdoclet.trafo.html.dita;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.ItemizedList;
import org.dbdoclet.tag.docbook.ListItem;
import org.dbdoclet.tag.docbook.OrderedList;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.trafo.TrafoConstants;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.param.TextParam;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.XmlServices;
import org.dbdoclet.xiphias.dom.ElementImpl;
import org.dbdoclet.xiphias.dom.NodeImpl;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ListDetector {

	private static final Log logger = LogFactory.getLog(ListDetector.class);

	private final Stack<String> cssClassStack = new Stack<String>();

	public enum ListType {
		ITEMIZED, ORDERED
	};

	public void closeList(EditorInstruction values) {

		HtmlElement child = values.getHtmlElement();
		String cssClass = child.getCssClass();
		logger.trace(String.format("Closing list %s, %s.", cssClass, child));

		NodeImpl parent = values.getParent();
		Script script = values.getScript();

		if (isItemizedListElement(child, script)
				|| isOrderedListElement(child, script)) {
			return;
		}

		if (cssClassStack.size() > 1) {
			closeNestedList(cssClass, values);
			parent = values.getParent();
		}

		Node previous = child.getPreviousSibling();
		while (previous != null && previous instanceof Element == false) {
			previous = previous.getNextSibling();
		}

		// Das Geschwisterelement vor dem aktuellen Element war ein
		// Listenelement. Die Liste wird abgeschlossen.
		// if (previous != null
		// && (isItemizedListElement((HtmlElement) previous, script) ||
		// isOrderedListElement(
		// (HtmlElement) previous, script))) {

		if (parent instanceof ListItem) {
			parent = (ElementImpl) parent.getParentNode();
		}

		if (parent instanceof ItemizedList || parent instanceof OrderedList) {
			values.setParent((ElementImpl) parent.getParentNode());
			values.setCurrent((ElementImpl) parent.getParentNode());
		}

		cssClassStack.clear();
		// }

	}

	private void closeNestedList(String cssClass, EditorInstruction values) {

		logger.trace(String.format("closeNestedList %s %s", cssClass,
				values.getHtmlElement()));

		if (cssClassStack.empty() == true) {
			return;
		}

		String currentCssClass = cssClassStack.peek();

		while (cssClassStack.size() > 1
				&& currentCssClass.equals(cssClass) == false) {

			cssClassStack.pop();
			NodeImpl parent = values.getParent();

			// Eingebettete Liste finden
			while (parent != null && parent instanceof ItemizedList == false
					&& parent instanceof OrderedList == false) {

				Node parentNode = parent.getParentNode();

				if (parentNode instanceof ElementImpl == false) {
					logger.warn("Couldn't find nested list element for HTML element "
							+ values.getHtmlElement()
							+ ". DocBookParent = "
							+ XmlServices.printPath(values.getParent())
							+ ". Stack = " + cssClassStack);
					parent = null;
					break;
				}

				parent = (ElementImpl) parentNode;
			}

			if (parent != null) {

				// Übergeordnete Liste suchen
				parent = (ElementImpl) parent.getParentNode();
				while (parent != null
						&& parent instanceof ItemizedList == false
						&& parent instanceof OrderedList == false) {

					Node parentNode = parent.getParentNode();
					if (parentNode instanceof ElementImpl == false) {
						logger.warn("Couldn't find parent list for a nested list for HTML element "
								+ values.getHtmlElement()
								+ ". DocBookParent = "
								+ XmlServices.printPath(values.getParent())
								+ ". Stack = " + cssClassStack);

						parent = null;
						break;
					}

					parent = (ElementImpl) parentNode;
				}

				// Falls eine übergeordnete Liste gefunden wurde, wird diese zum
				// neuen Vaterelement gemacht.
				if (parent != null) {
					values.setParent(parent);
					values.setCurrent(parent);
				}
			}
		}
	}

	public void edit(EditorInstruction values, DocBookTagFactory dbfactory) {

		HtmlElement htmlElement = values.getHtmlElement();
		Script script = values.getScript();

		if (isItemizedListElement(htmlElement, script)) {
			edit(values, dbfactory, ListDetector.ListType.ITEMIZED);
		} else if (isOrderedListElement(htmlElement, script)) {
			edit(values, dbfactory,ListDetector.ListType.ORDERED);
		} else if (isEndOfList(values, script)) {
			closeList(values);
		}
	}

	private boolean isEndOfList(EditorInstruction values, Script script) {

		if (cssClassStack.size() == 0) {
			return false;
		}

		HtmlElement htmlElement = values.getHtmlElement();

		while (htmlElement != null) {

			if (isItemizedListElement(htmlElement, script)
					|| isOrderedListElement(htmlElement, script)) {
				return false;
			}

			Node node = htmlElement.getParentNode();

			if (node instanceof HtmlElement) {
				htmlElement = (HtmlElement) node;
			} else {
				return true;
			}
		}

		return true;
	}

	public void edit(EditorInstruction values, DocBookTagFactory dbfactory, ListType type) {

		HtmlElement htmlElement = values.getHtmlElement();
		String cssClass = htmlElement.getCssClass();
		logger.trace(String.format("Editing list element %s, %s.", cssClass,
				htmlElement));

		NodeImpl parent = values.getParent();

		if (isNestedList(cssClass) == false && parent instanceof ListItem) {
			parent = (ElementImpl) parent.getParentNode();
		}

		parent = createListElement(type, cssClass, dbfactory, parent);

		if (isParentList(cssClass)) {
			closeNestedList(cssClass, values);
			parent = values.getParent();
		}

		ListItem listItem = dbfactory.createListItem();
		parent.appendChild(listItem);
		values.setParent(listItem);
		values.setCurrent(listItem);
	}

	private NodeImpl createListElement(ListType type, String cssClass,
			DocBookTagFactory dbfactory, NodeImpl parent) {

		if (type == ListType.ITEMIZED
				&& parent instanceof ItemizedList == false) {
			ItemizedList itemizedList = dbfactory.createItemizedList();
			parent.appendChild(itemizedList);
			parent = itemizedList;
			cssClassStack.push(cssClass);
		}

		if (type == ListType.ORDERED && parent instanceof OrderedList == false) {
			OrderedList orderedList = dbfactory.createOrderedList();
			parent.appendChild(orderedList);
			parent = orderedList;
			cssClassStack.push(cssClass);
		}

		return parent;
	}

	/**
	 * Die Methode prüft, ob das übergebene HTML-Element eine CSS-Klasse
	 * besitzt, die auf einen der regulären Ausdrücke aus der Liste des
	 * Parameters itemized-attribute-class passt. Ist dies der Fall wird mit
	 * Hilfe des Rückgabewerts true signalisiert, dass das Element eigentlich
	 * ein Listenelement einer ungeordneten Liste ist.
	 * 
	 * @param element
	 * @param script
	 * @return
	 */
	public boolean isItemizedListElement(HtmlElement element, Script script) {

		TextParam paramClasses = (TextParam) script.getParameter(
				TrafoConstants.SECTION_LIST_DETECTION,
				TrafoConstants.PARAM_ITEMIZED_ATTRIBUTE_CLASS);

		String cssClass = element.getCssClass();

		if (cssClass == null || paramClasses == null) {
			return false;
		}

		for (String regex : paramClasses.getValues()) {

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(cssClass);

			if (matcher.matches()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Die Methode prüft, ob das übergebene HTML-Element eine CSS-Klasse
	 * besitzt, die auf einen der regulären Ausdrücke aus der Liste des
	 * Parameters ordered-attribute-class passt. Ist dies der Fall wird mit
	 * Hilfe des Rückgabewerts true signalisiert, dass das Element eigentlich
	 * ein Listenelement einer geordneten Liste ist.
	 * 
	 * @param element
	 * @param script
	 * @return
	 */
	public boolean isOrderedListElement(HtmlElement element, Script script) {

		TextParam paramClasses = (TextParam) script.getParameter(
				TrafoConstants.SECTION_LIST_DETECTION,
				TrafoConstants.PARAM_ORDERED_ATTRIBUTE_CLASS);

		String cssClass = element.getCssClass();

		if (cssClass == null || paramClasses == null) {
			return false;
		}

		for (String regex : paramClasses.getValues()) {

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(cssClass);

			if (matcher.matches()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Ermittelt, ob die CSS-Klasse zu einer neuen geschachtelten Liste gehört.
	 * Dazu wird geprüft, ob sich die CSS-Klasse geändert hat und nicht einer
	 * übergeordneten Liste zu geordnet ist.
	 * 
	 * @param cssClass
	 * @return
	 */
	private boolean isNestedList(String cssClass) {

		if (cssClass == null) {
			return false;
		}

		if (cssClassStack.size() == 0
				|| cssClass.equals(cssClassStack.peek()) == false) {

			for (String elem : cssClassStack) {
				if (elem.equals(cssClass)) {
					return false;
				}
			}
			return true;
		}

		return false;
	}

	private boolean isParentList(String cssClass) {

		if (cssClass == null) {
			return false;
		}

		if (cssClassStack.size() == 0
				|| cssClass.equals(cssClassStack.peek()) == false) {

			for (String elem : cssClassStack) {
				if (elem.equals(cssClass)) {
					return true;
				}
			}
		}

		return false;
	}
}

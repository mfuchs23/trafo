package org.dbdoclet.trafo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dbdoclet.service.StringServices;
import org.dbdoclet.xiphias.dom.INodeVisitor;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class SectionNumberRemover implements INodeVisitor {

	private String regex = null;

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	@Override
	public void accept(Node node) {

		if (regex == null) {
			return;
		}

		if (node instanceof Text) {

			Text textNode = (Text) node;
			String text = textNode.getData();
			text = stripSectionNumber(text, regex);

			if (text != null) {
				textNode.setData(text);
			}
		}

		if (node instanceof Element) {

			Element elem = (Element) node;

			NamedNodeMap namedNodeMap = elem.getAttributes();

			for (int i = 0; i < namedNodeMap.getLength(); i++) {

				Node attrNode = namedNodeMap.item(i);

				if (attrNode instanceof Attr) {

					Attr attr = (Attr) attrNode;
					String value = stripSectionNumber(attr.getValue(), regex);
					attr.setValue(value);
				}
			}
		}
	}

	public static String stripSectionNumber(String title, String regex) {

		if (title == null) {
			return null;
		}

		if (regex == null) {
			return title;
		}

		regex = "(" + regex + ").*";
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(title);

		if (matcher.matches()) {
			String sectionNumber = matcher.group(1);
			title = StringServices.cutPrefix(title, sectionNumber);
		}

		return title;
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

package org.dbdoclet.trafo;

import org.dbdoclet.xiphias.XmlServices;
import org.dbdoclet.xiphias.dom.INodeVisitor;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class SpaceNormalizer implements INodeVisitor {

	public void accept(Node node) {

		if (node instanceof Text) {

			Text textNode = (Text) node;
			textNode.setData(XmlServices.normalizeText(textNode));
		}

		if (node instanceof Element) {

			Element elem = (Element) node;

			NamedNodeMap namedNodeMap = elem.getAttributes();

			for (int i = 0; i < namedNodeMap.getLength(); i++) {

				Node attrNode = namedNodeMap.item(i);

				if (attrNode instanceof Attr) {

					Attr attr = (Attr) attrNode;
					attr.setValue(XmlServices.normalizeText(attr.getValue()));
				}
			}
		}
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

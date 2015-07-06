package org.dbdoclet.trafo.html.docbook.editor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.dbdoclet.tag.docbook.BaseTagFactory;
import org.dbdoclet.tag.docbook.DocBookElement;
import org.dbdoclet.tag.docbook.DocBookTagFactory;
import org.dbdoclet.tag.docbook.Para;
import org.dbdoclet.tag.docbook.Section;
import org.dbdoclet.tag.html.Br;
import org.dbdoclet.tag.html.HtmlElement;
import org.dbdoclet.tag.html.HtmlFragment;
import org.dbdoclet.tag.html.P;
import org.dbdoclet.trafo.html.AbstractTests;
import org.dbdoclet.trafo.html.EditorException;
import org.dbdoclet.trafo.html.EditorInstruction;
import org.dbdoclet.trafo.internal.html.docbook.editor.BrEditor;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.xiphias.NodeSerializer;
import org.junit.Test;
import org.w3c.dom.Node;

public class BrEditorTests extends AbstractTests {

	@Test
	public void trappedBr_1() {

		HtmlFragment fragment = parse("<p>Erste Zeile<br>\nZweite Zeile</p>");
		P p = (P) fragment.getChildElementList().get(0);
		Br br = (Br) p.findChildElement("br");

		BaseTagFactory tf = new DocBookTagFactory();
		Section section = tf.createSection();
		Para para = tf.createPara();
		section.appendChild(para);
		para.setTextContent("Erste Zeile");
		BrEditor editor = new BrEditor();

		EditorInstruction ei = createEditorInstruction(br, para);

		try {
			ei = editor.edit(ei);
		} catch (EditorException e) {
			e.printStackTrace();
			fail();
		}

		dumpNode(section);

		ArrayList<Para> list = section.findChildren(Para.class);
		assertEquals(2, list.size());
	}

	@Test
	public void trappedBr_2() {

		HtmlFragment fragment = parse("<p>Erste Zeile<br/>Zweite Zeile</p>");
		P p = (P) fragment.getChildElementList().get(0);
		Br br = (Br) p.findChildElement("br");

		BaseTagFactory tf = new DocBookTagFactory();
		Section section = tf.createSection();
		Para para = tf.createPara();
		section.appendChild(para);
		para.setTextContent("Erste Zeile");
		BrEditor editor = new BrEditor();

		EditorInstruction ei = createEditorInstruction(br, para);

		try {
			ei = editor.edit(ei);
		} catch (EditorException e) {
			e.printStackTrace();
			fail();
		}

		dumpNode(section);

		ArrayList<Para> list = section.findChildren(Para.class);
		assertEquals(1, list.size());
	}

	private void dumpNode(Node node) {
		String buffer = new NodeSerializer().toXML(node);
		System.out.println(buffer);
	}

	private EditorInstruction createEditorInstruction(HtmlElement html,
			DocBookElement docbook) {

		Script script = new Script();
		EditorInstruction ei = new EditorInstruction(script);
		ei.setHtmlElement(html);
		ei.setCurrent(docbook);

		return ei;
	}
}

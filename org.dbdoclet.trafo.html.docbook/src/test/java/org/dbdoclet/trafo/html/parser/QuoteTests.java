package org.dbdoclet.trafo.html.parser;



import java.io.IOException;

import org.dbdoclet.tag.html.HtmlFragment;
import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.html.AbstractTests;
import org.dbdoclet.xiphias.NodeSerializer;
import org.junit.Test;

public class QuoteTests extends AbstractTests {

    @Test
    public void testQuote_1() throws TrafoException {
       HtmlFragment frag = parse("<p>Da \"<b>Fett</b>\"</p><p>WEG!!!</p>");
       System.out.println(frag.treeView());
    }

    @Test
    public void testQuote_2() throws TrafoException, IOException {
       HtmlFragment frag = parse("<p>Nach Hochkomma ' kann mit dem Zeichen</p>");
       System.out.println(frag.treeView());
       System.out.println(NodeSerializer.toXML(frag));
    }

    @Test
    public void testLt_1() throws TrafoException {
       HtmlFragment frag = parse("<p a='2>1'>Test</p>");
       System.out.println(frag.treeView());
    }

    @Test
    public void testLt_2() throws TrafoException {
       HtmlFragment frag = parse("Adresse <Name, Vorname>");
       System.out.println(frag.treeView());
    }

    @Test
    public void testLt_3() throws TrafoException {
       HtmlFragment frag = parse("Adresse 2<5 <b>Fett</b>");
       System.out.println(frag.treeView());
    }

}

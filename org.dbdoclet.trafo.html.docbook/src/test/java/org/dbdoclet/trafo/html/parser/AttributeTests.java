package org.dbdoclet.trafo.html.parser;



import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.html.AbstractTests;
import org.junit.Test;

public class AttributeTests extends AbstractTests {

    @Test
    public void testAttributeNoQuotes() throws TrafoException {
       String xmlCode = transform("<h1 align=center>Titel</h1>");
       System.out.println(xmlCode);
    }
    @Test
    public void testAttributeTrHeightZero() throws TrafoException {
       String xmlCode = transform("<table><tr height=0><td>A</td></tr></table>");
       System.out.println(xmlCode);
    }
}

package org.dbdoclet.trafo.html.parser;



import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.html.AbstractTests;
import org.junit.Test;

public class EmTests extends AbstractTests {

    @Test
    public void testSubscript_1() throws TrafoException {
       transform("<sub><em>i</em></sub>");
    }

    @Test
    public void test_2() throws TrafoException {
       transform("<em>emphasis</em> ist toll!");
    }
}

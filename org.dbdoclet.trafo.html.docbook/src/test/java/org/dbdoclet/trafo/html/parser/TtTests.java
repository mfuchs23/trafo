package org.dbdoclet.trafo.html.parser;



import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.html.AbstractTests;
import org.junit.Test;

public class TtTests extends AbstractTests {

    @Test
    public void test_1() throws TrafoException {
       transform("<tt>Literal</tt>s with trailing text.");
    }
}

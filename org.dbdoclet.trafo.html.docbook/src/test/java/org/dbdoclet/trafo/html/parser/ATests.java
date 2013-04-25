package org.dbdoclet.trafo.html.parser;

import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.html.AbstractTests;
import org.junit.Test;


public class ATests extends AbstractTests {

    @Test
    public void testA_1() {
        parse("<a href=\"href\"/>");
    }

    @Test
    public void testA_2() {
        parse("<a href=\"href'\"/>");
    }

    @Test
    public void testA_3() {
        parse("<a href=http://www.dbdoclet.org?arg1=x&arg2=y />");
    }
    
    @Test
    public void testA_4() throws TrafoException {
        transform("<a href=\"mailto:me >me</a>");
    }

 }

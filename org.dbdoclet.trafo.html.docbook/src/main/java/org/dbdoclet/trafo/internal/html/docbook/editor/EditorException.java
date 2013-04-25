/* 
 * ### Copyright (C) 2008 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo.internal.html.docbook.editor;

import java.io.PrintStream;
import java.io.PrintWriter;

public class EditorException extends Exception {

    private static final long serialVersionUID = 1L;
    private Exception cause;

    public EditorException(Exception oops) {

	super(oops.getClass().getName() + ": " + oops.getMessage());
	cause = oops;
    }

    public EditorException(String msg) {
	super(msg);
    }

    @Override
    public void printStackTrace() {

	if (cause != null) {

	    System.err.println("\nCause:");
	    cause.printStackTrace();
	    System.err.println("-------");
	}

	super.printStackTrace();
    }

    @Override
    public void printStackTrace(PrintStream stream) {

	printStackTrace(new PrintWriter(stream));
    }

    @Override
    public void printStackTrace(PrintWriter writer) {

	if (writer == null) {
	    return;
	}

	if (cause != null) {

	    writer.println("\nCause:");
	    cause.printStackTrace(writer);
	    writer.println("-------");
	}

	super.printStackTrace(writer);
    }
}

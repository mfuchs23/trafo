/* 
 * ### Copyright (C) 2005, 2009 Michael Fuchs ###
 * ### All Rights Reserved.             ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 * URL:    http://www.michael-a-fuchs.de
 */
package org.dbdoclet.trafo;

public class TrafoException extends Exception {

    private static final long serialVersionUID = 1L;

    public TrafoException() {
	super();
    }

    public TrafoException(String message) {
	super(message);
    }

    public TrafoException(String message, Throwable cause) {
	super(message, cause);
    }

    public TrafoException(Throwable cause) {
	super(cause);
    }
}

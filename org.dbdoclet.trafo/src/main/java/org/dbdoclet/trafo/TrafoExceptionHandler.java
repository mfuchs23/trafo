package org.dbdoclet.trafo;

public class TrafoExceptionHandler {

	public static Throwable getCause(Throwable oops) {

		Throwable cause = oops;

		while (cause.getCause() != null) {
			cause = cause.getCause();
		}

		return cause;
	}
}

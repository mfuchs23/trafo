/*
 * ### Copyright (C) 2001-2009 Michael Fuchs ###
 *
 * Author: Michael Fuchs
 * E-Mail: michael.fuchs@dbdoclet.org
 */
package org.dbdoclet.trafo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

import org.dbdoclet.service.StringServices;

/**
 * Die Klasse <code>TrafoResult</code> speichert das Protokoll einer
 * Transformation.
 * 
 * @author michael
 * 
 */
public class TrafoResult implements ErrorListener {

	private File file;
	private StringBuffer buffer;
	private byte[] data;
	private Throwable throwable;
	private boolean failed = false;

	public TrafoResult() {
		super();
		buffer = new StringBuffer();
	}

	public TrafoResult(File file) {

		if (file == null) {

			throw new IllegalArgumentException(
					"The argument file must not be null!");
		}

		this.file = file;
		buffer = new StringBuffer();
	}

	public void append(String line) {
		buffer.append(line);
	}

	public void error(TransformerException exception)
			throws TransformerException {
		buffer.append("[error] " + exception.getMessage());
		buffer.append('\n');
	}

	public void fatalError(TransformerException exception)
			throws TransformerException {
		buffer.append("[fatal] " + exception.getMessage());
		buffer.append('\n');
		failed = true;
	}

	public String getBuffer() {
		return buffer.toString();
	}

	public byte[] getData() {
		return data;
	}

	public File getFile() {
		return file;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public boolean isFailed() {
		return failed;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
		if (throwable != null) {
			failed = true;
		}
	}

	/**
	 * Generiert eine Zusammenfassung des Protokolls
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();

		builder.append('\n');
		builder.append(StringServices.createBox("TRANSFORMATION PROTOCOL"));

		if (buffer != null) {
			builder.append("=== STDOUT/STDERR ===\n");
			builder.append(buffer);
		}

		if (throwable != null) {

			if (throwable instanceof FileNotFoundException) {
				
				builder.append("[ERROR] File not found: " + throwable.getMessage());
				
			} else {
			
				builder.append("\n=== EXCEPTION ===\n");
				StringWriter strBuffer = new StringWriter();
				throwable.printStackTrace(new PrintWriter(strBuffer));
				builder.append(strBuffer.toString());
			}
		} 

		builder.append(">>TRANSFORMATION FINISHED.\n\n");
		
		return builder.toString();
	}

	public void warning(TransformerException exception)
			throws TransformerException {
		buffer.append(exception.getMessage());
		buffer.append('\n');
	}
}

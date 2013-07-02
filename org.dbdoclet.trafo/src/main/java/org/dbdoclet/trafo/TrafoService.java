package org.dbdoclet.trafo;

import java.io.InputStream;
import java.io.OutputStream;

import org.dbdoclet.progress.ProgressListener;
import org.dbdoclet.trafo.script.Script;

public interface TrafoService {

	public String getId();

	/**
	 * Transformation auf Basis einer TrafoScript-Datei.
	 * 
	 * @param script
	 * @param listener
	 * @return TrafoResult
	 */
	public TrafoResult transform(Script script);

	public void addProgressListener(ProgressListener listener);
	public void removeProgressListener(ProgressListener listener);
	public void setInputStream(InputStream in);
	public void setOutputStream(OutputStream out);
}

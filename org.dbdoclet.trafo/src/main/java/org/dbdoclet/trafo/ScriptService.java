package org.dbdoclet.trafo;

import java.io.File;

import org.dbdoclet.progress.ProgressListener;

public interface ScriptService {
	
	public TrafoResult executeTrafoScript(File scriptFile, ProgressListener listener);
}

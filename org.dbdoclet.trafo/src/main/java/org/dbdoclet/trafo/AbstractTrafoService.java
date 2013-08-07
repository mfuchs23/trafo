package org.dbdoclet.trafo;

import java.io.File;

public abstract class AbstractTrafoService implements TrafoService {

	private File systemId;

	@Override
	public File getSystemId() {
		return systemId;
	}

	public void setSystemId(File systemId) {
		this.systemId = systemId;
	}

	public String toString() {
		return getId();
	}
}

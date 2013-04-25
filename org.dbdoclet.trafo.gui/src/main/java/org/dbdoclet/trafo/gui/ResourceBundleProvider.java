package org.dbdoclet.trafo.gui;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.inject.Provider;

public class ResourceBundleProvider implements Provider<ResourceBundle> {

	@Override
	public ResourceBundle get() {

		// System.out.println("ResourceBundleProvider...");
		ResourceBundle res = PropertyResourceBundle.getBundle("trafo",
				Locale.getDefault(), TrafoGui.class.getClassLoader());

		// System.out.println("return resourceBundle=" + res);
		return res;
	}

}

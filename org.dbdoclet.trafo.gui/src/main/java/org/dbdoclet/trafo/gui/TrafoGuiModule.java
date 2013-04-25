package org.dbdoclet.trafo.gui;

import java.util.ResourceBundle;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class TrafoGuiModule extends AbstractModule {

	@Override
	public void configure() {
		bind(ResourceBundle.class).toProvider(ResourceBundleProvider.class).in(Scopes.SINGLETON);
		bind(TrafoMenu.class);
	}

}

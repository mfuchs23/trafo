package org.dbdoclet.trafo.gui;

import java.util.ResourceBundle;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.google.inject.Inject;

public class TrafoMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	@Inject ResourceBundle resourceBundle;

	public TrafoMenu createMenu() {
		
		JMenu menu = new JMenu(resourceBundle.getString(TrafoConstants.FILE));
		add(menu);

		JMenuItem menuItem = new JMenuItem(resourceBundle.getString(TrafoConstants.SAVE));
		menu.add(menuItem);
		
		menuItem = new JMenuItem(resourceBundle.getString(TrafoConstants.OPEN));
		menu.add(menuItem);
		
		return this;
	}
}

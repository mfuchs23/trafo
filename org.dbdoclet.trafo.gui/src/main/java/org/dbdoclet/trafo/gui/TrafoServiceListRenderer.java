package org.dbdoclet.trafo.gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import org.dbdoclet.trafo.TrafoService;

public class TrafoServiceListRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {

		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		if (value instanceof TrafoService) {
			
			TrafoService trafoService = (TrafoService) value;
			label.setText(trafoService.getId());
		}
		
		return label;
	}

}

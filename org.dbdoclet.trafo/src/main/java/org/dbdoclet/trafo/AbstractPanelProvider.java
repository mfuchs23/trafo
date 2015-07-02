package org.dbdoclet.trafo;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.dbdoclet.jive.widget.GridPanel;
import org.dbdoclet.jive.widget.LanguageListBox;
import org.dbdoclet.trafo.param.TextParam;
import org.dbdoclet.trafo.script.Namespace;
import org.dbdoclet.trafo.script.Script;

public abstract class AbstractPanelProvider extends GridPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<String> structureList;
	private Script script;
	protected JPanel panel;

	public Script createScript(JPanel panel, Script script) {
		this.panel = panel;
		this.script = script;
		structureList = new ArrayList<>();
		Component[] jcomponentArray = panel.getComponents();
		getComponentsList(jcomponentArray);
		script.getNamespace().findSection("project").setListParam("structure", structureList);
		return script;
	}

	private void getComponentsList(Component[] jcomponentArray) {

		String panelName = "Panel";
		
		Namespace namespace = script.getNamespace();
		
		for (Component comp : jcomponentArray) {

			if (comp instanceof JPanel) {
				if (comp.getName() == null) {
					getComponentsList(((JPanel) comp).getComponents());
					continue;
				}
				
				panelName = comp.getName();
				getComponentsList(((JPanel) comp).getComponents());
				
			} else if (comp instanceof JTabbedPane) {
				getComponentsList(((JTabbedPane) comp).getComponents());
			} else if (comp instanceof JLabel == false) {
				if (comp instanceof JTextField) {
					namespace.findOrCreateSection(panelName).setParam(new TextParam(comp.getName(),
							((JTextField) comp).getText()));
					continue;
				}

				if (comp instanceof JCheckBox) {
					if (comp.getName().startsWith("structure")) {
						structureList.add(((JCheckBox) comp).getToolTipText());
						continue;
					}
					namespace.findOrCreateSection(panelName).setParam(new TextParam(comp.getName(), (new Boolean(
							((JCheckBox) comp).isSelected())).toString()));
					continue;
				}

				if (comp instanceof LanguageListBox) {
					namespace.findOrCreateSection(panelName).setParam(new TextParam(comp.getName(),
							((LanguageListBox) comp).getSelectedLocale()
									.toString().toUpperCase()));
					continue;
				}

				if (comp instanceof JComboBox) {
					namespace.findOrCreateSection(panelName).setParam(new TextParam(comp.getName(), ((JComboBox<?>) comp)
							.getSelectedItem().toString()));
					continue;
				}
			}
		}
	}

}

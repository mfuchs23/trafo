package org.dbdoclet.trafo.html.dita;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import org.dbdoclet.Identifier;
import org.dbdoclet.jive.JiveFactory;
import org.dbdoclet.jive.widget.EncodingChooser;
import org.dbdoclet.jive.widget.GridPanel;
import org.dbdoclet.jive.widget.LanguageListBox;
import org.dbdoclet.service.ResourceServices;

public class HtmlDitaPanel extends GridPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private final EncodingChooser sourceEncodingChooser;
	private final LanguageListBox languageChooser;
	private final JCheckBox useAbsoluteImagePath;
	private final JComboBox<String> documentTypeComboBox;
	private final JComboBox<String> profileComboBox;
	private final JiveFactory jf;
	private final ResourceBundle res;

	public HtmlDitaPanel() {

		res = ResourceBundle
				.getBundle("org/dbdoclet/trafo/html/docbook/Resources");

		jf = JiveFactory.getInstance();

		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		sourceEncodingChooser = new EncodingChooser();
		sourceEncodingChooser.addActionListener(jf);

		addLabeledComponent(jf.createLabel(ResourceServices.getString(res,
				"C_ENCODING_SOURCE")), sourceEncodingChooser);

		incrRow();

		languageChooser = new LanguageListBox(Locale.getDefault());
		languageChooser.addActionListener(jf);

		addLabeledComponent(
				jf.createLabel(ResourceServices.getString(res, "C_LANGUAGE")),
				languageChooser);

		incrRow();

		useAbsoluteImagePath = jf.createCheckBox(new Identifier(
				"use.absolute.imagepath"), ResourceServices.getString(res,
				"C_USE_ABSOLUTE_IMAGE_PATH"));
		addComponent(useAbsoluteImagePath);

		incrRow();

		String[] documentElementList = { "article", "book", "part" };
		documentTypeComboBox = jf.createComboBox(
				new Identifier("document.type"), documentElementList);
		documentTypeComboBox.addActionListener(this);

		addLabeledComponent(jf.createLabel(ResourceServices.getString(res,
				"C_DOCUMENT_ELEMENT")), documentTypeComboBox);

		String[] profileList = { "Default", "Word", "LibreOffice" };
		profileComboBox = jf.createComboBox(new Identifier("profile"),
				profileList);
		profileComboBox.addActionListener(this);

		addLabeledComponent(
				jf.createLabel(ResourceServices.getString(res, "C_PROFILE")),
				profileComboBox);

		leaveSubPanel();

		addVerticalGlue();

		languageChooser.setSelectedLocale(Locale.getDefault());
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public String getSourceEncoding() {
		return sourceEncodingChooser.getEncoding();
	}

	public String getProfile() {

		Object selectedItem = profileComboBox.getSelectedItem();

		if (selectedItem == null) {
			return null;
		}

		return selectedItem.toString();
	}

	public String getLanguage() {
		return languageChooser.getSelectedLocale().getLanguage();
	}

	public Boolean useAbsoluteImagePath() {
		return useAbsoluteImagePath.isSelected();
	}

	public String getDocumentType() {
		return (String) documentTypeComboBox.getSelectedItem();
	}
}

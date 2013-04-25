package org.dbdoclet.trafo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.Identifier;
import org.dbdoclet.jive.JiveFactory;
import org.dbdoclet.jive.widget.GridPanel;
import org.dbdoclet.progress.ProgressEvent;
import org.dbdoclet.progress.ProgressVetoListener;
import org.dbdoclet.trafo.script.Script;

public class TrafoConsole extends JFrame implements ActionListener,
		ProgressVetoListener {

	private static final long serialVersionUID = 1l;
	private static Log logger = LogFactory.getLog(TrafoConsole.class);

	private JTextField from;
	private JTextField to;
	private JTextField source;
	private JTextField dest;
	private final HashMap<String, ArrayList<TrafoService>> trafoServiceMap;

	public TrafoConsole(String title,
			HashMap<String, ArrayList<TrafoService>> trafoServiceMap) {
		super(title);
		this.trafoServiceMap = trafoServiceMap;
		createGui();
	}

	private void createGui() {

		JiveFactory jive = JiveFactory.getInstance();
		GridPanel panel = new GridPanel();
		getContentPane().add(panel);

		from = jive.createTextField(new Identifier("from"), 11);
		from.setText("word");
		panel.addLabeledComponent("Von:", from);
		panel.incrRow();

		to = jive.createTextField(new Identifier("to"), 11);
		to.setText("docbook");
		panel.addLabeledComponent("Nach:", to);
		panel.incrRow();

		source = jive.createTextField(new Identifier("source"), 42);
		source.setText("./Werkbank/trafo5/org.dbdoclet.trafo.test/src/test/resources/doc/Siemens.doc");
		panel.addLabeledComponent("Quelldatei:", source);
		panel.incrRow();

		dest = jive.createTextField(new Identifier("dest"), 42);
		dest.setText("./Werkbank/trafo5/org.dbdoclet.trafo.test/build/docbook/Siemens.xml");
		panel.addLabeledComponent("Zieldatei:", dest);
		panel.incrRow();

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("ok");
		okButton.addActionListener(this);
		panel.addButton(okButton);

		panel.prepare();
		pack();

	}

	private TrafoService getTrafoService(String name) {

		ArrayList<TrafoService> trafoServiceList = trafoServiceMap.get(name);

		if (trafoServiceList == null || trafoServiceList.size() == 0) {
			return null;
		}

		return trafoServiceList.get(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			String fromType = from.getText();
			String toType = to.getText();

			TrafoService trafo = getTrafoService(fromType + "2" + toType);

			if (trafo != null) {

				System.out.println("Arbeitsverzeichnis: "
						+ new File(".").getAbsolutePath());

				Script script = new Script();
				trafo.setInputStream(new FileInputStream(source.getText()));
				trafo.setOutputStream(new FileOutputStream(dest.getText()));

				TrafoResult result = trafo.transform(script, this);

				if (result.getThrowable() != null) {
					result.getThrowable().printStackTrace();
				}
			}

		} catch (Exception oops) {
			logger.fatal("", oops);
		}
	}

	public int getProgressMaximum() {
		return 0;
	}

	public long getProgressStartTime() {
		return 0;
	}

	@Override
	public boolean isCanceled() {
		return false;
	}

	@Override
	public boolean progress(ProgressEvent event) {

		System.out.println(event.getAction());
		return false;
	}

	public int progressIncr() {
		return 0;
	}

	@Override
	public void setProgressMaximum(int max) {
	}

	public void setProgressStartTime(long startTime) {
	}

	@Override
	public boolean veto(ProgressEvent event) {
		return false;
	}
}

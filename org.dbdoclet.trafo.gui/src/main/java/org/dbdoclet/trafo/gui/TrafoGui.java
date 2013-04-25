package org.dbdoclet.trafo.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.Identifier;
import org.dbdoclet.jive.Anchor;
import org.dbdoclet.jive.Colspan;
import org.dbdoclet.jive.Fill;
import org.dbdoclet.jive.JiveConstants;
import org.dbdoclet.jive.JiveFactory;
import org.dbdoclet.jive.JiveServices;
import org.dbdoclet.jive.PanelProvider;
import org.dbdoclet.jive.Rowspan;
import org.dbdoclet.jive.widget.GridPanel;
import org.dbdoclet.jive.widget.StageProgressPanel;
import org.dbdoclet.jive.widget.TopPanel;
import org.dbdoclet.progress.StageProgressListener;
import org.dbdoclet.service.FileServices;
import org.dbdoclet.service.ResourceServices;
import org.dbdoclet.trafo.TrafoService;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.trafo.script.ScriptListener;
import org.osgi.service.component.ComponentContext;

import com.google.inject.Guice;

public class TrafoGui extends JFrame implements ActionListener,
		ListSelectionListener {

	private static Log log = LogFactory.getLog(TrafoGui.class);

	private static final long serialVersionUID = 1L;
	private static final String CMD_CHOOSE_INPUT = "chooseInput";
	private static final String CMD_CHOOSE_OUTPUT = "chooseOutput";

	private final JList<TrafoService> trafoServiceList;
	private ComponentContext context;
	private final JiveFactory jf;
	private JTextField inputLabel;
	private JTextField outputLabel;
	private ResourceBundle resourceBundle = PropertyResourceBundle
			.getBundle("trafo");
	private StageProgressPanel spp;
	private JButton startButton;
	private JButton cancelButton;
	private TrafoTransformer workerThread;
	private JTabbedPane workerPanel;
	private GridPanel mainPanel;
	private GridPanel trafoServiceListPanel;
	private Script script;
	private File previousInputFile = new File(
			"D:/Werkbank/Dokumentation/GSS/ElSAMarke-Gesamtsystemspezifikation.htm");

	public TrafoGui() {

		InstanceFactory.setInjector(Guice.createInjector(new TrafoGuiModule()));

		jf = JiveFactory.getInstance();
		trafoServiceList = jf.createList(new Identifier("services"));
		trafoServiceList.setDragEnabled(true);
		trafoServiceList.setCellRenderer(new TrafoServiceListRenderer());
		trafoServiceList.addListSelectionListener(this);

		DefaultListModel<TrafoService> model = new DefaultListModel<TrafoService>();
		trafoServiceList.setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		String cmd = event.getActionCommand();

		if (cmd.equals(CMD_CHOOSE_INPUT)) {

			JFileChooser chooser = new JFileChooser();
			chooser.setSelectedFile(previousInputFile);

			int rc = chooser.showOpenDialog(this);

			if (rc == JFileChooser.APPROVE_OPTION) {

				File selectedFile = chooser.getSelectedFile();

				inputLabel.setText(selectedFile.getName());
				setInputFile(selectedFile);
				previousInputFile = selectedFile;

				File outputFile = new File(
						FileServices.getFileBase(selectedFile) + ".xml");
				outputLabel.setText(outputFile.getName());
				setOutputFile(outputFile);
			}
		}

		if (cmd.equals(CMD_CHOOSE_OUTPUT)) {

			String cwd = null;

			if (previousInputFile != null) {
				cwd = previousInputFile.getParent();
			}

			JFileChooser chooser = new JFileChooser(cwd);
			int rc = chooser.showOpenDialog(this);

			if (rc == JFileChooser.APPROVE_OPTION) {
				File selectedFile = chooser.getSelectedFile();
				outputLabel.setText(selectedFile.getName());
				setOutputFile(selectedFile);
			}
		}
	}

	private void setOutputFile(File outputFile) {
		// TODO Auto-generated method stub

	}

	private void setInputFile(File selectedFile) {
		// TODO Auto-generated method stub

	}

	public void addTrafoService(final TrafoService trafoService) {

		if (trafoServiceList == null) {
			log.fatal("The trafoServiceList must not be null. Loosing trafo service "
					+ trafoService);
			return;
		}

		log.info("Adding TrafoService " + trafoService.getId());

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				DefaultListModel<TrafoService> model = (DefaultListModel<TrafoService>) trafoServiceList
						.getModel();
				model.addElement(trafoService);

				if (script != null && trafoService instanceof ScriptListener) {
					script.addScriptListener((ScriptListener) trafoService);
				}

				collapseTrafoServiceList();
			}
		});
	}

	public StageProgressListener getProgressListener() {
		return spp;
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public String getResourceString(String key) {
		return ResourceServices.getString(resourceBundle, key);
	}

	public TrafoService getTrafoService() {
		return trafoServiceList.getSelectedValue();
	}

	public TrafoTransformer getWorkerThread() {
		return workerThread;
	}

	public void removeTrafoService(final TrafoService trafoService) {

		if (trafoServiceList != null) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {

					((DefaultListModel<TrafoService>) trafoServiceList
							.getModel()).removeElement(trafoService);

					if (trafoService instanceof ScriptListener) {
						script.removeScriptListener((ScriptListener) trafoService);
					}

					collapseTrafoServiceList();
				}
			});
		}
	}

	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}

	public void setWorkerThread(TrafoTransformer workerThread) {
		this.workerThread = workerThread;
	}

	public void trafoFinished() {

		startButton.setEnabled(true);
		cancelButton.setEnabled(false);
	}

	public void beforeStart() {

		startButton.setEnabled(false);
		cancelButton.setEnabled(true);
		workerPanel.setSelectedIndex(0);
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {

		Object source = event.getSource();

		if (source == trafoServiceList) {

			workerPanel.removeAll();
			workerPanel.addTab(getResourceString(TrafoConstants.PROGRESS), spp);

			TrafoService trafoService = trafoServiceList.getSelectedValue();

			if (trafoService instanceof PanelProvider) {
				JPanel panel = ((PanelProvider) trafoService).getPanel();
				workerPanel.addTab(trafoService.getId(), panel);
				workerPanel.setSelectedComponent(panel);
			}
		}
	}

	private void collapseTrafoServiceList() {

		GridPanel pipePanel = (GridPanel) jf.getWidget(new Identifier("pipe"));

		if (trafoServiceListPanel != null) {

			DefaultListModel<TrafoService> model = (DefaultListModel<TrafoService>) trafoServiceList
					.getModel();

			boolean visible = false;

			if (model.size() > 1) {
				visible = true;
			} else if (model.size() == 1) {
				trafoServiceList.setSelectedIndex(0);
			}

			if (trafoServiceListPanel.isVisible() != visible) {

				if (pipePanel != null) {
					pipePanel.setVisible(visible);
					pipePanel.revalidate();
				}

				trafoServiceListPanel.setVisible(visible);
				trafoServiceListPanel.revalidate();

				mainPanel.revalidate();
				validate();
				pack();
			}
		}
	}

	private void createGui() {

		log.debug("creating GUI");

		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception oops) {
			oops.printStackTrace();
		}

		TrafoMenu trafoMenu = InstanceFactory.getInstance(TrafoMenu.class);
		setJMenuBar(trafoMenu.createMenu());

		script = new Script();
		// jf.setDefaultFont(Font.decode("Arial-plain-12"));
		// jf.setDefaultForeground(Color.white);
		// jf.setDefaultBackground(JiveConstants.COLOUR_GRAY_31);

		mainPanel = new GridPanel(new Insets(23, 23, 23, 23));

		TopPanel topPanel = new TopPanel();
		topPanel.setTitle("Trafo5 - Transformation GUI");
		topPanel.setForeground(Color.white);
		topPanel.setGradient(JiveConstants.COLOUR_BLUE_GRAY_1,
				JiveConstants.COLOUR_BLUE_GRAY_4);

		mainPanel.addComponent(topPanel, Colspan.CS_3, Rowspan.RS_1,
				Anchor.NORTHWEST, Fill.HORIZONTAL, new Insets(0, 0, 0, 0));
		mainPanel.incrRow();

		URL url = ResourceServices.getResourceAsUrl(
				"images/brushed_aluminium.png", this.getClass()
						.getClassLoader());
		if (url != null) {
			mainPanel.setBackgroundImage(new ImageIcon(url).getImage());
		}

		mainPanel.setGradientBackground(mainPanel.getBackground(), mainPanel
				.getBackground().darker());

		getContentPane().add(mainPanel);

		GridPanel serviceListPanel = createListPanel();
		mainPanel.addComponent(serviceListPanel, Anchor.NORTHWEST, Fill.BOTH);

		GridPanel pipePanel = createPipePanel();
		mainPanel.addComponent(pipePanel, Anchor.NORTHWEST, Fill.BOTH);

		GridPanel paramPanel = new GridPanel();
		paramPanel.setOpaque(false);
		paramPanel.setName("paramPanel");

		mainPanel.addComponent(paramPanel, Anchor.NORTHWEST, Fill.BOTH);

		JButton inputButton = jf.createButton(new Identifier(
				TrafoConstants.INPUT_FILE),
				getResourceString(TrafoConstants.INPUT_FILE));
		setMnemonic(inputButton, TrafoConstants.INPUT_FILE);
		inputButton.addActionListener(this);
		inputButton.setActionCommand(CMD_CHOOSE_INPUT);
		paramPanel.addComponent(inputButton);
		inputLabel = jf.createTextField(new Identifier("Input"), 30);
		inputLabel.setEditable(false);
		paramPanel.addComponent(inputLabel);
		paramPanel.incrRow();

		JButton outputButton = jf.createButton(new Identifier(
				TrafoConstants.OUTPUT_FILE),
				getResourceString(TrafoConstants.OUTPUT_FILE));
		setMnemonic(outputButton, TrafoConstants.OUTPUT_FILE);
		outputButton.addActionListener(this);
		outputButton.setActionCommand(CMD_CHOOSE_OUTPUT);
		paramPanel.addComponent(outputButton);
		outputLabel = jf.createTextField(new Identifier("Output"), 30);
		outputLabel.setEditable(false);
		paramPanel.addComponent(outputLabel);
		paramPanel.incrRow();

		workerPanel = jf.createTabbedPane();

		spp = new StageProgressPanel(this, "Trafo");
		workerPanel.addTab("Progress", spp);

		paramPanel.addComponent(workerPanel, Colspan.CS_2, Anchor.CENTER,
				Fill.BOTH);
		paramPanel.incrRow();

		startButton = jf.createButton("start");
		startButton.setAction(new TrafoStartAction(this));
		setMnemonic(startButton, TrafoConstants.START);
		paramPanel.addButton(startButton);

		cancelButton = jf.createButton("cancel");
		cancelButton.setAction(new TrafoCancelAction(this));
		cancelButton.setEnabled(false);
		setMnemonic(cancelButton, TrafoConstants.CANCEL);
		paramPanel.addButton(cancelButton);

		paramPanel.addVerticalGlue();

		paramPanel.prepare();

		pack();
		JiveServices.center(this);

		if (((DefaultListModel<TrafoService>) trafoServiceList.getModel())
				.size() > 0) {
			trafoServiceList.setSelectedIndex(0);
		}
	}

	private GridPanel createListPanel() {

		trafoServiceListPanel = new GridPanel();
		trafoServiceListPanel.setOpaque(false);

		Object[] serviceList = context.locateServices("trafoService");

		if (serviceList != null && serviceList.length > 0) {

			for (Object service : serviceList) {
				addTrafoService((TrafoService) service);
			}
		}

		JLabel label = jf
				.createLabel(getResourceString(TrafoConstants.TRAFO_SERVICES));

		trafoServiceListPanel.addComponent(label);
		trafoServiceListPanel.incrRow();

		JScrollPane scrollPane = new JScrollPane(trafoServiceList);
		scrollPane.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));
		trafoServiceListPanel.addComponent(scrollPane, Fill.BOTH);

		Dimension dim = trafoServiceListPanel.getPreferredSize();
		dim.width = 200;
		trafoServiceListPanel.setPreferredSize(dim);
		trafoServiceListPanel.setMaximumSize(dim);
		trafoServiceListPanel.setMinimumSize(dim);

		if (((DefaultListModel<TrafoService>) trafoServiceList.getModel())
				.size() > 1) {
			trafoServiceListPanel.setVisible(true);
		} else {
			trafoServiceListPanel.setVisible(false);
		}

		return trafoServiceListPanel;
	}

	private GridPanel createPipePanel() {

		GridPanel pipePanel = jf.createGridPanel(new Identifier("pipe"));
		pipePanel.setOpaque(false);

		JList<TrafoService> pipeList = jf
				.createList(new Identifier("pipeList"));
		pipeList.setTransferHandler(new PipeTransferHandler(
				(DefaultListModel<TrafoService>) trafoServiceList.getModel()));

		DefaultListModel<TrafoService> model = new DefaultListModel<TrafoService>();
		pipeList.setModel(model);
		pipeList.setCellRenderer(new TrafoServiceListRenderer());
		pipeList.addListSelectionListener(this);
		model.clear();

		JLabel label = jf.createLabel(getResourceString(TrafoConstants.PIPE));

		pipePanel.addComponent(label);
		pipePanel.incrRow();

		JScrollPane scrollPane = new JScrollPane(pipeList);
		scrollPane.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));
		pipePanel.addComponent(scrollPane, Fill.BOTH);

		Dimension dim = pipePanel.getPreferredSize();
		dim.width = 200;
		pipePanel.setPreferredSize(dim);
		pipePanel.setMaximumSize(dim);
		pipePanel.setMinimumSize(dim);

		if (((DefaultListModel<TrafoService>) trafoServiceList.getModel())
				.size() > 1) {
			pipePanel.setVisible(true);
		} else {
			pipePanel.setVisible(false);
		}

		return pipePanel;
	}

	private void setMnemonic(AbstractButton button, String key) {

		String mnemonicKey = key + "_m";
		String mnemonicResource = ResourceServices.getString(resourceBundle,
				mnemonicKey);

		KeyStroke keyStroke = KeyStroke.getKeyStroke(mnemonicResource);

		if (keyStroke != null) {
			button.setMnemonic(keyStroke.getKeyCode());
		}
	}

	/**
	 * OSGi Declarative Services
	 * 
	 * @param context
	 */
	protected void activate(ComponentContext context) {
		this.context = context;

		System.out.println("Activate TrafoGUI");
		createGui();
		pack();
		setVisible(true);
	}

	/**
	 * OSGi Declarative Services
	 * 
	 * @param context
	 */
	protected void deactivate(ComponentContext context) {

		setVisible(false);
		dispose();
	}

	public Script getScript() {
		return script;
	}

}

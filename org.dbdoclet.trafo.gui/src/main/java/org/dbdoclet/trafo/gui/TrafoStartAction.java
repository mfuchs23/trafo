package org.dbdoclet.trafo.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.dbdoclet.jive.JiveExceptionHandler;
import org.dbdoclet.jive.dialog.ErrorBox;
import org.dbdoclet.progress.StageProgressListener;
import org.dbdoclet.trafo.TrafoService;
import org.dbdoclet.trafo.script.Script;

public class TrafoStartAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private final TrafoGui trafoGui;

	public TrafoStartAction(TrafoGui trafoGui) {
		super(trafoGui.getResourceString(TrafoConstants.START));
		this.trafoGui = trafoGui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {

			TrafoService trafoService = trafoGui.getTrafoService();
			StageProgressListener listener = trafoGui.getProgressListener();

			Script script = trafoGui.getScript();

			if (trafoService == null) {

				ErrorBox.show(trafoGui, "Fehler", "trafoService == null");

			} else {

				TrafoTransformer trans = new TrafoTransformer(trafoService, script, listener, trafoGui);
				trafoGui.setWorkerThread(trans);
				trafoGui.beforeStart();
				trans.execute();
			}

		} catch (Throwable oops) {

			JiveExceptionHandler.handle(oops);
		}
	}
}

package org.dbdoclet.trafo.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.dbdoclet.jive.JiveExceptionHandler;

public class TrafoCancelAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private final TrafoGui trafoGui;

	public TrafoCancelAction(TrafoGui trafoGui) {
		super(trafoGui.getResourceString(TrafoConstants.CANCEL));
		this.trafoGui = trafoGui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {

			TrafoTransformer workerThread = trafoGui.getWorkerThread();
			
			if (workerThread != null) {
				workerThread.cancel(true);
			}
			
		} catch (Throwable oops) {
			
			JiveExceptionHandler.handle(oops);
		}
	}
}

package org.dbdoclet.trafo.gui;

import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.SwingWorker;

import org.dbdoclet.CanceledException;
import org.dbdoclet.jive.dialog.ErrorBox;
import org.dbdoclet.progress.ProgressManager;
import org.dbdoclet.progress.StageProgressListener;
import org.dbdoclet.service.ResourceServices;
import org.dbdoclet.trafo.TrafoResult;
import org.dbdoclet.trafo.TrafoService;
import org.dbdoclet.trafo.script.Script;

public class TrafoTransformer extends SwingWorker<TrafoResult, Object> {

	private final TrafoService trafoService;
	private final Script script;
	private final StageProgressListener listener;
	private TrafoResult result;
	private final ResourceBundle resourceBundle;
	private final TrafoGui trafoGui;

	public TrafoTransformer(TrafoService trafoService, Script script,
			StageProgressListener listener, TrafoGui trafoGui) {

		this.trafoService = trafoService;
		this.script = script;
		this.listener = listener;
		this.trafoGui = trafoGui;
		resourceBundle = trafoGui.getResourceBundle();
	}

	@Override
	protected TrafoResult doInBackground() {

		ProgressManager pm = new ProgressManager(listener);
		trafoService.addProgressListener(listener);
		result = trafoService.transform(script);


		if (result.getThrowable() != null) {

			Throwable cause = result.getThrowable();

			while (cause.getCause() != null) {
				cause = cause.getCause();
			}

			if (cause instanceof CanceledException) {

				pm.finished(ResourceServices.getString(resourceBundle,
						TrafoConstants.TRANSFORMATION_CANCELED));

			} else {

				ErrorBox errorBox = new ErrorBox(trafoGui, "Error",
						result.toString());
				errorBox.setFont(Font.decode("courier new"));
				errorBox.setVisible(true);
				errorBox.toFront();
				pm.finished(ResourceServices.getString(resourceBundle,
						TrafoConstants.TRANSFORMATION_FAILED));
			}

		} else {
			pm.finished(ResourceServices.getString(resourceBundle,
					TrafoConstants.TRANSFORMATION_SUCCESSFUL));
		}

		return result;
	}

	@Override
	protected void done() {
		trafoGui.trafoFinished();
	}
}

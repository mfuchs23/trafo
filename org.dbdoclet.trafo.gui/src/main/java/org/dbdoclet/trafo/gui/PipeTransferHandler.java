package org.dbdoclet.trafo.gui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.util.Enumeration;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

import org.dbdoclet.jive.JiveExceptionHandler;
import org.dbdoclet.trafo.TrafoService;

public class PipeTransferHandler extends TransferHandler {

	private static final long serialVersionUID = 1L;
	private final DefaultListModel<?> trafoServiceListModel;

	public PipeTransferHandler(DefaultListModel<?> trafoServiceListModel) {
		this.trafoServiceListModel = trafoServiceListModel;
	}

	@Override
	public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {

		DataFlavor flavor = DataFlavor.selectBestTextFlavor(transferFlavors);

		if (flavor != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean importData(JComponent comp, Transferable transferable) {

		try {

			DataFlavor[] flavors = transferable.getTransferDataFlavors();

			DataFlavor flavorTextPlain = null;

			for (DataFlavor flavor : flavors) {

				String mimeType = flavor.getMimeType();
				if (mimeType.equals("text/plain; class=java.lang.String")) {
					flavorTextPlain = flavor;
					break;
				}
			}

			if (flavorTextPlain == null) {
				return false;
			}

			BufferedReader reader = new BufferedReader(
					flavorTextPlain.getReaderForText(transferable));

			StringBuilder buffer = new StringBuilder();
			String line = reader.readLine();

			while (line != null) {
				buffer.append(line);
				line = reader.readLine();
			}

			reader.close();

			String trafoServiceId = buffer.toString().trim();
			TrafoService trafoService = findTrafoService(trafoServiceId);

			if (trafoService != null && comp instanceof JList) {
				JList<?> pipeList = (JList<?>) comp;
				@SuppressWarnings("unchecked")
				DefaultListModel<TrafoService> model = (DefaultListModel<TrafoService>) pipeList.getModel();
				model.addElement(trafoService);
			}

			return true;

		} catch (Exception oops) {

			JiveExceptionHandler.handle(oops);
			return false;
		}
	}

	private TrafoService findTrafoService(String trafoServiceId) {

		Enumeration<?> list = trafoServiceListModel.elements();

		while (list.hasMoreElements()) {

			Object obj = list.nextElement();

			if (obj instanceof TrafoService) {
				TrafoService trafoService = (TrafoService) obj;
				if (trafoServiceId.equals(trafoService.getId())) {
					return trafoService;
				}
			}
		}

		return null;
	}

}

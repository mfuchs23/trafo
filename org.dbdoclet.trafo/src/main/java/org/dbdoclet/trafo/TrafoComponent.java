package org.dbdoclet.trafo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbdoclet.progress.ConsoleProgressListener;
import org.dbdoclet.progress.ProgressListener;
import org.dbdoclet.trafo.script.Script;
import org.osgi.service.component.ComponentContext;

public class TrafoComponent implements ScriptService {

	private final static Log logger = LogFactory.getLog(TrafoComponent.class);
	private final HashMap<String, ArrayList<TrafoService>> trafoServiceMap;

	public TrafoComponent() {
		trafoServiceMap = new HashMap<String, ArrayList<TrafoService>>();
	}

	public void addTrafoService(TrafoService trafoService) {

		String category = trafoService.getId();

		if (category == null) {
			category = "";
		}

		ArrayList<TrafoService> trafoServiceList = trafoServiceMap
				.get(category);

		if (trafoServiceList == null) {

			trafoServiceList = new ArrayList<TrafoService>();
			trafoServiceMap.put(category, trafoServiceList);
		}

		trafoServiceList.add(trafoService);
		logger.info("Neuer TrafoService " + trafoService.getId());
	}

	/**
	 * Verarbeitung einer TrafoScript-Datei und Ausführung der Transformation
	 * falls ein passender TrafoService gestartet ist.
	 */
	@Override
	public TrafoResult executeTrafoScript(File scriptFile,
			ProgressListener listener) {

		TrafoResult result = new TrafoResult();

		try {

			TrafoScriptManager mgr = new TrafoScriptManager();
			Script script = mgr.parseScript(scriptFile);

			String type = script.getInputFormat() + "2"
					+ script.getOutputFormat();

			ArrayList<TrafoService> trafoServiceList = trafoServiceMap
					.get(type);

			if (trafoServiceList == null || trafoServiceList.size() == 0) {
				result.append(String
						.format("[Error] Can't find TrafoService for transformation type %s!",
								type));
				return result;
			}

			TrafoService trafo = trafoServiceList.get(0);
			return trafo.transform(script, listener);

		} catch (Throwable oops) {
			result.setThrowable(oops);
		}

		return result;
	}

	public ArrayList<TrafoService> getTrafoServiceList() {

		ArrayList<TrafoService> trafoServiceList = new ArrayList<TrafoService>();

		for (ArrayList<TrafoService> list : trafoServiceMap.values()) {
			trafoServiceList.addAll(list);
		}

		return trafoServiceList;
	}

	public List<TrafoService> getTrafoServiceList(String category) {

		if (category == null) {
			category = "";
		}

		return trafoServiceMap.get(category);
	}

	public void removeTrafoService(TrafoService trafoService) {

		String category = trafoService.getId();

		if (category == null) {
			category = "";
		}

		ArrayList<TrafoService> trafoServiceList = trafoServiceMap
				.get(category);

		if (trafoServiceList != null) {

			trafoServiceList.remove(trafoService);
			logger.info("Entferne TrafoService " + trafoService.getId());
		}
	}

	protected void activate(ComponentContext context) {

		logger.info("Activating Service Trafo...");

		String trnPath = System.getProperty("trafo.script");

		if (trnPath != null) {

			TrafoResult result = executeTrafoScript(new File(trnPath),
					new ConsoleProgressListener());

			if (result != null) {
				System.out.println(result.toString());
			} else {
				System.out.println("NO RESULT!!!");
			}
		}
	}

	protected void deactivate(ComponentContext context) {

		logger.info("Tidbit Deaktivierung");
	}
}

package org.dbdoclet.trafo.script;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.dbdoclet.service.UnicodeServices;
import org.dbdoclet.trafo.param.BooleanParam;
import org.dbdoclet.trafo.param.NumberParam;
import org.dbdoclet.trafo.param.Param;
import org.dbdoclet.trafo.param.TextParam;

public class Script {

	public static final String DEFAULT_NAMESPACE = "";
	private static final String DEFAULT_SECTION = "main";
	private static final String SECTION_SYSTEM = "SYS PARAMS";
	public static final String SYSPARAM_TRANSFORMATION_NAME = "transformation.name";
	private static final String SECTION_INPUT = "input";
	private static final String SECTION_OUTPUT = "output";
	private static final String PARAM_FORMAT = "format";

	private final LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Param<?>>>> namespaceMap;
	private LinkedHashMap<String, Param<?>> currentParamMap;
	private final LinkedHashMap<String, Param<?>> variableMap;
	private ArrayList<ScriptListener> listeners;

	public Script() {
		namespaceMap = new LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Param<?>>>>();
		variableMap = new LinkedHashMap<String, Param<?>>();
	}

	public void addBoolParam(String name, boolean flag) {

		if (currentParamMap == null) {
			throw new IllegalStateException(
					"Field currentParamMap must not be null!");
		}

		BooleanParam param = (BooleanParam) currentParamMap.get(name);

		if (param == null) {
			param = new BooleanParam(name, flag);
			currentParamMap.put(name, param);
		} else {
			param.addValue(flag);
		}
	}

	public void addBoolParam(String name, String bool) {

		addBoolParam(name, Boolean.valueOf(bool));
	}

	public void addNumberParam(String name, int number) {

		if (currentParamMap == null) {
			throw new IllegalStateException(
					"Field currentParamMap must not be null!");
		}

		NumberParam param = (NumberParam) currentParamMap.get(name);

		if (param == null) {
			param = new NumberParam(name, number);
			currentParamMap.put(name, param);
		} else {
			param.addValue(number);
		}
	}

	public void addNumberParam(String name, String number) {
		addNumberParam(name, Integer.valueOf(number));
	}

	public void addScriptListener(ScriptListener listener) {

		if (listener == null) {
			return;
		}

		if (listeners == null) {
			listeners = new ArrayList<ScriptListener>();
		}

		if (listeners.contains(listener) == false) {
			listeners.add(listener);
		}
	}

	public void addTextParam(String name, String text) {

		if (currentParamMap == null) {
			throw new IllegalStateException(
					"Field currentParamMap must not be null!");
		}

		text = UnicodeServices.unescape(text);
		TextParam param = (TextParam) currentParamMap.get(name);

		if (param == null) {
			param = new TextParam(name, new String(text));
			currentParamMap.put(name, param);
		} else {
			param.addValue(new String(text));
		}
	}

	public String dump() {

		StringBuilder buffer = new StringBuilder();

		for (String namespace : namespaceMap.keySet()) {

			System.out.println("\n+++ Namespace: " + namespace);

			LinkedHashMap<String, LinkedHashMap<String, Param<?>>> sectionMap = namespaceMap
					.get(namespace);

			for (String section : sectionMap.keySet()) {

				System.out.println("> Section: " + section);

				LinkedHashMap<String, Param<?>> paramMap = sectionMap
						.get(section);

				for (String name : paramMap.keySet()) {

					Param<?> param = paramMap.get(name);
					System.out.println(param.toString());
				}
			}
		}

		return buffer.toString();
	}

	private LinkedHashMap<String, Param<?>> findParamMap(String namespace,
			String section) {

		if (namespace == null) {
			namespace = DEFAULT_NAMESPACE;
		}

		if (section == null) {
			section = DEFAULT_SECTION;
		}

		LinkedHashMap<String, LinkedHashMap<String, Param<?>>> sectionMap = namespaceMap
				.get(namespace);

		if (sectionMap == null) {
			sectionMap = new LinkedHashMap<String, LinkedHashMap<String, Param<?>>>();
			namespaceMap.put(namespace, sectionMap);
		}

		LinkedHashMap<String, Param<?>> paramMap = sectionMap.get(section);

		if (paramMap == null) {
			paramMap = new LinkedHashMap<String, Param<?>>();
			sectionMap.put(section, paramMap);
		}

		return paramMap;
	}

	public void fireScriptEvent(ScriptEvent<?> event) {

		if (listeners == null) {
			return;
		}

		for (ScriptListener listener : listeners) {
			listener.scriptChanged(event);
		}
	}

	public String getInputFormat() {
		return getInputFormat(DEFAULT_NAMESPACE);
	}

	public String getInputFormat(String namespace) {
		return getTextParameter(namespace, SECTION_INPUT, PARAM_FORMAT, null);
	}

	public int getIntParameter(String section, String name, int def) {
		return getIntParameter(DEFAULT_NAMESPACE, section, name, def);
	}

	public int getIntParameter(String namespace, String section, String name,
			int def) {

		Param<?> param = getParameter(namespace, section, name);

		if (param == null) {
			return def;
		}

		return (Integer) param.getValue();
	}

	public String getOutputFormat() {
		return getOutputFormat(DEFAULT_NAMESPACE);
	}

	public String getOutputFormat(String namespace) {
		return getTextParameter(namespace, SECTION_OUTPUT, PARAM_FORMAT, null);
	}

	public Param<?> getParameter(String section, String name) {
		return getParameter(DEFAULT_NAMESPACE, section, name);
	}

	public Param<?> getParameter(String namespace, String section, String name) {

		LinkedHashMap<String, LinkedHashMap<String, Param<?>>> sectionMap = namespaceMap
				.get(namespace);

		if (sectionMap == null) {
			return null;
		}

		LinkedHashMap<String, Param<?>> paramMap = sectionMap.get(section);

		if (paramMap == null) {
			return null;
		}

		return paramMap.get(name);
	}

	@SuppressWarnings("unchecked")
	public <T> T getParameterValue(String namespace, String section,
			String name, T def) {

		Param<?> param = getParameter(namespace, section, name);

		if (param == null) {
			return def;
		}

		T value = def;

		try {

			Object paramValue = param.getValue();

			if (paramValue != null) {
				value = ((T) paramValue);
			}

		} catch (Throwable oops) {
			oops.printStackTrace();
		}

		return value;
	}

	public <T> T getParameterValue(String section, String name, T def) {
		return getParameterValue(DEFAULT_NAMESPACE, section, name, def);
	}

	public LinkedHashMap<String, LinkedHashMap<String, Param<?>>> getSectionMap(
			String namespace) {
		return namespaceMap.get(namespace);
	}

	public Param<?> getSystemParameter(String namespace, String name) {

		LinkedHashMap<String, Param<?>> paramMap = findParamMap(namespace,
				SECTION_SYSTEM);

		return paramMap.get(name);
	}

	public String getTextParameter(String section, String name, String def) {
		return getTextParameter(DEFAULT_NAMESPACE, section, name, def);
	}

	public String getTextParameter(String namespace, String section,
			String name, String def) {

		Param<?> param = getParameter(namespace, section, name);

		if (param == null) {
			return def;
		}

		return param.getValueAsText();
	}

	public List<String> getTextParameterList(String section, String name) {
		return getTextParameterList(DEFAULT_NAMESPACE, section, name,
				new ArrayList<String>());
	}

	public List<String> getTextParameterList(String section, String name,
			List<String> def) {
		return getTextParameterList(DEFAULT_NAMESPACE, section, name, def);
	}

	public List<String> getTextParameterList(String namespace,
			String section, String name, List<String> def) {

		Param<?> param = getParameter(namespace, section, name);

		if (param == null) {
			return def;
		}

		ArrayList<?> values = param.getValues();

		ArrayList<String> list = new ArrayList<String>(values.size());

		for (Object value : values) {
			list.add(value.toString());
		}

		return list;
	}

	public Param<?> getVariable(String param) {
		return variableMap.get(param);
	}

	public boolean isEnabled(String section, String name) {

		Param<?> param = getParameter(section, name);

		if (param != null) {
			param.isEnabled();
		}

		return false;
	}

	public boolean isParameterOn(String section, String name, boolean def) {
		return isParameterOn(DEFAULT_NAMESPACE, section, name, def);
	}

	public boolean isParameterOn(String namespace, String section, String name,
			boolean def) {

		Param<?> param = getParameter(namespace, section, name);

		if (param == null) {
			return def;
		}

		Object value = param.getValue();

		if (value == null) {
			return false;
		}

		if (value instanceof Boolean) {
			return (Boolean) value;
		}

		return Boolean.valueOf(value.toString());
	}

	public void mergeNamespaces() {

		LinkedHashMap<String, LinkedHashMap<String, Param<?>>> defSectionMap = namespaceMap
				.get(DEFAULT_NAMESPACE);

		if (defSectionMap == null) {
			defSectionMap = new LinkedHashMap<String, LinkedHashMap<String, Param<?>>>();
		}

		ArrayList<String> removeList = new ArrayList<String>();

		for (String namespace : namespaceMap.keySet()) {

			if (namespace.equals(DEFAULT_NAMESPACE)) {
				continue;
			}

			LinkedHashMap<String, LinkedHashMap<String, Param<?>>> sectionMap = namespaceMap
					.get(namespace);

			for (String section : sectionMap.keySet()) {

				LinkedHashMap<String, Param<?>> paramMap = sectionMap
						.get(section);

				LinkedHashMap<String, Param<?>> defParamMap = defSectionMap
						.get(section);

				if (defParamMap == null) {
					defParamMap = new LinkedHashMap<String, Param<?>>();
					defSectionMap.put(section, defParamMap);
				}

				for (String name : paramMap.keySet()) {

					Param<?> param = paramMap.get(name);
					defParamMap.put(name, param);
				}
			}

			removeList.add(namespace);
		}

		for (String namespace : removeList) {
			namespaceMap.remove(namespace);
		}
	}

	public void removeScriptListener(ScriptListener listener) {

		if (listeners == null || listener == null) {
			return;
		}

		if (listeners.contains(listener)) {
			listeners.remove(listener);
		}
	}

	public void selectSection(String section) {
		selectSection(DEFAULT_NAMESPACE, section);
	}

	public void selectSection(String namespace, String section) {

		LinkedHashMap<String, Param<?>> paramMap = findParamMap(namespace,
				section);
		currentParamMap = paramMap;
	}

	public void setBoolParameter(String name, boolean flag) {

		if (currentParamMap == null) {
			throw new IllegalStateException(
					"Field currentParamMap must not be null!");
		}

		BooleanParam param = new BooleanParam(name, flag);
		currentParamMap.put(name, param);
	}

	public void setEnabled(String section, String name, boolean flag) {

		Param<?> param = getParameter(section, name);

		if (param != null) {
			param.setEnabled(flag);
		}
	}

	public void setListParam(String name, ArrayList<String> textList) {

		@SuppressWarnings("unchecked")
		Param<ArrayList<String>> param = (Param<ArrayList<String>>) currentParamMap
				.get(name);
		param = new Param<ArrayList<String>>(name, textList);
		currentParamMap.put(name, param);
	}

	public void setSystemParameter(String name, String value) {
		setSystemParameter(DEFAULT_NAMESPACE, name, value);
	}

	public void setSystemParameter(String namespace, String name, String value) {

		LinkedHashMap<String, Param<?>> paramMap = findParamMap(namespace,
				SECTION_SYSTEM);
		paramMap.put(name, new TextParam(name, value));
	}

	public void setTextParameter(String name, String text) {

		if (currentParamMap == null) {
			throw new IllegalStateException(
					"The field currentParamMap must not be null! Select a scetion first.");
		}

		TextParam param = (TextParam) currentParamMap.get(name);
		param = new TextParam(name, new String(text));
		currentParamMap.put(name, param);
	}

	public void setVariable(Param<?> param) {
		variableMap.put(param.getName(), param);
	}
}

package org.dbdoclet.trafo.script;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.dbdoclet.service.StringServices;
import org.dbdoclet.tag.ITransformPosition;
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

	private final LinkedHashMap<String, Param<?>> variableMap;
	private final LinkedHashMap<String, Namespace> namespaceMap;
	
	private ArrayList<ScriptListener> listeners;
	private ITransformPosition transformPosition;
	private ArrayList<String> contextList;

	public enum SectionType {
		SECTION, NODE, ATTRIBUTE;
	};
	
	public Script() {
		namespaceMap = new LinkedHashMap<>();
		variableMap = new LinkedHashMap<>();
		contextList = new ArrayList<>();
	}

	public void addContext(String context) {
		contextList.add(0, context);
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

	public String dump() {

		StringBuilder buffer = new StringBuilder();

		for (String namespaceName : namespaceMap.keySet()) {

			System.out.println("\n+++ Namespace: " + namespaceName);
			Namespace namespace = namespaceMap.get(namespaceName);
			
			for (Section section : namespace.getSections()) {
				System.out.println("> Section: " + section.getName());
				for (Param<?> param : section.getParameters()) {
					System.out.println(param.toString());
				}
			}
			
			for (NodeRule nodeRule: namespace.getNodeRules()) {
				System.out.println("> Section: " + nodeRule.getName());
				for (Param<?> param : nodeRule.getParameters()) {
					System.out.println(param.toString());
				}
			}
			for (Section section : namespace.getSections()) {
				System.out.println("> Section: " + section.getName());
				for (Param<?> param : section.getParameters()) {
					System.out.println(param.toString());
				}
			}
		}

		return buffer.toString();
	}

	private Section findSection(String namespaceName, String name) {

		if (namespaceName == null) {
			namespaceName = DEFAULT_NAMESPACE;
		}

		if (name == null) {
			name = DEFAULT_SECTION;
		}

		Namespace namespace = namespaceMap.get(namespaceName);

		if (namespace == null) {
			return null;
		}

		return namespace.findSection(name);
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

	public Collection<NodeRule> getNodeRules(String section, String name) {
		return getNodeRules(DEFAULT_NAMESPACE, section, name);
	}

	private Collection<NodeRule> getNodeRules(
			String namespaceName, String section, String name) {
		
		Namespace namespace = namespaceMap.get(namespaceName);
		if (namespace == null) {
			return null;
		}
		
		return namespace.getNodeRules();
	}

	public Param<?> getParameter(String section, String name) {
		return getParameter(DEFAULT_NAMESPACE, section, name);
	}

	public Param<?> getParameter(String namespaceName, String sectionName, String paramName) {

		Namespace namespace = namespaceMap.get(namespaceName);

		if (namespace == null) {
			return null;
		}

		List<Section> specificSectionList = namespace.getSections().stream()
				.filter(section -> contextList.contains(section.getName()))
				.collect(Collectors.toList());
			
		List<Section> regexpSectionList = namespace.getSections().stream()
				.filter(section -> {
					if (section.getName().startsWith("/") && section.getName().endsWith("/")) {
						String regexp = StringServices.trim(section.getName(), "/");
						return contextList.stream().filter(contextName -> contextName.matches(regexp)).collect(Collectors.toList()).size() > 0;
					} else {
						return false;
					}
				})
				.collect(Collectors.toList());

		
		Section section = namespace.findSection(sectionName);

		if (specificSectionList != null && specificSectionList.size() > 0) {
			Param<?> param = specificSectionList.get(0).findParameter(paramName);
			if (param != null) {
				return param;
			}
		}		

		if (regexpSectionList != null && regexpSectionList.size() > 0) {
			Param<?> param = regexpSectionList.get(0).findParameter(paramName);
			if (param != null) {
				return param;
			}
		}
		
		if (section == null) {
			return null;
		}
		
		return section.findParameter(paramName);
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

	public Namespace getNamespace(
			String namespaceName) {
		return namespaceMap.get(namespaceName);
	}

	public Param<?> getSystemParameter(String namespace, String name) {

		Section section = findSection(namespace, SECTION_SYSTEM);

		if (section == null) {
			return null;
			
		}
		
		return section.findParameter(name);
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

	public ITransformPosition getTransformPosition() {
		return transformPosition;
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

		Namespace defNamespace = namespaceMap.get(DEFAULT_NAMESPACE);

		if (defNamespace == null) {
			defNamespace = new Namespace(DEFAULT_NAMESPACE);
		}

		ArrayList<Namespace> removeList = new ArrayList<Namespace>();

		for (Namespace namespace : namespaceMap.values()) {

			if (namespace.getName().equals(DEFAULT_NAMESPACE)) {
				continue;
			}

			for (Section section : namespace.getSections()) {

				
				Section defSection = defNamespace.findSection(section.getName());
				
				if (defSection == null) {
					defSection = new Section(section.getName());
					defNamespace.addSection(defSection);
				}
				
				for (Param<?> param : section.getParameters()) {
					defSection.addParam(param);
				}
			}

			removeList.add(namespace);
		}

		for (Namespace namespace : removeList) {
			namespaceMap.remove(namespace);
		}
	}

	public void removeContext(String context) {
		contextList.remove(context);
	}

	public void removeScriptListener(ScriptListener listener) {

		if (listeners == null || listener == null) {
			return;
		}

		if (listeners.contains(listener)) {
			listeners.remove(listener);
		}
	}

	/*
	public void setBoolParameter(String name, boolean flag) {

		if (currentMap == null) {
			throw new IllegalStateException(
					"Field currentParamMap must not be null!");
		}

		BooleanParam param = new BooleanParam(name, flag);
		currentMap.put(name, param);
	}
	*/
	
	public void setEnabled(String section, String name, boolean flag) {

		Param<?> param = getParameter(section, name);

		if (param != null) {
			param.setEnabled(flag);
		}
	}

	public void setSystemParameter(String name, String value) {
		setSystemParameter(DEFAULT_NAMESPACE, name, value);
	}

	public void setSystemParameter(String namespaceName, String name, String value) {

		Namespace namespace = namespaceMap.get(namespaceName);
		
		if (namespace == null) {
			namespace = new Namespace(namespaceName);
			namespaceMap.put(namespaceName, namespace);
		}
		
		Section section = namespace.findSection(SECTION_SYSTEM);
		
		if (section == null) {
			section = new Section(SECTION_SYSTEM);
			namespace.addSection(section);
		}
		
		section.addParam(new TextParam(name, value));
	}
	
	public void setTransformPosition(ITransformPosition transformPosition) {
		this.transformPosition = transformPosition;
	}

	public void setVariable(Param<?> param) {
		variableMap.put(param.getName(), param);
	}

	public void unsetVariable(String varname) {
		variableMap.remove(varname);
	}

	public void addNamespace(Namespace namespace) {
		namespaceMap.put(namespace.getName(), namespace);
	}

	public Namespace getNamespace() {
		
		Namespace namespace = getNamespace(DEFAULT_NAMESPACE);
		
		if (namespace == null) {
			namespace = new Namespace(DEFAULT_NAMESPACE);
			addNamespace(namespace);
		}
		
		return namespace;
	}
}

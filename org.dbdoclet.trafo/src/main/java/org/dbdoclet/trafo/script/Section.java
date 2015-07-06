package org.dbdoclet.trafo.script;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import org.dbdoclet.trafo.param.Param;
import org.dbdoclet.trafo.param.TextParam;

public class Section {

	private String name;
	private final TreeMap<String, Param<?>> parameterMap = new TreeMap<>();

	public Section() {
	}

	public Section(String name) {
		this.name = name;
	}

	@SuppressWarnings("unchecked")
	public <T> void addParam(Param<T> param) {

		if (param == null) {
			return;
		}

		Param<?> p = parameterMap.get(param.getName());
		if (p == null) {
			parameterMap.put(param.getName(), param);
		} else {
			if (param.getClass().isInstance(p)) {
				((Param<T>) p).addValue(param.getValue());
			} else {
				throw new IllegalStateException(
						String.format(
								"Parameter %s is defined twice with different types %s and %s!",
								param.getName(), param.getClass()
										.getSimpleName(), p.getClass()
										.getSimpleName()));
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Param<?>> getParameters() {
		return parameterMap.values();
	}

	public Param<?> findParameter(String paramName) {
		return parameterMap.get(paramName);
	}

	public void setParam(Param<?> param) {
		parameterMap.put(param.getName(), param);
	}

	public void setListParam(String name, ArrayList<String> textList) {

		Param<ArrayList<String>> param = new Param<>(name, textList);
		parameterMap.put(name, param);
	}

	public TextParam findTextParameter(String paramName) {

		Param<?> p = findParameter(paramName);
		
		if (TextParam.class.isInstance(p)) {
			return (TextParam) p;
		}
		
		return null;
	}
	
}

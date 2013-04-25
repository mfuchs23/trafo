package org.dbdoclet.trafo.param;

import java.util.ArrayList;

import org.dbdoclet.service.StringServices;

public class Param<T> {

	private String name;
	private final ArrayList<T> valueList;
	private boolean enabled = true;

	public Param(String name, T value) {

		this.name = name;
		valueList = new ArrayList<T>();
		valueList.add(value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getValue() {

		if (valueList.size() == 0) {
			return null;
		}

		return valueList.get(0);
	}

	public T getValue(int index) {

		if (index < 0 && index >= valueList.size()) {
			return null;
		}

		return valueList.get(index);
	}

	public ArrayList<T> getValues() {
		return valueList;
	}

	public void addValue(T value) {
		valueList.add(value);
	}

	public void setValue(T value) {

		valueList.clear();
		valueList.add(value);
	}

	public boolean isArray() {
		return valueList.size() > 1;
	}

	// public boolean isEnabled() {
	//
	// T value = getValue();
	//
	// if (value instanceof Boolean) {
	// return ((Boolean) value).booleanValue();
	// }
	//
	// if (value instanceof String) {
	// return Boolean.valueOf((String) value);
	// }
	//
	// if (value instanceof Number) {
	// return ((Number) value).doubleValue() != 0;
	// }
	//
	// return false;
	// }

	public String getValueAsText() {

		if (valueList == null || valueList.size() == 0) {
			return null;
		}

		if (valueList.size() == 1) {
			return getValue().toString();
		}

		StringBuilder buffer = new StringBuilder();

		for (T value : valueList) {

			if (value == null) {
				continue;
			}

			buffer.append(value.toString());
			buffer.append(", ");
		}

		String ret = buffer.toString().trim();
		return StringServices.cutSuffix(ret, ",");
	}

	@Override
	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append(name);
		buffer.append(" = ");

		if (valueList != null) {

			if (valueList.size() == 1) {
				Object valueObject = valueList.get(0);
				appendValueObject(buffer, valueObject);
			}

			if (valueList.size() > 1) {

				buffer.append("[ ");

				for (Object valueObject : valueList) {
					appendValueObject(buffer, valueObject);
					buffer.append(", ");
				}

				buffer.replace(buffer.length() - 2, buffer.length(), " ]");
			}
		}

		return buffer.toString();
	}

	private void appendValueObject(StringBuilder buffer, Object valueObject) {
		if (valueObject instanceof String) {
			buffer.append('"');
			buffer.append(valueObject);
			buffer.append('"');
		} else {
			buffer.append(valueObject);
		}
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}
}

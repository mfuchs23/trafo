package org.dbdoclet.trafo.script;

import java.io.Serializable;


public class ScriptEvent<T> implements Serializable {

	public enum Type { INPUT_FILE_CHANGED, OUTPUT_FILE_CHANGED }

	private static final long serialVersionUID = 1L;

	private final Type type;
	private final T value;
	private final Script script;
	
	public ScriptEvent(Script script, T value, Type type) {

		this.script = script;
		this.value = value;
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public T getValue() {
		return value;
	}

	public Script getScript() {
		return script;
	}
	
}

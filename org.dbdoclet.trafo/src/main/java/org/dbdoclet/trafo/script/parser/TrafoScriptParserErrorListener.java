package org.dbdoclet.trafo.script.parser;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class TrafoScriptParserErrorListener extends BaseErrorListener {

	private ArrayList<String> errorList = new ArrayList<>();
	
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer,
			Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		
		errorList.add(String.format("%s:%d:%d %s", recognizer.getInputStream().getSourceName(), line, charPositionInLine, msg));
	}

	public List<String> getErrors() {
		return errorList;
	}

}

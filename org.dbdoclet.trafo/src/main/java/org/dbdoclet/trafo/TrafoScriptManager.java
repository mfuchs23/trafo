package org.dbdoclet.trafo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.dbdoclet.trafo.param.Param;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.trafo.script.parser.TrafoScriptLexer;
import org.dbdoclet.trafo.script.parser.TrafoScriptListener;
import org.dbdoclet.trafo.script.parser.TrafoScriptListenerImpl;
import org.dbdoclet.trafo.script.parser.TrafoScriptParser;
import org.dbdoclet.trafo.script.parser.TrafoScriptParserErrorListener;

public class TrafoScriptManager {

	/**
	 * Parsen einer Skript-Datei.
	 * 
	 * @param scriptFile
	 * @return Script
	 * @throws TrafoException
	 */
	public Script parseScript(File scriptFile) throws TrafoException {

		Script script = new Script();
		parseScript(script, scriptFile, "");
		return script;
	}

	public void parseScript(Script script, File scriptFile)
			throws TrafoException {

		parseScript(script, scriptFile, "");
	}

	public void parseScript(Script script, File scriptFile, String namespace)
			throws TrafoException {

		try {
			TrafoScriptLexer lex = new TrafoScriptLexer(new ANTLRFileStream(
					scriptFile.getAbsolutePath(), "UTF8"));
			parseScript(script, lex, namespace);
		} catch (Exception oops) {
			throw new TrafoException(oops);
		}
	}

	public void parseScript(Script script, InputStream instr, String namespace)
			throws TrafoException {

		try {
			TrafoScriptLexer lex = new TrafoScriptLexer(new ANTLRInputStream(
					instr));
			parseScript(script, lex, namespace);
		} catch (Exception oops) {
			throw new TrafoException(oops);
		}
	}

	/**
	 * Parsen eines Skript-Puffers.
	 * 
	 * @param scriptBuffer
	 * @return Script
	 * @throws IOException
	 * @throws RecognitionException
	 * @throws TrafoException
	 */
	public Script parseScript(String scriptBuffer) throws IOException,
			RecognitionException, TrafoException {

		TrafoScriptLexer lex = new TrafoScriptLexer(new ANTLRInputStream(scriptBuffer));

		Script script = new Script();
		parseScript(script, lex, "");
		return script;
	}

	private Script parseScript(Script script, TrafoScriptLexer lex,
			String namespace) throws RecognitionException, TrafoException {

		CommonTokenStream tokens = new CommonTokenStream(lex);

		TrafoScriptParser parser = new TrafoScriptParser(tokens);
		parser.removeErrorListeners();
		TrafoScriptParserErrorListener errorListener = new TrafoScriptParserErrorListener();
		parser.addErrorListener(errorListener);
		ParseTree parserTree  = parser.parse();

		List<String> errors = errorListener.getErrors();

		if (errors != null && errors.size() > 0) {
			throw new TrafoException(errors.get(0));
		}

		ParseTreeWalker walker = new ParseTreeWalker();
		TrafoScriptListener listener = new TrafoScriptListenerImpl(script, namespace);
		walker.walk(listener, parserTree);
		
		return script;
	}

	public void parseScript(Script script, String scriptBuffer)
			throws TrafoException {

		try {
			TrafoScriptLexer lex = new TrafoScriptLexer(new ANTLRInputStream(
					scriptBuffer));
			parseScript(script, lex, "");
		} catch (Exception oops) {
			throw new TrafoException(oops);
		}
	}

	public void writeScript(Script script, File file) throws IOException {
		writeScript(Script.DEFAULT_NAMESPACE, script, file);
	}

	public void writeScript(String namespace, Script script, File file)
			throws IOException {
		writeScript(namespace, script, new FileWriter(file));
	}

	public void writeScript(Script script, Writer writer) throws IOException {
		writeScript(Script.DEFAULT_NAMESPACE, script, writer);
	}

	public void writeScript(String namespace, Script script, Writer writer)
			throws IOException {

		PrintWriter printWriter = null;

		try {

			printWriter = new PrintWriter(writer);

			String transformationName = "trafo";

			Param<?> sysparam = script.getSystemParameter(namespace,
					Script.SYSPARAM_TRANSFORMATION_NAME);

			if (sysparam != null) {
				transformationName = (String) sysparam.getValue();
			}

			printWriter.println(String.format("transformation %s;",
					transformationName));

			LinkedHashMap<String, LinkedHashMap<String, Param<?>>> sectionMap = script
					.getSectionMap(namespace);

			if (sectionMap != null) {

				for (String sectionName : sectionMap.keySet()) {

					if (sectionName.startsWith("SYS ")) {
						continue;
					}

					printWriter.println(String.format("%nsection %s {",
							sectionName));

					LinkedHashMap<String, Param<?>> paramMap = sectionMap
							.get(sectionName);

					for (Param<?> param : paramMap.values()) {
						printWriter.println(String.format("\t%s;",
								param.toString()));
					}

					printWriter.println("}");
				}
			}

		} finally {

			printWriter.close();
		}
	}

	public void mergeNamespaces(Script script) {
		script.mergeNamespaces();
	}
}

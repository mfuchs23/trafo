package org.dbdoclet.trafo.script.parser;

import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.trafo.script.parser.TrafoScriptParser.AttributeContext;
import org.dbdoclet.trafo.script.parser.TrafoScriptParser.ExprContext;
import org.dbdoclet.trafo.script.parser.TrafoScriptParser.NodeContext;
import org.dbdoclet.trafo.script.parser.TrafoScriptParser.ParamContext;
import org.dbdoclet.trafo.script.parser.TrafoScriptParser.SectionContext;
import org.dbdoclet.trafo.script.parser.TrafoScriptParser.TransformationContext;

public class TrafoScriptListenerImpl extends TrafoScriptBaseListener {

	private Script script;
	private String namespace;
	private String currentParamId;

	public TrafoScriptListenerImpl(Script script, String namespace) {
		this.script = script;
		this.namespace = namespace;
	}

	@Override
	public void enterTransformation(TransformationContext ctx) {
		script.setSystemParameter(namespace, Script.SYSPARAM_TRANSFORMATION_NAME, ctx.getText());
	}

	@Override
	public void enterSection(SectionContext ctx) {

		if (ctx.ID() != null) {
			script.selectSection(namespace, ctx.ID().getText());
		}
		
		if (ctx.REGEXP() != null) {
			script.selectSection(namespace, ctx.REGEXP().getText());
		}
	}

	@Override
	public void enterParam(ParamContext ctx) {
		
		currentParamId = ctx.ID().getText();
	}

	@Override
	public void enterNode(NodeContext ctx) {
		
	}

	@Override
	public void enterAttribute(AttributeContext ctx) {
	
	}

	@Override
	public void enterExpr(ExprContext ctx) {

		if (ctx.BOOL() != null) {
			script.addBoolParam(currentParamId, ctx.BOOL().getText());
		}

		if (ctx.NUMBER() != null) {
			script.addNumberParam(currentParamId, ctx.NUMBER().getText());
		}

		if (ctx.TEXT() != null) {
			script.addTextParam(currentParamId, ctx.TEXT().getText());
		}
		
		if (ctx.MULTILINE() != null) {
			script.addTextParam(currentParamId, ctx.MULTILINE().getText());
		}		
	}
}

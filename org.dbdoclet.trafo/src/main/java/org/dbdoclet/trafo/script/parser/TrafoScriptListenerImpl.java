package org.dbdoclet.trafo.script.parser;

import org.dbdoclet.trafo.param.BooleanParam;
import org.dbdoclet.trafo.param.NumberParam;
import org.dbdoclet.trafo.param.TextParam;
import org.dbdoclet.trafo.script.AttributeRule;
import org.dbdoclet.trafo.script.Namespace;
import org.dbdoclet.trafo.script.NodeRule;
import org.dbdoclet.trafo.script.Script;
import org.dbdoclet.trafo.script.Section;
import org.dbdoclet.trafo.script.parser.TrafoScriptParser.AttributeContext;
import org.dbdoclet.trafo.script.parser.TrafoScriptParser.ExprContext;
import org.dbdoclet.trafo.script.parser.TrafoScriptParser.NodeContext;
import org.dbdoclet.trafo.script.parser.TrafoScriptParser.ParamContext;
import org.dbdoclet.trafo.script.parser.TrafoScriptParser.SectionContext;
import org.dbdoclet.trafo.script.parser.TrafoScriptParser.TransformationContext;

public class TrafoScriptListenerImpl extends TrafoScriptBaseListener {

	private Script script;
	private Namespace namespace;
	private String currentParamId;
	private Section currentSection;

	public TrafoScriptListenerImpl(Script script, String namespaceName) {
		
		this.script = script;
		
		if (namespaceName == null || namespaceName.trim().length() == 0) {
			namespaceName = Script.DEFAULT_NAMESPACE;
		}
		
		this.namespace = new Namespace(namespaceName);
		script.addNamespace(namespace);
	}

	@Override
	public void enterTransformation(TransformationContext ctx) {
		script.addNamespace(namespace);
		script.setSystemParameter(namespace.getName(),
				Script.SYSPARAM_TRANSFORMATION_NAME, ctx.ID().getText());
	}

	@Override
	public void enterSection(SectionContext ctx) {

		Section section = new Section();

		if (ctx.ID() != null) {
			section.setName(ctx.ID().getText());
		}

		if (ctx.REGEXP() != null) {
			section.setName(ctx.REGEXP().getText());
		}

		namespace.addSection(section);
		currentSection = section;
	}

	@Override
	public void enterParam(ParamContext ctx) {

		currentParamId = ctx.ID().getText();
	}

	@Override
	public void enterNode(NodeContext ctx) {

		NodeRule nodeRule = new NodeRule();
		
		if (ctx.TEXT() != null) {
			nodeRule.setName(ctx.TEXT().getText());
		}
		
		namespace.addNodeRule(nodeRule);
		currentSection = nodeRule;
	}

	@Override
	public void enterAttribute(AttributeContext ctx) {

		AttributeRule attributeRule = new AttributeRule();
		
		if (ctx.TEXT() != null) {
			attributeRule.setName(ctx.TEXT().getText());
		}
		
		namespace.addAttributeRule(attributeRule);
		currentSection = attributeRule;
	}

	@Override
	public void enterExpr(ExprContext ctx) {

		if (ctx.BOOL() != null) {
			currentSection.addParam(new BooleanParam(currentParamId, Boolean
					.valueOf(ctx.BOOL().getText())));
		}

		if (ctx.NUMBER() != null) {
			currentSection.addParam(new NumberParam(currentParamId, Integer.valueOf(ctx.NUMBER().getText())));
		}

		if (ctx.TEXT() != null) {
			currentSection.addParam(new TextParam(currentParamId, ctx.TEXT().getText()));
		}

		if (ctx.MULTILINE() != null) {
			currentSection.addParam(new TextParam(currentParamId, ctx.MULTILINE().getText()));
		}
	}
}

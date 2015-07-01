// Generated from TrafoScript.g4 by ANTLR 4.5

package org.dbdoclet.trafo.script.parser;

import java.util.LinkedList;


import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TrafoScriptParser}.
 */
public interface TrafoScriptListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TrafoScriptParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(TrafoScriptParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link TrafoScriptParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(TrafoScriptParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link TrafoScriptParser#transformation}.
	 * @param ctx the parse tree
	 */
	void enterTransformation(TrafoScriptParser.TransformationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TrafoScriptParser#transformation}.
	 * @param ctx the parse tree
	 */
	void exitTransformation(TrafoScriptParser.TransformationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TrafoScriptParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(TrafoScriptParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TrafoScriptParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(TrafoScriptParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TrafoScriptParser#node}.
	 * @param ctx the parse tree
	 */
	void enterNode(TrafoScriptParser.NodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TrafoScriptParser#node}.
	 * @param ctx the parse tree
	 */
	void exitNode(TrafoScriptParser.NodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TrafoScriptParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(TrafoScriptParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TrafoScriptParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(TrafoScriptParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TrafoScriptParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(TrafoScriptParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link TrafoScriptParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(TrafoScriptParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link TrafoScriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(TrafoScriptParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link TrafoScriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(TrafoScriptParser.ExprContext ctx);
}
// Generated from TrafoScript.g4 by ANTLR 4.13.1
package org.dbdoclet.trafo.script.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TrafoScriptParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TrafoScriptVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TrafoScriptParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(TrafoScriptParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by {@link TrafoScriptParser#transformation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransformation(TrafoScriptParser.TransformationContext ctx);
	/**
	 * Visit a parse tree produced by {@link TrafoScriptParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(TrafoScriptParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TrafoScriptParser#node}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNode(TrafoScriptParser.NodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TrafoScriptParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute(TrafoScriptParser.AttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TrafoScriptParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(TrafoScriptParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link TrafoScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(TrafoScriptParser.ExprContext ctx);
}
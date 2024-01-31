// Generated from TrafoScript.g4 by ANTLR 4.13.1
package org.dbdoclet.trafo.script.parser;


import java.util.LinkedList;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class TrafoScriptParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BO=1, BC=2, SBO=3, SBC=4, BOOL=5, EQ=6, EOS=7, COMMA=8, NUMBER=9, NODE=10, 
		ATTRIBUTE=11, SECTION=12, TRAN=13, ID=14, REGEXP=15, TEXT=16, MULTILINE=17, 
		WS=18, COMMENT=19, LINE_COMMENT=20;
	public static final int
		RULE_parse = 0, RULE_transformation = 1, RULE_section = 2, RULE_node = 3, 
		RULE_attribute = 4, RULE_param = 5, RULE_expr = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "transformation", "section", "node", "attribute", "param", "expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "'['", "']'", null, "'='", "';'", "','", null, "'node'", 
			"'attribute'", "'section'", "'transformation'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BO", "BC", "SBO", "SBC", "BOOL", "EQ", "EOS", "COMMA", "NUMBER", 
			"NODE", "ATTRIBUTE", "SECTION", "TRAN", "ID", "REGEXP", "TEXT", "MULTILINE", 
			"WS", "COMMENT", "LINE_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "TrafoScript.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TrafoScriptParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParseContext extends ParserRuleContext {
		public TransformationContext transformation() {
			return getRuleContext(TransformationContext.class,0);
		}
		public TerminalNode EOF() { return getToken(TrafoScriptParser.EOF, 0); }
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public List<NodeContext> node() {
			return getRuleContexts(NodeContext.class);
		}
		public NodeContext node(int i) {
			return getRuleContext(NodeContext.class,i);
		}
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TrafoScriptListener ) ((TrafoScriptListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TrafoScriptListener ) ((TrafoScriptListener)listener).exitParse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TrafoScriptVisitor ) return ((TrafoScriptVisitor<? extends T>)visitor).visitParse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			transformation();
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7168L) != 0)) {
				{
				setState(18);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case SECTION:
					{
					setState(15);
					section();
					}
					break;
				case NODE:
					{
					setState(16);
					node();
					}
					break;
				case ATTRIBUTE:
					{
					setState(17);
					attribute();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(23);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TransformationContext extends ParserRuleContext {
		public TerminalNode TRAN() { return getToken(TrafoScriptParser.TRAN, 0); }
		public TerminalNode ID() { return getToken(TrafoScriptParser.ID, 0); }
		public TerminalNode EOS() { return getToken(TrafoScriptParser.EOS, 0); }
		public TransformationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transformation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TrafoScriptListener ) ((TrafoScriptListener)listener).enterTransformation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TrafoScriptListener ) ((TrafoScriptListener)listener).exitTransformation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TrafoScriptVisitor ) return ((TrafoScriptVisitor<? extends T>)visitor).visitTransformation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransformationContext transformation() throws RecognitionException {
		TransformationContext _localctx = new TransformationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_transformation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			match(TRAN);
			setState(26);
			match(ID);
			setState(27);
			match(EOS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SectionContext extends ParserRuleContext {
		public TerminalNode SECTION() { return getToken(TrafoScriptParser.SECTION, 0); }
		public TerminalNode BO() { return getToken(TrafoScriptParser.BO, 0); }
		public TerminalNode BC() { return getToken(TrafoScriptParser.BC, 0); }
		public TerminalNode ID() { return getToken(TrafoScriptParser.ID, 0); }
		public TerminalNode REGEXP() { return getToken(TrafoScriptParser.REGEXP, 0); }
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TrafoScriptListener ) ((TrafoScriptListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TrafoScriptListener ) ((TrafoScriptListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TrafoScriptVisitor ) return ((TrafoScriptVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			match(SECTION);
			setState(30);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==REGEXP) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(31);
			match(BO);
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(32);
				param();
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(38);
			match(BC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NodeContext extends ParserRuleContext {
		public TerminalNode NODE() { return getToken(TrafoScriptParser.NODE, 0); }
		public TerminalNode TEXT() { return getToken(TrafoScriptParser.TEXT, 0); }
		public TerminalNode BO() { return getToken(TrafoScriptParser.BO, 0); }
		public TerminalNode BC() { return getToken(TrafoScriptParser.BC, 0); }
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public NodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_node; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TrafoScriptListener ) ((TrafoScriptListener)listener).enterNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TrafoScriptListener ) ((TrafoScriptListener)listener).exitNode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TrafoScriptVisitor ) return ((TrafoScriptVisitor<? extends T>)visitor).visitNode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeContext node() throws RecognitionException {
		NodeContext _localctx = new NodeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_node);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(NODE);
			setState(41);
			match(TEXT);
			setState(42);
			match(BO);
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(43);
				param();
				}
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(49);
			match(BC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AttributeContext extends ParserRuleContext {
		public TerminalNode ATTRIBUTE() { return getToken(TrafoScriptParser.ATTRIBUTE, 0); }
		public TerminalNode TEXT() { return getToken(TrafoScriptParser.TEXT, 0); }
		public TerminalNode BO() { return getToken(TrafoScriptParser.BO, 0); }
		public TerminalNode BC() { return getToken(TrafoScriptParser.BC, 0); }
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TrafoScriptListener ) ((TrafoScriptListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TrafoScriptListener ) ((TrafoScriptListener)listener).exitAttribute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TrafoScriptVisitor ) return ((TrafoScriptVisitor<? extends T>)visitor).visitAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_attribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(ATTRIBUTE);
			setState(52);
			match(TEXT);
			setState(53);
			match(BO);
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(54);
				param();
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(60);
			match(BC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TrafoScriptParser.ID, 0); }
		public TerminalNode EQ() { return getToken(TrafoScriptParser.EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EOS() { return getToken(TrafoScriptParser.EOS, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TrafoScriptListener ) ((TrafoScriptListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TrafoScriptListener ) ((TrafoScriptListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TrafoScriptVisitor ) return ((TrafoScriptVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(ID);
			setState(63);
			match(EQ);
			setState(64);
			expr();
			setState(65);
			match(EOS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public TerminalNode BOOL() { return getToken(TrafoScriptParser.BOOL, 0); }
		public TerminalNode NUMBER() { return getToken(TrafoScriptParser.NUMBER, 0); }
		public TerminalNode TEXT() { return getToken(TrafoScriptParser.TEXT, 0); }
		public TerminalNode MULTILINE() { return getToken(TrafoScriptParser.MULTILINE, 0); }
		public TerminalNode SBO() { return getToken(TrafoScriptParser.SBO, 0); }
		public TerminalNode SBC() { return getToken(TrafoScriptParser.SBC, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TrafoScriptParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TrafoScriptParser.COMMA, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TrafoScriptListener ) ((TrafoScriptListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TrafoScriptListener ) ((TrafoScriptListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TrafoScriptVisitor ) return ((TrafoScriptVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_expr);
		int _la;
		try {
			setState(81);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 197152L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(68);
				match(SBO);
				setState(69);
				match(SBC);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				match(SBO);
				setState(71);
				expr();
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(72);
					match(COMMA);
					setState(73);
					expr();
					}
					}
					setState(78);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(79);
				match(SBC);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0014T\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0005\u0000\u0013\b\u0000\n\u0000\f\u0000\u0016\t\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002\"\b\u0002"+
		"\n\u0002\f\u0002%\t\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0005\u0003-\b\u0003\n\u0003\f\u00030\t\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0005\u00048\b\u0004\n\u0004\f\u0004;\t\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0005\u0006K\b\u0006\n\u0006\f\u0006N\t\u0006\u0001\u0006\u0001\u0006"+
		"\u0003\u0006R\b\u0006\u0001\u0006\u0000\u0000\u0007\u0000\u0002\u0004"+
		"\u0006\b\n\f\u0000\u0002\u0001\u0000\u000e\u000f\u0003\u0000\u0005\u0005"+
		"\t\t\u0010\u0011U\u0000\u000e\u0001\u0000\u0000\u0000\u0002\u0019\u0001"+
		"\u0000\u0000\u0000\u0004\u001d\u0001\u0000\u0000\u0000\u0006(\u0001\u0000"+
		"\u0000\u0000\b3\u0001\u0000\u0000\u0000\n>\u0001\u0000\u0000\u0000\fQ"+
		"\u0001\u0000\u0000\u0000\u000e\u0014\u0003\u0002\u0001\u0000\u000f\u0013"+
		"\u0003\u0004\u0002\u0000\u0010\u0013\u0003\u0006\u0003\u0000\u0011\u0013"+
		"\u0003\b\u0004\u0000\u0012\u000f\u0001\u0000\u0000\u0000\u0012\u0010\u0001"+
		"\u0000\u0000\u0000\u0012\u0011\u0001\u0000\u0000\u0000\u0013\u0016\u0001"+
		"\u0000\u0000\u0000\u0014\u0012\u0001\u0000\u0000\u0000\u0014\u0015\u0001"+
		"\u0000\u0000\u0000\u0015\u0017\u0001\u0000\u0000\u0000\u0016\u0014\u0001"+
		"\u0000\u0000\u0000\u0017\u0018\u0005\u0000\u0000\u0001\u0018\u0001\u0001"+
		"\u0000\u0000\u0000\u0019\u001a\u0005\r\u0000\u0000\u001a\u001b\u0005\u000e"+
		"\u0000\u0000\u001b\u001c\u0005\u0007\u0000\u0000\u001c\u0003\u0001\u0000"+
		"\u0000\u0000\u001d\u001e\u0005\f\u0000\u0000\u001e\u001f\u0007\u0000\u0000"+
		"\u0000\u001f#\u0005\u0001\u0000\u0000 \"\u0003\n\u0005\u0000! \u0001\u0000"+
		"\u0000\u0000\"%\u0001\u0000\u0000\u0000#!\u0001\u0000\u0000\u0000#$\u0001"+
		"\u0000\u0000\u0000$&\u0001\u0000\u0000\u0000%#\u0001\u0000\u0000\u0000"+
		"&\'\u0005\u0002\u0000\u0000\'\u0005\u0001\u0000\u0000\u0000()\u0005\n"+
		"\u0000\u0000)*\u0005\u0010\u0000\u0000*.\u0005\u0001\u0000\u0000+-\u0003"+
		"\n\u0005\u0000,+\u0001\u0000\u0000\u0000-0\u0001\u0000\u0000\u0000.,\u0001"+
		"\u0000\u0000\u0000./\u0001\u0000\u0000\u0000/1\u0001\u0000\u0000\u0000"+
		"0.\u0001\u0000\u0000\u000012\u0005\u0002\u0000\u00002\u0007\u0001\u0000"+
		"\u0000\u000034\u0005\u000b\u0000\u000045\u0005\u0010\u0000\u000059\u0005"+
		"\u0001\u0000\u000068\u0003\n\u0005\u000076\u0001\u0000\u0000\u00008;\u0001"+
		"\u0000\u0000\u000097\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000"+
		":<\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000<=\u0005\u0002\u0000"+
		"\u0000=\t\u0001\u0000\u0000\u0000>?\u0005\u000e\u0000\u0000?@\u0005\u0006"+
		"\u0000\u0000@A\u0003\f\u0006\u0000AB\u0005\u0007\u0000\u0000B\u000b\u0001"+
		"\u0000\u0000\u0000CR\u0007\u0001\u0000\u0000DE\u0005\u0003\u0000\u0000"+
		"ER\u0005\u0004\u0000\u0000FG\u0005\u0003\u0000\u0000GL\u0003\f\u0006\u0000"+
		"HI\u0005\b\u0000\u0000IK\u0003\f\u0006\u0000JH\u0001\u0000\u0000\u0000"+
		"KN\u0001\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000LM\u0001\u0000\u0000"+
		"\u0000MO\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000OP\u0005\u0004"+
		"\u0000\u0000PR\u0001\u0000\u0000\u0000QC\u0001\u0000\u0000\u0000QD\u0001"+
		"\u0000\u0000\u0000QF\u0001\u0000\u0000\u0000R\r\u0001\u0000\u0000\u0000"+
		"\u0007\u0012\u0014#.9LQ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
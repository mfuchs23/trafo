// Generated from TrafoScript.g4 by ANTLR 4.5

package org.dbdoclet.trafo.script.parser;

import java.util.List;

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TrafoScriptParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

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
	public static final String[] ruleNames = {
		"parse", "transformation", "section", "node", "attribute", "param", "expr"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "'}'", "'['", "']'", null, "'='", "';'", "','", null, "'node'", 
		"'attribute'", "'section'", "'transformation'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "BO", "BC", "SBO", "SBC", "BOOL", "EQ", "EOS", "COMMA", "NUMBER", 
		"NODE", "ATTRIBUTE", "SECTION", "TRAN", "ID", "REGEXP", "TEXT", "MULTILINE", 
		"WS", "COMMENT", "LINE_COMMENT"
	};
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NODE) | (1L << ATTRIBUTE) | (1L << SECTION))) != 0)) {
				{
				setState(18);
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
			} else {
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
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_expr);
		int _la;
		try {
			setState(81);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << NUMBER) | (1L << TEXT) | (1L << MULTILINE))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\26V\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\7\2\25\n\2"+
		"\f\2\16\2\30\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4$\n\4\f\4"+
		"\16\4\'\13\4\3\4\3\4\3\5\3\5\3\5\3\5\7\5/\n\5\f\5\16\5\62\13\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\7\6:\n\6\f\6\16\6=\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\7\bM\n\b\f\b\16\bP\13\b\3\b\3\b\5\bT\n\b\3"+
		"\b\2\2\t\2\4\6\b\n\f\16\2\4\3\2\20\21\5\2\7\7\13\13\22\23W\2\20\3\2\2"+
		"\2\4\33\3\2\2\2\6\37\3\2\2\2\b*\3\2\2\2\n\65\3\2\2\2\f@\3\2\2\2\16S\3"+
		"\2\2\2\20\26\5\4\3\2\21\25\5\6\4\2\22\25\5\b\5\2\23\25\5\n\6\2\24\21\3"+
		"\2\2\2\24\22\3\2\2\2\24\23\3\2\2\2\25\30\3\2\2\2\26\24\3\2\2\2\26\27\3"+
		"\2\2\2\27\31\3\2\2\2\30\26\3\2\2\2\31\32\7\2\2\3\32\3\3\2\2\2\33\34\7"+
		"\17\2\2\34\35\7\20\2\2\35\36\7\t\2\2\36\5\3\2\2\2\37 \7\16\2\2 !\t\2\2"+
		"\2!%\7\3\2\2\"$\5\f\7\2#\"\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&(\3"+
		"\2\2\2\'%\3\2\2\2()\7\4\2\2)\7\3\2\2\2*+\7\f\2\2+,\7\22\2\2,\60\7\3\2"+
		"\2-/\5\f\7\2.-\3\2\2\2/\62\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61\63\3\2"+
		"\2\2\62\60\3\2\2\2\63\64\7\4\2\2\64\t\3\2\2\2\65\66\7\r\2\2\66\67\7\22"+
		"\2\2\67;\7\3\2\28:\5\f\7\298\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<>\3"+
		"\2\2\2=;\3\2\2\2>?\7\4\2\2?\13\3\2\2\2@A\7\20\2\2AB\7\b\2\2BC\5\16\b\2"+
		"CD\7\t\2\2D\r\3\2\2\2ET\t\3\2\2FG\7\5\2\2GT\7\6\2\2HI\7\5\2\2IN\5\16\b"+
		"\2JK\7\n\2\2KM\5\16\b\2LJ\3\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2OQ\3\2"+
		"\2\2PN\3\2\2\2QR\7\6\2\2RT\3\2\2\2SE\3\2\2\2SF\3\2\2\2SH\3\2\2\2T\17\3"+
		"\2\2\2\t\24\26%\60;NS";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
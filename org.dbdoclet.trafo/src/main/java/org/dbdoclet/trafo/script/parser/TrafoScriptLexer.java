// Generated from TrafoScript.g4 by ANTLR 4.13.1
package org.dbdoclet.trafo.script.parser;


import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class TrafoScriptLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BO=1, BC=2, SBO=3, SBC=4, BOOL=5, EQ=6, EOS=7, COMMA=8, NUMBER=9, NODE=10, 
		ATTRIBUTE=11, SECTION=12, TRAN=13, ID=14, REGEXP=15, TEXT=16, MULTILINE=17, 
		WS=18, COMMENT=19, LINE_COMMENT=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"BO", "BC", "SBO", "SBC", "BOOL", "EQ", "EOS", "COMMA", "NUMBER", "NODE", 
			"ATTRIBUTE", "SECTION", "TRAN", "ID", "REGEXP", "TEXT", "MULTILINE", 
			"WS", "COMMENT", "LINE_COMMENT"
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


	public TrafoScriptLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TrafoScript.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 15:
			TEXT_action((RuleContext)_localctx, actionIndex);
			break;
		case 16:
			MULTILINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 17:
			WS_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void TEXT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			setText(getText().substring(1, getText().length()-1));
			break;
		}
	}
	private void MULTILINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			setText(getText().substring(3, getText().length()-3));
			break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			 skip(); 
			break;
		}
	}

	public static final String _serializedATN =
		"\u0004\u0000\u0014\u00be\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004;\b\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0004"+
		"\bD\b\b\u000b\b\f\bE\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0004\rp\b\r\u000b\r\f\rq\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0005\u000ey\b\u000e\n\u000e\f\u000e|\t\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0005\u000f\u0082\b\u000f"+
		"\n\u000f\f\u000f\u0085\t\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u008f"+
		"\b\u0010\n\u0010\f\u0010\u0092\t\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0004\u0011\u009b\b\u0011"+
		"\u000b\u0011\f\u0011\u009c\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0005\u0012\u00a5\b\u0012\n\u0012\f\u0012\u00a8"+
		"\t\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u00b3\b\u0013\n"+
		"\u0013\f\u0013\u00b6\t\u0013\u0001\u0013\u0003\u0013\u00b9\b\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0002\u0090\u00a6\u0000\u0014"+
		"\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r"+
		"\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e"+
		"\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014\u0001\u0000\u0007"+
		"\u0003\u0000AZ__az\u0005\u0000-.09AZ__az\u0001\u0000**\u0001\u0000//\u0003"+
		"\u0000\n\n\r\r\"\"\u0003\u0000\t\n\r\r  \u0002\u0000\n\n\r\r\u00c8\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0001)\u0001\u0000\u0000\u0000"+
		"\u0003+\u0001\u0000\u0000\u0000\u0005-\u0001\u0000\u0000\u0000\u0007/"+
		"\u0001\u0000\u0000\u0000\t:\u0001\u0000\u0000\u0000\u000b<\u0001\u0000"+
		"\u0000\u0000\r>\u0001\u0000\u0000\u0000\u000f@\u0001\u0000\u0000\u0000"+
		"\u0011C\u0001\u0000\u0000\u0000\u0013G\u0001\u0000\u0000\u0000\u0015L"+
		"\u0001\u0000\u0000\u0000\u0017V\u0001\u0000\u0000\u0000\u0019^\u0001\u0000"+
		"\u0000\u0000\u001bm\u0001\u0000\u0000\u0000\u001ds\u0001\u0000\u0000\u0000"+
		"\u001f\u007f\u0001\u0000\u0000\u0000!\u0089\u0001\u0000\u0000\u0000#\u009a"+
		"\u0001\u0000\u0000\u0000%\u00a0\u0001\u0000\u0000\u0000\'\u00ae\u0001"+
		"\u0000\u0000\u0000)*\u0005{\u0000\u0000*\u0002\u0001\u0000\u0000\u0000"+
		"+,\u0005}\u0000\u0000,\u0004\u0001\u0000\u0000\u0000-.\u0005[\u0000\u0000"+
		".\u0006\u0001\u0000\u0000\u0000/0\u0005]\u0000\u00000\b\u0001\u0000\u0000"+
		"\u000012\u0005t\u0000\u000023\u0005r\u0000\u000034\u0005u\u0000\u0000"+
		"4;\u0005e\u0000\u000056\u0005f\u0000\u000067\u0005a\u0000\u000078\u0005"+
		"l\u0000\u000089\u0005s\u0000\u00009;\u0005e\u0000\u0000:1\u0001\u0000"+
		"\u0000\u0000:5\u0001\u0000\u0000\u0000;\n\u0001\u0000\u0000\u0000<=\u0005"+
		"=\u0000\u0000=\f\u0001\u0000\u0000\u0000>?\u0005;\u0000\u0000?\u000e\u0001"+
		"\u0000\u0000\u0000@A\u0005,\u0000\u0000A\u0010\u0001\u0000\u0000\u0000"+
		"BD\u000209\u0000CB\u0001\u0000\u0000\u0000DE\u0001\u0000\u0000\u0000E"+
		"C\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000F\u0012\u0001\u0000"+
		"\u0000\u0000GH\u0005n\u0000\u0000HI\u0005o\u0000\u0000IJ\u0005d\u0000"+
		"\u0000JK\u0005e\u0000\u0000K\u0014\u0001\u0000\u0000\u0000LM\u0005a\u0000"+
		"\u0000MN\u0005t\u0000\u0000NO\u0005t\u0000\u0000OP\u0005r\u0000\u0000"+
		"PQ\u0005i\u0000\u0000QR\u0005b\u0000\u0000RS\u0005u\u0000\u0000ST\u0005"+
		"t\u0000\u0000TU\u0005e\u0000\u0000U\u0016\u0001\u0000\u0000\u0000VW\u0005"+
		"s\u0000\u0000WX\u0005e\u0000\u0000XY\u0005c\u0000\u0000YZ\u0005t\u0000"+
		"\u0000Z[\u0005i\u0000\u0000[\\\u0005o\u0000\u0000\\]\u0005n\u0000\u0000"+
		"]\u0018\u0001\u0000\u0000\u0000^_\u0005t\u0000\u0000_`\u0005r\u0000\u0000"+
		"`a\u0005a\u0000\u0000ab\u0005n\u0000\u0000bc\u0005s\u0000\u0000cd\u0005"+
		"f\u0000\u0000de\u0005o\u0000\u0000ef\u0005r\u0000\u0000fg\u0005m\u0000"+
		"\u0000gh\u0005a\u0000\u0000hi\u0005t\u0000\u0000ij\u0005i\u0000\u0000"+
		"jk\u0005o\u0000\u0000kl\u0005n\u0000\u0000l\u001a\u0001\u0000\u0000\u0000"+
		"mo\u0007\u0000\u0000\u0000np\u0007\u0001\u0000\u0000on\u0001\u0000\u0000"+
		"\u0000pq\u0001\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000qr\u0001\u0000"+
		"\u0000\u0000r\u001c\u0001\u0000\u0000\u0000st\u0005/\u0000\u0000tz\b\u0002"+
		"\u0000\u0000uv\u0005\\\u0000\u0000vy\u0005/\u0000\u0000wy\b\u0003\u0000"+
		"\u0000xu\u0001\u0000\u0000\u0000xw\u0001\u0000\u0000\u0000y|\u0001\u0000"+
		"\u0000\u0000zx\u0001\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000{}\u0001"+
		"\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000}~\u0005/\u0000\u0000~\u001e"+
		"\u0001\u0000\u0000\u0000\u007f\u0083\u0005\"\u0000\u0000\u0080\u0082\b"+
		"\u0004\u0000\u0000\u0081\u0080\u0001\u0000\u0000\u0000\u0082\u0085\u0001"+
		"\u0000\u0000\u0000\u0083\u0081\u0001\u0000\u0000\u0000\u0083\u0084\u0001"+
		"\u0000\u0000\u0000\u0084\u0086\u0001\u0000\u0000\u0000\u0085\u0083\u0001"+
		"\u0000\u0000\u0000\u0086\u0087\u0005\"\u0000\u0000\u0087\u0088\u0006\u000f"+
		"\u0000\u0000\u0088 \u0001\u0000\u0000\u0000\u0089\u008a\u0005\"\u0000"+
		"\u0000\u008a\u008b\u0005\"\u0000\u0000\u008b\u008c\u0005\"\u0000\u0000"+
		"\u008c\u0090\u0001\u0000\u0000\u0000\u008d\u008f\t\u0000\u0000\u0000\u008e"+
		"\u008d\u0001\u0000\u0000\u0000\u008f\u0092\u0001\u0000\u0000\u0000\u0090"+
		"\u0091\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0091"+
		"\u0093\u0001\u0000\u0000\u0000\u0092\u0090\u0001\u0000\u0000\u0000\u0093"+
		"\u0094\u0005\"\u0000\u0000\u0094\u0095\u0005\"\u0000\u0000\u0095\u0096"+
		"\u0005\"\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000\u0097\u0098\u0006"+
		"\u0010\u0001\u0000\u0098\"\u0001\u0000\u0000\u0000\u0099\u009b\u0007\u0005"+
		"\u0000\u0000\u009a\u0099\u0001\u0000\u0000\u0000\u009b\u009c\u0001\u0000"+
		"\u0000\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000"+
		"\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u009f\u0006\u0011"+
		"\u0002\u0000\u009f$\u0001\u0000\u0000\u0000\u00a0\u00a1\u0005/\u0000\u0000"+
		"\u00a1\u00a2\u0005*\u0000\u0000\u00a2\u00a6\u0001\u0000\u0000\u0000\u00a3"+
		"\u00a5\t\u0000\u0000\u0000\u00a4\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a8"+
		"\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a6\u00a4"+
		"\u0001\u0000\u0000\u0000\u00a7\u00a9\u0001\u0000\u0000\u0000\u00a8\u00a6"+
		"\u0001\u0000\u0000\u0000\u00a9\u00aa\u0005*\u0000\u0000\u00aa\u00ab\u0005"+
		"/\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u00ad\u0006\u0012"+
		"\u0003\u0000\u00ad&\u0001\u0000\u0000\u0000\u00ae\u00af\u0005/\u0000\u0000"+
		"\u00af\u00b0\u0005/\u0000\u0000\u00b0\u00b4\u0001\u0000\u0000\u0000\u00b1"+
		"\u00b3\b\u0006\u0000\u0000\u00b2\u00b1\u0001\u0000\u0000\u0000\u00b3\u00b6"+
		"\u0001\u0000\u0000\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b5"+
		"\u0001\u0000\u0000\u0000\u00b5\u00b8\u0001\u0000\u0000\u0000\u00b6\u00b4"+
		"\u0001\u0000\u0000\u0000\u00b7\u00b9\u0005\r\u0000\u0000\u00b8\u00b7\u0001"+
		"\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001"+
		"\u0000\u0000\u0000\u00ba\u00bb\u0005\n\u0000\u0000\u00bb\u00bc\u0001\u0000"+
		"\u0000\u0000\u00bc\u00bd\u0006\u0013\u0003\u0000\u00bd(\u0001\u0000\u0000"+
		"\u0000\f\u0000:Eqxz\u0083\u0090\u009c\u00a6\u00b4\u00b8\u0004\u0001\u000f"+
		"\u0000\u0001\u0010\u0001\u0001\u0011\u0002\u0000\u0002\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
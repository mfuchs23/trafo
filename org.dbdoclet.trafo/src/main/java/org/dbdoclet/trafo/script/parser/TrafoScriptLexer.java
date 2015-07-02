// Generated from TrafoScript.g4 by ANTLR 4.5

package org.dbdoclet.trafo.script.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TrafoScriptLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BO=1, BC=2, SBO=3, SBC=4, BOOL=5, EQ=6, EOS=7, COMMA=8, NUMBER=9, NODE=10, 
		ATTRIBUTE=11, SECTION=12, TRAN=13, ID=14, REGEXP=15, TEXT=16, MULTILINE=17, 
		WS=18, COMMENT=19, LINE_COMMENT=20;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"BO", "BC", "SBO", "SBC", "BOOL", "EQ", "EOS", "COMMA", "NUMBER", "NODE", 
		"ATTRIBUTE", "SECTION", "TRAN", "ID", "REGEXP", "TEXT", "MULTILINE", "WS", 
		"COMMENT", "LINE_COMMENT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\26\u00c0\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6=\n\6\3\7\3\7\3\b\3\b\3\t\3\t\3"+
		"\n\6\nF\n\n\r\n\16\nG\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\6\17r"+
		"\n\17\r\17\16\17s\3\20\3\20\3\20\3\20\3\20\7\20{\n\20\f\20\16\20~\13\20"+
		"\3\20\3\20\3\21\3\21\7\21\u0084\n\21\f\21\16\21\u0087\13\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\7\22\u0091\n\22\f\22\16\22\u0094\13\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\23\6\23\u009d\n\23\r\23\16\23\u009e\3"+
		"\23\3\23\3\24\3\24\3\24\3\24\7\24\u00a7\n\24\f\24\16\24\u00aa\13\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\7\25\u00b5\n\25\f\25\16\25"+
		"\u00b8\13\25\3\25\5\25\u00bb\n\25\3\25\3\25\3\25\3\25\4\u0092\u00a8\2"+
		"\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26\3\2\t\5\2C\\aac|\7\2/\60\62;C\\aac|\3\2"+
		",,\3\2\61\61\5\2\f\f\17\17$$\5\2\13\f\17\17\"\"\4\2\f\f\17\17\u00ca\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\3+\3\2\2\2\5-\3\2\2\2\7/\3\2\2\2"+
		"\t\61\3\2\2\2\13<\3\2\2\2\r>\3\2\2\2\17@\3\2\2\2\21B\3\2\2\2\23E\3\2\2"+
		"\2\25I\3\2\2\2\27N\3\2\2\2\31X\3\2\2\2\33`\3\2\2\2\35o\3\2\2\2\37u\3\2"+
		"\2\2!\u0081\3\2\2\2#\u008b\3\2\2\2%\u009c\3\2\2\2\'\u00a2\3\2\2\2)\u00b0"+
		"\3\2\2\2+,\7}\2\2,\4\3\2\2\2-.\7\177\2\2.\6\3\2\2\2/\60\7]\2\2\60\b\3"+
		"\2\2\2\61\62\7_\2\2\62\n\3\2\2\2\63\64\7v\2\2\64\65\7t\2\2\65\66\7w\2"+
		"\2\66=\7g\2\2\678\7h\2\289\7c\2\29:\7n\2\2:;\7u\2\2;=\7g\2\2<\63\3\2\2"+
		"\2<\67\3\2\2\2=\f\3\2\2\2>?\7?\2\2?\16\3\2\2\2@A\7=\2\2A\20\3\2\2\2BC"+
		"\7.\2\2C\22\3\2\2\2DF\4\62;\2ED\3\2\2\2FG\3\2\2\2GE\3\2\2\2GH\3\2\2\2"+
		"H\24\3\2\2\2IJ\7p\2\2JK\7q\2\2KL\7f\2\2LM\7g\2\2M\26\3\2\2\2NO\7c\2\2"+
		"OP\7v\2\2PQ\7v\2\2QR\7t\2\2RS\7k\2\2ST\7d\2\2TU\7w\2\2UV\7v\2\2VW\7g\2"+
		"\2W\30\3\2\2\2XY\7u\2\2YZ\7g\2\2Z[\7e\2\2[\\\7v\2\2\\]\7k\2\2]^\7q\2\2"+
		"^_\7p\2\2_\32\3\2\2\2`a\7v\2\2ab\7t\2\2bc\7c\2\2cd\7p\2\2de\7u\2\2ef\7"+
		"h\2\2fg\7q\2\2gh\7t\2\2hi\7o\2\2ij\7c\2\2jk\7v\2\2kl\7k\2\2lm\7q\2\2m"+
		"n\7p\2\2n\34\3\2\2\2oq\t\2\2\2pr\t\3\2\2qp\3\2\2\2rs\3\2\2\2sq\3\2\2\2"+
		"st\3\2\2\2t\36\3\2\2\2uv\7\61\2\2v|\n\4\2\2wx\7^\2\2x{\7\61\2\2y{\n\5"+
		"\2\2zw\3\2\2\2zy\3\2\2\2{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\177\3\2\2\2~|"+
		"\3\2\2\2\177\u0080\7\61\2\2\u0080 \3\2\2\2\u0081\u0085\7$\2\2\u0082\u0084"+
		"\n\6\2\2\u0083\u0082\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0085"+
		"\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087\u0085\3\2\2\2\u0088\u0089\7$"+
		"\2\2\u0089\u008a\b\21\2\2\u008a\"\3\2\2\2\u008b\u008c\7$\2\2\u008c\u008d"+
		"\7$\2\2\u008d\u008e\7$\2\2\u008e\u0092\3\2\2\2\u008f\u0091\13\2\2\2\u0090"+
		"\u008f\3\2\2\2\u0091\u0094\3\2\2\2\u0092\u0093\3\2\2\2\u0092\u0090\3\2"+
		"\2\2\u0093\u0095\3\2\2\2\u0094\u0092\3\2\2\2\u0095\u0096\7$\2\2\u0096"+
		"\u0097\7$\2\2\u0097\u0098\7$\2\2\u0098\u0099\3\2\2\2\u0099\u009a\b\22"+
		"\3\2\u009a$\3\2\2\2\u009b\u009d\t\7\2\2\u009c\u009b\3\2\2\2\u009d\u009e"+
		"\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0"+
		"\u00a1\b\23\4\2\u00a1&\3\2\2\2\u00a2\u00a3\7\61\2\2\u00a3\u00a4\7,\2\2"+
		"\u00a4\u00a8\3\2\2\2\u00a5\u00a7\13\2\2\2\u00a6\u00a5\3\2\2\2\u00a7\u00aa"+
		"\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00ab\3\2\2\2\u00aa"+
		"\u00a8\3\2\2\2\u00ab\u00ac\7,\2\2\u00ac\u00ad\7\61\2\2\u00ad\u00ae\3\2"+
		"\2\2\u00ae\u00af\b\24\5\2\u00af(\3\2\2\2\u00b0\u00b1\7\61\2\2\u00b1\u00b2"+
		"\7\61\2\2\u00b2\u00b6\3\2\2\2\u00b3\u00b5\n\b\2\2\u00b4\u00b3\3\2\2\2"+
		"\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00ba"+
		"\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00bb\7\17\2\2\u00ba\u00b9\3\2\2\2"+
		"\u00ba\u00bb\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\7\f\2\2\u00bd\u00be"+
		"\3\2\2\2\u00be\u00bf\b\25\5\2\u00bf*\3\2\2\2\16\2<Gsz|\u0085\u0092\u009e"+
		"\u00a8\u00b6\u00ba\6\3\21\2\3\22\3\3\23\4\2\4\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
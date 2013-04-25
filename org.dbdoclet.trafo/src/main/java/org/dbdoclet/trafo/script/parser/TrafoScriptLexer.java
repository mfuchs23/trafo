// $ANTLR 3.4 /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g 2012-12-12 17:48:44

package org.dbdoclet.trafo.script.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class TrafoScriptLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int BC=4;
    public static final int BO=5;
    public static final int BOOL=6;
    public static final int COMMENT=7;
    public static final int EOS=8;
    public static final int EQ=9;
    public static final int ID=10;
    public static final int LINE_COMMENT=11;
    public static final int MULTILINE=12;
    public static final int NUMBER=13;
    public static final int SECTION=14;
    public static final int TEXT=15;
    public static final int TRAN=16;
    public static final int WS=17;

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public TrafoScriptLexer() {} 
    public TrafoScriptLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public TrafoScriptLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "/home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g"; }

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:6:7: ( ',' )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:6:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:7:7: ( '[' )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:7:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:8:7: ( ']' )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:8:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "BO"
    public final void mBO() throws RecognitionException {
        try {
            int _type = BO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:51:4: ( '{' )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:51:6: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BO"

    // $ANTLR start "BC"
    public final void mBC() throws RecognitionException {
        try {
            int _type = BC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:52:4: ( '}' )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:52:6: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BC"

    // $ANTLR start "BOOL"
    public final void mBOOL() throws RecognitionException {
        try {
            int _type = BOOL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:53:6: ( 'true' | 'false' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='t') ) {
                alt1=1;
            }
            else if ( (LA1_0=='f') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }
            switch (alt1) {
                case 1 :
                    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:53:8: 'true'
                    {
                    match("true"); 



                    }
                    break;
                case 2 :
                    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:53:15: 'false'
                    {
                    match("false"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BOOL"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:54:4: ( '=' )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:54:6: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "EOS"
    public final void mEOS() throws RecognitionException {
        try {
            int _type = EOS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:55:5: ( ';' )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:55:7: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EOS"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:56:8: ( ( '0' .. '9' )+ )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:56:10: ( '0' .. '9' )+
            {
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:56:10: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "SECTION"
    public final void mSECTION() throws RecognitionException {
        try {
            int _type = SECTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:57:9: ( 'section' )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:57:11: 'section'
            {
            match("section"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SECTION"

    // $ANTLR start "TRAN"
    public final void mTRAN() throws RecognitionException {
        try {
            int _type = TRAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:58:7: ( 'transformation' )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:58:9: 'transformation'
            {
            match("transformation"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TRAN"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:59:4: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' )+ )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:59:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' )+
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:59:29: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='-'||(LA3_0 >= '0' && LA3_0 <= '9')||(LA3_0 >= 'A' && LA3_0 <= 'Z')||LA3_0=='_'||(LA3_0 >= 'a' && LA3_0 <= 'z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:
            	    {
            	    if ( input.LA(1)=='-'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "TEXT"
    public final void mTEXT() throws RecognitionException {
        try {
            int _type = TEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:60:6: ( '\"' (~ ( '\"' | '\\r' | '\\n' ) )* '\"' )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:60:8: '\"' (~ ( '\"' | '\\r' | '\\n' ) )* '\"'
            {
            match('\"'); 

            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:60:12: (~ ( '\"' | '\\r' | '\\n' ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0 >= '\u0000' && LA4_0 <= '\t')||(LA4_0 >= '\u000B' && LA4_0 <= '\f')||(LA4_0 >= '\u000E' && LA4_0 <= '!')||(LA4_0 >= '#' && LA4_0 <= '\uFFFF')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            match('\"'); 

            setText(getText().substring(1, getText().length()-1));

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TEXT"

    // $ANTLR start "MULTILINE"
    public final void mMULTILINE() throws RecognitionException {
        try {
            int _type = MULTILINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:62:2: ( '\"\"\"' ( options {greedy=false; } : ( . )* ) '\"\"\"' )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:62:4: '\"\"\"' ( options {greedy=false; } : ( . )* ) '\"\"\"'
            {
            match("\"\"\""); 



            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:62:10: ( options {greedy=false; } : ( . )* )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:62:35: ( . )*
            {
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:62:35: ( . )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0=='\"') ) {
                    int LA5_1 = input.LA(2);

                    if ( (LA5_1=='\"') ) {
                        int LA5_3 = input.LA(3);

                        if ( (LA5_3=='\"') ) {
                            alt5=2;
                        }
                        else if ( ((LA5_3 >= '\u0000' && LA5_3 <= '!')||(LA5_3 >= '#' && LA5_3 <= '\uFFFF')) ) {
                            alt5=1;
                        }


                    }
                    else if ( ((LA5_1 >= '\u0000' && LA5_1 <= '!')||(LA5_1 >= '#' && LA5_1 <= '\uFFFF')) ) {
                        alt5=1;
                    }


                }
                else if ( ((LA5_0 >= '\u0000' && LA5_0 <= '!')||(LA5_0 >= '#' && LA5_0 <= '\uFFFF')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:62:35: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }


            match("\"\"\""); 



            setText(getText().substring(3, getText().length()-3));

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MULTILINE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:63:4: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:63:6: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:63:6: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0 >= '\t' && LA6_0 <= '\n')||LA6_0=='\r'||LA6_0==' ') ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:
            	    {
            	    if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


             skip(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:69:17: ( '/*' ( options {greedy=false; } : ( . )* ) '*/' )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:69:19: '/*' ( options {greedy=false; } : ( . )* ) '*/'
            {
            match("/*"); 



            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:69:24: ( options {greedy=false; } : ( . )* )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:69:54: ( . )*
            {
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:69:54: ( . )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='*') ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1=='/') ) {
                        alt7=2;
                    }
                    else if ( ((LA7_1 >= '\u0000' && LA7_1 <= '.')||(LA7_1 >= '0' && LA7_1 <= '\uFFFF')) ) {
                        alt7=1;
                    }


                }
                else if ( ((LA7_0 >= '\u0000' && LA7_0 <= ')')||(LA7_0 >= '+' && LA7_0 <= '\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:69:54: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }


            match("*/"); 



             _channel = 2; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:70:17: ( '//' ( . )* '\\n' )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:70:19: '//' ( . )* '\\n'
            {
            match("//"); 



            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:70:24: ( . )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='\n') ) {
                    alt8=2;
                }
                else if ( ((LA8_0 >= '\u0000' && LA8_0 <= '\t')||(LA8_0 >= '\u000B' && LA8_0 <= '\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:70:24: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            match('\n'); 

             _channel = 2; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LINE_COMMENT"

    public void mTokens() throws RecognitionException {
        // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:8: ( T__18 | T__19 | T__20 | BO | BC | BOOL | EQ | EOS | NUMBER | SECTION | TRAN | ID | TEXT | MULTILINE | WS | COMMENT | LINE_COMMENT )
        int alt9=17;
        switch ( input.LA(1) ) {
        case ',':
            {
            alt9=1;
            }
            break;
        case '[':
            {
            alt9=2;
            }
            break;
        case ']':
            {
            alt9=3;
            }
            break;
        case '{':
            {
            alt9=4;
            }
            break;
        case '}':
            {
            alt9=5;
            }
            break;
        case 't':
            {
            int LA9_6 = input.LA(2);

            if ( (LA9_6=='r') ) {
                switch ( input.LA(3) ) {
                case 'u':
                    {
                    int LA9_23 = input.LA(4);

                    if ( (LA9_23=='e') ) {
                        int LA9_28 = input.LA(5);

                        if ( (LA9_28=='-'||(LA9_28 >= '0' && LA9_28 <= '9')||(LA9_28 >= 'A' && LA9_28 <= 'Z')||LA9_28=='_'||(LA9_28 >= 'a' && LA9_28 <= 'z')) ) {
                            alt9=12;
                        }
                        else {
                            alt9=6;
                        }
                    }
                    else {
                        alt9=12;
                    }
                    }
                    break;
                case 'a':
                    {
                    int LA9_24 = input.LA(4);

                    if ( (LA9_24=='n') ) {
                        int LA9_29 = input.LA(5);

                        if ( (LA9_29=='s') ) {
                            int LA9_33 = input.LA(6);

                            if ( (LA9_33=='f') ) {
                                int LA9_36 = input.LA(7);

                                if ( (LA9_36=='o') ) {
                                    int LA9_38 = input.LA(8);

                                    if ( (LA9_38=='r') ) {
                                        int LA9_40 = input.LA(9);

                                        if ( (LA9_40=='m') ) {
                                            int LA9_42 = input.LA(10);

                                            if ( (LA9_42=='a') ) {
                                                int LA9_43 = input.LA(11);

                                                if ( (LA9_43=='t') ) {
                                                    int LA9_44 = input.LA(12);

                                                    if ( (LA9_44=='i') ) {
                                                        int LA9_45 = input.LA(13);

                                                        if ( (LA9_45=='o') ) {
                                                            int LA9_46 = input.LA(14);

                                                            if ( (LA9_46=='n') ) {
                                                                int LA9_47 = input.LA(15);

                                                                if ( (LA9_47=='-'||(LA9_47 >= '0' && LA9_47 <= '9')||(LA9_47 >= 'A' && LA9_47 <= 'Z')||LA9_47=='_'||(LA9_47 >= 'a' && LA9_47 <= 'z')) ) {
                                                                    alt9=12;
                                                                }
                                                                else {
                                                                    alt9=11;
                                                                }
                                                            }
                                                            else {
                                                                alt9=12;
                                                            }
                                                        }
                                                        else {
                                                            alt9=12;
                                                        }
                                                    }
                                                    else {
                                                        alt9=12;
                                                    }
                                                }
                                                else {
                                                    alt9=12;
                                                }
                                            }
                                            else {
                                                alt9=12;
                                            }
                                        }
                                        else {
                                            alt9=12;
                                        }
                                    }
                                    else {
                                        alt9=12;
                                    }
                                }
                                else {
                                    alt9=12;
                                }
                            }
                            else {
                                alt9=12;
                            }
                        }
                        else {
                            alt9=12;
                        }
                    }
                    else {
                        alt9=12;
                    }
                    }
                    break;
                default:
                    alt9=12;
                }

            }
            else if ( (LA9_6=='-'||(LA9_6 >= '0' && LA9_6 <= '9')||(LA9_6 >= 'A' && LA9_6 <= 'Z')||LA9_6=='_'||(LA9_6 >= 'a' && LA9_6 <= 'q')||(LA9_6 >= 's' && LA9_6 <= 'z')) ) {
                alt9=12;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 6, input);

                throw nvae;

            }
            }
            break;
        case 'f':
            {
            int LA9_7 = input.LA(2);

            if ( (LA9_7=='a') ) {
                int LA9_17 = input.LA(3);

                if ( (LA9_17=='l') ) {
                    int LA9_25 = input.LA(4);

                    if ( (LA9_25=='s') ) {
                        int LA9_30 = input.LA(5);

                        if ( (LA9_30=='e') ) {
                            int LA9_34 = input.LA(6);

                            if ( (LA9_34=='-'||(LA9_34 >= '0' && LA9_34 <= '9')||(LA9_34 >= 'A' && LA9_34 <= 'Z')||LA9_34=='_'||(LA9_34 >= 'a' && LA9_34 <= 'z')) ) {
                                alt9=12;
                            }
                            else {
                                alt9=6;
                            }
                        }
                        else {
                            alt9=12;
                        }
                    }
                    else {
                        alt9=12;
                    }
                }
                else {
                    alt9=12;
                }
            }
            else if ( (LA9_7=='-'||(LA9_7 >= '0' && LA9_7 <= '9')||(LA9_7 >= 'A' && LA9_7 <= 'Z')||LA9_7=='_'||(LA9_7 >= 'b' && LA9_7 <= 'z')) ) {
                alt9=12;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 7, input);

                throw nvae;

            }
            }
            break;
        case '=':
            {
            alt9=7;
            }
            break;
        case ';':
            {
            alt9=8;
            }
            break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            {
            alt9=9;
            }
            break;
        case 's':
            {
            int LA9_11 = input.LA(2);

            if ( (LA9_11=='e') ) {
                int LA9_18 = input.LA(3);

                if ( (LA9_18=='c') ) {
                    int LA9_26 = input.LA(4);

                    if ( (LA9_26=='t') ) {
                        int LA9_31 = input.LA(5);

                        if ( (LA9_31=='i') ) {
                            int LA9_35 = input.LA(6);

                            if ( (LA9_35=='o') ) {
                                int LA9_37 = input.LA(7);

                                if ( (LA9_37=='n') ) {
                                    int LA9_39 = input.LA(8);

                                    if ( (LA9_39=='-'||(LA9_39 >= '0' && LA9_39 <= '9')||(LA9_39 >= 'A' && LA9_39 <= 'Z')||LA9_39=='_'||(LA9_39 >= 'a' && LA9_39 <= 'z')) ) {
                                        alt9=12;
                                    }
                                    else {
                                        alt9=10;
                                    }
                                }
                                else {
                                    alt9=12;
                                }
                            }
                            else {
                                alt9=12;
                            }
                        }
                        else {
                            alt9=12;
                        }
                    }
                    else {
                        alt9=12;
                    }
                }
                else {
                    alt9=12;
                }
            }
            else if ( (LA9_11=='-'||(LA9_11 >= '0' && LA9_11 <= '9')||(LA9_11 >= 'A' && LA9_11 <= 'Z')||LA9_11=='_'||(LA9_11 >= 'a' && LA9_11 <= 'd')||(LA9_11 >= 'f' && LA9_11 <= 'z')) ) {
                alt9=12;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 11, input);

                throw nvae;

            }
            }
            break;
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        case 'H':
        case 'I':
        case 'J':
        case 'K':
        case 'L':
        case 'M':
        case 'N':
        case 'O':
        case 'P':
        case 'Q':
        case 'R':
        case 'S':
        case 'T':
        case 'U':
        case 'V':
        case 'W':
        case 'X':
        case 'Y':
        case 'Z':
        case '_':
        case 'a':
        case 'b':
        case 'c':
        case 'd':
        case 'e':
        case 'g':
        case 'h':
        case 'i':
        case 'j':
        case 'k':
        case 'l':
        case 'm':
        case 'n':
        case 'o':
        case 'p':
        case 'q':
        case 'r':
        case 'u':
        case 'v':
        case 'w':
        case 'x':
        case 'y':
        case 'z':
            {
            alt9=12;
            }
            break;
        case '\"':
            {
            int LA9_13 = input.LA(2);

            if ( (LA9_13=='\"') ) {
                int LA9_19 = input.LA(3);

                if ( (LA9_19=='\"') ) {
                    alt9=14;
                }
                else {
                    alt9=13;
                }
            }
            else if ( ((LA9_13 >= '\u0000' && LA9_13 <= '\t')||(LA9_13 >= '\u000B' && LA9_13 <= '\f')||(LA9_13 >= '\u000E' && LA9_13 <= '!')||(LA9_13 >= '#' && LA9_13 <= '\uFFFF')) ) {
                alt9=13;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 13, input);

                throw nvae;

            }
            }
            break;
        case '\t':
        case '\n':
        case '\r':
        case ' ':
            {
            alt9=15;
            }
            break;
        case '/':
            {
            int LA9_15 = input.LA(2);

            if ( (LA9_15=='*') ) {
                alt9=16;
            }
            else if ( (LA9_15=='/') ) {
                alt9=17;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 15, input);

                throw nvae;

            }
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("", 9, 0, input);

            throw nvae;

        }

        switch (alt9) {
            case 1 :
                // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:10: T__18
                {
                mT__18(); 


                }
                break;
            case 2 :
                // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:16: T__19
                {
                mT__19(); 


                }
                break;
            case 3 :
                // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:22: T__20
                {
                mT__20(); 


                }
                break;
            case 4 :
                // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:28: BO
                {
                mBO(); 


                }
                break;
            case 5 :
                // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:31: BC
                {
                mBC(); 


                }
                break;
            case 6 :
                // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:34: BOOL
                {
                mBOOL(); 


                }
                break;
            case 7 :
                // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:39: EQ
                {
                mEQ(); 


                }
                break;
            case 8 :
                // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:42: EOS
                {
                mEOS(); 


                }
                break;
            case 9 :
                // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:46: NUMBER
                {
                mNUMBER(); 


                }
                break;
            case 10 :
                // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:53: SECTION
                {
                mSECTION(); 


                }
                break;
            case 11 :
                // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:61: TRAN
                {
                mTRAN(); 


                }
                break;
            case 12 :
                // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:66: ID
                {
                mID(); 


                }
                break;
            case 13 :
                // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:69: TEXT
                {
                mTEXT(); 


                }
                break;
            case 14 :
                // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:74: MULTILINE
                {
                mMULTILINE(); 


                }
                break;
            case 15 :
                // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:84: WS
                {
                mWS(); 


                }
                break;
            case 16 :
                // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:87: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 17 :
                // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:95: LINE_COMMENT
                {
                mLINE_COMMENT(); 


                }
                break;

        }

    }


 

}
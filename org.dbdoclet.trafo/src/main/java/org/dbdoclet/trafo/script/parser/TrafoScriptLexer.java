// $ANTLR 3.4 /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g 2014-07-22 16:28:15

package org.dbdoclet.trafo.script.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class TrafoScriptLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
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
    public static final int REGEXP=14;
    public static final int SECTION=15;
    public static final int TEXT=16;
    public static final int TRAN=17;
    public static final int WS=18;

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
    public String getGrammarFileName() { return "/home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g"; }

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:6:7: ( ',' )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:6:9: ','
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
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:7:7: ( '[' )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:7:9: '['
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
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:8:7: ( ']' )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:8:9: ']'
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
    // $ANTLR end "T__21"

    // $ANTLR start "BO"
    public final void mBO() throws RecognitionException {
        try {
            int _type = BO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:51:4: ( '{' )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:51:6: '{'
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
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:52:4: ( '}' )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:52:6: '}'
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
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:53:6: ( 'true' | 'false' )
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
                    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:53:8: 'true'
                    {
                    match("true"); 



                    }
                    break;
                case 2 :
                    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:53:15: 'false'
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
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:54:4: ( '=' )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:54:6: '='
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
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:55:5: ( ';' )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:55:7: ';'
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
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:56:8: ( ( '0' .. '9' )+ )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:56:10: ( '0' .. '9' )+
            {
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:56:10: ( '0' .. '9' )+
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
            	    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:
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
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:57:9: ( 'section' )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:57:11: 'section'
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
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:58:7: ( 'transformation' )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:58:9: 'transformation'
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
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:59:4: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '.' | '_' )+ )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:59:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '.' | '_' )+
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:59:29: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '.' | '_' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= '-' && LA3_0 <= '.')||(LA3_0 >= '0' && LA3_0 <= '9')||(LA3_0 >= 'A' && LA3_0 <= 'Z')||LA3_0=='_'||(LA3_0 >= 'a' && LA3_0 <= 'z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:
            	    {
            	    if ( (input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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

    // $ANTLR start "REGEXP"
    public final void mREGEXP() throws RecognitionException {
        try {
            int _type = REGEXP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:60:9: ( '/' ~ ( '*' ) ( '\\\\/' |~ ( '/' ) )* '/' )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:60:11: '/' ~ ( '*' ) ( '\\\\/' |~ ( '/' ) )* '/'
            {
            match('/'); 

            if ( (input.LA(1) >= '\u0000' && input.LA(1) <= ')')||(input.LA(1) >= '+' && input.LA(1) <= '\uFFFF') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:60:22: ( '\\\\/' |~ ( '/' ) )*
            loop4:
            do {
                int alt4=3;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='\\') ) {
                    int LA4_2 = input.LA(2);

                    if ( (LA4_2=='/') ) {
                        int LA4_4 = input.LA(3);

                        if ( ((LA4_4 >= '\u0000' && LA4_4 <= '\uFFFF')) ) {
                            alt4=1;
                        }

                        else {
                            alt4=2;
                        }


                    }
                    else if ( ((LA4_2 >= '\u0000' && LA4_2 <= '.')||(LA4_2 >= '0' && LA4_2 <= '\uFFFF')) ) {
                        alt4=2;
                    }


                }
                else if ( ((LA4_0 >= '\u0000' && LA4_0 <= '.')||(LA4_0 >= '0' && LA4_0 <= '[')||(LA4_0 >= ']' && LA4_0 <= '\uFFFF')) ) {
                    alt4=2;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:60:23: '\\\\/'
            	    {
            	    match("\\/"); 



            	    }
            	    break;
            	case 2 :
            	    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:60:29: ~ ( '/' )
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '\uFFFF') ) {
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


            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "REGEXP"

    // $ANTLR start "TEXT"
    public final void mTEXT() throws RecognitionException {
        try {
            int _type = TEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:61:6: ( '\"' (~ ( '\"' | '\\r' | '\\n' ) )* '\"' )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:61:8: '\"' (~ ( '\"' | '\\r' | '\\n' ) )* '\"'
            {
            match('\"'); 

            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:61:12: (~ ( '\"' | '\\r' | '\\n' ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0 >= '\u0000' && LA5_0 <= '\t')||(LA5_0 >= '\u000B' && LA5_0 <= '\f')||(LA5_0 >= '\u000E' && LA5_0 <= '!')||(LA5_0 >= '#' && LA5_0 <= '\uFFFF')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:
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
            	    break loop5;
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
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:63:2: ( '\"\"\"' ( options {greedy=false; } : ( . )* ) '\"\"\"' )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:63:4: '\"\"\"' ( options {greedy=false; } : ( . )* ) '\"\"\"'
            {
            match("\"\"\""); 



            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:63:10: ( options {greedy=false; } : ( . )* )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:63:35: ( . )*
            {
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:63:35: ( . )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='\"') ) {
                    int LA6_1 = input.LA(2);

                    if ( (LA6_1=='\"') ) {
                        int LA6_3 = input.LA(3);

                        if ( (LA6_3=='\"') ) {
                            alt6=2;
                        }
                        else if ( ((LA6_3 >= '\u0000' && LA6_3 <= '!')||(LA6_3 >= '#' && LA6_3 <= '\uFFFF')) ) {
                            alt6=1;
                        }


                    }
                    else if ( ((LA6_1 >= '\u0000' && LA6_1 <= '!')||(LA6_1 >= '#' && LA6_1 <= '\uFFFF')) ) {
                        alt6=1;
                    }


                }
                else if ( ((LA6_0 >= '\u0000' && LA6_0 <= '!')||(LA6_0 >= '#' && LA6_0 <= '\uFFFF')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:63:35: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop6;
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
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:64:4: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:64:6: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:64:6: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0 >= '\t' && LA7_0 <= '\n')||LA7_0=='\r'||LA7_0==' ') ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:
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
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
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
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:70:17: ( '/*' ( options {greedy=false; } : ( . )* ) '*/' )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:70:19: '/*' ( options {greedy=false; } : ( . )* ) '*/'
            {
            match("/*"); 



            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:70:24: ( options {greedy=false; } : ( . )* )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:70:54: ( . )*
            {
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:70:54: ( . )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='*') ) {
                    int LA8_1 = input.LA(2);

                    if ( (LA8_1=='/') ) {
                        alt8=2;
                    }
                    else if ( ((LA8_1 >= '\u0000' && LA8_1 <= '.')||(LA8_1 >= '0' && LA8_1 <= '\uFFFF')) ) {
                        alt8=1;
                    }


                }
                else if ( ((LA8_0 >= '\u0000' && LA8_0 <= ')')||(LA8_0 >= '+' && LA8_0 <= '\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:70:54: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop8;
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
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:71:17: ( '//' ( . )* '\\n' )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:71:19: '//' ( . )* '\\n'
            {
            match("//"); 



            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:71:24: ( . )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='\n') ) {
                    alt9=2;
                }
                else if ( ((LA9_0 >= '\u0000' && LA9_0 <= '\t')||(LA9_0 >= '\u000B' && LA9_0 <= '\uFFFF')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:71:24: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop9;
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
        // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:8: ( T__19 | T__20 | T__21 | BO | BC | BOOL | EQ | EOS | NUMBER | SECTION | TRAN | ID | REGEXP | TEXT | MULTILINE | WS | COMMENT | LINE_COMMENT )
        int alt10=18;
        alt10 = dfa10.predict(input);
        switch (alt10) {
            case 1 :
                // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:10: T__19
                {
                mT__19(); 


                }
                break;
            case 2 :
                // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:16: T__20
                {
                mT__20(); 


                }
                break;
            case 3 :
                // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:22: T__21
                {
                mT__21(); 


                }
                break;
            case 4 :
                // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:28: BO
                {
                mBO(); 


                }
                break;
            case 5 :
                // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:31: BC
                {
                mBC(); 


                }
                break;
            case 6 :
                // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:34: BOOL
                {
                mBOOL(); 


                }
                break;
            case 7 :
                // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:39: EQ
                {
                mEQ(); 


                }
                break;
            case 8 :
                // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:42: EOS
                {
                mEOS(); 


                }
                break;
            case 9 :
                // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:46: NUMBER
                {
                mNUMBER(); 


                }
                break;
            case 10 :
                // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:53: SECTION
                {
                mSECTION(); 


                }
                break;
            case 11 :
                // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:61: TRAN
                {
                mTRAN(); 


                }
                break;
            case 12 :
                // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:66: ID
                {
                mID(); 


                }
                break;
            case 13 :
                // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:69: REGEXP
                {
                mREGEXP(); 


                }
                break;
            case 14 :
                // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:76: TEXT
                {
                mTEXT(); 


                }
                break;
            case 15 :
                // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:81: MULTILINE
                {
                mMULTILINE(); 


                }
                break;
            case 16 :
                // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:91: WS
                {
                mWS(); 


                }
                break;
            case 17 :
                // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:94: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 18 :
                // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:1:102: LINE_COMMENT
                {
                mLINE_COMMENT(); 


                }
                break;

        }

    }


    protected DFA10 dfa10 = new DFA10(this);
    static final String DFA10_eotS =
        "\20\uffff\3\14\3\uffff\1\27\1\uffff\4\14\1\uffff\1\46\1\25\2\uffff"+
        "\1\47\3\14\1\25\2\uffff\1\14\1\47\4\14\1\60\1\14\1\uffff\5\14\1"+
        "\67\1\uffff";
    static final String DFA10_eofS =
        "\70\uffff";
    static final String DFA10_minS =
        "\1\11\5\uffff\2\55\3\uffff\1\55\1\uffff\2\0\1\uffff\1\141\1\154"+
        "\1\143\1\0\2\uffff\1\42\1\uffff\1\145\1\156\1\163\1\164\4\0\1\uffff"+
        "\1\55\1\163\1\145\1\151\1\0\2\uffff\1\146\1\55\2\157\1\156\1\162"+
        "\1\55\1\155\1\uffff\1\141\1\164\1\151\1\157\1\156\1\55\1\uffff";
    static final String DFA10_maxS =
        "\1\175\5\uffff\2\172\3\uffff\1\172\1\uffff\2\uffff\1\uffff\1\165"+
        "\1\154\1\143\1\uffff\2\uffff\1\42\1\uffff\1\145\1\156\1\163\1\164"+
        "\4\uffff\1\uffff\1\172\1\163\1\145\1\151\1\uffff\2\uffff\1\146\1"+
        "\172\2\157\1\156\1\162\1\172\1\155\1\uffff\1\141\1\164\1\151\1\157"+
        "\1\156\1\172\1\uffff";
    static final String DFA10_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\2\uffff\1\7\1\10\1\11\1\uffff\1\14"+
        "\2\uffff\1\20\4\uffff\1\21\1\15\1\uffff\1\16\10\uffff\1\17\5\uffff"+
        "\1\22\1\6\10\uffff\1\12\6\uffff\1\13";
    static final String DFA10_specialS =
        "\15\uffff\1\1\1\0\4\uffff\1\2\10\uffff\1\3\1\6\1\4\1\5\5\uffff\1"+
        "\7\22\uffff}>";
    static final String[] DFA10_transitionS = {
            "\2\17\2\uffff\1\17\22\uffff\1\17\1\uffff\1\16\11\uffff\1\1\2"+
            "\uffff\1\15\12\12\1\uffff\1\11\1\uffff\1\10\3\uffff\32\14\1"+
            "\2\1\uffff\1\3\1\uffff\1\14\1\uffff\5\14\1\7\14\14\1\13\1\6"+
            "\6\14\1\4\1\uffff\1\5",
            "",
            "",
            "",
            "",
            "",
            "\2\14\1\uffff\12\14\7\uffff\32\14\4\uffff\1\14\1\uffff\21\14"+
            "\1\20\10\14",
            "\2\14\1\uffff\12\14\7\uffff\32\14\4\uffff\1\14\1\uffff\1\21"+
            "\31\14",
            "",
            "",
            "",
            "\2\14\1\uffff\12\14\7\uffff\32\14\4\uffff\1\14\1\uffff\4\14"+
            "\1\22\25\14",
            "",
            "\52\25\1\24\4\25\1\23\uffd0\25",
            "\12\27\1\uffff\2\27\1\uffff\24\27\1\26\uffdd\27",
            "",
            "\1\31\23\uffff\1\30",
            "\1\32",
            "\1\33",
            "\12\37\1\35\44\37\1\36\54\37\1\34\uffa3\37",
            "",
            "",
            "\1\40",
            "",
            "\1\41",
            "\1\42",
            "\1\43",
            "\1\44",
            "\12\37\1\35\44\37\1\45\54\37\1\34\uffa3\37",
            "\12\37\1\35\44\37\1\36\54\37\1\34\uffa3\37",
            "\0\46",
            "\12\37\1\35\44\37\1\36\54\37\1\34\uffa3\37",
            "",
            "\2\14\1\uffff\12\14\7\uffff\32\14\4\uffff\1\14\1\uffff\32\14",
            "\1\50",
            "\1\51",
            "\1\52",
            "\12\37\1\35\44\37\1\36\54\37\1\34\uffa3\37",
            "",
            "",
            "\1\53",
            "\2\14\1\uffff\12\14\7\uffff\32\14\4\uffff\1\14\1\uffff\32\14",
            "\1\54",
            "\1\55",
            "\1\56",
            "\1\57",
            "\2\14\1\uffff\12\14\7\uffff\32\14\4\uffff\1\14\1\uffff\32\14",
            "\1\61",
            "",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "\2\14\1\uffff\12\14\7\uffff\32\14\4\uffff\1\14\1\uffff\32\14",
            ""
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__19 | T__20 | T__21 | BO | BC | BOOL | EQ | EOS | NUMBER | SECTION | TRAN | ID | REGEXP | TEXT | MULTILINE | WS | COMMENT | LINE_COMMENT );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA10_14 = input.LA(1);

                        s = -1;
                        if ( (LA10_14=='\"') ) {s = 22;}

                        else if ( ((LA10_14 >= '\u0000' && LA10_14 <= '\t')||(LA10_14 >= '\u000B' && LA10_14 <= '\f')||(LA10_14 >= '\u000E' && LA10_14 <= '!')||(LA10_14 >= '#' && LA10_14 <= '\uFFFF')) ) {s = 23;}

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA10_13 = input.LA(1);

                        s = -1;
                        if ( (LA10_13=='/') ) {s = 19;}

                        else if ( (LA10_13=='*') ) {s = 20;}

                        else if ( ((LA10_13 >= '\u0000' && LA10_13 <= ')')||(LA10_13 >= '+' && LA10_13 <= '.')||(LA10_13 >= '0' && LA10_13 <= '\uFFFF')) ) {s = 21;}

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA10_19 = input.LA(1);

                        s = -1;
                        if ( (LA10_19=='\\') ) {s = 28;}

                        else if ( (LA10_19=='\n') ) {s = 29;}

                        else if ( (LA10_19=='/') ) {s = 30;}

                        else if ( ((LA10_19 >= '\u0000' && LA10_19 <= '\t')||(LA10_19 >= '\u000B' && LA10_19 <= '.')||(LA10_19 >= '0' && LA10_19 <= '[')||(LA10_19 >= ']' && LA10_19 <= '\uFFFF')) ) {s = 31;}

                        if ( s>=0 ) return s;
                        break;

                    case 3 : 
                        int LA10_28 = input.LA(1);

                        s = -1;
                        if ( (LA10_28=='/') ) {s = 37;}

                        else if ( (LA10_28=='\\') ) {s = 28;}

                        else if ( (LA10_28=='\n') ) {s = 29;}

                        else if ( ((LA10_28 >= '\u0000' && LA10_28 <= '\t')||(LA10_28 >= '\u000B' && LA10_28 <= '.')||(LA10_28 >= '0' && LA10_28 <= '[')||(LA10_28 >= ']' && LA10_28 <= '\uFFFF')) ) {s = 31;}

                        if ( s>=0 ) return s;
                        break;

                    case 4 : 
                        int LA10_30 = input.LA(1);

                        s = -1;
                        if ( ((LA10_30 >= '\u0000' && LA10_30 <= '\uFFFF')) ) {s = 38;}

                        else s = 21;

                        if ( s>=0 ) return s;
                        break;

                    case 5 : 
                        int LA10_31 = input.LA(1);

                        s = -1;
                        if ( (LA10_31=='/') ) {s = 30;}

                        else if ( (LA10_31=='\\') ) {s = 28;}

                        else if ( (LA10_31=='\n') ) {s = 29;}

                        else if ( ((LA10_31 >= '\u0000' && LA10_31 <= '\t')||(LA10_31 >= '\u000B' && LA10_31 <= '.')||(LA10_31 >= '0' && LA10_31 <= '[')||(LA10_31 >= ']' && LA10_31 <= '\uFFFF')) ) {s = 31;}

                        if ( s>=0 ) return s;
                        break;

                    case 6 : 
                        int LA10_29 = input.LA(1);

                        s = -1;
                        if ( (LA10_29=='/') ) {s = 30;}

                        else if ( (LA10_29=='\\') ) {s = 28;}

                        else if ( (LA10_29=='\n') ) {s = 29;}

                        else if ( ((LA10_29 >= '\u0000' && LA10_29 <= '\t')||(LA10_29 >= '\u000B' && LA10_29 <= '.')||(LA10_29 >= '0' && LA10_29 <= '[')||(LA10_29 >= ']' && LA10_29 <= '\uFFFF')) ) {s = 31;}

                        else s = 38;

                        if ( s>=0 ) return s;
                        break;

                    case 7 : 
                        int LA10_37 = input.LA(1);

                        s = -1;
                        if ( (LA10_37=='/') ) {s = 30;}

                        else if ( (LA10_37=='\\') ) {s = 28;}

                        else if ( (LA10_37=='\n') ) {s = 29;}

                        else if ( ((LA10_37 >= '\u0000' && LA10_37 <= '\t')||(LA10_37 >= '\u000B' && LA10_37 <= '.')||(LA10_37 >= '0' && LA10_37 <= '[')||(LA10_37 >= ']' && LA10_37 <= '\uFFFF')) ) {s = 31;}

                        else s = 21;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 10, _s, input);
            error(nvae);
            throw nvae;
        }

    }
 

}
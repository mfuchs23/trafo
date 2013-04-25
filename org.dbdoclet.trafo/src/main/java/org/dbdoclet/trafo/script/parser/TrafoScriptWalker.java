// $ANTLR 3.4 /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g 2012-10-19 13:08:51

package org.dbdoclet.trafo.script.parser;
import org.dbdoclet.trafo.script.Script;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class TrafoScriptWalker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BC", "BO", "BOOL", "COMMENT", "EOS", "EQ", "ID", "LINE_COMMENT", "MULTILINE", "NUMBER", "SECTION", "TEXT", "TRAN", "WS", "','", "'['", "']'"
    };

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
    public TreeParser[] getDelegates() {
        return new TreeParser[] {};
    }

    // delegators


    public TrafoScriptWalker(TreeNodeStream input) {
        this(input, new RecognizerSharedState());
    }
    public TrafoScriptWalker(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return TrafoScriptWalker.tokenNames; }
    public String getGrammarFileName() { return "/home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g"; }


      
      Script script;
      String namespace;



    // $ANTLR start "parse"
    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:19:1: parse[Script script, String namespace] : transformation ( section )* ;
    public final void parse(Script script, String namespace) throws RecognitionException {

           this.script = script;
           this.namespace = namespace;

        try {
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:24:3: ( transformation ( section )* )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:24:3: transformation ( section )*
            {
            pushFollow(FOLLOW_transformation_in_parse53);
            transformation();

            state._fsp--;


            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:24:18: ( section )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==ID) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:24:18: section
            	    {
            	    pushFollow(FOLLOW_section_in_parse55);
            	    section();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "parse"



    // $ANTLR start "transformation"
    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:26:1: transformation : ^( TRAN ID ) ;
    public final void transformation() throws RecognitionException {
        CommonTree ID1=null;

        try {
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:26:16: ( ^( TRAN ID ) )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:26:18: ^( TRAN ID )
            {
            match(input,TRAN,FOLLOW_TRAN_in_transformation65); 

            match(input, Token.DOWN, null); 
            ID1=(CommonTree)match(input,ID,FOLLOW_ID_in_transformation67); 

             script.setSystemParameter(namespace, Script.SYSPARAM_TRANSFORMATION_NAME, (ID1!=null?ID1.getText():null)); 

            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "transformation"



    // $ANTLR start "section"
    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:27:1: section : ^( ID ( expr )* ) ;
    public final void section() throws RecognitionException {
        CommonTree ID2=null;

        try {
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:27:9: ( ^( ID ( expr )* ) )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:27:11: ^( ID ( expr )* )
            {
            ID2=(CommonTree)match(input,ID,FOLLOW_ID_in_section78); 

             script.selectSection(namespace, (ID2!=null?ID2.getText():null)); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:27:63: ( expr )*
                loop2:
                do {
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==ID) ) {
                        alt2=1;
                    }


                    switch (alt2) {
                	case 1 :
                	    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:27:63: expr
                	    {
                	    pushFollow(FOLLOW_expr_in_section82);
                	    expr();

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop2;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "section"



    // $ANTLR start "expr"
    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:28:1: expr : ^( ID ( bparam[$ID.text] | nparam[$ID.text] | tparam[$ID.text] | mtparam[$ID.text] )+ ) ;
    public final void expr() throws RecognitionException {
        CommonTree ID3=null;

        try {
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:28:6: ( ^( ID ( bparam[$ID.text] | nparam[$ID.text] | tparam[$ID.text] | mtparam[$ID.text] )+ ) )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:28:8: ^( ID ( bparam[$ID.text] | nparam[$ID.text] | tparam[$ID.text] | mtparam[$ID.text] )+ )
            {
            ID3=(CommonTree)match(input,ID,FOLLOW_ID_in_expr92); 

            match(input, Token.DOWN, null); 
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:28:13: ( bparam[$ID.text] | nparam[$ID.text] | tparam[$ID.text] | mtparam[$ID.text] )+
            int cnt3=0;
            loop3:
            do {
                int alt3=5;
                switch ( input.LA(1) ) {
                case BOOL:
                    {
                    alt3=1;
                    }
                    break;
                case NUMBER:
                    {
                    alt3=2;
                    }
                    break;
                case TEXT:
                    {
                    alt3=3;
                    }
                    break;
                case MULTILINE:
                    {
                    alt3=4;
                    }
                    break;

                }

                switch (alt3) {
            	case 1 :
            	    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:28:14: bparam[$ID.text]
            	    {
            	    pushFollow(FOLLOW_bparam_in_expr95);
            	    bparam((ID3!=null?ID3.getText():null));

            	    state._fsp--;


            	    }
            	    break;
            	case 2 :
            	    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:28:33: nparam[$ID.text]
            	    {
            	    pushFollow(FOLLOW_nparam_in_expr100);
            	    nparam((ID3!=null?ID3.getText():null));

            	    state._fsp--;


            	    }
            	    break;
            	case 3 :
            	    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:28:52: tparam[$ID.text]
            	    {
            	    pushFollow(FOLLOW_tparam_in_expr105);
            	    tparam((ID3!=null?ID3.getText():null));

            	    state._fsp--;


            	    }
            	    break;
            	case 4 :
            	    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:28:71: mtparam[$ID.text]
            	    {
            	    pushFollow(FOLLOW_mtparam_in_expr110);
            	    mtparam((ID3!=null?ID3.getText():null));

            	    state._fsp--;


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


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "expr"



    // $ANTLR start "bparam"
    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:29:1: bparam[String ID] : BOOL ;
    public final void bparam(String ID) throws RecognitionException {
        CommonTree BOOL4=null;

        try {
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:29:18: ( BOOL )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:29:20: BOOL
            {
            BOOL4=(CommonTree)match(input,BOOL,FOLLOW_BOOL_in_bparam121); 

             script.addBoolParam(ID, (BOOL4!=null?BOOL4.getText():null)); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "bparam"



    // $ANTLR start "nparam"
    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:30:1: nparam[String ID] : NUMBER ;
    public final void nparam(String ID) throws RecognitionException {
        CommonTree NUMBER5=null;

        try {
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:30:18: ( NUMBER )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:30:20: NUMBER
            {
            NUMBER5=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_nparam130); 

             script.addNumberParam(ID, (NUMBER5!=null?NUMBER5.getText():null)); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "nparam"



    // $ANTLR start "tparam"
    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:31:1: tparam[String ID] : TEXT ;
    public final void tparam(String ID) throws RecognitionException {
        CommonTree TEXT6=null;

        try {
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:31:18: ( TEXT )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:31:20: TEXT
            {
            TEXT6=(CommonTree)match(input,TEXT,FOLLOW_TEXT_in_tparam139); 

             script.addTextParam(ID, (TEXT6!=null?TEXT6.getText():null)); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "tparam"



    // $ANTLR start "mtparam"
    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:32:1: mtparam[String ID] : MULTILINE ;
    public final void mtparam(String ID) throws RecognitionException {
        CommonTree MULTILINE7=null;

        try {
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:32:19: ( MULTILINE )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScriptWalker.g:32:25: MULTILINE
            {
            MULTILINE7=(CommonTree)match(input,MULTILINE,FOLLOW_MULTILINE_in_mtparam152); 

             script.addTextParam(ID, (MULTILINE7!=null?MULTILINE7.getText():null)); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "mtparam"

    // Delegated rules


 

    public static final BitSet FOLLOW_transformation_in_parse53 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_section_in_parse55 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_TRAN_in_transformation65 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_transformation67 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_section78 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_section82 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_ID_in_expr92 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_bparam_in_expr95 = new BitSet(new long[]{0x000000000000B048L});
    public static final BitSet FOLLOW_nparam_in_expr100 = new BitSet(new long[]{0x000000000000B048L});
    public static final BitSet FOLLOW_tparam_in_expr105 = new BitSet(new long[]{0x000000000000B048L});
    public static final BitSet FOLLOW_mtparam_in_expr110 = new BitSet(new long[]{0x000000000000B048L});
    public static final BitSet FOLLOW_BOOL_in_bparam121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_nparam130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEXT_in_tparam139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MULTILINE_in_mtparam152 = new BitSet(new long[]{0x0000000000000002L});

}
// $ANTLR 3.4 /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g 2014-07-22 16:28:14

package org.dbdoclet.trafo.script.parser;

import java.util.LinkedList;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class TrafoScriptParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BC", "BO", "BOOL", "COMMENT", "EOS", "EQ", "ID", "LINE_COMMENT", "MULTILINE", "NUMBER", "REGEXP", "SECTION", "TEXT", "TRAN", "WS", "','", "'['", "']'"
    };

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
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public TrafoScriptParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public TrafoScriptParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return TrafoScriptParser.tokenNames; }
    public String getGrammarFileName() { return "/home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g"; }


      
      private List<String> errors = new LinkedList<String>();

      public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
     
        String hdr = getErrorHeader(e);
        String msg = getErrorMessage(e, tokenNames);
        errors.add(hdr + " " + msg);
      }

      public List<String> getErrors() {
        return errors;
      }


    public static class parse_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "parse"
    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:35:1: parse : transformation ( section )* EOF ;
    public final TrafoScriptParser.parse_return parse() throws RecognitionException {
        TrafoScriptParser.parse_return retval = new TrafoScriptParser.parse_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token EOF3=null;
        TrafoScriptParser.transformation_return transformation1 =null;

        TrafoScriptParser.section_return section2 =null;


        CommonTree EOF3_tree=null;

        try {
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:35:7: ( transformation ( section )* EOF )
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:35:9: transformation ( section )* EOF
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_transformation_in_parse54);
            transformation1=transformation();

            state._fsp--;

            adaptor.addChild(root_0, transformation1.getTree());

            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:35:24: ( section )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==SECTION) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:35:25: section
            	    {
            	    pushFollow(FOLLOW_section_in_parse57);
            	    section2=section();

            	    state._fsp--;

            	    adaptor.addChild(root_0, section2.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            EOF3=(Token)match(input,EOF,FOLLOW_EOF_in_parse61); 
            EOF3_tree = 
            (CommonTree)adaptor.create(EOF3)
            ;
            adaptor.addChild(root_0, EOF3_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "parse"


    public static class transformation_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "transformation"
    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:37:1: transformation : TRAN ^ ID EOS !;
    public final TrafoScriptParser.transformation_return transformation() throws RecognitionException {
        TrafoScriptParser.transformation_return retval = new TrafoScriptParser.transformation_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token TRAN4=null;
        Token ID5=null;
        Token EOS6=null;

        CommonTree TRAN4_tree=null;
        CommonTree ID5_tree=null;
        CommonTree EOS6_tree=null;

        try {
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:38:2: ( TRAN ^ ID EOS !)
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:38:4: TRAN ^ ID EOS !
            {
            root_0 = (CommonTree)adaptor.nil();


            TRAN4=(Token)match(input,TRAN,FOLLOW_TRAN_in_transformation70); 
            TRAN4_tree = 
            (CommonTree)adaptor.create(TRAN4)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(TRAN4_tree, root_0);


            ID5=(Token)match(input,ID,FOLLOW_ID_in_transformation73); 
            ID5_tree = 
            (CommonTree)adaptor.create(ID5)
            ;
            adaptor.addChild(root_0, ID5_tree);


            EOS6=(Token)match(input,EOS,FOLLOW_EOS_in_transformation75); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "transformation"


    public static class section_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "section"
    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:40:1: section : SECTION ! ( ID ^| REGEXP ^) BO ! ( param )* BC !;
    public final TrafoScriptParser.section_return section() throws RecognitionException {
        TrafoScriptParser.section_return retval = new TrafoScriptParser.section_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token SECTION7=null;
        Token ID8=null;
        Token REGEXP9=null;
        Token BO10=null;
        Token BC12=null;
        TrafoScriptParser.param_return param11 =null;


        CommonTree SECTION7_tree=null;
        CommonTree ID8_tree=null;
        CommonTree REGEXP9_tree=null;
        CommonTree BO10_tree=null;
        CommonTree BC12_tree=null;

        try {
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:40:9: ( SECTION ! ( ID ^| REGEXP ^) BO ! ( param )* BC !)
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:40:11: SECTION ! ( ID ^| REGEXP ^) BO ! ( param )* BC !
            {
            root_0 = (CommonTree)adaptor.nil();


            SECTION7=(Token)match(input,SECTION,FOLLOW_SECTION_in_section85); 

            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:40:20: ( ID ^| REGEXP ^)
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==ID) ) {
                alt2=1;
            }
            else if ( (LA2_0==REGEXP) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }
            switch (alt2) {
                case 1 :
                    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:40:21: ID ^
                    {
                    ID8=(Token)match(input,ID,FOLLOW_ID_in_section89); 
                    ID8_tree = 
                    (CommonTree)adaptor.create(ID8)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(ID8_tree, root_0);


                    }
                    break;
                case 2 :
                    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:40:25: REGEXP ^
                    {
                    REGEXP9=(Token)match(input,REGEXP,FOLLOW_REGEXP_in_section92); 
                    REGEXP9_tree = 
                    (CommonTree)adaptor.create(REGEXP9)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(REGEXP9_tree, root_0);


                    }
                    break;

            }


            BO10=(Token)match(input,BO,FOLLOW_BO_in_section96); 

            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:40:38: ( param )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==ID) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:40:39: param
            	    {
            	    pushFollow(FOLLOW_param_in_section100);
            	    param11=param();

            	    state._fsp--;

            	    adaptor.addChild(root_0, param11.getTree());

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            BC12=(Token)match(input,BC,FOLLOW_BC_in_section104); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "section"


    public static class param_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "param"
    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:42:1: param : ID ^ EQ ! expr EOS !;
    public final TrafoScriptParser.param_return param() throws RecognitionException {
        TrafoScriptParser.param_return retval = new TrafoScriptParser.param_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID13=null;
        Token EQ14=null;
        Token EOS16=null;
        TrafoScriptParser.expr_return expr15 =null;


        CommonTree ID13_tree=null;
        CommonTree EQ14_tree=null;
        CommonTree EOS16_tree=null;

        try {
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:42:7: ( ID ^ EQ ! expr EOS !)
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:42:9: ID ^ EQ ! expr EOS !
            {
            root_0 = (CommonTree)adaptor.nil();


            ID13=(Token)match(input,ID,FOLLOW_ID_in_param113); 
            ID13_tree = 
            (CommonTree)adaptor.create(ID13)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(ID13_tree, root_0);


            EQ14=(Token)match(input,EQ,FOLLOW_EQ_in_param116); 

            pushFollow(FOLLOW_expr_in_param119);
            expr15=expr();

            state._fsp--;

            adaptor.addChild(root_0, expr15.getTree());

            EOS16=(Token)match(input,EOS,FOLLOW_EOS_in_param121); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "param"


    public static class expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr"
    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:44:1: expr : ( TEXT | MULTILINE | NUMBER | BOOL | '[' ! ']' !| '[' ! expr ( ',' ! expr )* ']' !);
    public final TrafoScriptParser.expr_return expr() throws RecognitionException {
        TrafoScriptParser.expr_return retval = new TrafoScriptParser.expr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token TEXT17=null;
        Token MULTILINE18=null;
        Token NUMBER19=null;
        Token BOOL20=null;
        Token char_literal21=null;
        Token char_literal22=null;
        Token char_literal23=null;
        Token char_literal25=null;
        Token char_literal27=null;
        TrafoScriptParser.expr_return expr24 =null;

        TrafoScriptParser.expr_return expr26 =null;


        CommonTree TEXT17_tree=null;
        CommonTree MULTILINE18_tree=null;
        CommonTree NUMBER19_tree=null;
        CommonTree BOOL20_tree=null;
        CommonTree char_literal21_tree=null;
        CommonTree char_literal22_tree=null;
        CommonTree char_literal23_tree=null;
        CommonTree char_literal25_tree=null;
        CommonTree char_literal27_tree=null;

        try {
            // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:44:7: ( TEXT | MULTILINE | NUMBER | BOOL | '[' ! ']' !| '[' ! expr ( ',' ! expr )* ']' !)
            int alt5=6;
            switch ( input.LA(1) ) {
            case TEXT:
                {
                alt5=1;
                }
                break;
            case MULTILINE:
                {
                alt5=2;
                }
                break;
            case NUMBER:
                {
                alt5=3;
                }
                break;
            case BOOL:
                {
                alt5=4;
                }
                break;
            case 20:
                {
                int LA5_5 = input.LA(2);

                if ( (LA5_5==21) ) {
                    alt5=5;
                }
                else if ( (LA5_5==BOOL||(LA5_5 >= MULTILINE && LA5_5 <= NUMBER)||LA5_5==TEXT||LA5_5==20) ) {
                    alt5=6;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 5, input);

                    throw nvae;

                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:44:9: TEXT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    TEXT17=(Token)match(input,TEXT,FOLLOW_TEXT_in_expr131); 
                    TEXT17_tree = 
                    (CommonTree)adaptor.create(TEXT17)
                    ;
                    adaptor.addChild(root_0, TEXT17_tree);


                    }
                    break;
                case 2 :
                    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:45:4: MULTILINE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    MULTILINE18=(Token)match(input,MULTILINE,FOLLOW_MULTILINE_in_expr137); 
                    MULTILINE18_tree = 
                    (CommonTree)adaptor.create(MULTILINE18)
                    ;
                    adaptor.addChild(root_0, MULTILINE18_tree);


                    }
                    break;
                case 3 :
                    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:46:8: NUMBER
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    NUMBER19=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_expr146); 
                    NUMBER19_tree = 
                    (CommonTree)adaptor.create(NUMBER19)
                    ;
                    adaptor.addChild(root_0, NUMBER19_tree);


                    }
                    break;
                case 4 :
                    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:47:8: BOOL
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    BOOL20=(Token)match(input,BOOL,FOLLOW_BOOL_in_expr155); 
                    BOOL20_tree = 
                    (CommonTree)adaptor.create(BOOL20)
                    ;
                    adaptor.addChild(root_0, BOOL20_tree);


                    }
                    break;
                case 5 :
                    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:48:8: '[' ! ']' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal21=(Token)match(input,20,FOLLOW_20_in_expr164); 

                    char_literal22=(Token)match(input,21,FOLLOW_21_in_expr167); 

                    }
                    break;
                case 6 :
                    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:49:8: '[' ! expr ( ',' ! expr )* ']' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal23=(Token)match(input,20,FOLLOW_20_in_expr177); 

                    pushFollow(FOLLOW_expr_in_expr180);
                    expr24=expr();

                    state._fsp--;

                    adaptor.addChild(root_0, expr24.getTree());

                    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:49:18: ( ',' ! expr )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==19) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /home/michael/Java/workspaces/trafo8/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:49:19: ',' ! expr
                    	    {
                    	    char_literal25=(Token)match(input,19,FOLLOW_19_in_expr183); 

                    	    pushFollow(FOLLOW_expr_in_expr186);
                    	    expr26=expr();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, expr26.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    char_literal27=(Token)match(input,21,FOLLOW_21_in_expr190); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expr"

    // Delegated rules


 

    public static final BitSet FOLLOW_transformation_in_parse54 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_section_in_parse57 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_EOF_in_parse61 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRAN_in_transformation70 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_transformation73 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EOS_in_transformation75 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SECTION_in_section85 = new BitSet(new long[]{0x0000000000004400L});
    public static final BitSet FOLLOW_ID_in_section89 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_REGEXP_in_section92 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_BO_in_section96 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_param_in_section100 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_BC_in_section104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_param113 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_EQ_in_param116 = new BitSet(new long[]{0x0000000000113040L});
    public static final BitSet FOLLOW_expr_in_param119 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EOS_in_param121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEXT_in_expr131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MULTILINE_in_expr137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_expr146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_in_expr155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_expr164 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_expr167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_expr177 = new BitSet(new long[]{0x0000000000113040L});
    public static final BitSet FOLLOW_expr_in_expr180 = new BitSet(new long[]{0x0000000000280000L});
    public static final BitSet FOLLOW_19_in_expr183 = new BitSet(new long[]{0x0000000000113040L});
    public static final BitSet FOLLOW_expr_in_expr186 = new BitSet(new long[]{0x0000000000280000L});
    public static final BitSet FOLLOW_21_in_expr190 = new BitSet(new long[]{0x0000000000000002L});

}
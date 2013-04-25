// $ANTLR 3.4 /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g 2012-12-12 17:48:44

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
    public String getGrammarFileName() { return "/home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g"; }


      
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
    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:35:1: parse : transformation ( section )* EOF ;
    public final TrafoScriptParser.parse_return parse() throws RecognitionException {
        TrafoScriptParser.parse_return retval = new TrafoScriptParser.parse_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token EOF3=null;
        TrafoScriptParser.transformation_return transformation1 =null;

        TrafoScriptParser.section_return section2 =null;


        CommonTree EOF3_tree=null;

        try {
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:35:7: ( transformation ( section )* EOF )
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:35:9: transformation ( section )* EOF
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_transformation_in_parse54);
            transformation1=transformation();

            state._fsp--;

            adaptor.addChild(root_0, transformation1.getTree());

            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:35:24: ( section )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==SECTION) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:35:25: section
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
    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:37:1: transformation : TRAN ^ ID EOS !;
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
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:38:2: ( TRAN ^ ID EOS !)
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:38:4: TRAN ^ ID EOS !
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
    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:40:1: section : SECTION ! ID ^ BO ! ( param )* BC !;
    public final TrafoScriptParser.section_return section() throws RecognitionException {
        TrafoScriptParser.section_return retval = new TrafoScriptParser.section_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token SECTION7=null;
        Token ID8=null;
        Token BO9=null;
        Token BC11=null;
        TrafoScriptParser.param_return param10 =null;


        CommonTree SECTION7_tree=null;
        CommonTree ID8_tree=null;
        CommonTree BO9_tree=null;
        CommonTree BC11_tree=null;

        try {
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:40:9: ( SECTION ! ID ^ BO ! ( param )* BC !)
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:40:11: SECTION ! ID ^ BO ! ( param )* BC !
            {
            root_0 = (CommonTree)adaptor.nil();


            SECTION7=(Token)match(input,SECTION,FOLLOW_SECTION_in_section85); 

            ID8=(Token)match(input,ID,FOLLOW_ID_in_section88); 
            ID8_tree = 
            (CommonTree)adaptor.create(ID8)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(ID8_tree, root_0);


            BO9=(Token)match(input,BO,FOLLOW_BO_in_section91); 

            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:40:28: ( param )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==ID) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:40:29: param
            	    {
            	    pushFollow(FOLLOW_param_in_section95);
            	    param10=param();

            	    state._fsp--;

            	    adaptor.addChild(root_0, param10.getTree());

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            BC11=(Token)match(input,BC,FOLLOW_BC_in_section99); 

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
    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:42:1: param : ID ^ EQ ! expr EOS !;
    public final TrafoScriptParser.param_return param() throws RecognitionException {
        TrafoScriptParser.param_return retval = new TrafoScriptParser.param_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID12=null;
        Token EQ13=null;
        Token EOS15=null;
        TrafoScriptParser.expr_return expr14 =null;


        CommonTree ID12_tree=null;
        CommonTree EQ13_tree=null;
        CommonTree EOS15_tree=null;

        try {
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:42:7: ( ID ^ EQ ! expr EOS !)
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:42:9: ID ^ EQ ! expr EOS !
            {
            root_0 = (CommonTree)adaptor.nil();


            ID12=(Token)match(input,ID,FOLLOW_ID_in_param108); 
            ID12_tree = 
            (CommonTree)adaptor.create(ID12)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(ID12_tree, root_0);


            EQ13=(Token)match(input,EQ,FOLLOW_EQ_in_param111); 

            pushFollow(FOLLOW_expr_in_param114);
            expr14=expr();

            state._fsp--;

            adaptor.addChild(root_0, expr14.getTree());

            EOS15=(Token)match(input,EOS,FOLLOW_EOS_in_param116); 

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
    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:44:1: expr : ( TEXT | MULTILINE | NUMBER | BOOL | '[' ! ']' !| '[' ! expr ( ',' ! expr )* ']' !);
    public final TrafoScriptParser.expr_return expr() throws RecognitionException {
        TrafoScriptParser.expr_return retval = new TrafoScriptParser.expr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token TEXT16=null;
        Token MULTILINE17=null;
        Token NUMBER18=null;
        Token BOOL19=null;
        Token char_literal20=null;
        Token char_literal21=null;
        Token char_literal22=null;
        Token char_literal24=null;
        Token char_literal26=null;
        TrafoScriptParser.expr_return expr23 =null;

        TrafoScriptParser.expr_return expr25 =null;


        CommonTree TEXT16_tree=null;
        CommonTree MULTILINE17_tree=null;
        CommonTree NUMBER18_tree=null;
        CommonTree BOOL19_tree=null;
        CommonTree char_literal20_tree=null;
        CommonTree char_literal21_tree=null;
        CommonTree char_literal22_tree=null;
        CommonTree char_literal24_tree=null;
        CommonTree char_literal26_tree=null;

        try {
            // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:44:7: ( TEXT | MULTILINE | NUMBER | BOOL | '[' ! ']' !| '[' ! expr ( ',' ! expr )* ']' !)
            int alt4=6;
            switch ( input.LA(1) ) {
            case TEXT:
                {
                alt4=1;
                }
                break;
            case MULTILINE:
                {
                alt4=2;
                }
                break;
            case NUMBER:
                {
                alt4=3;
                }
                break;
            case BOOL:
                {
                alt4=4;
                }
                break;
            case 19:
                {
                int LA4_5 = input.LA(2);

                if ( (LA4_5==20) ) {
                    alt4=5;
                }
                else if ( (LA4_5==BOOL||(LA4_5 >= MULTILINE && LA4_5 <= NUMBER)||LA4_5==TEXT||LA4_5==19) ) {
                    alt4=6;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 5, input);

                    throw nvae;

                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:44:9: TEXT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    TEXT16=(Token)match(input,TEXT,FOLLOW_TEXT_in_expr126); 
                    TEXT16_tree = 
                    (CommonTree)adaptor.create(TEXT16)
                    ;
                    adaptor.addChild(root_0, TEXT16_tree);


                    }
                    break;
                case 2 :
                    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:45:4: MULTILINE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    MULTILINE17=(Token)match(input,MULTILINE,FOLLOW_MULTILINE_in_expr132); 
                    MULTILINE17_tree = 
                    (CommonTree)adaptor.create(MULTILINE17)
                    ;
                    adaptor.addChild(root_0, MULTILINE17_tree);


                    }
                    break;
                case 3 :
                    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:46:8: NUMBER
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    NUMBER18=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_expr141); 
                    NUMBER18_tree = 
                    (CommonTree)adaptor.create(NUMBER18)
                    ;
                    adaptor.addChild(root_0, NUMBER18_tree);


                    }
                    break;
                case 4 :
                    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:47:8: BOOL
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    BOOL19=(Token)match(input,BOOL,FOLLOW_BOOL_in_expr150); 
                    BOOL19_tree = 
                    (CommonTree)adaptor.create(BOOL19)
                    ;
                    adaptor.addChild(root_0, BOOL19_tree);


                    }
                    break;
                case 5 :
                    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:48:8: '[' ! ']' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal20=(Token)match(input,19,FOLLOW_19_in_expr159); 

                    char_literal21=(Token)match(input,20,FOLLOW_20_in_expr162); 

                    }
                    break;
                case 6 :
                    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:49:8: '[' ! expr ( ',' ! expr )* ']' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal22=(Token)match(input,19,FOLLOW_19_in_expr172); 

                    pushFollow(FOLLOW_expr_in_expr175);
                    expr23=expr();

                    state._fsp--;

                    adaptor.addChild(root_0, expr23.getTree());

                    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:49:18: ( ',' ! expr )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==18) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /home/michael/Java/workspaces/Development/org.dbdoclet.trafo/src/main/java/org/dbdoclet/trafo/script/parser/TrafoScript.g:49:19: ',' ! expr
                    	    {
                    	    char_literal24=(Token)match(input,18,FOLLOW_18_in_expr178); 

                    	    pushFollow(FOLLOW_expr_in_expr181);
                    	    expr25=expr();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, expr25.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    char_literal26=(Token)match(input,20,FOLLOW_20_in_expr185); 

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


 

    public static final BitSet FOLLOW_transformation_in_parse54 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_section_in_parse57 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_EOF_in_parse61 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRAN_in_transformation70 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_transformation73 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EOS_in_transformation75 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SECTION_in_section85 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_section88 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_BO_in_section91 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_param_in_section95 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_BC_in_section99 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_param108 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_EQ_in_param111 = new BitSet(new long[]{0x000000000008B040L});
    public static final BitSet FOLLOW_expr_in_param114 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EOS_in_param116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEXT_in_expr126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MULTILINE_in_expr132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_expr141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_in_expr150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_expr159 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_expr162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_expr172 = new BitSet(new long[]{0x000000000008B040L});
    public static final BitSet FOLLOW_expr_in_expr175 = new BitSet(new long[]{0x0000000000140000L});
    public static final BitSet FOLLOW_18_in_expr178 = new BitSet(new long[]{0x000000000008B040L});
    public static final BitSet FOLLOW_expr_in_expr181 = new BitSet(new long[]{0x0000000000140000L});
    public static final BitSet FOLLOW_20_in_expr185 = new BitSet(new long[]{0x0000000000000002L});

}
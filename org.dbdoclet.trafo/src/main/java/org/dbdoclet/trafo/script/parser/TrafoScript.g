grammar TrafoScript;

options {
  output = AST;
  ASTLabelType = CommonTree;
}

@header {
package org.dbdoclet.trafo.script.parser;

import java.util.LinkedList;

}

@lexer::header {
package org.dbdoclet.trafo.script.parser;
}

@members {
  
  private List<String> errors = new LinkedList<String>();

  public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
 
    String hdr = getErrorHeader(e);
    String msg = getErrorMessage(e, tokenNames);
    errors.add(hdr + " " + msg);
  }

  public List<String> getErrors() {
    return errors;
  }
}

parse	: transformation (section)* EOF;

transformation
	: TRAN^ ID EOS!;
	
section : SECTION! ID^ BO! (param)* BC!;

param	: ID^ EQ! expr EOS!;

expr 	: TEXT 
	| MULTILINE
    	| NUMBER
    	| BOOL
    	| '['! ']'!
    	| '['! expr (','! expr)* ']'!;
	
BO	:	'{';
BC	:	'}';
BOOL	:	'true'|'false';
EQ	:	'=';
EOS	:	';';
NUMBER	:	('0'..'9')+;
SECTION	:	'section';
TRAN 	:	'transformation';
ID	:	('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'0'..'9'|'-'|'_')+;
TEXT	:	'"' ~('"'|'\r'|'\n')* '"'{setText(getText().substring(1, getText().length()-1));};
MULTILINE
	:	'"""' (options {greedy=false;}:.*) '"""' {setText(getText().substring(3, getText().length()-3));};
WS	:	(' '
	| '\t'
	| '\r'
	| '\n'
	)+ { skip(); };	
	
COMMENT         : '/*' ( options { greedy=false; } : .*) '*/' { $channel = 2; };
LINE_COMMENT    : '//' .* '\n' { $channel = 2; };	
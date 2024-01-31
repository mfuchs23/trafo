grammar TrafoScript;

@parser::header {

import java.util.LinkedList;

}

@lexer::header {
}

parse	: transformation (section | node | attribute)* EOF;

transformation
	: TRAN ID EOS;
	
section : SECTION (ID|REGEXP) BO (param)* BC;

node        : NODE TEXT BO (param)* BC;
attribute   : ATTRIBUTE TEXT BO (param)* BC;

param	: ID EQ expr EOS;

expr 	: (BOOL 
    	| NUMBER
    	| TEXT
    	| MULTILINE)
    	| SBO SBC
    	| SBO expr (COMMA expr)* SBC;
	
BO	:	'{';
BC	:	'}';
SBO	:	'[';
SBC	:	']';
BOOL	:	'true'|'false';
EQ	:	'=';
EOS	:	';';
COMMA   :       ',';
NUMBER	:	('0'..'9')+;
NODE    :       'node';
ATTRIBUTE:      'attribute';
SECTION	:	'section';
TRAN 	:	'transformation';
ID	:	('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'0'..'9'|'-'|'.'|'_')+;
REGEXP  :	'/' ~('*') ('\\/'|~('/'))* '/';
TEXT	:	'"' ~('"'|'\r'|'\n')* '"'{setText(getText().substring(1, getText().length()-1));};
MULTILINE
	:	'"""' .*? '"""' {setText(getText().substring(3, getText().length()-3));};
WS	:	(' '
	| '\t'
	| '\r'
	| '\n'
	)+ { skip(); };	
	
COMMENT         : '/*' .*? '*/' -> channel(2);
LINE_COMMENT    : '//' ~[\r\n]* '\r'? '\n' -> channel(2);	
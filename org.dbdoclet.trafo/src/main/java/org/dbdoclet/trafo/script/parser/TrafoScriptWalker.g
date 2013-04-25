tree grammar TrafoScriptWalker;

options {
  tokenVocab = TrafoScript;
  ASTLabelType = CommonTree;
}

@header {
package org.dbdoclet.trafo.script.parser;
import org.dbdoclet.trafo.script.Script;
}

@members {
  
  Script script;
  String namespace;
}

parse[Script script, String namespace]
@init {
   this.script = script;
   this.namespace = namespace;
}
: transformation section*;

transformation	:	^(TRAN ID { script.setSystemParameter(namespace, Script.SYSPARAM_TRANSFORMATION_NAME, $ID.text); });
section	:	^(ID { script.selectSection(namespace, $ID.text); } expr*);
expr	:	^(ID (bparam[$ID.text] | nparam[$ID.text] | tparam[$ID.text] | mtparam[$ID.text])+);
bparam[String ID]:	BOOL { script.addBoolParam($ID, $BOOL.text); };
nparam[String ID]:	NUMBER { script.addNumberParam($ID, $NUMBER.text); };
tparam[String ID]:	TEXT { script.addTextParam($ID, $TEXT.text); };
mtparam[String ID]:     MULTILINE { script.addTextParam($ID, $MULTILINE.text); };
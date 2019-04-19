%{
	#include<stdio.h>
	#include"stmt.tab.h"
%}

%%
go|dance|sing|fall|run|play|am|is|are|was|can|could|will	{printf("verb --> %s\n",yytext);return verb;}
I|we|he|she|it|they	{printf("pronoun-->%s\n",yytext);return pronoun;}
good|nice|fast	{printf("Adjective-->%s\n",yytext);return adjective;}
in|on|at	{printf("Preposition-->%s\n",yytext);return preposition;}
and|or|also|so|but|if|then {printf("Conjunction-->%s\n",yytext);return conjunction;}
[a-zA-Z]+	{printf("noun-->%s\n",yytext);return noun;}
%%

int yywrap(){
	return 1;
}

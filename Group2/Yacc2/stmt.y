%{
	#include<stdio.h>
	int yylex();
	void yyerror(char* s);
%}

%token	noun verb pronoun adjective preposition conjunction

%%
sentence: simple	{printf("simple statment\n");return 0;}
	| compound	{printf("compound statement\n");return 0;}
simple: subject verb object;
compound: subject verb object conjunction subject verb object;

subject: noun | pronoun;
object: noun|adjective|preposition noun;
%%

void yyerror(char *s){
	printf("error:%s",s);
}

main(){
	yyparse();
	return 0;
}

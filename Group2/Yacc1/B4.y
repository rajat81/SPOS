%{
	#include<stdio.h>
	void yyerror(char* s);
	
	int yylex();
%}

%token	stringdecl string  num varname operator sc eq intdecl

%%
start: intdecl varname sc{printf("valid\n");return 0;} 
	| stringdecl varname sc {printf("valid\n");return 0;} 
	| intdecl varname eq num sc{printf("valid\n");return 0;} 
	| stringdecl varname eq string sc{printf("valid\n");return 0;};

%%

void yyerror(char *s)
{
	printf("Error: %s \n", s);
}
int yywrap()
{
	return(1);
}
int main()
{
	// extern FILE *yyin;
	// yyin = fopen("sample2.java", "r");

	yyparse();
	return 0;
}

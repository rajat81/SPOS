%{
	// Java tokens
	int word_count = 0, char_count = 0,line_count = 0;
%}

importKeyword	import
accessSpecifier	public|private|protected
classKeyword	class|static|void|main
dataTypes	int|String|float
digit	[0-9]
integer	{digit}*
floatingPoint	{digit}*[.]{integer}
identifier	[_a-zA-Z]+[_a-zA-Z0-9]*
unaryOperator	--|\+\+
binaryOperator	-|\+|\/|\*|<<|>>
assignmentOperator	=
comparisonOperator	<|>|==|<=|>=|!=
semicolon	;

%%
{importKeyword}	{printf("import statement:%s\n", yytext);}
{accessSpecifier}	{printf("access specifier:%s\n",yytext);}
{classKeyword}	{printf("class keyword:%s\n",yytext);}
{dataTypes}	{printf("data types:%s\n",yytext);}
{digit}	{printf("digit:%s\n",yytext);}
{integer}	{printf("integer:%s\n",yytext);}
{floatingPoint}	{printf("Floating point:%s\n",yytext);}
{identifier}	{printf("identifier:%s\n",yytext);}
{unaryOperator}	{printf("unary operator:%s\n",yytext);}
{assignmentOperator}	{printf("assignment operator:%s\n",yytext);}
{binaryOperator}	{printf("binary operator:%s\n",yytext);}
{comparisonOperator}	{printf("comparison Operator:%s\n",yytext);}
{semicolon}	{printf("semicolon:%s\n",yytext);}
%%

int yywrap(){
	return 1;
}

int main(){
	extern FILE* yyin;
	yyin = fopen("input","r");
	yylex();
	return 0;
}

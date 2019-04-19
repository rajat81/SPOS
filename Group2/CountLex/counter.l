%{
	// Java tokens
	int word_count = 0, char_count = 0,line_count = 0;
%}

word	[a-zA-Z0-9]+
eol	\n

%%
{word}	{word_count++; char_count += yyleng;}
{eol}	{line_count++;}
.	{char_count++;}
%%

int yywrap(){
	return 1;
}

int main(){
	extern FILE* yyin ;
	yyin = fopen("input","r");
	yylex();
	printf("Word count:%d\nchar count:%d\nline count:%d\n",word_count,char_count,line_count);
	
	return 0;
}

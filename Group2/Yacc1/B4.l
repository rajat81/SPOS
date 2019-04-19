%{
//int|char|String|float|long	{return datatype;}
	#include<stdio.h>
	#include"B4.tab.h"
%}

%%
int	{return intdecl;}
\".*\"	{return string;}
String	{return stringdecl;}
[0-9]+[.][0-9]*	{return num;}
[a-zA-Z0-9]+	{return varname;}
-|\+|\/|\*	{return operator;}
=	{return eq;}
;	{return sc;}
%%

package Analizadores;


import java_cup.runtime.*;

%%
%class AnalizadorLexico
%public
%line
%column
%cup
%cupdebug


/*Analizador LEXICO*/

/*Palabras reservadas*/

ESPACIOS = [ \r\t\b\n\f]
COMILLA = "\""
COMA = ","
PUNTO = "."
DOSPUNTO = ":"
PUNTOCOMA = ";"
LITERALES = [a-zA-Z]
NUMEROS = [0-9]

/*cadenas*/
ENTEROS = {NUMEROS}+
DECIMAL = ({ENTEROS}+{PUNTO}{ENTEROS}+)
CADENA = {LITERALES}+({LITERALES}|{ENTEROS})*
CADENAS = {COMILLA}({ESPACIOS}|{CADENA}|{DECIMAL}|{ENTEROS})*{COMILLA}
/*OPeradores aritmeticos*/
SUMA = "+"
RESTA = "-"
MULTIPLICACION = "*"
DIVISION = "/"
PARENTESISABIERTO = "("
PARENTESISCERRADO = ")"


LLAVESABIERTO = "{"
LLAVESCERRADO = "}"
CORCHETESABIERTO = "["
CORCHETESCERRADO = "]"
/*comentarios*/
COMENTARIOS = "#".*


/*Definicion de graficas*/
DEFINICIONES = ("Def"|"def")
BARRA = "Barras"
PIE = "Pie"
TITULO = "titulo"
EJEX = "ejex"
EJEY = "ejey"
ETIQUETAS = "etiquetas"
VALORES = "valores"
UNIR = "unir"
TIPO = "tipo"
TOTAL = "total"
EXTRA = "extra"
CANTIDAD = "Cantidad"
PORCENTAJE = "Porcentaje"
/*Ejecucion*/

EJECUTAR = "Ejecutar"

%{
    private ReporteErrores tabla = new ReporteErrores();

    public void setTabla(ReporteErrores tabla){
        this.tabla = tabla;
    }

%}
%%
  {COMA}			{return new Symbol(sym.COMA,yyline+1,yycolumn+1, yytext());}
  {PUNTO}			{return new Symbol(sym.PUNTO, yyline+1,yycolumn+1, yytext());}
  {PUNTOCOMA}		{return new Symbol(sym.PUNTOCOMA,yyline+1,yycolumn+1, yytext());}
  {DOSPUNTO}		{return new Symbol(sym.DOSPUNTO, yyline+1,yycolumn+1, yytext());}

  /*valores artimeticos*/
  {SUMA} 					{return new Symbol(sym.SUMA, yyline+1, yycolumn+1, yytext());}
  {RESTA} 				{return new Symbol(sym.RESTA, yyline+1, yycolumn+1, yytext());}
  {MULTIPLICACION}		{return new Symbol(sym.MULTIPLICACION, yyline+1, yycolumn+1, yytext());}
  {DIVISION} 				{return new Symbol(sym.DIVISION, yyline+1, yycolumn+1, yytext());}
  {PARENTESISABIERTO} 	{return new Symbol(sym.PARENTESISABIERTO, yyline+1, yycolumn+1, yytext());}
  {PARENTESISCERRADO} 	{return new Symbol(sym.PARENTESISCERRADO, yyline+1, yycolumn+1, yytext());}
  {LLAVESABIERTO} 		{return new Symbol(sym.LLAVESABIERTO, yyline+1, yycolumn+1, yytext());}
  {LLAVESCERRADO} 		{return new Symbol(sym.LLAVESCERRADO, yyline+1, yycolumn+1, yytext());}
  {CORCHETESABIERTO} 		{return new Symbol(sym.CORCHETESABIERTO, yyline+1, yycolumn+1, yytext());}
  {CORCHETESCERRADO} 		{return new Symbol(sym.CORCHETESCERRADO, yyline+1, yycolumn+1, yytext());}

  {ENTEROS}		{return new Symbol(sym.ENTEROS, yyline+1,yycolumn+1, new Double(yytext()));}
  {DECIMAL}		{return new Symbol(sym.DECIMAL, yyline+1,yycolumn+1, new Double(yytext()));}
  {CADENAS}		{return new Symbol(sym.CADENAS ,yyline+1,yycolumn+1,yytext());}


  {DEFINICIONES}  {return new Symbol(sym.DEFINICIONES, yyline+1, yycolumn+1,yytext());}
  {BARRA}  		{return new Symbol(sym.BARRA, yyline+1, yycolumn+1,yytext());}
  {PIE}    		{return new Symbol(sym.PIE, yyline+1, yycolumn+1,yytext());}
  {TITULO}		{return new Symbol(sym.TITULO, yyline+1, yycolumn+1,yytext());}
  {EJEX}          {return new Symbol(sym.EJEX, yyline+1, yycolumn+1,yytext());}
  {EJEY}          {return new Symbol(sym.EJEY, yyline+1, yycolumn+1,yytext());}
  {ETIQUETAS} 	{return new Symbol(sym.ETIQUETAS, yyline+1, yycolumn+1,yytext());}
  {VALORES}		{return new Symbol(sym.VALORES, yyline+1, yycolumn+1,yytext());}
  {UNIR}			{return new Symbol(sym.UNIR, yyline+1, yycolumn+1,yytext());}
  {TIPO}			{return new Symbol(sym.TIPO, yyline+1, yycolumn+1,yytext());}
  {TOTAL}			{return new Symbol(sym.TOTAL, yyline+1, yycolumn+1,yytext());}
  {EXTRA} 		{return new Symbol(sym.EXTRA, yyline+1, yycolumn+1,yytext());}
  {CANTIDAD}    {return new Symbol(sym.CANTIDAD, yyline+1, yycolumn+1,yytext());}
  {PORCENTAJE}  {return new Symbol(sym.PORCENTAJE, yyline+1, yycolumn+1,yytext());}


  {EJECUTAR}		{return new Symbol(sym.EJECUTAR, yyline+1, yycolumn+1,yytext());}

  {ESPACIOS}		{/*NADA*/}
  {COMENTARIOS} 	{/*NADA*/}



    0.(0)+.{DECIMAL}  {tabla.agregarError(yytext(), yyline, yycolumn, "Lexico");
                        return new Symbol(sym.ERROR, yyline+1, yycolumn+1, yytext());}
    0.(0)+.{ENTEROS} {tabla.agregarError(yytext(), yyline, yycolumn, "Lexico");
                       return new Symbol(sym.ERROR, yyline+1, yycolumn+1, yytext());}
   /*SI NO VIENE NADA, ENTONCES GENERA UN ERROR*/
    [^]     {tabla.agregarError(yytext(), yyline+1, yycolumn+1, "ERROR LEXICO");
            return new Symbol(sym.ERROR, yyline+1, yycolumn+1, yytext());}

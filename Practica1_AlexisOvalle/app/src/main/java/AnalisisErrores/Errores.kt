package AnalisisErrores

import java.io.Serializable


/*
* Clase de errores (onjeto) para
* trabajar independientemente
* */
class Errores: Serializable {

    private var lexema: String = ""
    private var fila: Int= 0
    private var columna: Int = 0
    private var tipo: String = ""



    /*COnstructor de la clase error vacio*/

    constructor(){

    }

    /*COnstructor de la clase error*/

    constructor(lexema:String, fila:Int,columna:Int, tipo:String){
        this.lexema = lexema
        this.fila = fila
        this.columna = columna
        this.tipo = tipo
    }

    /*Metodos Getters y Setters*/

    fun getLexema(): String {
        return lexema;
    }


    fun getFila(): Int {
        return fila;
    }

    fun getColumna(): Int {
        return columna;
    }

    fun getTipo(): String {
        return tipo;
    }

    fun setLexema(lexema: String) {
        this.lexema = lexema
    }
    fun setFila(fila: Int) {
        this.fila = fila
    }
    fun setColumna(columna: Int) {
        this.columna = columna
    }
    fun setTipo(tipo: String){
        this.tipo = tipo
    }



}
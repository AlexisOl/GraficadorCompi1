package GUIController

import java.io.Serializable

open class TipoGrafica:Serializable {

    private var unir:ArrayList<Int> = ArrayList<Int>()
    private var titulo:String = ""


    constructor(titulo: String, unir: ArrayList<Int>) {
        this.titulo = titulo
        this.unir = unir
    }
    constructor(){}




    /*
    *
    * GETTERS Y SETTERS
    * */
    fun getTitulo(): String? {
        return titulo
    }


    fun getUnir(): ArrayList<Int>? {
        return unir
    }


    fun setUnion(union: Int) {
        unir.add(union)
    }


    fun setTitulo(nuevotitulo: String) {
        titulo = nuevotitulo.substring(1, nuevotitulo.length - 1)
    }
}
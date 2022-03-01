package GUIController

import java.io.Serializable

class Barras_Controller : TipoGrafica, Serializable {

    private var ejeX = ArrayList<String>()
    private  var ejeY = ArrayList<Double>()
    constructor(){}
    constructor(titulo: String,unir: ArrayList<Int>, ejeX: ArrayList<String>, ejeY: ArrayList<Double>):super(titulo, unir) {
        this.ejeX = ejeX
        this.ejeY = ejeY
    }

/*
*
* GETTERS Y SETTERS
* */

    fun getEjeX(): ArrayList<String>? {
        return ejeX
    }


    fun getEjeY(): ArrayList<Double>? {
        return ejeY
    }


    fun agregarEjeX(eje: String) {
        ejeX.add(eje)
    }


    fun agregarEjeY(eje: Double) {
        ejeY.add(eje)
    }
}
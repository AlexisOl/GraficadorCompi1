package GUIController

import java.io.DataOutput
import java.io.Serializable

class Pie_Controller:Serializable, TipoGrafica {

    private var etiquetas: ArrayList<String> = ArrayList<String>()
    private var valores:ArrayList<Double> = ArrayList<Double>()
    private var tipo:String = ""
    private var total:Double = 0.0
    private var extra:String = ""


    // Constructor para las graficas de Pie
    constructor(titulo:String, unir:ArrayList<Int>,  tipo: String, etiquetas: ArrayList<String>, valores: ArrayList<Double>, total: Double, extra: String):super(titulo, unir) {
        this.tipo = tipo;
        this.etiquetas = etiquetas;
        this.valores = valores;
        this.total = total;
        this.extra = extra;
    }
    constructor(){}


    /*
   * Getters y Setters de la clase
   * */
    fun getEtiquetas(): ArrayList<String>? {
        return etiquetas
    }

    fun getValores(): ArrayList<Double>? {
        return valores
    }

    fun getTipo(): String? {
        return tipo
    }

    fun getTotal(): Double {
        return total
    }

    fun getExtra(): String? {
        return extra
    }

    fun setEtiqueta(etiqueta: String) {
        etiquetas.add(etiqueta)
    }

    fun setValor(valor: Double) {
        valores.add(valor)
    }

    fun setTotal(nuevoTotal: Double) {
        total = nuevoTotal
    }

    fun setTipo(nuevo_tipo: String) {
        tipo = nuevo_tipo
    }

    fun setExtra(nuevo_extra: String) {
        extra = nuevo_extra
    }
}
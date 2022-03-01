package Reportes

import AnalisisErrores.Errores
import java.io.Serializable

class ReporteErrores : Serializable {

    /*Clase para los reportes de los errores*/

    private var erroresRecopilacion = arrayListOf<Errores>()
    private var cantidad = 0;
    constructor(erroresRecopilacion: ArrayList<Errores>, cantidad: Int) {
        this.erroresRecopilacion = erroresRecopilacion
        this.cantidad = cantidad
    }
    constructor(){

    }

    /*Getters y Setter*/
    fun getListaDeErrores(): ArrayList<Errores>{
        return erroresRecopilacion
    }
    fun agregarError(lexema:String, linea:Int,columna:Int, tipo:String){
        var nuevoError: Errores = Errores(lexema, linea, columna, tipo)
        erroresRecopilacion.add(nuevoError)
        cantidad++
    }
    fun getCantidadErrores():Int{
        return cantidad
    }
    fun reiniciarTabla() {
        erroresRecopilacion.clear()
        cantidad = 0;


    }
}
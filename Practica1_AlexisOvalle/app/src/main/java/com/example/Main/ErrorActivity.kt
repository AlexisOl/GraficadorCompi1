package com.example.Main

import AnalisisErrores.Errores
import Reportes.ReporteErrores
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TableLayout
import android.widget.TextView

class ErrorActivity : AppCompatActivity() {
    var tablaLayout:TableLayout?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)

        tablaLayout = findViewById(R.id.tablaErrores)
        var objetoEnviado = intent.extras
        var tabla = ReporteErrores()
        if (objetoEnviado != null){
            tabla = objetoEnviado.getSerializable("Tabla") as ReporteErrores
        }
        var listaDeErrores = tabla.getListaDeErrores()
        agregarDatos(listaDeErrores)
    }

    fun agregarDatos(lista: ArrayList<Errores>){
        for (i in -1 until  lista.size){
            val registro = LayoutInflater.from(this).inflate(R.layout.item_tabla_layout, null,false)
            val itemLexema = registro.findViewById<View>(R.id.itemLexema) as TextView
            val itemFila = registro.findViewById<View>(R.id.itemLinea) as TextView
            val itemColumna = registro.findViewById<View>(R.id.itemColumna) as TextView
            val itemTipo = registro.findViewById<View>(R.id.itemTipo) as TextView

            if (i == -1){
                itemLexema.setText("Lexema")
                itemLexema.setBackgroundColor(Color.RED)
                itemLexema.setTextColor(Color.WHITE);

                itemFila.setText("Fila")
                itemFila.setBackgroundColor(Color.RED)
                itemFila.setTextColor(Color.WHITE);

                itemColumna.setText("Columna")
                itemColumna.setBackgroundColor(Color.RED)
                itemColumna.setTextColor(Color.WHITE);

                itemTipo.setText("Tipo")
                itemTipo.setBackgroundColor(Color.RED)
                itemTipo.setTextColor(Color.WHITE);

            } else {

                itemLexema.setText(lista[i].getLexema())
                itemLexema.setBackgroundColor(Color.red(100))
                itemLexema.setTextColor(Color.BLACK);


                itemFila.setText(lista[i].getFila().toString())
                itemFila.setBackgroundColor(Color.red(100))
                itemFila.setTextColor(Color.BLACK);

                itemColumna.setText(lista[i].getColumna().toString())
                itemColumna.setBackgroundColor(Color.red(100))
                itemColumna.setTextColor(Color.BLACK);

                itemTipo.setText(lista[i].getTipo())
                itemTipo.setBackgroundColor(Color.red(100))
                itemTipo.setTextColor(Color.BLACK);


            }
            tablaLayout?.addView(registro)
        }
    }

}
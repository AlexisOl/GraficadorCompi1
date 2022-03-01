package com.example.Main

import Analizadores.AnalizadorLexico
import Analizadores.parser
import GUI.GeneracionGraficas
import Reportes.ReporteErrores
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import java.io.StringReader

// variables globales
var tabla: ReporteErrores = ReporteErrores()
var graficas: GeneracionGraficas = GeneracionGraficas()
/*****************/
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var nuevoTexto = intent.getStringExtra("textoArea")
        var areaTexto: EditText = findViewById(R.id.editText)
        areaTexto.setText(nuevoTexto);
    }



    fun Analizar(view: View) {
        tabla.reiniciarTabla()
        graficas.reiniciarLista()
        graficas.reiniciarEjecuciones()
        var areaTexto: EditText = findViewById(R.id.editText)
        var texto = areaTexto.text.toString();
        println(texto)
        val str = StringReader(texto)
        val analizador = AnalizadorLexico(str)
        var par: parser =
            parser(analizador)
        graficas.setErrores(tabla)
        par.setTabla(tabla)
        par.setGraficas(graficas);
        analizador.setTabla(tabla)
        try {
            par.parse()
        } catch (e: Exception) {
            println(e.printStackTrace())
        }
    desicionEjecucion(tabla.getCantidadErrores(), texto)
    }


    fun desicionEjecucion(valor: Int, areaTexto: String) {
        if (valor == 0) {
            val miIntent = Intent(this@MainActivity, VistaGeneral::class.java)
            var miBundle = Bundle()
            miBundle.putSerializable("tablaDeGraficas", graficas)
            miBundle.putString("textoArea", areaTexto)
            miIntent.putExtras(miBundle)
            startActivity(miIntent)
        } else {
            var lista = tabla.getListaDeErrores()
            val miIntent = Intent(this@MainActivity, ErrorActivity::class.java)
            val miBundle = Bundle()
            miBundle.putSerializable("Tabla", tabla)
            miBundle.putString("textoArea", areaTexto)
            miIntent.putExtras(miBundle)
            startActivity(miIntent)
        }

    }
}
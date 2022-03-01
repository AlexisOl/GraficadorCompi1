package com.example.Main

import GUI.GeneracionGraficas
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class VistaGeneral : AppCompatActivity() {
// variable Global
    var tablaDeGraficas = GeneracionGraficas()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_general)
        var objetoEnviado = intent.extras

        if (objetoEnviado!=null){
            tablaDeGraficas = objetoEnviado.getSerializable("tablaDeGraficas") as GeneracionGraficas
        }
    }

// evento del boton para ver la graficas
    fun eventoVerGraficas(view: View) {
        val miIntent = Intent(this@VistaGeneral, Ejecuciones_Graficas::class.java)
        var miBundle = Bundle()
        miBundle.putSerializable("tablaDeGraficas", graficas)
        miIntent.putExtras(miBundle)
        startActivity(miIntent)
    }



}
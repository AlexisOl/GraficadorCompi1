package com.example.Main

import GUI.GeneracionGraficas
import GUIController.Barras_Controller
import GUIController.Pie_Controller
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate


class Ejecuciones_Graficas : AppCompatActivity() {
public var POCRCENTAJE: String = "Porcentaje"
    public var CANTIDAD: String = "Cantidad"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // se ingresa el valor de la actividad
        setContentView(R.layout.activity_ejecuciones_graficas)

    // intento para determinar si las graficas no son nulas
        var recepcionGraficas = intent.extras
        var tablaDeGraficas = GeneracionGraficas()
        if (recepcionGraficas!=null){
            tablaDeGraficas = recepcionGraficas.getSerializable("tablaDeGraficas") as GeneracionGraficas
        }
        // si no es nulo genera el analisis
        var cantidadGraficasTotales = tablaDeGraficas.listaDeEjecuciones



        //valores esquematicos de las graficas
        var layoutL = findViewById(R.id.linealLayout) as LinearLayout
        var parametrosLayout = LinearLayout.LayoutParams(800,800)
        parametrosLayout.bottomMargin = 200
        parametrosLayout.leftMargin = 100
        parametrosLayout.rightMargin = 100
        parametrosLayout.gravity = 1

        // INgresp de lso colores
        var colors = ArrayList<Int>()
        for (color in ColorTemplate.JOYFUL_COLORS) {
            colors.add(color)
        }
        for (color in ColorTemplate.LIBERTY_COLORS) {
            colors.add(color)
        }

        /***-***************/

        // comienzo de las graficas de barras
        for (i  in  cantidadGraficasTotales.indices) {
            // para las graficas de barras
            if (cantidadGraficasTotales[i] is Barras_Controller) {
                var graficaBarra = cantidadGraficasTotales[i] as Barras_Controller
                var grafica:BarChart = BarChart(this)
                var ejexBarras = graficaBarra.getEjeX()
                var ejeyBarras = graficaBarra.getEjeY()
                var UnionesBarras = graficaBarra.getUnir()
                grafica.setLayoutParams(parametrosLayout)


                // ingresa el nombre de la grafica
                grafica.description.setText(graficaBarra.getTitulo())
                grafica.description.setTextSize(8f)
                // generacion de valores de entrada y nombres
                var entradas = ArrayList<BarEntry>()
                var nombres = ArrayList<String>()
                var PosicionBarras = 0
                var cantidadGraficasParciales = 0
                while (PosicionBarras < UnionesBarras!!.size) {
                    try {
                        nombres.add(ejexBarras!![UnionesBarras[PosicionBarras]])

                        // aqui ingresa la grafica en dos posiciones, X
                        // que seria simplemente correlativo y Y, el cual es el valor de la siguiente posicion de la union
                        var entrada = BarEntry(cantidadGraficasParciales.toFloat(), ejeyBarras!![UnionesBarras[PosicionBarras + 1]].toFloat())
                        entradas.add(entrada)
                        // incrementa las graficas existentes
                        cantidadGraficasParciales++
                    } catch (e:Exception){
                        Toast.makeText(this,"ERROR", Toast.LENGTH_SHORT ).show()
                    }
                    PosicionBarras+=2
                }

                // ingreso de sintaxis del tipo de grafica en este caso el eje X
                var ejeXGraficaBarra = grafica.xAxis
                ejeXGraficaBarra.setGranularityEnabled(true)
                ejeXGraficaBarra.setLabelRotationAngle(40f)
                ejeXGraficaBarra.setPosition(XAxis.XAxisPosition.BOTTOM)
                // ingreso general
                var index = IndexAxisValueFormatter(nombres)
                ejeXGraficaBarra.setValueFormatter(index)
                var dataBarras = BarDataSet(entradas, graficaBarra.getTitulo())
                var data = BarData(dataBarras)
                data.setBarWidth(0.5f)
                dataBarras.setColors(colors)
                grafica.setData(data)
                grafica.setFitBars(true)
                layoutL.addView(grafica)
            }


            // para las graficas de Pie
            else if (cantidadGraficasTotales[i] is Pie_Controller) {
                var graficaPie = cantidadGraficasTotales[i] as Pie_Controller
                var grafica:PieChart = PieChart(this)
                grafica.setLayoutParams(parametrosLayout)
                var listaEtiquetas = graficaPie.getEtiquetas()
                var listaValores = graficaPie.getValores()
                var listaUniones = graficaPie.getUnir()

                var dataSet = PieDataSet(null, null);
                var dataValores = ArrayList<PieEntry>()
                var contadorPie = 0
                while (contadorPie < listaUniones!!.size) {
                    try {
                        if (contadorPie == listaUniones!!.size) {

                        } else {
                            var nuevoPie = PieEntry(
                                listaValores!![listaUniones[contadorPie + 1]].toFloat(),
                                listaEtiquetas!![listaUniones[contadorPie]]
                            )
                            dataValores.add(nuevoPie);

                        }
                    } catch (e: Exception){
                        Toast.makeText(this,"ERROR ",Toast.LENGTH_SHORT).show()

                    }
                    contadorPie+=2
                }



                fun determinarTipoPie(tipo: String) {
                    if (tipo == POCRCENTAJE){
                        grafica.setUsePercentValues(true)
                        var residuo = 360 - aritmeticaGrafica(listaValores!!)
                        var nuevoPie = PieEntry(residuo.toFloat(), "Extra")
                        dataValores.add(nuevoPie);
                    } else {
                        var residuo = graficaPie.getTotal() - aritmeticaGrafica(listaValores!!)
                        var extra = ""
                        if (graficaPie.getExtra().equals("")){
                            extra = "Extra"
                        } else {
                            extra = graficaPie.getExtra().toString()
                        }
                        if (residuo > 0) {
                            var nuevoPie = PieEntry(residuo.toFloat(), extra)
                            dataValores.add(nuevoPie);
                        }
                    }
                }


                // ingreso de sintaxis del tipo de grafica
                determinarTipoPie(graficaPie.getTipo()!!);
                dataSet.setValues(dataValores)
                dataSet.setLabel(graficaPie.getTitulo())
                dataSet.setColors(colors)
                dataSet.setValueTextSize(10f)
                dataSet.setValueTextColor(Color.BLACK)
                var dataPie = PieData(dataSet)
                grafica.setData(dataPie)
                grafica.description.setEnabled(false)
                grafica.setEntryLabelTextSize(8f)
                grafica.setEntryLabelColor(Color.BLACK)
                grafica.setCenterText(graficaPie.getTitulo())
                dataSet.setFormLineWidth(4F)
                layoutL.addView(grafica)
            }

        }


    }

    fun aritmeticaGrafica(lista: ArrayList<Double>): Double {
        var cantidad:Double = 0.0
        lista.forEach {
            cantidad += it
        }
        return cantidad
    }


}
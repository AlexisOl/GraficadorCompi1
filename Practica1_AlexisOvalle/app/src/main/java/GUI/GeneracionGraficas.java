package GUI;

import java.io.Serializable;
import java.util.ArrayList;

import GUIController.Barras_Controller;
import GUIController.Pie_Controller;
import GUIController.TipoGrafica;
import Reportes.ReporteErrores;


public class GeneracionGraficas implements Serializable {
    // generacion de valores de grafiacs y errores
    private ReporteErrores errores = new ReporteErrores();
    private Barras_Controller barra = new Barras_Controller();
    private Pie_Controller pie = new Pie_Controller();
    // generacion del conjunto de gracias
    private ArrayList<TipoGrafica> listaGraficasDefinidas = new ArrayList<>();

    // valores generales de numeros,enteros y cadenas de texto

    private ArrayList<String> cadenas = new ArrayList<>();
    private ArrayList<Double> numeros = new ArrayList<>();
    private ArrayList<Integer> enteros = new ArrayList<>();
   // cantidad de ejecuciones y la cantidad parcial de ejecucion
    private int ejecuciones = 0;
    private ArrayList<TipoGrafica> listaDeEjecuciones = new ArrayList<>();

    // valores de tipo de error
    public static final String ERRORLEXICO= "ERROR LEXICO";
    public static final String ERRORSINTACTICO= "ERROR SINTACTICO";


    public GeneracionGraficas() {

    }

/*
*
* GETTERS Y SETTERS DE LAS vARIABLES
*
* */
    public ArrayList<TipoGrafica> getListaDeEjecuciones() {

        return listaDeEjecuciones;
    }


    public void setErrores(ReporteErrores errores) {
        this.errores = errores;
    }


    public ArrayList<TipoGrafica> getListaGraficasDefinidas() {

        return listaGraficasDefinidas;
    }


    public void setListaGraficasDefinidas(ArrayList<TipoGrafica> listaGraficasDefinidas) {
        this.listaGraficasDefinidas = listaGraficasDefinidas;
    }
    public void agregarCadenas(String cadenaNueva) {
        cadenas.add(0, cadenaNueva.substring(1, cadenaNueva.length() - 1));
    }


    public void agregarNumeros(double nuevoNumero) {
        numeros.add(0, nuevoNumero);
    }


    public void reiniciarLista() {
        listaGraficasDefinidas.clear();
    }




    public void agregarEntero(double cantidad, double cantidad2) {
        int valor1 = (int) cantidad;
        int valor2 = (int) cantidad2;

        enteros.add(0, valor1);
        enteros.add(1, valor2);
    }


    /***********************************************/


    public void barrasErrores(int fila, int columna) {
        if (barra.getTitulo().equals("")||barra.getUnir().size() == 0||barra.getEjeX().size() == 0||barra.getEjeY().size() == 0){
            errores.agregarError("DEFINICION", fila, columna, ERRORSINTACTICO);
        }
        listaGraficasDefinidas.add(barra);
        barra = null;
        Barras_Controller nueva = new Barras_Controller();
        barra = nueva;
    }

    public void PieErrores(int fila, int columna) {
        if (pie.getTipo().equals("")||pie.getTitulo().equals("")||pie.getUnir().size() == 0||pie.getEtiquetas().size() == 0||pie.getValores().size() == 0||
                pie.getExtra().equals("")){
            errores.agregarError("DEFINICION", fila, columna, ERRORSINTACTICO);
        } else {
            if (pie.getTipo().equals("Porcentaje")){
                if (pie.getTotal() != 0){
                    errores.agregarError("DEFINICION", fila, columna, ERRORSINTACTICO );
                }
            } else if (pie.getTipo().equals("Cantidad")){
                if (pie.getTotal() == 0){
                    errores.agregarError("DEFINICION", fila, columna, ERRORSINTACTICO);
                }
            }
        }
        listaGraficasDefinidas.add(pie);
        pie = null;
        Pie_Controller nuevo_pie = new Pie_Controller();
        pie = nuevo_pie;
    }


    public void agregarGrafica(int valor, int fila, int columna) {
        if (ejecuciones > 0){
            errores.agregarError("DEFINICION", fila, columna, ERRORSINTACTICO);
        } else {
            switch (valor) {
                case 1:
                    barrasErrores(fila, columna);
                    break;
                case 2:
                    PieErrores(fila,columna);
                    break;
            }
        }
    }


    public void ingresodeTextoGrafica(String texto, int valor, int fila, int columna) {
        switch (valor) {
            case 1: barraTitulo(texto, fila, columna);
                break;
            case 2:pieTitulo(texto, fila, columna);
                break;
            case 3:ingresoTipo(texto, fila, columna);
                break;
            case 4:ingresoExtra(texto, fila, columna);
                break;
        }
    }

    public void barraTitulo(String texto, int fila, int columna) {
        if (("").equals(barra.getTitulo())) {
            barra.setTitulo(texto);
        } else {
            errores.agregarError("Titulo", fila, columna, ERRORSINTACTICO);
        }
    }
    public void pieTitulo(String texto, int fila, int columna){
        if (pie.getTitulo().equals("")) {
            pie.setTitulo(texto);
        } else {
            errores.agregarError("Titulo", fila, columna, ERRORSINTACTICO);
        }
    }
    public void ingresoTipo(String texto, int fila, int columna) {
        if (pie.getTipo().equals("")) {
            pie.setTipo(texto);
        } else {
            errores.agregarError("Tipo", fila, columna, ERRORSINTACTICO);
        }
    }
    public void ingresoExtra(String texto, int fila, int columna ) {
        if (pie.getExtra().equals("")) {
            pie.setExtra(texto);
        } else {
            errores.agregarError("Extra", fila, columna, ERRORSINTACTICO);
        }
    }
    /*                        ------------                      */


    public void ingresoCadena(int valor, int linea, int columna) {
        if (valor == 1) {
            if (cadenas.isEmpty()) {
                errores.agregarError("EjeX", linea, columna, ERRORSINTACTICO);
            } else {
                for (int i = 0; i < cadenas.size(); i++) {
                    barra.agregarEjeX(cadenas.get(i));
                }
            }
        } else if (valor == 2) {
            if (cadenas.isEmpty()) {
                errores.agregarError("Etiquetas", linea, columna, ERRORSINTACTICO);
            } else {
                for (int i = 0; i < cadenas.size(); i++) {
                    pie.setEtiqueta(cadenas.get(i));
                }
            }
        }
        cadenas.clear();
    }




    public void asignarNumeros(int valor, int linea, int columna) {
        if (valor == 1) {
            if (numeros.isEmpty()) {
                errores.agregarError("EjeY", linea, columna, ERRORSINTACTICO);
            } else {
                for (int i = 0; i < numeros.size(); i++) {
                    barra.agregarEjeY(numeros.get(i));
                }
            }
        } else if (valor == 2) {
            if (numeros.isEmpty()) {
                errores.agregarError("EjeY", linea, columna, ERRORSINTACTICO);
            } else {
                for (int i = 0; i < numeros.size(); i++) {
                    pie.setValor(numeros.get(i));
                }
            }
        }
        numeros.clear();
    }
    /***********************************************/
    // metodo para la creacion de uniones entre las graficas
    public void crearUniones(int valor, int filas, int columna) {
        generacionUniones(valor, filas, columna);
        enteros.clear();
    }
    public void generacionUniones(int seleccion, int fila, int columna ) {
        if (enteros.isEmpty()) {
            errores.agregarError("ERROR UNIR", fila, columna, ERRORSINTACTICO);
        } else if (seleccion ==1) {
            for (int i = 0; i < enteros.size(); i++) {
                barra.setUnion(enteros.get(i));
            }
        } else if (seleccion==2) {
            for (int i = 0; i < enteros.size(); i++) {
                pie.setUnion(enteros.get(i));
            }
        }
    }
    /***********************************************/

    public void ingresoTotal(double nuevo_total, int linea, int columna) {
        if (pie.getTotal() == 0) {
            pie.setTotal(nuevo_total);
        } else {
            errores.agregarError("Total", linea, columna, ERRORSINTACTICO);
        }

    }


    public void agregarEjecucion(String texto, int linea, int columna) {
        String titulo = texto.substring(1,texto.length()-1);
        boolean prueba = false;
        for (int i = 0; i < listaGraficasDefinidas.size(); i++) {
            TipoGrafica grafica = listaGraficasDefinidas.get(i);
            if (grafica.getTitulo().equals(titulo)){
                listaDeEjecuciones.add(grafica);
                i = listaGraficasDefinidas.size();
                ejecuciones++;
                prueba = true;
            }
        }
        if (!prueba){
            errores.agregarError("Ejecutar", linea, columna, ERRORSINTACTICO);
        }
    }


    public void reiniciarEjecuciones(){
        ejecuciones = 0;
        listaDeEjecuciones.clear();
    }


}

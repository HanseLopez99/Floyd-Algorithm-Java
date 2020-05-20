/*Algoritmos y Estructura de Datos
* Hoja de trabajo 10
*Seccion 30
*Autor: Hansel Lopez
*Clase Controller.java
*Fecha: 19/05/2020*/

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Controller {

    //Metodo que inicializa el programa
    public void runProgram() throws IOException{
        //Se declara variables necesarias para abrir el archivo de texto
        ArrayList<GraphModel> graphArrayList = new ArrayList<>();
        File fileTXT = new File ("guategrafo.txt");
        FileReader reader = new FileReader(fileTXT);
        BufferedReader bfReader = new BufferedReader(reader);
        Scanner scanner = new Scanner(reader);
        String line = "";

        //Se declara variables del modelo y se crea una instancia
        GraphModel graph = new GraphModel(); 
        String origin;
        String destination;
        int distancia;
        int cont = 0;

        //Se lee el archivo de texto y se guarda en el arraylist graphArrayList
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            
            //Capturando origen, destino y distancia que contiene el archivo de texto
            origin = line.substring(0, line.indexOf(" "));
            line = line.substring(line.indexOf(" ") + 1, line.length());
            destination = line.substring(0, line.indexOf(" "));
            line = line.substring(line.indexOf(" ") + 1, line.length());  
            distancia = Integer.parseInt(line.substring(0, line.length()));
            cont++;
            
            //Agregando a ArrayList y cerrando BufferedReader
            graphArrayList.add(new GraphModel(origin, destination, distancia)); 
            reader.close();
            bfReader.close();
        }
    }
}
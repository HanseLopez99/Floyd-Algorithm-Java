/*Algoritmos y Estructura de Datos
* Hoja de trabajo 10
*Seccion 30
*Autor: Hansel Lopez
*Clase Graph.java
*Fecha: 19/05/2020*/

import java.util.ArrayList;

public class Graph {
    private String origin;
    private String destination;
    private int distance;

    public Graph(){

    }

    public Graph(String origin, String destination, int distance){
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }

    public void setOrigin(String origin){
        this.origin = origin;
    }

    public String getOrigin(){
        return this.origin;
    }

    public void setDestination(String destination){
        this.destination = destination;
    }

    public String getDestination(){
        return this.destination;
    }

    public void setDistance(int distance){
        this.distance = distance;
    }

    public int getDistance(){
        return this.distance;
    }
   
    public ArrayList<String> generateGraphArray(ArrayList<Graph> graph){
        ArrayList<String> graphArray = new ArrayList<>();
        for(Graph g: graph){
            if(!graphArray.contains(g.getOrigin())){
                graphArray.add(g.getOrigin());
            }
            if(!graphArray.contains(g.getDestination())){
                graphArray.add(g.getDestination());
            }
        }
        return graphArray;
    }
}
/*Algoritmos y Estructura de Datos
* Hoja de trabajo 10
*Seccion 30
*Clase Floyd.java
*Fecha: 19/05/2020
*[Referencia: https://www.youtube.com/watch?v=xK0ShW9G-Ts&t=1531s]*/

import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//Comienza Algoritmo de Floyd
public class Floyd {
    public String FloydAlgorithm(long[][] matrix, ArrayList<String> graph, String origin, String destination){
        int vertex = matrix.length;
        long adjMatrix[][] = matrix;
        
        String routes[][] = new String[vertex][vertex];
        String auxRoutes[][] = new String[vertex][vertex];
        String pathTraveled = "";
        String chain = "", route = "";
        
        int i, j, k;
        float temp1, temp2, temp3, temp4, min;
        int org = graph.indexOf(origin);
        int dest = graph.indexOf(destination);
        
        for(i = 0; i < vertex; i++){
            for(j = 0; j < vertex; j++){
                routes[i][j] = "";
                auxRoutes[i][j] = "";
            }
        }
        
        for (k = 0; k < vertex; k++) {
            for (i = 0; i < vertex; i++) {
                for (j = 0; j < vertex; j++) {
                    temp1 = adjMatrix[i][j];
                    temp2 = adjMatrix[i][k];
                    temp3 = adjMatrix[k][j];
                    temp4 = temp2 + temp3;
                    
                    min = Math.min(temp1, temp4);
                    if(temp1 != temp4){
                        if(min == temp4){
                            pathTraveled = "";
                            auxRoutes[i][j] = k + "";
                            routes[i][j] = rTraveled(i, k, auxRoutes, pathTraveled) + (k + 1);                            
                        }
                    }
                    adjMatrix[i][j] = (long) min;
                }
            }
        }
        
        ArrayList<Integer> arrayRoutes = new ArrayList<>();
        
        //Ruta que llega al destino
        for (i = 0; i < vertex; i++) {
            for (j = 0; j < vertex; j++) {
                if(adjMatrix[i][j] != 1000000000){
                    if(i != j){
                        if(routes[i][j].equals("") && (i == org) && (j == dest)){
                            route += "De (" + graph.get(i) + ") ---> (" + graph.get(j) + ") deberia irse por: (" + graph.get(i) + ", " + graph.get(j) + ") \n";
                        }else if(!routes[i][j].equals("") && (j == dest) && (i == org)){
                            String between = routes[i][j];
                            if(!between.contains(",")){
                                arrayRoutes.add(Integer.parseInt(between));
                            }
                            while(between.contains(",")){
                                String walk = between.substring(0, between.indexOf(","));
                                between = between.substring(between.indexOf(",") + 2);
                                arrayRoutes.add(Integer.parseInt(walk));
                                if(!between.contains(",")){
                                    arrayRoutes.add(Integer.parseInt(between));
                                }
                            }
                            
                            String center = "";
                            for(Integer in: arrayRoutes){
                                
                                center += graph.get(in - 1) + ", ";
                            }
                            route += "De (" + graph.get(i) + ") ---> (" + graph.get(j) + ") deberia irse por: (" + graph.get(i) + ", " + center + graph.get(j) + ") \n";
                        }
                    }
                }
            }
        }
        return "\n" + route;
    }
     
    public String rTraveled(int i, int k, String[][] routeAux, String routeTraveled){
        if(routeAux[i][k].equals("")){
            return "";
        }else{
            routeTraveled += rTraveled(i, Integer.parseInt(routeAux[i][k]), routeAux, routeTraveled) + (Integer.parseInt(routeAux[i][k]) + 1) + ", ";
            return routeTraveled;
        }
    }
    
    public long[][] createMatrix(ArrayList<String> newGraph, ArrayList<GraphModel> model){
        long matriz[][] = new long[newGraph.size()][newGraph.size()];
        
        for (int i = 0; i < newGraph.size(); i++) {
            for (int j = 0; j < newGraph.size(); j++) {
                if(i == j){
                    matriz[i][j] = 0;
                }else{
                    String o = newGraph.get(i);
                    String d = newGraph.get(j);
                    int distance = 999999999;
                    for(GraphModel c: model){
                        if(o.equals(c.getOrigin()) && d.equals(c.getDestination())){
                            distance = c.getDistance();
                        }
                    }
                    matriz[i][j] = distance;
                }
            }
        }
        return matriz;
    }

    public int graphCenter(long[][] matriz){
        ArrayList<Long> maximum = new ArrayList<>();
        ArrayList<Long> summation = new ArrayList<>();
        long max;
        int iterationCounter = 0;
        
        while(iterationCounter != matriz.length){
            max = 0;
            for (int i = 0; i < matriz.length; i++) {
                summation.add(matriz[i][iterationCounter]);
            }
            for(Long l: summation){
                if((l <= 9999999) && (l != 0)){
                    if(l > max){
                        max = l;
                    }
                }
            }
            maximum.add(max);
            summation.clear();
            iterationCounter++;
        }
        
        max = 0;
        iterationCounter = 0;
        for(Long l: maximum){
            if(l > max){
                max = l;
            }
        }

        int pos = maximum.indexOf(max);
        long min = max;       
        for (int i = 0; i < matriz.length; i++) {
            if((matriz[i][pos]<=999999) && (matriz[i][pos]!=0)){
                if(matriz[i][pos] < min){
                    min = matriz[i][pos];
                }
            }
        }
        
        int center = 0;
        for (int i = 0; i < matriz.length; i++) {
            if(matriz[i][pos] == min){
                center = i;
                break;
            }
        }
        
        return center;
    }
    
    public ArrayList<GraphModel> readTXTFile() throws FileNotFoundException, IOException{
        ArrayList<GraphModel> graph = new ArrayList<>();
        File txtFile = new File ("guategrafo.txt");
        FileReader reader = new FileReader(txtFile);
        BufferedReader bfReader = new BufferedReader(reader);
        String row = "";
        Scanner sc = new Scanner(reader);
        
        String org;
        String des;
        int dis;
        int cont = 0;
        
        while (sc.hasNextLine()) {
            row = sc.nextLine();        
            org = row.substring(0, row.indexOf(" "));
            row = row.substring(row.indexOf(" ") + 1, row.length());      
            des = row.substring(0, row.indexOf(" "));
            row = row.substring(row.indexOf(" ") + 1, row.length());     
            dis = Integer.parseInt(row.substring(0, row.length()));   
            cont++;
            graph.add(new GraphModel(org, des, dis));
            reader.close();
            bfReader.close();
        }
        return graph;
    }
    
    public boolean check(ArrayList<String> graph,String origin, String destination){
        boolean result;
        result = graph.contains(origin) && graph.contains(destination);
        return result;
    }
    
    public String showMatrix(long[][] matrix){ 
        int row = matrix.length;  
        String container = "";    
        for(int x = 0; x < row; x++){
            for(int y = 0; y < row; y++){
                if(matrix[x][y]==999999999){
                    container += -1 + "\t";
                }else{
                    container += matrix[x][y] + "\t";
                }  
            }
            container += "\n";
        }
        if(container.equals("")){
            container = "Advertencia: (No existe ningun dato!)";
        }
        return container;
    }
}
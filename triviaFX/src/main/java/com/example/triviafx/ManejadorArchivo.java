package com.example.triviafx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ManejadorArchivo {

    public static ArrayList<Pregunta> listaPreguntas(String ruta) throws IOException {
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;
            while ((linea = br.readLine()) != null){
                String[] tokens = linea.split(";");
                ArrayList<String> opciones = new ArrayList<>();
                opciones.add(tokens[1]);
                opciones.add(tokens[2]);
                opciones.add(tokens[3]);
                opciones.add(tokens[4]);
                int indiceRespuesta = Integer.parseInt(tokens[5]);
                indiceRespuesta--;
                Pregunta p = new Pregunta(tokens[0],opciones,indiceRespuesta);
                preguntas.add(p);
            }
        }catch (IOException e){
            throw new IOException("Error al abrir el archivo");
        }
        return preguntas;
    };
}

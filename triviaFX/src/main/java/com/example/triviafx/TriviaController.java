package com.example.triviafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.*;

public class TriviaController {

    private ArrayList<Pregunta> bancoPreguntas;
    private ArrayList<Integer> indices;
    private int indiceActual = 0;
    private int puntuacion = 0;

    private List<RadioButton> listaRadioButtons;
    @FXML
    private Button btnSiguiente;

    @FXML
    private ImageView imgPregunta;

    @FXML
    private TextField lbPregunta;

    @FXML
    private Label lbPuntaje;

    @FXML
    private ToggleGroup opciones;

    @FXML
    private ProgressBar pbProgreso;

    @FXML
    private RadioButton rbCuatro;

    @FXML
    private RadioButton rbDos;

    @FXML
    private RadioButton rbTres;

    @FXML
    private RadioButton rbUno;

    @FXML
    private TextField tfRetroalimentacion;

    @FXML
    void onActionSiguiente(ActionEvent event) {
        if(indiceActual == 10){
            tfRetroalimentacion.setText("HA TERMINADO!");
            lbPregunta.setText("");
            return;
        }
        RadioButton seleccionado = (RadioButton) opciones.getSelectedToggle();
        if(seleccionado == null){
            tfRetroalimentacion.setText("No hay nada seleccionado");
        }else{
            boolean estado = revisarRespuestaCorrecta(seleccionado);
            indiceActual++;
            pbProgreso.setProgress(indiceActual * 0.1);
            if(indiceActual == 10){
                tfRetroalimentacion.setText("HA TERMINADO!");
                lbPregunta.setText("");
                return;
            }
            if(estado){
                tfRetroalimentacion.setText("Correcta :D");
                puntuacion++;
                lbPuntaje.setText("Puntaje: "+puntuacion);
            }else{
                tfRetroalimentacion.setText("Incorrecta :(");
            }
            cargarNuevaPregunta();

        }

    }

    private void cargarNuevaPregunta() {
        lbPregunta.setText(bancoPreguntas.get(indices.get(indiceActual)).getTexto());
        ArrayList<String> opciones = bancoPreguntas.get(indices.get(indiceActual)).getOpciones();
        rbUno.setText(opciones.get(0));
        rbDos.setText(opciones.get(1));
        rbTres.setText(opciones.get(2));
        rbCuatro.setText(opciones.get(3));
        this.opciones.selectToggle(null);
    }

    public boolean revisarRespuestaCorrecta(RadioButton opcion){

        Pregunta pregunta = (Pregunta) bancoPreguntas.get(indices.get(indiceActual));
        int indiceCorrecto = pregunta.getIndiceRespuestaCorrecta();
        if((opcion == rbUno) && (indiceCorrecto == 0)){
            System.out.println("Correcto");
            return true;
        }else if((opcion == rbDos) && (indiceCorrecto == 1)){
            return true;
        }else if((opcion == rbTres) && (indiceCorrecto == 2)){
            return true;
        }else if((opcion == rbCuatro) && (indiceCorrecto == 3)){
            return true;
        }else{
            return false;
        }
    };

    public void initialize() throws IOException {
        try{
            Image imagen = new Image("file:src/main/resources/com/example/triviafx/cover.png");
            imgPregunta.setImage(imagen);
            bancoPreguntas = ManejadorArchivo.listaPreguntas("src/main/resources/com/example/triviafx/bancoPreguntas.txt");
            Collections.shuffle(bancoPreguntas);
            indices = new ArrayList<>();
            while(indices.size() <10){
                int random = new Random().nextInt(bancoPreguntas.size()-1);
                boolean bandera = false;
                for(Integer i : indices){
                    if (i == random) {
                        bandera = true;
                        break;
                    }
                }
                if(!bandera){
                    indices.add(random);
                }
            }
            lbPuntaje.setText("Puntaje: "+puntuacion);
            cargarNuevaPregunta();
        }catch (IOException e){
            throw new IOException("Error al abrir el archivo");
        }
    }
}

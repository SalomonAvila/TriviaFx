package com.example.triviafx;

import java.util.ArrayList;

public class Pregunta {
    private String texto;
    private ArrayList<String> opciones;
    private int indiceRespuestaCorrecta;

    public Pregunta(String texto, ArrayList<String> opciones, int indiceRespuestaCorrecta) {
        if((opciones.size() == 4) && (indiceRespuestaCorrecta < 4) && (indiceRespuestaCorrecta >=0)){
            this.texto = texto;
            this.opciones = opciones;
            this.indiceRespuestaCorrecta = indiceRespuestaCorrecta;
        }
    }

    public String getTexto() {
        return texto;
    }

    public ArrayList<String> getOpciones() {
        return opciones;
    }

    public int getIndiceRespuestaCorrecta() {
        return indiceRespuestaCorrecta;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setOpciones(ArrayList<String> opciones) {
        this.opciones = opciones;
    }

    public void setIndiceRespuestaCorrecta(int indiceRespuestaCorrecta) {
        this.indiceRespuestaCorrecta = indiceRespuestaCorrecta;
    }

    @Override
    public String toString() {
        return String.format("Texto: %s\n; Opciones: %s;\n Correcta: %s", texto, opciones,indiceRespuestaCorrecta);
    }
}

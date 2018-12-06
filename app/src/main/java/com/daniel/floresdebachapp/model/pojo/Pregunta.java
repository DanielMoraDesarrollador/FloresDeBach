package com.daniel.floresdebachapp.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pregunta implements Serializable {

    @SerializedName("preguntaG")
    private String laPregunta;

    @SerializedName("respuesta")
    private String laRespuesta;

    public Pregunta() {
    }

    public String getLaPregunta() {
        return laPregunta;
    }

    public String getLaRespuesta() {
        return laRespuesta;
    }
}

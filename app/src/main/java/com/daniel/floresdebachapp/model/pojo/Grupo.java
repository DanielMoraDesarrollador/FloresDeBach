package com.daniel.floresdebachapp.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Grupo implements Serializable {

    @SerializedName("data")
    private List<Pregunta> listaDePreguntas;

    private String grupoId;

    @SerializedName("nombreG")
    private String nombreGrupo;

    public Grupo() {
    }

    public List<Pregunta> getListaDePreguntas() {
        return listaDePreguntas;
    }

    public String getGrupoId() {
        return grupoId;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (!(obj instanceof Grupo)){
//            return false;
//        }
//        Grupo grupoAComparar= (Grupo) obj;
//        return grupoAComparar.getGrupoId().equals(this.grupoId);
//    }

    @Override
    public String toString() {
        return getNombreGrupo();
    }
}

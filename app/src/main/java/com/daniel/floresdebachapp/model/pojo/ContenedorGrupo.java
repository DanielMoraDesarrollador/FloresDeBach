package com.daniel.floresdebachapp.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContenedorGrupo {

    @SerializedName("grupos")
    private List<Grupo>listaDeGrupos;

    public ContenedorGrupo() {
    }

    public List<Grupo> getListaDeGrupos() {
        return listaDeGrupos;
    }

    public void setListaDeGrupos(List<Grupo> listaDeGrupos) {
        this.listaDeGrupos = listaDeGrupos;
    }
}

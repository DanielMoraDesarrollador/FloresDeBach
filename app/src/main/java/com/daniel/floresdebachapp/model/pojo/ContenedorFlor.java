package com.daniel.floresdebachapp.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ContenedorFlor {

    @SerializedName("flores")
    private List<FlorDeBach> flores;

    public List<FlorDeBach> getFlores() {
        return flores;
    }

    public void setFlores(List<FlorDeBach> flores) {
        this.flores = flores;
    }

    public ContenedorFlor() {

    }
}

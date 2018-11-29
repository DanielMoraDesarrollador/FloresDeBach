package com.daniel.floresdebachapp.model.pojo;

import java.io.Serializable;

public class FlorDeBach implements Serializable {

    private String nombre;

    private String nombreOriginal;

    private String descripcion;

    private String florId;

    private String imagenFlor;

    private String imagenFlor2;

    private String nombreCientifico;

    private String palabraClave;

    private String tendenciaPsicosomatica;

    private String aspectosPositivos;

    private String comoActua;

    private String grupo;

    private String modalidad;

    private String uso;

    private String color;

    private String chakra;

    private String preparacion;

    public FlorDeBach(String nombre, String nombreOriginal, String descripcion, String florId) {
        this.nombre = nombre;
        this.nombreOriginal = nombreOriginal;
        this.descripcion = descripcion;
        this.florId = florId;
    }

    public FlorDeBach() {
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public String getPalabraClave() {
        return palabraClave;
    }

    public String getTendenciaPsicosomatica() {
        return tendenciaPsicosomatica;
    }

    public String getAspectosPositivos() {
        return aspectosPositivos;
    }

    public String getComoActua() {
        return comoActua;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getModalidad() {
        return modalidad;
    }

    public String getUso() {
        return uso;
    }

    public String getColor() {
        return color;
    }

    public String getChakra() {
        return chakra;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public String getImagenFlor2() {
        return imagenFlor2;
    }

    public String getImagenFlor() {
        return imagenFlor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreOriginal() {
        return nombreOriginal;
    }

    public void setNombreOriginal(String nombreOriginal) {
        this.nombreOriginal = nombreOriginal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFlorId() {
        return florId;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FlorDeBach)) {
            return false;
        }
        FlorDeBach florAComparar = (FlorDeBach) obj;
        return florAComparar.getNombre().equals(this.nombre);
    }

    @Override
    public String toString() {
        return getNombre();
    }
}

package com.daniel.floresdebachapp.model.dao;


import com.daniel.floresdebachapp.model.pojo.ContenedorFlor;
import com.daniel.floresdebachapp.model.pojo.ContenedorGrupo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceDao {

//    @GET("bins/buqj6")
//    Call<ContenedorFlor>obtenerFloresBach();

    @GET("bins/10sb3y")
    Call<ContenedorFlor>obtenerFloresBach();

    @GET("bins/10sb3y")
    Call<ContenedorGrupo>obtenerGrupos();

//    @GET("bins/buqj6/:grupos")
//    Call<ContenedorGrupo>obtenerGrupos();
}

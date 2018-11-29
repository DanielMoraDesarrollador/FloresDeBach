package com.daniel.floresdebachapp.model.dao;


import com.daniel.floresdebachapp.model.pojo.ContenedorFlor;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceDao {

    @GET("bins/1d9qte")
    Call<ContenedorFlor>obtenerFloresBach();
}

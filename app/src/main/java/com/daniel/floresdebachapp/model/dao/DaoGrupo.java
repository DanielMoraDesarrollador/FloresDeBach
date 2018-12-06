package com.daniel.floresdebachapp.model.dao;

import com.daniel.floresdebachapp.model.pojo.ContenedorGrupo;
import com.daniel.floresdebachapp.model.pojo.Grupo;
import com.daniel.floresdebachapp.utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaoGrupo {

    private Retrofit retrofit;
    private ServiceDao serviceRetrofit;
    private static final String BASE_URL = "https://api.myjson.com/";

    public DaoGrupo() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.client(httpClient.build()).build();
        serviceRetrofit = retrofit.create(ServiceDao.class);
    }

    public void obtenerGrupos(final ResultListener<List<Grupo>> resultListenerDelController) {
        Call<ContenedorGrupo> call = serviceRetrofit.obtenerGrupos();
        call.enqueue(new Callback<ContenedorGrupo>() {
            @Override
            public void onResponse(Call<ContenedorGrupo> call, Response<ContenedorGrupo> response) {
                List<Grupo> gruposObtenidos = response.body().getListaDeGrupos();
                resultListenerDelController.finish(gruposObtenidos);
            }

            @Override
            public void onFailure(Call<ContenedorGrupo> call, Throwable t) {
                resultListenerDelController.finish(new ArrayList<Grupo>());
            }
        });
    }
}

package com.daniel.floresdebachapp.model.dao;

import com.daniel.floresdebachapp.model.pojo.ContenedorFlor;
import com.daniel.floresdebachapp.model.pojo.FlorDeBach;
import com.daniel.floresdebachapp.utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaoFlor {


    private Retrofit retrofit;
    private ServiceDao serviceRetrofit;
    private static final String BASE_URL = "https://api.myjson.com/";

    public DaoFlor() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.client(httpClient.build()).build();
        serviceRetrofit = retrofit.create(ServiceDao.class);
    }

    public void obtenerFloresDeBach(final ResultListener<List<FlorDeBach>> resultListenerDelController) {
        Call<ContenedorFlor> call = serviceRetrofit.obtenerFloresBach();
        call.enqueue(new Callback<ContenedorFlor>() {
            @Override
            public void onResponse(Call<ContenedorFlor> call, Response<ContenedorFlor> response) {
                List<FlorDeBach> floresObtenidas = response.body().getFlores();
                resultListenerDelController.finish(floresObtenidas);
            }

            @Override
            public void onFailure(Call<ContenedorFlor> call, Throwable t) {
                resultListenerDelController.finish(new ArrayList<FlorDeBach>());
            }
        });
    }

}

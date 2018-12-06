package com.daniel.floresdebachapp.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.daniel.floresdebachapp.model.dao.DaoGrupo;
import com.daniel.floresdebachapp.model.pojo.Grupo;
import com.daniel.floresdebachapp.utils.ResultListener;

import java.util.List;

public class ControllerGrupo {

    private Context context;

    public ControllerGrupo(Context context) {
        this.context = context;
    }

    private boolean hayInternet() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    public void obtenerGruposOnline(final ResultListener<List<Grupo>> resultListenerDeLaVista) {
        if (hayInternet()) {
            DaoGrupo daoGrupo = new DaoGrupo();
            daoGrupo.obtenerGrupos(new ResultListener<List<Grupo>>() {
                @Override
                public void finish(List<Grupo> resultado) {
                    resultListenerDeLaVista.finish(resultado);
                }
            });
        }
    }
}

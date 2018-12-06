package com.daniel.floresdebachapp.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daniel.floresdebachapp.R;
import com.daniel.floresdebachapp.controller.ControllerGrupo;
import com.daniel.floresdebachapp.model.adapter.AdapterGrupo;
import com.daniel.floresdebachapp.model.pojo.Grupo;
import com.daniel.floresdebachapp.utils.ResultListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGrupos extends Fragment implements AdapterGrupo.NotificadorGrupo {

    private RecyclerView recyclerViewGrupo;
    private AdapterGrupo adapterGrupo;
    private ControllerGrupo controllerGrupo;


    public FragmentGrupos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grupos, container, false);
        recyclerViewGrupo = view.findViewById(R.id.recycler_fragment_grupo);
        controllerGrupo = new ControllerGrupo(getContext());
        adapterGrupo = new AdapterGrupo(getContext(), this);
        recyclerViewGrupo.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewGrupo.setBackgroundResource(R.drawable.gradienteverde);
        recyclerViewGrupo.setAdapter(adapterGrupo);
        obtenerGrupos();
        return view;
    }

    public void obtenerGrupos() {
        controllerGrupo.obtenerGruposOnline(new ResultListener<List<Grupo>>() {
            @Override
            public void finish(List<Grupo> resultado) {
                adapterGrupo.agregarGrupos(resultado);
            }
        });
    }

    @Override
    public void notificarGrupoCliqueado(List<Grupo> listaGrupos, int posicion) {

    }
}

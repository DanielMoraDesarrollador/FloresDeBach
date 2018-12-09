package com.daniel.floresdebachapp.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daniel.floresdebachapp.R;
import com.daniel.floresdebachapp.model.adapter.AdapterPregunta;
import com.daniel.floresdebachapp.model.pojo.Grupo;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPregunta extends Fragment {

    private RecyclerView recyclerViewPregunta;
    private AdapterPregunta adapterPregunta;
    private LinearLayoutManager linearLayoutManagerPregunta;

    public FragmentPregunta() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pregunta, container, false);

        recyclerViewPregunta = view.findViewById(R.id.recycler_pregunta);
        adapterPregunta = new AdapterPregunta(getContext());
        linearLayoutManagerPregunta = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewPregunta.setLayoutManager(linearLayoutManagerPregunta);
        recyclerViewPregunta.setAdapter(adapterPregunta);


        return view;
    }

    public void setearListaPreguntas(Grupo grupo){
        adapterPregunta.setPreguntas(grupo.getListaDePreguntas());
    }
}

package com.daniel.floresdebachapp.model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daniel.floresdebachapp.R;
import com.daniel.floresdebachapp.model.pojo.Pregunta;

import java.util.ArrayList;
import java.util.List;

public class AdapterPregunta extends RecyclerView.Adapter {

    private Context context;
    private List<Pregunta> preguntas;


    public AdapterPregunta(Context context) {
        this.context = context;
        this.preguntas = new ArrayList<>();
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.celda_pregunta, parent, false);
        ViewHolderPregunta viewHolderPregunta = new ViewHolderPregunta(view);
        return viewHolderPregunta;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Pregunta pregunta = preguntas.get(position);
        ViewHolderPregunta viewHolderPregunta = (ViewHolderPregunta) holder;
        viewHolderPregunta.cargarPregunta(pregunta);
    }

    @Override
    public int getItemCount() {
        if (preguntas != null) {
            return preguntas.size();
        } else {
            return 0;
        }
    }


    public void agregarPreguntas(List<Pregunta> resultado) {
        for (Pregunta preguntaAAgregar : resultado) {
            if (!preguntas.contains(preguntaAAgregar)) {
                preguntas.add(preguntaAAgregar);
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolderPregunta extends RecyclerView.ViewHolder {

        private TextView textViewPregunta;
        private TextView textViewRespuesta;

        public ViewHolderPregunta(View itemView) {
            super(itemView);
            textViewPregunta = itemView.findViewById(R.id.pregunta);
            textViewRespuesta = itemView.findViewById(R.id.respuesta);
            textViewPregunta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textViewRespuesta.setVisibility(View.VISIBLE);
                }
            });
        }

        public void cargarPregunta(Pregunta pregunta) {
            textViewPregunta.setText(pregunta.getLaPregunta());
            textViewRespuesta.setText(pregunta.getLaRespuesta());
        }
    }
}

package com.daniel.floresdebachapp.model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daniel.floresdebachapp.R;
import com.daniel.floresdebachapp.model.pojo.Grupo;
import com.daniel.floresdebachapp.model.pojo.Pregunta;

import java.util.ArrayList;
import java.util.List;

public class AdapterGrupo extends RecyclerView.Adapter {

    private Context context;
    private List<Grupo> grupos;
    private NotificadorGrupo notificadorGrupo;

    public AdapterGrupo(Context context, NotificadorGrupo notificadorGrupo) {
        this.context = context;
        this.grupos = new ArrayList<>();
        this.notificadorGrupo = notificadorGrupo;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.celda_grupos, parent, false);
        ViewHolderGrupo viewHolderGrupo = new ViewHolderGrupo(view);
        return viewHolderGrupo;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Grupo grupo = grupos.get(position);
        ViewHolderGrupo viewHolderGrupo = (ViewHolderGrupo) holder;
        viewHolderGrupo.cargarGrupo(grupo);
    }

    @Override
    public int getItemCount() {
        if (grupos != null) {
            return grupos.size();
        } else {
            return 0;
        }
    }

    public void agregarGrupos(List<Grupo> resultado) {
        for (Grupo grupoAAgregar : resultado) {
            if (!grupos.contains(grupoAAgregar)) {
                grupos.add(grupoAAgregar);
            }
        }
        notifyDataSetChanged();
    }


    public class ViewHolderGrupo extends RecyclerView.ViewHolder {

        private TextView textViewNombre;
        private List<Pregunta> preguntaList;

        public ViewHolderGrupo(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.nombre_celda_grupo);
            textViewNombre.setSelected(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicionCliqueada = getAdapterPosition();
                    notificadorGrupo.notificarGrupoCliqueado(grupos, posicionCliqueada, preguntaList);
                }
            });
        }


        public void cargarGrupo(Grupo grupo) {
            textViewNombre.setText(grupo.getNombreGrupo());
            preguntaList.addAll(grupo.getListaDePreguntas());
        }
    }

    public interface NotificadorGrupo {
        public void notificarGrupoCliqueado(List<Grupo> listaGrupos, int posicion, List<Pregunta> preguntas);
    }

}

package com.daniel.floresdebachapp.model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daniel.floresdebachapp.R;
import com.daniel.floresdebachapp.model.pojo.FlorDeBach;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class AdapterFlor extends RecyclerView.Adapter {

    private Context context;
    private List<FlorDeBach> listaFlores;
    private NotificadorFlor notificadorFlor;

    public AdapterFlor(Context context, NotificadorFlor notificadorFlor) {
        this.context = context;
        this.listaFlores = new ArrayList<>();
        this.notificadorFlor = notificadorFlor;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.celda_recycler_flores, parent, false);
        ViewHolderFlor viewHolderFlor = new ViewHolderFlor(view);
        return viewHolderFlor;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FlorDeBach florDeBach = listaFlores.get(position);
        ViewHolderFlor viewHolderFlor = (ViewHolderFlor) holder;
        viewHolderFlor.cargarFlor(florDeBach);

    }

    @Override
    public int getItemCount() {
        if (listaFlores != null) {
            return listaFlores.size();
        } else {
            return 0;
        }
    }

    public void agregarFlores(List<FlorDeBach> resultado) {

        for (FlorDeBach florAAgregar : resultado) {
            if (!listaFlores.contains(florAAgregar)) {
                listaFlores.add(florAAgregar);
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolderFlor extends RecyclerView.ViewHolder {

        private TextView textViewFlor;
        private TextView textViewFlorOriginal;
        private ImageView imageViewFlor;
        private FirebaseStorage storage;
        private StorageReference reference;

        public ViewHolderFlor(View itemView) {
            super(itemView);
            textViewFlor = itemView.findViewById(R.id.nombre_flor);
            imageViewFlor = itemView.findViewById(R.id.imagen_flor);
            textViewFlorOriginal = itemView.findViewById(R.id.nombre_original);
            textViewFlorOriginal.setSelected(true);
            textViewFlor.setSelected(true);
            storage = FirebaseStorage.getInstance();
            reference = storage.getReference();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicionFlorCliqueada = getAdapterPosition();
                    notificadorFlor.notificarCeldaCliqueada(listaFlores,posicionFlorCliqueada);
                }
            });
        }

        public void cargarFlor(FlorDeBach florDeBach) {
            textViewFlor.setText(florDeBach.getNombreOriginal());
            textViewFlorOriginal.setText("(" + florDeBach.getNombreCientifico() + ")");
            if (TextUtils.isEmpty(florDeBach.getImagenFlor2())) {
                return;
            }
            cargarImagenesDescargadas(florDeBach.getImagenFlor2());
        }

        private void cargarImagenesDescargadas(String imagenDescargada) {
            Glide.with(context)
                    .using(new FirebaseImageLoader())
                    .load(reference.child(imagenDescargada))
                    .placeholder(R.drawable.placeholder)
                    .into(imageViewFlor);
        }
    }

    public interface NotificadorFlor {
        public void notificarCeldaCliqueada(List<FlorDeBach> flores, int posicion);
    }


}

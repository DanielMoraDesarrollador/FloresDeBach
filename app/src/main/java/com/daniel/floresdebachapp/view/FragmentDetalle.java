package com.daniel.floresdebachapp.view;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalle extends Fragment {

    private static final String FLOR_RECIBIDA = "flor_recibida";

    private ImageView imageView;
    private TextView nombreFlor, nombreOriginal, descripcion, nombreCientifico, uso, color, chakra,
            palabraClave, tendenciaPsicosomatica, aspectosPositivos, comoActua, modalidad,
            preparacion, grupo;

    private FirebaseStorage storage;
    private StorageReference reference;

    private FlorDeBach florDeBach;

    private ImageView imagenGrande;
    private TextView titulo;
    private CardView cardview;

    private AppBarLayout appBarLayout;
    private NestedScrollView nestedScrollView;

    public FragmentDetalle() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);
        imageView = view.findViewById(R.id.imagen_detalle);
        nombreFlor = view.findViewById(R.id.nombre_flor_detalle);
        nombreOriginal = view.findViewById(R.id.nombre_español_detalle);
        nombreCientifico = view.findViewById(R.id.nombre_cientifico_detalle);
        uso = view.findViewById(R.id.uso_detalle);
        color = view.findViewById(R.id.color_detalle);
        chakra = view.findViewById(R.id.chakra_detalle);
        palabraClave = view.findViewById(R.id.palabra_clave_detalle);
        aspectosPositivos = view.findViewById(R.id.aspec_posit_detalle);
        tendenciaPsicosomatica = view.findViewById(R.id.tend_psico_detalle);
        comoActua = view.findViewById(R.id.como_actua_detalle);
        modalidad = view.findViewById(R.id.modalidad_detalle);
        preparacion = view.findViewById(R.id.preparacion_detalle);
        grupo = view.findViewById(R.id.grupo_detalle);
        nombreFlor.setSelected(true);
        nombreOriginal.setSelected(true);
        descripcion = view.findViewById(R.id.descripcion);
        Bundle bundle = getArguments();
        florDeBach = (FlorDeBach) bundle.getSerializable(FLOR_RECIBIDA);
        storage = FirebaseStorage.getInstance();
        reference = storage.getReference();
        nombreFlor.setText(florDeBach.getNombreOriginal());
        nombreOriginal.setText(florDeBach.getNombre());
        nombreCientifico.setText("Nombre Cientifico: " + florDeBach.getNombreCientifico());
        descripcion.setText(florDeBach.getDescripcion());
        uso.setText(florDeBach.getUso());
        palabraClave.setText(florDeBach.getPalabraClave());
        tendenciaPsicosomatica.setText(florDeBach.getTendenciaPsicosomatica());
        aspectosPositivos.setText(florDeBach.getAspectosPositivos());
        comoActua.setText(florDeBach.getComoActua());
        modalidad.setText(florDeBach.getModalidad());
        preparacion.setText("Preparación: \n\n" + florDeBach.getPreparacion());
        color.setText("Color: \n\n" + florDeBach.getColor());
        chakra.setText("Chakra: \n\n" + florDeBach.getChakra());
        grupo.setText(florDeBach.getGrupo());

        cardview = view.findViewById(R.id.card_detalle);
        nestedScrollView = view.findViewById(R.id.scroll_detalle);
        appBarLayout = view.findViewById(R.id.appbar_detalle);
        imagenGrande = view.findViewById(R.id.imagen_gigante);
        titulo = view.findViewById(R.id.titulo);
        titulo.setText(florDeBach.getNombreOriginal());
        cargarImagenesGiganteDescargadas(florDeBach.getImagenFlor());

        cargarImagenesGrandeDescargadas(florDeBach.getImagenFlor());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardview.setVisibility(View.VISIBLE);
                appBarLayout.setVisibility(View.GONE);
                nestedScrollView.setVisibility(View.GONE);
                imagenGrande.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cardview.setVisibility(View.GONE);
                        appBarLayout.setVisibility(View.VISIBLE);
                        nestedScrollView.setVisibility(View.VISIBLE);
                    }
                });

            }
        });
        return view;
    }

    public static FragmentDetalle dameUnFragment(FlorDeBach florDeBach) {
        FragmentDetalle fragmentDetalleFlorCreado = new FragmentDetalle();
        Bundle bundle = new Bundle();
        bundle.putSerializable(FLOR_RECIBIDA, florDeBach);
        fragmentDetalleFlorCreado.setArguments(bundle);
        return fragmentDetalleFlorCreado;
    }

    private void cargarImagenesGrandeDescargadas(String imagenDescargada) {
        if (TextUtils.isEmpty(imagenDescargada)) {
            return;
        }
        Glide.with(getContext())
                .using(new FirebaseImageLoader())
                .load(reference.child(imagenDescargada))
                .into(imageView);
    }

    private void cargarImagenesGiganteDescargadas(String imagenDescargada) {
        if (TextUtils.isEmpty(imagenDescargada)) {
            return;
        }
        Glide.with(getContext())
                .using(new FirebaseImageLoader())
                .load(reference.child(imagenDescargada))
                .into(imagenGrande);
    }

}

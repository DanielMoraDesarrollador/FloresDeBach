package com.daniel.floresdebachapp.view;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.daniel.floresdebachapp.R;
import com.daniel.floresdebachapp.model.pojo.FlorDeBach;
import com.daniel.floresdebachapp.utils.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetalle extends AppCompatActivity {

    public static final String FLOR_KEY = "flor_key";
    public static final String POSICION = "posicion_key";

    private List<FragmentDetalle> fragmentDetalles;
    private List<FlorDeBach> floresRecibidas;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        viewPager = findViewById(R.id.view_pager);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        floresRecibidas = (List<FlorDeBach>) bundle.getSerializable(FLOR_KEY);
        crearListaDeFragments();
        FragDetallePAFlor fragDetallePAFlor = new FragDetallePAFlor(getSupportFragmentManager(), fragmentDetalles);
        viewPager.setAdapter(fragDetallePAFlor);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        int posicionDelItem = bundle.getInt(POSICION);
        viewPager.setCurrentItem(posicionDelItem);

    }

    private void crearListaDeFragments() {
        fragmentDetalles = new ArrayList<>();
        for (FlorDeBach florDeBach : floresRecibidas) {
            fragmentDetalles.add(FragmentDetalle.dameUnFragment(florDeBach));
        }
    }
}

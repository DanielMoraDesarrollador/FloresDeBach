package com.daniel.floresdebachapp.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.daniel.floresdebachapp.model.dao.DaoFlor;
import com.daniel.floresdebachapp.model.pojo.FlorDeBach;
import com.daniel.floresdebachapp.utils.ResultListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ControllerFlor {

    private Context context;
//    private static final String FLORES = "flores";
//    private FirebaseDatabase database;
//    private DatabaseReference reference;

    public ControllerFlor(Context context) {
        this.context = context;
    }

    private boolean hayInternet() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void obtenerFloresOnLine(final ResultListener<List<FlorDeBach>> resultListenerDeLaVista) {
        if (hayInternet()) {

            DaoFlor daoFlor = new DaoFlor();
            daoFlor.obtenerFloresDeBach(new ResultListener<List<FlorDeBach>>() {
                @Override
                public void finish(List<FlorDeBach> resultado) {
                    resultListenerDeLaVista.finish(resultado);
                }
            });


        }
//            database = FirebaseDatabase.getInstance();
//            reference = database.getReference().child(FLORES);
//            reference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    if (!dataSnapshot.exists()) {
//                        return;
//                    }
//                    List<FlorDeBach> florDeBachLista = new ArrayList<>();
//
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        FlorDeBach florDeBach = snapshot.getValue(FlorDeBach.class);
//                        florDeBachLista.add(florDeBach);
//                        resultListenerDeLaVista.finish(florDeBachLista);
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    Toast.makeText(context, "Fallo", Toast.LENGTH_SHORT).show();
//
//                }
//            });
//        }
    }
}

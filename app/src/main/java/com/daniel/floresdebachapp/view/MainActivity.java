package com.daniel.floresdebachapp.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.daniel.floresdebachapp.R;
import com.daniel.floresdebachapp.controller.ControllerFlor;
import com.daniel.floresdebachapp.model.adapter.AdapterFlor;
import com.daniel.floresdebachapp.model.adapter.AdapterPregunta;
import com.daniel.floresdebachapp.model.pojo.FlorDeBach;
import com.daniel.floresdebachapp.model.pojo.Grupo;
import com.daniel.floresdebachapp.model.pojo.Pregunta;
import com.daniel.floresdebachapp.utils.FragmentHelper;
import com.daniel.floresdebachapp.utils.ResultListener;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterFlor.NotificadorFlor{

    private FirebaseAuth firebaseAuth;
    private Intent intent;
    private FragmentManager fragmentManager;

//    private boolean doubleBackToExitPressedOnce = false;


    private RecyclerView recyclerViewFlores;
    private LinearLayoutManager linearLayoutManagerGrupo;
    private AdapterFlor adapterFlor;
    private ControllerFlor controllerFlor;

    private FragmentGrupos fragmentGrupos;

    private RecyclerView recyclerViewPregunta;
    private AdapterPregunta adapterPregunta;
    private LinearLayoutManager linearLayoutManagerPregunta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar miToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(miToolbar);
        firebaseAuth = FirebaseAuth.getInstance();
        intent = new Intent(this, LoginActivity.class);
        adapterFlor = new AdapterFlor(getApplicationContext(), this);

        recyclerViewPregunta = findViewById(R.id.recycler_pregunta);
        adapterPregunta = new AdapterPregunta(getApplicationContext());
        linearLayoutManagerPregunta = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        fragmentManager = getSupportFragmentManager();
        linearLayoutManagerGrupo = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewFlores = findViewById(R.id.recycler_main);
        setAdapterLinear(recyclerViewFlores, linearLayoutManagerGrupo, adapterFlor);
        controllerFlor = new ControllerFlor(getApplicationContext());
        obtenerFlores();
        chequearSiEstaLogueado();
        //printHash();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_search:
                return true;

            case R.id.action_setting:
                fragmentGrupos = new FragmentGrupos();
                FragmentHelper.cargarFragmentConBackStack(fragmentGrupos, R.id.contenedor_de_fragments, fragmentManager);
                break;

            case R.id.action_close:
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    if (AccessToken.getCurrentAccessToken() != null) {
                        LoginManager.getInstance().logOut();
                    }
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(getApplicationContext(),
                            "¡Cerró Sesión Correctamente!",
                            Toast.LENGTH_SHORT).show();
                    finishAffinity();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }


    public void chequearSiEstaLogueado() {
        if (firebaseAuth.getCurrentUser() == null) {
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        chequearSiEstaLogueado();
    }

    public void setAdapterLinear(RecyclerView recyclerView, LinearLayoutManager
            linearLayoutManager,
                                 RecyclerView.Adapter adapter) {

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }


    private void obtenerFlores() {
        controllerFlor.obtenerFloresOnLine(new ResultListener<List<FlorDeBach>>() {
            @Override
            public void finish(List<FlorDeBach> resultado) {
                adapterFlor.agregarFlores(resultado);
            }
        });
    }


    @Override
    public void notificarCeldaCliqueada(List<FlorDeBach> flores, int posicion) {
        Intent intent = new Intent(MainActivity.this, ActivityDetalle.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ActivityDetalle.FLOR_KEY, (Serializable) flores);
        bundle.putInt(ActivityDetalle.POSICION, posicion);
        intent.putExtras(bundle);
        startActivity(intent);
    }




//    private void printHash() {
//        try {
//
//            PackageInfo info =
//                    getPackageManager().getPackageInfo(getApplicationContext().getPackageName(),
//                            PackageManager.GET_SIGNATURES);
//
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.v("MY KEY HASH:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//    }


//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
//    }
}
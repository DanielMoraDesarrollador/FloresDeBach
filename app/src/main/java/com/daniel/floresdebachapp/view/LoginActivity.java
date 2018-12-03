package com.daniel.floresdebachapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daniel.floresdebachapp.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    private EditText emailEdit;
    private EditText passEdit;
    private Button buttonIngresar;
    private Button buttonRegistrarse;
    private TextView textViewError;

//    private Intent intent;

    private CallbackManager callbackManager;
    private LoginButton loginButton;

    private CardView cardViewBienvenida;
    private CardView bienvenida;

    private static final String TAG = LoginActivity.class.getSimpleName();
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailEdit = findViewById(R.id.edit_text_email);
        passEdit = findViewById(R.id.edit_text_password);
        buttonIngresar = findViewById(R.id.button_ingresar);
        buttonRegistrarse = findViewById(R.id.button_registrarse);
        textViewError = findViewById(R.id.error_text_id);

        bienvenida = findViewById(R.id.bienvenida);
        cardViewBienvenida = findViewById(R.id.card_bienvenida);
        bienvenida.setBackgroundResource(R.color.transparente);
        cardViewBienvenida.setBackgroundResource(R.color.transparente);

        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailEdit.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this,
                            "Completar el campo E-MAIL",
                            Toast.LENGTH_SHORT).show();
                }
                if (passEdit.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this,
                            "Completar el campo PASSWORD",
                            Toast.LENGTH_SHORT).show();
                } else {
                    loginUsuario(emailEdit.getText().toString(), passEdit.getText().toString());
                }
            }
//                buttonIngresar.requestFocus();
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(buttonIngresar.getWindowToken(), 0);
//                if (emailEdit.getText().toString().isEmpty() || passEdit.getText().toString().isEmpty()) {
//                    textViewError.setText("Ingrese un email y contraseña validos");
//                    textViewError.setVisibility(View.VISIBLE);
//                    return;
//                }
//                loguearNativamente(emailEdit.getText().toString(), passEdit.getText().toString());
//            }
        });

        buttonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailEdit.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this,
                            "Completar el campo E-MAIL",
                            Toast.LENGTH_SHORT).show();
                }
                if (passEdit.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this,
                            "Completar el campo PASSWORD",
                            Toast.LENGTH_SHORT).show();
                } else {
                    crearUsuario(emailEdit.getText().toString(), passEdit.getText().toString());
                }
            }
//                buttonRegistrarse.requestFocus();
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(buttonRegistrarse.getWindowToken(), 0);
//                if (emailEdit.getText().toString().isEmpty() || passEdit.getText().toString().isEmpty()) {
//                    textViewError.setText("Ingrese un email y contraseña validos");
//                    textViewError.setVisibility(View.VISIBLE);
//                    return;
//                }
//                registrarUsuario(emailEdit.getText().toString(), passEdit.getText().toString());
//            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //El usuario ha iniciado sesión
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Log.d("facebook", "onAuthStateChanged:signed_out");

                } else {
                    //El usuario está desconectado
                    Log.d("facebook", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email, public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });

//        intent = new Intent(LoginActivity.this, MainActivity.class);


//        chequearSiEstaLogueado();

//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
//
//        LoginManager.getInstance().registerCallback(callbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        // App code
//                        loguearFirebaseConFacebook(loginResult.getAccessToken());
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        // App code
//                    }
//
//                    @Override
//                    public void onError(FacebookException exception) {
//                        // App code
//                        textViewError.setText("Error al loguearse con Facebook, intentelo nuevamente");
//                        textViewError.setVisibility(View.VISIBLE);
//                    }
//                });

//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//        if (isLoggedIn) {
//            return;
//        }
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void crearUsuario(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Inicie sesión con éxito, actualice la IU con la información del usuario que inició sesión.
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            //Si el inicio de sesión falla, muestre un mensaje al usuario
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }

    private void loginUsuario(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Inicie sesión con éxito, actualice la IU con la información del usuario que inició sesión.
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            //Si el inicio de sesión falla, muestre un mensaje al usuario.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }
//    private void loguearNativamente(String s, String s1) {
//        mAuth.signInWithEmailAndPassword(s, s1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    startActivity(intent);
//                    finish();
//                } else {
//                    textViewError.setText("Error al loguearse, revise si escribio bien el email y password");
//                    textViewError.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//    }


//    private void chequearSiEstaLogueado() {
//        if (mAuth.getCurrentUser() != null) {
//            startActivity(intent);
//            finish();
//        }
//        if (AccessToken.getCurrentAccessToken() != null) {
//            startActivity(intent);
//            finish();
//        }
//    }

//    public void registrarUsuario(String email, String password) {
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            startActivity(intent);
//                        } else {
//                            textViewError.setText("Error al registrarse, por favor asegurese de que escribio un email valido");
//                            textViewError.setVisibility(View.VISIBLE);
//                        }
//                    }
//                });
//    }


//    private void loguearFirebaseConFacebook(AccessToken accessToken) {
//        loginButton.setVisibility(View.INVISIBLE);
//        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
//        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    startActivity(intent);
//                    finish();
//                } else {
//                    Toast.makeText(LoginActivity.this,
//                            "Autentificacion fallida, Intentelo Nuevamente", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }


    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}

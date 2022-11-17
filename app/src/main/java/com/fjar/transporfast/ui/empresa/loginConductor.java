package com.fjar.transporfast.ui.empresa;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.fjar.transporfast.R;

public class loginConductor extends AppCompatActivity {

    private EditText edtcorreo, edtcontrasena;
    private TextView tvMensaje;
    private Button btnRegistrar, btnRecContrasena, btnRecusuario, btnIngresar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtcorreo = (EditText) findViewById(R.id.et_usuarioIS);
        edtcontrasena = (EditText) findViewById(R.id.et_contrasenaIS);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrarse);
        tvMensaje = (TextView) findViewById(R.id.mensajeRegistrar);
        btnRecContrasena = (Button) findViewById(R.id.btnRecuperarContrasena);
        btnRecusuario = (Button) findViewById(R.id.btnRecuperarUsuario);


        edtcorreo.setVisibility(View.GONE);

        btnRegistrar.setVisibility(View.GONE);
        tvMensaje.setVisibility(View.GONE);

        btnRecusuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new android.app.AlertDialog.Builder(loginConductor.this)
                        .setTitle("Recuperar usuario")
                        .setMessage("Para recuperar su usuario, contacte al \n al admiistrador de su empresa.")
                        .setNegativeButton(android.R.string.cancel, null)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {

                            }
                        })
                        .show();
            }
        });

        btnRecContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new android.app.AlertDialog.Builder(loginConductor.this)
                        .setTitle("Recuperar contraseña")
                        .setMessage("Para recuperar su contraseña, contacte al \n al admiistrador de su empresa.")
                        .setNegativeButton(android.R.string.cancel, null)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {

                            }
                        })
                        .show();
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vista = new Intent(loginConductor.this, ConductorUI.class);
                startActivity(vista);
            }
        });
    }
}
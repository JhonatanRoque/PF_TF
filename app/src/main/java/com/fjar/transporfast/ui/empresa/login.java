package com.fjar.transporfast.ui.empresa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fjar.transporfast.MainActivity;
import com.fjar.transporfast.R;

public class login extends AppCompatActivity {
    private Button btnReg, btnRecuperarUsu, btnRecuperarContrasena, btning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btning = (Button) findViewById(R.id.btnIngresar);
        btnReg = (Button) findViewById(R.id.btnRegistrarse);
        btnRecuperarUsu = (Button) findViewById(R.id.btnRecuperarUsuario);
        btnRecuperarContrasena = (Button) findViewById(R.id.btnRecuperarContrasena);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog codigoV = new Dialog(login.this);
                codigoV.setContentView(R.layout.activity_prueba);
                codigoV.setTitle("Codigo de registro");
                Button btnValidar = (Button) codigoV.findViewById(R.id.btnValidar);
                btnValidar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Aqui ira el codigo para validar el codigo de registro de empresa
                        Intent vista = new Intent(login.this, RegistrarEmpresa.class);
                        startActivity(vista);
                    }
                });
                codigoV.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                codigoV.show();
            }
        });

        btnRecuperarContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog codigoV = new Dialog(login.this);
                codigoV.setContentView(R.layout.activity_prueba);
                codigoV.setTitle("Codigo de registro");
                Button btnValidar = (Button) codigoV.findViewById(R.id.btnValidar);
                btnValidar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent vista = new Intent(login.this, RecuperarContrasena.class);
                        startActivity(vista);
                    }
                });
                codigoV.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                codigoV.show();
            }
        });

        btnRecuperarUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog codigoV = new Dialog(login.this);
                codigoV.setContentView(R.layout.activity_prueba);
                codigoV.setTitle("Codigo de registro");
                Button btnValidar = (Button) codigoV.findViewById(R.id.btnValidar);
                btnValidar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent vista = new Intent(login.this, RecuperarUsuario.class);
                        startActivity(vista);
                    }
                });
                codigoV.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                codigoV.show();

            }
        });
        btning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vista = new Intent(login.this, MainActivity.class);
                startActivity(vista);
            }
        });
    }
}
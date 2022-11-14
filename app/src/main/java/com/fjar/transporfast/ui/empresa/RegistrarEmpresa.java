package com.fjar.transporfast.ui.empresa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fjar.transporfast.R;

public class RegistrarEmpresa extends AppCompatActivity {
    private TextView tvLogin;
    private Button btnRegEmpresa, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_empresa);

        btnRegEmpresa = (Button) findViewById(R.id.btn_registrar);
        btnVolver = (Button) findViewById(R.id.btn_Volver);
        tvLogin = (TextView) findViewById(R.id.btn_Login);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vista = new Intent(RegistrarEmpresa.this, login.class);
                startActivity(vista);
            }
        });

        btnRegEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vista = new Intent(RegistrarEmpresa.this, login.class);
                startActivity(vista);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vista = new Intent(RegistrarEmpresa.this, login.class);
                startActivity(vista);
            }
        });

    }
}
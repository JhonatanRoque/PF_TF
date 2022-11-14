package com.fjar.transporfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fjar.transporfast.ui.empresa.tipo;
import com.fjar.transporfast.ui.usuarioUI;

public class PrimerVentana extends AppCompatActivity {
    private Button btnUsuario, btnEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer_ventana);

        btnUsuario = (Button) findViewById(R.id.btnUsuario);
        btnEmpresa = (Button) findViewById(R.id.btnEmpresa);

        btnEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Aqui deberia de ir el codigo que envie al usuario a la ventana de elegir que es, si conductor o administrador de empresa
                Intent vista = new Intent(PrimerVentana.this, tipo.class);
                startActivity(vista);
            }
        });

        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vista = new Intent(PrimerVentana.this, usuarioUI.class);
                startActivity(vista);
            }
        });
    }
}
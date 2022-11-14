package com.fjar.transporfast.ui.empresa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fjar.transporfast.R;

public class RecuperarUsuario extends AppCompatActivity {
    private TextView tvRecCorreo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_usuario);

        tvRecCorreo = (TextView) findViewById(R.id.recuperarcorreo);

        tvRecCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vista = new Intent(RecuperarUsuario.this, RecuperarCorreo.class);
                startActivity(vista);
            }
        });
    }
}
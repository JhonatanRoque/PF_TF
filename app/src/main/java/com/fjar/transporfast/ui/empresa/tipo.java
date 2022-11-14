package com.fjar.transporfast.ui.empresa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fjar.transporfast.PrimerVentana;
import com.fjar.transporfast.R;

public class tipo extends AppCompatActivity {
    private Button btnAdmnin, btnConductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo);
        btnAdmnin = (Button) findViewById(R.id.btnAdmin);
        btnConductor = (Button) findViewById(R.id.btnConductor);

        btnAdmnin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vista = new Intent(tipo.this, login.class);
                startActivity(vista);
            }
        });

        btnConductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vista = new Intent(tipo.this, loginConductor.class);
                startActivity(vista);
            }
        });

    }
}
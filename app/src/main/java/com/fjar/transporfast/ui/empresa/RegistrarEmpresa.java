package com.fjar.transporfast.ui.empresa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fjar.transporfast.R;

public class RegistrarEmpresa extends AppCompatActivity {
    private EditText nombre, telefono, correo, direccion, codigoPostal, contrasena;
    private EmpresaCRUD CRUD = new EmpresaCRUD();
    private EmpresaDTO dto = new EmpresaDTO();
    private TextView tvLogin;
    private Button btnRegEmpresa, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_empresa);

        nombre = (EditText) findViewById(R.id.edt_NomEmp);
        telefono = (EditText) findViewById(R.id.edt_NumEmp);
        correo = (EditText) findViewById(R.id.edt_correoEmp);
        direccion = (EditText) findViewById(R.id.edt_direccionEmp);
        codigoPostal = (EditText) findViewById(R.id.edt_codigoP);
        contrasena = (EditText) findViewById(R.id.edt_claveEmp);

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
                dto.setNombre(nombre.toString());
                dto.setTelefono(telefono.toString());
                dto.setCorreo(correo.toString());
                dto.setDireccion(direccion.toString());
                dto.setCodigopostal(codigoPostal.toString());
                dto.setContrasena(contrasena.toString());

                if(dto.getNombre().length() == 0){
                    nombre.setError("Campo obligatorio");
                }else if(dto.getTelefono().length() == 0){
                    telefono.setError("Campo obligatorio");
                }else if(dto.getCorreo().length() == 0){
                    correo.setError("Campo obligatorio");
                }else if(dto.getCodigopostal().length() == 0){
                    codigoPostal.setError("Campo obligatorio");
                }else if(dto.getDireccion().length() == 0){
                    direccion.setError("Campo obligatorio");
                }else if(dto.getContrasena().length() == 0){
                    contrasena.setError("Campo obligatorio");
                }else {
                    CRUD.registrarEmpresa(RegistrarEmpresa.this, dto);
                }
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
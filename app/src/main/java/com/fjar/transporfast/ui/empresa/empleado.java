package com.fjar.transporfast.ui.empresa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fjar.transporfast.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link empleado#newInstance} factory method to
 * create an instance of this fragment.
 */
public class empleado extends Fragment {

    private EditText nombre, apellido, telefono, correo, direccion;
    private Spinner rutaID, autoID;
    private Button btnRegistrar;
    private empleadoDTO empleadoObj = new empleadoDTO();
    private empleadoCRUD CRUD = new empleadoCRUD();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public empleado() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment empleado.
     */
    // TODO: Rename and change types and number of parameters
    public static empleado newInstance(String param1, String param2) {
        empleado fragment = new empleado();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_empleado, container, false);
        nombre = (EditText) root.findViewById(R.id.edt_Nombreempl);
        apellido = (EditText) root.findViewById(R.id.edt_Apellidoempl);
        telefono = (EditText) root.findViewById(R.id.edt_NumEmpl);
        correo = (EditText) root.findViewById(R.id.edt_correoEmpl);
        direccion = (EditText) root.findViewById(R.id.edt_direccionEmpl);
        btnRegistrar = (Button) root.findViewById(R.id.guardarempl);
        rutaID = (Spinner) root.findViewById(R.id.rutaID);
        autoID = (Spinner) root.findViewById(R.id.autoID);


        rutaID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if(position > 0){
                    String item = rutaID.getSelectedItem().toString();
                    empleadoObj.setRutaID(Integer.parseInt(item));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        autoID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if (position > 0) {
                    String item = autoID.getSelectedItem().toString();
                    empleadoObj.setAutoID((Integer.parseInt(item)));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nombre.length() == 0){
                    nombre.setError("Campo obligatorio");
                }else if(apellido.length() == 0){
                    apellido.setError("Campo obligatorio");
                }else if(telefono.length() == 0){
                    telefono.setError("Campo obligatorio");
                }else if(correo.length() == 0){
                    correo.setError("Campo obligatorio");
                }else if(direccion.length() == 0){
                    direccion.setError("Campo obligatorio");
                }else if(rutaID.getSelectedItemPosition() == 0){
                    Toast.makeText(getContext(), "Debe asignar una ruta al empleado", Toast.LENGTH_SHORT).show();

                }else if(autoID.getSelectedItemPosition() == 0){
                    Toast.makeText(getContext(), "Debe asignar un bus al empleado", Toast.LENGTH_SHORT).show();

                }else{
                    empleadoObj.setNombre(nombre.getText().toString());
                    empleadoObj.setApellido(apellido.getText().toString());
                    empleadoObj.setTelefono(telefono.getText().toString());
                    empleadoObj.setCorreo(correo.getText().toString());
                    empleadoObj.setDireccion(direccion.getText().toString());
                    empleadoObj.setEmpresaID(1);
                    CRUD.registarEmpleado(getContext(), empleadoObj );
                }
            }
        });

        return root;
    }
}
package com.fjar.transporfast.ui.empresa;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fjar.transporfast.MainActivity;
import com.fjar.transporfast.MySingleton;
import com.fjar.transporfast.R;
import com.fjar.transporfast.ui.RutaDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class rutas extends Fragment {

    TextView tvRutas;
    EditText etRutas;
    Button btnGuardar, btnEliminar, btnModificar;
    boolean [] selectedRuta;
    ArrayList<Integer> rutaList = new ArrayList<>();
    String[] rutaArray = {"3LP", "4LP", "5LP"};
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public rutas() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static rutas newInstance(String param1, String param2) {
        rutas fragment = new rutas();
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
        View root = inflater.inflate(R.layout.fragment_rutas, container, false);
        tvRutas = root.findViewById(R.id.tv_rutas);

        selectedRuta = new boolean[rutaArray.length];

        tvRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        getContext()
                );

                builder.setTitle("Selected Ruta");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(rutaArray, selectedRuta, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            rutaList.add(i);

                            Collections.sort(rutaList);
                        }else {
                            rutaList.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for(int j=0; j<rutaList.size(); j++) {
                            stringBuilder.append(rutaArray[rutaList.get(j)]);
                            if(j != rutaList.size()-1){
                                stringBuilder.append(",");
                            }
                        }
                        tvRutas.setText(stringBuilder.toString());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for(int j=0; j<selectedRuta.length; j++){
                            selectedRuta[j] = false;

                            rutaList.clear();

                            tvRutas.setText("");
                        }
                    }
                });
                builder.show();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etRutas.getText().toString();
                String spinner = tvRutas.getText().toString();


                if(id.length() == 0){
                    etRutas.setError("Campo obligatotio");
                }else if (spinner.length() == 0){
                    tvRutas.setError("Campo obligatorio");
                }
            }
        });
        return root;
    }

    //Método para obtener todos los productos
    public void obtenerUsuariosLista (final Context context, ListView lv) {
        //Creamos un array en el cual guardaremos cada una de los datos que vendran de nuestra API
        ArrayList<String> usuarios = new ArrayList<String>();

        String url = "https://franciscowebtw.000webhostapp.com/service2020/obtenerUsuarios.php";
        StringRequest request = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray peticionJSON = new JSONArray(response.toString());
                    usuarios.add("NOMBRE - ID ");
                    for (int i = 0; i < peticionJSON.length(); i ++){
                        JSONObject requestJSON = peticionJSON.getJSONObject(i);
                        RutaDTO usuariotmp = new RutaDTO();
                        String nombre = requestJSON.getString("nombre");
                        String Id = requestJSON.getString("Id");
                        Log.e("guarda daatos", "si");
                        //Añadimos los datos a nuestro objeto usuario
                        usuariotmp.setNombre(nombre);
                        usuariotmp.setId(Id);


                        usuarios.add(usuariotmp.getNombre() + " - " + usuariotmp.getId());

                        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, usuarios);
                        lv.setAdapter(adaptador);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "No se pudo obtener los usuarios \n" +"Intentelo más tarde.", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                //En este método se colocan o se setean los valores a recibir por el fichero *.php
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(request);

    }
}
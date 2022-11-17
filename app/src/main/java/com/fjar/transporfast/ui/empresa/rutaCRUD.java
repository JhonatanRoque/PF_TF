package com.fjar.transporfast.ui.empresa;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fjar.transporfast.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class rutaCRUD {
    public void obtenerRutaSpinner(final Context context, Spinner spin){
        ArrayList<String> rutas = new ArrayList<>();
        String url ="https://transporfast.xyz/transportfast/listarRutas.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray requestJSON = new JSONArray(response.toString());
                    rutas.add("Seleccione su ruta");
                    for (int i = 0; i < requestJSON.length(); i++) {
                        JSONObject requestobjectJSON = requestJSON.getJSONObject(i);
                        String id = requestobjectJSON.getString("id");
                        String nombre = requestobjectJSON.getString("nombre");

                        rutas.add(id + " - " +  nombre );
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, rutas);
                    spin.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "No se pudo obtener la lsita de empleado\n"+"Intentelo mas tarde.", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}

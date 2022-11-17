package com.fjar.transporfast.ui;

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
import com.fjar.transporfast.ui.empresa.empleadoDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RutaCRUD {
    private RutaDTO rutaObj;

    public RutaCRUD(){

    }
    public void obtenerRutaSpinner(final Context context, Spinner spin, RutaDTO rutas){
        ArrayList<String> ruta = new ArrayList<>();
        String url ="https://transporfast.xyz/transportfast/rutas.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray requestJSON = new JSONArray(response.toString());
                    ruta.add("Seleccione una ruta");
                    for (int i = 0; i < requestJSON.length(); i++) {
                        JSONObject requestobjectJSON = requestJSON.getJSONObject(i);
                        String nombre = requestobjectJSON.getString("nombre");

                        ruta.add(nombre);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, ruta);
                    spin.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "No se pudo obtener la lista de rutas\n"+"Intentelo mas tarde.", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(rutas.getId()));
                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(request);

}
}

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BusesCRUD {
    private BusesDTO BuseObj;

    public BusesCRUD(){

    }
    public void obtenerBusesSpinner(final Context context, Spinner spin, BusesDTO buses){
        ArrayList<String> bus = new ArrayList<>();
        String url ="https://transporfast.xyz/transportfast/buses.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray requestJSON = new JSONArray(response.toString());
                    bus.add("Seleccione un Bus");
                    for (int i = 0; i < requestJSON.length(); i++) {
                        JSONObject requestobjectJSON = requestJSON.getJSONObject(i);
                        String marca = requestobjectJSON.getString("marca");
                        String Nplaca = requestobjectJSON.getString("NumeroPlaca");

                        bus.add(marca);
                        bus.add(Nplaca);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, bus);
                    spin.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "No se pudo obtener la lista de buses\n"+"Intentelo mas tarde.", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(buses.getId()));
                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(request);

    }
}

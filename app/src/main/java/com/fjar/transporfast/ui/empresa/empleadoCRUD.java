package com.fjar.transporfast.ui.empresa;

import android.content.Context;
import android.util.Log;
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

public class empleadoCRUD {

    private empleadoDTO empleadoObj;

    public empleadoCRUD(){

    }

    //Registrar empleado
    public void registarEmpleado(final Context context, empleadoDTO empleado) {
        String url = "https://transporfast.xyz/transportfast/regEmpleado.php";
        StringRequest request = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject requestJSON = new JSONObject(response.toString());
                    String estado = requestJSON.getString("estado");
                    String mensaje = requestJSON.getString("mensaje");
                    if(estado.equals("1")){
                        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(context, "Registro almacenado en MySQL.", Toast.LENGTH_SHORT).show();
                    }else if(estado.equals("2")){
                        Toast.makeText(context, "Error: "+mensaje, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "No se pudo guardar. \n" +"Intentelo más tarde.", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                //En este método se colocan o se setean los valores a recibir por el fichero *.php
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                map.put("nombre", empleado.getNombre());
                map.put("apellido", empleado.getApellido());
                map.put("telefono", empleado.getTelefono());
                map.put("correo", empleado.getCorreo());
                map.put("direccion", empleado.getDireccion());
                map.put("empresa", String.valueOf(empleado.getEmpresaID()));
                map.put("ruta", String.valueOf(empleado.getRutaID()));
                map.put("auto", String.valueOf(empleado.getAutoID()));
                return map;
            }
        };
        Log.e("URL", request.getUrl().toString());
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    //Enviar datos de ubicación a la api
    public void setUbicacion(final Context context, empleadoDTO empleado) {
        String url = "https://transporfast.xyz/transportfast/setUbicacionConductor.php";
        StringRequest request = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject requestJSON = new JSONObject(response.toString());
                    String estado = requestJSON.getString("estado");
                    String mensaje = requestJSON.getString("mensaje");
                    if(estado.equals("1")){
                        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(context, "Registro almacenado en MySQL.", Toast.LENGTH_SHORT).show();
                    }else if(estado.equals("2")){
                        Toast.makeText(context, "Error: "+mensaje, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "No se pudo guardar. \n" +"Intentelo más tarde.", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                //En este método se colocan o se setean los valores a recibir por el fichero *.php
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                map.put("idEmpleado", String.valueOf(empleado.getId()));
                map.put("latitud", empleado.getLatitud());
                map.put("longitud", empleado.getLongitud());
                return map;
            }
        };
        Log.e("URL", request.getUrl().toString());
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
    public void obtenerempleadoSpinner(final Context context, Spinner spin, empleadoDTO conductor){
        ArrayList<String> empleado = new ArrayList<>();
        String url ="https://transporfast.xyz/transportfast/obtenerEmpleados.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray requestJSON = new JSONArray(response.toString());
                    empleado.add("Seleccione un empleado");
                    for (int i = 0; i < requestJSON.length(); i++) {
                        JSONObject requestobjectJSON = requestJSON.getJSONObject(i);
                        String nombre = requestobjectJSON.getString("nombre");
                        String apellido = requestobjectJSON.getString("apellido");
                        String correo = requestobjectJSON.getString("correo");

                        empleado.add(nombre + " - " + apellido + " - " + correo);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, empleado);
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
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> map = new HashMap<>();
                map.put("empresaID", String.valueOf(conductor.getEmpresaID()));
                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}

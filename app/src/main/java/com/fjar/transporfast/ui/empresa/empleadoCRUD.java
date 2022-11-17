package com.fjar.transporfast.ui.empresa;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fjar.transporfast.MySingleton;
import com.fjar.transporfast.R;

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
    public void IniciarSesionempleado(final Context context, empleadoDTO emplea, Switch mantener) {
        String url = "";
        StringRequest request = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject requestJSON = new JSONObject(response.toString());
                    if(requestJSON.has("mensaje") == false){
                        String id = requestJSON.getString("id");
                        String nombre = requestJSON.getString("nombre");
                        String correo = requestJSON.getString("correo");

                        if(id.length() > 0){
                            Toast.makeText(context, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
                            SharedPreferences spEmpresa = context.getSharedPreferences("empresa", context.MODE_PRIVATE);
                            String estado = "logON";
                            SharedPreferences.Editor editor = spEmpresa.edit();
                            editor.putString("estado", estado);
                            //Si se establecio la opcion de mantener iniciada sesion
                            if(mantener.isChecked()){

                                editor.putString("id", id);


                            }
                            editor.putString("nickName", nombre);
                            editor.commit();

                        }else {
                            Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        String mensaje = requestJSON.getString("mensaje");
                        Toast.makeText(context, "" + mensaje, Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "No se pudo iniciar session. \n" +"Intentelo más tarde." + volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                //En este método se colocan o se setean los valores a recibir por el fichero *.php
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                map.put("correo", emplea.getCorreo());
                map.put("contrasena", emplea.getContrasena());


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
    public void guardarempleado(final Context context, empleadoDTO empleado ) {
        String url = "";
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
    public void eliminarempleado(final Context context, empleadoDTO empleado) {
        String url = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_baseline_delete_24);
        builder.setTitle("Warning");
        builder.setMessage("¿Esta seguro de borrar el registro? \n Código:" +
                empleado.getId());
        builder.setCancelable(false);
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject requestJSON = new JSONObject(response.toString());
                            String estado = requestJSON.getString("estado");
                            String mensaje = requestJSON.getString("mensaje");
                            if (estado.equals("1")) {
                                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(context, "Registro almacenado en MySQL.", Toast.LENGTH_SHORT).show();
                            } else if (estado.equals("2")) {
                                Toast.makeText(context, "Error" + mensaje, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context, "No se pudo eliminar. \n" + "Intentelo más tarde.", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    protected Map<String, String> getParams() throws AuthFailureError {
                        //En este método se colocan o se setean los valores a recibir por el fichero *.php
                        Map<String, String> map = new HashMap<>();
                        map.put("Content-Type", "application/json; charset=utf-8");
                        map.put("Accept", "application/json");
                        map.put("id", String.valueOf(empleado.getId()));
                        return map;
                    }
                };
                MySingleton.getInstance(context).addToRequestQueue(request);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();;

    }

}

package com.fjar.transporfast.ui.empresa;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fjar.transporfast.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EmpresaCRUD {

    private EmpresaDTO empresaObj;

    public void registrarEmpresa(final Context context, EmpresaDTO empresa) {
        String url = "https://transporfast.xyz/transportfast/registrarEmpresa.php";
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
                    }else{
                        Toast.makeText(context, "Error: "+mensaje, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "No se pudo registrar. \n" +"Intentelo más tarde.", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                //En este método se colocan o se setean los valores a recibir por el fichero *.php
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                map.put("nombres", empresa.getNombre());
                map.put("telefono", empresa.getTelefono());
                map.put("correo", empresa.getCorreo());
                map.put("direccion", empresa.getDireccion());
                map.put("codigoPostal", empresa.getCodigopostal());
                map.put("contrasena", empresa.getContrasena());
                return map;
            }
        };
        Log.e("URL", request.getUrl().toString());
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
    public void IniciarSesionempl(final Context context, EmpresaDTO empleado, Switch mantener) {
<<<<<<< HEAD
        String url = "https://transporfast.xyz/transportfast/iniciarSesionEmpresa.php";
=======
        String url = "";
>>>>>>> 45492ade57719ed2d16716bf49d3f3c335e199dc
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
                            editor.putString("correo", correo);
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
                    map.put("empresa", empleado.getNombre());
                    map.put("contrasena", empleado.getContrasena());


                return map;
            }
        };
        Log.e("URL", request.getUrl().toString());
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

        public void ValidarCodigo(final Context context, EmpresaDTO empresa, final String codigo) {
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
                        }else{
                            Toast.makeText(context, "Error: "+mensaje, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(context, "No se pudo obtener el codigo. \n" +"Intentelo más tarde.", Toast.LENGTH_SHORT).show();
                }
            }) {
                protected Map<String, String> getParams() throws AuthFailureError {
                    //En este método se colocan o se setean los valores a recibir por el fichero *.php
                    Map<String, String> map = new HashMap<>();
                    map.put("Content-Type", "application/json; charset=utf-8");
                    map.put("Accept", "application/json");
                    map.put("codigo", codigo);

                    return map;
                }
            };
            Log.e("URL", request.getUrl().toString());
            MySingleton.getInstance(context).addToRequestQueue(request);

    }
}

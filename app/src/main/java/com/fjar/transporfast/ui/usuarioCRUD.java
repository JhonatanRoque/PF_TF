package com.fjar.transporfast.ui;



import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fjar.transporfast.MySingleton;
import com.fjar.transporfast.R;
import com.fjar.transporfast.ui.empresa.empleadoDTO;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import androidx.fragment.app.FragmentActivity;



import com.google.android.gms.maps.model.CameraPosition;

public class usuarioCRUD extends FragmentActivity{

    public usuarioCRUD(){

    }

    public void obtenerbuses(final Context context, GoogleMap map, empleadoDTO conductor){

        String url ="https://transporfast.xyz/transportfast/obtenerUbicacionBuses.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray requestJSON = new JSONArray(response.toString());
                    LatLng ubicacion = new LatLng(0,0);
                    map.clear();
                    for (int i = 0; i < requestJSON.length(); i++) {
                        JSONObject requestobjectJSON = requestJSON.getJSONObject(i);
                        String nombre = requestobjectJSON.getString("nombre");
                        String apellido = requestobjectJSON.getString("apellido");
                        Double latitud = Double.parseDouble(requestobjectJSON.getString("latitud"));
                        Double longitud = Double.parseDouble(requestobjectJSON.getString("longitud"));

                        Toast.makeText(context, "Ubicacion: " + latitud + longitud, Toast.LENGTH_SHORT).show();


                        ubicacion = new LatLng(latitud, longitud);
                        map.addMarker(new MarkerOptions().title("Unidad " + nombre).position(ubicacion).icon(vectorToBitmap(R.drawable.bus, Color.parseColor("#A4C639"), context)));

                    }
                    CameraPosition cameraP = new CameraPosition.Builder()
                            .target(ubicacion)
                            .zoom(14)
                            .build();
                    map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraP));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "No se pudo obtener la ubicacion de los buses\n"+"Intentelo mas tarde.", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("rutaID", String.valueOf(conductor.getRutaID()));
                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public void obtenerParadas(final Context context, GoogleMap map, empleadoDTO conductor){

        String url ="https://transporfast.xyz/transportfast/listarParadasRuta.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray requestJSON = new JSONArray(response.toString());
                    LatLng ubicacion = new LatLng(0,0);
                    for (int i = 0; i < requestJSON.length(); i++) {
                        JSONObject requestobjectJSON = requestJSON.getJSONObject(i);
                        String nombre = requestobjectJSON.getString("nombre");
                        Double latitud = Double.parseDouble(requestobjectJSON.getString("latitud"));
                        Double longitud = Double.parseDouble(requestobjectJSON.getString("longitud"));

                        Toast.makeText(context, "Ubicacion: " + latitud + longitud, Toast.LENGTH_SHORT).show();


                        ubicacion = new LatLng(latitud, longitud);
                        MarkerOptions markOpt = new MarkerOptions().title("Parada  " + nombre).position(ubicacion).icon(vectorToBitmap(R.drawable.parada, Color.parseColor("#A4C639"), context));
                        map.addMarker(markOpt);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "No se pudo obtener la ubicacion de las paradas"+"Intentelo mas tarde.", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("rutaID", String.valueOf(conductor.getRutaID()));
                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public BitmapDescriptor vectorToBitmap(@DrawableRes int id, @ColorInt int color, Context context) {

        Drawable vectorDrawable = ResourcesCompat.getDrawable(context.getResources(), id, null);
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        DrawableCompat.setTint(vectorDrawable, color);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

}

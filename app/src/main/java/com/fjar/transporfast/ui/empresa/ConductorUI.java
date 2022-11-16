package com.fjar.transporfast.ui.empresa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fjar.transporfast.PrimerVentana;
import com.fjar.transporfast.R;
import com.fjar.transporfast.ui.usuarioUI;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ConductorUI extends AppCompatActivity implements  GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback {
    private Button btnIniciar, btnFinalizar;
    private Boolean bandera = false;
    private GoogleMap mMap;
    private empleadoDTO empleadoObj = new empleadoDTO();
    private empleadoCRUD CRUD = new empleadoCRUD();
    int i = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conductor_ui);

        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnFinalizar = (Button) findViewById(R.id.btnFinalizar);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bandera = true;




                        String longtud = getLongitud();
                        String latitud = getLatitud();

                        String lastLat = latitud;
                        String lastLongi = longtud;

                        if(lastLongi == longtud){

                        }



                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                int i = 0;
                                while(bandera) {
                                    try {
                                        Thread.sleep(1500);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                getUbicacion();
                                            }
                                        });
                                        empleadoObj.setId(1);
                                        CRUD.setUbicacion(getBaseContext(), empleadoObj);

                                        Log.e("mesnaje", empleadoObj.getLongitud() + empleadoObj.getLatitud());
                                        i++;
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                        };


                        Thread hilo = new Thread(runnable);
                        hilo.start();


                }


        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bandera = false;
            }
        });



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(ConductorUI.this);
    }

    private void getUbicacion(){

        empleadoObj.setLongitud(getLongitud() + i);
        empleadoObj.setLatitud(getLatitud() + i);
        i++;

    }

    private String getLongitud ()
    {
        String longtud = String.valueOf(mMap.getMyLocation().getLongitude());
        longtud += 1;
        return longtud;
    }

    private String getLatitud ()
    {
        String latitud = String.valueOf(mMap.getMyLocation().getLatitude());
        latitud += 1;
        return latitud;
    }
    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(ConductorUI.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ConductorUI.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(ConductorUI.this, Manifest.permission.ACCESS_FINE_LOCATION)) && ActivityCompat.shouldShowRequestPermissionRationale(ConductorUI.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(ConductorUI.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            return;
        }
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationClickListener(ConductorUI.this);




        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        MarkerOptions options = new MarkerOptions().position(sydney).title("Marker in Sydney");
        mMap.addMarker(options);

    }



    public void miPosicion() {
        LocationManager lmanager = (LocationManager) ConductorUI.this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener lLIstener = new LocationListener() {

            @Override
            public void onLocationChanged(@NonNull Location location) {
                int i = 0;
                LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
                Toast.makeText(ConductorUI.this, "Su ubicacion actual es: " + miUbicacion.toString(), Toast.LENGTH_SHORT).show();
                mMap.addMarker(new MarkerOptions().position(miUbicacion).title("Mi ubicación" + i)); i++;
                mMap.moveCamera(CameraUpdateFactory.newLatLng(miUbicacion));
                CameraPosition cameraP = new CameraPosition.Builder()
                        .target(miUbicacion)
                        .zoom(14)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraP));

            }

            @Override
            public void onLocationChanged(@NonNull List<Location> locations) {
                LocationListener.super.onLocationChanged(locations);
            }

            @Override
            public void onFlushComplete(int requestCode) {
                LocationListener.super.onFlushComplete(requestCode);
            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {
                LocationListener.super.onProviderEnabled(provider);
                Toast.makeText(ConductorUI.this, "Proveedor GPS disponible", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                LocationListener.super.onProviderDisabled(provider);

                Toast.makeText(ConductorUI.this, "Proveedor GPS no disponible", Toast.LENGTH_SHORT).show();
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if((ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)){

            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            return;
        }
        lmanager.requestLocationUpdates(lmanager.GPS_PROVIDER, 100, 1, lLIstener);
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
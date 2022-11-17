package com.fjar.transporfast.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.fjar.transporfast.R;
import com.fjar.transporfast.ui.empresa.empleadoDTO;
import com.fjar.transporfast.ui.empresa.rutaCRUD;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.maps.DirectionsApi;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;

public class usuarioUI extends AppCompatActivity implements OnMapReadyCallback {

    private Button btnMiPosicion;
    private Spinner spnRutas;
    private rutaCRUD rutaCRUD = new rutaCRUD();
    private usuarioCRUD usuCRUD = new usuarioCRUD();
    boolean bandera = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_ui);

        btnMiPosicion = (Button) findViewById(R.id.btnMiPosicion);
        spnRutas = (Spinner) findViewById(R.id.spnFiltrarRutas);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(usuarioUI.this);

        rutaCRUD.obtenerRutaSpinner(getApplicationContext(), spnRutas);

        spnRutas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = spnRutas.getSelectedItem().toString();
                if(position != 0){
                    String s[] = item.split("-");
                    empleadoDTO dtoruta = new empleadoDTO();
                    dtoruta.setRutaID(Integer.parseInt(s[0].trim()));
                    bandera = true;
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            int i = 0;
                            while(bandera) {
                                try {
                                    Thread.sleep(500);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {


                                            usuCRUD.obtenerbuses(getBaseContext(), mMap, dtoruta);
                                            usuCRUD.obtenerParadas(getBaseContext(), mMap, dtoruta);


                                        }
                                    });

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    };


                    Thread hilo = new Thread(runnable);
                    hilo.start();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
    private GoogleMap mMap;
    private DirectionsApi.Response direcciones;
    private DistanceMatrixApi.Response distancia;


    @Override
    protected void onStop() {
        super.onStop();
        bandera = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera = false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(usuarioUI.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(usuarioUI.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if((ActivityCompat.shouldShowRequestPermissionRationale(usuarioUI.this, Manifest.permission.ACCESS_FINE_LOCATION)) && ActivityCompat.shouldShowRequestPermissionRationale(usuarioUI.this, Manifest.permission.ACCESS_COARSE_LOCATION)){

            }else{
                ActivityCompat.requestPermissions(usuarioUI.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            return;
        }
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setMyLocationEnabled(true);




    }

    private void setMarker(GoogleMap map){

    }

}
package com.velvetropes.googlemapstest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap m_map;
    boolean mapReady = false;
    Button mapButton;
    Button satelliteButton;
    Button hybridButton;
    Button seattleButton;
    Button newYorkButton;
    Button dublinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapButton = (Button) findViewById(R.id.btnMap);
        satelliteButton = (Button) findViewById(R.id.btnSatellite);
        hybridButton = (Button) findViewById(R.id.btnHybrid);
        seattleButton = (Button) findViewById(R.id.seattle);
        newYorkButton = (Button) findViewById(R.id.newyork);
        dublinButton = (Button) findViewById(R.id.dublin);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady) {
                    m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });

        satelliteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady) {
                    m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        });

        hybridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady) {
                    m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            }
        });

        seattleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady) {
                    LatLng seattle = new LatLng(47.6204, -122.3491);
                    CameraPosition newTarget = CameraPosition.builder().target(seattle).zoom(14).build();
                    m_map.moveCamera(CameraUpdateFactory.newCameraPosition(newTarget));
                }
            }
        });

        newYorkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady) {
                    LatLng newYorkPosition = new LatLng(40.7127, -74.0059);
                    CameraPosition newTarget = CameraPosition.builder().target(newYorkPosition).zoom(14).build();
                    m_map.moveCamera(CameraUpdateFactory.newCameraPosition(newTarget));
                }
            }
        });

        dublinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady) {
                    LatLng dublinPosition = new LatLng(53.3478, 6.2597);
                    CameraPosition newTarget = CameraPosition.builder().target(dublinPosition).zoom(14).build();
                    m_map.moveCamera(CameraUpdateFactory.newCameraPosition(newTarget));
                }
            }
        });

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mapReady = true;
        m_map = map;
        LatLng newYork = new LatLng(40.7484, -73.9857);
        CameraPosition target = CameraPosition.builder().target(newYork).zoom(14).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
    }
}

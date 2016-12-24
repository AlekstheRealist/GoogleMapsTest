package com.velvetropes.googlemapstest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap m_map;
    boolean mapReady = false;
    Button mapButton;
    Button satelliteButton;
    Button hybridButton;
    Button seattleButton;
    Button newYorkButton;
    Button dublinButton;
    MarkerOptions newYorkMarker;
    MarkerOptions seattleMarker;
    MarkerOptions dublinMarker;

    static final CameraPosition NEWYORK = CameraPosition.builder().target(new LatLng(40.784,-73.9857))
            .zoom(21)
            .bearing(0)
            .tilt(45)
            .build();

    static final CameraPosition SEATTLE = CameraPosition.builder()
            .target(new LatLng(47.6204,-122.3491))
            .zoom(17)
            .bearing(0)
            .tilt(45)
            .build();

    static final CameraPosition DUBLIN = CameraPosition.builder()
            .target(new LatLng(53.3478,-6.2597))
            .zoom(17)
            .bearing(90)
            .tilt(45)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newYorkMarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)).position(new LatLng(40.784, -73.9857)).title("New York");
        seattleMarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)).position(new LatLng(47.6204, -122.3491)).title("Seattle");
        dublinMarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)).position(new LatLng(53.3478, -6.2597)).title("Dublin");

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
                    m_map.addMarker(seattleMarker);
                    flyTo(SEATTLE);
                }
            }
        });

        newYorkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady) {
                    m_map.addMarker(newYorkMarker);
                    flyTo(NEWYORK);
                }
            }
        });

        dublinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady) {
                    m_map.addMarker(dublinMarker);
                    flyTo(DUBLIN);
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

    private void flyTo(CameraPosition target) {
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 10000, null);
    }
}

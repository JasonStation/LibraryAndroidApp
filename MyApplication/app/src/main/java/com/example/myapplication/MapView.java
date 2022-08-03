package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapView extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap googleMaps;
    private String sTitle, sAuthor, sSynopsis, sRequester, sPhone;
    private int iCover;
    private double lat, longi;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.bookMapView);
        supportMapFragment.getMapAsync(this);

        Button requestBtn = findViewById(R.id.sendRequest);

        sTitle = getIntent().getStringExtra("dataTitle");
        sAuthor = getIntent().getStringExtra("dataAuthor");
        sSynopsis = getIntent().getStringExtra("dataSynopsis");
        sRequester = "Requester: " + MainActivity.emailAddress.get(MainActivity.loggedAccount);
        sPhone = MainActivity.phoneNumbers.get(MainActivity.loggedAccount);
        iCover = getIntent().getIntExtra("dataCover", 1);

        db = new DatabaseHelper(getApplicationContext());

        requestBtn.setOnClickListener(v -> {
            //RequestForm.numberOfRequests++;
            db.insertDataRequest(sTitle, sAuthor, sSynopsis, sRequester, "Receiver: -", lat, longi, sPhone);
            Toast.makeText(this, "Request sent " + Double.toString(lat) + " " + Double.toString(longi), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), MainForm.class);
            startActivity(i);
        });
    }

    @Override
    public void onMapReady(GoogleMap gMap) {
        googleMaps = gMap;
        googleMaps.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            public void onMapClick(LatLng ll){
                MarkerOptions mo = new MarkerOptions();
                mo.position(ll);
                mo.title("Latitude: " + ll.latitude + ", Longitude:" + ll.longitude);
                lat = ll.latitude;
                longi = ll.longitude;
                googleMaps.clear();
                googleMaps.animateCamera(CameraUpdateFactory.newLatLngZoom(ll, 20));
                googleMaps.addMarker(mo);
            }
        });
    }
}
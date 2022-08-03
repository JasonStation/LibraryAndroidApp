package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RequestDetailForm extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap googleMaps;
    double sLatitude, sLongitude;
    DatabaseHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_detail_form);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.requestMapView);
        supportMapFragment.getMapAsync(this);

        TextView bookTitle = findViewById(R.id.detailTitle2);
        TextView bookAuthor = findViewById(R.id.detailAuthor2);
        TextView bookSynopsis = findViewById(R.id.detailSynopsis2);
        ImageView bookImage = findViewById(R.id.detailCover2);
        TextView bookRequester = findViewById(R.id.requesterEmail);
        TextView bookReceiver = findViewById(R.id.receiverEmail);
        TextView smsForm = findViewById(R.id.SMSFormButton);
        Button accept = findViewById(R.id.acceptButton);

        String sTitle = getIntent().getStringExtra("dataTitle2");
        String sAuthor = getIntent().getStringExtra("dataAuthor2");
        String sSynopsis = getIntent().getStringExtra("dataSynopsis2");
        int iCover = getIntent().getIntExtra("dataCover2", 1);
        String sRequester = getIntent().getStringExtra("dataRequester");
        String sReceiver = getIntent().getStringExtra("dataReceiver");
        int sId = getIntent().getIntExtra("dataId", 0);
        sLatitude = getIntent().getDoubleExtra("dataLatitude", 0);
        sLongitude = getIntent().getDoubleExtra("dataLongitude", 0);
        String sPhone = getIntent().getStringExtra("dataPhoneNum");


        String comp = "Requester: " + MainActivity.emailAddress.get(MainActivity.userId.indexOf(MainActivity.loggedAccount));

        if(comp.equals(sRequester)){
            accept.setVisibility(View.INVISIBLE);
        }

        if(!sReceiver.equals("Receiver: -")){
            accept.setVisibility(View.INVISIBLE);
        }


        bookTitle.setText(sTitle);
        bookAuthor.setText(sAuthor);
        bookSynopsis.setText(sSynopsis);
        bookRequester.setText(sRequester);
        bookReceiver.setText(sReceiver);
        bookImage.setImageResource(iCover);

        database = new DatabaseHelper(getApplicationContext());

        accept.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Request accepted", Toast.LENGTH_SHORT).show();
            RequestForm.receiverEmail.set(RequestForm.bookIdRequest.indexOf(sId), "Receiver: " + MainActivity.emailAddress.get(MainActivity.userId.indexOf(MainActivity.loggedAccount)));
            //database.updateReceiver(MainActivity.emailAddress.get(MainActivity.loggedAccount), RequestForm.bookIdRequest.indexOf(sId));
            Intent i = new Intent(getApplicationContext(), RequestForm.class);
            startActivity(i);
        });

        smsForm.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), SMSForm.class);
            i.putExtra("dataPhoneNum", sPhone);
            startActivity(i);
        });
    }


    @Override
    public void onMapReady(GoogleMap gMap) {
        googleMaps = gMap;
        MarkerOptions mo = new MarkerOptions();
        LatLng ll = new LatLng(sLatitude, sLongitude);
        mo.position(ll);
        googleMaps.clear();
        googleMaps.animateCamera(CameraUpdateFactory.newLatLngZoom(ll, 15));
        googleMaps.addMarker(mo);
        Toast.makeText(getApplicationContext(), Double.toString(sLatitude) + " " + Double.toString(sLongitude), Toast.LENGTH_SHORT).show();
    }
}
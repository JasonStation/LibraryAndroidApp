package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

public class BookDetailForm extends AppCompatActivity {
    private TextView bookTitle;
    private TextView bookAuthor;
    private TextView bookSynopsis;
    private ImageView bookImage;
    private Button request;

    private String sTitle, sAuthor, sSynopsis;
    private int iCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail_form);

        bookTitle = findViewById(R.id.detailTitle);
        bookAuthor = findViewById(R.id.detailAuthor);
        bookSynopsis = findViewById(R.id.detailSynopsis);
        bookImage = findViewById(R.id.detailCover);
        request = findViewById(R.id.requestButton);

        sTitle = getIntent().getStringExtra("dataTitle");
        sAuthor = getIntent().getStringExtra("dataAuthor");
        sSynopsis = getIntent().getStringExtra("dataSynopsis");
        iCover = getIntent().getIntExtra("dataCover", 1);

        bookTitle.setText(sTitle);
        bookAuthor.setText(sAuthor);
        bookSynopsis.setText(sSynopsis);
        bookImage.setImageResource(iCover);

        request.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), MapView.class);
            i.putExtra("dataTitle", sTitle);
            i.putExtra("dataAuthor", sAuthor);
            i.putExtra("dataSynopsis", sSynopsis);
            i.putExtra("dataCover", iCover);
            startActivity(i);
        });
    }

}
package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class RequestForm extends AppCompatActivity {

    public static ArrayList<Integer> bookIdRequest = new ArrayList<Integer>();
    public static ArrayList<String> titleRequest = new ArrayList<String>();
    public static ArrayList<String> authorRequest = new ArrayList<String>();
    public static ArrayList<String> synopsisRequest = new ArrayList<String>();
    public static ArrayList<Integer> coverRequest = new ArrayList<Integer>();
    public static ArrayList<String> receiverEmail = new ArrayList<String>();
    public static ArrayList<String> requesterEmail = new ArrayList<String>();
    public static ArrayList<Double> requesterLatitude = new ArrayList<Double>();
    public static ArrayList<Double> requesterLongitude = new ArrayList<Double>();
    public static ArrayList<String> requesterPhone = new ArrayList<String>();
    public static int numberOfRequests = 0;
    public static RecyclerView rView2;
    DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_form);
        rView2 = findViewById(R.id.recyclerequest);
        TextView note = findViewById(R.id.emptyPlaceholder);

        bookIdRequest.clear();
        titleRequest.clear();
        authorRequest.clear();
        synopsisRequest.clear();
        requesterEmail.clear();
        receiverEmail.clear();
        coverRequest.clear();
        requesterLatitude.clear();
        requesterLongitude.clear();

        database = new DatabaseHelper(getApplicationContext());

        Cursor cur = database.viewDataRequest();

        if(cur.getCount() == 0) Toast.makeText(getApplicationContext(), "Nothing here.", Toast.LENGTH_SHORT).show();
        else{
            while(cur.moveToNext()){
                bookIdRequest.add(cur.getInt(0));
                titleRequest.add(cur.getString(1));
                String sTitle = cur.getString(1);
                authorRequest.add(cur.getString(2));
                synopsisRequest.add(cur.getString(3));
                requesterEmail.add(cur.getString(4));
                receiverEmail.add(cur.getString(5));
                requesterLatitude.add(cur.getDouble(6));
                requesterLongitude.add(cur.getDouble((7)));
                requesterPhone.add(cur.getString(8));
                //Toast.makeText(getApplicationContext(), String.valueOf(MainForm.bookCover.get(MainForm.bookTitle.indexOf(sTitle))), Toast.LENGTH_SHORT).show();
                coverRequest.add(MainForm.bookCover.get(MainForm.bookTitle.indexOf(sTitle)));
            }
        }

        if(!titleRequest.isEmpty()){
            note.setText("");
        }

        RecycleAdapter2 ra = new RecycleAdapter2(this, titleRequest, authorRequest, synopsisRequest,
                coverRequest, requesterEmail, receiverEmail, bookIdRequest, requesterLatitude, requesterLongitude, requesterPhone);
        rView2.setAdapter(ra);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);
        rView2.setLayoutManager(llm);
    }
}
package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import android.content.Context;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.Toast;

public class RecycleAdapter2 extends RecyclerView.Adapter<RecycleAdapter2.RecycleHolder> {
    public static ArrayList<Integer> dataCover2 = new ArrayList<Integer>();
    public static ArrayList<String> dataTitle2 = new ArrayList<String>();
    public static ArrayList<String> dataAuthor2 = new ArrayList<String>();
    public static ArrayList<String> dataSynopsis2 = new ArrayList<String>();
    public static ArrayList<String> dataRequester = new ArrayList<String>();
    public static ArrayList<String> dataReceiver = new ArrayList<String>();
    public static ArrayList<Double> dataLatitude = new ArrayList<Double>();
    public static ArrayList<Double> dataLongitude = new ArrayList<Double>();
    public static ArrayList<String> dataPhone = new ArrayList<String>();
    public static ArrayList<Integer> dataId = new ArrayList<Integer>();
    Context dataContext2;

    public RecycleAdapter2(Context context, ArrayList<String> bookTitle, ArrayList<String> bookAuthor,
                          ArrayList<String> bookSynopsis, ArrayList<Integer> bookCover, ArrayList<String> requester,
                           ArrayList<String> receiver, ArrayList<Integer> id, ArrayList<Double> latitude,
                           ArrayList<Double> longitude, ArrayList<String> phone){
        dataContext2 = context;
        dataTitle2 = bookTitle;
        dataAuthor2 = bookAuthor;
        dataSynopsis2 = bookSynopsis;
        dataCover2 = bookCover;
        dataReceiver = receiver;
        dataRequester = requester;
        dataId = id;
        dataLatitude = latitude;
        dataLongitude = longitude;
        dataPhone = phone;

    }

    @NonNull
    @Override
    public RecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(dataContext2);
        View rv = inf.inflate(R.layout.recycleviewrequest, parent, false);
        return new RecycleHolder(rv);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleHolder holder, final int position) {
        holder.textTitle.setText(dataTitle2.get(position));
        holder.textAuthor.setText(dataAuthor2.get(position));
        holder.imageCover.setImageResource(dataCover2.get(position));
        holder.textReceiver.setText(dataReceiver.get(position));
        holder.textRequester.setText(dataRequester.get(position));
        holder.layoutCon.setOnClickListener(v -> {
            Intent i = new Intent(dataContext2, RequestDetailForm.class);
            i.putExtra("dataId", dataId.get(position));
            i.putExtra("dataTitle2", dataTitle2.get(position));
            i.putExtra("dataAuthor2", dataAuthor2.get(position));
            i.putExtra("dataSynopsis2", dataSynopsis2.get(position));
            i.putExtra("dataCover2", dataCover2.get(position));
            i.putExtra("dataReceiver", dataReceiver.get(position));
            i.putExtra("dataRequester", dataRequester.get(position));
            i.putExtra("dataLatitude", dataLatitude.get(position));
            i.putExtra("dataLongitude", dataLongitude.get(position));
            i.putExtra("dataPhoneNum", dataPhone.get(position));
            dataContext2.startActivity(i);

        });
    }

    @Override
    public int getItemCount() {
        return dataTitle2.size();
    }

    public class RecycleHolder extends RecyclerView.ViewHolder{
        TextView textTitle, textAuthor, textSynopsis, textReceiver, textRequester;
        ImageView imageCover;
        ConstraintLayout layoutCon;
        public RecycleHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.bookTitle2);
            textAuthor = itemView.findViewById(R.id.bookAuthor2);
            imageCover = itemView.findViewById(R.id.bookCover2);
            textRequester = itemView.findViewById(R.id.bookRequester);
            textReceiver = itemView.findViewById(R.id.bookReceiver);
            layoutCon = itemView.findViewById(R.id.bookView2);
        }
    }

}
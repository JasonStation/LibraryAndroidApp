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

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecycleHolder> {
    public static ArrayList<Integer> dataCover = new ArrayList<Integer>();
    public static ArrayList<String> dataTitle = new ArrayList<String>();
    public static ArrayList<String> dataAuthor = new ArrayList<String>();
    public static ArrayList<String> dataSynopsis = new ArrayList<String>();
    Context dataContext;

    public RecycleAdapter(Context context, ArrayList<String> bookTitle, ArrayList<String> bookAuthor,
                          ArrayList<String> bookSynopsis, ArrayList<Integer> bookCover){
        dataContext = context;
        dataTitle = bookTitle;
        dataAuthor = bookAuthor;
        dataSynopsis = bookSynopsis;
        dataCover = bookCover;

    }

    @NonNull
    @Override
    public RecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(dataContext);
        View rv = inf.inflate(R.layout.recycleviewbook, parent, false);
        return new RecycleHolder(rv);
    }


    public void onBindViewHolder(@NonNull RecycleHolder holder, final int position) {
        holder.textTitle.setText(dataTitle.get(position));
        holder.textAuthor.setText(dataAuthor.get(position));
        holder.textSynopsis.setText(dataSynopsis.get(position));
        holder.imageCover.setImageResource(dataCover.get(position));
        holder.layoutCon.setOnClickListener(v -> {
            Intent i = new Intent(dataContext, BookDetailForm.class);
            i.putExtra("dataTitle", dataTitle.get(position));
            i.putExtra("dataAuthor", dataAuthor.get(position));
            i.putExtra("dataSynopsis", dataSynopsis.get(position));
            i.putExtra("dataCover", dataCover.get(position));
            dataContext.startActivity(i);

        });
    }

    @Override
    public int getItemCount() {
        return dataTitle.size();
    }

    public class RecycleHolder extends RecyclerView.ViewHolder{
        TextView textTitle, textAuthor, textSynopsis;
        ImageView imageCover;
        ConstraintLayout layoutCon;
        public RecycleHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.bookTitle);
            textAuthor = itemView.findViewById(R.id.bookAuthor);
            textSynopsis = itemView.findViewById(R.id.bookSynopsis);
            imageCover = itemView.findViewById(R.id.bookCover);
            layoutCon = itemView.findViewById(R.id.bookView);
        }
    }

}

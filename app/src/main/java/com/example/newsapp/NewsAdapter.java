package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    Context context;
    ArrayList<Articles> articlesArrayList;

    public NewsAdapter(Context context, ArrayList<Articles> articlesArrayList) {
        this.context = context;
        this.articlesArrayList = articlesArrayList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sample_row,parent,false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Articles articles = articlesArrayList.get(position);
    holder.title.setText(articles.getTitle());
    holder.description.setText(articles.getDescription());
    holder.publish.setText("Published At:-"+articles.getPublishedAt());
        Glide.with(context).load(articles.getUrlToImage()).into(holder.image);



    holder.cardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context,webView.class);
            intent.putExtra("url",articles.getUrl());
            context.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView title,description,publish;
        ImageView image;
        CardView cardView;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.ititle);
            description = itemView.findViewById(R.id.idescprition);
            publish = itemView.findViewById(R.id.ipublish);
            image = itemView.findViewById(R.id.newsimage);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}

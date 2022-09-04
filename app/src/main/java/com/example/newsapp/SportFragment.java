package com.example.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportFragment extends Fragment {
    ArrayList<Articles> articlesArrayList ;
    private RecyclerView recyclerViewofsport;
    NewsAdapter newsAdapter;
    private final String api = "d7f31af5cf65419fa1bbe51386f842e9";
    String country = "in";
    private String category = "sports";
    ShimmerFrameLayout shimmerFrameLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sportfragement,null);
        articlesArrayList = new ArrayList<>();
        recyclerViewofsport = v.findViewById(R.id.recycleofsport);
        shimmerFrameLayout = v.findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();
        recyclerViewofsport.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(getContext(),articlesArrayList);
        recyclerViewofsport.setAdapter(newsAdapter);
        findNews();
        return v;
    }

    private void findNews() {
        ApiUtilies.getApiInterface().getCateNews(country,category,100,api).enqueue(new Callback<POJO>() {
            @Override
            public void onResponse(Call<POJO> call, Response<POJO> response) {
                if(response.isSuccessful()){
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                    recyclerViewofsport.setVisibility(View.VISIBLE);
                    articlesArrayList.addAll(response.body().getArticles());
                    newsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<POJO> call, Throwable t) {

            }
        });
    }
}

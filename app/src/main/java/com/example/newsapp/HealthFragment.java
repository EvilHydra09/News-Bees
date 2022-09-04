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

public class HealthFragment extends Fragment {
    ArrayList<Articles> articlesArrayList ;
    private RecyclerView recyclerViewofhealth;
    NewsAdapter newsAdapter;
    private final String api = "d7f31af5cf65419fa1bbe51386f842e9";
    String country = "in";
    private String category = "health";
    ShimmerFrameLayout shimmerFrameLayout ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.healthfragment,null);
        recyclerViewofhealth = v.findViewById(R.id.recycleofenter);
        articlesArrayList = new ArrayList<>();
        shimmerFrameLayout = v.findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();
        recyclerViewofhealth.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(getContext(),articlesArrayList);
        recyclerViewofhealth.setAdapter(newsAdapter);
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
                    recyclerViewofhealth.setVisibility(View.VISIBLE);
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

package com.example.newsapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

public class ScienceFragment extends Fragment {
    ArrayList<Articles> articlesArrayList ;
    private RecyclerView recyclerViewofscience;
    NewsAdapter newsAdapter;
    String country = "in";
    private String category = "science";
    ShimmerFrameLayout shimmerFrameLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sciencefragment,null);
        recyclerViewofscience = v.findViewById(R.id.recycleofscience);
        articlesArrayList = new ArrayList<>();
        shimmerFrameLayout = v.findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();
        recyclerViewofscience.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(getContext(),articlesArrayList);
        recyclerViewofscience.setAdapter(newsAdapter);

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                findNews();
            }
        };
        handler.postDelayed(runnable,1000);
        return v;
    }

    private void findNews() {
        ApiUtilies.getApiInterface().getCateNews(country,category,100,HomePage.api).enqueue(new Callback<POJO>() {
            @Override
            public void onResponse(Call<POJO> call, Response<POJO> response) {
                if(response.isSuccessful()){
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                    recyclerViewofscience.setVisibility(View.VISIBLE);
                    articlesArrayList.addAll(response.body().getArticles());
                    newsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<POJO> call, Throwable t) {
                Toast.makeText(getContext(), "Please Connect The Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

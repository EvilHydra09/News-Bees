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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {


    ArrayList<Articles> articlesArrayList ;
    private RecyclerView recyclerViewofhome;
    NewsAdapter newsAdapter;

    String country = "in";
    ShimmerFrameLayout shimmerFrameLayout;

    SwipeRefreshLayout swipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.homefragment,null);
        articlesArrayList = new ArrayList<>();
        recyclerViewofhome = v.findViewById(R.id.recycleofhome);


        shimmerFrameLayout = v.findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();


        recyclerViewofhome.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(getContext(),articlesArrayList);
        recyclerViewofhome.setAdapter(newsAdapter);


        // For Post Delay the Method
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                findnew();
            }
        };
        handler.postDelayed(runnable,1000);

        swipeRefreshLayout = v.findViewById(R.id.refreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                newsAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return v;
    }



    private void findnew() {
        ApiUtilies.getApiInterface().getNews(country,100,HomePage.api).enqueue(new Callback<POJO>() {
            @Override
            public void onResponse(Call<POJO> call, Response<POJO> response) {
                if(response.isSuccessful()){

                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                    recyclerViewofhome.setVisibility(View.VISIBLE);
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

package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.newsapp.databinding.ActivityHomePageBinding;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {
Toolbar toolbar;
PagerAdapter pagerAdapter;
TabLayout tabLayout;
TabItem mhome,msport,mhealth,mscience,menterntainment,mtechnology;
    ViewPager viewPager;

private final String api = "d7f31af5cf65419fa1bbe51386f842e9";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        // ID of Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // Use to set custom action;
        // ID of Tab Item
        mhome = findViewById(R.id.home);
        msport = findViewById(R.id.sport);
        mhealth = findViewById(R.id.health);
        mscience = findViewById(R.id.science);
        menterntainment = findViewById(R.id.enterntainment);
        mtechnology = findViewById(R.id.technology);
        // ID of View Pager and Tablayout.
        viewPager = findViewById(R.id.fragmentcontainers);
        tabLayout = findViewById(R.id.include);

        // Object of PagerAdapter it is a custom class
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),6);
        viewPager.setAdapter(pagerAdapter);

        // TabLayout addOnTabSelectedLister is use set pageradapter
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0||tab.getPosition()==1||tab.getPosition()==2||tab.getPosition()==3||tab.getPosition()==4||tab.getPosition()==5){
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // change the page
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
























































//        binding.loginid.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(getApplicationContext(),LoginPage.class));
//                finish();
//            }
//        });

    }


}
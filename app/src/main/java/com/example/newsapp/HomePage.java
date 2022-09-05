package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.newsapp.databinding.ActivityHomePageBinding;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePage extends AppCompatActivity {
Toolbar toolbar;
PagerAdapter pagerAdapter;
TabLayout tabLayout;
TabItem mhome,msport,mhealth,mscience,menterntainment,mtechnology;
ViewPager viewPager;
public static String api;
DatabaseReference reference;




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

        //datareference instance for getting api from realtime database
        reference = FirebaseDatabase.getInstance("https://newsbees-2d7a0-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                api = snapshot.child("Api").getValue().toString();
                Log.d("data base connection completed",api);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePage.this, "Failed to connect showdata", Toast.LENGTH_SHORT).show();
            }
        });





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
package com.mfec.teamandroidshare.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.fragment.FragmentTitle;

public class TitleActivity extends AppCompatActivity {
    public String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);


        String cateName = getIntent().getStringExtra("cateName");


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentTitle, new FragmentTitle().newInstance(cateName))
                    .commit();
        }
        // set title name
        //title = "Android";
        //getSupportActionBar().setTitle(title);

    }
}

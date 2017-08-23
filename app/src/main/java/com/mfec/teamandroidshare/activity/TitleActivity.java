package com.mfec.teamandroidshare.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.fragment.FragmentAddTitle;
import com.mfec.teamandroidshare.fragment.FragmentTitle;
import com.mfec.teamandroidshare.fragment.FragmentWebTitle;
import com.mfec.teamandroidshare.manager.SharedPrefUtil;

public class TitleActivity extends AppCompatActivity {
    public String title;
    public BottomNavigationView bottomNavView;
    public FrameLayout fragmentTitile;
    public String cateName;
    public String fragMent;
    public String userId;
    TextView tvToolbar;


    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        //setupUI(findViewById(R.id.parent));

        bottomNavView = (BottomNavigationView) findViewById(R.id.bottom_nav_view);
        fragmentTitile = (FrameLayout) findViewById(R.id.fragmentTitle);
        tvToolbar = (TextView) findViewById(R.id.tvToolbar);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            cateName = getIntent().getStringExtra("cateName");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentTitle , FragmentTitle.newInstance(cateName,"LoadTopicLikeList"),"getFragmentTitle")
                    .commit();
        }
        tvToolbar.setText(cateName);
        bottomNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavView.getMenu().getItem(1).setChecked(true);


        // set title name
        //title = "Android";
        //getSupportActionBar().setTitle(title);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.item_new:
                    init("item_new");
                    return true;

                case R.id.item_hot:
                    init("item_hot");
                    return true;
                case R.id.item_add:
                    init("item_add");
                    return true;
            }
            return false;
        }
    };

    private void init(String navigation) {

        cateName = getIntent().getStringExtra("cateName");


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (navigation.equals("item_new")) {
            fragMent = "LoadTopicList";
            transaction.replace(R.id.fragmentTitle , FragmentTitle.newInstance(cateName,fragMent),"getFragmentTitle")
            .commit();
        } else if (navigation.equals("item_hot")) {
            fragMent = "LoadTopicLikeList";
            transaction.replace(R.id.fragmentTitle , FragmentTitle.newInstance(cateName,"LoadTopicLikeList"),"getFragmentTitle")
                    .commit();
        } else if (navigation.equals("item_add")) {
            transaction.replace(R.id.fragmentTitle , FragmentAddTitle.newInstance(cateName),"getFragmentAddTitle")
                    .commit();
        }
    }

    private  void createFragmentView(){
        cateName = getIntent().getStringExtra("cateName");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                onBackPressed();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save thing(s) here
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore thing(s) here
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("mmr","onDestroyWaht");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("mmr","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("mmr","onStop");
    }
    @Override
    protected void onResume() {

        super.onResume();
        Log.d("mmr","onResume");
    }

}

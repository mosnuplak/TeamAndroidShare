package com.mfec.teamandroidshare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.fragment.FragmentAddTitle;
import com.mfec.teamandroidshare.fragment.FragmentTitle;
import com.mfec.teamandroidshare.fragment.FragmentWebTitle;

public class TitleActivity extends AppCompatActivity {
    public String title;
    public BottomNavigationView bottomNavView;
    public FrameLayout fragmentTitile;
    public String cateName;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        bottomNavView = (BottomNavigationView) findViewById(R.id.bottom_nav_view);
        fragmentTitile = (FrameLayout) findViewById(R.id.fragmentTitle);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            cateName = getIntent().getStringExtra("cateName");
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentTitle , FragmentWebTitle.newInstance(),"getFragmentWebTitle")
                    .commit();
        }
        bottomNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavView.getMenu().getItem(1).setChecked(true);
        //init("item_hot");
        //init("item_add");

        //init();






        // set title name
        //title = "Android";
        //getSupportActionBar().setTitle(title);

    }

    @Override
    protected void onResume() {

        super.onResume();

        Log.wtf("plengslowtoad", "resume activity");
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
            transaction.replace(R.id.fragmentTitle , FragmentTitle.newInstance(cateName),"getFragmentTitle")
            .commit();
        } else if (navigation.equals("item_hot")) {
            transaction.replace(R.id.fragmentTitle , FragmentWebTitle.newInstance(),"getFragmentWebTitle")
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
        if (item.getItemId() == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



}

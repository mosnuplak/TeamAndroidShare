package com.mfec.teamandroidshare.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.fragment.FragmentCategory;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by User on 4/8/2560.
 */

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    FancyButton btnRank;

    //CircleProgressView mCircleProgressView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        initInstance();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fmCategory, new FragmentCategory())
                    .commit();
        }
        btnRank.setOnClickListener(this);

///////////////////////////////////////////////////////////////////////////////////////////
//        mCircleProgressView = (CircleProgressView) findViewById(R.id.circle_progress_view);
//        mCircleProgressView.setTextEnabled(false);
//        mCircleProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
//        mCircleProgressView.setStartAngle(45);
//        mCircleProgressView.setProgressWithAnimation(85, 2000);
//
//        mCircleProgressView.addAnimationListener(new ProgressAnimationListener() {
//            @Override
//            public void onValueChanged(float value) {
//
//            }
//
//            @Override
//            public void onAnimationEnd() {
//                Toast.makeText(CategoryActivity.this, "Animation of CircleProgressView done", Toast.LENGTH_SHORT).show();
//            }
//        });
/////////////////////////////////////////////////////////////////////////
    }

    private void initInstance() {
        btnRank = (FancyButton) findViewById(R.id.btn_rank);
//        toolbar = (Toolbar) findViewById(R.id.toobar); เครื่องมือ ทำเมนู toobar
//        setSupportActionBar(toolbar); //คอมเม้น

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(CategoryActivity.this,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplication(),RankActivity.class);
        startActivity(i);
    }

}

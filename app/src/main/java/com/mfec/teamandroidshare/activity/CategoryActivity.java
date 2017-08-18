package com.mfec.teamandroidshare.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.dao.LoginDao;
import com.mfec.teamandroidshare.fragment.FragmentCategory;
import com.mfec.teamandroidshare.manager.http.HttpManagerNice;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 4/8/2560.
 */

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    ActionBarDrawerToggle actionBarDrawerToggle;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.fmCategory)
    FrameLayout fmCategory;
    @InjectView(R.id.imgProfile)
    ImageView imgProfile;
    @InjectView(R.id.tvProfile)
    TextView tvProfile;
    @InjectView(R.id.btn_rank)
    FancyButton btnRank;
    @InjectView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    private String TAG = "ff";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.inject(this);
        String userId = getIntent().getExtras().getString("userId");

        initInstance();
        Call<LoginDao> call = HttpManagerNice.getInstance().getService().loadProfile(userId);
        call.enqueue(new Callback<LoginDao>() {
            @Override
            public void onResponse(Call<LoginDao> call, Response<LoginDao> response) {
                if (response.isSuccessful()) {

                    LoginDao dao = response.body();
                    Log.d("mos", dao.getName() + "");
                    tvProfile.setText(dao.getName());
                } else {
                    try {
                        Toast.makeText(getApplicationContext(),
                                response.errorBody().string(),
                                Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginDao> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fmCategory, new FragmentCategory().newInstance(userId))
                    .commit();
        }
        btnRank.setOnClickListener(this);


    }

    private void initInstance() {
        btnRank = (FancyButton) findViewById(R.id.btn_rank);
//        toolbar = (Toolbar) findViewById(R.id.toolbar); //เครื่องมือ ทำเมนู toobar
        setSupportActionBar(toolbar); //คอมเม้น
//        tvProfile = (TextView) findViewById(R.id.tvProfile);


//        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
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
        if (item.getItemId() == R.id.action_settings) {
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(CategoryActivity.this);
            builder.setMessage(R.string.dialog_logout);
            builder.setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(getApplicationContext(),
                            R.string.dialog_thankYou, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplication(), LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            });
            builder.setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //dialog.dismiss();
                }
            });
            builder.show();

            return true;
        }

        return super.onOptionsItemSelected(item);


    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplication(), RankActivity.class);
        startActivity(i);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "INSIDE: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "INSIDE: onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "INSIDE: onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "INSIDE: onResume");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}

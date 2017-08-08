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

        if (savedInstanceState == null) {
            Log.d("mosmosmsosm","mosmosmsosm");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentTitle, new FragmentTitle())
                    .commit();
        }
        // set title name
        //title = "Android";
        //getSupportActionBar().setTitle(title);

    }
}

package com.mfec.teamandroidshare.activity;

import android.app.ActionBar;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.fragment.FragmentTitle;
import com.mfec.teamandroidshare.fragment.MainFragment;

public class TitleActivity extends AppCompatActivity {
    public String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_title);

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

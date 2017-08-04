package com.mfec.teamandroidshare.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.fragment.FragmentCategory;

/**
 * Created by User on 4/8/2560.
 */

public class CategoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fmCategory, new FragmentCategory())
                    .commit();
        }
    }
}

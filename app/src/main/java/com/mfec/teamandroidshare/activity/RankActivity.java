package com.mfec.teamandroidshare.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.fragment.FragmentRank;

public class RankActivity extends AppCompatActivity {
    Toolbar toolbar;
    private String TAG = "ff";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentRank, new FragmentRank())
                    .commit();
        }
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if (item.getItemId() == R.id.action_settings) {
//            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
//            finish();
//            startActivity(i);
//            return true;
//
//        }
//        return super.onOptionsItemSelected(item);
//
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

}

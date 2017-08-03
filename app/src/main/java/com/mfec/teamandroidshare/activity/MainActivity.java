package com.mfec.teamandroidshare.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.mfec.teamandroidshare.R;

public class MainActivity extends AppCompatActivity {
TextView tvTest;
    EditText mos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTest = (TextView) findViewById(R.id.tvTest);
        tvTest.setText("mosnaja");
        tvTest.setText("Nooooooo");
        tvTest.setText("Nooooooo");
        mos = (EditText) findViewById(R.id.mos);
        mos.setText("mosnaja");

        //mosnaja

    }
    //fah narak
}

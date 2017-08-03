package com.mfec.teamandroidshare.activity;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;

public class MainActivity extends AppCompatActivity {
    TextView tvTest;
    EditText mos;
    EditText game;

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
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Toast.makeText(MainActivity.this,
                "Width = " + width + ", Height = " + height,
                Toast.LENGTH_SHORT)
                .show();
        game = (EditText) findViewById(R.id.game);
        game.setText("gamenaja");
    }
    //fah narak
}

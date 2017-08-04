package com.mfec.teamandroidshare.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initinstances();
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        getTaskId();

        btnLogin.setOnClickListener(this);

    }
    private void initinstances(){
        btnLogin = (Button) findViewById(R.id.btn_login);
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            Toast.makeText(this,
                    "ffffff",
                    Toast.LENGTH_SHORT)
                    .show();

        }
    }
}

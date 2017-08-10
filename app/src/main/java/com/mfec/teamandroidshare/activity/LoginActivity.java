package com.mfec.teamandroidshare.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;



public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnLogin;
    EditText editUsername;
    EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initinstances();
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
       // getTaskId();

        btnLogin.setOnClickListener(this);
        editUsername.setOnClickListener(this);
        editPassword.setOnClickListener(this);

    }
    private void initinstances(){
        btnLogin = (Button) findViewById(R.id.btn_login);
        editUsername = (EditText) findViewById(R.id.input_username);
        editUsername.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            boolean check = checkLoginValidate(editUsername.getText().toString(),editPassword.getText().toString());
                            if(check == true){
                                Intent i = new Intent(getApplication(),CategoryActivity.class);
                                startActivity(i);
                            }else {

                            }
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
        editPassword = (EditText) findViewById(R.id.input_password);
        editPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            boolean check = checkLoginValidate(editUsername.getText().toString(),editPassword.getText().toString());
                            if(check == true){
                                Intent i = new Intent(getApplication(),CategoryActivity.class);
                                startActivity(i);
                            }else {

                            }
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }
    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            boolean check = checkLoginValidate(editUsername.getText().toString(),editPassword.getText().toString());
            if(check == true){
                Intent i = new Intent(getApplication(),CategoryActivity.class);
                startActivity(i);
            }
        }
    }
    public boolean checkLoginValidate(String username, String password) {
        if ( (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) &&
                username.equals(username) &&
                password.equals(password)) {
            return true;
        }else if(TextUtils.isEmpty(username) && TextUtils.isEmpty(password)){
            Toast.makeText(this,
                    "กรุณาใส่ username และ password",
                    Toast.LENGTH_SHORT)
                    .show();
        }else if(TextUtils.isEmpty(username)){
            Toast.makeText(this,
                    "กรุณาใส่ username",
                    Toast.LENGTH_SHORT)
                    .show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,
                    "กรุณาใส่ password",
                    Toast.LENGTH_SHORT)
                    .show();
        }
        return false;
    }
}

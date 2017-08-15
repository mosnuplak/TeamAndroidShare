package com.mfec.teamandroidshare.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.input_username)
    EditText editUsername;
    @InjectView(R.id.input_password)
    EditText editPassword;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.cv)
    CardView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        initInstances();
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
       // getTaskId();

        btnLogin.setOnClickListener(this);
        editUsername.setOnClickListener(this);
        editPassword.setOnClickListener(this);
       // finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initInstances(){
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
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.btn_login, R.id.fab})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(this, RegisterActivity.class));
                }
                break;
            case R.id.btn_login:
                if (v == btnLogin) {
                    boolean check = checkLoginValidate(editUsername.getText().toString(), editPassword.getText().toString());
                    if (check == true) {
                        Intent i = new Intent(getApplication(), CategoryActivity.class);
                        startActivity(i);
                    }
                }
                break;
        }
    }
    public boolean checkLoginValidate(String username, String password) {
        if ( (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) &&
                username.equals(username) &&
                password.equals(password)) {
            //เช็คอักขระพิเศษ
            boolean check = checkSpecialCharacter(username, password);
            if( check == false ){
                return false;
            }
            return true;
        }else if(TextUtils.isEmpty(username) && TextUtils.isEmpty(password)){
            Toast.makeText(this,
                    "username/password ไม่ถูกต้อง",
                    Toast.LENGTH_SHORT)
                    .show();
        }else if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(this,
                    "username/password ไม่ถูกต้อง",
                    Toast.LENGTH_SHORT)
                    .show();
        }
        return false;
    }
    public boolean checkSpecialCharacter(String username, String password){

        if (!username.matches( "[a-zA-Z0-9._-]*" ) || !password.matches( "[a-zA-Z0-9._-]*" )){
            Toast.makeText(this,
                    "username/password ไม่ถูกต้อง",
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        } else {
            return true;
        }
    }
}

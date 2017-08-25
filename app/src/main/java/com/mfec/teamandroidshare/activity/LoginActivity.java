package com.mfec.teamandroidshare.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.common.Toaster;
import com.mfec.teamandroidshare.dao.LoginDao;
import com.mfec.teamandroidshare.manager.SharedPrefUtil;
import com.mfec.teamandroidshare.manager.http.HttpManagerNice;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    @InjectView(R.id.relative)
    RelativeLayout relative;

    SharedPrefUtil sharedPrefUtil;
   // private  Call<LoginDao> checkLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        sharedPrefUtil = new SharedPrefUtil(this);
        checkSharePre();
        initInstances();


        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
       // getTaskId();
      //  setHideKeyboard(editUsername);
        //setHideKeyboard(editPassword);
        initHideKeyboard(relative);
        btnLogin.setOnClickListener(this);
        editUsername.setOnClickListener(this);
        editPassword.setOnClickListener(this);

    }

    private void checkSharePre() {
        if (sharedPrefUtil.getUserId()!=""){
            Intent i = new Intent(getApplication(), CategoryActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
      //  finish();
    }

    private void initInstances(){
        editPassword = (EditText) findViewById(R.id.input_password);
        editPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            InputMethodManager enter = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            enter.hideSoftInputFromWindow(v.getWindowToken(),0);
                            checkLogin();

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
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){
                    getWindow().setExitTransition(null);
                    getWindow().setEnterTransition(null);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options =
                                ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                        startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                    } else {
                        startActivity(new Intent(this, RegisterActivity.class));

                    }
                }else {
                    startActivity(new Intent(this, RegisterActivity.class));
                    finish();
                }
                break;
            case R.id.btn_login:
                if (v == btnLogin) {
                  //  Log.d("nnnn","aaaa");
                    checkLogin();
                    //Toast.makeText(this,""+check,Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    private void checkLogin(){
        final LoginDao loginDao = new LoginDao();
        loginDao.setUsername(editUsername.getText().toString());
        loginDao.setPassword(editPassword.getText().toString());

        Call<LoginDao> call = HttpManagerNice.getInstance().getService().CheckLogin(loginDao); //call service ส่ง ตัวแบบไป
        call.enqueue(new Callback<LoginDao>() {
            @Override
            public void onResponse(Call<LoginDao> call, Response<LoginDao> response) {
                if(response.isSuccessful()){
                    LoginDao login = response.body();
                    boolean check = checkLoginValidate(editUsername.getText().toString(), editPassword.getText().toString());
                    if (check == true) {
                        Intent i = new Intent(getApplication(), CategoryActivity.class);
                        i.putExtra("userId",login.getUserId());

                        sharedPrefUtil.saveUserId(login.getUserId());
                        sharedPrefUtil.saveName(login.getName());

                        startActivity(i);
                        finish();
                    }
                                login.getName();
//                    Toast.makeText(getApplicationContext(),
//                            "success",
//                            Toast.LENGTH_SHORT)
//                            .show();
                    Toaster.ggToast(getApplicationContext(),
                            R.string.Toast_success,20);

                }else {
                    Toast.makeText(getApplicationContext(),
                            R.string.Toast_nosuccess,
                            Toast.LENGTH_SHORT)
                            .show();
//                    Toaster.ggToast(getApplicationContext(),"no success",500);
                }

            }
            @Override
            public void onFailure(Call<LoginDao> call, Throwable t) {
//                Toaster.ggToast(getApplicationContext(),"gg",500);
                Toast.makeText(getApplicationContext(),
                        R.string.Toast_Invalid,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
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
                    R.string.Toast_EnterUP,
                    Toast.LENGTH_SHORT)
                    .show();
//            Toaster.ggToast(getApplicationContext(),"กรุณากรอก username และ password",500);
        }else if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(this,
                    R.string.Toast_Invalid,
                    Toast.LENGTH_SHORT)
                    .show();
//            Toaster.ggToast(getApplicationContext(),"กรุณากรอก username และ password",500);
        }
        return false;
    }
    public boolean checkSpecialCharacter(String username, String password){

        if (!username.matches( "[a-zA-Z0-9._-]*" ) || !password.matches( "[a-zA-Z0-9._-]*" )){
            editUsername.setError("username");
            editPassword.setError("pass");
            Toast.makeText(this,
                    R.string.Toast_Invalid,
                    Toast.LENGTH_SHORT)
                    .show();
//            Toaster.ggToast(getApplicationContext(),"username หรือ password ไม่ถูกต้อง",500);
            return false;
        }else {
            return true;
        }
    }
    private void initHideKeyboard(RelativeLayout touchInterceptor) {
        touchInterceptor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setHideKeyboard(editUsername,v,event);
                    setHideKeyboard(editPassword,v,event);
                }
                return false;
            }
        });
    }

    private void setHideKeyboard(final EditText editText,View v, MotionEvent event) {
        if (editText.isFocused()) {
            Rect outRect = new Rect();
            editText.getGlobalVisibleRect(outRect);
            if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                editText.clearFocus();
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }

}

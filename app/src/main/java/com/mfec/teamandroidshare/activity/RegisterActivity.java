package com.mfec.teamandroidshare.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.dao.LoginDao;
import com.mfec.teamandroidshare.manager.http.HttpManagerNice;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.cv_add)
    CardView cvAdd;
    @InjectView(R.id.input_name)
    EditText editName;
    @InjectView(R.id.input_username)
    EditText editUsername;
    @InjectView(R.id.input_password)
    EditText editPassword;
    @InjectView(R.id.input_repassword)
    EditText editRepassword;
    boolean CheckRegister;
    @InjectView(R.id.btn_register)
    Button btnRegister;
    @InjectView(R.id.relative)
    RelativeLayout relative;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
//        setHideKeyboard(editName);
//        setHideKeyboard(editUsername);
//        setHideKeyboard(editPassword);
        initInstances();
        initHideKeyboard(relative);
        btnRegister.setOnClickListener(this) ;
        fab.setOnClickListener(this);

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd,cvAdd.getWidth()/2,0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {

        animateRevealClose();

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    @OnClick({R.id.btn_register, R.id.fab})
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btn_register:
               if(v == btnRegister){
//            Toast.makeText(getApplicationContext(),
//                    "fah",
//                    Toast.LENGTH_SHORT)
//                    .show();
                   checkRegister();

               }
               break;

           case R.id.fab:
               if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                   animateRevealClose();
               }else {
                   startActivity(new Intent(this, LoginActivity.class));
               }
               break;
       }
    }
    private void initInstances(){
        editRepassword = (EditText) findViewById(R.id.input_repassword);
        editRepassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            InputMethodManager enter = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            enter.hideSoftInputFromWindow(v.getWindowToken(),0);
                            checkRegister();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }
    public boolean checkRegisterValidate(String name, String username,String password ,String repassword ) {
        if ( (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(repassword)) &&
                name.equals(name) && username.equals(username) &&
                password.equals(password)&& repassword.equals(repassword)) {
            //เช็คอักขระพิเศษ
            boolean check = checkSpecialCharacter(name, username, password, repassword);
            if( check == false ){
                return false;
            }
            return true;
        }else if(TextUtils.isEmpty(name) && TextUtils.isEmpty(username) && TextUtils.isEmpty(password) && TextUtils.isEmpty(repassword)){
            editName.setError(getResources().getString(R.string.Toast_EnterFill));
            editUsername.setError(getResources().getString(R.string.Toast_EnterFill));
            editPassword.setError(getResources().getString(R.string.Toast_EnterFill));
            editRepassword.setError(getResources().getString(R.string.Toast_EnterFill));
            Toast.makeText(this,
                    R.string.Toast_EnterFill,
                    Toast.LENGTH_SHORT)
                    .show();
        }else if(TextUtils.isEmpty(name) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty((repassword))){
            Toast.makeText(this,
                    R.string.Toast_EnterFill,
                    Toast.LENGTH_SHORT)
                    .show();
        }
        return false;
    }
    public boolean checkSpecialCharacter(String name, String username, String password, String repassword){

        if (!name.matches( "[a-zA-Z0-9._-]*" ) || !username.matches( "[a-zA-Z0-9._-]*" ) || !password.matches( "[a-zA-Z0-9._-]*" )|| !repassword.matches( "[a-zA-Z0-9._-]*" )){
            Toast.makeText(this,
                    R.string.Toast_Invalid,
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        } else {
            return true;
        }
    }
    private void callRegister(){
        LoginDao loginDao = new LoginDao();
        loginDao.setName(editName.getText().toString());
        loginDao.setUsername(editUsername.getText().toString());
        loginDao.setPassword(editPassword.getText().toString());
        Call<Boolean> call = HttpManagerNice.getInstance().getService().CheckRegister(loginDao);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()){
                    CheckRegister = response.body();
                    //   boolean check = checkRegisterValidate(editName.getText().toString(), editUsername.getText().toString(), editPassword.getText().toString());
                    if(CheckRegister == true) {
                        Toast.makeText(getApplicationContext()
                                ,R.string.Toast_success
                                , Toast.LENGTH_SHORT)
                                .show();
                            finish();
//                        final Toast toast = Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT);
//                        toast.show();
//
//                        Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                toast.cancel();
//
//                        }, 500);
                    }else{
                        Toast.makeText(getApplicationContext()
                                , R.string.Toast_exists
                                , Toast.LENGTH_SHORT)
                                .show();
                    }
                    // Log.d("ff","dd");
//                               editName.setText(CheckRegister+"");
//                               editUsername.setText(CheckRegister+"");
//                               editPassword.setText(CheckRegister+"");
                }else {
                    Toast.makeText(getApplicationContext()
                            ,R.string.Toast_nosuccess
                            ,Toast.LENGTH_SHORT)
                            .show();

//                    final Toast toast = Toast.makeText(getApplicationContext(), "no success", Toast.LENGTH_SHORT);
//                    toast.show();
//
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            toast.cancel();
//                        }
//                    }, 500);
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(getApplicationContext()
                        ,t.getMessage()
                        ,Toast.LENGTH_SHORT)
                        .show();

            }
        });
    }
    private void checkRegister(){
        boolean check = checkRegisterValidate(editName.getText().toString(), editUsername.getText().toString(), editPassword.getText().toString(), editRepassword.getText().toString());
        if (check == true) {
            if(editPassword.getText().toString().equals(editRepassword.getText().toString())){
                callRegister();
            }else {
                    Toast.makeText(getApplicationContext()
                    , R.string.Toast_pwnoSuccess
                    , Toast.LENGTH_SHORT)
                    .show();
            }
           // callRegister();

        } else{

//            Toast.makeText(getApplicationContext()
//                    , "test"
//                    , Toast.LENGTH_SHORT)
//                    .show();
        }

    }
    private void initHideKeyboard(RelativeLayout touchInterceptor) {
        touchInterceptor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setHideKeyboard(editName,v,event);
                    setHideKeyboard(editUsername,v,event);
                    setHideKeyboard(editPassword,v,event);
                    setHideKeyboard(editRepassword,v,event);
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

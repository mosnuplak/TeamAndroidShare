package com.mfec.teamandroidshare.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
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
    boolean CheckRegister;
    @InjectView(R.id.btn_register)
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);

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
                               // Log.d("ff","dd");
                               editName.setText(CheckRegister+"");
                               editUsername.setText(CheckRegister+"");
                               editPassword.setText(CheckRegister+"");
                           }else {
                               Toast.makeText(getApplicationContext()
                                       ,"no success"
                                       ,Toast.LENGTH_LONG)
                                       .show();
                           }
                       }
                       @Override
                       public void onFailure(Call<Boolean> call, Throwable t) {
                           Toast.makeText(getApplicationContext()
                                   ,"Fail"
                                   ,Toast.LENGTH_LONG)
                                   .show();

                       }
                   });

               }
               break;
           case R.id.fab:
               animateRevealClose();
               break;
       }

    }
}

package com.mfec.teamandroidshare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.fragment.FragmentAddTitle;
import com.mfec.teamandroidshare.fragment.FragmentTitle;
import com.mfec.teamandroidshare.fragment.FragmentWebTitle;

public class TitleActivity extends AppCompatActivity {
    public String title;
    public BottomNavigationView bottomNavView;
    public FrameLayout fragmentTitile;
    public String cateName;
    TextView tvToolbar;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        setupUI(findViewById(R.id.parent));
        bottomNavView = (BottomNavigationView) findViewById(R.id.bottom_nav_view);
        fragmentTitile = (FrameLayout) findViewById(R.id.fragmentTitle);
        tvToolbar = (TextView) findViewById(R.id.tvToolbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            cateName = getIntent().getStringExtra("cateName");
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentTitle , FragmentWebTitle.newInstance("https://www.google.com"),"getFragmentWebTitle")
                    .commit();
        }
        tvToolbar.setText(cateName);
        bottomNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavView.getMenu().getItem(1).setChecked(true);
        //init("item_hot");
        //init("item_add");

        //init();






        // set title name
        //title = "Android";
        //getSupportActionBar().setTitle(title);

    }

    @Override
    protected void onResume() {

        super.onResume();

        Log.wtf("plengslowtoad", "resume activity");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.item_new:
                    init("item_new");
                    return true;
                case R.id.item_hot:
                    init("item_hot");
                    return true;
                case R.id.item_add:
                    init("item_add");
                    return true;
            }
            return false;
        }
    };

    private void init(String navigation) {

        cateName = getIntent().getStringExtra("cateName");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (navigation.equals("item_new")) {
            transaction.replace(R.id.fragmentTitle , FragmentTitle.newInstance(cateName),"getFragmentTitle")
            .commit();
        } else if (navigation.equals("item_hot")) {
            transaction.replace(R.id.fragmentTitle , FragmentWebTitle.newInstance("https://www.google.com"),"getFragmentWebTitle")
                    .commit();
        } else if (navigation.equals("item_add")) {
            transaction.replace(R.id.fragmentTitle , FragmentAddTitle.newInstance(cateName),"getFragmentAddTitle")
                    .commit();
        }
    }

    private  void createFragmentView(){
        cateName = getIntent().getStringExtra("cateName");

    }
    public void setupUI(View view) {


        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(TitleActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
//        if (view instanceof ViewGroup) {
//            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
//                View innerView = ((ViewGroup) view).getChildAt(i);
//                setupUI(innerView);
//            }
//        }
    }
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);

    }

}

package com.mfec.teamandroidshare.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ScrollingView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.dao.CategoryDao;
import com.mfec.teamandroidshare.dao.TitleDao;
import com.mfec.teamandroidshare.dao.WrapperDao;
import com.mfec.teamandroidshare.manager.HttpManager;
import com.mfec.teamandroidshare.manager.http.HttpManagerNice;

import java.io.IOException;
import java.sql.Wrapper;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentAddTitle extends Fragment implements View.OnClickListener {

    public EditText etTitleDis;
    public EditText etTilteName;
    public EditText etTitleLink;
    public TextView tvAlert;
    public Button btnAdd;
    public String cateName;
    public String userId;
    public RelativeLayout scrollView;
    public LinearLayout linear;

    public FragmentAddTitle() {
        super();
    }

    public static FragmentAddTitle newInstance(String cateName,String userId) {
        FragmentAddTitle fragment = new FragmentAddTitle();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putString("cateName", cateName);
        args.putString("userId", userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            cateName = getArguments().getString("cateName");
            userId = getArguments().getString("userId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_title, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {

        etTitleDis = (EditText) rootView.findViewById(R.id.etTitleDis);
        etTilteName = (EditText) rootView.findViewById(R.id.etTilteName);
        etTitleLink = (EditText) rootView.findViewById(R.id.etTitleLink);
        tvAlert = (TextView) rootView.findViewById(R.id.tvAlert);
        scrollView = (RelativeLayout) rootView.findViewById(R.id.scrollView);
        linear = (LinearLayout) rootView.findViewById(R.id.linear);
        setHideKeyboard(etTitleDis);
        setHideKeyboard(etTilteName);
        setHideKeyboard(etTitleLink);
        btnAdd = (Button) rootView.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);




        etTitleLink.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    addTopic();
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity()
                            .getCurrentFocus()
                            .getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        // Init 'View' instance(s) with rootView.findViewById here
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.wtf("plengslowtoad", "resume fragmentAdd");
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnAdd) {
            addTopic();
        }
    }

    private void addTopic() {
        if (!TextUtils.isEmpty(etTilteName.getText().toString()) && !TextUtils.isEmpty(etTitleDis.getText().toString())
                && !TextUtils.isEmpty(etTitleLink.getText().toString())) {
            //Toast.makeText(getActivity(), "mosNaja" + cateName, Toast.LENGTH_SHORT).show();
            WrapperDao wrapper = new WrapperDao();
            CategoryDao categoryDao = new CategoryDao();
            TitleDao titleDao = new TitleDao();
            categoryDao.setCateName(cateName);

            titleDao.setHead(etTilteName.getText().toString());
            titleDao.setLink(etTitleLink.getText().toString());
            titleDao.setDescription(etTitleDis.getText().toString());
            titleDao.setUserId(userId);

            wrapper.setTitleDao(titleDao);
            wrapper.setCategoryDao(categoryDao);

            Call<WrapperDao> call = HttpManagerNice.getInstance().getService().AddTopic(wrapper);
            call.enqueue(new Callback<WrapperDao>() {
                @Override
                public void onResponse(Call<WrapperDao> call, Response<WrapperDao> response) {
                    if (response.isSuccessful()) {

                    } else {
                        try {
                            Toast.makeText(getActivity(),
                                    response.errorBody().string(),
                                    Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<WrapperDao> call, Throwable t) {

                }
            });

            etTilteName.setText("");
            etTitleDis.setText("");
            etTitleLink.setText("");
            tvAlert.setText("บันทึกสำเร็จ");
        } else {
            tvAlert.setText("กรุณากรอกข้อมูลให้ครบ");
        }
    }
    private void setHideKeyboard(final EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                }
            }
        });
    }

}


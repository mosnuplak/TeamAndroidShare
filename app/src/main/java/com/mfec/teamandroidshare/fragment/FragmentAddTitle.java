package com.mfec.teamandroidshare.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

    public FragmentAddTitle() {
        super();
    }

    public static FragmentAddTitle newInstance(String cateName) {
        FragmentAddTitle fragment = new FragmentAddTitle();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putString("cateName", cateName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            cateName = getArguments().getString("cateName");
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
        btnAdd = (Button) rootView.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
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
                titleDao.setUserId("5992ced1e4b0bfbd84845d0b");

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
    }
}

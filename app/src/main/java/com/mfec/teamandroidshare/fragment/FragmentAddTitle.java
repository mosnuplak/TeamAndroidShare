package com.mfec.teamandroidshare.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.dao.TitleDao;
import com.mfec.teamandroidshare.dao.WrapperDao;
import com.mfec.teamandroidshare.manager.HttpManager;
import com.mfec.teamandroidshare.manager.http.HttpManagerNice;

import java.io.IOException;
import java.sql.Wrapper;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentAddTitle extends Fragment implements View.OnClickListener{

    public EditText etTitleDis;
    public EditText etTilteName;
    public EditText etTitleLink;
    public Button btnAdd;

    public FragmentAddTitle() {
        super();
    }

    public static FragmentAddTitle newInstance() {
        FragmentAddTitle fragment = new FragmentAddTitle();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_title, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {

        etTitleDis = (EditText)  rootView.findViewById(R.id.etTitleDis);
        etTilteName = (EditText) rootView.findViewById(R.id.etTilteName);
        etTitleLink = (EditText) rootView.findViewById(R.id.etTitleLink);
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
            Toast.makeText(getActivity(),"mosNaja",Toast.LENGTH_SHORT).show();
            WrapperDao wrapper = new WrapperDao();
            wrapper.getCategoryDao().setCateName("Android");
            wrapper.getTitleDao().setHead("mos");
            wrapper.getTitleDao().setLink("www.google.com");
            wrapper.getTitleDao().setDescription("ssssss");
            wrapper.getTitleDao().setUserId("123456789");


//            Call<Wrapper> call = HttpManagerNice.getInstance().getService().AddTitle(wrapper);
//           call.enqueue(new Callback<Wrapper>() {
//               @Override
//               public void onResponse(Call<Wrapper> call, Response<Wrapper> response) {
//
//               }
//
//               @Override
//               public void onFailure(Call<Wrapper> call, Throwable t) {
//
//               }
//           });
        }
    }
}

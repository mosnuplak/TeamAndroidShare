package com.mfec.teamandroidshare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.activity.CategoryActivity;
import com.mfec.teamandroidshare.activity.LoginActivity;
import com.mfec.teamandroidshare.activity.RankActivity;
import com.mfec.teamandroidshare.activity.TitleActivity;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    Button btnGoLogin;
    Button btnGoTitle;
    Button btnGoCatagory;
    Button btnGoRank;

    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView);

        btnGoLogin.setOnClickListener(this);
        btnGoTitle.setOnClickListener(this);
        btnGoCatagory.setOnClickListener(this);
        btnGoRank.setOnClickListener(this);

        return rootView;
    }

    private void initInstances(View rootView) {

        btnGoLogin = (Button) rootView.findViewById(R.id.btnGoLogin);
        btnGoTitle = (Button) rootView.findViewById(R.id.btnGoTitle);
        btnGoCatagory = (Button) rootView.findViewById(R.id.btnGoCatagory);
        btnGoRank = (Button) rootView.findViewById(R.id.btnGoRank);

        // Init 'View' instance(s) with rootView.findViewById here
    }

    @Override
    public void onStart() {
        super.onStart();
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
        if (v == btnGoLogin) {
            Toast.makeText(getActivity(),
                    "ไปหน้า Login",
                    Toast.LENGTH_LONG)
                    .show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);

            //ใส่ intent ไปหน้า Login ไว้ในนี้
        }
        if (v == btnGoTitle) {
            Toast.makeText(getActivity(),
                    "ไปหน้า Title",
                    Toast.LENGTH_LONG)
                    .show();
            Intent intent = new Intent(getActivity(), TitleActivity.class);
            startActivity(intent);
        }
        if (v == btnGoCatagory) {
            Toast.makeText(getActivity(),
                    "ไปหน้า Catagory",
                    Toast.LENGTH_LONG)
                    .show();
            Intent intent = new Intent(getActivity(), CategoryActivity.class);
            startActivity(intent);
        }
        if (v == btnGoRank) {
            Toast.makeText(getActivity(),
                    "ไปหน้า Rank",
                    Toast.LENGTH_LONG)
                    .show();
            Intent intent = new Intent(getActivity(), RankActivity.class);
            startActivity(intent);
        }
    }

}

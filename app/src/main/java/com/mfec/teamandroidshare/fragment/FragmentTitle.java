package com.mfec.teamandroidshare.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.dao.PeopleDao;
import com.mfec.teamandroidshare.manager.HttpManager;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentTitle extends Fragment {
    View vOne;
    public FragmentTitle() {
        super();
    }

    public static FragmentTitle newInstance() {
        FragmentTitle fragment = new FragmentTitle();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_title, container, false);
        initInstances(rootView);

        return rootView;
    }

    private void initInstances(View rootView) {

        vOne = (View) rootView.findViewById(R.id.vOne);

        Call<List<PeopleDao>> call = HttpManager.getInstance().getService().LoadPerpeoList();
        call.enqueue(new Callback<List<PeopleDao>>() {
            @Override
            public void onResponse(Call<List<PeopleDao>> call,
                                   Response<List<PeopleDao>> response) {
                if (response.isSuccessful()) {
                    List<PeopleDao> dao = response.body();
                    showPeopleOnLogD(dao);

                } else {
                    try {
                        Toast.makeText(getActivity(),
                                response.errorBody().string(),
                                Toast.LENGTH_LONG)
                                .show();
                    } catch (IOException e){

                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PeopleDao>> call, Throwable t) {

            }
        });
        // Init 'View' instance(s) with rootView.findViewById here
    }

    private void showPeopleOnLogD(List<PeopleDao> dao) {
        int i = 0;
        for (PeopleDao p : dao) {
            Log.d("mosnaja"+i , "" +p.getKnownAsName());
            i++;
        }
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
}
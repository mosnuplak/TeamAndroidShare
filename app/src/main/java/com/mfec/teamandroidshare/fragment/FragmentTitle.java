package com.mfec.teamandroidshare.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.dao.PeopleDao;
import com.mfec.teamandroidshare.dao.TitleDao;
import com.mfec.teamandroidshare.manager.HttpManager;
import com.mfec.teamandroidshare.view.TitleAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentTitle extends Fragment {

    RecyclerView rvTitle;
    TitleAdapter adapter;
    private List<PeopleDao> peopleList;

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
        Log.d("mosaaa", "mosaaa");

        initInstances(rootView);


        return rootView;
    }

    private void initPeople() {
        peopleList = new ArrayList<>();
        peopleList.add(new PeopleDao("AndroidStaggered Grid"));
        peopleList.add(new PeopleDao("Paralloid"));
        peopleList.add(new PeopleDao("Retrofit"));
        peopleList.add(new PeopleDao("SwipeRefreshLayout"));
        peopleList.add(new PeopleDao("Android GraphView"));
        peopleList.add(new PeopleDao("Holo Color Picker"));
        peopleList.add(new PeopleDao("Android Async Http"));
        peopleList.add(new PeopleDao("Crashlytics"));
        peopleList.add(new PeopleDao("Butter Knife"));
        peopleList.add(new PeopleDao("Android Annotations"));
        peopleList.add(new PeopleDao("DateTimePicker"));
        peopleList.add(new PeopleDao("Circular Progress Button"));
        peopleList.add(new PeopleDao("ViewPager"));
        peopleList.add(new PeopleDao("ViewPagerIndicator"));
        peopleList.add(new PeopleDao("FadingActionBar"));
        peopleList.add(new PeopleDao("AutofitTextView"));
    }


    private void initInstances(View rootView) {

        rvTitle = (RecyclerView) rootView.findViewById(R.id.rvTitle);

        //initPeople();

        Call<List<TitleDao>> call = HttpManager.getInstance().getService().LoadTopicList();
        call.enqueue(new Callback<List<TitleDao>>() {
            @Override
            public void onResponse(Call<List<TitleDao>> call, Response<List<TitleDao>> response) {
                if (response.isSuccessful()) {
                    List<TitleDao> dao = response.body();
                    showTitle(dao);
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
            public void onFailure(Call<List<TitleDao>> call, Throwable t) {

            }
        });

    }

    private void showTitle(List<TitleDao> dao) {
        GridLayoutManager manager = new GridLayoutManager(getContext().getApplicationContext(), 1);
        rvTitle.setLayoutManager(manager);

        adapter = new TitleAdapter(this, dao, FragmentTitle.this);
        rvTitle.setAdapter(adapter);
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

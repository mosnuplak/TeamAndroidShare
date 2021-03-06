package com.mfec.teamandroidshare.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.dao.CategoryDao;
import com.mfec.teamandroidshare.dao.PeopleDao;
import com.mfec.teamandroidshare.dao.TitleDao;
import com.mfec.teamandroidshare.manager.SharedPrefUtil;
import com.mfec.teamandroidshare.manager.http.HttpManagerNice;
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
    SwipeRefreshLayout swipeRefreshLayout;
    private List<TitleDao> titleList;
    String cateName;
    String userId;
    private String fragMent;
    SharedPrefUtil sharedPrefUtil;

    public FragmentTitle() {
        super();
    }

    public static FragmentTitle newInstance(String cateName,String fragMent) {
        FragmentTitle fragment = new FragmentTitle();
        Bundle args = new Bundle();
        args.putString("cateName",cateName);
        args.putString("fragMent",fragMent);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefUtil = new SharedPrefUtil(getContext());
        userId = sharedPrefUtil.getUserId();
        if(getArguments()!=null)
        cateName = getArguments().getString("cateName");
        fragMent = getArguments().getString("fragMent");

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_title, container, false);

        initInstances(rootView);


        return rootView;
    }

    private void initPeople() {
        titleList = new ArrayList<>();

    }


    private void initInstances(View rootView) {


        rvTitle = (RecyclerView) rootView.findViewById(R.id.rvTitle);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                    reloadData();
            }
        });

        reloadData();

    }

    private void reloadData() {
        CategoryDao categoryDao = new CategoryDao();
        categoryDao.setCateName(cateName);

        Log.d("userId","ENG"+userId+cateName);
        if(fragMent == "LoadTopicList") {
            Call<List<TitleDao>> call = HttpManagerNice.getInstance().getService().LoadTopicList(categoryDao, userId);
            call.enqueue(new Callback<List<TitleDao>>() {
                @Override
                public void onResponse(Call<List<TitleDao>> call, Response<List<TitleDao>> response) {
                    swipeRefreshLayout.setRefreshing(false);
                    if (response.isSuccessful()) {
                        List<TitleDao> dao = response.body();
                        if (dao == null) {

                        } else {
                            showTitle(dao);
                        }
                    } else {
                        try {
                            Toast.makeText(getContext(),
                                    response.errorBody().string(),
                                    Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                @Override
                public void onFailure(Call<List<TitleDao>> call, Throwable t) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }else if(fragMent == "LoadTopicLikeList"){
            Call<List<TitleDao>> call = HttpManagerNice.getInstance().getService().LoadTopicLikeList(categoryDao, userId);
            call.enqueue(new Callback<List<TitleDao>>() {
                @Override
                public void onResponse(Call<List<TitleDao>> call, Response<List<TitleDao>> response) {
                    swipeRefreshLayout.setRefreshing(false);
                    if (response.isSuccessful()) {
                        List<TitleDao> dao = response.body();
                        if (dao == null) {

                        } else {
                            showTitle(dao);
                        }
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
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }
    }

    private void showTitle(List<TitleDao> dao) {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
        rvTitle.setLayoutManager(manager);

        adapter = new TitleAdapter(dao, FragmentTitle.this,userId);
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

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}

package com.mfec.teamandroidshare.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ag.floatingactionmenu.OptionsFabLayout;
import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.dao.CategoryDao;
import com.mfec.teamandroidshare.manager.http.HttpManagerNice;
import com.mfec.teamandroidshare.view.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentCategory extends Fragment {
    FancyButton btnRank;
    //    RecyclerView rvCategory;
    CategoryAdapter adapter;
    String userId;
    OptionsFabLayout fabWithOptions;
    @InjectView(R.id.rvCategory)
    RecyclerView rvCategory;
    private List<CategoryDao> categoryList;

    public FragmentCategory() {
        super();
    }

    public static FragmentCategory newInstance(String userId) {
        FragmentCategory fragment = new FragmentCategory();
        Bundle args = new Bundle();
        args.putString("userId", userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            userId = getArguments().getString("userId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        initInstances(rootView);

        ButterKnife.inject(this, rootView);
        return rootView;


    }

    private void getCategoryItem() {
        categoryList = new ArrayList<>();

        categoryList.add(new CategoryDao("0001","Mos"));
        categoryList.add(new CategoryDao("0002","MosNaja"));

        //categoryList.add(new CategoryDao());

    }

    private void initInstances(View rootView) {
//        rvCategory = (RecyclerView) rootView.findViewById(R.id.rvCategory);
        btnRank = (FancyButton) rootView.findViewById(R.id.btn_rank);

        //getCategoryItem();
        Call<List<CategoryDao>> call = HttpManagerNice.getInstance().getService().LoadCategory();
        call.enqueue(new Callback<List<CategoryDao>>() {
            @Override
            public void onResponse(Call<List<CategoryDao>> call, Response<List<CategoryDao>> response) {
                if (response.isSuccessful()) {
                    List<CategoryDao> dao = response.body();
                    showCategory(dao);
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<CategoryDao>> call, Throwable t) {


            }
        });


        // Init 'View' instance(s) with rootView.findViewById here
    }

    private void showCategory(List<CategoryDao> dao) {
        GridLayoutManager manager = new GridLayoutManager(getContext().getApplicationContext(), 3);
        rvCategory.setLayoutManager(manager);

        adapter = new CategoryAdapter(this, dao, FragmentCategory.this, userId);
        rvCategory.setAdapter(adapter);

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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}

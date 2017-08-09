package com.mfec.teamandroidshare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.ag.floatingactionmenu.OptionsFabLayout;
import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.activity.CategoryActivity;
import com.mfec.teamandroidshare.activity.RankActivity;
import com.mfec.teamandroidshare.dao.CategoryDao;
import com.mfec.teamandroidshare.manager.HttpManager;
import com.mfec.teamandroidshare.view.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mfec.teamandroidshare.R.id.fab_addCategory;
import static com.mfec.teamandroidshare.R.id.fab_gotoRank;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentCategory extends Fragment {

    RecyclerView rvCategory;
    CategoryAdapter adapter;
    OptionsFabLayout fabWithOptions;
    private List<CategoryDao> categoryList;

    public FragmentCategory() {
        super();
    }

    public static FragmentCategory newInstance() {
        FragmentCategory fragment = new FragmentCategory();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        initInstances(rootView);

        fabWithOptions = (OptionsFabLayout) rootView.findViewById(R.id.fab_l);


        //Set mini fab's colors.
        fabWithOptions.setMiniFabsColors(
                R.color.colorPrimary,
                R.color.green_fab);

        //Set main fab clicklistener.
        fabWithOptions.setMainFabOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Main fab clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        //Set mini fabs clicklisteners.
        fabWithOptions.setMiniFabSelectedListener(new OptionsFabLayout.OnMiniFabSelectedListener() {
            @Override
            public void onMiniFabSelected(MenuItem fabItem) {

                if (fabItem.getItemId() == fab_addCategory) {
                    Toast.makeText(getContext(),
                            fabItem.getTitle(),
                            Toast.LENGTH_SHORT).show();

                }
                if (fabItem.getItemId() == fab_gotoRank) {
                    Toast.makeText(
                            getContext(),
                            "Go To " + fabItem.getTitle(),
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), RankActivity.class);
                    startActivity(intent);
                }
            }
        });

        //getCategoryItem();

        return rootView;


    }

    private void getCategoryItem() {
        categoryList = new ArrayList<>();
        categoryList.add(new CategoryDao("Android"));
        categoryList.add(new CategoryDao("IOS"));
        categoryList.add(new CategoryDao("Web"));
        categoryList.add(new CategoryDao("คลิปโป๊ สุดยอดแห่งความมันส์"));
        categoryList.add(new CategoryDao("คลิปโป๊ สุดยอดแห่งความมันส์"));
        categoryList.add(new CategoryDao("มอส เอง ไง จะ ใคร ละ"));
        categoryList.add(new CategoryDao("เกมส์เอง อิอิ อะอะ อึอึ อุอุ"));
        categoryList.add(new CategoryDao("คลิปโป๊ สุดยอดแห่งความมันส์"));
        categoryList.add(new CategoryDao("คลิปโป๊ สุดยอดแห่งความมันส์"));
        categoryList.add(new CategoryDao("ป๊อป กวน ตีน"));
        categoryList.add(new CategoryDao("ฟ้า กวน ตีน "));
        categoryList.add(new CategoryDao("คลิปหลุดเกมส์ 18+ "));
        categoryList.add(new CategoryDao("คลิปเกย์เรท ฉ "));
        categoryList.add(new CategoryDao("คลิปโป๊ สุดยอดแห่งความมันส์"));
        categoryList.add(new CategoryDao("คลิปโป๊ สุดยอดแห่งความมันส์"));
        categoryList.add(new CategoryDao("คลิปโป๊ สุดยอดแห่งความมันส์"));
        categoryList.add(new CategoryDao("คลิปโป๊ สุดยอดแห่งความมันส์"));
        categoryList.add(new CategoryDao("คลิปโป๊ สุดยอดแห่งความมันส์"));
        categoryList.add(new CategoryDao("คลิปโป๊ สุดยอดแห่งความมันส์"));


    }

    private void initInstances(View rootView) {
        rvCategory = (RecyclerView) rootView.findViewById(R.id.rvCategory);

        //getCategoryItem();
        Call<List<CategoryDao>> call = HttpManager.getInstance().getService().LoadCategory();
        call.enqueue(new Callback<List<CategoryDao>>() {
            @Override
            public void onResponse(Call<List<CategoryDao>> call, Response<List<CategoryDao>> response) {
                if (response.isSuccessful()) {
                    List<CategoryDao> dao = response.body();
                    showCategory(dao);
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

        adapter = new CategoryAdapter(this, dao, FragmentCategory.this);
        rvCategory.setAdapter(adapter);

    }
//    public void doProgress(View v){
//        ProgressDialog dialog = new ProgressDialog(getContext());
//        dialog.setTitle("*-*");
//        dialog.setMessage("*-*-*-*-*");
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.setIcon(R.drawable.bg);
//        dialog.setButton("*-*-", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//
//
//        dialog.show();
//
//    }

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

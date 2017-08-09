package com.mfec.teamandroidshare.view;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.activity.TitleActivity;
import com.mfec.teamandroidshare.dao.CategoryDao;
import com.mfec.teamandroidshare.fragment.FragmentCategory;
import com.mfec.teamandroidshare.fragment.FragmentTitle;

import java.util.List;

/**
 * Created by MSI on 8/8/2560.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> implements FragmentCategory.gg{
    FragmentCategory fragmentCategory;
    List<CategoryDao> categoryList;
    FragmentCategory fragmentCategory1;
    CategoryDao categoryDao;
    CategoryViewHolder holder;
    public CategoryAdapter(FragmentCategory fragmentCategory, List<CategoryDao> categoryList, FragmentCategory fragmentCategory1) {

        this.fragmentCategory = fragmentCategory;
        this.categoryList = categoryList;
        this.fragmentCategory1 = fragmentCategory1;

    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_category,parent,false);
        return new CategoryViewHolder(view, itemClick);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        categoryDao = categoryList.get(position);
        holder.tvNameCate.setText(categoryDao.getCateName());


    }

    CategoryViewHolder.onItemClick itemClick = new CategoryViewHolder.onItemClick() {
        @Override
        public void onItemClickListener(int position) {
            Log.d("MOSS", "onItemClickListener: "+categoryList.get(position).getCateName());
            Intent intent = new Intent(fragmentCategory1.getActivity(), TitleActivity.class);
            intent.putExtra("cateName",categoryList.get(position).getCateName());
            fragmentCategory1.startActivity(intent);
        }
    };

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    @Override
    public void setCateName(String name) {

    }
}

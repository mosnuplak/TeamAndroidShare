package com.mfec.teamandroidshare.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.dao.CategoryDao;
import com.mfec.teamandroidshare.fragment.FragmentCategory;

import java.util.List;

/**
 * Created by MSI on 8/8/2560.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> implements View.OnClickListener{
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
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        categoryDao = categoryList.get(position);
        holder.tvNameCate.setText(categoryDao.getCateName());
        holder.rvCategory.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    @Override
    public void onClick(View v) {
        if (v == v.findViewById(R.id.rvCategory)){
                Toast.makeText(v.getContext(),
                        "go Title",
                        Toast.LENGTH_SHORT)
                        .show();
        }
    }
}

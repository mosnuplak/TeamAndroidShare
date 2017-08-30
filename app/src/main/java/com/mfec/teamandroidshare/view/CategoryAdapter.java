package com.mfec.teamandroidshare.view;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.activity.TitleActivity;
import com.mfec.teamandroidshare.dao.CategoryDao;
import com.mfec.teamandroidshare.fragment.FragmentCategory;
import com.mfec.teamandroidshare.fragment.FragmentTitle;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by MSI on 8/8/2560.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder>{
    FragmentCategory fragmentCategory;
    List<CategoryDao> categoryList;
    FragmentCategory fragmentCategory1;
    CategoryDao categoryDao;
    CategoryViewHolder holder;
    String userId;
    public CategoryAdapter(FragmentCategory fragmentCategory, List<CategoryDao> categoryList, FragmentCategory fragmentCategory1 , String userId) {

        this.fragmentCategory = fragmentCategory;
        this.categoryList = categoryList;
        this.fragmentCategory1 = fragmentCategory1;
        this.userId = userId;


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
        Log.d("MildMos",categoryDao.getImageUrl()+"");
        Glide.with(fragmentCategory.getActivity())
                .load(categoryDao.getImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .bitmapTransform(new CropCircleTransformation(fragmentCategory.getContext()))
                .into(holder.ivPicCate);


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


}

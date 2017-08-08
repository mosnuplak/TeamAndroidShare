package com.mfec.teamandroidshare.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfec.teamandroidshare.R;

/**
 * Created by MSI on 8/8/2560.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    LinearLayout rvCategory;
    ImageView ivPicCate;
    TextView tvNameCate;
    public CategoryViewHolder(View itemView) {
        super(itemView);
        rvCategory = (LinearLayout) itemView.findViewById(R.id.rvCategory);
        ivPicCate = (ImageView) itemView.findViewById(R.id.ivPicCate);
        tvNameCate = (TextView) itemView.findViewById(R.id.tvNameCate);
    }
}

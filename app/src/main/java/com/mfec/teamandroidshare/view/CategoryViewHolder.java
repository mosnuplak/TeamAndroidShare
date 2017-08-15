package com.mfec.teamandroidshare.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
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

    View view;
    onItemClick holderClick;
    public CategoryViewHolder(View itemView, onItemClick itemClick) {
        super(itemView);
        this.view = itemView;
        rvCategory = (LinearLayout) itemView.findViewById(R.id.rvCategory);
        ivPicCate = (ImageView) itemView.findViewById(R.id.ivPicCate);
        tvNameCate = (TextView) itemView.findViewById(R.id.tvNameCate);

        holderClick = itemClick;
        view.setOnClickListener(clickItem);
    }

    View.OnClickListener clickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            holderClick.onItemClickListener(getAdapterPosition());
        }
    };

    public interface onItemClick{
        void onItemClickListener(int position);
    }
}

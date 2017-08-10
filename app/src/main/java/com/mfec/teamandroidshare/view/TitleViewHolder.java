package com.mfec.teamandroidshare.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfec.teamandroidshare.R;

/**
 * Created by MSI on 4/8/2560.
 */

public class TitleViewHolder extends RecyclerView.ViewHolder {
    LinearLayout rvTitle;
    TextView tvTitle;
    TextView tvDescript;
    TextView tvPoster;
    ImageButton ibtnStar;
    TextView tvStar;
    ImageView ivTitle;
    public TitleViewHolder(View itemView) {
        super(itemView);

        rvTitle = (LinearLayout) itemView.findViewById(R.id.rvTitle);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        tvDescript = (TextView) itemView.findViewById(R.id.tvDescript);
        tvPoster = (TextView) itemView.findViewById(R.id.tvPoster);
        ibtnStar = (ImageButton) itemView.findViewById(R.id.ibtnStar);
        tvStar = (TextView) itemView.findViewById(R.id.tvStar);
        ivTitle = (ImageView) itemView.findViewById(R.id.ivTitle);


    }
}

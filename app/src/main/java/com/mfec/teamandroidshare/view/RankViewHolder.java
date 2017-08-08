package com.mfec.teamandroidshare.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfec.teamandroidshare.R;

/**
 * Created by lenovoNB on 08-Aug-17.
 */

public class RankViewHolder extends RecyclerView.ViewHolder{

    LinearLayout rv_rank;
    TextView tv_num;
    ImageView iv_image;
    TextView tv_name;

    public RankViewHolder (View itemView){
        super(itemView);

        rv_rank = (LinearLayout) itemView.findViewById(R.id.rv_rank);
        tv_num = (TextView) itemView.findViewById(R.id.tv_num);
        iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
        tv_name = (TextView) itemView.findViewById(R.id.tv_name);

    }

}

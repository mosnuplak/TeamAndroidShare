package com.mfec.teamandroidshare.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
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
    TextView totalViewer;

    View view;
    onItemClick holderClick;
    public TitleViewHolder(View itemView, onItemClick itemClick) {
        super(itemView);
        this.view = itemView;
        totalViewer = (TextView) itemView.findViewById(R.id.totalViewer);
        rvTitle = (LinearLayout) itemView.findViewById(R.id.rvTitle);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        tvDescript = (TextView) itemView.findViewById(R.id.tvDescript);
        tvPoster = (TextView) itemView.findViewById(R.id.tvPoster);
        ibtnStar = (ImageButton) itemView.findViewById(R.id.ibtnStar);
        tvStar = (TextView) itemView.findViewById(R.id.tvStar);
        ivTitle = (ImageView) itemView.findViewById(R.id.ivTitle);

        holderClick = itemClick;
        ivTitle.setOnClickListener(clickItem);
        //view.setOnClickListener(clickItem);
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

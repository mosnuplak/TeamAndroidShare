package com.mfec.teamandroidshare.view;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.dao.TitleDao;
import com.mfec.teamandroidshare.fragment.FragmentTitle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by MSI on 4/8/2560.
 */

public class TitleAdapter extends RecyclerView.Adapter<TitleViewHolder> {
    FragmentTitle fragmentTitle;
    private FragmentTitle mContext;
    private List<TitleDao> TitleList;
    public TitleAdapter(FragmentTitle mContext, List<TitleDao> TitleList , FragmentTitle fragmentTitle) {

        this.mContext = mContext;
        this.fragmentTitle = fragmentTitle;
        this.TitleList = TitleList;

    }




    @Override
    public TitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_title,parent,false);
        return new TitleViewHolder(view, itemClick);
    }

    @Override
    public void onBindViewHolder(TitleViewHolder holder, int position) {
        String android = "Android";
        String ios = "IOS";
        String iot = "IoT";
        String wed = "Web";
        TitleDao titleDao = TitleList.get(position);

        //SpannableString content = new SpannableString(titleDao.getHead());
        //content.setSpan(new UnderlineSpan(), 0, content.length(), 0);

        holder.tvTitle.setText(titleDao.getHead());
        holder.tvDescript.setText(titleDao.getDescription());
        holder.totalViewer.setText(titleDao.getTotalViewer());
        if (titleDao.getPoster() == null){
            holder.tvPoster.setText("Share by Anonymous");
        } else {
            holder.tvPoster.setText("Share by "+titleDao.getPoster());
        }

        if (titleDao.getCategory().equals(android)) {
            holder.ivTitle.setImageResource(R.drawable.android_fix);
        }else if (titleDao.getCategory().equals(ios)){
            holder.ivTitle.setImageResource(R.drawable.ios_fix);
        }else if (titleDao.getCategory().equals(iot)){
            holder.ivTitle.setImageResource(R.drawable.iot_fix);
        }else if (titleDao.getCategory().equals(wed)) {
            holder.ivTitle.setImageResource(R.drawable.web_fix);
        }else {
            holder.ivTitle.setImageResource(R.drawable.logo);
        }


    }

    TitleViewHolder.onItemClick itemClick = new TitleViewHolder.onItemClick() {

        @Override
        public void onItemClickListener(int position) {
            Toast.makeText(fragmentTitle.getActivity(),"go webView",Toast.LENGTH_SHORT).show();

        }
    };


    @Override
    public int getItemCount() {
        return TitleList.size();
    }


}


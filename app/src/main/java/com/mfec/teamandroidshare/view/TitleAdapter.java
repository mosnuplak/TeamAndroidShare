package com.mfec.teamandroidshare.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.dao.PeopleDao;
import com.mfec.teamandroidshare.dao.TitleDao;
import com.mfec.teamandroidshare.fragment.FragmentTitle;

import java.util.List;

/**
 * Created by MSI on 4/8/2560.
 */

public class TitleAdapter extends RecyclerView.Adapter<TitleViewHolder> implements View.OnClickListener {
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
        return new TitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TitleViewHolder holder, int position) {
        TitleDao titleDao = TitleList.get(position);
        holder.tvTitle.setText(titleDao.getHead());
        holder.tvDescript.setText(titleDao.getDescription());
        holder.tvPoster.setText(titleDao.getPoster());
        holder.ibtnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Star",Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return TitleList.size();
    }

    @Override
    public void onClick(View v) {

    }
}

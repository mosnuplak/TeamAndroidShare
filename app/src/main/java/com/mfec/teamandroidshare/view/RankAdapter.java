package com.mfec.teamandroidshare.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.dao.RankDao;
import com.mfec.teamandroidshare.fragment.FragmentRank;

import java.util.List;

/**
 * Created by lenovoNB on 08-Aug-17.
 */

public class RankAdapter extends RecyclerView.Adapter<RankViewHolder>{

    FragmentRank fragmentRank;
    List<RankDao> rankList;
    FragmentRank fragmentRank1;
    RankDao rankDao;
    RankViewHolder holder;

    public RankAdapter(FragmentRank fragmentRank, List<RankDao> rankList, FragmentRank fragmentRank1) {
        this.fragmentRank = fragmentRank;
        this.rankList = rankList;
        this.fragmentRank1 = fragmentRank1;

    }

    @Override
    public RankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_rank,parent,false);
        return new RankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RankViewHolder holder, int position) {

        rankDao = rankList.get(position);
        holder.tv_num.setText(rankDao.getTv_num());
        holder.tv_name.setText(rankDao.getTv_name());

    }

    @Override
    public int getItemCount() {
        return rankList.size();
    }
}

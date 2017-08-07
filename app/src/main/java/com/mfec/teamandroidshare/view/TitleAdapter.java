package com.mfec.teamandroidshare.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.dao.PeopleDao;
import com.mfec.teamandroidshare.fragment.FragmentTitle;

import java.util.List;

/**
 * Created by MSI on 4/8/2560.
 */

public class TitleAdapter extends RecyclerView.Adapter<TitleViewHolder> {
    FragmentTitle fragmentTitle;
    private FragmentTitle mContext;
    private List<PeopleDao> peopleList;
    public TitleAdapter(FragmentTitle mContext, List<PeopleDao> peopleList , FragmentTitle fragmentTitle) {
        Log.d("mosTitleAdapter","mosTitleAdapter");
        this.mContext = mContext;
        this.fragmentTitle = fragmentTitle;
        this.peopleList = peopleList;

    }

    @Override
    public TitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_title,parent,false);
        return new TitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TitleViewHolder holder, int position) {
        PeopleDao peopleDao = peopleList.get(position);
        holder.tvTitle.setText(peopleDao.getKnownAsName());

    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }
}

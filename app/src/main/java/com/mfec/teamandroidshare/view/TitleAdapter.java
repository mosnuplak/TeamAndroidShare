package com.mfec.teamandroidshare.view;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.fragment.FragmentTitle;

/**
 * Created by MSI on 4/8/2560.
 */

public class TitleAdapter extends RecyclerView.Adapter<TitleViewHolder> {
    FragmentTitle fragmentTitle;
    public TitleAdapter(FragmentTitle fragmentTitle) {
        this.fragmentTitle = fragmentTitle;
    }

    @Override
    public TitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_title,parent,false);
        return new TitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TitleViewHolder holder, int position) {
        holder.tvTitle.setText("mos");

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

package com.mfec.teamandroidshare.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.activity.TitleActivity;
import com.mfec.teamandroidshare.activity.WebviewActivity;
import com.mfec.teamandroidshare.dao.TitleDao;
import com.mfec.teamandroidshare.fragment.FragmentTitle;
import com.mfec.teamandroidshare.fragment.FragmentWebTitle;
import com.mfec.teamandroidshare.manager.http.HttpManagerNice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MSI on 4/8/2560.
 */

public class TitleAdapter extends RecyclerView.Adapter<TitleViewHolder> {
    FragmentTitle fragmentTitle;
    private FragmentTitle mContext;
    private List<TitleDao> TitleList;
    Boolean status = false;
    String userId;
    Boolean callBackLike = false;
    public TitleAdapter(List<TitleDao> TitleList , FragmentTitle fragmentTitle) {

        SharedPreferences sp = fragmentTitle.getActivity().getSharedPreferences("SHARE_DATA", Context.MODE_PRIVATE);
        userId = sp.getString("userId", "0");
        this.fragmentTitle = fragmentTitle;
        this.TitleList = TitleList;

    }




    @Override
    public TitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_title,parent,false);
        return new TitleViewHolder(view, itemClick);
    }

    @Override
    public void onBindViewHolder(final TitleViewHolder holder, int position) {
        String android = "Android";
        String ios = "IOS";
        String iot = "IoT";
        String wed = "Web";
        final TitleDao titleDao = TitleList.get(position);

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

        if(titleDao.getStatus() == true) {
            holder.ibtnStar.setBackgroundResource(R.drawable.love_s2);
        } else {
            holder.ibtnStar.setBackgroundResource(R.drawable.love_s1);
        }

        holder.ibtnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(titleDao.getStatus() == true) {
                    holder.ibtnStar.setBackgroundResource(R.drawable.love_s1);
                    likeService(titleDao.getTopicId(), userId);
                } else {
                    holder.ibtnStar.setBackgroundResource(R.drawable.love_s2);
                    likeService(titleDao.getTopicId(), userId);
                }
            }
        });


    }

    private void likeService(String titleId,String userId) {
        TitleDao titledao = new TitleDao();
        titledao.setTopicId(titleId);
        Call<Boolean> call = HttpManagerNice.getInstance().getService().likeAndUnlike(titledao,userId);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    callBackLike = response.body();
                    if (callBackLike == true) {
                        Toast.makeText(fragmentTitle.getContext()
                                , "like"
                                , Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        Toast.makeText(fragmentTitle.getContext()
                                , "Unlike"
                                , Toast.LENGTH_SHORT)
                                .show();
                    }
                }else {
                    try {
                        Toast.makeText(fragmentTitle.getContext(),
                                response.errorBody().string(),
                                Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(fragmentTitle.getContext()
                        , t.getMessage()
                        , Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    TitleViewHolder.onItemClick itemClick = new TitleViewHolder.onItemClick() {

        @Override
        public void onItemClickListener(int position) {
            //TitleDao titleDao = TitleList.get(position);


            //Toast.makeText(fragmentTitle.getActivity(),""+titleDao.getLink(),Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(fragmentTitle.getActivity(),WebviewActivity.class);
            intent.putExtra("linkUrl",TitleList.get(position).getLink());
            intent.putExtra("topicId",TitleList.get(position).getTopicId());
            intent.putExtra("topicName",TitleList.get(position).getHead());
            fragmentTitle.startActivity(intent);

        }
    };


    @Override
    public int getItemCount() {
        return TitleList.size();
    }


}


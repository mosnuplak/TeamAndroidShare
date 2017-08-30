package com.mfec.teamandroidshare.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CountDownTimer;
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
import com.mfec.teamandroidshare.common.Toaster;
import com.mfec.teamandroidshare.dao.TitleDao;
import com.mfec.teamandroidshare.fragment.FragmentTitle;
import com.mfec.teamandroidshare.fragment.FragmentWebTitle;
import com.mfec.teamandroidshare.manager.SharedPrefUtil;
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
    private List<TitleDao> TitleList;
    String userId;
    Boolean callBackLike = false;
    private int totalLike;
    String android = "Android";
    String ios = "IOS";
    String iot = "IoT";
    String wed = "Web";

    public TitleAdapter(List<TitleDao> TitleList, FragmentTitle fragmentTitle, String userId) {

        this.userId = userId;
        this.fragmentTitle = fragmentTitle;
        this.TitleList = TitleList;

    }


    @Override
    public TitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_title, parent, false);
        return new TitleViewHolder(view, itemClick);
    }

    @Override
    public void onBindViewHolder(final TitleViewHolder holder, final int position) {


        final TitleDao titleDao = TitleList.get(position);
        totalLike = Integer.parseInt(titleDao.getTotalLike());
        if (totalLike == 0) {
            holder.tvStar.setText("Love this");
        } else {
            holder.tvStar.setText(totalLike + "");
        }

        holder.tvTitle.setText(titleDao.getHead());
        holder.tvDescript.setText(titleDao.getDescription());
        holder.totalViewer.setText(titleDao.getTotalViewer());
        setPoster(titleDao.getPoster(),holder);
        setPic(titleDao.getCategory(),holder);
        setLike(titleDao.getStatus(),holder);

        holder.ibtnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (titleDao.getStatus() == true) {
                    titleDao.setStatus(false);
                    totalLike = Integer.parseInt(titleDao.getTotalLike());
                    totalLike = totalLike - 1;
                    titleDao.setTotalLike(totalLike + "");
                    likeService(titleDao.getTopicId(), userId, totalLike + "", false);
                    holder.ibtnStar.setBackgroundResource(R.drawable.love_s1);
                } else {
                    titleDao.setStatus(true);
                    totalLike = Integer.parseInt(titleDao.getTotalLike());
                    totalLike = totalLike + 1;
                    titleDao.setTotalLike(totalLike + "");
                    likeService(titleDao.getTopicId(), userId, totalLike + "", true);
                    holder.ibtnStar.setBackgroundResource(R.drawable.love_s2);
                }
                notifyItemChanged(position);

            }
        });

    }

    private void setLike(Boolean status, TitleViewHolder holder) {
        if (status == true) {
            holder.ibtnStar.setBackgroundResource(R.drawable.love_s2);
        } else {
            holder.ibtnStar.setBackgroundResource(R.drawable.love_s1);
        }
    }

    private void setPic(String category, TitleViewHolder holder) {
        if (category.equals(android)) {
            holder.ivTitle.setImageResource(R.drawable.android_fix);
        } else if (category.equals(ios)) {
            holder.ivTitle.setImageResource(R.drawable.ios_fix);
        } else if (category.equals(iot)) {
            holder.ivTitle.setImageResource(R.drawable.iot_fix);
        } else if (category.equals(wed)) {
            holder.ivTitle.setImageResource(R.drawable.web_fix);
        } else {
            holder.ivTitle.setImageResource(R.drawable.logo);
        }
    }

    private void setPoster(String namePoster,TitleViewHolder holder) {
        if (namePoster == null) {
            holder.tvPoster.setText("Share by Anonymous");
        } else {
            holder.tvPoster.setText("Share by " + namePoster);
        }

    }

    private Boolean likeService(String titleId, String userId, String totalLike, boolean sta) {
        TitleDao titledao = new TitleDao();
        titledao.setTopicId(titleId);
        titledao.setTotalLike(totalLike);
        titledao.setStatus(sta);
        Call<Boolean> call = HttpManagerNice.getInstance().getService().likeAndUnlike(titledao, userId);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    callBackLike = response.body();
                    if (callBackLike == true) {

                        Toaster.ggToast(fragmentTitle.getContext(),
                                R.string.Toast_like,
                                1000);

                    } else {
                        Toaster.ggToast(fragmentTitle.getContext(),
                                R.string.Toast_unlike,
                                1000);
                    }
                } else {
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
        //notifyItemChanged(position);
        return callBackLike;
    }

    TitleViewHolder.onItemClick itemClick = new TitleViewHolder.onItemClick() {

        @Override
        public void onItemClickListener(int position) {

            Intent intent = new Intent(fragmentTitle.getActivity(), WebviewActivity.class);
            intent.putExtra("linkUrl", TitleList.get(position).getLink());
            intent.putExtra("topicId", TitleList.get(position).getTopicId());
            intent.putExtra("topicName", TitleList.get(position).getHead());
            intent.putExtra("category", TitleList.get(position).getCategory());
            fragmentTitle.startActivity(intent);

        }
    };


    @Override
    public int getItemCount() {
        return TitleList.size();
    }


}


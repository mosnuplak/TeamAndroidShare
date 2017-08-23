package com.mfec.teamandroidshare.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovoNB on 08-Aug-17.
 */

public class RankDao {
    @SerializedName("name")
    private String name;
    @SerializedName("totalLike")
    private int totalLike;
    @SerializedName("rankedNumber")
    private int rankedNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(int totalLike) {
        this.totalLike = totalLike;
    }

    public int getRankedNumber() {
        return rankedNumber;
    }

    public void setRankedNumber(int rankedNumber) {
        this.rankedNumber = rankedNumber;
    }
}

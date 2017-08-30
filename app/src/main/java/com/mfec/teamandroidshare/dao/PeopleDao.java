package com.mfec.teamandroidshare.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by E5-473G on 8/4/2017.
 */

public class PeopleDao {
    @SerializedName("head")
    private String KnownAsName;
//    @SerializedName("totalLikeRank")
//    private int totalLikeRank;

    public PeopleDao(String knownAsName){
        this.KnownAsName = knownAsName;
    }
    public String getKnownAsName() {
        return KnownAsName;
    }

    public void setKnownAsName(String knownAsName) {
        KnownAsName = knownAsName;
    }
//    public int getTotalLikeRank() {
//        return totalLikeRank;
//    }
//
//    public void setTotalLikeRank(int totalLike) {
//        this.totalLikeRank = totalLikeRank;
//    }
}

package com.mfec.teamandroidshare.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MSI on 16/8/2560.
 */

public class LoginDao {

    @SerializedName("name")
    private String name;

    @SerializedName("username")
    private String username;

    @SerializedName("userId")
    private String userId;

    @SerializedName("password")
    private String password;

    @SerializedName("totalLikeRank")
    private  String totalLikeRank;

    public String getTotalLikeRank() {
        return totalLikeRank;
    }

    public void setTotalLikeRank(String totalLikeRank) {
        this.totalLikeRank = totalLikeRank;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

package com.mfec.teamandroidshare.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MSI on 8/8/2560.
 */

public class CategoryDao {
    @SerializedName("name")
    private String cateName;

    public CategoryDao(String cateName){
        this.cateName = cateName;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}

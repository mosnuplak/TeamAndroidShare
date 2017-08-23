package com.mfec.teamandroidshare.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MSI on 8/8/2560.
 */

public class CategoryDao {
    @SerializedName("name")
    private String cateName;

    @SerializedName("categoryId")
    private String id;

    public CategoryDao(){

    }
    public CategoryDao(String id,String cateName){
        this.id = id;
        this.cateName = cateName;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}

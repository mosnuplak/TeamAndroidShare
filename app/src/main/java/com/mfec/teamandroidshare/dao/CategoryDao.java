package com.mfec.teamandroidshare.dao;

/**
 * Created by MSI on 8/8/2560.
 */

public class CategoryDao {
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

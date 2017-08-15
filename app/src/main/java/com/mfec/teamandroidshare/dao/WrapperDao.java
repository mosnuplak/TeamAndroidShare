package com.mfec.teamandroidshare.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MSI on 15/8/2560.
 */

public class WrapperDao {
    @SerializedName("category")
    private CategoryDao categoryDao;

    @SerializedName("topic")
    private TitleDao titleDao;

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public TitleDao getTitleDao() {
        return titleDao;
    }

    public void setTitleDao(TitleDao titleDao) {
        this.titleDao = titleDao;
    }
}


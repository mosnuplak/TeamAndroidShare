package com.mfec.teamandroidshare.dao;

/**
 * Created by MSI on 15/8/2560.
 */

public class WrapperDao {
    private CategoryDao categoryDao;
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


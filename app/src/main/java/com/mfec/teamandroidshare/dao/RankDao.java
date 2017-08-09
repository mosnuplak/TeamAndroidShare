package com.mfec.teamandroidshare.dao;

/**
 * Created by lenovoNB on 08-Aug-17.
 */

public class RankDao {
    private String tv_num;
    private String tv_name;

    public RankDao(String tv_num, String tv_name) {
        this.tv_num = tv_num;
        this.tv_name = tv_name;
    }

    public String getTv_num() {
        return tv_num;
    }

    public String getTv_name() {
        return tv_name;
    }
}

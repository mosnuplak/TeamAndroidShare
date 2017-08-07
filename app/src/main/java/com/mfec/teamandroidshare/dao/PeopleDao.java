package com.mfec.teamandroidshare.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by E5-473G on 8/4/2017.
 */

public class PeopleDao {
    @SerializedName("knownAsName")
    private String KnownAsName;

    public PeopleDao(String knownAsName){
        this.KnownAsName = knownAsName;
    }
    public String getKnownAsName() {
        return KnownAsName;
    }

    public void setKnownAsName(String knownAsName) {
        KnownAsName = knownAsName;
    }
}

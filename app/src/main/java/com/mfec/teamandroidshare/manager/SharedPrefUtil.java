package com.mfec.teamandroidshare.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by MSI on 23/8/2560.
 */

public class SharedPrefUtil {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private String PREF_SHARE = "prefShared";
    private int PREF_MODE = Context.MODE_PRIVATE;
    private Context mContext;
    private String NAME = "name";
    private String USERID = "userId";

    public SharedPrefUtil(Context mContext) {
        this.mContext = mContext;
        this.sharedPreferences = mContext.getSharedPreferences(PREF_SHARE, PREF_MODE);
        this.editor = sharedPreferences.edit();
    }


    public void saveUserId(String userId){
        if (sharedPreferences.contains(USERID)) {
            editor.remove(USERID);
        }
        editor.putString(USERID,userId);
        editor.commit();
    }

    public void saveName(String name){
        if (sharedPreferences.contains(NAME)) {
            editor.remove(NAME);
        }
        editor.putString(NAME,name);
        editor.commit();
    }

    public String getUserId() {
        return sharedPreferences.getString(USERID, "");
    }

    public String getName() {
        return sharedPreferences.getString(NAME, "");
    }


    public void clearSharedPref() {
        editor.clear();
        editor.commit();
    }
}


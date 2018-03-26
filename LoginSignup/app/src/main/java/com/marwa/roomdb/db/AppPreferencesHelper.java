package com.marwa.roomdb.db;

import android.content.Context;
import android.content.SharedPreferences;


public class AppPreferencesHelper {

    private static final String PREF_KEY_LOGGED = "PREF_LOGGED";
    private static final String PREF_KEY_USERID = "PREF_USERID";


    private final SharedPreferences mPrefs;


    public AppPreferencesHelper(Context context, String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }


    public void setLogged(Boolean logged) {
        mPrefs.edit().putBoolean(PREF_KEY_LOGGED, logged).apply();
    }


    public Boolean getLogged() {
        return mPrefs.getBoolean(PREF_KEY_LOGGED, false);
    }

    public void setUserId(int userId) {
        mPrefs.edit().putInt(PREF_KEY_USERID, userId).apply();
    }


    public int getuserId() {
        return mPrefs.getInt(PREF_KEY_USERID, 0);
    }





}

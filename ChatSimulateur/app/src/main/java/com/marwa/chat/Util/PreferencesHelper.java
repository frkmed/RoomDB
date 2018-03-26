package com.marwa.chat.Util;

import android.content.Context;
import android.content.SharedPreferences;


public class PreferencesHelper {

    private static final String PREF_KEY_LOGGED = "PREF_LOGGED";
    private static final String PREF_KEY_USERA = "PREF_USERA";
    private static final String PREF_KEY_USERB = "PREF_USERB";

    private final SharedPreferences mPrefs;

    public PreferencesHelper(Context context, String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    public void setLogged(Boolean logged) {
        mPrefs.edit().putBoolean(PREF_KEY_LOGGED, logged).apply();
    }

    public Boolean getLogged() {
        return mPrefs.getBoolean(PREF_KEY_LOGGED, false);
    }

    public void setUserA(String userA) {
        mPrefs.edit().putString(PREF_KEY_USERA, userA).apply();
    }

    public String getuserA() {
        return mPrefs.getString(PREF_KEY_USERA, "");
    }

    public void setUserB(String userB) {
        mPrefs.edit().putString(PREF_KEY_USERB, userB).apply();
    }

    public String getuserB() {
        return mPrefs.getString(PREF_KEY_USERB, "");
    }





}

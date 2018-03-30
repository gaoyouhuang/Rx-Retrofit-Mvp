package com.example.scs.rxretrofitmvp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by scs on 18-3-12.
 */

public class SharedPreferencesManager {
    private static SharedPreferencesManager manager;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String SHARE_NAME = "MySharePManager";

    private static final String Key_UserToken = "UserToken";

    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static synchronized void init(Context context) {
        if (manager == null)
            manager = new SharedPreferencesManager(context);
    }

    public static synchronized SharedPreferencesManager getInstance() {
        if (manager == null) {
            throw new RuntimeException("SharedPreferencesManager is null");
        }
        return manager;
    }

    public void setUserToken(String token) {
        editor.putString(Key_UserToken, token);
        editor.commit();
    }

    public String getUserTken() {
        return sharedPreferences.getString(Key_UserToken, "");
    }

}

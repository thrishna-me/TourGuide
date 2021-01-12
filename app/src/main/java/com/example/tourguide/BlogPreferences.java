package com.example.tourguide;

import android.content.Context;
import android.content.SharedPreferences;

class BlogPreferences {
    public static final String KEY_LOGIN_STATE = "key_login_state";

    private SharedPreferences preferences;

    BlogPreferences(Context context) {
        preferences = context.getSharedPreferences(KEY_LOGIN_STATE, Context.MODE_PRIVATE);
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(KEY_LOGIN_STATE, false);
    }

    public void setLoggedIn(boolean loggedIn) {
        //edit method returns the editor object and apply method stores the data into memory
        preferences.edit().putBoolean(KEY_LOGIN_STATE, loggedIn).apply();
    }


}

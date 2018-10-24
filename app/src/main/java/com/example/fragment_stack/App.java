package com.example.fragment_stack;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import modules.general.utils.LanguageUtil;
import modules.general.utils.Prefs;


public class App extends Application {

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
     }

    public void onCreate() {
        super.onCreate();
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(false)
                .build();
         LanguageUtil.setAppLanguage(getApplicationContext(),  LanguageUtil.getAppLanguage());

    }

}

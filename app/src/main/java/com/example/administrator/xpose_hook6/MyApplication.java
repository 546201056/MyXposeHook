package com.example.administrator.xpose_hook6;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import org.xutils.x;

/**
 * Created by shoewann on 2017/9/6 0006.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
    }
}

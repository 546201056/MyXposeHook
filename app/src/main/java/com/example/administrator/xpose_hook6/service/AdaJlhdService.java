package com.example.administrator.xpose_hook6.service;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


import com.example.administrator.xpose_hook6.utils.SharedPreferencesHelper;
import com.example.administrator.xpose_hook6.utils.ShellUtils;
import com.example.administrator.xpose_hook6.utils.TapSwipe;

import static android.content.ContentValues.TAG;

/**
 * UC
 * Created on 2018/1/4.
 */

public class AdaJlhdService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferencesHelper.init(this);
        SharedPreferencesHelper.getInstance().saveData(Const.NUM,111);
        SharedPreferencesHelper.getInstance().getData(Const.NUM,-1);
//        ShellUtils.execCommand("am start -a android.intent.action.VIEW -d "+ Const.Url, true);
        //打开hook修改参数
//        ShellUtils.execCommand("am start -n com.example.administrator.xpose_hook6/com.example.administrator.xpose_hook6.MainActivity", true);

//        ShellUtils.execCommand("am force-stop com.UCMobile", true);



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        ShellUtils.execCommand("am force-stop com.UCMobile", true);
                        Thread.sleep(2000);

                        Intent intent2 = new Intent();
                        intent2.setAction("android.intent.action.VIEW");
                        String url = "http://m.uczzd.cn/webview/newslist?app=tucaojieh5-iflow&zzd_from=tucaojieh5-iflow&uc_param_str=dndsfrvesvntnwpfgi&uc_biz_str=S%3Acustom%257CC%3Azzd_list&is_hide_top=1&is_hide_bottom=1";
                        Uri content_url = Uri.parse(url);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent2.setData(content_url);
                        startActivity(intent2);
                        Thread.sleep(4000);
                        TapSwipe.TapUc();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
        //服务关闭，就重启手机
//        ShellUtils.execCommand("reboot", true);
    }
}

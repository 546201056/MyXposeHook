package com.example.administrator.xpose_hook6;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/7/20/020.
 */

public class XposeHook implements IXposedHookLoadPackage {


    public static final String XPOSED_REPLACE_COORDINATE = "XPOSED_REPLACE_COORDINATE";
      private XSharedPreferences sp =    new XSharedPreferences("com.example.administrator.xpose_hook6", XPOSED_REPLACE_COORDINATE);

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {


        if(android.os.Process.myUid() <= 10000){
            Log.d("XposeHook","系统应用"+loadPackageParam.packageName+android.os.Process.myUid());
            return ;
        }else{
            Log.d("XposeHook","普通应用"+loadPackageParam.packageName+android.os.Process.myUid());
        }

        //com.ss.android.article.news
       if(!loadPackageParam.packageName.equals("com.sina.weibo") && !loadPackageParam.packageName.equals("com.example.myxposed")&& !loadPackageParam.packageName.equals("com.soft.apk008v")
               && !loadPackageParam.packageName.equals("com.qiyi.video") && !loadPackageParam.packageName.equals("com.example.administrator.xpose_hook6")
               &&!loadPackageParam.packageName.equals("com.ss.android.article.news")&&!loadPackageParam.packageName.equals("com.android.chrome"))
                    return;

        Log.e("11111111111111111", "handleLoadPackage: 1111111111" +loadPackageParam.packageName);
        hookPhone(loadPackageParam,sp);

        //劫持指定的方法
        addHookMethod(loadPackageParam.packageName, TelephonyManager.class.getName(), loadPackageParam.classLoader, "getDeviceId", new Object[]{});
        //更改imei号
        addHookMethod(loadPackageParam.packageName, Settings.Secure.class.getName(), loadPackageParam.classLoader, "getString", new Object[]{ContentResolver.class.getName(), String.class.getName()});
        addHookMethod(loadPackageParam.packageName, Settings.System.class.getName(), loadPackageParam.classLoader, "getString", new Object[]{ContentResolver.class.getName(), String.class.getName()});
        addHookMethod(loadPackageParam.packageName, TelephonyManager.class.getName(), loadPackageParam.classLoader, "getLine1Number", new Object[]{});
        addHookMethod(loadPackageParam.packageName, TelephonyManager.class.getName(), loadPackageParam.classLoader, "getSimSerialNumber", new Object[]{});
        addHookMethod(loadPackageParam.packageName, TelephonyManager.class.getName(), loadPackageParam.classLoader, "getSubscriberId", new Object[]{});
        addHookMethod(loadPackageParam.packageName, TelephonyManager.class.getName(), loadPackageParam.classLoader, "getSimOperator", new Object[]{});
        addHookMethod(loadPackageParam.packageName, TelephonyManager.class.getName(), loadPackageParam.classLoader, "getNetworkOperatorName", new Object[]{});
        addHookMethod(loadPackageParam.packageName, TelephonyManager.class.getName(), loadPackageParam.classLoader, "getNetworkType", new Object[]{});
        addHookMethod(loadPackageParam.packageName, TelephonyManager.class.getName(), loadPackageParam.classLoader, "getPhoneType", new Object[]{});
        addHookMethod(loadPackageParam.packageName, TelephonyManager.class.getName(), loadPackageParam.classLoader, "getSimState", new Object[]{});

        addHookMethod(loadPackageParam.packageName, WifiInfo.class.getName(), loadPackageParam.classLoader, "getMacAddress", new Object[]{});
        addHookMethod(loadPackageParam.packageName, WifiInfo.class.getName(), loadPackageParam.classLoader, "getSSID", new Object[]{});
        addHookMethod(loadPackageParam.packageName, WifiInfo.class.getName(), loadPackageParam.classLoader, "getBSSID", new Object[]{});

        addHookMethod(loadPackageParam.packageName, Build.class.getName(), loadPackageParam.classLoader, "getRadioVersion", new Object[]{});
        addHookMethod(loadPackageParam.packageName, BluetoothAdapter.class.getName(), loadPackageParam.classLoader, "getAddress", new Object[]{});

        addHookMethod(loadPackageParam.packageName, NetworkInfo.class.getName(), loadPackageParam.classLoader, "getTypeName", new Object[]{});
        addHookMethod(loadPackageParam.packageName, NetworkInfo.class.getName(), loadPackageParam.classLoader, "getType", new Object[]{});
        addHookMethod(loadPackageParam.packageName, NetworkInfo.class.getName(), loadPackageParam.classLoader, "getSubtype", new Object[]{});
        addHookMethod(loadPackageParam.packageName, NetworkInfo.class.getName(), loadPackageParam.classLoader, "getSubtypeName", new Object[]{});
        addHookMethod(loadPackageParam.packageName, NetworkInfo.class.getName(), loadPackageParam.classLoader, "getExtraInfo", new Object[]{});
        addHookMethod(loadPackageParam.packageName, ConnectivityManager.class.getName(), loadPackageParam.classLoader, "getNetworkInfo", new Object[]{Integer.TYPE.getName()});

        addHookMethod(loadPackageParam.packageName, ActivityManager.class.getName(), loadPackageParam.classLoader, "getRunningAppProcesses", new Object[]{});
        addHookMethod(loadPackageParam.packageName, "android.app.ApplicationPackageManager", loadPackageParam.classLoader, "getInstalledPackages", new Object[]{Integer.TYPE.getName()});
        addHookMethod(loadPackageParam.packageName, "android.app.ApplicationPackageManager", loadPackageParam.classLoader, "getPackageInfo", new Object[]{String.class.getName(), Integer.TYPE.getName()});
        addHookMethod(loadPackageParam.packageName, "android.app.ApplicationPackageManager", loadPackageParam.classLoader, "getApplicationInfo", new Object[]{String.class.getName(), Integer.TYPE.getName()});
        addHookMethod(loadPackageParam.packageName, "android.app.ApplicationPackageManager", loadPackageParam.classLoader, "getInstalledApplications", new Object[]{Integer.TYPE.getName()});

        addHookMethod(loadPackageParam.packageName, "android.os.SystemProperties", loadPackageParam.classLoader, "getInt", new Object[]{String.class.getName()});
        addHookMethod(loadPackageParam.packageName, "android.content.ContextWrapper", loadPackageParam.classLoader, "getExternalCacheDir", new Object[]{});

        //劫持构造方法
        addHookConstructor(loadPackageParam.packageName, File.class.getName(), loadPackageParam.classLoader, new Object[]{String.class.getName()});
        addHookConstructor(loadPackageParam.packageName, File.class.getName(), loadPackageParam.classLoader, new Object[]{String.class.getName(), String.class.getName()});
        addHookConstructor(loadPackageParam.packageName, FileReader.class.getName(), loadPackageParam.classLoader, new Object[]{String.class.getName()});
        addHookConstructor(loadPackageParam.packageName, FileReader.class.getName(), loadPackageParam.classLoader, new Object[]{File.class.getName()});


    }



    //劫持指定方法
    public void addHookMethod(final String packageName, final String className, ClassLoader classLoader, final String methodName, Object[] parameterTypesAndCallback){

        XC_MethodHook xc_methodHook = new XC_MethodHook() {
            //方法调用前劫持，将参数替换成指定参数，实现屏蔽指定的包名
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                if("getPackageInfo".equals(methodName) ){
                    if(param.args[0].equals(XposeUtil.pkg1) || param.args[0].equals(XposeUtil.pkg2) || param.args[0].equals(XposeUtil.pkg3)){
                        param.args[0] = "yyyy.mmmm.aaaa.xxxx";
                    }
                }else
                if("getApplicationInfo".equals(methodName) ){
                    if(param.args[0].equals(XposeUtil.pkg1) || param.args[0].equals(XposeUtil.pkg2) || param.args[0].equals(XposeUtil.pkg3)){
                        param.args[0] = "yyyy.mmmm.aaaa.xxxx";
                    }
                }
            }
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        L.log("android.os.SystemProperties获取序列号");

                if("get".equals(methodName) && className.equals("android.os.SystemProperties")){
                    XposedBridge.log("ooooo="+param.args[0] );
                    if(param.args[0].equals("ro.serialno")){
                        String serial = "37124218946";//XposeUtil.configMap.optString(XposeUtil.m_serial);
                        if(!TextUtils.isEmpty(serial)){
                            param.setResult(serial);
                        }
                    }
                }else
                    //屏蔽自己的包名
                    if("getInstalledApplications".equals(methodName) ){
                        List<ApplicationInfo> installedApplications = (List<ApplicationInfo>) param.getResult();
                        for (int i = installedApplications.size() - 1; i >= 0 ; i--) {
                            ApplicationInfo applicationInfo = installedApplications.get(i);
                            if(applicationInfo.equals(XposeUtil.pkg1) || applicationInfo.equals(XposeUtil.pkg2) || applicationInfo.equals(XposeUtil.pkg3)){
                                installedApplications.remove(i);
                            }
                        }
                        param.setResult(installedApplications);
                    }else
                        ////屏蔽自己
                        if("getRunningAppProcesses".equals(methodName) ){
                            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = (List<ActivityManager.RunningAppProcessInfo>) param.getResult();
                            for (int i = runningAppProcesses.size() - 1; i >= 0; i--) {
                                ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i);
                                if(runningAppProcessInfo.processName.equals(XposeUtil.pkg1) || runningAppProcessInfo.processName.equals(XposeUtil.pkg2) || runningAppProcessInfo.processName.equals(XposeUtil.pkg3)){
                                    runningAppProcesses.remove(i);
                                }
                            }
                            param.setResult(runningAppProcesses);
                        }else
                            //屏蔽自己
                            if("getInstalledPackages".equals(methodName) ){
                                List<PackageInfo> installedPackages = (List<PackageInfo>) param.getResult();
                                for (int i = installedPackages.size() - 1; i >= 0; i--) {
                                    String s = installedPackages.get(i).packageName;
                                    if(s.equals(XposeUtil.pkg1) || s.equals(XposeUtil.pkg2) || s.equals(XposeUtil.pkg3)){
                                        Log.d("XposeHook","getInstalledPackages+移除"+s);
                                        installedPackages.remove(i);
                                    }
                                }
                                param.setResult(installedPackages);
                            }else
                                //蓝牙地址
                                if("getAddress".equals(methodName)){
//                                    String m_bluetoothaddress = XposeUtil.configMap.optString(XposeUtil.m_bluetoothaddress);
                                    String m_bluetoothaddress =sp.getString("getAddress","");
                                    if(!TextUtils.isEmpty(m_bluetoothaddress)){
                                        param.setResult(m_bluetoothaddress);
                                    }else{
                                    }
                                }else
                                    //固件版本
                                    if("getRadioVersion".equals(methodName)){
//                                        String m_firmwareversion = XposeUtil.configMap.optString(XposeUtil.m_firmwareversion);
                                        String m_firmwareversion =sp.getString("getRadioVersion","");
                                        if(!TextUtils.isEmpty(m_firmwareversion)){
                                            Log.d("XposeHook","修改m_firmwareversion");
                                            param.setResult(m_firmwareversion);
                                        }else{
                                            Log.d("XposeHook","获取m_firmwareversion为空");
                                        }
                                    }else
                                        //无线路由地址
                                        if("getBSSID".equals(methodName)){
//                                            String m_BSSID = "";
//                                            String m_BSSID =XposeUtil.configMap.optString(XposeUtil.m_BSSID);//"24:69:21:e4:b7:1a"
                                            String m_BSSID=sp.getString("BSSID","");
                                            if(!TextUtils.isEmpty(m_BSSID)){
                                                Log.d("XposeHook","修改m_BSSID");
                                                param.setResult(m_BSSID);
                                            }else{
                                                Log.d("XposeHook","获取m_BSSID为空");
                                            }
                                        }else
                                            //无线路由名
                                            if("getSSID".equals(methodName)){
//                                                String m_SSID = XposeUtil.configMap.optString(XposeUtil.m_SSID);
                                                String m_SSID =sp.getString("SSID","");
                                                if(!TextUtils.isEmpty(m_SSID)){
                                                    Log.d("XposeHook","修改m_SSID");
                                                    param.setResult(m_SSID);
                                                }else{
                                                    Log.d("XposeHook","获取m_SSID为空");
                                                }
                                            }else
                                                //mac地址
                                                if("getMacAddress".equals(methodName)){
                                                    String m_macAddress =sp.getString("WIFIMAC","");
                                                    Log.d("m_macAddress", "m_macAddress========> "+m_macAddress);
                                                    if(!TextUtils.isEmpty(m_macAddress)){
                                                        param.setResult(m_macAddress);
                                                        Log.d("XposeHook","修改m_macAddress");
                                                    }else{
                                                        Log.d("XposeHook","获取m_macAddress为空");
                                                    }
                                                }else
                                                    //手机卡状态
                                                    if("getSimState".equals(methodName)){
                                                        int m_simState=sp.getInt("getSimState",-1);
                                                        if(m_simState != -1)
                                                            param.setResult(5);
                                                        else
                                                            param.setResult(m_simState);

                                                    }else
                                                        //手机类型
                                                        if("getPhoneType".equals(methodName)){
                                                            int m_phoneType = sp.getInt("getPhoneType",-1);
                                                            if(m_phoneType != -1)
                                                                param.setResult(m_phoneType);
                                                            else
                                                                param.setResult(m_phoneType);

                                                        }else
                                                            //网络类型
                                                            if("getNetworkType".equals(methodName)){
                                                                int m_networkType = sp.getInt("getNetworkType",-1);
                                                                if(m_networkType != -1)
                                                                    param.setResult(m_networkType);
                                                                else
                                                                    param.setResult(m_networkType);

                                                            }else
                                                                //网络类型名
                                                                if("getNetworkOperatorName".equals(methodName)){
                                                                    String networkOperatorName = sp.getString("getNetworkOperatorName","");
//                                                                    String networkOperatorName = XposeUtil.configMap.optString(XposeUtil.m_networkOperatorName);
                                                                    if(!TextUtils.isEmpty(networkOperatorName)){
                                                                        Log.d("XposeHook","修改networkOperatorName");
                                                                        param.setResult(networkOperatorName);
                                                                    }else{
                                                                        Log.d("XposeHook","获取networkOperatorName为空");
                                                                    }
                                                                }else
                                                                    //运营商
                                                                    if("getSimOperator".equals(methodName)){
//                                                                        String simOperator = XposeUtil.configMap.optString(XposeUtil.m_simOperator);
                                                                        String simOperator =sp.getString("getSimOperator","");
                                                                        if(!TextUtils.isEmpty(simOperator)){
                                                                            Log.d("XposeHook","修改simOperatord");
                                                                            param.setResult(simOperator);
                                                                        }else{
                                                                            Log.d("XposeHook","获取simOperator为空");
                                                                        }
                                                                    }else
                                                                        //IMSI
                                                                        if("getSubscriberId".equals(methodName)){
//                                                                            String subscriberId = XposeUtil.configMap.optString(XposeUtil.m_subscriberId);//460013045362135
                                                                            String subscriberId=sp.getString("getSubscriberId","");
                                                                            if(!TextUtils.isEmpty(subscriberId)){
                                                                                Log.d("XposeHook","修改subscriberId");
                                                                                param.setResult(subscriberId);
                                                                            }else{
                                                                                Log.d("XposeHook","获取subscriberId为空");
                                                                            }
                                                                        }else
                                                                            //手机卡序列号
                                                                            if("getSimSerialNumber".equals(methodName)){
//                                                                                String simSerialNumber = XposeUtil.configMap.optString(XposeUtil.m_simSerialNumber);//89862336817032296354
                                                                                String simSerialNumber = sp.getString("getSimSerialNumber","");//89862336817032296354
                                                                                if(!TextUtils.isEmpty(simSerialNumber)){
                                                                                    Log.d("XposeHook","修改simSerialNumber");
                                                                                    param.setResult(simSerialNumber);
                                                                                }else{
                                                                                    Log.d("XposeHook","获取simSerialNumber为空");
                                                                                }
                                                                            }else
                                                                                //电话号码
                                                                                if("getLine1Number".equals(methodName)){
//                                                                                    String phoneNum = XposeUtil.configMap.optString(XposeUtil.m_phoneNum);//"+8615653115241"
                                                                                    String phoneNum =sp.getString("getLine1Number","");//"+8615653115241"
                                                                                    if(!TextUtils.isEmpty(phoneNum)){
                                                                                        Log.d("XposeHook","修改phoneNum");
                                                                                        param.setResult(phoneNum);
                                                                                    }else{
                                                                                        Log.d("XposeHook","获取phoneNum为空");
                                                                                    }
                                                                                }else
                                                                                    //android_id
                                                                                    if("getString".equals(methodName) && param.args[1].equals("android_id")){
//                                                                                        String androidId = "ff2d9dede9915e55";//XposeUtil.configMap.optString(XposeUtil.m_androidId);
//
                                                                                        String androidId=sp.getString("ANDROID_ID","be55e0220879d0d9");
                                                                                        XposedBridge.log("android_id=======>"+androidId);
                                                                                        if(!TextUtils.isEmpty(androidId)){
                                                                                            Log.d("XposeHook","修改androidId");
                                                                                            param.setResult(androidId);
                                                                                        }else{
                                                                                            Log.d("XposeHook","获取androidId为空");

                                                                                        }
                                                                                    }else
                                                                                        //device_id
                                                                                        if("getDeviceId".equals(methodName)){
//                                                                                            Log.d("XposeHook","packageName" + packageName + "configMap" + XposeUtil.configMap.toString());
//                                                                                            XposeUtil.initConfigMap();
//                                                                                            String deviceid = "867789023951700";//XposeUtil.configMap.optString(XposeUtil.m_deviceId);

                                                                                            String deviceid=sp.getString("getDeviceId","863925028142978");
                                                                                            XposedBridge.log("device_id=======>"+deviceid);
                                                                                            if(!TextUtils.isEmpty(deviceid)){
                                                                                                Log.d("XposeHook","修改deviceid");
                                                                                                param.setResult(deviceid);
                                                                                            }else{
                                                                                                Log.d("XposeHook","获取deviceid为空");

                                                                                            }
                                                                                        }else
                                                                                        if("testXpose".equals(methodName)){
                                                                                            param.setResult(1);
                                                                                        }

            }
        };

        //执行hook方法findAndHookMethod的param值为参数+回调的可变参数，故要将回调加入进去
        Object [] param = new Object[parameterTypesAndCallback.length + 1];
        for (int i = 0; i < param.length; i++) {
            if(i == param.length-1){
                param[param.length - 1] = xc_methodHook;
                XposedHelpers.findAndHookMethod(className, classLoader, methodName, param);
                return ;
            }
            param[i] = parameterTypesAndCallback[i];
        }
    }

    //劫持构造方法
    public void addHookConstructor(final String packageName,String className,ClassLoader classLoader,Object[] parameterTypesAndCallback){

        XC_MethodHook xc_methodHook = new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);

                //监听File实例构建，实现监听文件的操作
                if (XposeUtil.configMap.optBoolean(XposeUtil.FileRecordPackageNameSwitch) && XposeUtil.configMap.optString(XposeUtil.FileRecordPackageName).contains(packageName)) {
                    String attr = "";
                    if(param.args[0]instanceof File){
                        attr = ((File) param.args[0]).getAbsolutePath();
                    }else if(param.args.length > 1 && param.args[1] != null ){
                        String separator = "";
                        if(!param.args[0].toString().endsWith("/"))
                            separator = "/";
                        attr =  param.args[0].toString() + separator + param.args[1].toString();
                    }else{
                        attr = (String) param.args[0];
                    }
                    /*
                    if (attr.contains(RecordFileUtil.ExternalStorage) && !attr.contains("xpose")
                            && !(attr.startsWith(RecordFileUtil.ExternalStorage+RecordFileUtil.FILE_PATH_RECORD))
                            && RecordFileUtil.addFileRecord(packageName, attr)) ;*/
                }
            }
        };

        //执行hook方法findAndHookConstructor的param值为参数+回调的可变参数，故要将回调加入进去
        Object [] param = new Object[parameterTypesAndCallback.length + 1];
        for (int i = 0; i < param.length; i++) {
            if(i == param.length-1){
                param[param.length - 1] = xc_methodHook;
                XposedHelpers.findAndHookConstructor(className,classLoader,param);
                return ;
            }
            param[i] = parameterTypesAndCallback[i];
        }
    }
    private void HookMethod(final Class clazz, final String method, final String result){
        try{
            XposedHelpers.findAndHookMethod(clazz, method, new Object[] { new XC_MethodHook() {
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(result);
                }
            } });
        } catch (Throwable e){
            e.printStackTrace();
        }
    }

    private void hookPhone(XC_LoadPackage.LoadPackageParam lpparam,final SharedPreferences sp){
        //更改imei号
        HookMethod(TelephonyManager.class, "getDeviceId", sp.getString("getDeviceId", "863925028142978"));
        //更改品牌
        XposedHelpers.setStaticObjectField(Build.class, "BRAND", sp.getString("BRAND", Build.BRAND));
        Log.e(TAG, "Lixiang: "+sp.getString("BRAND", Build.BRAND) );
        //更改型号
        XposedHelpers.setStaticObjectField(Build.class, "MODEL", sp.getString("MODEL", Build.MODEL));
        //更改DISPLAY
        XposedHelpers.setStaticObjectField(Build.class, "DISPLAY", sp.getString("DISPLAY", Build.DISPLAY));
        //更改REALSE
        XposedHelpers.setStaticObjectField(Build.VERSION.class, "RELEASE", sp.getString("RELEASE", Build.VERSION.RELEASE));
        //更改SDK
        XposedHelpers.setStaticObjectField(Build.VERSION.class, "SDK", sp.getString("SDK", Build.VERSION.SDK));
        //更改厂商MANUFACTURER
        XposedHelpers.setStaticObjectField(Build.class, "MANUFACTURER", sp.getString("MANUFACTURER", Build.MANUFACTURER));
        //更改Android_ID
        XposedHelpers.findAndHookMethod(Settings.Secure.class.getName(), lpparam.classLoader, "getString", ContentResolver.class, String.class, new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                return sp.getString("ANDROID_ID", "be55e0220879d0d9");
            }
        });
        //更改Mac地址
        XposedHelpers.findAndHookMethod(android.net.wifi.WifiInfo.class.getName(), lpparam.classLoader, "getMacAddress", new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                return sp.getString("WIFIMAC", "");
            }
        });
        //更改SSID
        XposedHelpers.findAndHookMethod(android.net.wifi.WifiInfo.class.getName(), lpparam.classLoader, "getSSID", new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                return sp.getString("SSID", "");
            }
        });
    }
}

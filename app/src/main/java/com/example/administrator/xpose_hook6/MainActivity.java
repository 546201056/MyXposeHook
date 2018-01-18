package com.example.administrator.xpose_hook6;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.xpose_hook6.utils.ShellUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;


/**
 * 手机型号(0)，手机品牌(1)，SDK版本(2)，手机版本(3)，device_id(4)，IME号（android_id）(5)，分辨率(6)，屏幕密度(7)，mac地址(8)，无线路由器地址(9)，无线路由器名字(10)，IMSI(11)，产品(12)，制造商(13)，蓝牙地址(14)，固件版本(15)，手机卡状态（Int）(16),手机类型(int)(17),网络类型(int)(18),网络类型名(19),运营商(20),手机卡序列号(21),电话号码(22)
 *SM- J7108,samsung,23,6.0.1,357212074848144,82a13111a639c2a,1080*1920,480,b3:n3:m3:j4:k2:p2,b3:n3:m3:j4:k2:p2,XM,12345678911,as,hu,b3:n3:m3:j4:k2:p2,15,1,1,1,xiao,xiao,12345678912,18712312312
 *
 *
 * SDK  ,品牌,型号,REALSE,DISPLAY,厂商MANUFACTURER,蓝牙地址,固件版本,无线路由地址,无线路由名,mac地址,手机卡状态,手机类型,网络类型,网络类型名,运营商,IMSI,手机卡序列号,电话号码,android_id,device_id imei号
 *
 * android中有mtpd命令可以连接vpn
 在pc上执行adb shell进入控制台
 执行 mtpd
 输出
 mtpd interface 12tp <server> <port> <secret> pppd-arguments
 mtpd interface pptp <server> <port> pppd-arguments

 实例如下:

 mtpd wlan0  pptp a.ueuz.com 1723 name d11234 password 1234 linkname vpn refuse-eap  nodefaultroute idle 1800 mtu 1400 mru 1400 nomppe unit 100\
 mtpd的所有参数解释在这个链接:https://ppp.samba.org/pppd.html
 参数解释:
 wlan0 使用的接口
 pptp  协议
 a.ueuz.com  服务器地址
 1723 端口号
 name d11234  账号是d11234
 password 1234 密码是1234
 linkname vpn  连接的名称
 refuse-eap  不使用eap
 nodefaultroute 不使用默认路由
 idle 1800 心跳间隔
 mtu 1400   一个包最大发送1400字节
 mru 1400   一个包最大接收1400字节
 nomppe  不使用微软点对点加密
 unit 100  接口名称，连接名称 如果连接成功 通过 ip ro命令可以看到类似0.0.0.0/1 dev ppp100  scope link的字符串，ppp的后缀100就是这里设置的
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    //String command = "pm clear " + packageName + "\n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txt= (TextView) findViewById(R.id.text);
        EditText edit= (EditText) findViewById(R.id.edit);
        Button btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    setVpn("192.168.1.1","","");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        putEditorSp();
    }

    private void putEditorSp(){
        SharedPreferences.Editor editor = getSharedPreferences(XposeHook.XPOSED_REPLACE_COORDINATE, Context.MODE_WORLD_READABLE).edit();
//SDK  ,品牌,型号,REALSE,DISPLAY,厂商MANUFACTURER,蓝牙地址,固件版本,无线路由地址,无线路由名,mac地址,手机卡状态,手机类型,网络类型,网络类型名,运营商,IMSI,手机卡序列号,电话号码,android_id,device_id imei号
        editor.putString("SDK","23");//更改SDK
        editor.putString("BRAND","samsung");//更改品牌
        editor.putString("MODEL","SM- J7108");//更改型号
        editor.putString("RELEASE","6.0.1"); //更改REALSE
        editor.putString("DISPLAY","1080*1920");  //更改DISPLAY
//        editor.putString("PRODUCT","samsung");
        editor.putString("MANUFACTURER","samsung");   //更改厂商MANUFACTURER
        editor.putString("getAddress","0a:5n:15:m1:4o"); //蓝牙地址
        editor.putString("getRadioVersion","1.2");  //固件版本
        editor.putString("BSSID","1234246");  //无线路由地址
        editor.putString("SSID","1246"); //无线路由名
        editor.putString("WIFIMAC","b3:n3:m3:j4:k2:p2");//mac地址
        editor.putInt("getSimState",5);//手机卡状态
        editor.putInt("getPhoneType",1);  //手机类型
        editor.putInt("getNetworkType",1);  //网络类型
        editor.putString("getNetworkOperatorName","123");//网络类型名
        editor.putString("getSimOperator","1");//运营商
        editor.putString("getSubscriberId","1255168465856"); //IMSI
        editor.putString("getSimSerialNumber","15685410323325566");//手机卡序列号
        editor.putString("getLine1Number","18752914877"); //电话号码
        editor.putString("ANDROID_ID","82a131154s984c2a"); //android_id
        editor.putString("getDeviceId","35712345948144"); //device_id  //更改imei号
        editor.apply();
        editor.commit();
    }

    /**
     *  vpn
     * @param ip
     * @param name
     * @param password
     * @throws Throwable
     */
    private void setVpn(String ip,String name,String password)throws Exception{
        new AutoTaskHelper.ShellTask().execute("mtpd wlan0 pptp " + ip + " 1723 name " + name + " password " + password +
                " linkname vpn nodefaultroute mtu 1396 mru 1396 +mppe unit 100");
        Thread.sleep(4000);
        ShellUtils.execCommand("ip -4 ro flush dev ppp100", true, true);
        Thread.sleep(4000);
        ShellUtils.execCommand("ip ro add 0.0.0.0/1 dev ppp100", true, true);
    }

    /**
     * var returnCitySN = {"cip": "171.88.40.79", "cid": "510000", "cname": "四川省"};
     */
    private  void inspectIP() {
        try {
            RequestParams params = new RequestParams("http://pv.sohu.com/cityjson?ie=utf-8");
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    try {
                        JSONObject jb = new JSONObject(result.substring(result.lastIndexOf("=")+1,result.length()).trim());
                        Log.d(TAG, "onSuccess2222: "+   jb.get("cip")  );
                    } catch (JSONException e) {
                    }
                    Log.d(TAG, "onSuccess: "+result.toString());
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.d(TAG, "onError: "+ex.toString());
                }
                @Override
                public void onCancelled(CancelledException cex) {
                    Log.d(TAG, "onCancelled: "+cex.toString());
                }
                @Override
                public void onFinished() {
                }
            });
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static class AutoTaskHelper {

        public static class ShellTask extends AsyncTask<String, Void, Boolean> {

            @Override
            protected Boolean doInBackground(String... voids) {
                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
                try {
                    ShellUtils.execCommand(voids[0], true);
                    return true;
                } catch (Exception e) {
                    //
                }
                return false;
            }
        }
    }

    public  void setWifiProxySettingsFor17And(Context context, String host, int port, String exclList) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiConfiguration config;

        config = getCurrentWifiConfiguration(wifiManager);
        if (config == null) return;

        try {
            Object linkProperties = getFieldObject(config, "linkProperties");
            if (null == linkProperties) return;
            Class<?> proxyPropertiesClass = Class.forName("android.net.ProxyProperties");
            Class<?>[] setHttpProxyParams = new Class[1];
            setHttpProxyParams[0] = proxyPropertiesClass;
            Class<?> lpClass = Class.forName("android.net.LinkProperties");

            Method setHttpProxy = lpClass.getDeclaredMethod("setHttpProxy", setHttpProxyParams);
            setHttpProxy.setAccessible(true);

            Constructor<?> proxyPropertiesCtor = proxyPropertiesClass.getConstructor(String.class, int.class, String.class);
            Object proxySettings =proxyPropertiesCtor.newInstance(host, port, exclList);
            Object[] params = new Object[1];
            params[0] = proxySettings;
            setHttpProxy.invoke(linkProperties, params);
            setEnumField(config, "STATIC", "proxySettings");
            //save the settings
            wifiManager.updateNetwork(config);
            wifiManager.disconnect();
            wifiManager.reconnect();
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "setWifiProxySettingsFor17And: e====>"+e.getMessage());
        }finally {
            inspectIP();
        }
    }

    // 设置公共成员常量值
    public static void setEnumField(Object obj, String value, String name) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        Field f = obj.getClass().getField(name);
        f.set(obj, Enum.valueOf((Class<Enum>) f.getType(), value));
    }

    // getField只能获取类的public 字段.
    public static Object getFieldObject(Object obj, String name) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field f =
                obj.getClass().getField(name);
        Object out = f.get(obj);
        return out;
    }

    // 获取当前的Wifi连接
    public static WifiConfiguration getCurrentWifiConfiguration(WifiManager wifiManager) {
        if (!wifiManager.isWifiEnabled())
            return null;
        List<WifiConfiguration> configurationList = wifiManager.getConfiguredNetworks();
        WifiConfiguration configuration = null;
        int cur = wifiManager.getConnectionInfo().getNetworkId();
        for (int i = 0; i < configurationList.size(); ++i) {
            WifiConfiguration wifiConfiguration = configurationList.get(i);
            if (wifiConfiguration.networkId == cur)
                configuration = wifiConfiguration;
        }
        return configuration;
    }


    /***
     * 查看当前应用的包名以及启动页面
     * @param resolveInfos
     */
    public void plintPkgAndCls(List<ResolveInfo> resolveInfos){
        for (int i = 0; i < resolveInfos.size(); i++) {
            String pkg = resolveInfos.get(i).activityInfo.packageName;
            String cls = resolveInfos.get(i).activityInfo.name;
            Log.i("maint", "packageName = " + pkg);
            Log.i("maint", "name = " + cls);
        }
    }

    /***
     *
     * @return
     */
    public List<ResolveInfo> getResolveInfos(){
        List<ResolveInfo> appList = null;

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager pm =getPackageManager();
        appList = pm.queryIntentActivities(intent, 0);
        Collections.sort(appList, new ResolveInfo.DisplayNameComparator(pm));

        return appList;
    }
}
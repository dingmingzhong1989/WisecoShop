package com.wiseco.wisecoshop.utils;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.tamic.statInterface.statsdk.model.header.DeviceInfo;
import com.tamic.statInterface.statsdk.model.header.NetworkInfo;
import com.tamic.statInterface.statsdk.util.NetworkUtil;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.i;
import static com.wiseco.wisecoshop.MyApplication.sContext;
import static com.wiseco.wisecoshop.utils.AppUtil.getVersionCode;
import static com.wiseco.wisecoshop.utils.HttpPostUtils.getParamsMap;
import static com.wiseco.wisecoshop.utils.SystemUtil.getDateString;
import static com.wiseco.wisecoshop.utils.SystemUtil.getDeviceInfo;
import static com.wiseco.wisecoshop.utils.SystemUtil.getSystemModel;
import static com.wiseco.wisecoshop.utils.SystemUtil.getSystemVersion;
import static com.wiseco.wisecoshop.utils.UtilsOther.getCarInfo;

/**
 * Created by wiseco on 2018/12/24.
 */

public class PointUpdataUtils {

    private static TelephonyManager mTelephonyMgr;

    /**
     * 注册时，server端将sessionID和userid进行关联。登录时候后端需要将脱敏数据返回。
     * 用户打开App的时候，App端判断当前用户是否与缓存年终的数据一致，
     * 若与本地缓存的不一致的话，需要将数据传送过来，一致的话，不用传送。
     * 用户退出登录的时候，client和server需要将cookie清除，以免sessionID和userID关联出现错误。
     **/

    public static void sendUserExt(Context context, String url) throws Exception {
/**
 * 属性	说明
 channelID	渠道id，用户从哪个渠道注册的
 tempUserID	未注册之前或无法判断出是老用户的情况下使用。
 userID	用户ID
 IDCard	脱敏后的值：110108**********12
 age	25
 mobileNumber	脱敏后的值：139****1234
 mobilePlace	手机号归属地
 operationTime	2018-12-13 16:20:20
 appVersion	1.1
 appType	H5/Android/IOS
 deviceID	IMEI号
 deviceType	OPPO R11s
 osVersion	Android 7.1.1
 comment
 **/

        Map<String, String> paramsMap = getParamsMap();
        Map<String, String> paramsMapHeader = getParamsMap();
        String ipAddress = UtilsOther.getIPAddress(sContext);
        paramsMapHeader.put("x-forwarded-for", ipAddress);
        //注册渠道
        paramsMap.put("channelID", "");

        // paramsMap.put("tempUserID", CacheUtil.getString(context, "tempUserID", ""));

        paramsMap.put("userID", CacheUtil.getString(sContext, "USERID", ""));

        String idCard = CacheUtil.getString(sContext, "IDCard", "");

        paramsMap.put("IDCard", idCard.equals("") ? "" : idCard.replace(idCard.substring(6, 16), "**********"));

        if (idCard.equals("")) {

            paramsMap.put("age", "");
        } else {

            paramsMap.put("age", getCarInfo(idCard).get("age") + "");
        }


        String mobile = CacheUtil.getString(sContext, "mobile", "");

        paramsMap.put("mobileNumber", mobile.equals("") ? "" : mobile.replaceAll(mobile.substring(3, 7), "****"));


        paramsMap.put("operationTime", getDateString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));


        paramsMap.put("appVersion", getVersionCode(context) + "." + AppUtil.getVersionName(context));

        paramsMap.put("appType", "Android");

        paramsMap.put("deviceID", getDeviceInfo(context));

        paramsMap.put("deviceType", getSystemModel());

        paramsMap.put("osVersion", getSystemVersion());


        String s = gson.toJson(paramsMap);

        String str = "{\"key\":\"userextension\",\"data\":" + s + "}";


        Map<String, String> stringMap = new HashMap<>();


        stringMap.put("data", s);
        stringMap.put("key", "userextension");
        String s1 = gson.toJson(stringMap);


        Log.d("sendUserExt", "responseresponse==========" + str);
        OkhttpUtil.okHttpPostJson(url, str, new CallBackUtil.CallBackString() {

            @Override
            public void onFailure(Call call, Exception e) {


            }

            @Override
            public void onResponse(String response) {
                try {

                    Log.d("sendUserExt", "responseresponse==========" + response);

                } catch (Exception e) {

                }


            }
        });
    }

    /**
     * 与用户网络信息相关的属性会通过此接口发送，当用户打开App的时候，App将数据传送过来，只是打开的时候进行传送就可以
     **/
    public static void sendNetwork(Context context, String url) {
        /**
         * 属性	说明
         sessionID
         (tempUserID)	使用tempUserID作为sessionID,可以从cookie里面得到
         channelID 	从哪个渠道进来的，用户从哪个渠道进来的，打开app后保证传送一次
         userID
         expValue	AB测试的条件列表
         deviceInfo	{“mac”:”48:db:50:d6:10:18”,”resolution”:”720*1184”}
         networkInfo	{“carrier”:”中国电信”,”wifi_ind”:true, “latitude”:”0.0”,”longitude”}}
         ipaddress	128.1.1.1
         location	{"latitude":"0.0","longitude":"0.0"}
         address	北京东城区隆福大厦
         city	北京
         operationTime	2018-12-16 10:12:19
         browserType

         {"key":"requestenv", 

         "value":{"channelID":"10001",
         "tempUserID":"T1000201",
         "userID":"1000214",
         "expValue":"{1,2,2,2,2,2}",
         "deviceInfo":{"mac":"48:db:50:d6:10:18","resolution":"720*1184"},
         "ipaddress":"128.1.1.1",
         "location":{"latitude":"0.0","longitude":"0.0"},
         "city":"北京",
         "operationTime":"2018-12-16 10:12:19",


         "appVersion":"1.0",
         "appType":"Android",
         "deviceID":"11111008",
         "deviceType":"OPPO R11s",
         "osVersion":"Android 7.1.1"}


         **/


        Map<String, String> paramsMap = getParamsMap();
        Map<String, String> paramsMapHeader = getParamsMap();
        String ipAddress = UtilsOther.getIPAddress(sContext);
        paramsMapHeader.put("x-forwarded-for", ipAddress);
        //注册渠道
        paramsMap.put("channelID", "");
        paramsMap.put("userID", CacheUtil.getString(sContext, "USERID", ""));

        paramsMap.put("location", "{" + "latitude:" + CacheUtil.getString(sContext, "LOCATION_LATITUDE_SP_KEY", "") +
                ",longitude:" + CacheUtil.getString(sContext, "LOCATION_lONGITUDE_SP_KEY", "") + "}");


        String address = LocationUtil.getAddress(sContext, Double.parseDouble(CacheUtil.getString(sContext, "LOCATION_LATITUDE_SP_KEY", "")),
                Double.parseDouble(CacheUtil.getString(sContext, "LOCATION_lONGITUDE_SP_KEY", "")));

        // paramsMap.put("address", address.replace(",", ""));
        paramsMap.put("city", address.split(",")[0]);
        paramsMap.put("operationTime", getDateString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));

        paramsMap.put("deviceInfo", gson.toJson(getDevice(context)));
        paramsMap.put("networkInfo", gson.toJson(getNetWorkInfo(context)));
        paramsMap.put("appType", "1");
       // Log.d("sendUserExt", "getDevice==========" +   gson.toJson(getDevice(context)) +"getNetWorkInfo==========" +gson.toJson( getNetWorkInfo(context)));

        paramsMap.put("deviceID", getDeviceInfo(context));

        paramsMap.put("deviceType", getSystemModel());

        paramsMap.put("osVersion", getSystemVersion());
        String s = gson.toJson(paramsMap);
        Log.d("sendUserExt", "responseresponse==========" + s);

        String replace = s.replace("\\", "");
        String replace1 = replace.replace("\"{", "{");
        String replace2 = replace1.replace("}\"", "}");


        String str = "{\"key\":\"requestenv\",\"value\":" + replace2 + "}";
        Log.d("sendUserExt", "replace==========" + replace2);


        // gson.toJson(getDevice(context)) ;
        // gson.toJson( getNetWorkInfo(context)) ;

        //  Log.d("sendUserExt", "getDevice==========" +   gson.toJson(getDevice(context)) +"getNetWorkInfo==========" +gson.toJson( getNetWorkInfo(context)));

        i = 4;
        OkhttpUtil.okHttpPostJson(url, str, new CallBackUtil.CallBackString() {

            @Override
            public void onFailure(Call call, Exception e) {


            }

            @Override
            public void onResponse(String response) {
                try {

                    Log.d("sendUserExt", "responseresponse==========" + response);

                } catch (Exception e) {

                }


            }
        });


    }

    /**
     * get Device Info
     *
     * @param context
     */
    private static DeviceInfo getDevice(Context context) {

        DeviceInfo deviceinfo = null;
        if (deviceinfo != null) {
            return deviceinfo;
        }
        deviceinfo = new DeviceInfo();

        // 设备ID，

        mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephonyMgr != null) {

            deviceinfo.setDevice_id(mTelephonyMgr.getDeviceId());
            // android Imei
            deviceinfo.setImei(mTelephonyMgr.getDeviceId());
        }

        // AndroidId
        try {
            String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            //  deviceinfo.setAndroid_id(androidId);
            if (TextUtils.isEmpty(deviceinfo.getImei())) {
                // deviceinfo.setImei(androidId);
            }
        } catch (Exception e) {
            // do nothing. not use the data
        }

        return deviceinfo;

    }

    /**
     * get NetWork Info
     *
     * @param context
     */
    protected static NetworkInfo getNetWorkInfo(Context context) {


        NetworkInfo networkinfo = null;
        if (networkinfo == null) {

            networkinfo = new NetworkInfo();
        }
        networkinfo.setIp_addr(NetworkUtil.getLocalIpAddress());

        networkinfo.setWifi_ind(NetworkUtil.isWifi(context));

        if (mTelephonyMgr.getSimState() == TelephonyManager.SIM_STATE_READY) {
            networkinfo.setCarrier(mTelephonyMgr.getSimOperatorName());
        }

       /* Location location = getLocation(context);
        if (location != null) {
            networkinfo.setLatitude(String.valueOf(location.getLatitude()));
            networkinfo.setLongitude(String.valueOf(location.getLongitude()));
        }*/

        return networkinfo;
    }


}







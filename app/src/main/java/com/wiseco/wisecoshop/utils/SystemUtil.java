package com.wiseco.wisecoshop.utils;

import android.content.Context;
import android.provider.Settings;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.wiseco.wisecoshop.utils.AppUtil.getVersionCode;

/**
 * Created by wiseco on 2018/11/26.
 */

public class SystemUtil {
    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

   /* *//**
     * 获取APP版本
     *
     * @param context
     * @return
     *//*
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }*/

    public static String getUserAgent(Context context) {
        String userAgent = "";
        //        APP版本
       // String versionName = SystemUtil.getVersionName(context);
        //        手机型号
        String systemModel = SystemUtil.getSystemModel();
        //        系统版本
        String systemVersion = SystemUtil.getSystemVersion();
        String deviceBrand = SystemUtil.getDeviceBrand();
        userAgent = "Android/"+"kuai yi sheng" + getVersionCode(context) + "." + AppUtil.getVersionName(context) + "/" + deviceBrand + "" + systemModel + "/" + systemVersion;
        return userAgent;
    }
    /**
     * getDate
     * yyyy-MM-dd HH:mm:ss
     *
     * @param
     * @param format 如yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getDateString(long milliseconds, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(milliseconds));
    }


    /**
     * get Device Info
     *
     * @param context
     */
    public static String getDeviceInfo(Context context) {


        String string = "";
        // 设备ID，
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        string = androidId;
      /*
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephonyMgr != null) {
            string = mTelephonyMgr.getDeviceId();

        }*/

        return string;
    }
}

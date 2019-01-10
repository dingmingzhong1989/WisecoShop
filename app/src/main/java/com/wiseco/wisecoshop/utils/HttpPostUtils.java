package com.wiseco.wisecoshop.utils;

import android.util.Log;

import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.sContext;
import static com.wiseco.wisecoshop.utils.UrlUtil.EVENTTAG;


/**
 * Created by Administrator on 2018/4/16.
 */

public class HttpPostUtils {
    public static Map<String, String> getParamsMap() {

        Map<String, String> paramsMap = new HashMap<>();

        return paramsMap;
    }

    public static void getToken(String str) {
        Map<String, String> paramsMap = getParamsMap();

        paramsMap.put("mobile", str);
        OkhttpUtil.okHttpPost(UrlUtil.GETTOKEN, paramsMap, new CallBackUtil.CallBackString() {

            @Override
            public void onFailure(Call call, Exception e) {


            }

            @Override
            public void onResponse(String response) {

                Log.d("TAG", "response" + response);


            }
        });
    }

    /**
     * Put 埋点 tag.
     *
     * @param userID  the user id
     * @param source  the source
     * @param target  the target
     * @param action  the action
     * @param content the content
     * @param comment the comment
     */
    public static void putEventTag(String userID, String source, String target, String action, String content, final String comment) {
       // i=2;
        Map<String, String> paramsMap = getParamsMap();
        Map<String, String> paramsMapHeader = getParamsMap();
        String ipAddress = UtilsOther.getIPAddress(sContext);
        paramsMapHeader.put("x-forwarded-for", ipAddress);

        paramsMap.put("userID", CacheUtil.getString(sContext, "USERID", "-1"));
        paramsMap.put("source", source);
        paramsMap.put("target", target);
        paramsMap.put("action", action);
        paramsMap.put("content", content);
        paramsMap.put("comment", comment);
        String s = gson.toJson(paramsMap);


        OkhttpUtil.okHttpPostJson(EVENTTAG, s, new CallBackUtil.CallBackString() {

            @Override
            public void onFailure(Call call, Exception e) {


            }

            @Override
            public void onResponse(String response) {
                try {
                    Log.d("TAG", "response" + comment);

                    Log.d("TAG", "responseresponse==========" + response);

                } catch (Exception e) {

                }





            }
        });
    }
}

package com.tamic.statInterface.statsdk.aop;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.tamic.statInterface.statsdk.core.TcStatInterface;
import com.tamic.statInterface.statsdk.sp.SharedPreferencesHelper;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.util.EncodingUtils;

import static com.tamic.statInterface.statsdk.core.TcStaticsManagerImpl.mContext;
import static com.tamic.statInterface.statsdk.core.TcStaticsManagerImpl.useId;

/**
 * @author : YangHaoYi on 2018/9/12.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/9/12.
 *         Version : V 1.0
 */
@Aspect
public class AnalyticPage {


    private HashMap<String, String> statIdMaps;
    private String returnValue;

    @Before("execution(* android.app.Activity.onResume(..))")
    public void aroundOnResume(JoinPoint joinPoint) throws Throwable {



        try {
           /* Signature signature = joinPoint.getSignature();
            String name = signature.getName();
*/
            Context aThis = (Context) joinPoint.getThis();


            statIdMaps = getStatIdMaps("stat_id.json", aThis);

            for (Map.Entry<String, String> entry : statIdMaps.entrySet()) {


                if (aThis.getClass().getName().contains(entry.getKey()) ) {

                    if (aThis.getClass().getName().contains("MoneyDetailActivity")||aThis.getClass().getName().contains("CardDetailActivity")||aThis.getClass().getName().contains("DiscountChannelAcyivity")){
                        TcStatInterface.onEvent(SharedPreferencesHelper.getInstance((Context) joinPoint.getThis()).getString("UserId", ""), "10" , entry.getValue()+returnValue, "");
                        System.out.println("----getTagId===" + returnValue + "-----------");
                    }else{

                        TcStatInterface.onEvent(SharedPreferencesHelper.getInstance((Context) joinPoint.getThis()).getString("UserId", ""), "10" , entry.getValue(), "");
                    }





                } else {



                }

            }

        } catch (Exception e1) {


        }
    }
    //在连接点进行方法替换
    @Around("execution(*  getUserId(..))")
    public String aroundJoinUserIDPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String userId = (String) joinPoint.proceed(args);

        SharedPreferencesHelper.getInstance(mContext).putString("UserId", useId);
        return userId;
    }

    public static String getDateString(long milliseconds, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(milliseconds));
    }

    @Before("execution(* android.app.Activity.onPause(..))")
    public void aroundOnPause(JoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();



        for (Map.Entry<String, String> entry : statIdMaps.entrySet()) {


            if (joinPoint.getThis().getClass().getName().contains(entry.getKey())) {

                TcStatInterface.onEvent(SharedPreferencesHelper.getInstance(mContext).getString("UserId", ""), "11", entry.getValue(), "");

            } else {


            }

        }

        //  System.out.println( "页面退出"+joinPoint.getThis().getClass().getName());

    }

     @Around("execution(* android.app.Activity.onCreate(..))")
    public void aroundonCreate(ProceedingJoinPoint joinPoint) throws Throwable {

         joinPoint.proceed();



    }

  //在连接点进行方法替换
    @Around("execution(*  getCodePage(..))")
    public String aroundJoinListIDPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        returnValue = (String) joinPoint.proceed(args);
        return returnValue;
    }

    public HashMap<String, String> getStatIdMaps(String jsonName, Context context) {


        HashMap<String, String> map = null;
        if (getFromAsset(jsonName, context) != null) {
            map = (HashMap<String, String>) JSON.parseObject(getFromAsset("stat_id.json", context), HashMap.class);
        }
        return map;
    }

    public String getFromAsset(String fileName, Context context) {
        String result = "";
        try {
            InputStream in = context.getResources().getAssets().open(fileName);
            int length = in.available();
            byte[] buffer = new byte[length];
            in.read(buffer);
            result = EncodingUtils.getString(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

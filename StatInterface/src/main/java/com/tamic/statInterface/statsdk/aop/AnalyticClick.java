package com.tamic.statInterface.statsdk.aop;

import android.view.View;

import com.alibaba.fastjson.JSON;
import com.tamic.statInterface.statsdk.core.TcStatInterface;
import com.tamic.statInterface.statsdk.sp.SharedPreferencesHelper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;

import static com.tamic.statInterface.statsdk.core.TcStaticsManagerImpl.mContext;
import static com.tamic.statInterface.statsdk.core.TcStaticsManagerImpl.targetIdMaps;

/**
 * @author : YangHaoYi on 2018/9/12.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/9/12.
 *         Version : V 1.0
 */
@Aspect
public class AnalyticClick {

    private String returnValue;

    //根据Analytic注解找到方法切入点
    @Pointcut("execution(* onViewClicked(..))")
    public void methodAnnotated() {


    }

    //在连接点进行方法替换
    @Around("execution(* onViewClicked(..))")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object[] args = joinPoint.getArgs();
            View view = (View) args[0];

            Object tag = view.getTag();
            if (tag != null) {
                String s = targetIdMaps.get(tag.toString());
                if (s!=null) {
                    System.out.println("getKey===" + s);
                    if (view.getTag().toString().contains("A17_") || view.getTag().toString().contains("A18_")) {
                        System.out.println("A17_没找到");
                        TcStatInterface.onEvent(SharedPreferencesHelper.getInstance(mContext).getString("UserId", ""), "12" + tag.toString(), s + returnValue, "");
                    } else {

                        TcStatInterface.onEvent(SharedPreferencesHelper.getInstance(mContext).getString("UserId", ""), "12" + tag.toString(), s, "");
                    }
                }else{

                }

                joinPoint.proceed();
            } else {
                System.out.println("没找到");
                joinPoint.proceed();
                return;
            }

        } catch (Exception e) {
            joinPoint.proceed();
        }

    }

    //在连接点进行方法替换
    @Around("execution(*  getCodePage(..))")
    public String aroundJoinListIDPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        returnValue = (String) joinPoint.proceed(args);
        return returnValue;
    }


    public HashMap<String, String> getStatIdMaps(String json) {


        HashMap<String, String> map = null;

        map = (HashMap<String, String>) JSON.parseObject(json, HashMap.class);

        return map;
    }
}

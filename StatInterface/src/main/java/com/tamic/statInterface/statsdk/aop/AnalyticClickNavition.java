package com.tamic.statInterface.statsdk.aop;

import android.content.Context;
import android.view.View;

import com.tamic.statInterface.statsdk.core.TcStatInterface;
import com.tamic.statInterface.statsdk.sp.SharedPreferencesHelper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author : YangHaoYi on 2018/9/12.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/9/12.
 *         Version : V 1.0
 */
@Aspect
public class AnalyticClickNavition {

    //根据Analytic注解找到方法切入点
    @Pointcut("execution(* onNavigationItemSelected(..))")
    public void methodAnnotated() {


    }




    //在连接点进行方法替换
    @Around("execution(* onNavigationItemSelected(..))")
    public void aroundJoinNavigationItemPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        View view = null;
        for (Object arg : joinPoint.getArgs())
            if (arg instanceof View) view = (View) arg;
        if (view != null) {

            System.out.println(joinPoint.getThis().getClass().getName()+"-------onNavigationItemSelected-----"+view.getTag() );
            TcStatInterface.onEvent( SharedPreferencesHelper.getInstance((Context) joinPoint.getThis()).getString("UserId", ""),view.getTag()+"","","comment");




        }
        joinPoint.proceed();
    }


}

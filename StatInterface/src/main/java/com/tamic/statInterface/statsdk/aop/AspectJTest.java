package com.tamic.statInterface.statsdk.aop;

import android.content.Context;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author : YangHaoYi on 2018/9/11.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/9/11.
 *         Version : V 1.0
 */

@Aspect
public class AspectJTest {

    private static final String TAG = "tag00";

    @Pointcut("execution(@com.tamic.statInterface.statsdk.aop.AspectJAnnotation  * *(..))")
    public void executionAspectJ() {

    }

    @Around("executionAspectJ()")
    public Object aroundAspectJ(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Log.i(TAG, "aroundAspectJ(ProceedingJoinPoint joinPoint)");
        AspectJAnnotation aspectJAnnotation = methodSignature.getMethod().getAnnotation(AspectJAnnotation.class);
        String permission = aspectJAnnotation.value();
        Context context = (Context) joinPoint.getThis();
        Object o = null;
        if (true) {
            o = joinPoint.proceed();
            Log.i(TAG, "有权限");
        } else {
            Log.i(TAG, "没有权限，不给用");
        }
        return o;
    }

}

package com.tamic.statInterface.statsdk.aop;

import org.aspectj.lang.annotation.Aspect;

/**
 * @author : YangHaoYi on 2018/9/12.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/9/12.
 *         Version : V 1.0
 */
@Aspect
public class AnalyticOkhttp {

    /*//根据Analytic注解找到方法切入点
    @Pointcut("execution(* onFailure(..))")
    public void methodAnnotated() {


    }*/


    /*//在连接点进行方法替换
    @Around("execution(* onFailure(..))")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {


            System.out.println("网络失败" );



        joinPoint.proceed();
    }*/
    //在连接点进行方法替换
   /* @Around("execution(* onResponse(..))")
    public void aroundJoinPointSuccess(ProceedingJoinPoint joinPoint) throws Throwable {


        Object[] args = joinPoint.getArgs();



        System.out.println("网络成功"+args[0] );
        joinPoint.proceed();
    }*/

}

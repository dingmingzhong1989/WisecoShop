package com.tamic.statInterface.statsdk.aop;

import android.widget.EditText;

import com.tamic.statInterface.statsdk.core.TcStatInterface;
import com.tamic.statInterface.statsdk.sp.SharedPreferencesHelper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

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
public class AnalyticInput {

    //根据Analytic注解找到方法切入点
    @Pointcut("execution(* onTextChanged(..))")
    public void methodAnnotated() {


    }

    //在连接点进行方法替换
    @Around("execution(* onFocusChange(..))")
    public void aroundJoinPointNavigation(ProceedingJoinPoint joinPoint) throws Throwable {


        try {
            Object[] args = joinPoint.getArgs();
            EditText view = (EditText) args[0];
            Object tagText = view.getTag();
            if (tagText != null) {
                String s = targetIdMaps.get(tagText.toString());
                if (s != null) {

                    if ((Boolean) args[1]) {

                        System.out.println("正在输入==" + "useid+12+coment" + view.getTag());


                        TcStatInterface.onEvent(SharedPreferencesHelper.getInstance(mContext).getString("UserId", ""), "13" + tagText.toString(), s , "");

                    } else {
                        System.out.println("输入完成");
                        // TcStatInterface.onEvent(useId,"14",view.getTag()+"","comment");

                    }

                }else{

                    joinPoint.proceed();

                }



            }else{
                joinPoint.proceed();

            }
        } catch (Exception e) {

            joinPoint.proceed();
        }
        /*for (Object arg : joinPoint.getArgs())
            if (arg instanceof EditText) view = (EditText) arg;*/


        joinPoint.proceed();


    }

    //在连接点进行方法替换
    @Around("execution(* afterTextChanged(..))")
    public void aroundJoinPointFinish(ProceedingJoinPoint joinPoint) throws Throwable {


        Object[] args = joinPoint.getArgs();


        joinPoint.proceed();
    }

}

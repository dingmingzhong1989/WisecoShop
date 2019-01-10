package com.tamic.statInterface.statsdk.aop;

import android.view.View;

import com.tamic.statInterface.statsdk.core.TcStatInterface;
import com.tamic.statInterface.statsdk.sp.SharedPreferencesHelper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.List;

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
public class AnalyticList {
    public List<String> returnValue;
    private List<String> returnValueMoney;
    private List<String> returnValueCard;

    /*  //根据Analytic注解找到方法切入点
      @Pointcut("execution(*  onScrollStateChanged(..))")
      public void methodListAnnotated() {


      }*/
//在连接点进行方法替换
  @Around("execution(*  getMainMoneyCode(..))")
  public List<String> aroundJoingetMainMoneyCode(ProceedingJoinPoint joinPoint) throws Throwable {

      Object[] args = joinPoint.getArgs();

      returnValueMoney = (List<String>) joinPoint.proceed(args);
      System.out.println("----getTagId===" + returnValue + "-----------");


      return returnValueMoney;
  }

    //在连接点进行方法替换
    @Around("execution(*  getMainCardCode(..))")
    public List<String> aroundJoingetMainCardCode(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();

        returnValueCard = (List<String>) joinPoint.proceed(args);
        System.out.println("----getTagId===" + returnValue + "-----------");


        return returnValueCard;
    }
    //在连接点进行方法替换
    @Around("execution(*  onItemClick(..))")
    public void aroundJoinListPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            //访问目标方法的参数：
            Object[] args = joinPoint.getArgs();

            View view = (View) args[0];
            Object tag = view.getTag();
            if (tag.toString().equals("H15")){
                String s = targetIdMaps.get(tag.toString());
                TcStatInterface.onEvent(SharedPreferencesHelper.getInstance(mContext).getString("UserId", ""), tag.toString(), s + returnValueCard.get((int)args[2]), "");
            }else if (tag.toString().equals("_life")){
                String s = targetIdMaps.get(tag.toString());
                TcStatInterface.onEvent(SharedPreferencesHelper.getInstance(mContext).getString("UserId", ""), "12"+tag.toString(), s , "");
            }else if (tag.toString().equals("_shopLife")){

                String s = targetIdMaps.get(tag.toString());
                TcStatInterface.onEvent(SharedPreferencesHelper.getInstance(mContext).getString("UserId", ""), "12"+tag.toString(), s , "");
            }



        } catch (Exception e) {


        }

       // DateUtil.getDateString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");

        //System.out.println("stsrtTime:"+ DateUtil.getDateString(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss") + "TagID" + returnValue + "ActionID"+"P011");

       // System.out.println("首页=========" + args[0].toString());


        joinPoint.proceed();


    }

    //在连接点进行方法替换
    @Around("execution(*  getCoadList(..))")
    public List<String> aroundJoinListIDPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();

        returnValue = (List<String>) joinPoint.proceed(args);
        System.out.println("----getTagId===" + returnValue + "-----------");


        return returnValue;
    }

    //在连接点进行方法替换
    @Around("execution(* onClick(..))")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        //访问目标方法的参数：


        try {
            System.out.println("onClickonClick=========" + joinPoint.getSignature().toString());
            Object[] args = joinPoint.getArgs();
            if (joinPoint.getSignature().toString().contains("CardListFragment")) {

                TcStatInterface.onEvent(SharedPreferencesHelper.getInstance(mContext).getString("UserId", ""), "L19", "AndroidL19" + returnValue.get((int) args[0]), "");


            }
            if (joinPoint.getSignature().toString().contains("MoneyListFragment")) {

                TcStatInterface.onEvent(SharedPreferencesHelper.getInstance(mContext).getString("UserId", ""), "L20", "AndroidL20" + returnValue.get((int) args[0]), "");



            }

            if (joinPoint.getSignature().toString().contains("DiscountListAcyivity")) {

                TcStatInterface.onEvent(SharedPreferencesHelper.getInstance(mContext).getString("UserId", ""), "12_exclusiveLife", "AndroidBtn_exclusiveLife" , "");



            }


        } catch (Exception e) {


        }


        joinPoint.proceed();

    }
}

package com.wiseco.wisecoshop.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static com.wiseco.wisecoshop.MyApplication.IS_TRUE_NAME;
import static com.wiseco.wisecoshop.MyApplication.IS_USER_REGIEST;
import static com.wiseco.wisecoshop.MyApplication.sContext;

/**
 * Created by wiseco on 2018/10/12.
 */

public class UtilsOther {

    private static final int MIN_DELAY_TIME = 2000;
    // 两次点击间隔不能少于1000ms
    private static long lastClickTime;


    /**
     * 将字符串转换成Bitmap类型
     *
     * @param string the string
     * @return the bitmap
     */
    public static Bitmap stringtoBitmap(String string) {
        //将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNumber(String mobiles) {
        /*		移动：134、135、136、137、138、139、150、151、157(TD)、
        158、159、187、188
        联通：130、131、132、152、155、156、185、186
        电信：133、153、180、189、（1349卫通）
        总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9		*/
        String telRegex = "[1][3456789]\\d{9}";
        //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，
        // "\\d{9}"代表后面是可以是0～9的数字，有9位。

        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    /**
     * 验证输入的名字是否为“中文”或者是否包含“·”
     */
    public static boolean isLegalName(String name) {
        if (name.contains("·") || name.contains("•")) {
            if (name.matches("^[\\u4e00-\\u9fa5]+[·•][\\u4e00-\\u9fa5]+$")) {
                return true;
            } else {
                return false;
            }
        } else {
            if (name.matches("^[\\u4e00-\\u9fa5]+$")) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 是否注册用户
     **/
    public static boolean isRegist() {
        boolean tag;
        if (CacheUtil.getBoolean(sContext, IS_USER_REGIEST, false)) {
            tag = true;
        } else {
            tag = false;
        }

        return tag;
    }

    /**
     * 是否实名
     **/
    public static boolean isTrueName() {
        boolean tag;
        if (CacheUtil.getBoolean(sContext, IS_TRUE_NAME, false)) {
            tag = true;
        } else {
            tag = false;
        }

        return tag;
    }


    //获取userid
    public static String getUserId() {
        if (isRegist()) {

            return CacheUtil.getString(sContext, "userID", "");
        } else {
            return "";
        }

    }


    //时间转换

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String changeTime(String time) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+0000");
        Date d = format.parse(time);
        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；
        String formatDate = dFormat.format(d);

        return formatDate;
    }

    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     *
     * @param str 需要处理的字符串
     * @return 处理完之后的字符串
     */
    public static String addComma(String str) {
        DecimalFormat decimalFormat = new DecimalFormat(",###");
        return decimalFormat.format(Double.parseDouble(str));
    }


    /**
     * Sets key word.
     *
     * @param context the context
     * @param str     the str
     * @param lin     the lin
     * @param tag     the tag
     */
    public static void setKeyWord(Context context, String str, LinearLayout lin, String tag, int max) {
        String s = "1|全线上,2|放款快,3|超灵活,4|随时用,5|超低利率";
        String s2 = "1|全线上";

        int num = 0;
        String[] split = str.split(",");

        if (split.length <= max) {
            num = split.length;
        } else {
            num = max;
        }
        lin.removeAllViews();
        // Log.d("TAG","num===="+num);
        for (int i = 0; i < num; i++) {
            TextView textView = new TextView(context);

            textView.setCompoundDrawablePadding(3);
            LinearLayout.LayoutParams textviewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            textviewParams.setMargins(20, 0, 0, 0);
            textView.setLayoutParams(textviewParams);


            if (tag.equals("blue")) {
                textView.setTextColor(context.getResources().getColor(R.color.keyword));
                textView.setTextSize(11f);
                textView.setText(split[i].split("\\|")[1]);
                Drawable drowable = getDrowable(context, split[i].split("\\|")[0]);
                drowable.setBounds(0, 0, drowable.getMinimumWidth(), drowable.getMinimumHeight());
                textView.setCompoundDrawables(drowable, null, null, null); //设置左图标
            } else {
                textView.setTextColor(context.getResources().getColor(R.color.gray_888888));
                textView.setTextSize(11f);
                textView.setText(split[i].split("\\|")[1]);
                Drawable drowable = getGrayDrowable(context, split[i].split("\\|")[0]);
                drowable.setBounds(0, 0, drowable.getMinimumWidth(), drowable.getMinimumHeight());
                textView.setCompoundDrawables(drowable, null, null, null); //设置左图标
            }


            lin.addView(textView);
        }

        split = null;
    }


    /**
     * Gets drowable.
     *
     * @param context the context
     * @param tag     the tag
     * @return the drowable
     */
    public static Drawable getDrowable(Context context, String tag) {

        if (tag.equals("1")) {
            return context.getResources().getDrawable(R.drawable.dui);
        }
        if (tag.equals("2")) {
            return context.getResources().getDrawable(R.drawable.dun);
        }
        if (tag.equals("3")) {
            return context.getResources().getDrawable(R.drawable.heart);
        }
        if (tag.equals("4")) {
            return context.getResources().getDrawable(R.drawable.sun);
        }
        if (tag.equals("5")) {
            return context.getResources().getDrawable(R.drawable.xing);
        }

        return context.getResources().getDrawable(R.drawable.xing);
    }

    /**
     * Gets gray drowable.
     *
     * @param context the context
     * @param tag     the tag
     * @return the gray drowable
     */
    public static Drawable getGrayDrowable(Context context, String tag) {

        if (tag.equals("1")) {
            return context.getResources().getDrawable(R.drawable.dui_gray);
        }
        if (tag.equals("2")) {
            return context.getResources().getDrawable(R.drawable.dun_gray);
        }
        if (tag.equals("3")) {
            return context.getResources().getDrawable(R.drawable.heart_gray);
        }
        if (tag.equals("4")) {
            return context.getResources().getDrawable(R.drawable.sun_gray);
        }
        if (tag.equals("5")) {
            return context.getResources().getDrawable(R.drawable.xing_gray);
        }

        return context.getResources().getDrawable(R.drawable.xing_gray);
    }

    /**
     * 获取ip
     *
     * @param context the context
     * @return the ip address
     */
    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }


    /**
     * Is fast click boolean.
     *
     * @return the boolean
     */
    public static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }


    @SuppressWarnings("all")
    public static void rotateArrow(ImageView arrow, boolean flag) {
        float pivotX = arrow.getWidth() / 2f;
        float pivotY = arrow.getHeight() / 2f;
        float fromDegrees = 0f;
        float toDegrees = 0f;
        // flag为true则向上
        if (flag) {
            fromDegrees = 0f;
            toDegrees = 90f;
        } else {
            fromDegrees = 90f;
            toDegrees = 0f;
        }
        //旋转动画效果   参数值 旋转的开始角度  旋转的结束角度  pivotX x轴伸缩值
        RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees,
                pivotX, pivotY);
        //该方法用于设置动画的持续时间，以毫秒为单位
        animation.setDuration(100);
        //设置重复次数
        //animation.setRepeatCount(int repeatCount);
        //动画终止时停留在最后一帧
        animation.setFillAfter(true);
        //启动动画
        arrow.startAnimation(animation);
    }


    /*动态测量ListView高度*/


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if (listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    //判断性别和年龄
    public static Map<String, Object> getCarInfo(String CardCode)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String year = CardCode.substring(6).substring(0, 4);// 得到年份
        String yue = CardCode.substring(10).substring(0, 2);// 得到月份
        // String day=CardCode.substring(12).substring(0,2);//得到日
        String sex;
        if (Integer.parseInt(CardCode.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别
            sex = "女";
        } else {
            sex = "男";
        }
        Date date = new Date();// 得到当前的系统时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fyear = format.format(date).substring(0, 4);// 当前年份
        String fyue = format.format(date).substring(5, 7);// 月份
        // String fday=format.format(date).substring(8,10);
        int age = 0;
        if (Integer.parseInt(yue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
            age = Integer.parseInt(fyear) - Integer.parseInt(year) + 1;
        } else {// 当前用户还没过生
            age = Integer.parseInt(fyear) - Integer.parseInt(year);
        }
        map.put("sex", sex);
        map.put("age", age);
        return map;
    }

}

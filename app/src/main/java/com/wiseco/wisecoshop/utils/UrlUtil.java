package com.wiseco.wisecoshop.utils;

import com.wiseco.wisecoshop.BuildConfig;

/**
 * Created by Administrator on 2018/3/12.
 */

public class UrlUtil {
    //public static String BASE = "http://58.87.92.138:9280/wisecofin";
    //  public static String BASE = "http://192.168.1.131:8090/wisecofin";
    // public static String BASE = "http://192.168.1.140:6789/wiseuser/login";
    //public static String END = ".html";
    public static String END = "";
    //public static String BASE = "https://m.wisecofincloud.com/api/wisecofin";
    public static String BASE = BuildConfig.SERVER_ADD;
    //获取token接口
    public static String GETTOKEN = BASE + "/api/wisecofin/queryToken" + END;
    //验证验证码
    public static String PHONEPREVALID = BASE + "/api/wisecofin/phoneprevalid" + END;
    //验证身份证
    public static String FILLIDCARD = BASE + "/api/wisecofin/fillidcard" + END;
    //获取验证码
    public static String GETCODE = BASE + "/api/wisecofin/phonevalid" + END;
    //获取图片验证码
    public static String GETPASSIMG = BASE + "/api/wisecofin/getImgValidCode" + END;

    //验证图片验证码
    public static String VAILDPASSIMG = BASE + "/api/wisecofin/validImgCode" + END;
    //数据接口
    public static String GOOSDS = BASE + "/api/wisecofin/queryf3suit" + END;
    //数据查询接口/api/wisecofin/getImgValidCode
    public static String GOOSDSPAGEFIND = BASE + "/api/wisecofin/pagedata" + END;
    //产品详情
    public static String GOOSDSDEDAIL = BASE + "/api/wisecofin/prddetail" + END;
    //产品详情
    public static String GOOSDSAPPLY = BASE + "/api/wisecofin/applyproduct" + END;

    //首次办卡
    public static String FIRSTUPDATA = BASE + "/api/wisecofin/updateusercc" + END;
    //首页接口
    public static String APPDFPRDLIST = BASE + "/api/wisecofin/appdfprdlist" + END;

    //埋点接口
    public static String EVENTTAG = BASE + "/api/eventlog/write" + END;

    //订单接口
    public static String ORDER = BASE + "/api/order/queryOrders" + END;

    //订单详情接口
    public static String ORDERDEATAIL = BASE + "/api/order/detail" + END;
    //首页接口
    public static String HORSE = BASE + "/api/front/headimages" + END;

    //版本跟新接口
    public static String UPDATA = BASE + "/api/appVersion/get/wise_android" + END;

    //更改个人信息

    public static String UPDATAUSERINFO = BASE + "/api/user/updateUserNameAndIDCard" + END;

    //生活优惠生活优惠信息展示功能

    public static String PROM = BASE + "/api/prom/baseprom" + END;
    //附近接口
    public static String NEARBYSHOP = BASE + "/api/prom/nearbyshop" + END;

    //商店接口
    public static String SHOPDETAIL = BASE + "/api/prom/shopdetail" + END;
    //优惠详情接口
    public static String ACTIVEDETAIL = BASE + "/api/prom/activedetail" + END;




    //个人中心
    public static String USERCRENTER = BASE + "/api/usercent/rootmsg" + END;
    //查询个人卡列表
    public static String QUERYCARDLIST = BASE + "/api/usercent/querycardlist" + END;
    //用户新增一张卡片
    public static String ADDCARD = BASE + "/api/usercent/addcard" + END;
    //更新某个卡片信息
    public static String UPDATACARD = BASE + "/api/usercent/updatacard" + END;
    //删除某个卡片信息
    public static String DELETECARD = BASE + "/api/usercent/deletecard" + END;
    //查询所有的优惠券信息
    public static String QUERYWALLET = BASE + "/api/usercent/querywallet" + END;
    //设置多个券为已使用
    public static String SETWALLETUSED = BASE + "/api/usercent/setwalletused" + END;
    //查询个人卡片
    public static String QUERYUSBAERNKLIST = BASE + "/api/usercent/queryuserbanklist" + END;
    public static String WRITECOMMENT = BASE + "/api/usercent/writecomment" + END;

    public static String EVENTLOG = BASE + "/api/eventlog/sendlog" + END;


}

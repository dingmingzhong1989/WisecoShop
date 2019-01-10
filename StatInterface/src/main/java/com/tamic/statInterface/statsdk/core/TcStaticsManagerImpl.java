package com.tamic.statInterface.statsdk.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tamic.statInterface.statsdk.constants.StaticsConfig;
import com.tamic.statInterface.statsdk.db.helper.DataConstruct;
import com.tamic.statInterface.statsdk.db.helper.StaticsAgent;
import com.tamic.statInterface.statsdk.http.TcHttpClient;
import com.tamic.statInterface.statsdk.model.TargetBean;
import com.tamic.statInterface.statsdk.model.TempIdBean;
import com.tamic.statInterface.statsdk.model.VisionBean;
import com.tamic.statInterface.statsdk.sp.SharedPreferencesHelper;
import com.tamic.statInterface.statsdk.util.JsonUtil;
import com.tamic.statInterface.statsdk.util.NetworkUtil;
import com.tamic.statInterface.statsdk.util.StatLog;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.EncodingUtils;

import static com.tamic.statInterface.statsdk.constants.NetConfig.ENVBASE;


/**
 * StaticsManagerImpl core
 * Created by LIUYONGKUI726 on 2016-03-24.
 */
public class TcStaticsManagerImpl implements TcStaticsManager, TcObserverPresenter.ScheduleListener {
    /**
     * context
     */
    public static Context mContext;
    /**
     * sInstance
     */
    private static TcStaticsManager sInstance;

    public static String useId;

    private static TcObserverPresenter paObserverPresenter;

    private StaticsListener eventInterface;

    private TcStatiPollMgr statiPollMgr;

    public static HashMap<String, String> pageIdMaps = new HashMap<String, String>();

    public static HashMap<String, String> targetIdMaps = new HashMap<String, String>();
    /**
     * Log TAG
     */
    private static final String LOG_TAG = TcStatiPollMgr.class.getSimpleName();
    private RequestParams requestParams;
    private String versionName;

    public TcStaticsManagerImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public boolean onInit(String useId, String fileName) {
        try {
            PackageInfo packageInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            versionName = packageInfo.versionName;


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        // init  ObserverPresenter
        paObserverPresenter = new TcObserverPresenter(this);

        // init StaticsAgent
        StaticsAgent.init(mContext);

        // load pageIdMaps
        pageIdMaps = getStatIdMaps(fileName);
        //useId=useId;
        targetIdMaps = getStatIdMaps("target_id.json");

        String tempUserId = SharedPreferencesHelper.getInstance(mContext).getString("TempUserId", "");

        if (tempUserId==null || tempUserId.equals("")){

            TcHttpClient.post( ENVBASE,new ResponseTempHandler(true));
        }else{


        }



        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("apptype", "1");
            ByteArrayEntity entity = new ByteArrayEntity(jsonObject.toString().getBytes("UTF-8"));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            TcHttpClient.post(mContext, "https://m.wisecofincloud.com/api/eventlog/getconfigversion", entity, RequestParams.APPLICATION_JSON, new PaJsonHttpResponseHandler(true));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        Log.d("useId", useId);
        SharedPreferencesHelper.getInstance(mContext).putString("UserId", useId);
        // init  StatiPoll
        statiPollMgr = new TcStatiPollMgr(this);
        // init Header
        return initHeader(useId);
    }

    @Override
    public void onSend() {
        new Thread(new Runnable() {
            @Override
            public void run() {


                TcUpLoadManager.getInstance(mContext).report(JsonUtil.toJSONString(StaticsAgent.getDataBlock()));
            }
        }).start();

    }

    @Override
    public void onStore() {
        DataConstruct.storeEvent();
        // DataConstruct.storePage();
    }

    @Override
    public void onRelease() {
        if (paObserverPresenter != null) {
            paObserverPresenter.destroy();
        }

        stopSchedule();

    }

    @Override
    public void onRecordAppStart() {
        //send
       // onSend();
        // store appAction
        // DataConstruct.storeAppAction("1");
    }

    @Override
    public void onRrecordPageEnd() {
        //DataConstruct.storeEvent();
        // DataConstruct.storePage();
        if (paObserverPresenter != null) {
            paObserverPresenter.onStop(mContext);
        }
        stopSchedule();
    }

    @Override
    public void onRecordPageStart(Context context) {

        if (context == null) {
            return;
        }

        //开始计时
        startSchedule();


        //  String pageId = checkValidId(context.getClass().getSimpleName());
        //  if (pageId == null) {
        //     pageId = context.getClass().getSimpleName();
        //  }

        // init page
        //  onInitPage(pageId, null);

        if (paObserverPresenter != null) {
            paObserverPresenter.init(mContext);
        }

        if (paObserverPresenter != null) {
            paObserverPresenter.onStart(mContext);
        }
    }


    @Override
    public void onRrecordAppEnd() {

        //recard APP exit
        // DataConstruct.storeAppAction("2");

        onSend();

        onRelease();
    }

    @Override
    public void onInitPage(String... strings) {

        // DataConstruct.initPage(mContext, eventInterface, strings[0], strings[1]);

    }

    @Override
    public void onPageParameter(String... strings) {

        // DataConstruct.initPageParameter(strings[0], strings[1]);

    }


    @Override
    public void onInitEvent(String useID, String eventAction, String tagid, String comment) {

        DataConstruct.initEvent(eventInterface, useID, eventAction, tagid, comment);
    }

    @Override
    public void onEventParameter(String... strings) {

        // DataConstruct.onEvent(strings[0], strings[1]);

    }

    /**
     * init header
     */
    private boolean initHeader(String useId) {


       /* if (!TcHeadrHandle.isInit()) {
            return TcHeadrHandle.initHeader(mContext, appId, channel);
        }*/

        return false;

    }

    /**
     * onScheduleTimeOut
     */
    void onScheduleTimeOut() {

        StatLog.d(LOG_TAG, "onScheduleTimeOut  is sendData");

        onSend();
    }

    /**
     * startSchedule
     */
    public void startSchedule() {
        // if debug  time is 5 min
        if (StaticsConfig.DEBUG &&
                TcStatInterface.uploadPolicy == TcStatInterface.UploadPolicy.UPLOAD_POLICY_DEVELOPMENT) {
            statiPollMgr.start(5 * 1000);
            StatLog.d(LOG_TAG, "Schedule is start");
        } else {
            if (NetworkUtil.isWifi(mContext)) {
                statiPollMgr.start(TcStatInterface.getIntervalRealtime() * 60 * 1000);
            } else {
                statiPollMgr.start(TcStatInterface.UPLOAD_TIME_ONE * 60 * 1000);
            }

        }
    }

    /**
     * checkValidId
     *
     * @param name activitiyname
     * @return pageId
     */
    private String checkValidId(String name) {
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        if (name.length() <= 0) {
            return null;
        }

        return getPageId(name);
    }


    /**
     * getPageId
     *
     * @param clazz
     * @return
     */
    private String getPageId(String clazz) {
        if (mContext == null) {
            return null;
        }
        return pageIdMaps.get(clazz);
    }

    /**
     * stop Schedule
     */
    public void stopSchedule() {

        StatLog.d(LOG_TAG, "stopSchedule()");

        statiPollMgr.stop();
    }

    @Override
    public void onStart() {
        StatLog.d(LOG_TAG, "startSchedule");

        startSchedule();

    }

    @Override
    public void onStop() {

        stopSchedule();
    }

    @Override
    public void onReStart() {
        // stopSchedule
        stopSchedule();
        // startSchedule
        startSchedule();
    }


    public HashMap<String, String> getStatIdMaps(String jsonName) {


        HashMap<String, String> map = null;
        if (getFromAsset(jsonName) != null) {
            map = (HashMap<String, String>) JSON.parseObject(getFromAsset(jsonName), HashMap.class);
        }
        return map;
    }

    public String getFromAsset(String fileName) {
        String result = "";
        try {
            InputStream in = mContext.getResources().getAssets().open(fileName);
            int length = in.available();
            byte[] buffer = new byte[length];
            in.read(buffer);
            result = EncodingUtils.getString(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private class PaJsonHttpResponseHandler extends AsyncHttpResponseHandler {

        public PaJsonHttpResponseHandler() {
        }

        public PaJsonHttpResponseHandler(Looper looper) {
            super(looper);
        }

        public PaJsonHttpResponseHandler(boolean usePoolThread) {
            super(usePoolThread);
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            Object parse = JSONObject.parse(responseBody);


            //String str = Base64.encodeToString(responseBody,1);
            Log.d("1234567", "statusCode==" + versionName);
            Log.d("1234567", "statusCode==" + JSONObject.toJSON(parse));


            VisionBean visionBean = JSONObject.parseObject(JSONObject.toJSON(parse).toString(), VisionBean.class);


            if (visionBean.getCode().equals("S")) {

                if (!visionBean.getVersion().equals(versionName)) {

                    try {
                        JSONObject jsonObject = new JSONObject();

                        jsonObject.put("appType", "1");
                        ByteArrayEntity entity = new ByteArrayEntity(jsonObject.toString().getBytes("UTF-8"));
                        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                        TcHttpClient.post(mContext, "https://m.wisecofincloud.com/api/eventlog/getconfig", entity, RequestParams.APPLICATION_JSON, new ResponseHandler(true));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

            }else{


            }


        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

        }

    }

    private class ResponseHandler extends AsyncHttpResponseHandler {

        public ResponseHandler() {
        }

        public ResponseHandler(Looper looper) {
            super(looper);
        }

        public ResponseHandler(boolean usePoolThread) {
            super(usePoolThread);
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            Object parse = JSONObject.parse(responseBody);


            //String str = Base64.encodeToString(responseBody,1);

            Log.d("1234567", "statusCode==" + JSONObject.toJSON(parse));

            TargetBean targetBean = JSONObject.parseObject(JSONObject.toJSON(parse).toString(), TargetBean.class);

            if (targetBean.getCode().equals("S")) {

                List<String> targetList = targetBean.getTargetList();
                for (int i = 0; i < targetList.size(); i++) {
                    targetIdMaps.remove(targetList.get(i));

                }

            } else {

            }


        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

        }

    }

    private class ResponseTempHandler extends AsyncHttpResponseHandler {

        public ResponseTempHandler() {
        }

        public ResponseTempHandler(Looper looper) {
            super(looper);
        }

        public ResponseTempHandler(boolean usePoolThread) {
            super(usePoolThread);
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            Object parse = JSONObject.parse(responseBody);

            Log.d("1234567", "ENVBASEENVBASE==" + JSONObject.toJSON(parse));

            TempIdBean tempIdBean = JSONObject.parseObject(JSONObject.toJSON(parse).toString(), TempIdBean.class);

            String useridtmp = tempIdBean.getUseridtmp();

            SharedPreferencesHelper.getInstance(mContext).putString("TempUserId", useridtmp);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

        }

    }
}

package com.tamic.statInterface.statsdk.core;

import android.content.Context;
import android.text.TextUtils;


import com.tamic.statInterface.statsdk.constants.NetConfig;
import com.tamic.statInterface.statsdk.db.helper.StaticsAgent;
import com.tamic.statInterface.statsdk.http.TcHttpClient;
import com.tamic.statInterface.statsdk.util.NetworkUtil;

import java.util.concurrent.atomic.AtomicReference;

/**
 *  Stat UpLoadManager
 * Created by Tamic on 2016-03-24.
 */
public class TcUpLoadManager implements IUpLoadlistener{


    /** context */
    private Context mContext;
    /** http client */
    private TcHttpClient mHttpClient;
    /** UpLoadManager */
    private static TcUpLoadManager sInstance;

    private Boolean isRunning = false;

    private AtomicReference<TcNetEngine> atomic;

    private TcNetEngine netEngine;

    /**
     * getInstance
     *
     * @param aContext
     *            context
     * @return  UpLoadManager
     */
    public static synchronized TcUpLoadManager getInstance(Context aContext) {
        if (sInstance == null) {
            sInstance = new TcUpLoadManager(aContext);
        }
        return sInstance;
    }

    /**
     * constructor
     *
     * @param aContext
     *            context
     */
    private TcUpLoadManager(Context aContext) {
        mContext = aContext;
        init();
    }

    /**
     * init
     */
    private void init() {
        mHttpClient = getHttpclient();
        atomic = new AtomicReference<> ();
        netEngine = new TcNetEngine(mContext, this);
    }


    /**
     * report
     */
    public void report(String jsonString) {

        if (!NetworkUtil.isNetworkAvailable(mContext)) {
            return;
        }

        if (TextUtils.isEmpty(jsonString)) {
            return;
        }
        //netEngine.setHttpClient(getHttpclient());
        atomic.set(netEngine);
        atomic.getAndSet(netEngine).start(jsonString);
    }

    /**
     * cancle
     */
    public void cancle() {

        if (atomic.get() != null) {
            atomic.get().cancel();

        }

    }


    /**
     * get http client
     *
     * @return http client
     */
    public TcHttpClient getHttpclient() {
        if (mHttpClient == null) {
            // HttpClient
            mHttpClient = new TcHttpClient();
            mHttpClient.setTimeOut(NetConfig.TIME_OUT);
        }
        return mHttpClient;

    }


    @Override
    public void onStart() {

        isRunning = true;
    }

    @Override
    public void onUpLoad() {

        isRunning = true;
    }

    @Override
    public void onSucess() {

        isRunning = false;
        // delete data
        StaticsAgent.deleteTable();
    }

    @Override
    public void onFailure() {
       // StaticsAgent.deleteTable();
        isRunning = false;

    }

    @Override
    public void onCancell() {

        isRunning = false;
    }
}

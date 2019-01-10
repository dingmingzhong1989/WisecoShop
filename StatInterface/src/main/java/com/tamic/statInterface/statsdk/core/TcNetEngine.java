package com.tamic.statInterface.statsdk.core;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tamic.statInterface.statsdk.constants.NetConfig;
import com.tamic.statInterface.statsdk.http.TcHttpClient;
import com.tamic.statInterface.statsdk.util.StatLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Set;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by LIUYONGKUI726 on 2016-04-18.
 */
public class TcNetEngine {


    private Context context;

    private TcHttpClient mHttpClient;

    private String mKey;

    /**
     * 重试次数
     */
    protected int mRetrytimes = NetConfig.RETRY_TIMES;

    private static final String TAG = "TamicStat::TaNetEngine";

    /**
     * 是否支持断点
     */
    protected boolean mCanContinue;

    private String mHostUrl = NetConfig.ONLINE_URL;

    private PaJsonHttpResponseHandler mTaskHandler;

    private IUpLoadlistener mUpLoadlistener;

    private HashMap<String, String> headers;

    private RequestParams requestParams;

    Header[] reqHeaders;

    Header header;
    private ByteArrayEntity entity;

    public TcNetEngine(Context context, IUpLoadlistener upLoadlistener) {

        this(context, null, upLoadlistener);

    }

    public TcNetEngine(Context context, TcHttpClient httpClient, IUpLoadlistener upLoadlistener) {
        this.context = context;
        mHttpClient = httpClient;
        mCanContinue = true;
        mTaskHandler = new PaJsonHttpResponseHandler(true);
        mUpLoadlistener = upLoadlistener;
        init();


    }

    private void init() {

       /* if (StaticsConfig.DEBUG) {
            mHostUrl = NetConfig.URL;
        }*/
        headers = new HashMap<String, String>();
        requestParams = new RequestParams();
    }

    public TcHttpClient getHttpClient() {
        return mHttpClient;
    }

    public void setHttpClient(TcHttpClient mHttpClient) {
        this.mHttpClient = mHttpClient;
    }

    public String start(final String... strings) {
        if (headers.size() >= 0) {
            headers.clear();
        }
        if (headers != null && headers.size() > 0) {
            reqHeaders = new Header[headers.size()];
            Set<String> keys = headers.keySet();
            int index = 0;
            for (final String mykey : keys) {
                header = new Header() {
                    @Override
                    public String getName() {
                        return mykey;
                    }

                    @Override
                    public String getValue() {
                        return headers.get(mykey);
                    }

                    @Override
                    public HeaderElement[] getElements() throws ParseException {
                        return new HeaderElement[0];
                    }
                };
                reqHeaders[index++] = header;
            }

        }

        try {
            JSONObject jsonObjectALL = new JSONObject(strings[0]);


            // 通过标识(person)，获取JSON数组
            JSONArray jsonArray = jsonObjectALL.getJSONArray("eventlog");
            String value = "";
            for (int i = 0; i < jsonArray.length(); i++) {
                // JSON数组里面的具体-JSON对象
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String user_id = jsonObject.optString("user_id", null);
                String action_id = jsonObject.optString("action_id", null);
                String target_id = jsonObject.optString("target_id", null);
                String operation_time = jsonObject.optString("operation_time", null);
                String comment = jsonObject.optString("comment", null);

                value +=  user_id + "#" +""+"#"+ action_id + "#" +operation_time + "#" + target_id + "#" +  comment +  "\n";

                // 日志打印结果：
                Log.d(TAG, "analyzeJSONArray2 解析的结果：name" + value);
            }
            if (value != null || !value.equals("")){

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("key", "eventlog");
                jsonObject.put("value", value);


                entity = new ByteArrayEntity(jsonObject.toString().getBytes("UTF-8"));
                entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

                TcHttpClient.post(context, "https://m.wisecofincloud.com/api/eventlog/sendlog", reqHeaders, entity, RequestParams.APPLICATION_JSON, mTaskHandler);
            }else{

            }


            // requestParams.put("value", str);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return null;
    }

    void cancel() {

        TcHttpClient.cancle(mKey, true);
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

            if (mUpLoadlistener != null) {
                mUpLoadlistener.onSucess();
            }

            for (Header tmp : headers) {
                StatLog.d(TAG, tmp.getName() + ":" + tmp.getValue());
            }

            StatLog.d(TAG, "response code: " + statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                StatLog.d(TAG, "onSuccess");
                mCanContinue = false;
            } else if (statusCode == HttpStatus.SC_PARTIAL_CONTENT) {
                mCanContinue = true;
            }

            Log.d(TAG, "埋点上传成功");
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            if (mUpLoadlistener != null) {
                mUpLoadlistener.onFailure();
            }
            cancel();
        }

    }

}

package com.wiseco.wisecoshop.okhttp;

/**
 * Created by wiseco on 2018/10/12.
 */

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import static com.wiseco.wisecoshop.MyApplication.sContext;


/**
 * 自动管理Cookies
 */
public class CookiesManager implements CookieJar {
    public final PersistentCookieStore cookieStore = new PersistentCookieStore(sContext);

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

        for (Cookie item : cookies) {
           // if (item.name().equals("token"))
            cookieStore.add(url, item);
            Log.d("TAG", "Cookie.size()===" + cookies.size());
            Log.d("TAG", "Cookie===" + cookies.get(0).toString());
            Log.d("TAG", "url===" + url);
            //String cookie = cookies.get(0).toString();
            String name = item.name();
            String value = item.value();
            Log.d("TAG", "cookie===" + name + "-----" + value);


        }

    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies != null ? cookies : new ArrayList<Cookie>();
    }
}




package com.wiseco.wisecoshop.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.wiseco.wisecoshop.location.LocationTracker;
import com.wiseco.wisecoshop.location.TrackerSettings;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by wiseco on 2018/12/20.
 */

public class LocationUtil {


    public static void getLongitudeAndLatitude(final Context context) {
        //允许GPS、WiFi、基站定位，设置超时时间5秒
        TrackerSettings trackerSettings = new TrackerSettings();
        trackerSettings.setUseGPS(true).setUseNetwork(true).setUsePassive(true).setTimeout(5000);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationTracker locationTracker = new LocationTracker(context, trackerSettings) {
            @Override
            public void onLocationFound(@NonNull Location location) {
                double latitude = location.getLatitude() ;
                double longitude = location.getLongitude() ;
                Log.i("LocationUtil", "latitude:" + latitude + "longitude:" + longitude);

               // getAddress(context, 39.6226149409,98.3056640625);
              getAddress(context, latitude, longitude);
                CacheUtil.putString(context,"LOCATION_LATITUDE_SP_KEY", latitude+"");
                CacheUtil.putString(context,"LOCATION_lONGITUDE_SP_KEY", longitude+"");
            }

            @Override
            public void onTimeout() {
                Log.i("LocationUtil", "location time out");
            }
        };
        locationTracker.startListening();
    }
    //放入经纬度就可以了
    public static String getAddress(Context context,double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude,
                    longitude, 1);

            Log.i("LocationUtil", "latitude:" + addresses.size());
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                String data = address.toString();
//                int start= data.indexOf("Address[addressLines=[") + "Address[addressLines=[".length();
//
//                data.substring(start, data.length()-1-start);
//
//
//
//                Log.i("TAG", "+++++" +  data.substring(start, data.length()));

                int startCity = data.indexOf("0:\"") + "0:\"".length();
                int endCity = data.indexOf("\"", startCity);
                String place = data.substring(startCity, endCity);
                int startPlace = data.indexOf("locality=") + "locality=".length();
                int endplace = data.indexOf(",", startPlace);
                String city = data.substring(startPlace, endplace);
                Log.i("LocationUtil", "latitude:" + data);
                Log.i("LocationUtil", "+++++" + city + "=======" + place.substring(place.indexOf(city) + city.length(),place.length()));

                Log.i("LocationUtil", CacheUtil.getString(context,"LOCATION_LATITUDE_SP_KEY", ""));
                Log.i("LocationUtil", CacheUtil.getString(context,"LOCATION_lONGITUDE_SP_KEY", ""));

                return city +","+ place.substring(place.indexOf(city) + city.length(),place.length()) ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "获取失败";
    }


}

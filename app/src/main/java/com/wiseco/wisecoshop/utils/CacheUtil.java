package com.wiseco.wisecoshop.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class CacheUtil {
	private static final String NAME = "smartAbstraction";
	private static SharedPreferences sp;
    // 从sp中获取一个int值
	public static int getInt(Context context, String key,
							 int defValue) {
		if(sp==null){
			sp = context.getSharedPreferences(NAME,
					Context.MODE_PRIVATE);
		}
		return sp.getInt(key, defValue);
	}

	// 往sp中存boolean值
	public static void putInt(Context context, String key, int value) {
		if(sp==null){
			sp = context.getSharedPreferences(NAME,
					Context.MODE_PRIVATE);
		}
		sp.edit().putInt(key, value).commit();
	}
	// 从sp中获取一个boolean值
	public static boolean getBoolean(Context context, String key,
                                     boolean defValue) {
		if(sp==null){
			sp = context.getSharedPreferences(NAME,
					Context.MODE_PRIVATE);
		}
		return sp.getBoolean(key, defValue);
	}

	// 往sp中存boolean值
	public static void putBoolean(Context context, String key, boolean value) {
		if(sp==null){
			sp = context.getSharedPreferences(NAME,
					Context.MODE_PRIVATE);
		}
		sp.edit().putBoolean(key, value).commit();
	}
	
	// 往sp中存String值
	public static void putString(Context context, String key, String value) {
		if(sp==null){
			sp = context.getSharedPreferences(NAME,
					Context.MODE_PRIVATE);
		}
		sp.edit().putString(key, value).commit();
	}
	
	// 往sp中取String值
	public static String getString(Context context, String key,
                                   String defValue) {
		if(sp==null){
			sp = context.getSharedPreferences(NAME,
					Context.MODE_PRIVATE);
		}
		return sp.getString(key, defValue);
	}

}

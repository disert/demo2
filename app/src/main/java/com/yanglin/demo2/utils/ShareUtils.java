package com.yanglin.demo2.utils;

/**
 * 作者 Administrator
 * 时间   2017/7/15 0015 16:18.
 * 描述   ${TODO}
 */

import android.content.Context;

import com.yanglin.demo2.Constants;
import com.yanglin.demo2.GlobalContext;


/**
 * 功 能： <br>
 * 时 间：2016/11/15 17:16 <br>
 */

public class ShareUtils {
    public static void putShare(String key, String value) {
        GlobalContext.getContext()
                .getSharedPreferences(Constants.SP_PACKAGE, Context.MODE_PRIVATE)
                .edit()
                .putString(key, value)
                .apply();
    }

    public static void putShare(String key, boolean value) {
        GlobalContext.getContext()
                .getSharedPreferences(Constants.SP_PACKAGE, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(key, value)
                .apply();
    }
    public static void clear() {
        GlobalContext.getContext()
                .getSharedPreferences(Constants.SP_PACKAGE, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .commit();
    }
    public static void putShareInt(String key, int value) {
        GlobalContext.getContext()
                .getSharedPreferences(Constants.SP_PACKAGE, Context.MODE_PRIVATE)
                .edit()
                .putInt(key, value)
                .apply();
    }
    public static void putShareLong(String key, long value) {
        GlobalContext.getContext()
                .getSharedPreferences(Constants.SP_PACKAGE, Context.MODE_PRIVATE)
                .edit()
                .putLong(key, value)
                .apply();
    }

    public static int getShareInt(String key, int defValue) {
        return GlobalContext.getContext()
                .getSharedPreferences(Constants.SP_PACKAGE, Context.MODE_PRIVATE)
                .getInt(key, defValue);
    }
    public static long getShareLong(String key, long defValue) {
        return GlobalContext.getContext()
                .getSharedPreferences(Constants.SP_PACKAGE, Context.MODE_PRIVATE)
                .getLong(key, defValue);
    }
    public static boolean getShare(String key, boolean defValue) {
        return GlobalContext.getContext()
                .getSharedPreferences(Constants.SP_PACKAGE, Context.MODE_PRIVATE)
                .getBoolean(key, defValue);
    }

    public static String getShare(String key, String defValue) {
        return GlobalContext.getContext()
                .getSharedPreferences(Constants.SP_PACKAGE, Context.MODE_PRIVATE)
                .getString(key, defValue);
    }
}

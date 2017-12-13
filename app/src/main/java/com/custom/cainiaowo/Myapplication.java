package com.custom.cainiaowo;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by xuchichi on 2017/12/13.
 */
public class Myapplication extends Application {
    public static  OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build(); //设置各种超时时间
}

package com.example.scs.rxretrofitmvp.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.scs.rxretrofitmvp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by scs on 18-3-12.
 */

public class OkHttp3Utils {
    private static OkHttpClient okHttpClient = null;
    private static final int DEFAULT_TIMEOUT = 10;
    private static Context mContext;

    public static OkHttpClient getInstence() {
        if (okHttpClient == null) {
            synchronized (OkHttp3Utils.class) {
                if (okHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    builder.readTimeout(3, TimeUnit.SECONDS)//读操作超时时间
                            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS) //设置连接超时 30秒
                            .writeTimeout(3, TimeUnit.MINUTES)//写操作 超时时间
                            .addInterceptor(new LoggingInterceptor())//添加请求拦截
                            .retryOnConnectionFailure(true);

                    //如果不是在正式包，添加拦截 打印响应json
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(
                                new HttpLoggingInterceptor.Logger() {
                                    @Override
                                    public void log(String message) {
                                        if (TextUtils.isEmpty(message)) return;
                                        String s = message.substring(0, 1);
                                        //如果收到响应是json才打印
                                        if ("{".equals(s) || "[".equals(s)) {
                                            Log.i("HttpLog","收到响应: " + message);
                                        }
                                    }
                                });
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                        builder.addInterceptor(logging);
                    }
                    okHttpClient = builder.build();
                }
            }
        }
        return okHttpClient;
    }
}

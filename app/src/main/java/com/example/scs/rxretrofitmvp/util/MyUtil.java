package com.example.scs.rxretrofitmvp.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by scs on 18-3-12.
 */

public class MyUtil {

    /**
     * 解绑广播
     *
     * @param receiver
     * @param <T>
     */
    public static <T extends BroadcastReceiver> void unregister(Context context, T receiver) {
        if (receiver != null)
            context.unregisterReceiver(receiver);
    }

    /**
     * 注册广播
     *
     * @param receiver 广播
     * @param action   广播过滤action
     * @param <T>
     */
    public static <T extends BroadcastReceiver> void register(Context context, T receiver, String action) {
        IntentFilter filter = new IntentFilter();
        if (action != null) {
            filter.addAction(action);
        }
        context.registerReceiver(receiver, filter);
    }
    /**
     * 注册广播
     *
     * @param context  上下文
     * @param receiver 广播
     * @param actions  存放广播过滤action的数组
     * @param <T>
     */
    public static <T extends BroadcastReceiver> void register(Context context, T receiver, String... actions) {
        Log.d("chat","broadcast receiver register success");
        IntentFilter filter = new IntentFilter();
        if (actions != null) {
            for (String action : actions) {
                filter.addAction(action);
            }
        }
        context.registerReceiver(receiver, filter);
    }
    /**
     * 发送广播
     * @param context
     * @param action
     */
    public static void sendBroadcast(Context context,String action){
        Intent intent = new Intent();
        intent.setAction(action);
        context.sendBroadcast(intent);
    }

    /**
     * 发送广播
     * @param context
     * @param action
     * @param bundle
     */
    public static void sendBroadcast(Context context,String action,String bundleName,Bundle bundle){
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle!=null)
            intent.putExtra(bundleName, bundle);
        context.sendBroadcast(intent);
    }
}

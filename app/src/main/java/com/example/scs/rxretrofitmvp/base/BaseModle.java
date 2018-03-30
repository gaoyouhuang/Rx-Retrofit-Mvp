package com.example.scs.rxretrofitmvp.base;

/**
 * Created by scs on 18-3-12.
 */

public interface BaseModle {
    interface HttpListener {
        <T extends Object> void httpSuccess(T msg);
        void httpFail();
    }
}

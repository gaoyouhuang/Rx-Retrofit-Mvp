package com.example.scs.rxretrofitmvp.m;

import com.example.scs.rxretrofitmvp.base.BaseModle;
import com.example.scs.rxretrofitmvp.listener.RxHttpListener;
import com.example.scs.rxretrofitmvp.util.RetrofitUtils;
import com.example.scs.rxretrofitmvp.webapi.Request_Interface;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by scs on 18-3-12.
 */

public class LoginModle implements BaseModle {
    public void Login(Map<String, String> loginmap, final HttpListener httpListener) {
        Observable observable = RetrofitUtils.getHttpInstence().create(Request_Interface.class).login(loginmap);
        RetrofitUtils.getInstence().httpResponseListener(observable, new RxHttpListener() {
            @Override
            public void success(String msg, String method) {
                System.out.println("success　msg = " + msg);
                httpListener.httpSuccess(msg);
            }

            @Override
            public void error(int code, String msg, String method) {
                System.out.println("error　msg = " + msg);
                httpListener.httpFail();
            }

            @Override
            public void doonNext(String msg, String method) {
                System.out.println("缓存　msg = " + msg);
            }
        });
    }
}

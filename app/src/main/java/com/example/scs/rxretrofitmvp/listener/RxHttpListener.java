package com.example.scs.rxretrofitmvp.listener;

/**
 * Created by scs on 18-3-13.
 */

public interface RxHttpListener {

    <T extends Object> void success(String msg, String method);

    void error(int code, String msg, String method);

    <T extends Object> void doonNext( String msg, String method);
}

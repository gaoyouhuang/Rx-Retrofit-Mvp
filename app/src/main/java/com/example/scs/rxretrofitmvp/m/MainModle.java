package com.example.scs.rxretrofitmvp.m;

import com.example.scs.rxretrofitmvp.base.BaseModle;
import com.example.scs.rxretrofitmvp.util.RetrofitUtils;
import com.example.scs.rxretrofitmvp.webapi.Request_Interface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by scs on 18-3-12.
 */

public class MainModle implements BaseModle {
    public void getMainData(final HttpListener httpListener) {
        RetrofitUtils.getHttpInstence().create(Request_Interface.class).getUserScenes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        httpListener.httpSuccess(responseBody.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        httpListener.httpFail();
                    }
                });
    }
}

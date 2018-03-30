package com.example.scs.rxretrofitmvp.p;

import com.example.scs.rxretrofitmvp.base.BaseModle;
import com.example.scs.rxretrofitmvp.base.BasePresenter;
import com.example.scs.rxretrofitmvp.m.MainModle;
import com.example.scs.rxretrofitmvp.v.MainView;

/**
 * Created by scs on 18-3-12.
 */

public class MainPresenter extends BasePresenter<MainView> {
    private MainModle mainModle;

    public MainPresenter() {
        mainModle = new MainModle();
    }

    public void getMainData() {
        getView().ShowLoading();
        mainModle.getMainData(new BaseModle.HttpListener() {
            @Override
            public <T> void httpSuccess(T msg) {
                getView().CencleLoading();
                getView().MainSuccess(msg);
            }

            @Override
            public void httpFail() {
                getView().CencleLoading();
                getView().MainFail();
            }
        });
    }
}

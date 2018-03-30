package com.example.scs.rxretrofitmvp.p;

import com.example.scs.rxretrofitmvp.base.BaseModle;
import com.example.scs.rxretrofitmvp.base.BasePresenter;
import com.example.scs.rxretrofitmvp.m.LoginModle;
import com.example.scs.rxretrofitmvp.v.LoginView;

import java.util.Map;

/**
 * Created by scs on 18-3-12.
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    private LoginModle loginModle;

    public LoginPresenter() {
        loginModle = new LoginModle();
    }

    public void login(Map<String, String> loginmap) {
        getView().ShowLoading();
        loginModle.Login(loginmap, new BaseModle.HttpListener() {
            @Override
            public <T> void httpSuccess(T msg) {
                getView().CencleLoading();
                getView().loginSuccess(msg);
            }

            @Override
            public void httpFail() {
                getView().CencleLoading();
                getView().loginFail();
            }
        });
    }
}

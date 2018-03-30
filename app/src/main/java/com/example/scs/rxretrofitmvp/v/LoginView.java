package com.example.scs.rxretrofitmvp.v;

import com.example.scs.rxretrofitmvp.base.BaseView;

/**
 * Created by scs on 18-3-12.
 */

public interface LoginView extends BaseView {
    <T extends Object>void loginSuccess(T msg);

    void loginFail();
}

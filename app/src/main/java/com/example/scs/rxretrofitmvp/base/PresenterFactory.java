package com.example.scs.rxretrofitmvp.base;

/**
 * Created by ideal-gn on 2017/6/29.
 */

public interface PresenterFactory<P extends BasePresenter> {

    P create();
}

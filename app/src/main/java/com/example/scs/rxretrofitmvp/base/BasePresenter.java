package com.example.scs.rxretrofitmvp.base;

import java.lang.ref.WeakReference;

/**
 * Created by scs on 18-3-12.
 */

public class BasePresenter<V extends BaseView> {
    protected WeakReference<V> weakReference;

    public void attach(V view) {
        weakReference = new WeakReference<V>(view);
    }

    public V getView() {
        if (weakReference != null)
            return weakReference.get();
        else
            return null;
    }

    public void detach() {
        if (weakReference != null)
            weakReference.clear();
    }
}

package com.example.scs.rxretrofitmvp.util.exception;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * Created by scs on 18-3-13.
 */

public class ExceptionFunc implements Function<Throwable, ObservableSource<? extends ResponseBody>> {

    @Override
    public ObservableSource<? extends ResponseBody> apply(Throwable throwable) throws Exception {
        return Observable.error(throwable);
    }
}

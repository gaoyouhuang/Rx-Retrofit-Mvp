package com.example.scs.rxretrofitmvp.util.exception;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by scs on 18-3-13.
 */

public class RetryWithDelay implements Function<Observable<Throwable>, ObservableSource<?>> {

    /* retry次数*/
    private int count = 0;
    /*延迟*/
    private long delay = 100;
    /*叠加延迟*/
    private long maxcount = 1;

    public RetryWithDelay(long delay) {
        this.delay = delay;
    }

    public RetryWithDelay(long delay, long maxcount) {
        this.delay = delay;
        this.maxcount = maxcount;
    }

    @Override
    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
        return throwableObservable
                .flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        count++;
                        if ((throwable instanceof ConnectException
                                || throwable instanceof SocketTimeoutException
                                || throwable instanceof TimeoutException)
                                && count <= maxcount) { //如果超出重试次数也抛出错误，否则默认是会进入onCompleted
                            return Observable.timer(delay , TimeUnit.MILLISECONDS);
                        }
                        return Observable.error(throwable);

                    }
                });
    }
}

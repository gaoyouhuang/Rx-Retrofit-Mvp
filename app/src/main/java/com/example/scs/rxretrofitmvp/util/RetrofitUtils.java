package com.example.scs.rxretrofitmvp.util;

import com.example.scs.rxretrofitmvp.listener.RxHttpListener;
import com.example.scs.rxretrofitmvp.util.exception.ApiException;
import com.example.scs.rxretrofitmvp.util.exception.FactoryException;
import com.example.scs.rxretrofitmvp.util.exception.RetryWithDelay;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by scs on 18-3-12.
 */

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;
    private Retrofit retrofit;

    public Retrofit getRetrofit() {
        return retrofit;
    }

    private RetrofitUtils() {
        if (retrofit == null) {
            OkHttpClient client = OkHttp3Utils.getInstence();
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .setLenient()
                    .create();//使用 gson coverter，统一日期请求格式
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.sojson.com/open/")
                    .addConverterFactory(StringConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
    }

    public static RetrofitUtils getInstence() {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    public static Retrofit getHttpInstence() {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils.getRetrofit();
    }

    public static void removeRetrofit() {
        retrofitUtils = null;
    }


    public void httpResponseListener(Observable observable, final RxHttpListener listener) {
        if (listener == null) {
            new RuntimeException("listener is null");
        }
        observable.retryWhen(new RetryWithDelay(1, 100))
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends ResponseBody>>() {
                    @Override
                    public ObservableSource<? extends ResponseBody> apply(Throwable throwable) throws Exception {
                        return Observable.error(throwable);
                    }
                })
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(ResponseBody stringBaseResponse) throws Exception {
//                        if (stringBaseResponse.isSuccess()) {
                            return stringBaseResponse.string();
//                        } else {
//                            return Observable.error(new HttpTimeException(CHACHE_TIMEOUT_ERROR));
//                        }
                    }
                })
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String baseResponse) throws Exception {
                        listener.doonNext(baseResponse,"login");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String baseResponse) throws Exception {
                        listener.success(baseResponse,"login");

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ApiException apiException = FactoryException.analysisExcetpion(throwable);
                        listener.error(apiException.getCode(),apiException.getDisplayMessage(),"login");

                    }
                });
    }
}

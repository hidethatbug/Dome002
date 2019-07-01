package com.bawei.dome002.http;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author:程金柱
 * Date:2019/6/29 8:53
 * Description：
 */

public class HttpUtile {
    private static HttpUtile httpUtile;
    private final Retrofit retrofit;

    private HttpUtile() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://172.17.8.100/")
                .build();
    }

    /**
     * 双锁
     *
     * @return
     */
    public static HttpUtile httpUtile() {
        if (httpUtile == null) {
            synchronized (HttpUtile.class) {
                if (httpUtile == null) {
                    httpUtile = new HttpUtile();
                }
            }
        }
        return httpUtile;
    }

    /**
     * 动态网络代理
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getRxjava(Class<T> tClass) {
        return retrofit.create(tClass);
    }
}

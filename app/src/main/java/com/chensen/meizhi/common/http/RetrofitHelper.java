package com.chensen.meizhi.common.http;


import com.chensen.meizhi.bean.GirlBean;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * author：chasen
 * time: 2016/11/25 22:01
 * description：
 */

public class RetrofitHelper {
    public static RetrofitHelper instance;
    private ApiService mGirlService;


    public RetrofitHelper() {
        Retrofit girlRetrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mGirlService = girlRetrofit.create(ApiService.class);

    }

    public static RetrofitHelper getInstance() {
        if (instance == null) {
            synchronized (RetrofitHelper.class) {
                if (instance == null) {
                    instance = new RetrofitHelper();
                }
            }
        }
        return instance;
    }

    /**
     * 获取妹子列表
     */
    public Observable<GirlHttpResponse<List<GirlBean>>> getGirlList(int page, int num) {
        if (mGirlService != null) {
            new RetrofitHelper();
        }
        return mGirlService.getGirlList(num, page);
    }

}

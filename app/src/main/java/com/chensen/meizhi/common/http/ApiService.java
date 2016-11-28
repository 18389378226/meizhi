package com.chensen.meizhi.common.http;

import com.chensen.meizhi.bean.GirlBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * author：chensen on 2016/11/25 14:09
 * desc：
 */

public interface ApiService {
    /**
     * 妹子列表
     */
    @GET("data/福利/{num}/{page}")
    Observable<GirlHttpResponse<List<GirlBean>>> getGirlList(@Path("num") int num, @Path("page") int page);


}
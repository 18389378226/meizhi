package com.chensen.meizhi.mvp.present;

import com.chensen.meizhi.bean.GirlBean;
import com.chensen.meizhi.common.http.GirlHttpResponse;
import com.chensen.meizhi.common.http.RetrofitHelper;
import com.chensen.meizhi.mvp.contact.GirlContact;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author：chensen on 2016/11/25 16:22
 * desc：
 */

public class GirlPresent implements GirlContact.Present {
    private GirlContact.View mView;
    private int page = 1;

    public GirlPresent(GirlContact.View mView) {
        this.mView = mView;
    }

    @Override
    public void getGirlList() {
        page = 1;
        RetrofitHelper.getInstance()
                .getGirlList(page, 16)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GirlHttpResponse<List<GirlBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView != null) {
                            mView.showFail("获取数据失败");
                        }

                    }

                    @Override
                    public void onNext(GirlHttpResponse<List<GirlBean>> listGirlHttpResponse) {
                        if (mView != null) {

                            if (!listGirlHttpResponse.isError()) {
                                if (listGirlHttpResponse.getResults() != null
                                        && listGirlHttpResponse.getResults().size() > 0) {

                                    mView.showContent(listGirlHttpResponse.getResults());

                                } else {
                                    mView.showFail("暂无数据");
                                }


                            } else {
                                mView.showFail("获取数据失败");
                            }
                        }
                    }
                });
    }

    @Override
    public void getMoreGirl() {
        page++;
        RetrofitHelper.getInstance()
                .getGirlList(page, 16)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GirlHttpResponse<List<GirlBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView != null) {
                            mView.showFail(e.toString());
                        }

                    }

                    @Override
                    public void onNext(GirlHttpResponse<List<GirlBean>> listGirlHttpResponse) {
                        if (mView != null) {
                            mView.showMoreGirl(listGirlHttpResponse.getResults());
                        }
                    }
                });


    }

    @Override
    public void detachView() {
        mView = null;

    }
}

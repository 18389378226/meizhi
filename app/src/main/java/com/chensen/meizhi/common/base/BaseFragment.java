package com.chensen.meizhi.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * author：chensen on 2016/11/25 11:51
 * desc：
 */

public abstract class BaseFragment extends Fragment {
    protected View mRootView;
    protected Context mContext;

    public BaseFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mRootView);
        intiView();
        return mRootView;
    }
    protected abstract void intiView();
    public abstract int getLayoutId();


}

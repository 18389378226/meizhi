package com.chensen.meizhi.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.chensen.meizhi.R;
import com.chensen.meizhi.common.Constants;
import com.chensen.meizhi.common.base.BaseActivity;
import com.chensen.meizhi.common.http.ImageLoader;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;

/**
 * author：chensen on 2016/11/29 09:03
 * desc：
 */

public class GirlDetailActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.photo_view)
    PhotoView photoView;

    private  String mUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBar(toolBar, "妹纸");
        initData();
    }

    private void initData() {
        mUrl = getIntent().getStringExtra(Constants.GIRL_URL);
        ImageLoader.load(mContext,mUrl,photoView);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_girldetail;
    }
}

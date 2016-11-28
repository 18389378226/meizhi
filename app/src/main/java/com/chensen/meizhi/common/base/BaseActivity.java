package com.chensen.meizhi.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * author：chensen on 2016/11/25 11:44
 * desc：
 */

public abstract class BaseActivity extends AppCompatActivity {
    private ArrayList<Activity> mActivitys = new ArrayList<>();
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mActivitys.add(this);
        mContext = this;
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivitys.remove(this);
    }


    public void finishAll() {
        for (Activity activity : mActivitys) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

    public abstract int getLayoutId();


    protected void setToolBar(Toolbar toolBar, String title) {
        toolBar.setTitle(title);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();

                } else {
                    finish();
                }

            }
        });

    }
}

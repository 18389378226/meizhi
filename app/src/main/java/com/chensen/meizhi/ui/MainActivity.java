package com.chensen.meizhi.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.bumptech.glide.gifdecoder.GifDecoder;
import com.chensen.meizhi.R;
import com.chensen.meizhi.bean.GirlBean;
import com.chensen.meizhi.common.base.BaseActivity;
import com.chensen.meizhi.ui.fragment.GirlFragment;

import butterknife.BindView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {


    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    private ActionBarDrawerToggle toggle;

    private GirlFragment girlFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }


    private void initView() {

        toolBar.setTitle("每日一妹纸");
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.open, R.string.close);
        toggle.syncState();

        drawerLayout.addDrawerListener(toggle);

    }

    private void initData() {
        girlFragment = new GirlFragment(mContext);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_content, girlFragment)
                .commit();


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void onBackPressed() {

        if (navigation.isShown()) {
            drawerLayout.closeDrawers();
        } else {
            showExit();
        }


    }

    private void showExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("提示")
                .setMessage("你确定要退出吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finishAll();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create()
                .setCanceledOnTouchOutside(true);
        builder.show();

    }


}

package com.chensen.meizhi.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chensen.meizhi.R;
import com.chensen.meizhi.adapter.AdapterGirl;
import com.chensen.meizhi.bean.GirlBean;
import com.chensen.meizhi.common.base.BaseFragment;
import com.chensen.meizhi.common.utils.SnackBarUtil;
import com.chensen.meizhi.mvp.contact.GirlContact;
import com.chensen.meizhi.mvp.present.GirlPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author：chensen on 2016/11/25 14:47
 * desc：
 */

public class GirlFragment extends BaseFragment implements GirlContact.View {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.floatActionBtn)
    FloatingActionButton floatActionBtn;


    private GirlPresent girlPresent;
    private AdapterGirl adapterGirl;
    private ArrayList<GirlBean> mList = new ArrayList<>();

    public GirlFragment(Context mContext) {
        super(mContext);
    }


    @Override
    protected void intiView() {
        intiSwipeRefreshLayout();
        intiRecyclerView();

    }

    private void intiSwipeRefreshLayout() {
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                girlPresent.getGirlList();
            }
        });
        girlPresent = new GirlPresent(this);
        girlPresent.getGirlList();
    }

    private void intiRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapterGirl = new AdapterGirl(mContext, mList, recyclerView);
        recyclerView.setAdapter(adapterGirl);


        adapterGirl.setOnItemClickListener(new AdapterGirl.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

        adapterGirl.setOnLoadMoreListener(new AdapterGirl.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                girlPresent.getMoreGirl();
            }
        });

        floatActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }


    @Override
    public void showContent(List<GirlBean> data) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        mList.clear();
        mList.addAll(data);
        adapterGirl.notifyDataSetChanged();

    }

    @Override
    public void showMoreGirl(List<GirlBean> data) {
        adapterGirl.loadCompleted();
        if (data != null && data.size() > 0) {
            mList.addAll(data);
            adapterGirl.notifyDataSetChanged();

        } else {
            SnackBarUtil.showLong(mRootView, "没有更多数据了");
        }

    }

    @Override
    public void showFail(String msg) {
        adapterGirl.loadCompleted();
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        SnackBarUtil.showLong(mRootView, "获取数据失败");

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_girl;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        girlPresent.detachView();
    }
}

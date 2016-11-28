package com.chensen.meizhi.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chensen.meizhi.R;
import com.chensen.meizhi.bean.GirlBean;
import com.chensen.meizhi.common.base.BaseViewHolder;
import com.chensen.meizhi.common.http.ImageLoader;
import com.chensen.meizhi.common.utils.ScreenUtil;

import java.util.ArrayList;

/**
 * author：chasen
 * time: 2016/11/25 23:23
 * description：
 */

public class AdapterGirl extends RecyclerView.Adapter {

    private final int item_type = 100;
    private final int load_type = 101;

    private boolean isFirst = true;
    private boolean isLoading = false;

    private Context mContext;
    private ArrayList<GirlBean> mList;
    private RecyclerView mRecyclerView;

    private OnItemClickListener mItemClickListener;
    private OnLoadMoreListener mOnLoadMoreListener;


    public AdapterGirl(Context mContext, ArrayList<GirlBean> mList, RecyclerView mRecyclerView) {
        this.mContext = mContext;
        this.mList = mList;
        this.mRecyclerView = mRecyclerView;
        initRecyclerView();
    }

    private void initRecyclerView() {
        GridLayoutManager mLayoutManager = (GridLayoutManager) mRecyclerView.getLayoutManager();
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == mList.size()) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                int itemCount = layoutManager.getItemCount();

                if (lastVisibleItemPosition > itemCount - 2 && !isLoading && dy > 0) {
                    isLoading = true;
                    if (mOnLoadMoreListener != null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == load_type) {

            View loadView = LayoutInflater.from(mContext).inflate(R.layout.loading_view, parent, false);
            LoadViewHolder holder = new LoadViewHolder(loadView);
            return holder;
        } else {
            View girlView = LayoutInflater.from(mContext).inflate(R.layout.item_rv_girl, parent, false);
            BaseViewHolder holder = new BaseViewHolder(girlView);
            return holder;

        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof BaseViewHolder) {
            BaseViewHolder baseViewHolder = (BaseViewHolder) holder;
            ImageView imageView = baseViewHolder.getView(R.id.iv_gril);
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height = ScreenUtil.getScreenHeight(mContext) / 3;

            ImageLoader.load(mContext, mList.get(position).getUrl(), imageView);
            baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(view, position);
                }
            });

        }

    }

    @Override
    public int getItemViewType(int position) {

        if (position == mList.size()) {
            return load_type;
        } else {
            return item_type;
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mOnLoadMoreListener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void loadCompleted() {
        isLoading = false;

    }

    class LoadViewHolder extends RecyclerView.ViewHolder {
        public LoadViewHolder(View itemView) {
            super(itemView);
        }
    }
}

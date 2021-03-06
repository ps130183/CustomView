package com.ps.commonadapter.adapter.wrapper;

import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.ps.commonadapter.R;
import com.ps.commonadapter.adapter.CommonViewHolder;

/**
 * Created by PengSong on 17/12/12.
 */

public class LoadMoreWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_TYPE_LOAD_MORE = Integer.MAX_VALUE - 2;
    private static final int ITEM_TYPE_LOAD_MORE_FINISH = Integer.MAX_VALUE - 3;

    private RecyclerView.Adapter mInnerAdapter;

    //加载更多展示的内容
    private View mItemLoadMoreView;
    private @LayoutRes
    int mItemLoadMoreLayoutRes = R.layout.item_rv_load_more;


    //加载完最后一页 展示的内容
    private View mitemLoadMoreFinishView;
    private @LayoutRes
    int mItemLoadMoreFinishLayoutRes = R.layout.item_rv_load_more_finish;

    //加载更多 监听
    private OnLoadMoreListener onLoadMoreListener;

    //根据该字段 判断是否加载完最后一页
    private boolean isLoadMoreFinish = false;

    //当前页数 以及 下一页
    private int mCurPage = 1;
    private int mNextPage = mCurPage;

    public LoadMoreWrapper(RecyclerView.Adapter mInnerAdapter) {
        this.mInnerAdapter = mInnerAdapter;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_LOAD_MORE) {
            CommonViewHolder viewHolder;
            if (mItemLoadMoreView != null) {
                viewHolder = CommonViewHolder.getHolder(parent.getContext(), mItemLoadMoreView);
            } else {
                viewHolder = CommonViewHolder.getHolder(parent.getContext(), parent, mItemLoadMoreLayoutRes);
            }
            return viewHolder;
        } else if (viewType == ITEM_TYPE_LOAD_MORE_FINISH) {
            CommonViewHolder viewHolder;
            if (mitemLoadMoreFinishView != null) {
                viewHolder = CommonViewHolder.getHolder(parent.getContext(), mitemLoadMoreFinishView);
            } else {
                viewHolder = CommonViewHolder.getHolder(parent.getContext(), parent, mItemLoadMoreFinishLayoutRes);
            }
            return viewHolder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isShowLoadMore(position)) {
            Log.d("CommonAdapterActivity","加载更多   nextPage" + mNextPage);
            if (!isLoadMoreFinish && mNextPage != getmNextPage()){
                mNextPage = getmNextPage();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onLoadMoreListener.onLoadMoreRequest(LoadMoreWrapper.this,mNextPage);
                    }
                }, 2000);
            }
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mInnerAdapter.getItemCount() + (hasLoadMore() ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowLoadMore(position)) {
            if (isLoadMoreFinish) {
                return ITEM_TYPE_LOAD_MORE_FINISH;
            } else {
                return ITEM_TYPE_LOAD_MORE;
            }
        }
        return mInnerAdapter.getItemViewType(position);
    }

    /**
     * 是否有加载更多
     *
     * @return
     */
    private boolean hasLoadMore() {
        return mItemLoadMoreView != null || mItemLoadMoreLayoutRes != 0;
    }

    private boolean isShowLoadMore(int position) {
        return hasLoadMore() && position >= mInnerAdapter.getItemCount();
    }

    public void setmItemLoadMoreView(View mItemLoadMoreView) {
        this.mItemLoadMoreView = mItemLoadMoreView;
    }

    public void setmItemLoadMoreLayoutRes(int mItemLoadMoreLayoutRes) {
        this.mItemLoadMoreLayoutRes = mItemLoadMoreLayoutRes;
    }

    /**
     * 加载更多 监听
     */
    public interface OnLoadMoreListener {
        void onLoadMoreRequest(LoadMoreWrapper wrapper,int nextPage);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    /**
     * 是否已经加载完最后一页
     * @param loadMoreFinish
     */
    public void setLoadMoreFinish(boolean loadMoreFinish) {
        isLoadMoreFinish = loadMoreFinish;
        mCurPage = mNextPage;
        notifyDataSetChanged();
    }

    /**
     * 获取下一页的页数
     * @return
     */
    public int getmNextPage(){
        return mCurPage + 1;
    }
}

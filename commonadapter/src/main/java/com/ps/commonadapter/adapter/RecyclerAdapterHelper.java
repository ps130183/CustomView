package com.ps.commonadapter.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ps.commonadapter.recyclerviewAnimator.adapters.AlphaInAnimationAdapter;
import com.ps.commonadapter.recyclerviewAnimator.adapters.ScaleInAnimationAdapter;
import com.ps.commonadapter.recyclerviewAnimator.animators.BaseItemAnimator;
import com.ps.commonadapter.recyclerviewAnimator.animators.SlideInDownAnimator;
import com.ps.commonadapter.recyclerviewAnimator.animators.SlideInLeftToRightAnimator;
import com.ps.commonadapter.adapter.base.ItemViewDelegate;
import com.ps.commonadapter.adapter.wrapper.EmptyWrapper;
import com.ps.commonadapter.adapter.wrapper.HeaderAndFooterWrapper;
import com.ps.commonadapter.adapter.wrapper.LoadMoreWrapper;
import com.ps.commonadapter.recyclerviewAnimator.animators.SlideInLeftAnimator;
import com.ps.commonadapter.recyclerviewAnimator.animators.SlideInRightAnimator;
import com.ps.commonadapter.recyclerviewAnimator.animators.SlideInUpAnimator;

import java.util.List;


/**
 * Created by PengSong on 17/12/13.
 */

public class RecyclerAdapterHelper<T> {

    private RecyclerView mRecyclerView;

    private Context mContext;

    private RecyclerView.Adapter mAdapter;

    private MultiItemTypeAdapter<T> mMulitItemAdapter;
    private CommonAdapter<T> mCommonAdapter;

    public RecyclerAdapterHelper(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
        mContext = mRecyclerView.getContext();
    }

    /**
     * 设置itemAnimator
     * @param itemAnimator
     * @param duration
     * @return
     */
    public RecyclerAdapterHelper addItemAnimator(BaseItemAnimator itemAnimator,int duration){
        mRecyclerView.setItemAnimator(itemAnimator);
        setAnimatorDuration(duration);
        return this;
    }

    /**
     * 设置itemAnimator 左进右出
     * @param duration
     * @return
     */
    public RecyclerAdapterHelper addSlidInLeftToRightAnimator(int duration){
        mRecyclerView.setItemAnimator(new SlideInLeftToRightAnimator());
        setAnimatorDuration(duration);
        return this;
    }

    /**
     * 设置itemAnimator 从左侧
     * @param duration
     * @return
     */
    public RecyclerAdapterHelper addSlidInLeftAnimator(int duration){
        mRecyclerView.setItemAnimator(new SlideInLeftAnimator());
        setAnimatorDuration(duration);
        return this;
    }

    /**
     * 设置itemAnimator 从右侧
     * @param duration
     * @return
     */
    public RecyclerAdapterHelper addSlidInRightAnimator(int duration){
        mRecyclerView.setItemAnimator(new SlideInRightAnimator());
        setAnimatorDuration(duration);
        return this;
    }

    /**
     * 设置itemAnimator 向上
     * @param duration
     * @return
     */
    public RecyclerAdapterHelper addSlidInUpAnimator(int duration){
        mRecyclerView.setItemAnimator(new SlideInUpAnimator());
        setAnimatorDuration(duration);
        return this;
    }

    /**
     * 设置itemAnimator 向下
     * @param duration
     * @return
     */
    public RecyclerAdapterHelper addSlidInDownAnimator(int duration){
        mRecyclerView.setItemAnimator(new SlideInDownAnimator());
        setAnimatorDuration(duration);
        return this;
    }

    /**
     * 设置itemAnimator 动画的执行时间
     * @param duration
     */
    private void setAnimatorDuration(int duration){
        if (duration <= 0){
            return;
        }
        mRecyclerView.getItemAnimator().setAddDuration(duration);
        mRecyclerView.getItemAnimator().setChangeDuration(duration);
        mRecyclerView.getItemAnimator().setMoveDuration(duration);
        mRecyclerView.getItemAnimator().setRemoveDuration(duration);
    }

    /**
     * 给recyclerView添加 线性布局  默认水平方向
     * @return
     */
    public RecyclerAdapterHelper addLinearLayoutManager(){
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(llm);
        return this;
    }

    /**
     * 给recyclerView添加 线性布局
     * @param orientation
     * @return
     */
    public RecyclerAdapterHelper addLinearLayoutManager(int orientation){
        LinearLayoutManager llm = new LinearLayoutManager(mContext,orientation,false);
        mRecyclerView.setLayoutManager(llm);
        return this;
    }

    /**
     * 给recyclerView 添加网格布局 默认水平方向
     * @param spanCount
     * @return
     */
    public RecyclerAdapterHelper addGrigLayoutMnager(int spanCount){
        GridLayoutManager glm = new GridLayoutManager(mContext,spanCount);
        mRecyclerView.setLayoutManager(glm);
        return this;
    }

    /**
     * 给recyclerView添加网格布局
     * @param spanCount 
     * @param orientation 布局方向
     */
    public RecyclerAdapterHelper addGrigLayoutMnager(int spanCount,int orientation){
        GridLayoutManager glm = new GridLayoutManager(mContext,spanCount,orientation,false);
        mRecyclerView.setLayoutManager(glm);
        return this;
    }

    /**
     * 添加分割线
     * @param orientation
     * @return
     */
    public RecyclerAdapterHelper addDividerItemDecoration(int orientation){
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,orientation));
        return this;
    }

    /**
     * 添加commonAdapter
     * @param layoutRes
     * @param mDatas
     * @param commonConvert
     * @return
     */
    public RecyclerAdapterHelper addCommonAdapter(@LayoutRes int layoutRes, List<T> mDatas, final CommonConvert<T> commonConvert){
        mCommonAdapter = new CommonAdapter<T>(mContext,mDatas,layoutRes) {
            @Override
            public void convert(CommonViewHolder holder, T mData) {
                if (commonConvert != null){
                    commonConvert.convert(holder,mData);
                }
            }
        };
        mAdapter = mCommonAdapter;
        return this;
    }

    public interface CommonConvert<T>{
        void convert(CommonViewHolder holder,T mData);
    }

    public RecyclerAdapterHelper addMulitItemTypeAdapter(List<T> mDatas, List<ItemViewDelegate<T>> itemViewDelegates){
        mMulitItemAdapter = new MultiItemTypeAdapter<>(mContext,mDatas);
        for (int i = 0; i < itemViewDelegates.size(); i++){
            mMulitItemAdapter.addItemViewDelegate(itemViewDelegates.get(i));
        }
        mAdapter = mMulitItemAdapter;
        return this;
    }

    /**
     * 设置recyclerView 列表没有数据时展示的内容
     * @param emptyLayoutRes
     * @return
     */
    public RecyclerAdapterHelper addEmptyWrapper(@LayoutRes Integer emptyLayoutRes) {
        if (mAdapter == null){
            throw new IllegalArgumentException("mAdapter 不能为空，请先使用addCommonAdapter方法添加adapter");
        }
        EmptyWrapper wrapper = new EmptyWrapper(mAdapter);
        if (emptyLayoutRes != null){
            wrapper.setmEmptyLayoutRes(emptyLayoutRes);
        }
        mAdapter = wrapper;
        return this;
    }

    /**
     * 为recyclerView添加顶部 和 底部
     * @param headerLayoutIds
     * @param footerLayoutIds
     * @param loadHeaderAndFooterData
     * @return
     */
    public RecyclerAdapterHelper addHeaderAndFooterWrapper(Integer[] headerLayoutIds,
                                                           Integer[] footerLayoutIds,
                                                           HeaderAndFooterWrapper.LoadHeaderAndFooterData loadHeaderAndFooterData){
        if (mAdapter == null){
            throw new IllegalArgumentException("mAdapter 不能为空，请先使用addCommonAdapter方法添加adapter");
        }
        HeaderAndFooterWrapper wrapper = new HeaderAndFooterWrapper(mAdapter);
        if (headerLayoutIds != null){
            for (int i = 0; i < headerLayoutIds.length; i++){
                wrapper.addHeader(headerLayoutIds[i]);
            }
        }
        if (footerLayoutIds != null){
            for (int i = 0; i < footerLayoutIds.length; i++){
                wrapper.addFooter(footerLayoutIds[i]);
            }
        }
        if (loadHeaderAndFooterData != null){
            wrapper.addLoadHeaderAndFooterData(loadHeaderAndFooterData);
        }
        mAdapter = wrapper;
        return this;
    }

    /**
     * 给recyclerView 添加加载更多的功能
     * @param loadMoreListener
     * @return
     */
    public RecyclerAdapterHelper addLoadMoreWrapper(LoadMoreWrapper.OnLoadMoreListener loadMoreListener){
        if (mAdapter == null){
            throw new IllegalArgumentException("mAdapter 不能为空，请先使用addCommonAdapter方法添加adapter");
        }
        LoadMoreWrapper wrapper = new LoadMoreWrapper(mAdapter);
        if (loadMoreListener != null){
            wrapper.setOnLoadMoreListener(loadMoreListener);
        }
        mAdapter = wrapper;
        return this;
    }

    /**
     * itemView 进出屏幕的时候 缩放动画
     * @return
     */
    public RecyclerAdapterHelper addScaleInAdnimationForAdapter(){
        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(mAdapter);
        scaleInAnimationAdapter.setFirstOnly(false);
        mAdapter = scaleInAnimationAdapter;
        return this;
    }

    /**
     * itemView 进出屏幕的时候 透明动画
     * @return
     */
    public RecyclerAdapterHelper addAlphaInAnimatorForAdapter(){
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(mAdapter);
        alphaInAnimationAdapter.setFirstOnly(false);
        mAdapter = alphaInAnimationAdapter;
        return this;
    }

    public void create(){
        if (mRecyclerView != null && mAdapter != null){
//            ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(mAdapter);
//            scaleInAnimationAdapter.setFirstOnly(false);
//            mRecyclerView.setAdapter(scaleInAnimationAdapter);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            throw new IllegalArgumentException("mrecyclerView 或 mAdapter 不能为空，请先使用addCommonAdapter方法添加adapter");
        }
    }

    public RecyclerView.Adapter getmAdapter() {
        return mAdapter;
    }

    public MultiItemTypeAdapter<T> getmMulitItemAdapter() {
        return mMulitItemAdapter;
    }

    public CommonAdapter<T> getmCommonAdapter() {
        return mCommonAdapter;
    }
}

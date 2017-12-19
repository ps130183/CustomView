package com.ps.customview.menus.mulit;

import com.ps.commonadapter.adapter.CommonViewHolder;
import com.ps.commonadapter.adapter.base.ItemViewDelegate;
import com.ps.customview.R;

/**
 * Created by PengSong on 17/12/16.
 */

public class TwoDelegate implements ItemViewDelegate<Integer> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.delegate_two;
    }

    @Override
    public boolean isForViewType(Integer item, int position) {
        return position % 2 == 1;
    }

    @Override
    public void convert(CommonViewHolder holder, Integer integer, int position) {
        holder.setText(R.id.content,"第 " + integer + "个");
    }
}

package com.ps.customview.menus.mulit;

import com.ps.commonadapter.adapter.CommonViewHolder;
import com.ps.commonadapter.adapter.base.ItemViewDelegate;
import com.ps.customview.R;

/**
 * Created by PengSong on 17/12/16.
 */

public class OneDelegate implements ItemViewDelegate<Integer> {

    public OneDelegate() {
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.delegate_one;
    }

    @Override
    public boolean isForViewType(Integer item, int position) {
        return position%2 == 0;
    }

    @Override
    public void convert(CommonViewHolder holder, Integer integer, int position) {

    }

}

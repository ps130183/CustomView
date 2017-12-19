package com.ps.customview.menus.mulit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ps.commonadapter.adapter.MultiItemTypeAdapter;
import com.ps.commonadapter.adapter.RecyclerAdapterHelper;
import com.ps.commonadapter.adapter.base.ItemViewDelegate;
import com.ps.commonadapter.recyclerviewAnimator.animators.SlideInDownAnimator;
import com.ps.customview.R;

import java.util.ArrayList;
import java.util.List;

public class MulitItemTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulit_item_type);

        final List<Integer> mDatas = new ArrayList<>();
        for (int i = 0;i < 30; i++){
            mDatas.add(i);
        }

        List<ItemViewDelegate<Integer>> itemViewDelegates = new ArrayList<>();
        itemViewDelegates.add(new OneDelegate());
        itemViewDelegates.add(new TwoDelegate());
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        RecyclerAdapterHelper<Integer> mHelper = new RecyclerAdapterHelper<>(recyclerView);
        mHelper.addSlidInLeftToRightAnimator(0)
                .addLinearLayoutManager(LinearLayoutManager.VERTICAL)
                .addDividerItemDecoration(LinearLayoutManager.VERTICAL)
                .addMulitItemTypeAdapter(mDatas,itemViewDelegates)
                .addScaleInAdnimationForAdapter()
                .addAlphaInAnimatorForAdapter()
                .create();

        MultiItemTypeAdapter<Integer> adapter = mHelper.getmMulitItemAdapter();
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(view.getContext(),"this is " + mDatas.get(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

    }
}

package com.ps.customview.menus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ps.commonadapter.adapter.CommonAdapter;
import com.ps.customview.R;
import com.ps.commonadapter.adapter.CommonViewHolder;
import com.ps.commonadapter.adapter.DividerItemDecoration;
import com.ps.commonadapter.adapter.RecyclerAdapterHelper;
import com.ps.commonadapter.adapter.wrapper.HeaderAndFooterWrapper;
import com.ps.commonadapter.adapter.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;


public class CommonAdapterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "CommonAdapterActivity";

    private RecyclerView.Adapter mAdapter;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_adapter);
        init();
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);

        mDatas = new ArrayList<>();
        for (int i = 'A'; i <= 'Z'; i++) {
            String data = (char) i + "";
            mDatas.add(data);
        }

//        CustomItemAnimator animator = new CustomItemAnimator();
//        animator.setAddDuration(1000);
//        animator.setRemoveDuration(1000);
        Integer[] headerLayoutIds = {R.layout.item_rv_header1, R.layout.item_rv_header2};
        RecyclerAdapterHelper<String> mHelper = new RecyclerAdapterHelper<>(recyclerView);
        mHelper.addSlidInLeftToRightAnimator(0);
        mHelper.addLinearLayoutManager(LinearLayoutManager.VERTICAL)
                .addDividerItemDecoration(DividerItemDecoration.VERTICAL_LIST);
        mHelper.addCommonAdapter(R.layout.item_rv_common_demo, mDatas, new RecyclerAdapterHelper.CommonConvert<String>() {
            @Override
            public void convert(CommonViewHolder holder, String mData) {
                holder.setText(R.id.content, mData);
            }
        }).addEmptyWrapper(R.layout.item_rv_empty)
                .addHeaderAndFooterWrapper(headerLayoutIds, null, new HeaderAndFooterWrapper.LoadHeaderAndFooterData() {
                    @Override
                    public void loadHeaderData(CommonViewHolder holder, int position) {
                        if (position == 1){
                            holder.setText(R.id.tv_header, "这是动态加载的header2View 数据");
                        }
                    }

                    @Override
                    public void loadFooterData(CommonViewHolder holder, int position) {

                    }
                })
                .addLoadMoreWrapper(new LoadMoreWrapper.OnLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequest(LoadMoreWrapper wrapper, int nextPage) {
                        Log.d(TAG, "nextPage = " + nextPage);
                        int data1 = 'a';
                        char data = (char) (data1 + nextPage);
                        for (int i = 0; i < 5; i++) {
                            String str = data + "";
                            mDatas.add(str + i);
                        }
                        if ('e' == data) {
                            wrapper.setLoadMoreFinish(true);
                        } else {
                            wrapper.setLoadMoreFinish(false);
                        }
                    }
                })
                .addScaleInAdnimationForAdapter()
                .addAlphaInAnimatorForAdapter()
                .create();
        mAdapter = mHelper.getmAdapter();

        mHelper.getmCommonAdapter().setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void itemClick(CommonViewHolder holder, int position) {
                Toast.makeText(CommonAdapterActivity.this,"this is " + mDatas.get(position),Toast.LENGTH_SHORT).show();
            }
        });

        Button add = findViewById(R.id.btn_add);
        add.setOnClickListener(this);
        findViewById(R.id.btn_remove).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                if (mAdapter != null){
                    mDatas.add(3,"hehehe");
                    mAdapter.notifyItemInserted(3);
                }
                break;
            case R.id.btn_remove:
                if (mAdapter != null){
                    mDatas.remove(3);
                    mAdapter.notifyItemRemoved(3);
                }
                break;
        }
    }
}

package com.ps.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.ps.customview.menus.CommonAdapterActivity;
import com.ps.customview.menus.ShouXieActivity;
import com.ps.customview.menus.WaveActivity;
import com.ps.customview.menus.motion.MotionActivity;
import com.ps.customview.menus.mulit.MulitItemTypeActivity;
import com.ps.customview.menus.pinwheel.PinWheelActivity;
import com.ps.customview.view.BreakLineaView;
import com.ps.customview.view.WaveView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] menuNames = {"手写动画","波浪动画","曲线移动","大风车","通用的Adapter","mulitItemTypeAdapter"};
    Class[] classNames = {ShouXieActivity.class, WaveActivity.class, MotionActivity.class, PinWheelActivity.class, CommonAdapterActivity.class, MulitItemTypeActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvMenu = findViewById(R.id.rv_menus);

        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true);
        rvMenu.setLayoutManager(llm);

        List<MenuEntity> menuEntities = new ArrayList<>();
        for (int i = 0; i < menuNames.length; i++){
            menuEntities.add(new MenuEntity(classNames[i],menuNames[i]));
        }
        MenuAdapter adapter = new MenuAdapter(this,menuEntities);
        rvMenu.setAdapter(adapter);
    }
}

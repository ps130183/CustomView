package com.ps.customview.menus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ps.customview.R;
import com.ps.customview.view.WaveView;

public class WaveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);

        WaveView waveView = (WaveView) findViewById(R.id.wave);
        waveView.startAnimator();
    }
}

package com.ps.customview.menus.pinwheel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ps.customview.R;
import com.ps.customview.view.PinwheelView;

public class PinWheelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_wheel);

        Button btnStart = findViewById(R.id.btn_start);
        final PinwheelView pinwheelView = findViewById(R.id.pinwheel);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pinwheelView.startAnimator();
            }
        });
    }
}

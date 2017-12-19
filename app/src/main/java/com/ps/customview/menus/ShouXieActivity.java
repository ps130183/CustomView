package com.ps.customview.menus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ps.customview.R;
import com.ps.customview.view.BreakLineaView;

public class ShouXieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_xie);

        Button btnReset = (Button) findViewById(R.id.btn_reset);
        final BreakLineaView breakLineaView = (BreakLineaView) findViewById(R.id.break_line);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                breakLineaView.reset();
            }
        });
    }
}

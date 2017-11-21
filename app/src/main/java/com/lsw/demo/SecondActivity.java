package com.lsw.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private RelativeLayout questionLayout1;
    private ImageView openClose1;
    private TextView question1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        questionLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (question1.getVisibility() == View.GONE) {
                    openClose1.setRotationX(180);
//                    openClose1.animate().rotationX(180);
                    question1.setVisibility(View.VISIBLE);
                } else {
                    openClose1.setRotationX(0);
//                    openClose1.animate().rotationX(0);
                    question1.setVisibility(View.GONE);
                }

            }
        });
    }

    private void initView() {
        questionLayout1 = (RelativeLayout) findViewById(R.id.questionLayout1);
        openClose1 = (ImageView) findViewById(R.id.openClose1);
        question1 = (TextView) findViewById(R.id.question1);
    }
}

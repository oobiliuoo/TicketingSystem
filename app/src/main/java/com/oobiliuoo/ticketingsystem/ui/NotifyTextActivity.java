package com.oobiliuoo.ticketingsystem.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.oobiliuoo.ticketingsystem.R;
import com.oobiliuoo.ticketingsystem.data.NotifyText;

public class NotifyTextActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvTitle;
    private TextView tvTime;
    private TextView tvText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_text);

        initView();
    }

    private void initView() {
        NotifyText notifyText = (NotifyText) getIntent().getSerializableExtra("notify");

        toolbar = (Toolbar) findViewById(R.id.notifyText_toorbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTitle = (TextView) findViewById(R.id.notifyText_tv_title);
        tvTime = (TextView) findViewById(R.id.notifyText_tv_time);
        tvText = (TextView) findViewById(R.id.notifyText_tv_text);

        tvTitle.setText(notifyText.getTitle());
        tvTime.setText(notifyText.getTime());
        tvText.setText(notifyText.getText());
    }
}
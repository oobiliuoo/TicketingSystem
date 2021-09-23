package com.oobiliuoo.ticketingsystem.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.oobiliuoo.ticketingsystem.R;
import com.oobiliuoo.ticketingsystem.data.OrderInfo;
import com.oobiliuoo.ticketingsystem.data.UserInfo;
import com.oobiliuoo.ticketingsystem.utils.LBUtils;

import org.litepal.LitePal;

import java.util.List;

public class TicketQRCodeActivity extends AppCompatActivity {

    private OrderInfo orderInfo;
    private TextView tvScenicName;
    private TextView tvWorkTime;
    private TextView tvUserName;
    private TextView tvTel;
    private TextView tvNumber;
    private TextView tvType;
    private TextView tvStatue;
    private TextView tvSumPrice;
    private Button btnQuitTicket;
    private ImageButton ibtnBack;
    private String orderID;
    private ImageView ivQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_qrcode);

        initView();
    }

    private void initView() {
        orderInfo = (OrderInfo) getIntent().getSerializableExtra("orderInfo");
        orderID = orderInfo.getOderID();

        tvScenicName = (TextView) findViewById(R.id.qrcode_tv_name);
        tvWorkTime = (TextView) findViewById(R.id.qrcode_tv_workTime);
        tvUserName = (TextView) findViewById(R.id.qrcode_tv_userName);
        tvTel = (TextView) findViewById(R.id.qrcode_tv_tel);
        tvNumber = (TextView) findViewById(R.id.qrcode_tv_num);
        tvType = (TextView) findViewById(R.id.qrcode_tv_type);
        tvStatue = (TextView) findViewById(R.id.qrcode_tv_statue);
        tvSumPrice = (TextView) findViewById(R.id.qrcode_tv_sumPrice);
        btnQuitTicket = (Button) findViewById(R.id.qrcode_btn_quitTicket);
        ibtnBack = (ImageButton) findViewById(R.id.qrcode_ibtn_back);
        ivQRCode = (ImageView) findViewById(R.id.qrcode_iv_qrcode);

        tvScenicName.setText(orderInfo.getScenicName());
        tvWorkTime.setText(orderInfo.getWorkTime());

        String Tel = orderInfo.getTel();
        List<UserInfo> userList = LitePal.where("tel = ?", Tel).find(UserInfo.class);
        tvUserName.setText(userList.get(0).getNickName());
        tvTel.setText(Tel);

        tvNumber.setText(orderInfo.getOrderNumber().toString());
        tvType.setText(orderInfo.getOrderType());

        String statue = orderInfo.getOrderStatue();
        tvStatue.setText(statue);

        if(!"可使用".equals(statue)){
            btnQuitTicket.setVisibility(View.GONE);
        }

        tvSumPrice.setText(orderInfo.getSumPrice().toString());


        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnQuitTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderInfo info = new OrderInfo();
                info.setOrderStatue("已失效");
                info.updateAll("oderID = ?", orderID);
                tvStatue.setText("已失效");
                btnQuitTicket.setVisibility(View.GONE);
                LBUtils.showToast(TicketQRCodeActivity.this,"退票成功");
            }
        });

        String text = "单号：" + orderID +" 人数: "+ orderInfo.getOrderNumber() + " 状态："+ statue;
        Bitmap bitmap = LBUtils.createQRCodeBitmap(text,160,160,"UTF-8","H","1", Color.BLACK,Color.WHITE);
        ivQRCode.setImageBitmap(bitmap);

    }


}
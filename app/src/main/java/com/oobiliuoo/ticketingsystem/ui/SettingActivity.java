package com.oobiliuoo.ticketingsystem.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.oobiliuoo.ticketingsystem.R;
import com.oobiliuoo.ticketingsystem.data.UserInfo;
import com.oobiliuoo.ticketingsystem.utils.LBUtils;

import org.litepal.LitePal;

import java.util.List;

/**
 * @author biliu
 */
public class SettingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvNickName;
    private TextView tvTel;

    private Button btnQuit;
    private Button btnOUt;
    private UserInfo userInfo;
    private LinearLayout llMessage;
    private LinearLayout llSafe;
    private LinearLayout llUpdate;
    private LinearLayout llFk;
    private LinearLayout llAbout;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();

        mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case 1:
                        // 连接成功
                        break;
                    case 2:
                        // 连接失败
                        break;
                    case 3:
                        // 接收成功
                        changeUI();
                        break;
                    case 4:
                        break;
                    default:
                        break;
                }
            }
        };

        // 读取当前登录用户
        String tel = LBUtils.readCurrentUser(SettingActivity.this);
        //String tel = "";
        // 读取帐号信息
        if (tel != "") {
            readDate(tel);
        }
    }

    private void changeUI() {

        tvNickName.setText(userInfo.getNickName());
        tvTel.setText(userInfo.getTel());
    }

    /**
     * 从本地数据库中读取用户信息
     * tel: 当前登录帐号
     */
    private void readDate(String tel) {
        // 查询数据库中数据
        List<UserInfo> userList = LitePal.where("tel = ?", tel).find(UserInfo.class);
        // 查询到数据传递给handler进行UI更新

        userInfo = userList.get(0);

        LBUtils.sendMessage(mHandler, 3, "changeUI");
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.set_toorbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvNickName = findViewById(R.id.set_tv_nickName);
        tvTel = findViewById(R.id.set_tv_tel);

        btnQuit = (Button) findViewById(R.id.set_btn_quit);
        btnQuit.setOnClickListener(new MyClickListener());


        btnOUt = (Button) findViewById(R.id.set_btn_out);
        btnOUt.setOnClickListener(new MyClickListener());

        llMessage = (LinearLayout) findViewById(R.id.set_ll_message);
        llSafe = (LinearLayout) findViewById(R.id.set_ll_safe);
        llUpdate = (LinearLayout) findViewById(R.id.set_ll_update);
        llFk = (LinearLayout) findViewById(R.id.set_ll_fk);
        llAbout = (LinearLayout) findViewById(R.id.set_ll_about);


        llMessage.setOnClickListener(new MyClickListener());
        llSafe.setOnClickListener(new MyClickListener());
        llUpdate.setOnClickListener(new MyClickListener());
        llFk.setOnClickListener(new MyClickListener());
        llAbout.setOnClickListener(new MyClickListener());

    }


    /**
     * 修改本地账户
     */
    private void changeCurrentUser(String tel) {
        SharedPreferences.Editor editor = SettingActivity.this.getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putString("tel", tel);
        editor.apply();

    }

    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.set_btn_quit:
                    LBUtils.showToast(SettingActivity.this, "退出登录");
                    // 修改本地账户为"“
                    changeCurrentUser("");
                    // 打开登录界面并获取结果
                    startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                    finish();
                    break;
                case R.id.set_btn_out:
                    // 将帐号传回 MineFragment
                    Intent intent = new Intent();
                    intent.putExtra("register_return", "out");
                    setResult(RESULT_OK, intent);
                    finish();
                    break;
                case R.id.set_ll_message:
                    LBUtils.showToast(SettingActivity.this, "暂未开放");
                    break;
                case R.id.set_ll_safe:
                    LBUtils.showToast(SettingActivity.this, "暂未开放");
                    break;
                case R.id.set_ll_update:
                    LBUtils.showToast(SettingActivity.this, "暂无更新");
                    break;
                case R.id.set_ll_fk:
                    LBUtils.showToast(SettingActivity.this, "暂未开放");
                    break;
                case R.id.set_ll_about:
                    LBUtils.showLongToast(SettingActivity.this, "设计by Mr.biliu \n来自智慧旅游实训小组\n (刘博、彭渤、周淼柯、徐明明）");
                    break;
                default:
                    break;
            }
        }
    }


}
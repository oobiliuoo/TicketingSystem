package com.oobiliuoo.ticketingsystem.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.oobiliuoo.ticketingsystem.R;
import com.oobiliuoo.ticketingsystem.data.UserInfo;
import com.oobiliuoo.ticketingsystem.utils.LBUtils;

import org.litepal.LitePal;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final int MIX_NAME_LENGTH = 10 ;
    private static final int MAX_NAME_LENGTH = 12 ;
    private static final int MIX_PWD_LENGTH = 5 ;


    private EditText etName;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private Handler mHandler;
    private String[] userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userInfo = new String[2];

        mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    // 连接成功
                    case 1:
                        break;
                    // 连接失败
                    case 2:
                        LBUtils.showToast(LoginActivity.this, "服务器连接失败，请检查网络设置");
                        break;
                    // 接收结果
                    case 3:
                        handMsg(msg.obj.toString());
                        break;
                    default:
                        break;
                }

            }
        };

        initView();
    }


    private void handMsg(String string) {
        if(LBUtils.RESPOND_ACCESS_LOGIN.equals(string)){
            LBUtils.showToast(LoginActivity.this,"登录成功");
            // 将帐号传回 MineFragment
            Intent intent = new Intent();
            intent.putExtra("login_return",userInfo[0]);
            setResult(RESULT_OK,intent);
            // 保存帐号到本地
            saveCurrentUser(userInfo[0]);

            finish();
        }
    }


    private void initView() {
        etName = findViewById(R.id.login_et_tel);
        etPassword = findViewById(R.id.login_et_pwd);

        btnLogin = findViewById(R.id.login_btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInfo[0] = etName.getText().toString();
                userInfo[1] = etPassword.getText().toString();
                if(userInfo[0].length() > MIX_NAME_LENGTH && userInfo[0].length() < MAX_NAME_LENGTH
                        && userInfo[1].length() > MIX_PWD_LENGTH){
                    if(LBUtils.isNumeric(userInfo[0])){
                        // TODO 连接服务器
                        /*
                        TcpClient mTcpClient = new TcpClient(mHandler, Utils.IP_ADDRESS, Utils.IP_PORT);
                        String message = Utils.REQUEST_LOGIN + userInfo[0] + Utils.DIVISION + userInfo[1];
                        mTcpClient.setSendMsg(message);
                        mTcpClient.send();
                         */
                        // 本地验证

                        List<UserInfo> users = LitePal.where("tel = ? ",userInfo[0] )
                                .find(UserInfo.class);
                        // 验证是否存在帐号
                        if(users.size()>0) {
                            // 验证密码是否正确
                            if(users.get(0).getPwd().equals(userInfo[1])){
                                // 登录成功
                                LBUtils.sendMessage(mHandler, 3, LBUtils.RESPOND_ACCESS_LOGIN);
                            }else {

                                LBUtils.showToast(LoginActivity.this,"密码不正确");
                            }
                        }else{
                            LBUtils.showToast(LoginActivity.this,"帐号不存在");
                        }

                    }else {
                        LBUtils.showToast(LoginActivity.this,"帐号输入出错");
                    }
                }else {
                    LBUtils.showToast(LoginActivity.this,"帐号或密码输入错误");
                }
            }
        });

        tvRegister = findViewById(R.id.login_tv_register);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //  LBUtils.showToast(LoginActivity.this, "注册");
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

    }


    /**
     *  保存当前用户到本地
     * */
    private void saveCurrentUser(String tel) {
        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        editor.putString("tel",tel);
        editor.apply();

    }

}
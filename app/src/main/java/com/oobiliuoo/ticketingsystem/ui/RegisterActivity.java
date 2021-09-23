package com.oobiliuoo.ticketingsystem.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.oobiliuoo.ticketingsystem.R;
import com.oobiliuoo.ticketingsystem.data.UserInfo;
import com.oobiliuoo.ticketingsystem.utils.LBUtils;

import org.litepal.LitePal;

/**
 * @author biliu
 * 注册
 */
public class RegisterActivity extends AppCompatActivity {

    /** 电话长度 */
    private static final int TEL_LENGTH = 11;
    /** 密码最小长度 */
    private static final int MIN_PWD_LENGTH = 6;


    private Button btnRegister;
    private EditText etTel;
    private EditText etName;
    private EditText etPwd;
    private EditText etPwd2;
    private ImageView ibtnBack;

    /** 保存用户输入*/
    private String[] userInfo;

    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        userInfo = new String[3];

        mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    // 连接成功
                    case 1:
                        break;
                    // 连接失败
                    case 2:
                        LBUtils.showToast(RegisterActivity.this, "服务器连接失败，请检查网络设置");
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


    /**
     *  处理服务器响应
     *  string: 服务器传回的信息
     * */
    private void handMsg(String string) {
        if(LBUtils.RESPOND_REGISTER_OK.equals(string)){
            // LBUtils.showToast(RegisterActivity.this,"注册成功" + string);
            // 将信息保存到本地数据库
            saveData();
            finish();
        }
    }



    private void initView() {

        etTel = findViewById(R.id.register_et_tel);
        etName= findViewById(R.id.register_et_nickName);
        etPwd = findViewById(R.id.register_et_pwd);
        etPwd2 = findViewById(R.id.register_et_pwd2);


        btnRegister = findViewById(R.id.register_btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userInfo[0] = etTel.getText().toString();
                userInfo[1] = etName.getText().toString();
                userInfo[2] = etPwd.getText().toString();
                // 检查输入格式是否正确
                if(!check()){
                    return;
                }
                /*
                // 将信息发送至远程服务器
                TcpClient tcpClient = new TcpClient(mHandler,Utils.IP_ADDRESS,Utils.IP_PORT);
                // 请求信息
                String str = Utils.REQUEST_REGISTER + userInfo[0] +Utils.DIVISION
                        + userInfo[1] + Utils.DIVISION + userInfo[2] + Utils.DIVISION
                        + userInfo[3];
                tcpClient.setSendMsg(str);
                tcpClient.send();
                Utils.showToast(RegisterActivity.this,"注册成功");

                 */

                LBUtils.sendMessage(mHandler,3,LBUtils.RESPOND_REGISTER_OK);


            }
        });

        ibtnBack = (ImageButton) findViewById(R.id.register_ibtn_back);
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     *  检查输入格式是否正确
     *
     * */
    private boolean check() {
        // 判断电话长度
        if(userInfo[0].length()!=TEL_LENGTH ){
            LBUtils.showToast(RegisterActivity.this,"电话输入有误");
            return false;
        }
        // 判断电话是否为纯数字
        if(!LBUtils.isNumeric(etTel.getText().toString())){
            LBUtils.showToast(RegisterActivity.this,"电话输入有误");
            return false;
        }
        // 判断密码格式
        if( userInfo[2].length()<MIN_PWD_LENGTH || etPwd2.getText().toString().length()<MIN_PWD_LENGTH ){
            LBUtils.showToast(RegisterActivity.this,"密码输入有误");
            return false;
        }
        // 判断两次密码是否相等
        String pwd = etPwd.getText().toString();
        String pwd2 = etPwd2.getText().toString();
        if(!pwd.equals(pwd2)){
            LBUtils.showToast(RegisterActivity.this,"密码不匹配");
            return false;
        }

        return true;
    }

    /**
     *  将用户信息保存到litePal数据库
     * */
    private void saveData() {
        // 首次执行需要下面语句创建数据库
        SQLiteDatabase db = LitePal.getDatabase();

        UserInfo user = new UserInfo();
        user.setTel(userInfo[0]);
        user.setNickName(userInfo[1]);
        user.setPwd(userInfo[2]);
        user.save();
        LBUtils.mLog1("DataBase","save ok "+user.getTel());
    }

}
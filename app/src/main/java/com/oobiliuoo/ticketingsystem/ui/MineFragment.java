package com.oobiliuoo.ticketingsystem.ui;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.oobiliuoo.ticketingsystem.R;
import com.oobiliuoo.ticketingsystem.adapter.NotifyTextAdapter;
import com.oobiliuoo.ticketingsystem.adapter.OrderInfoAdapter;
import com.oobiliuoo.ticketingsystem.data.OrderInfo;
import com.oobiliuoo.ticketingsystem.data.UserInfo;
import com.oobiliuoo.ticketingsystem.utils.LBUtils;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageButton ibtnLogin;
    private TextView tvNickName;

    private String tel;
    private Handler mHandler;
    private String userNickName;

    private TextView tv1,tv2,tv3,tvCurrent;

    private ListView listView;
    private List<OrderInfo> orderInfos;
    private boolean showList = false;


    private boolean isLogin = false;

    public MineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        ibtnLogin = (ImageButton) getView().findViewById(R.id.mine_userIcon);
        ibtnLogin.setOnClickListener(new MyClickListener());
        tvNickName = (TextView) getView().findViewById(R.id.mine_tv_nickName);

        tv1 = (TextView) getView().findViewById(R.id.mine_tv_1);
        tv1.setOnClickListener(new MyClickListener());
        tv2 = (TextView) getView().findViewById(R.id.mine_tv_2);
        tv2.setOnClickListener(new MyClickListener());
        tv3 = (TextView) getView().findViewById(R.id.mine_tv_3);
        tv3.setOnClickListener(new MyClickListener());
        tv1.setTextColor(getResources().getColor(R.color.green1));
        tv2.setTextColor(getResources().getColor(R.color.front_black));
        tv3.setTextColor(getResources().getColor(R.color.front_black));

        tvCurrent = tv1;

        mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case 1:
                        // 连接成功
                        LBUtils.showToast(getContext(), "连接成功");
                        break;
                    case 2:
                        // 连接失败
                        LBUtils.showToast(getContext(), "连接失败");
                        break;
                    case 3:
                        // 接收成功
                        changeUI();
                        break;
                    case  4:
                        break;
                    default:
                        break;
                }
            }
        };


      //  LBUtils.resetUserInfo();
        // 读取当前登录用户
        tel = LBUtils.readCurrentUser(getContext());
         //String tel = "";
        // 读取帐号信息
        if (tel != "") {
            readDate(tel);
            isLogin = true;
        } else {
            isLogin = false;
            ibtnLogin.setImageDrawable(getResources().getDrawable(R.drawable.mine_ibtn_login));
            tvNickName.setText("请先登录");
            if(showList){
                orderInfos.clear();
                OrderInfoAdapter adapter = new OrderInfoAdapter(getContext(),R.layout.mine_list_layout,orderInfos);
                listView = getView().findViewById(R.id.mine_lv);
                listView.deferNotifyDataSetChanged();
                listView.setAdapter(adapter);
            }
        }

    }

    private void changeUI() {
        ibtnLogin.setImageDrawable(getResources().getDrawable(R.drawable.ticket));
        tvNickName.setText(userNickName);

        if(showList && isLogin){
            OrderInfoAdapter adapter = new OrderInfoAdapter(getContext(),R.layout.mine_list_layout,orderInfos);
            listView = getView().findViewById(R.id.mine_lv);
            listView.deferNotifyDataSetChanged();
            listView.setAdapter(adapter);
        }


    }

    /**
     * 从本地数据库中读取用户信息
     * tel: 当前登录帐号
     */
    private void readDate(String tel) {
        // 查询数据库中数据
        List<UserInfo> userList = LitePal.where("tel = ?", tel).find(UserInfo.class);
        // 查询到数据传递给handler进行UI更新
        if(userList.size()>0){
            userNickName = userList.get(0).getNickName();
            readOrderInfo(userList.get(0).getTel(),"可使用");
            LBUtils.sendMessage(mHandler, 3, "changeUI");
        }

    }


    private void readOrderInfo(String tel, String key){
        if(orderInfos != null){
            orderInfos.clear();
        }
        List<OrderInfo> orderList;
        if(key!=""){
            orderList = LitePal.where("tel = ? and orderStatue = ?", tel, key).find(OrderInfo.class);
        }else {
            orderList = LitePal.where("tel = ?", tel).find(OrderInfo.class);
        }
        if(orderList.size()>0){
            orderInfos = orderList;
            showList = true;
        }else {
            showList = false;
        }

    }

    private void clearList(){
        if(orderInfos != null){
            orderInfos.clear();
            OrderInfoAdapter adapter = new OrderInfoAdapter(getContext(),R.layout.mine_list_layout,orderInfos);
            listView = getView().findViewById(R.id.mine_lv);
            listView.deferNotifyDataSetChanged();
            listView.setAdapter(adapter);
        }

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.mine_userIcon:
                    if(!isLogin){
                        startActivityForResult(new Intent(getActivity(), LoginActivity.class), 1);
                    }else {
                        startActivityForResult(new Intent(getActivity(),SettingActivity.class),2);
                    }
                    break;
                case R.id.mine_tv_1:
                   changeTab(v.getId());
                   break;

                case R.id.mine_tv_2:
                    changeTab(v.getId());
                    break;

                case R.id.mine_tv_3:
                    changeTab(v.getId());
                    break;

                default:
                    break;
            }
        }
    }

    private void changeTab(int position) {

        clearList();
        tvCurrent.setTextColor(getResources().getColor(R.color.front_black));
        switch (position){
            case R.id.mine_tv_1:
                if(isLogin){
                    readOrderInfo(tel,"可使用");
                    changeUI();
                }
            case 0:
                tv1.setTextColor(getResources().getColor(R.color.green1));
                tvCurrent = tv1;
                break;
            case R.id.mine_tv_2:
                if(isLogin) {
                    readOrderInfo(tel, "已失效");
                    changeUI();
                }
            case 1:
                tv2.setTextColor(getResources().getColor(R.color.green1));
                tvCurrent = tv2;
                break;
            case R.id.mine_tv_3:
                if(isLogin) {
                    readOrderInfo(tel, "");
                    changeUI();
                }
            case 2:
                tv3.setTextColor(getResources().getColor(R.color.green1));
                tvCurrent = tv3;
                break;

        }
    }

    /**
     * 上一个活动结束时的回调函数
     * 取出返回结果
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("login_return");
                   // LBUtils.showToast(getContext(), returnedData);
                    isLogin=true;
                    readDate(returnedData);
                }
                break;

            case 2:
                if (resultCode == RESULT_OK) {
                  getActivity().finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }
}
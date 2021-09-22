package com.oobiliuoo.ticketingsystem.adapter;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.oobiliuoo.ticketingsystem.R;
import com.oobiliuoo.ticketingsystem.data.OrderInfo;
import com.oobiliuoo.ticketingsystem.data.ScenicSpot;
import com.oobiliuoo.ticketingsystem.utils.LBUtils;
import com.oobiliuoo.ticketingsystem.utils.MyTime;
import com.oobiliuoo.ticketingsystem.utils.ScreenSizeUtils;

import java.util.List;

/**
 * @author biliu
 */
public class ScenicSpotAdapter extends ArrayAdapter<ScenicSpot> {

    private int resourceId;

    public ScenicSpotAdapter(@NonNull Context context, int resource, @NonNull List<ScenicSpot> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ScenicSpot scenicSpot = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.iv1 = (ImageView) view.findViewById(R.id.hll_iv);
            viewHolder.name = (TextView) view.findViewById(R.id.hll_tv_name);
            viewHolder.level = (TextView) view.findViewById(R.id.hll_tv_level);
            viewHolder.openTime = (TextView) view.findViewById(R.id.hll_tv_time);
            viewHolder.price = (TextView) view.findViewById(R.id.hll_tv_money);

            viewHolder.btnBuy = (Button) view.findViewById(R.id.hll_btn_buy);
            viewHolder.btnLook = (Button) view.findViewById(R.id.hll_btn_look);

            viewHolder.dialogBuy = new Dialog(getContext(), R.style.NormalDialogStyle);
            viewHolder.dialogLook = new Dialog(getContext(), R.style.NormalDialogStyle);

            viewHolder.scenicSpot = scenicSpot;


            view.setTag(viewHolder);

        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.iv1.setImageResource(scenicSpot.getPicId());
        viewHolder.name.setText(scenicSpot.getName());
        viewHolder.level.setText(scenicSpot.getLevel());
        viewHolder.openTime.setText(scenicSpot.getOpenTime());
        viewHolder.price.setText(scenicSpot.getPrice());


        viewHolder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 购票
                // 打开对话框
                View view1 = View.inflate(getContext(), R.layout.home_buy_dialog, null);
                // 初始化对话框数据
                initDialog(viewHolder,view1);
                showBuyDialog(viewHolder.dialogBuy, view1);
            }
        });

        viewHolder.btnLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view1 = View.inflate(getContext(), R.layout.home_look_dialog, null);
                // 初始化对话框数据

                ImageView lookIv1;
                TextView lookTv1;
                Button btnBack;

                lookIv1 = (ImageView) view1.findViewById(R.id.hld_iv);
                lookTv1 = (TextView) view1.findViewById(R.id.hld_tv);
                btnBack = (Button) view1.findViewById(R.id.hld_btn);

                lookIv1.setImageResource(scenicSpot.getPicId());
                lookTv1.setText(scenicSpot.getIntro());
                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolder.dialogLook.dismiss();
                    }
                });

                showBuyDialog(viewHolder.dialogLook, view1);
            }
        });



        return view;
    }


    private void showBuyDialog(Dialog dialog, View view){


        dialog.setContentView(view);
        //使得点击对话框外部不消失对话框
        dialog.setCanceledOnTouchOutside(true);
        //设置对话框的大小
        view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(getContext()).getScreenHeight() * 0.23f));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(getContext()).getScreenWidth() * 0.90f);
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        //lp.height = (int) (ScreenSizeUtils.getInstance(getContext()).getScreenHeight() * 0.70f);
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    private void initDialog(ViewHolder viewHolder,View view) {
        ImageView orderIv1;
        TextView orderTvName;
        TextView orderTvlevel;
        TextView orderTvOpenTime;
        TextView orderTvPrice;
        TextView orderTvOrderID;
        EditText orderEtWorkTime;
        Spinner orderSpOrderType;
        EditText orderEtOrderNum;
        TextView orderTvSumPrice;
        Button orderBtnOk;
        Button orderBtnCancel;

        orderIv1 = view.findViewById(R.id.hbd_iv);
        orderTvName = view.findViewById(R.id.hbd_tv_name);
        orderTvlevel = view.findViewById(R.id.hbd_tv_level);
        orderTvOpenTime = view.findViewById(R.id.hbd_tv_time);
        orderTvPrice = view.findViewById(R.id.hbd_tv_price);
        orderTvOrderID = view.findViewById(R.id.hbd_tv_orderID);
        orderEtWorkTime = view.findViewById(R.id.hbd_et_workTime);
        orderSpOrderType = view.findViewById(R.id.hbd_sp_orderType);
        orderEtOrderNum = view.findViewById(R.id.hbd_et_orderNum);
        orderTvSumPrice = view.findViewById(R.id.hbd_tv_sumPrice);
        orderBtnOk = view.findViewById(R.id.hbd_btn_ok);
        orderBtnCancel = view.findViewById(R.id.bhd_btn_cancel);

        ScenicSpot scenicSpot = viewHolder.scenicSpot;

        orderIv1.setImageResource(scenicSpot.getPicId());
        orderTvName.setText(scenicSpot.getName());
        orderTvlevel.setText(scenicSpot.getLevel());
        orderTvOpenTime.setText(scenicSpot.getOpenTime());
        orderTvPrice.setText(scenicSpot.getPrice());
        orderTvSumPrice.setText(scenicSpot.getPrice());

        // 读取本地帐号
        String tel = LBUtils.readCurrentUser(getContext());
        // 获取当前时间
        MyTime myTime = new MyTime();
        if(tel!=""){
            String id = myTime.getXxTime() + tel.substring(7, 11);
            orderTvOrderID.setText(id);
        }
        orderEtWorkTime.setText(myTime.getYMDTime());

        orderEtOrderNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int num = Integer.parseInt(v.getText().toString());
                if(num<=0){
                    num = 1;
                }
                if(TextUtils.isEmpty(v.getText().toString())){
                    String text = "1";
                    orderEtOrderNum.setText(text);
                    return false;
                }
                int price = Integer.parseInt(orderTvPrice.getText().toString());
                int sum = num * price;
                orderTvSumPrice.setText(String.valueOf(sum));
                return false;
            }
        });

        orderBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tel!=""){
                    OrderInfo orderInfo = new OrderInfo();
                    orderInfo.setScenicSpot(scenicSpot);
                    orderInfo.setScenicName(orderTvName.getText().toString());
                    orderInfo.setOderID(orderTvOrderID.getText().toString());
                    orderInfo.setWorkTime(orderEtWorkTime.getText().toString());
                    orderInfo.setOrderType(orderSpOrderType.getSelectedItem().toString());
                    orderInfo.setOrderNumber(Integer.parseInt(orderEtOrderNum.getText().toString()));
                    orderInfo.setSumPrice(Integer.parseInt(orderTvSumPrice.getText().toString()));
                    orderInfo.setOrderTime(myTime.getTime());
                    orderInfo.setTel(tel);
                    // TODO 保存订单
                    orderInfo.save();

                    LBUtils.showToast(getContext(),"购买成功");
                    viewHolder.dialogBuy.dismiss();
                }else {
                    LBUtils.showToast(getContext(),"请先登录");
                }


            }
        });

        orderBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LBUtils.showToast(getContext(),"取消购票");
                viewHolder.dialogBuy.dismiss();
            }
        });

    }

    class ViewHolder {

        TextView name;
        TextView level;
        TextView openTime;
        ImageView iv1;
        TextView price;
        Button btnBuy;
        Button btnLook;
        Dialog dialogBuy;
        Dialog dialogLook;
        ScenicSpot scenicSpot;

    }
}

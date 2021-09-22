package com.oobiliuoo.ticketingsystem.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.oobiliuoo.ticketingsystem.R;
import com.oobiliuoo.ticketingsystem.data.ScenicSpot;
import com.oobiliuoo.ticketingsystem.utils.ScreenSizeUtils;

import java.util.List;

/**
 * @author biliu
 */
public class ScenicSpotAdapter extends ArrayAdapter<ScenicSpot> {

    private ScenicSpot scenicSpot;
    private int resourceId;
    private Dialog dialog;

    public ScenicSpotAdapter(@NonNull Context context, int resource, @NonNull List<ScenicSpot> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        scenicSpot = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.iv1 = (ImageView) view.findViewById(R.id.hll_iv);
            viewHolder.name = (TextView) view.findViewById(R.id.hll_tv_name);
            viewHolder.level = (TextView) view.findViewById(R.id.hll_tv_level);
            viewHolder.openTime = (TextView) view.findViewById(R.id.hll_tv_time);
            viewHolder.btnBuy = (Button) view.findViewById(R.id.hll_btn_buy);
            viewHolder.btnLook = (Button) view.findViewById(R.id.hll_btn_look);
            view.setTag(viewHolder);

        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.iv1.setImageResource(scenicSpot.getPicId());
        viewHolder.name.setText(scenicSpot.getName());
        viewHolder.level.setText(scenicSpot.getLevel());
        viewHolder.openTime.setText(scenicSpot.getOpenTime());

        viewHolder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 购票
                // 打开对话框
               showBuyDialog();

            }
        });


        return view;
    }


    private void showBuyDialog(){
        dialog = new Dialog(getContext(), R.style.NormalDialogStyle);
        View view = View.inflate(getContext(), R.layout.home_buy_dialog, null);

        // 初始化对话框数据
        initDialog(view);

        dialog.setContentView(view);
        //使得点击对话框外部不消失对话框
        dialog.setCanceledOnTouchOutside(true);
        //设置对话框的大小
        view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(getContext()).getScreenHeight() * 0.23f));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(getContext()).getScreenWidth() * 0.90f);
        lp.height = (int) (ScreenSizeUtils.getInstance(getContext()).getScreenHeight() * 0.70f);
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    private void initDialog(View view) {
        ImageView orderIv1;
        
        orderIv1 = view.findViewById(R.id.hbd_iv);
        orderIv1.setImageResource(scenicSpot.getPicId());


    }

    class ViewHolder {

        TextView name;
        TextView level;
        TextView openTime;
        ImageView iv1;
        Button btnBuy;
        Button btnLook;

    }
}

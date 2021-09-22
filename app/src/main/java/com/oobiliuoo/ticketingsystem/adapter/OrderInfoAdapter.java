package com.oobiliuoo.ticketingsystem.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.oobiliuoo.ticketingsystem.R;
import com.oobiliuoo.ticketingsystem.data.OrderInfo;
import com.oobiliuoo.ticketingsystem.ui.TicketQRCodeActivity;

import java.util.List;

/**
 * @author biliu
 */
public class OrderInfoAdapter extends ArrayAdapter<OrderInfo> {


    private int resourceId;
    public OrderInfoAdapter(@NonNull Context context, int resource, @NonNull List<OrderInfo> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       OrderInfo orderInfo = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.ll = view.findViewById(R.id.mll_ll_order);
            viewHolder.orderID = view.findViewById(R.id.mll_tv_orderID);
            viewHolder.orderStatue = view.findViewById(R.id.mll_tv_orderStatue);
            viewHolder.orderScenicName = view.findViewById(R.id.mll_tv_ScenicName);
            viewHolder.orderNum = view.findViewById(R.id.mll_tv_orderNum);
            viewHolder.orderWorkTime = view.findViewById(R.id.mll_tv_orderWorkTime);

            view.setTag(viewHolder);

        }else {
            view = convertView;
            viewHolder =  (ViewHolder) view.getTag();
        }

        viewHolder.orderID.setText(orderInfo.getOderID());
        viewHolder.orderStatue.setText(orderInfo.getOrderStatue());
        viewHolder.orderScenicName.setText(orderInfo.getScenicName());
        viewHolder.orderNum.setText(orderInfo.getOrderNumber().toString());
        viewHolder.orderWorkTime.setText(orderInfo.getWorkTime());
        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TicketQRCodeActivity.class);
                OrderInfo info = orderInfo;
                Bundle bundle = new Bundle();
                bundle.putSerializable("orderInfo",info);
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });

        return view;

    }

    class ViewHolder {

        LinearLayout ll;
        TextView orderID;
        TextView orderStatue;
        TextView orderScenicName;
        TextView orderNum;
        TextView orderWorkTime;

    }
}

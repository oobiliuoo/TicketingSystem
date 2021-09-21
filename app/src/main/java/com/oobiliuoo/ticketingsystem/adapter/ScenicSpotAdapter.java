package com.oobiliuoo.ticketingsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.oobiliuoo.ticketingsystem.R;
import com.oobiliuoo.ticketingsystem.data.ScenicSpot;

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



        return view;
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

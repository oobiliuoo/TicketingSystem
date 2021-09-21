package com.oobiliuoo.ticketingsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.oobiliuoo.ticketingsystem.R;
import com.oobiliuoo.ticketingsystem.data.NotifyText;

import java.util.List;

/**
 * @author biliu
 */
public class NotifyTextAdapter extends ArrayAdapter<NotifyText> {

    private int resourceId;

    public NotifyTextAdapter(@NonNull Context context, int resource, @NonNull List<NotifyText> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NotifyText notifyText = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.nll_title);
            viewHolder.time = (TextView) view.findViewById(R.id.nll_time);
            view.setTag(viewHolder);

        }else {
            view = convertView;
            viewHolder =  (ViewHolder) view.getTag();
        }

        viewHolder.title.setText(notifyText.getTitle());
        viewHolder.time.setText(notifyText.getTime());

        return view;
    }


    class ViewHolder {

        TextView title;
        TextView time;
        TextView text;

    }

}

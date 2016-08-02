package com.travel.liuyun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.travel.liuyun.R;
import com.travel.liuyun.bean.DialogButtonItem;
import java.util.ArrayList;

/**
 * Created by tan_ on 14-10-28.
 */
public class DialogMoreButtonsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DialogButtonItem> items = new ArrayList<DialogButtonItem>();

    public DialogMoreButtonsAdapter(Context context, ArrayList<DialogButtonItem> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (null == view) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dialog_more_buttons_item, null);
            holder = new Holder();
            holder.item = (TextView) view.findViewById(R.id.button_item);
            view.setTag(holder);
        } else holder = (Holder) view.getTag();
        final DialogButtonItem item = items.get(i);
        holder.item.setText(0 == item.getRid() ? item.getTitle() : context.getString(item.getRid()));
        holder.item.setTextColor(context.getResources().getColor(item.getItemColor()));
        if (item.isShowBg()) holder.item.setBackgroundResource(item.getButtonBg());
        else holder.item.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        return view;
    }

    private class Holder {
        TextView item;
    }
}

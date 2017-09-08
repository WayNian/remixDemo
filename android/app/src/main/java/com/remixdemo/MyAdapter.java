package com.remixdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by waynian on 2017/9/8.
 */

public class MyAdapter extends BaseAdapter {
    Context context;
    List<String> dataSource;

    public MyAdapter(Context context, List<String> dataSource) {
        this.context = context;
        this.dataSource = dataSource;
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public String getItem(int i) {
        return dataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_item, null);
        }
        TextView tvDesc = (TextView) view.findViewById(R.id.tv_desc);
        tvDesc.setText(getItem(i));
        return view;
    }
}

package com.itech.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itech.models.DrawerListViewItem;
import com.itech.mybabygrowing.R;

import java.util.List;

/**
 * Created by oSunshine on 24/06/2015.
 */
public class DrawerListViewItemAdapter extends BaseAdapter {

    private List<DrawerListViewItem> data;
    private Context context;

    public DrawerListViewItemAdapter(Context context, List<DrawerListViewItem> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View drawerListViewItem = null;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            drawerListViewItem = layoutInflater.inflate(R.layout.drawer_list_view_item, parent, false);
        } else {

            drawerListViewItem = convertView;
        }

        Log.v("dkholt", "DrawerListViewnItemAdapter-getView");

        ((ImageView)drawerListViewItem.findViewById(R.id.list_icon)).setImageResource(data.get(position).getIconId());
        ((TextView)drawerListViewItem.findViewById(R.id.list_text)).setText(data.get(position).getTitle());
        Log.v("dkholt", "DrawerListViewnItemAdapter-getViewAfter");



        return drawerListViewItem;
    }
}

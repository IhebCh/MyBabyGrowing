package com.itech.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itech.models.DrawerListViewItem;
import com.itech.mybabygrowing.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by oSunshine on 24/06/2015.
 */

public class RecyclerViewItemAdapter extends RecyclerView.Adapter<RecyclerViewItemAdapter.RecyclerViewItemHolder> {

    private LayoutInflater inflater;
    List<DrawerListViewItem> data = Collections.emptyList();

    public RecyclerViewItemHolder getRecyclerViewItemHolder() {
        return recyclerViewItemHolder;
    }

    private RecyclerViewItemHolder recyclerViewItemHolder;

    public RecyclerViewItemAdapter(Context context, List<DrawerListViewItem> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.drawer_list_view_item, parent, false);
        recyclerViewItemHolder = new RecyclerViewItemHolder(view);
        return recyclerViewItemHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewItemHolder holder, int position) {
        DrawerListViewItem drawerListViewItem = data.get(position);
        holder.text.setText(drawerListViewItem.getTitle());
        holder.image.setImageResource(drawerListViewItem.getIconId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecyclerViewItemHolder extends RecyclerView.ViewHolder  {

        TextView text;
        ImageView image;

        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.list_text);
            image = (ImageView) itemView.findViewById(R.id.list_icon);

        }


    }

    private void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }
}

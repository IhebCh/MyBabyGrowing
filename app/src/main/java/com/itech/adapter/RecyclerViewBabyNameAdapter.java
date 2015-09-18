package com.itech.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.itech.models.BabyName;
import com.itech.mybabygrowing.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oSunshine on 08/08/2015.
 */
public class RecyclerViewBabyNameAdapter extends RecyclerView.Adapter<RecyclerViewBabyNameAdapter.RecyclerViewBabyNameHolder> {


    private List<BabyName> boysNames;
    private List<BabyName> girlsNames;
    private List<BabyName> selectedList;
    private final LayoutInflater inflater;


    public RecyclerViewBabyNameAdapter(Context context, List<BabyName> boysNames, List<BabyName> girlsNames) {
        this.girlsNames = new ArrayList<>(girlsNames);
        this.boysNames = new ArrayList<>(boysNames);
        selectedList = boysNames;
        inflater = LayoutInflater.from(context);

    }

    public List<BabyName> getBoysNames() {
        return boysNames;
    }

    @Override
    public RecyclerViewBabyNameHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.babyname_item, parent, false);

        RecyclerViewBabyNameHolder recyclerViewBabyNameHolder = new RecyclerViewBabyNameAdapter.RecyclerViewBabyNameHolder(view);

        return recyclerViewBabyNameHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewBabyNameHolder holder, final int position) {

        BabyName babyName = selectedList.get(position);

        holder.getName().setText(babyName.getName());

        holder.getCheckBox().setSelected(babyName.isChecked());

        holder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                notifyDataSetChanged();

                selectedList.get(position).setChecked(isChecked);

                Log.v("babyy",selectedList.get(position).getName()+"  " +selectedList.get(position).isChecked());

            }
        });

    }

    @Override
    public int getItemCount() {
        return selectedList == null ? 0 : selectedList.size();
    }

    public class RecyclerViewBabyNameHolder extends RecyclerView.ViewHolder {

        TextView name;
        CheckBox checkBox;

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

        public RecyclerViewBabyNameHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);

        }
    }

    public void changeList(boolean selectBoys) {

        selectedList=null;
        notifyDataSetChanged();


        if (selectBoys)
            selectedList = boysNames;
        else selectedList = girlsNames;

        notifyDataSetChanged();
    }

}
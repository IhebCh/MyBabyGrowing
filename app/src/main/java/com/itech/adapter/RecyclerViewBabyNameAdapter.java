package com.itech.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.itech.models.BabyName;
import com.itech.mybabygrowing.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oSunshine on 08/08/2015.
 */
public class RecyclerViewBabyNameAdapter extends RecyclerView.Adapter<RecyclerViewBabyNameAdapter.RecyclerViewBabyNameHolder>{



    private  List<BabyName> data;
    private final LayoutInflater inflater;


    public RecyclerViewBabyNameAdapter(Context context,List<BabyName> data) {
        this.data = new ArrayList<>(data);
        inflater = LayoutInflater.from(context);

    }

    public List<BabyName> getData() {
        return data;
    }

    @Override
    public RecyclerViewBabyNameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.babyname_item, parent, false);

        RecyclerViewBabyNameHolder recyclerViewBabyNameHolder= new RecyclerViewBabyNameAdapter.RecyclerViewBabyNameHolder(view);

        return recyclerViewBabyNameHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewBabyNameHolder holder, int position) {

        BabyName babyName = data.get(position);
        holder.getName().setText(babyName.getName());
        holder.getCheckBox().setSelected(babyName.isChecked());
    }

    @Override
    public int getItemCount() {
        return  data == null ? 0 : data.size();
    }

    public class RecyclerViewBabyNameHolder  extends RecyclerView.ViewHolder{

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

    public void changeList(ArrayList<BabyName> list){
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

}
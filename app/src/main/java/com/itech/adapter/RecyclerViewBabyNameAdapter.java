package com.itech.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.itech.DataBasesHandlers.DataBaseSQLiteHandler;
import com.itech.models.BabyName;
import com.itech.mybabygrowing.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oSunshine on 08/08/2015.
 */
public class RecyclerViewBabyNameAdapter extends BaseAdapter {


    private  Context context;
    private List<BabyName> boysNames;
    private List<BabyName> girlsNames;
    private List<BabyName> selectedList;
    private final LayoutInflater inflater;

    public RecyclerViewBabyNameAdapter(Context context, List<BabyName> boysNames, List<BabyName> girlsNames) {
        this.girlsNames = new ArrayList<>(girlsNames);
        this.boysNames = new ArrayList<>(boysNames);
        selectedList = boysNames;
        this.context=context ;
        inflater = LayoutInflater.from(context);

    }

    public List<BabyName> getBoysNames() {
        return boysNames;
    }


    public void changeList(boolean selectBoys) {

        selectedList=null;

        if (selectBoys)
            selectedList = boysNames;
        else selectedList = girlsNames;

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return selectedList.size();
    }

    @Override
    public Object getItem(int position) {
        return selectedList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View nameViewItem  = null;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            nameViewItem = layoutInflater.inflate(R.layout.babyname_item, parent, false);
        } else {

            nameViewItem = convertView;
        }

        Log.v("dkholt", "DrawerListViewnItemAdapter-getView");

        CheckBox checkBox = ((CheckBox)nameViewItem.findViewById(R.id.checkbox));
                ((TextView) nameViewItem.findViewById(R.id.name)).setText(selectedList.get(position).getName());
                 checkBox.setChecked(selectedList.get(position).isChecked());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectedList.get(position).setChecked(isChecked);
                DataBaseSQLiteHandler dbh =new DataBaseSQLiteHandler(context);


                if(isChecked==true){
                   // String strSQL = "UPDATE babyname SET checked =1 WHERE id = "+position;
                    String strSQL = "UPDATE babyname SET checked =1 WHERE nombebe like '"+selectedList.get(position).getName()+"'";
                    dbh.getWritableDatabase().execSQL(strSQL);
                    Log.d("checked 1 ", ((Integer) position).toString()) ;

                }
                else{
                    String strSQL = "UPDATE babyname SET checked =0 WHERE nombebe like '"+selectedList.get(position).getName()+"'";

                    dbh.getWritableDatabase().execSQL(strSQL);
                    Log.d("checked 0 ", ((Integer) position).toString()) ;
                }


            }
        });

        Log.v("dkholt", "DrawerListViewnItemAdapter-getViewAfter");



        return nameViewItem;
    }
    }

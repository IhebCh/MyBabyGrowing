package com.itech.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itech.mybabygrowing.AppointementsFragment;
import com.itech.mybabygrowing.MeFragment;
import com.itech.mybabygrowing.MotherGuideFragment;
import com.itech.mybabygrowing.MotherWeightFragment;
import com.itech.mybabygrowing.R;
import com.itech.mybabygrowing.ToDoFragment;
import com.itech.tab.PagerSlidingTabStrip;

/**
 * Created by oSunshine on 24/06/2015.
 */

public class MePagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider, MeFragment.TabChangeTitleListener {

    private Context context;
    private int[] iconsId = {R.drawable.guide_icon, R.drawable.myweight_icon, R.drawable.appointment_icon, R.drawable.todo_icon};
    private String[] titres = {"Guide", "Poids", "Rendez vous", "Tâches à faire"};

    MotherGuideFragment motherGuideFragment = new MotherGuideFragment();
    MotherWeightFragment motherWeightFragment = new MotherWeightFragment();
    AppointementsFragment appointementsFragment = new AppointementsFragment();
    ToDoFragment toDoFragment = new ToDoFragment();

    public MePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                return motherGuideFragment;
            case 1:
                return motherWeightFragment;
            case 2:
                return appointementsFragment;
            case 3:
                return toDoFragment;

        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }


    @Override
    public int getPageIconResId(int position) {
        return iconsId[position];
    }

    @Override
    public String changeSubtitle(int position) {
        return titres[position];
    }
}

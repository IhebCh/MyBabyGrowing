package com.itech.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itech.mybabygrowing.BabyEvolutionFragment;
import com.itech.mybabygrowing.BabyFragment;
import com.itech.mybabygrowing.BabyNamesFragment;
import com.itech.mybabygrowing.R;
import com.itech.tab.PagerSlidingTabStrip;

/**
 * Created by oSunshine on 27/06/2015.
 */
public class BabyPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider, BabyFragment.TabChangeTitleListener {

    private Context context;
    private int[] iconsId = {R.drawable.babynames_icon_new, R.drawable.weekly_icon};
    private String[] titres = {"Nom de bébé", "Evolution du bébé"};
    BabyNamesFragment babyNamesFragment = new BabyNamesFragment() ;
    BabyEvolutionFragment babyEvolutionFragment = new BabyEvolutionFragment();
    public BabyPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                return babyNamesFragment;

            case 1:
                return babyEvolutionFragment;

        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

   /*@Override
    public CharSequence getPageTitle(int position) {
        Drawable drawable = context.getResources().getDrawable(R.drawable.guide_icon_info_grey);
        drawable.setBounds(0,0,36,36);
        ImageSpan imageSpan = new ImageSpan(drawable);
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan, 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }
*/

    /*@Override
    public CharSequence getPageTitle(int position) {

        return " Tab   " + position;
    }
*/
    @Override
    public int getPageIconResId(int position) {
        return iconsId[position];
    }

    @Override
    public String changeSubtitle(int position) {
        return titres[position];
    }}

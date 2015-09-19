package com.itech.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itech.mybabygrowing.R;

/**
 * Created by oSunshine on 19/09/2015.
 */
public class BabyEvolutionPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;

    int[] mResources = {
            R.drawable.week1_small,
            R.drawable.week2_small,
            R.drawable.week3_small,
            R.drawable.week4_small,
            R.drawable.week5_small,
            R.drawable.week6_small,
            R.drawable.week7_small,
            R.drawable.week8_small,
            R.drawable.week9_small,
            R.drawable.week10_small,
            R.drawable.week11_small,
            R.drawable.week12_small,
            R.drawable.week13_small,
            R.drawable.week14_small,
            R.drawable.week15_small,
            R.drawable.week16_small,
            R.drawable.week17_small,
            R.drawable.week18_small,
            R.drawable.week19_small,
            R.drawable.week20_small,
            R.drawable.week21_small,
            R.drawable.week22_small,
            R.drawable.week23_small,
            R.drawable.week24_small,
            R.drawable.week25_small,
            R.drawable.week26_small,
            R.drawable.week27_small,
            R.drawable.week28_small,
            R.drawable.week29_small,
            R.drawable.week30_small,
            R.drawable.week31_small,
            R.drawable.week32_small,
            R.drawable.week33_small,
            R.drawable.week34_small,
            R.drawable.week35_small,
            R.drawable.week36_small,
            R.drawable.week37_small,
            R.drawable.week38_small,
            R.drawable.week39_small
    };
    public BabyEvolutionPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.baby_evolution_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.image);

        imageView.setImageResource(mResources[position]);

        TextView textView = (TextView)itemView.findViewById(R.id.text_semaine);

        textView.setText("Semaine "+(position+1));

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}

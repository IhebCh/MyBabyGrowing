package com.itech.tab;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by oSunshine on 26/06/2015.
 */
public class Utils {
    public static int dpToPx(Resources resources, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }
}
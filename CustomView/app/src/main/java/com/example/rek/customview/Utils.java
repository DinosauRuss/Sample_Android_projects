package com.example.rek.customview;

import android.content.res.Resources;


public class Utils {

    public static final String TAG = "something";

    public static int dpToPx(int dp) {
        return Math.round(dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int spToPx(int num) {
        return Math.round(num * Resources.getSystem().getDisplayMetrics().scaledDensity);
    }

    public static float pxToSp(float px) {
        return px / Resources.getSystem().getDisplayMetrics().scaledDensity;
    }
}

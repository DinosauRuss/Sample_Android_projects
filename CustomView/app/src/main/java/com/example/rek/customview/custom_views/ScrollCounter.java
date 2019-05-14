package com.example.rek.customview.custom_views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;

import com.example.rek.customview.Utils;


public class ScrollCounter extends LinearLayoutCompat {

    private static int MAX_COUNT = 9999;
    private static int MIN_COUNT = 0;

    private DigitScroller onesScroller;
    private DigitScroller tensScroller;
    private DigitScroller hundredsScroller;
    private DigitScroller thousandsScroller;

    private int currentCount;

    public ScrollCounter(Context context) {
        super(context);
        init(context, null);
    }

    public ScrollCounter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_HORIZONTAL);

        thousandsScroller = new DigitScroller(context, attrs);
        hundredsScroller = new DigitScroller(context, attrs);
        tensScroller = new DigitScroller(context, attrs);
        onesScroller = new DigitScroller(context, attrs);

        addView(thousandsScroller);
        addView(hundredsScroller);
        addView(tensScroller);
        addView(onesScroller);

        onesScroller.getLayoutParams().width = Utils.dpToPx(10);
        onesScroller.getLayoutParams().height = Utils.dpToPx(10);
    }

    public void setAnimCount(int count) {
        currentCount = Utils.constrainNumber(count, MIN_COUNT, MAX_COUNT);

        int thousands = (int) Math.floor(currentCount/1000f) % 10;
        int hundreds = (int) Math.floor(currentCount/100f) % 10;
        int tens = (int) Math.floor(currentCount/10f) % 10;
        int ones = currentCount % 10;

        thousandsScroller.setDigitAnim(thousands);
        hundredsScroller.setDigitAnim(hundreds);
        tensScroller.setDigitAnim(tens);
        onesScroller.setDigitAnim(ones);
    }

    public void setCount(int count) {
        currentCount = Utils.constrainNumber(count, MIN_COUNT, MAX_COUNT);

        int thousands = (int) Math.floor(currentCount/1000f) % 10;
        int hundreds = (int) Math.floor(currentCount/100f) % 10;
        int tens = (int) Math.floor(currentCount/10f) % 10;
        int ones = currentCount % 10;

        thousandsScroller.setDigit(thousands);
        hundredsScroller.setDigit(hundreds);
        tensScroller.setDigit(tens);
        onesScroller.setDigit(ones);
    }

    public void increment() {
        setAnimCount(++currentCount);
    }

}

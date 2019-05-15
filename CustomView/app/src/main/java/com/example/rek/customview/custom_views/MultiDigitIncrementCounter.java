package com.example.rek.customview.custom_views;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;

import com.example.rek.customview.R;
import com.example.rek.customview.Utils;


public class MultiDigitIncrementCounter extends LinearLayoutCompat {

    private static int MAX_COUNT = 9999;
    private static int MIN_COUNT = 0;

    private DigitIncrementCounter onesScroller;
    private DigitIncrementCounter tensScroller;
    private DigitIncrementCounter hundredsScroller;
    private DigitIncrementCounter thousandsScroller;

    private int currentCount;
    private boolean currentlyAnimating = false;

    public MultiDigitIncrementCounter(Context context) {
        super(context);
        init(context, null);
    }

    public MultiDigitIncrementCounter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_HORIZONTAL);

        thousandsScroller = new DigitIncrementCounter(context, attrs);
        hundredsScroller = new DigitIncrementCounter(context, attrs);
        tensScroller = new DigitIncrementCounter(context, attrs);
        onesScroller = new DigitIncrementCounter(context, attrs);

        addView(thousandsScroller);
        addView(hundredsScroller);
        addView(tensScroller);
        addView(onesScroller);
    }

    public void setAnimCount(int count) {
        if (!currentlyAnimating) {
            currentCount = Utils.constrainNumber(count, MIN_COUNT, MAX_COUNT);

            int thousands = (int) Math.floor(currentCount / 1000f) % 10;
            int hundreds = (int) Math.floor(currentCount / 100f) % 10;
            int tens = (int) Math.floor(currentCount / 10f) % 10;
            int ones = currentCount % 10;

            thousandsScroller.setDigitAnim(thousands);
            hundredsScroller.setDigitAnim(hundreds);
            tensScroller.setDigitAnim(tens);
            onesScroller.setDigitAnim(ones);
        }
    }

    public void setCount(int count) {
        if (!currentlyAnimating) {
            currentCount = Utils.constrainNumber(count, MIN_COUNT, MAX_COUNT);

            int thousands = (int) Math.floor(currentCount / 1000f) % 10;
            int hundreds = (int) Math.floor(currentCount / 100f) % 10;
            int tens = (int) Math.floor(currentCount / 10f) % 10;
            int ones = currentCount % 10;

            thousandsScroller.setDigit(thousands);
            hundredsScroller.setDigit(hundreds);
            tensScroller.setDigit(tens);
            onesScroller.setDigit(ones);
        }
    }

    public void increment() {
        if (!currentlyAnimating) {
            setAnimCount(++currentCount);
            startTimer();
        }
    }

    private void startTimer() {
        currentlyAnimating = true;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentlyAnimating = false;
            }
        }, getResources().getInteger(R.integer.single_digit_animation_ms));
    }

}

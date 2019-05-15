package com.example.rek.customview.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.Animatable2Compat;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.util.AttributeSet;

import com.example.rek.customview.R;
import com.example.rek.customview.Utils;


public class DigitIncrementCounter extends android.support.v7.widget.AppCompatImageView {

    private static final int MAX_DIGIT = 9;
    private static final int MIN_DIGIT = 0;

    private int currentCount;
    private int maxCount;
    private int[] mDrawables;

    private boolean animationRunning = false;

    public DigitIncrementCounter(Context context) {
        super(context);
        init(context, null);
    }

    public DigitIncrementCounter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        // Load avd references into local array
        TypedArray ta = getResources().obtainTypedArray(R.array.digit_avd);
        int length = ta.length();
        mDrawables = new int[length];
        for (int i = 0; i < length; i++) {
            mDrawables[i] = ta.getResourceId(i, R.drawable.ic_reset);
        }
        maxCount = length;
        ta.recycle();

        readAttrs(context, attrs);
        setDrawableFromCount();
    }

    private void readAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.DigitIncrementCounter);
            currentCount = attributes.getInt(R.styleable.DigitIncrementCounter_startingDigit, MIN_DIGIT);

            attributes.recycle();
        }
    }

    private void animateCount() {
        // Animate digit
        Drawable drawable = getDrawable();
        if (drawable instanceof AnimatedVectorDrawableCompat) {

            ((AnimatedVectorDrawableCompat) drawable).registerAnimationCallback(
                    new Animatable2Compat.AnimationCallback() {
                        @Override
                        public void onAnimationEnd(Drawable drawable) {
                            setDrawableFromCount();
                            animationRunning = false;
                        }
                    });

            animationRunning = true;
            ((AnimatedVectorDrawableCompat) drawable).start();
        }
    }

    private void setDrawableFromCount() {
        AnimatedVectorDrawableCompat avd = AnimatedVectorDrawableCompat.create(
                getContext(), mDrawables[currentCount]);
        setImageDrawable(avd);
    }

    public void increment() {
        if (!animationRunning) {
            currentCount = ++currentCount % maxCount;
            animateCount();
        }
    }

    public void setDigitAnim(int digit) {
        if (!animationRunning) {
            int newDigit = Utils.constrainNumber(digit, MIN_DIGIT, MAX_DIGIT);

            if ((newDigit - currentCount == 1 || newDigit - currentCount == -9)) {
                increment();
            } else {
                currentCount = newDigit;
                setDrawableFromCount();
            }
        }
    }

    public void setDigit(int newDigit) {
        if (!animationRunning) {
            currentCount = newDigit;
            setDrawableFromCount();
        }
    }

}

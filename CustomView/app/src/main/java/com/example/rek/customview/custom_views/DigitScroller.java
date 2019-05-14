package com.example.rek.customview.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.Animatable2Compat;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.util.AttributeSet;

import com.example.rek.customview.R;


public class DigitScroller extends android.support.v7.widget.AppCompatImageView {

    private int currentCount;
    private int maxCount;
    private int[] mDrawables;

    private boolean animationRunning = false;

    public DigitScroller(Context context) {
        super(context);
        init();
    }

    public DigitScroller(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        TypedArray ta = getResources().obtainTypedArray(R.array.digit_avd);
        int length = ta.length();
        mDrawables = new int[length];
        for (int i = 0; i < length; i++) {
            mDrawables[i] = ta.getResourceId(i, R.drawable.ic_reset);
        }
        maxCount = length;
        ta.recycle();

        AnimatedVectorDrawableCompat initialAvd = AnimatedVectorDrawableCompat.create(
                getContext(), mDrawables[0]);
        setImageDrawable(initialAvd);
    }

    private void animateCount() {
        // Animate digit
        Drawable drawable = getDrawable();
        if (drawable instanceof AnimatedVectorDrawableCompat) {

            ((AnimatedVectorDrawableCompat) drawable).registerAnimationCallback(
                    new Animatable2Compat.AnimationCallback() {
                        @Override
                        public void onAnimationEnd(Drawable drawable) {
                            // Set drawable to new digit, waiting for animation
//                            AnimatedVectorDrawableCompat avd = AnimatedVectorDrawableCompat.create(
//                                    getContext(), mDrawables[currentCount]);
//                            setImageDrawable(avd);
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

    public void setDigit(int digit) {
        currentCount = digit;
        setDrawableFromCount();

    }
}

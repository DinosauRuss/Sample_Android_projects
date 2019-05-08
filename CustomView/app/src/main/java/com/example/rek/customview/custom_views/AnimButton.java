package com.example.rek.customview.custom_views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;

public class AnimButton extends android.support.v7.widget.AppCompatButton {

    private float initialTextSp;
    private float textScaleMultiplier;

    public AnimButton(Context context) {
        super(context);
        initView(context, null);
    }

    public AnimButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        initialTextSp = getTextSizeSp();
        textScaleMultiplier = 2.0f;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
          switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                scaleTextSize(getTextSizeSp(), (getTextSizeSp() * textScaleMultiplier) );
                return true;
            case MotionEvent.ACTION_UP:
                scaleTextSize(getTextSizeSp(), initialTextSp);

                performClick();
                return true;
        }
        return false;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    private float getTextSizeSp() {
        return getTextSize() / getResources().getDisplayMetrics().density;
    }

    private void scaleTextSize(float initialSize, float finalSize) {
        ObjectAnimator animato = ObjectAnimator.ofFloat(
                this,
                "textSize",
                initialSize,
                finalSize
        );
        animato.setDuration(100);
        animato.setInterpolator(new AccelerateInterpolator());
        animato.start();
    }

}

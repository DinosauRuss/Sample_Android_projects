package com.example.rek.customview.custom_views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.example.rek.customview.R;

public class GreenView extends View{

    private static final int defaultTextSize = 24;
    private static final int defaultStrokeWidth = 3;
    private static final int defaultPadding = 40;
    private static final float labelScaleValue = 1.3f;

    private Paint bgPaint;
    private Paint textPaint;
    private Paint accentPaint;

    private Rect textBounds;
    private Paint.FontMetrics fm;
    private float textSizeSp;
    private float initialTextSizeSp;
    private String label;
    private int defaultPaddingDp;

    private RectF backgroundRect;
    private float cornerRadius;

    public GreenView(Context context) {
        super(context);
        initView(context, null);
    }

    public GreenView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public GreenView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {

        this.setClickable(true);

        // Init paints
        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setColor(ContextCompat.getColor(context, R.color.GREEN));

        accentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        accentPaint.setColor(Color.WHITE);
        accentPaint.setStyle(Paint.Style.STROKE);
        accentPaint.setStrokeWidth(3);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);

        // Init values
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.GreenView);
        if (ta.hasValue(R.styleable.GreenView_label)) {
           setLabel(ta.getString(R.styleable.GreenView_label));
        } else {
            setLabel("");
        }
        float size = ta.getDimension(R.styleable.GreenView_textSize, getLabelSizePx(defaultTextSize));
        setTextSizeSp(size);
        initialTextSizeSp = size;
        defaultPaddingDp = Math.round(defaultPadding * getResources().getDisplayMetrics().density);

        ta.recycle();

        // Objects needed to draw on canvas
        backgroundRect = new RectF();
        cornerRadius = Math.round(12f * getResources().getDisplayMetrics().density);
        fm = textPaint.getFontMetrics();
        textBounds = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // Measure max width of label
        final float maxTextWidth = textPaint.measureText(label);
        // Estimate max height of label
        final float maxTextHeight = fm.bottom - fm.top;

        // Add padding to width
        final int padding = Math.max( (getPaddingStart()+getPaddingEnd()), defaultPaddingDp );
        final int desiredWdith = Math.round((maxTextWidth + padding));
        // Add padding to height
        final int desiredHeight = Math.round(2 * (maxTextHeight + getPaddingTop() + getPaddingBottom()));

        // Reconcile size with constraints from parent view
        final int measuredWidth = resolveSize(desiredWdith, widthMeasureSpec);
        final int measuredHeight = resolveSize(desiredHeight, heightMeasureSpec);

        // Store the final dimension
        setMeasuredDimension(measuredWidth, measuredHeight);

        // Set rects based on final dimensions
        backgroundRect.set(0,0, getMeasuredWidth(), getMeasuredHeight());
        textPaint.getTextBounds(label, 0, label.length(), textBounds);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final float canvasWidth = getWidth();
        final float canvasHeight = getHeight();
        final float centerX = canvasWidth / 2.0f;
        final float centerY = canvasHeight / 2.0f;

        // Draw background
        canvas.drawRoundRect(backgroundRect, cornerRadius, cornerRadius, bgPaint);

        // Draw accent stripe
        final float offset = Math.min(canvasHeight*.1f, canvasWidth*.1f);
        canvas.drawRoundRect(
                offset, offset,
                canvasWidth-offset, canvasHeight-offset,
                cornerRadius/2f, cornerRadius/2f,
                accentPaint);

        // Draw label
        final int textBottom = Math.round(centerY - ((fm.descent + fm.ascent)/2f) );
        canvas.drawText(
                label,
                centerX,
                textBottom,
                textPaint);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String text) {
        this.label = text;
        invalidate();
    }

    public float getTextSizeSp() {
        return textSizeSp;
    }

    public void setTextSizeSp(float size) {
        textSizeSp = size;
        textPaint.setTextSize(size);
        invalidate();
    }

    private float getLabelSizePx(float size) {
        return Math.round(size * getResources().getDisplayMetrics().scaledDensity);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                bgPaint.setColor(getResources().getColor(R.color.DARK_GREEN));
                accentPaint.setStrokeWidth(defaultStrokeWidth * 2);

                ObjectAnimator animato = ObjectAnimator.ofFloat(
                        this,
                        "textSizeSp",
                        textSizeSp, (textSizeSp * labelScaleValue)
                );
                animato.setDuration(100);
                animato.setInterpolator(new LinearInterpolator());
                animato.start();
                return true;

            case MotionEvent.ACTION_UP:
                bgPaint.setColor(getResources().getColor(R.color.GREEN));
                accentPaint.setStrokeWidth(defaultStrokeWidth);

                ObjectAnimator animata = ObjectAnimator.ofFloat(
                        this,
                        "textSizeSp",
                        textSizeSp, initialTextSizeSp
                );
                animata.setDuration(100);
                animata.setInterpolator(new AccelerateInterpolator());
                animata.start();

                performClick();
                return true;
        }
        return false;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}

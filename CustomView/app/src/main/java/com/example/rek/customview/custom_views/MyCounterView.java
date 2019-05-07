package com.example.rek.customview.custom_views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.rek.customview.R;

import java.util.Locale;


/**
 * Custom view which displays a count from 0 - 9999
 */
public class MyCounterView extends View {

    private int currentCount;
    private static final int MAX_COUNT = 9999;
    private static final int  MIN_COUNT = 0;
    private static final String MAX_COUNT_STRING = "9999";
    private String displayedCount;

    private Paint backgroundPaint;
    private Paint underlinePaint;
    private Paint textPaint;
    private RectF backgroundRect;
    private int cornerRadius;


    public MyCounterView(Context context) {
        this(context, null);
    }

    public MyCounterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        // Paints to draw on canvas
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(ContextCompat.getColor(context, R.color.colorAccent));

        underlinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        underlinePaint.setColor(ContextCompat.getColor(context, android.R.color.holo_orange_dark));
        underlinePaint.setStrokeWidth(3f);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(ContextCompat.getColor(context, android.R.color.white));
        textPaint.setTextSize(Math.round(64f * getResources().getDisplayMetrics().scaledDensity));
        textPaint.setTextAlign(Paint.Align.CENTER);

        // Objects needed to draw on canvas
        backgroundRect = new RectF();
        cornerRadius = Math.round(6f * getResources().getDisplayMetrics().density);

        // Set initial seconds to zero
        resetCount();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // Get some dimensions

        final int canvasWidth = getWidth();
        final int canvasHeight = getHeight();
        final float canvasCenterX = canvasWidth * .5f;

        // Draw background
        backgroundRect.set(0f,0f, canvasWidth, canvasHeight);
        canvas.drawRoundRect(backgroundRect, cornerRadius, cornerRadius, backgroundPaint);

        // Draw underline
        final float underlineY = canvasHeight * .6f;
        canvas.drawLine(0, underlineY, canvasWidth, underlineY, underlinePaint);

        // Draw text
//        final float textWidth = textPaint.measureText(displayedCount);
//        final float textStart = Math.round(canvasCenterX - (textWidth * .5f));
//        canvas.drawText(displayedCount, textStart, underlineY-20, textPaint);
        canvas.drawText(displayedCount, canvasCenterX, underlineY-20, textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final Paint.FontMetrics textMetrics = textPaint.getFontMetrics();

        // Measure max width of text
        final float maxTextWidth = textPaint.measureText(MAX_COUNT_STRING);
        // Estimate max height of text
        final float maxTextHeight = textMetrics.bottom - textMetrics.top;

        // Add padding to width
        final int desiredWdith = Math.round(2 * (maxTextWidth + getPaddingLeft() + getPaddingRight()));
        // Add padding to height
        final int desiredHeight = Math.round(2 * (maxTextHeight + getPaddingTop() + getPaddingBottom()));

        // Reconcile size with constraints from parent view
        final int measuredWidth = resolveSize(desiredWdith, widthMeasureSpec);
        final int measuredHeight = resolveSize(desiredHeight, heightMeasureSpec);

        // Store the final dimension
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    public void setCount(int i) {
        // Constrain within 0 - 9999
        currentCount = Math.max( Math.min(i, MAX_COUNT), MIN_COUNT );
        displayedCount = String.format(Locale.getDefault(), "%04d", currentCount);
        // Redraw
        invalidate();
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void resetCount() {
        setCount(0);
    }

    public void incrementCount() {
        setCount(currentCount + 1);
    }

    public void decrementCount() {
        setCount(currentCount -1);
    }

}

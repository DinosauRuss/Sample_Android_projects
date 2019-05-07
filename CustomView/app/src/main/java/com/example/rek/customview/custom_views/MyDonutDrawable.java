package com.example.rek.customview.custom_views;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Custom drawable of a donut
 * Based on 'https://github.com/rharter/mmmmm'
 */
public class MyDonutDrawable extends Drawable {

    private Paint basePaint;
    private Paint icingPaint;
    private Paint sprinklePaint;
    private Path holePath;
    private float scale;
    private List<Sprinkle> sprinkles;
    private float sprinkleRotation;

    private static int[] sprinkleColors = new int[] {
                0xFFFF0000,   // Red
                0xFFFFFFFF,   // White
                0xFFFFFF00,   // Yellow
                0xFF0000FF,   // Blue
                0xFF00FFFF,   // Cyan
                0xFF00FF00,   // Green
                0xFFFF00FF,   // Magenta
                };


    public MyDonutDrawable(int numOfSprinkles) {

        basePaint = new Paint();
        basePaint.setColor(0xFFc6853b);
        basePaint.setStyle(Paint.Style.FILL);
        icingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        icingPaint.setColor(0xFF53250F);
        icingPaint.setStyle(Paint.Style.FILL);
        icingPaint.setPathEffect(
                new ComposePathEffect(
                    new CornerPathEffect(40f),
                    new DiscretePathEffect(60f, 15f) ) );
        sprinklePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        sprinkleRotation = 0f;
        sprinkles = generateSprinkles(numOfSprinkles);
        scale = 0.5f;
        holePath = new Path();
    }

    private List<Sprinkle> generateSprinkles(int numOfSprinkles) {
        Random rando = new Random();
        List<Sprinkle> listo = new ArrayList<>();

        for (int i=0; i<numOfSprinkles; i++) {
            int color = sprinkleColors[i % sprinkleColors.length];
            listo.add(new Sprinkle(
                    color,
                    rando.nextFloat() * 360f,
                    rando.nextFloat(),
                    rando.nextFloat() * 360f)
            );
        }
        return listo;

    }

//    public void setBasePaintColor(int color) {
//        basePaint.setColor(color);
//        invalidateSelf();
//    }
//
//    public void setIcingPaintColor(int color) {
//        icingPaint.setColor(color);
//        invalidateSelf();
//    }

    public void setScale(float value) {
        scale = value;
        invalidateSelf();
    }

//    public void setSprinkleRotation(float value) {
//        sprinkleRotation = value;
//        invalidateSelf();
//    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);

        holePath.reset();
        holePath.addCircle(
                bounds.exactCenterX(),
                bounds.exactCenterY(),
                bounds.width() / 6f,
                Path.Direction.CW);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        final float centerX = getBounds().exactCenterX();
        final float centerY = getBounds().exactCenterY();

        int initialSave = canvas.save();

        // Scale as appropriate
        canvas.scale(scale, scale, centerX, centerY);

        // Draw outside the hole only
        if (Build.VERSION.SDK_INT >= 26) {
            canvas.clipOutPath(holePath);
        } else {
            canvas.clipPath(holePath, Region.Op.DIFFERENCE);
        }

        // Draw the base
        canvas.drawCircle(
                centerX,
                centerY,
                getBounds().width() / 2f,
                basePaint);

        // Draw the icing
        canvas.drawCircle(
                centerX,
                centerY,
                getBounds().width() / 2.1f,
                icingPaint);

        // Draw the sprinkles
        for (Sprinkle sprinky : sprinkles) {
            final float holeRadius = getBounds().width() / 6f;
            final float ringRadius = getBounds().width() / 3f;
            final float padding = 20f;

            float modDistance = holeRadius + padding + (ringRadius - padding * 2) * sprinky.distance;

            canvas.save();
            canvas.rotate(sprinky.angle, centerX, centerY); // Rotate entire canvas around center
            canvas.translate(0f, modDistance);  // Translate canvas to sprinkle's position
            canvas.rotate(                          // Rotate canvas around sprinkle's location
                    sprinky.rotation + 360f * sprinkleRotation,
                    centerX,
                    centerY);

            sprinklePaint.setColor(sprinky.color);
            canvas.drawRoundRect(
                    centerX - 4f,
                    centerY - 18f,
                    centerX + 4f,
                    centerY + 18f,
                    10f,
                    10f,
                    sprinklePaint);

            canvas.restore();
        }

        canvas.restoreToCount(initialSave);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }



    private class Sprinkle {
        private int color;
        private float angle;
        private float distance;
        private float rotation;

        public Sprinkle(int color, float angle, float distance, float rotation) {
            this.color = color;
            this.angle = angle;
            this.distance = distance;
            this.rotation = rotation;
        }
    }

}

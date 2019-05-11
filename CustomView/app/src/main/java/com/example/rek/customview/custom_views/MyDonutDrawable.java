package com.example.rek.customview.custom_views;

import android.content.res.Resources;
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
import android.support.v4.content.res.ResourcesCompat;

import com.example.rek.customview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Custom drawable of a donut
 * Based on 'https://github.com/rharter/mmmmm'
 */
public class MyDonutDrawable extends Drawable {

    private Paint basePaint;
    private int currentBaseColor;
    private Paint icingPaint;
    private int currentIcingColor;
    private Paint sprinklePaint;
    private Path holePath;
    private float scale;
    private List<Sprinkle> sprinkles;
    private float sprinkleRotation;

    private static int[] baseColors = new int[] {
            0xFFc6853b,     // Beige
            0xFF994c1a,     // Brown
            0xFFf4e77f      // Yellow
    };

    private static int[] icingColors = new int[] {
            0xFF53250F,     // Dark Brown
            0xFFfcaeae,     // Pink
            0xFFf2b3e8      // Purple
    };


    private static int[] sprinkleColors = new int[] {
            0xFFFF0000,     // Red
            0xFFFFFFFF,     // White
            0xFFFFFF00,     // Yellow
            0xFF0000FF,     // Blue
            0xFF00FFFF,     // Cyan
            0xFF00FF00,     // Green
            0xFFFF00FF,     // Magenta
            };

    public MyDonutDrawable(int numOfSprinkles) {

        basePaint = new Paint();
        currentBaseColor = 0;
        basePaint.setColor(baseColors[currentBaseColor]);
        basePaint.setStyle(Paint.Style.FILL);
        icingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        currentIcingColor = 0;
        icingPaint.setColor(icingColors[currentIcingColor]);
        icingPaint.setStyle(Paint.Style.FILL);
        icingPaint.setPathEffect(
                new ComposePathEffect(
                    new CornerPathEffect(40f),
                    new DiscretePathEffect(60f, 19f) ) );
        sprinklePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        sprinkles = generateSprinkles(numOfSprinkles);
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

    public void toggleBasePaintColor() {
        currentBaseColor = (++currentBaseColor % baseColors.length);
        basePaint.setColor(baseColors[currentBaseColor]);
        invalidateSelf();
    }

    public void toggleIcingPaintColor() {
        currentIcingColor = (++currentIcingColor % icingColors.length);
        icingPaint.setColor(icingColors[currentIcingColor]);
        invalidateSelf();
    }

    public void setScale(float value) {
        scale = value;
        invalidateSelf();
    }

    public void setSprinkleRotation(float value) {
        sprinkleRotation = value;
        invalidateSelf();
    }

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

        // Don't draw the hole
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
                getBounds().width() / 2.17f,
                icingPaint);

        // Draw the sprinkles
        for (Sprinkle sprinky : sprinkles) {
            final float holeRadius = getBounds().width() / 6f;
            final float ringRadius = getBounds().width() / 3f;
            final float padding = 20f;

            float modDistance = holeRadius + padding + ((ringRadius-(padding*2)) * sprinky.distance);

            canvas.save();
            canvas.rotate(sprinky.angle, centerX, centerY); // Rotate entire canvas around center
            canvas.translate(0f, modDistance);  // Translate canvas to sprinkle's position
            canvas.rotate(                          // Rotate canvas around sprinkle's location
                    sprinky.rotation + (360f * sprinkleRotation),
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

        private Sprinkle(int color, float angle, float distance, float rotation) {
            this.color = color;
            this.angle = angle;
            this.distance = distance;
            this.rotation = rotation;
        }
    }

}

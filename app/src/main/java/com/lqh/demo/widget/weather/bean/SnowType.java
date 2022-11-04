package com.lqh.demo.widget.weather.bean;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.lqh.demo.R;
import com.lqh.demo.widget.weather.DynamicWeatherView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import androidx.annotation.IntDef;

public class SnowType extends BaseWeatherType {

    public static final int SNOW_LEVEL_1 = 20;//小雪级别
    public static final int SNOW_LEVEL_2 = 40;//中雪级别
    public static final int SNOW_LEVEL_3 = 60;//大到暴雪级别

    private ArrayList<Snow> mSnows;

    private Paint mPaint;

    private Snow snow;

    float transFactor;

    private int snowLevel = SNOW_LEVEL_1;

    Bitmap bitmap;

    Matrix matrix;

    public SnowType(Context context, @SnowLevel int snowLevel) {
        super(context);
        setColor(0xFF62B1FF);
        this.snowLevel = snowLevel;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(5);
        mSnows = new ArrayList<>();
        matrix = new Matrix();
        bitmap = decodeResource(bitmap, R.drawable.ic_snow_ground);
    }

    @Override
    public void onDrawElements(Canvas canvas) {
        clearCanvas(canvas);
        canvas.drawColor(getDynamicColor());
        mPaint.setAlpha(255);
        matrix.reset();
        matrix.postScale(0.25f, 0.25f);
        matrix.postTranslate(transFactor, getHeight() - bitmap.getHeight() * 0.23f);
        canvas.drawBitmap(bitmap, matrix, mPaint);
        for (int i = 0; i < mSnows.size(); i++) {
            snow = mSnows.get(i);
            mPaint.setAlpha((int) (255 * ((float) snow.y / (float) getHeight())));
            canvas.drawCircle(snow.x, snow.y, snow.size, mPaint);
        }
        for (int i = 0; i < mSnows.size(); i++) {
            snow = mSnows.get(i);
            snow.y += snow.speed;
            if (snow.y > getHeight() + snow.size * 2) {
                snow.y = 0 - snow.size * 2;
                snow.x = getRandom(0, getWidth());
            }
        }
    }

    @Override
    public void generateElements() {
        mSnows.clear();
        for (int i = 0; i < snowLevel; i++) {
            Snow snow = new Snow(
                    getRandom(0, getWidth()),
                    getRandom(0, getHeight()),
                    getRandom(dp2px(1), dp2px(6)),
                    getRandom(1, snowLevel / 12)
            );
            mSnows.add(snow);
        }
    }

    @Override
    public void startAnimation(DynamicWeatherView dynamicWeatherView, int fromColor) {
        super.startAnimation(dynamicWeatherView, fromColor);
        ValueAnimator animator = ValueAnimator.ofFloat(-bitmap.getWidth() * 0.25f, getWidth() - bitmap.getWidth() * 0.25f);
        animator.setDuration(1000);
        animator.setRepeatCount(0);
        animator.setInterpolator(new OvershootInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                transFactor = (float) animation.getAnimatedValue();
            }
        });

        animator.start();
    }

    @Override
    public void endAnimation(DynamicWeatherView dynamicWeatherView, Animator.AnimatorListener listener) {
        super.endAnimation(dynamicWeatherView, listener);
        ValueAnimator animator = ValueAnimator.ofFloat(getWidth() - bitmap.getWidth() * 0.25f, getWidth());
        animator.setDuration(1000);
        animator.setRepeatCount(0);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                transFactor = (float) animation.getAnimatedValue();
            }
        });
        if (listener != null) {
            animator.addListener(listener);
        }
        animator.start();
    }

    public static class Snow {
        /**
         * 雪花 x 轴坐标
         */
        int x;
        /**
         * 雪花 y 轴坐标
         */
        int y;
        /**
         * 雪花大小
         */
        int size;
        /**
         * 雪花移动速度
         */
        int speed;

        public Snow(int x, int y, int size, int speed) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.speed = speed;
        }

    }

    @IntDef({SNOW_LEVEL_1, SNOW_LEVEL_2, SNOW_LEVEL_3})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SnowLevel {
    }
}

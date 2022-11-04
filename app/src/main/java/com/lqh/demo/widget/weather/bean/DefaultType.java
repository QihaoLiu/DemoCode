package com.lqh.demo.widget.weather.bean;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Canvas;

import com.lqh.demo.widget.weather.DynamicWeatherView;

public class DefaultType extends BaseWeatherType {

    public DefaultType(Context context) {
        super(context);
        setColor(0xFF51C0F8);
    }

    @Override
    public void onDrawElements(Canvas canvas) {
        clearCanvas(canvas);
        canvas.drawColor(getDynamicColor());
    }

    @Override
    public void generateElements() {

    }

    @Override
    public void endAnimation(DynamicWeatherView dynamicWeatherView, Animator.AnimatorListener listener) {
        super.endAnimation(dynamicWeatherView, listener);
        listener.onAnimationEnd(null);
    }
}

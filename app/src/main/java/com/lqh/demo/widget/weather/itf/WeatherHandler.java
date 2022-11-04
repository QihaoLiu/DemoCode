package com.lqh.demo.widget.weather.itf;

import android.content.Context;
import android.graphics.Canvas;

public interface WeatherHandler {
    void onDrawElements(Canvas canvas);

    void onSizeChanged(Context context, int width, int height);
}

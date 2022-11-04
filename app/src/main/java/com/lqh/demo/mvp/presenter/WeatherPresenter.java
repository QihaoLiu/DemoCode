package com.lqh.demo.mvp.presenter;

import com.lqh.base.mvp.BasePresenter;
import com.lqh.demo.bean.LocationBean;
import com.lqh.demo.mvp.contract.WeatherContract;
import com.lqh.demo.mvp.model.WeatherModel;
import com.qweather.sdk.bean.weather.WeatherNowBean;

import javax.inject.Inject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

public class WeatherPresenter extends BasePresenter<WeatherContract.Model,WeatherContract.View> {

    @Inject
    public WeatherPresenter(WeatherContract.Model model, WeatherContract.View rootView) {
        super(model, rootView);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onCreate() {
        requestWeather();
    }

    public void requestWeather() {
        mModel.getWeather(new WeatherModel.OnRequestFinishedListener() {
            @Override
            public void onResult(LocationBean locationBean,WeatherNowBean.NowBaseBean weatherNowBean) {
                if (null == weatherNowBean) {
                    mRootView.showMessage("天气信息获取失败");
                }else {
                    mRootView.callResult(locationBean,weatherNowBean);
                }
            }
        });
    }

}

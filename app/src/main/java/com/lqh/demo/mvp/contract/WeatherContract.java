package com.lqh.demo.mvp.contract;

import com.lqh.base.mvp.IModel;
import com.lqh.base.mvp.IView;
import com.lqh.demo.bean.LocationBean;
import com.lqh.demo.mvp.model.WeatherModel;
import com.qweather.sdk.bean.weather.WeatherNowBean;

public class WeatherContract {

    public interface View extends IView {

        void callResult(LocationBean locationBean, WeatherNowBean.NowBaseBean now);

    }

    public interface Model extends IModel {

        void getWeather(WeatherModel.OnRequestFinishedListener listener);

    }

}

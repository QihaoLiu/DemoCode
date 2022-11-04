package com.lqh.demo.mvp.model;

import com.amap.api.location.AMapLocation;
import com.blankj.utilcode.util.Utils;
import com.lqh.base.mvp.BaseModel;
import com.lqh.base.utils.ILog;
import com.lqh.demo.bean.LocationBean;
import com.lqh.demo.data.LocationClient;
import com.lqh.demo.mvp.contract.WeatherContract;
import com.qweather.sdk.bean.base.Code;
import com.qweather.sdk.bean.base.Lang;
import com.qweather.sdk.bean.base.Unit;
import com.qweather.sdk.bean.weather.WeatherNowBean;
import com.qweather.sdk.view.QWeather;

import java.text.DecimalFormat;

import javax.inject.Inject;

public class WeatherModel extends BaseModel implements WeatherContract.Model {

    public interface OnRequestFinishedListener {

        void onResult(LocationBean locationBean,WeatherNowBean.NowBaseBean weatherNowBean);

    }

    @Inject
    public WeatherModel() {
        super();
    }

    @Override
    public void getWeather(OnRequestFinishedListener listener) {
        LocationClient.get(Utils.getApp()).startLocation(new LocationClient.LocateListener() {
            @Override
            public void onReceiveLocation(AMapLocation aMapLocation) {
                LocationBean locationBean = new LocationBean();
                if (null == aMapLocation || AMapLocation.LOCATION_SUCCESS != aMapLocation.getErrorCode()) {
                    locationBean.mLatitude = 22.573497;
                    locationBean.mLongitude = 113.92768;
                    locationBean.mAddress = "TCL科学园国际E城G区";
                    locationBean.mCountry = "中国";
                    locationBean.mProvince = "广东省";
                    locationBean.mCity = "深圳市";
                    locationBean.mCityCode = "101280604";
                    locationBean.mDistrict = "南山区";
                    locationBean.mAdCode = "中山园路1号";
                }else {
                    locationBean.mLatitude = aMapLocation.getLatitude();
                    locationBean.mLongitude = aMapLocation.getLongitude();
                    locationBean.mAddress = aMapLocation.getAddress();
                    locationBean.mCountry = aMapLocation.getCountry();
                    locationBean.mProvince = aMapLocation.getProvince();
                    locationBean.mCity = aMapLocation.getCity();
                    locationBean.mCityCode = aMapLocation.getCityCode();
                    locationBean.mDistrict = aMapLocation.getDistrict();
                    locationBean.mAdCode = aMapLocation.getAdCode();
                }
                requestWeather(listener,locationBean);
            }
        });
    }

    public void requestWeather(OnRequestFinishedListener listener,LocationBean locationBean){
        String lat_lng = new DecimalFormat("#.##").format(locationBean.mLongitude) + "," + new DecimalFormat("#.##").format(locationBean.mLatitude);
        QWeather.getWeatherNow(Utils.getApp(), lat_lng, Lang.ZH_HANS, Unit.METRIC, new QWeather.OnResultWeatherNowListener() {
            @Override
            public void onError(Throwable e) {
                ILog.d("requestWeather onError "+e.toString());
                listener.onResult(locationBean,null);
            }

            @Override
            public void onSuccess(WeatherNowBean weatherBean) {
                ILog.d("requestWeather onSuccess "+weatherBean.getCode());
                if (Code.OK == weatherBean.getCode()) {
                    WeatherNowBean.NowBaseBean now = weatherBean.getNow();
                    listener.onResult(locationBean,now);
                } else {
                    Code code = weatherBean.getCode();
                    ILog.d("天气信息获取失败:"+code.toString());
                    listener.onResult(locationBean,null);
                }
            }
        });
    }

}

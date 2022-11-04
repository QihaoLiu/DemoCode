package com.lqh.demo.data;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

public class LocationClient {

    private static LocationClient client;
    private static AMapLocationClient mLocationClient = null;
    private static AMapLocationClientOption mLocationOption = null;
    private LocateListener mLocateListener;

    public interface LocateListener {
        void onReceiveLocation(AMapLocation var1);
    }

    public LocationClient(Context context) {
        try {
            AMapLocationClient.updatePrivacyShow(context,true,true);
            AMapLocationClient.updatePrivacyAgree(context,true);
            mLocationClient = new AMapLocationClient(context);
            mLocationOption = getDefaultOption();
            mLocationClient.setLocationOption(mLocationOption);
            mLocationClient.setLocationListener(mLocationListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LocationClient get(Context context) {
        if (client == null) {
            synchronized (LocationClient.class) {
                client = new LocationClient(context.getApplicationContext());
            }
        }
        return client;
    }

    private AMapLocationClientOption getDefaultOption() {
        //定位参数
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //使用一次定位
        mLocationOption.setOnceLocation(true);
        //采用设备传感器计算海拔，角度和速度
        mLocationOption.setSensorEnable(true);
        //高精度定位模式，会同时使用网络定位和GPS定位，优先返回最高精度的定位结果，以及对应的地址描述信息
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位请求超时时间
        mLocationOption.setHttpTimeOut(5 * 1000);
        //关闭网络定位结果缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //每x分钟定位一次
        mLocationOption.setInterval(10*1000);
        //设置返回地址信息
        mLocationOption.setNeedAddress(true);
        //设置允许模拟位置
        mLocationOption.setMockEnable(true);
        //设置定位语言
        mLocationOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.ZH);
        return mLocationOption;
    }

    /**
     * 开始定位
     */
    public void startLocation(LocateListener listener) {
        if (null != mLocationClient) {
            mLocateListener = listener;
            mLocationClient.startLocation();
        }
    }

    /**
     * 停止定位
     */
    private void stopLocation() {
        if (null != mLocationClient) {
            mLocationClient.stopLocation();
        }
    }

    /**
     * 销毁定位
     */
    private void destroyLocation() {
        if (null != mLocationClient) {
            mLocationClient.onDestroy();
            mLocationClient = null;
            mLocationOption = null;
        }
    }

    /**
     * 异步获取定位结果
     */
    AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            stopLocation();
            destroyLocation();

            if (null == location) {
                return;
            }
            if (null != mLocateListener) {
                mLocateListener.onReceiveLocation(location);
            }
        }
    };

}

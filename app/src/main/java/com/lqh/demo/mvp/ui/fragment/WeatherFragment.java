package com.lqh.demo.mvp.ui.fragment;

import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lqh.base.activity.BaseFragment;
import com.lqh.demo.R;
import com.lqh.demo.bean.LocationBean;
import com.lqh.demo.di.component.DaggerWeatherComponent;
import com.lqh.demo.mvp.contract.WeatherContract;
import com.lqh.demo.mvp.presenter.WeatherPresenter;
import com.lqh.demo.widget.weather.DynamicWeatherView;
import com.lqh.demo.widget.weather.bean.BaseWeatherType;
import com.lqh.demo.widget.weather.bean.FogType;
import com.lqh.demo.widget.weather.bean.HailType;
import com.lqh.demo.widget.weather.bean.HazeType;
import com.lqh.demo.widget.weather.bean.OvercastType;
import com.lqh.demo.widget.weather.bean.RainType;
import com.lqh.demo.widget.weather.bean.SandstormType;
import com.lqh.demo.widget.weather.bean.ShortWeatherInfo;
import com.lqh.demo.widget.weather.bean.SnowType;
import com.lqh.demo.widget.weather.bean.SunnyType;
import com.qweather.sdk.bean.weather.WeatherNowBean;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

public class WeatherFragment extends BaseFragment<WeatherPresenter> implements View.OnClickListener, WeatherContract.View {

    private DynamicWeatherView dynamicWeatherView;
    private TextView district;
    private TextView temp;
    private TextView tempText;
    private TextView hum;
    private TextView precip;
    private TextView wind;
    private TextView windDir;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_weather;
    }

    @Override
    public void setupActivityComponent() {
        DaggerWeatherComponent.builder().view(this).build().injectWeatherFragment(this);
    }

    @Override
    public void initView() {
        super.initView();
        findView(R.id.weather_type,this);
        dynamicWeatherView = (DynamicWeatherView) findView(R.id.dynamicWeather);
        district = findView(R.id.district);
        temp = findView(R.id.temp);
        tempText = findView(R.id.temp_text);
        hum = findView(R.id.tv_hum);
        precip = findView(R.id.tv_precip);
        wind = findView(R.id.tv_wind_sc);
        windDir = findView(R.id.tv_wind_dir);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void onClick(View v) {
        previewDynamicWeather();
    }

    private void previewDynamicWeather() {
        final String[] items = new String[]{"晴（白天）", "晴（夜晚）", "多云", "阴", "雨", "雨夹雪",
                "雪", "冰雹", "雾", "雾霾", "沙尘暴"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("动态天气预览");
        builder.setCancelable(true);
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                switchDynamicWeather(items[which]);
            }
        });
        builder.create().show();
    }

    private void switchDynamicWeather(String which) {
        ShortWeatherInfo info = new ShortWeatherInfo();
        info.setCode("100");
        info.setWindSpeed("11");
        BaseWeatherType type;
        switch (which) {
            case "晴（白天）":
                info.setSunrise("00:01");
                info.setSunset("23:59");
                info.setMoonrise("00:00");
                info.setMoonset("00:01");
                type = new SunnyType(getActivity(), info);
                break;
            case "晴（夜晚）":
                info.setSunrise("00:00");
                info.setSunset("00:01");
                info.setMoonrise("00:01");
                info.setMoonset("23:59");
                type = new SunnyType(getActivity(), info);
                break;
            case "多云":
            case "少云":
            case "晴间多云":
                info.setSunrise("00:01");
                info.setSunset("23:59");
                info.setMoonrise("00:00");
                info.setMoonset("00:01");
                SunnyType sunnyType = new SunnyType(getActivity(), info);
                sunnyType.setCloud(true);
                type = sunnyType;
                break;
            case "阴":
                type = new OvercastType(getActivity(), info);
                break;
            case "雨":
            case "阵雨":
            case "强阵雨":
            case "强雷阵雨":
            case "雷阵雨伴有冰雹":
            case "小雨":
            case "中雨":
            case "大雨":
            case "极端降雨":
            case "毛毛雨/细雨":
            case "暴雨":
            case "大暴雨":
            case "特大暴雨":
            case "冻雨":
            case "小到中雨":
            case "中到大雨":
            case "大到暴雨":
            case "暴雨到大暴雨":
            case "大暴雨到特大暴雨":
                RainType rainType = new RainType(getActivity(), RainType.RAIN_LEVEL_2, RainType.WIND_LEVEL_2);
                rainType.setFlashing(true);
                type = rainType;
                break;
            case "雨夹雪":
                RainType rainSnowType = new RainType(getActivity(), RainType.RAIN_LEVEL_1, RainType.WIND_LEVEL_1);
                rainSnowType.setSnowing(true);
                type = rainSnowType;
                break;
            case "雪":
            case "小雪":
            case "中雪":
            case "大雪":
            case "暴雪":
            case "雨雪天气":
            case "阵雨夹雪":
            case "阵雪":
            case "小到中雪":
            case "中到大雪":
            case "大到暴雪":
                type = new SnowType(getActivity(), SnowType.SNOW_LEVEL_2);
                break;
            case "冰雹":
                type = new HailType(getActivity());
                break;
            case "雾":
            case "薄雾":
            case "浓雾":
            case "大雾":
            case "强浓雾":
            case "特强浓雾":
                type = new FogType(getActivity());
                break;
            case "雾霾":
            case "中度霾":
            case "重度霾":
            case "严重霾":
                type = new HazeType(getActivity());
                break;
            case "扬沙":
            case "浮尘":
            case "沙尘暴":
            case "强沙尘暴":
                type = new SandstormType(getActivity());
                break;
            default:
                info.setSunrise("00:01");
                info.setSunset("23:59");
                info.setMoonrise("00:00");
                info.setMoonset("00:01");
                type = new SunnyType(getActivity(), info);
        }
        dynamicWeatherView.setType(type);
    }


    @Override
    public void callResult(LocationBean locationBean, WeatherNowBean.NowBaseBean now) {
        runUiThread(new Runnable() {
            @Override
            public void run() {
                district.setText(locationBean.mDistrict);
                temp.setText(now.getTemp() + "℃");
                tempText.setText(now.getText());
                switchDynamicWeather(now.getText());
                hum.setText(now.getHumidity()+"%");
                precip.setText(now.getPrecip());
                wind.setText(now.getWindScale()+"级");
                windDir.setText(now.getWindDir());
            }
        });
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

}

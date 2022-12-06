package com.lqh.demo.mvp.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.lqh.base.activity.BaseActivity;
import com.lqh.base.utils.DeviceUtils;
import com.lqh.demo.R;

public class DeviceActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_device;
    }

    @Override
    public void setupActivityComponent() {

    }

    @Override
    public void initView() {
        findView(R.id.back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        TextView text = (TextView) findView(R.id.device);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Imei:"+ DeviceUtils.getImei(this,0));
        stringBuilder.append("\nWifi:"+ DeviceUtils.getMacByWifi(this));
        stringBuilder.append("\nEth0:"+ DeviceUtils.getMacByEth0());
        stringBuilder.append("\nIPv4:"+ DeviceUtils.getIP(true));
        stringBuilder.append("\nIPv6:"+ DeviceUtils.getIP(false));
        text.setText(stringBuilder.toString());
    }
}
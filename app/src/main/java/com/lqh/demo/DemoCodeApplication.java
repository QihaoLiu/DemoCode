package com.lqh.demo;

import android.Manifest;
import android.app.Application;

import com.blankj.utilcode.util.PermissionUtils;
import com.lqh.base.BaseManager;
import com.lqh.database.DBManager;

import java.util.List;

/**
 * DemoCode
 *
 * @author LeoLiu
 */

public class DemoCodeApplication extends Application{

    private String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
    };

    @Override
    public void onCreate() {
        super.onCreate();
        BaseManager.getInstance().init(this);
        BaseManager.getInstance().setDebug(true);
        DBManager.getInstance().init(this);
        if (!PermissionUtils.isGranted(permissions)) {
            PermissionUtils.permission(permissions).callback(new PermissionUtils.FullCallback() {
                @Override
                public void onGranted(List<String> permissionsGranted) {

                }

                @Override
                public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {

                }
            }).request();
        }
    }
}

package com.lqh.base.utils;

import android.graphics.Point;
import android.hardware.Camera;

import java.util.ArrayList;
import java.util.List;

/**
 * DemoCode
 *
 * @author LeoLiu
 */

public class CameraPreviewSizeUtil {

    public static final String TAG = "CameraPreviewSize";

    public static int PREVIEW_EQUAL_SCREEN = 0;
    public static int PREVIEW_EQUAL_SCALE = 1;
    public static int PREVIEW_EQUAL_SIDE = 2;
    public static int PREVIEW_MODE = PREVIEW_EQUAL_SCREEN;

    public static int bestPreviewWidth;
    public static int bestPreviewHeight;
    public static boolean showAllSupportedPreviewSizes = false;

    public static boolean getBestPreviewSize(Camera.Parameters parameters, boolean landScape, int screenWidth, int screenHeight){
        List<Camera.Size> sizeList = parameters.getSupportedPreviewSizes();
        if (showAllSupportedPreviewSizes) {
            for (Camera.Size size : sizeList) {
                ILog.d(TAG,"Supported PreviewSizes " + size.width + " - " + size.height);
            }
        }
        int initWidth;
        int initHeight;
        boolean hasChoose = false;
        if (landScape) {
            initWidth = screenWidth;
            initHeight = screenHeight;
        }else {
            initWidth = screenHeight;
            initHeight = screenWidth;
        }
        for (Camera.Size size : sizeList) {
            if (size.width == initWidth && size.height == initHeight) {
                hasChoose = true;
                bestPreviewWidth = size.width;
                bestPreviewHeight = size.height;
                PREVIEW_MODE = PREVIEW_EQUAL_SCREEN;
                break;
            }
        }
        if (hasChoose) {
            return true;
        }
        double initScale = initWidth * 1.0 / initHeight;
        List<Camera.Size> scaleList = new ArrayList<>();
        scaleList.clear();
        for (Camera.Size size : sizeList) {
            double supportScale = size.width * 1.0 / size.height;
            if (initScale == supportScale) {
                scaleList.add(size);
            }
        }
        if (scaleList.size() > 0) {
            int choose = 0;
            int chooseWidth = scaleList.get(choose).width;
            for (int i = 0; i < scaleList.size(); i++) {
                if (scaleList.get(i).width > chooseWidth) {
                    choose = i;
                    chooseWidth = scaleList.get(i).width;
                }
            }
            bestPreviewWidth = scaleList.get(choose).width;
            bestPreviewHeight = scaleList.get(choose).height;
            PREVIEW_MODE = PREVIEW_EQUAL_SCALE;
            return true;
        }
        for (Camera.Size size : sizeList) {
            if (size.width == initWidth || size.height == initHeight) {
                hasChoose = true;
                bestPreviewWidth = size.width;
                bestPreviewHeight = size.height;
                PREVIEW_MODE = PREVIEW_EQUAL_SIDE;
                break;
            }
        }
        if (hasChoose) {
            return true;
        }
        int choose = 0;
        int lastChooseWidth = 0;
        for (int i = 0; i < sizeList.size(); i++) {
            if (sizeList.get(i).width > lastChooseWidth) {
                choose = i;
                lastChooseWidth = sizeList.get(i).width;
            }
        }
        if (lastChooseWidth != 0) {
            bestPreviewWidth = sizeList.get(choose).width;
            bestPreviewHeight = sizeList.get(choose).height;
            return true;
        }
        return false;
    }

    public static Point getSurfaceViewSize(boolean landScape, int screenWidth, int screenHeight, int previewWidth, int previewHeight){
        Point point = new Point();
        if (landScape) {
            //宽相等
            if (screenWidth == previewWidth) {
                //预览高大于屏幕高，则缩短宽
                if (previewHeight > screenHeight) {
                    point.y = screenHeight;
                    point.x = (int)(screenHeight * 1.0 / previewHeight * previewWidth);
                    return point;
                }else {
                    //预览高小于屏幕高，则宽为预览宽
                    point.y = previewHeight;
                    point.x = screenWidth;
                    return point;
                }
            }else if(screenHeight == previewHeight){
                //高相等
                if (previewWidth > screenWidth) {
                    //预览宽大于屏幕宽，则压缩高
                    point.x = screenWidth;
                    point.y = (int)(screenWidth * 1.0 / previewWidth * previewHeight);
                    return point;
                }else {
                    //预览宽小于屏幕宽，则宽为预览宽
                    point.x = previewWidth;
                    point.y = screenHeight;
                    return point;
                }
            }else {
                double screenScale = screenWidth * 1.0 / screenHeight;
                double previewScale = previewWidth * 1.0 / previewHeight;
                if (screenScale > previewScale) {
                    //屏幕比例大于预览比例，折预览高到屏幕高
                    point.y = screenHeight;
                    point.x = (int)(screenHeight * 1.0 / previewHeight * screenWidth);
                    return point;
                }else {
                    point.x = screenWidth;
                    point.y = (int)(screenHeight * 1.0 / previewWidth * previewHeight);
                    return point;
                }
            }
        }else {
            if (screenWidth == previewHeight) {
                if (previewWidth > screenHeight) {
                    point.x = (int)(screenHeight * 1.0 / previewWidth * previewHeight);
                    point.y = screenHeight;
                    return point;
                }else {
                    point.x = screenWidth;
                    point.y = previewWidth;
                    return point;
                }
            }else if(screenHeight == previewWidth){
                if (previewHeight > screenWidth) {
                    point.x = screenWidth;
                    point.y = (int)(screenWidth * 1.0 / previewHeight * previewWidth);
                    return point;
                }else {
                    point.x = previewHeight;
                    point.y = screenHeight;
                    return point;
                }
            }else {
                double screenScale = screenHeight * 1.0 / screenWidth;
                double previewScale = previewWidth * 1.0 / previewHeight;
                if (screenScale > previewScale) {
                    point.x = screenWidth;
                    point.y = (int)(screenWidth * 1.0 / previewHeight * previewWidth);
                    return point;
                }else {
                    point.x = (int)(screenHeight * 1.0 / previewWidth * previewHeight);
                    point.y = screenHeight;
                    return point;
                }
            }
        }
    }

}

package com.lqh.base.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.StringUtils;

import java.util.List;

/**
 * DemoCode
 *
 * @author LeoLiu
 */

public class JSON {

    private static final String TAG = "JSON_JSON";

    /**
     * 判断json格式是否正确
     * @param s
     * @return
     */
    public static boolean isJsonCorrect(String s) {
        if (StringUtils.isTrimEmpty(s)
                || s.equals("{}")
                || s.equals("[null]")
                || s.equals("{null}")
                || s.equals("null")) {
            return false;
        }
        return true;
    }

    /**
     * 获取有效的json
     * @param json
     * @return
     */
    public static String getCorrectJson(String json) {
        return isJsonCorrect(json) ? json : "";
    }

    /**
     * JSONObject转实体类
     * @param object
     * @param clazz
     * @return
     */
    public static <T> T parseObject(JSONObject object, Class<T> clazz) {
        return parseObject(toJSONString(object), clazz);
    }
    /**
     * json转实体类
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        try {
            return com.alibaba.fastjson.JSON.parseObject(getCorrectJson(json), clazz);
        } catch (Exception e) {
            ILog.e(TAG, "parseObject  catch \n" + e.getMessage());
        }
        return null;
    }

    /**
     * @param obj
     * @return
     */
    public static JSONObject parseObject(Object obj) {
        return parseObject(toJSONString(obj));
    }

    /**
     * json转JSONObject
     * @param json
     * @return
     */
    public static JSONObject parseObject(String json) {
        try {
            return com.alibaba.fastjson.JSON.parseObject(getCorrectJson(json));
        } catch (Exception e) {
            ILog.e(TAG, "parseObject  catch \n" + e.getMessage());
        }
        return null;
    }

    /**
     * @param obj
     * @return
     */
    public static String toJSONString(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        try {
            return com.alibaba.fastjson.JSON.toJSONString(obj);
        } catch (Exception e) {
            ILog.e(TAG, "toJSONString  catch \n" + e.getMessage());
        }
        return null;
    }

    /**
     * 判断是否为JSONObject
     * @param obj instanceof String ? parseObject
     * @return
     */
    public static boolean isJSONObject(Object obj) {
        if (obj instanceof JSONObject) {
            return true;
        }
        if (obj instanceof String) {
            try {
                JSONObject json = parseObject((String) obj);
                return json != null && json.isEmpty() == false;
            } catch (Exception e) {
                ILog.e(TAG, "isJSONObject  catch \n" + e.getMessage());
            }
        }

        return false;
    }
    /**
     * 判断是否为JSONArray
     * @param obj instanceof String ? parseArray
     * @return
     */
    public static boolean isJSONArray(Object obj) {
        if (obj instanceof JSONArray) {
            return true;
        }
        if (obj instanceof String) {
            try {
                JSONArray json = parseArray((String) obj);
                return json != null && json.isEmpty() == false;
            } catch (Exception e) {
                ILog.e(TAG, "isJSONArray  catch \n" + e.getMessage());
            }
        }

        return false;
    }

    /**
     * @param json
     * @return
     */
    public static JSONArray parseArray(String json) {
        try {
            return com.alibaba.fastjson.JSON.parseArray(getCorrectJson(json));
        } catch (Exception e) {
            ILog.e(TAG, "parseArray  catch \n" + e.getMessage());
        }
        return null;
    }
    /**
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        try {
            return com.alibaba.fastjson.JSON.parseArray(getCorrectJson(json), clazz);
        } catch (Exception e) {
            ILog.e(TAG, "parseArray  catch \n" + e.getMessage());
        }
        return null;
    }

}

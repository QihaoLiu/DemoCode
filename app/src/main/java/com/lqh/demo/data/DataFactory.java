package com.lqh.demo.data;

import com.lqh.base.utils.JSON;
import com.lqh.demo.R;
import com.lqh.demo.bean.Item;
import com.lqh.demo.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * DemoCode
 *
 * @author LeoLiu
 */

public class DataFactory {

    public static String getCheckBeanData(boolean mistake){
        User correctBean = new User(1,"a");
        correctBean.setTag(1);
        User mistakeBean = new User(2,"b");
        mistakeBean.setTag(0);
        return mistake? JSON.toJSONString(mistakeBean):JSON.toJSONString(correctBean);
    }

    public static List<Item> getItemData(){
        List<Item> items = new ArrayList<Item>();
        items.clear();
        items.add(new Item("Streaming", R.drawable.ic_rtsp));
        items.add(new Item("Windy.com", R.drawable.ic_windy));
        items.add(new Item("I N F O", R.drawable.ic_info));
        return items;
    }

}

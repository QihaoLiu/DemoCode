package com.lqh.demo.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lqh.base.activity.BaseActivity;
import com.lqh.demo.R;
import com.lqh.demo.bean.Item;

import java.util.List;

/**
 * DemoCode
 *
 * @author LeoLiu
 */
public class ItemAdapter<T extends BaseActivity> extends BaseQuickAdapter<Item, BaseViewHolder> {

    private T t;

    public ItemAdapter(List<Item> items) {
        super(R.layout.item_layout, items);
    }

    public void setActivity(T t){
        this.t = t;
    }

    @Override
    protected void convert(BaseViewHolder helper, Item item) {
        helper.setText(R.id.name,item.getName());
        helper.setImageResource(R.id.icon,item.getIcon());
    }

}

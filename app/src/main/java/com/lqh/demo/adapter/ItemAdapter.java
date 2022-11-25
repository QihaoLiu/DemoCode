package com.lqh.demo.adapter;


import android.widget.ImageView;

import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
        if (item.isGif()) {
            ImageView view = helper.getView(R.id.icon);
            Glide.with(Utils.getApp()).asGif().
              load(item.getIcon()).
              diskCacheStrategy(DiskCacheStrategy.NONE).
              into(view);
        }else {
            helper.setImageResource(R.id.icon,item.getIcon());
        }
    }

}

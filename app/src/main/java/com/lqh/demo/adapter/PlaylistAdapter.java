package com.lqh.demo.adapter;


import android.widget.ImageView;

import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lqh.demo.R;
import com.lqh.demo.bean.PlayList;

import java.util.List;

/**
 * DemoCode
 *
 * @author LeoLiu
 */
public class PlaylistAdapter extends BaseQuickAdapter<PlayList.Playlists, BaseViewHolder> {

    public PlaylistAdapter(List<PlayList.Playlists> playlists) {
        super(R.layout.playlist_layout, playlists);
    }

    @Override
    protected void convert(BaseViewHolder helper, PlayList.Playlists item) {
        helper.setText(R.id.name,item.getName());
        ImageView imageView = helper.getView(R.id.icon);
        Glide.with(Utils.getApp()).load(item.getCoverImgUrl()).into(imageView);
    }

}

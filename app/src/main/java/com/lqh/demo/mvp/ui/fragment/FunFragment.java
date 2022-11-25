package com.lqh.demo.mvp.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lqh.base.activity.BaseFragment;
import com.lqh.demo.R;
import com.lqh.demo.adapter.ItemAdapter;
import com.lqh.demo.data.DataFactory;
import com.lqh.demo.mvp.ui.activity.RtspActivity;
import com.lqh.demo.mvp.ui.activity.WindyActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FunFragment extends BaseFragment {

    private ItemAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fun;
    }

    @Override
    public void setupActivityComponent() {

    }

    @Override
    public void initView() {
        super.initView();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,4,LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = (RecyclerView) findView(R.id.itemView);
        mAdapter = new ItemAdapter(DataFactory.getItemData());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setActivity(context);
    }

    @Override
    public void initData() {
        super.initData();
        ImageView view = (ImageView) findView(R.id.logo);
        Glide.with(Utils.getApp()).asGif().
                load(R.drawable.money).
                diskCacheStrategy(DiskCacheStrategy.NONE).
                into(view);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if (position == 0) {
                    Intent intent = new Intent();
                    intent.setClass(context, RtspActivity.class);
                    context.startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent();
                    intent.setClass(context, WindyActivity.class);
                    context.startActivity(intent);
                }
            }
        });
    }
}

package com.lqh.demo.mvp.ui.fragment;

import com.blankj.utilcode.util.ToastUtils;
import com.lqh.base.activity.BaseFragment;
import com.lqh.demo.R;
import com.lqh.demo.adapter.PlaylistAdapter;
import com.lqh.demo.bean.PlayList;
import com.lqh.demo.di.component.DaggerMusicComponent;
import com.lqh.demo.mvp.contract.MusicContract;
import com.lqh.demo.mvp.presenter.MusicPresenter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MusicFragment extends BaseFragment<MusicPresenter> implements MusicContract.View {

    private RecyclerView recycler;
    private PlaylistAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_music;
    }

    @Override
    public void setupActivityComponent() {
        DaggerMusicComponent.builder().view(this).build().injectMusicFragment(this);
    }

    @Override
    public void initView() {
        super.initView();
        recycler = findView(R.id.list);
    }

    @Override
    public void initData() {
        super.initData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,4, LinearLayoutManager.VERTICAL,false);
        mAdapter = new PlaylistAdapter(new ArrayList<>());
        recycler.setLayoutManager(gridLayoutManager);
        recycler.setAdapter(mAdapter);
    }

    @Override
    public void callResult(PlayList playList) {
        mAdapter.setNewData(playList.getPlaylists());
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

}

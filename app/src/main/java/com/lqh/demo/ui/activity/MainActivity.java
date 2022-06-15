package com.lqh.demo.ui.activity;

import android.view.View;

import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.lqh.base.activity.BaseActivity;
import com.lqh.base.activity.BaseFragment;
import com.lqh.base.bean.BaseBean;
import com.lqh.base.common.BaseConstants;
import com.lqh.base.utils.JSON;
import com.lqh.base.utils.LogUtil;
import com.lqh.base.utils.ThreadPoolUtil;
import com.lqh.demo.R;
import com.lqh.demo.adapter.ItemAdapter;
import com.lqh.demo.bean.User;
import com.lqh.demo.data.DataFactory;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.lqh.demo.ui.fragment.FunFragment;
import com.lqh.demo.ui.fragment.MusicFragment;
import com.lqh.demo.ui.fragment.WeatherFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * DemoCode
 *
 * @author LeoLiu
 */
public class MainActivity extends BaseActivity implements View.OnClickListener{

    private ViewPager mViewPager;
    private BubbleNavigationLinearView mNavigationLinearView;

    private List<BaseFragment> mFragments;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        mViewPager = findView(R.id.view_pager);
        mNavigationLinearView = findView(R.id.navigation_bar);
    }

    @Override
    public void initData() {
        super.initData();
        mFragments = new ArrayList<>();
        mFragments.add(new MusicFragment());
        mFragments.add(new WeatherFragment());
        mFragments.add(new FunFragment());
        mViewPager.setAdapter(new IFragmentAdapter(getSupportFragmentManager(),mFragments));
        mViewPager.setCurrentItem(1);
        mNavigationLinearView.setCurrentActiveItem(1);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mNavigationLinearView.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                mViewPager.setCurrentItem(position, true);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            default:break;
        }
    }

    class IFragmentAdapter extends FragmentPagerAdapter{

        private List<BaseFragment> list;

        public IFragmentAdapter(FragmentManager fm, List<BaseFragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }
    }

}

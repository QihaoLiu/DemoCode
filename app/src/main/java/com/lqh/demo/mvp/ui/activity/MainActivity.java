package com.lqh.demo.mvp.ui.activity;

import android.view.View;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.lqh.base.activity.BaseActivity;
import com.lqh.base.activity.BaseFragment;
import com.lqh.demo.R;
import com.lqh.demo.mvp.ui.fragment.MusicFragment;
import com.lqh.demo.mvp.ui.fragment.WeatherFragment;
import com.lqh.demo.mvp.ui.fragment.FunFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

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
    public void setupActivityComponent() {

    }

    @Override
    public void initView() {
        super.initView();
        mViewPager = (ViewPager) findView(R.id.view_pager);
        mNavigationLinearView = (BubbleNavigationLinearView) findView(R.id.navigation_bar);
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
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mNavigationLinearView.setCurrentActiveItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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

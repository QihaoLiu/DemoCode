package com.lqh.demo;

import android.view.View;

import com.lqh.base.activity.BaseActivity;
import com.lqh.base.bean.BaseBean;
import com.lqh.base.common.BaseConstants;
import com.lqh.base.utils.JSON;
import com.lqh.base.utils.LogUtil;
import com.lqh.base.utils.ThreadPoolUtil;
import com.lqh.demo.adapter.ItemAdapter;
import com.lqh.demo.bean.User;
import com.lqh.demo.data.DataFactory;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * DemoCode
 *
 * @author LeoLiu
 */
public class MainActivity extends BaseActivity implements View.OnClickListener{

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initEvent() {
        super.initView();
        findView(R.id.hello,this);
        RecyclerView recyclerView = findView(R.id.itemView);
        ItemAdapter adapter = new ItemAdapter(DataFactory.getItemData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.setActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.hello:
                ThreadPoolUtil.getSingleThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        LogUtil.d(BaseConstants.TAG,"Run ...... ");
                        User correctBean = JSON.parseObject(DataFactory.getCheckBeanData(false),User.class);
                        User mistakeBean = JSON.parseObject(DataFactory.getCheckBeanData(true),User.class);
                        LogUtil.d(BaseConstants.TAG,"correctBean > "+ BaseBean.isCorrect(correctBean));
                        LogUtil.d(BaseConstants.TAG,"correctBean > "+ BaseBean.isCorrect(mistakeBean));
                    }
                });
                ThreadPoolUtil.shutDown();
                break;

            default:break;
        }
    }



}

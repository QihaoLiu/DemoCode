package com.lqh.demo.ui.fragment;

import com.lqh.base.activity.BaseFragment;
import com.lqh.demo.R;
import com.lqh.demo.adapter.ItemAdapter;
import com.lqh.demo.data.DataFactory;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_weather;
    }

    @Override
    public void initView() {
        super.initView();
        RecyclerView recyclerView = findView(R.id.itemView);
        ItemAdapter adapter = new ItemAdapter(DataFactory.getItemData());
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.setActivity(context);
    }

    @Override
    public void initData() {
        super.initData();
    }
}

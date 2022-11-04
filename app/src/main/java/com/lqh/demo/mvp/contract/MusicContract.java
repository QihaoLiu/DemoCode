package com.lqh.demo.mvp.contract;

import com.lqh.base.mvp.IModel;
import com.lqh.base.mvp.IView;
import com.lqh.demo.bean.PlayList;
import com.lqh.demo.mvp.model.MusicModel;

public class MusicContract {

    public interface View extends IView {

        void callResult(PlayList playList);

    }

    public interface Model extends IModel {

        void getMusic(MusicModel.OnRequestFinishedListener listener);

    }

}

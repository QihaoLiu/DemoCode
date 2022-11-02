package com.lqh.demo.mvp.contract;

import com.lqh.base.mvp.IModel;
import com.lqh.base.mvp.IView;
import com.lqh.demo.bean.BiYing;
import com.lqh.demo.mvp.model.SplashModel;

public class SplashContract {

   public interface View extends IView{

      void callResult(BiYing biYing);

   }

   public interface Model extends IModel{
       void getPic(SplashModel.OnRequestFinishedListener onRequestFinishedListener);
   }

}

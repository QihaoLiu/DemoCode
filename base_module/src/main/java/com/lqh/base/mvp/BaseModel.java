package com.lqh.base.mvp;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class BaseModel implements IModel, LifecycleObserver {

   @Override
   public void onDestroy() {

   }

   @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
   void onDestroy(LifecycleOwner owner) {
      owner.getLifecycle().removeObserver(this);
   }

}

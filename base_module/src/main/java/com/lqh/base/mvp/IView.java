package com.lqh.base.mvp;

import androidx.annotation.NonNull;

public interface IView {

    default void showLoading() {

    }

    default void hideLoading() {

    }

    void showMessage(@NonNull String message);

}

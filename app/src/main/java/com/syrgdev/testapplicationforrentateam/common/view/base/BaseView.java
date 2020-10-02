package com.syrgdev.testapplicationforrentateam.common.view.base;

import android.content.Context;
import android.view.View;

import androidx.annotation.IdRes;

public abstract class BaseView implements IView {

    private View mRootView;

    @Override
    public View getRootView() {
        return mRootView;
    }

    public void setRootView(View rootView) {
        mRootView = rootView;
    }

    @SuppressWarnings("unchecked")
    public  <T extends View> T findViewById(@IdRes int id) {
        return (T) mRootView.findViewById(id);
    }

    public Context getContext() {
        return getRootView().getContext();
    }

}

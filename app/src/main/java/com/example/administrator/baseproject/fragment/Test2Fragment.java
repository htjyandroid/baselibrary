package com.example.administrator.baseproject.fragment;

import android.os.Bundle;
import android.view.View;


import com.blankj.utilcode.util.LogUtils;
import com.example.administrator.baseproject.R;
import com.example.administrator.baseproject.view.Test2View;
import com.example.administrator.baseproject.presenter.Test2Presenter;
import com.htjy.baselibrary.base.MvpFragment;


public class Test2Fragment extends MvpFragment<Test2View, Test2Presenter> implements Test2View {
    private static final String TAG = "Test2Fragment";

    @Override
    public Test2Presenter initPresenter() {
        return new Test2Presenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d("baseLibrary", "onResume");
    }


    @Override
    protected void initViews(Bundle savedInstanceState) {
        LogUtils.d("baseLibrary", "initViews");
    }

    @Override
    public void OnFragmentTrueResume() {
        LogUtils.d("baseLibrary", "resumeUi");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtils.d("baseLibrary", "onHiddenChanged isHidden?" + hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.d("baseLibrary", "setUserVisibleHint isVisibleToUser?" + isVisibleToUser);
    }

    @Override
    protected void lazyLoad() {
        LogUtils.d("baseLibrary", "lazyLoad");
    }

    @Override
    protected void initFragmentData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initStateLayout(View inflateView) {

    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.fragment_test2; //如果你不需要帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    @Override
    public void showNullLayout() {

    }

    @Override
    public void showErrorLayout() {

    }

    @Override
    public void showSuccessLayout() {

    }

    @Override
    public void showNetWorkErrorLayout() {

    }
}

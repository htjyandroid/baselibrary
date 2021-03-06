package com.example.administrator.baseproject.fragment;

import android.os.Bundle;
import android.view.View;


import androidx.databinding.ViewDataBinding;

import com.blankj.utilcode.util.LogUtils;
import com.example.administrator.FragmentViewModel;
import com.example.administrator.baseproject.R;
import com.example.administrator.baseproject.view.Test1View;
import com.example.administrator.baseproject.presenter.Test1Presenter;
import com.htjy.baselibrary.base.MvpFragment;
import com.htjy.baselibrary.base.ViewModelFragment;


public class Test1Fragment extends ViewModelFragment<ViewDataBinding, FragmentViewModel> {
    private static final String TAG = "Test1Fragment";

    @Override
    protected boolean isBinding() {
        return true;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        LogUtils.d("baseLibrary", "initViews");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d("baseLibrary", "onResume");
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
        return R.layout.fragment_test1; //如果你不需要帮你设置 setContentView(id) 需要自行设置,请返回 0
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

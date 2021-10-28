package com.example.administrator.ezviz

import android.content.res.Configuration
import android.os.Bundle
import com.example.administrator.baseproject.R
import com.example.administrator.baseproject.databinding.ActivityEzvizPlayerBinding
import com.htjy.baselibrary.base.ViewModelActivity
import com.htjy.baselibrary.ezvizsdk.EzvizSdkInitTool
import com.htjy.baselibrary.ezvizsdk.fragment.EZRealPlayFragment
import com.lyb.besttimer.pluginwidget.utils.FragmentUtil

/**
 * Created by besttimer on 2021/10/27
 */
class EzvizPlayerActivity : ViewModelActivity<ActivityEzvizPlayerBinding, EzvizPlayerViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_ezviz_player
    }

    override fun initViews(savedInstanceState: Bundle?) {
        EzvizSdkInitTool.initSdk(application, "234fdfe19d594faeb7a9e2c68786e8e7")
        EzvizSdkInitTool.updateAccessToken("at.3vnlz9h85jkfvqn558po7nugdg0y4pea-7efks6qdaz-11sixpb-8yfehutjo")
        FragmentUtil.showAndHideOthers(supportFragmentManager,
            mBinding.layoutPlayer.id,
            EZRealPlayFragment::class.java,
            EZRealPlayFragment.createArgs("ezopen://open.ys7.com/184408397/1.hd.live"),
            null)
        mBinding.layoutPlayer.setHwfactor(0.5625f)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val orientation = newConfig.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            mBinding.layoutPlayer.setHwfactor(0.5625f)
        } else {
            mBinding.layoutPlayer.setHwfactor(0f)
        }
    }

    override fun initData() {

    }

    override fun initListener() {
        mBinding.btnSound.setOnClickListener {
            val fragment = FragmentUtil.findFragment(supportFragmentManager, mBinding.layoutPlayer.id, null)
            if (fragment is EZRealPlayFragment) {
                if (fragment.getRealPlaySound()) {
                    fragment.updateRealPlaySound(false)
                } else {
                    fragment.updateRealPlaySound(true)
                }
            }
        }
    }

}
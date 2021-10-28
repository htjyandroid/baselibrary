package com.htjy.baselibrary.ezvizsdk.fragment

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.view.SurfaceHolder
import android.view.View
import android.view.WindowManager
import com.htjy.baselibrary.base.ViewModelFragment
import com.htjy.baselibrary.ezvizsdk.EZMyConstants
import com.htjy.baselibrary.ezvizsdk.R
import com.htjy.baselibrary.ezvizsdk.RealPlaySquareInfo
import com.htjy.baselibrary.ezvizsdk.databinding.EzvizFragmentRealPlayBinding
import com.htjy.baselibrary.ezvizsdk.viewmodel.EZRealPlayViewModel
import com.videogo.openapi.EZConstants
import com.videogo.openapi.EZOpenSDK
import com.videogo.openapi.EZPlayer
import com.videogo.realplay.RealPlayStatus
import com.videogo.util.LocalInfo
import com.videogo.util.Utils
import java.lang.ref.WeakReference

/**
 * Created by besttimer on 2021/10/27
 */
class EZRealPlayFragment : ViewModelFragment<EzvizFragmentRealPlayBinding, EZRealPlayViewModel>() {

    companion object {

        val PLAY_URL: String = "PLAY_URL"

        fun createArgs(url: String): Bundle {
            val bundle = Bundle()
            bundle.putString(PLAY_URL, url)
            return bundle
        }
    }

    /**
     * 更新方向
     */
    fun updateOrientation(orientation: Int) {
        setRealPlaySvLayout()
    }

    /**
     * 获取声音开关
     */
    fun getRealPlaySound(): Boolean {
        return mRealPlaySquareInfo.mSoundType == 1
    }

    /**
     * 更新声音开关
     */
    fun updateRealPlaySound(openSound: Boolean) {
        mRealPlaySquareInfo.mSoundType = if (openSound) 1 else 0
        setRealPlaySound()
    }

    override fun getCreateViewLayoutId(): Int {
        return R.layout.ezviz_fragment_real_play
    }

    //播放状态
    private var mStatus = RealPlayStatus.STATUS_INIT

    //播放状态回调
    private var handle: Handler? = null

    //播放流画布
    private var mRealPlaySh: SurfaceHolder? = null

    //播放链接
    private lateinit var mRtspUrl: String

    //临时存储播放信息
    private lateinit var mRealPlaySquareInfo: RealPlaySquareInfo

    //播放器
    private var mEZPlayer: EZPlayer? = null

    private lateinit var mLocalInfo: LocalInfo

    override fun initViews(savedInstanceState: Bundle?) {

        mRtspUrl = requireArguments().getString(PLAY_URL, "")

        //保持屏幕常亮 Keep the screen on
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        mLocalInfo = LocalInfo.getInstance()

        mRealPlaySquareInfo = RealPlaySquareInfo()
        getRealPlaySquareInfo()

        handle = Handler(PlayCallback(this))
        mRealPlaySh = mBinding.realplaySv.holder
        mRealPlaySh?.addCallback(SurfaceCallback(this))

    }

    private fun getRealPlaySquareInfo() {
        if (TextUtils.isEmpty(mRtspUrl)) {
            return
        }
        val uri = Uri.parse(mRtspUrl.replaceFirst("&".toRegex(), "?"))
        try {
            mRealPlaySquareInfo.mSquareId = uri.getQueryParameter("squareid")?.toInt() ?: 0
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        try {
            mRealPlaySquareInfo.mChannelNo = Utils.getUrlValue(mRtspUrl, "channelno=", "&")?.toInt() ?: 1
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        mRealPlaySquareInfo.mCameraName = uri.getQueryParameter("cameraname")
        try {
            mRealPlaySquareInfo.mSoundType = uri.getQueryParameter("soundtype")?.toInt() ?: 1
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        mRealPlaySquareInfo.mCoverUrl = uri.getQueryParameter("md5Serial")
        if (!TextUtils.isEmpty(mRealPlaySquareInfo.mCoverUrl)) {
            mRealPlaySquareInfo.mCoverUrl = mLocalInfo.servAddr + mRealPlaySquareInfo.mCoverUrl.toString() + "_mobile.jpeg"
        }
    }

    override fun onResume() {
        super.onResume()
        if (mStatus == RealPlayStatus.STATUS_PAUSE
            || mStatus == RealPlayStatus.STATUS_DECRYPT) {
            // 开始播放
            startRealPlay()
        }
    }

    override fun onStop() {
        super.onStop()
        if (mStatus != RealPlayStatus.STATUS_STOP) {
            stopRealPlay()
            mStatus = RealPlayStatus.STATUS_PAUSE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyRealPlay()
    }

    private fun startRealPlay() {
        if (mStatus == RealPlayStatus.STATUS_START || mStatus == RealPlayStatus.STATUS_PLAY) {
            return
        }
        mStatus = RealPlayStatus.STATUS_START
        mEZPlayer = EZOpenSDK.getInstance().createPlayerWithUrl(mRtspUrl)
        mEZPlayer?.setHandler(handle)
        mEZPlayer?.setSurfaceHold(mRealPlaySh)
        mEZPlayer?.startRealPlay()
    }

    private fun setRealPlaySound() {
        if (mEZPlayer != null) {
            if (mRealPlaySquareInfo.mSoundType == 0) {
                mEZPlayer?.closeSound()
            } else {
                mEZPlayer?.openSound()
            }
        }
    }

    private fun handlePlaySuccess(msg: Message) {
        mStatus = RealPlayStatus.STATUS_PLAY

        // 声音处理  Sound processing
        setRealPlaySound()
        setRealPlaySvLayout()
    }

    private fun setRealPlaySvLayout() {

    }

    private fun stopRealPlay() {
        mStatus = RealPlayStatus.STATUS_STOP
        mEZPlayer?.stopRealPlay()
    }

    private fun destroyRealPlay() {
        mEZPlayer?.release()
    }

    override fun lazyLoad() {

    }

    override fun initFragmentData() {

    }

    override fun initListener() {

    }

    override fun initStateLayout(inflateView: View?) {

    }

    class PlayCallback(fragment: EZRealPlayFragment) : Handler.Callback {

        private val fragmentWeakReference: WeakReference<EZRealPlayFragment> = WeakReference(fragment)

        override fun handleMessage(msg: Message?): Boolean {
            val fragment: EZRealPlayFragment? = fragmentWeakReference.get()
            if (fragment != null && fragment.isAdded) {
                when (msg?.what) {
                    EZConstants.EZRealPlayConstants.MSG_REALPLAY_PLAY_SUCCESS -> {
                        fragment.handlePlaySuccess(msg)
                    }
                    EZMyConstants.MSG_PREVIEW_START_PLAY -> {
                        fragment.mStatus = RealPlayStatus.STATUS_INIT
                        fragment.startRealPlay()
                    }
                }
            }
            return false
        }
    }

    class SurfaceCallback(fragment: EZRealPlayFragment) : SurfaceHolder.Callback {

        private val fragmentWeakReference: WeakReference<EZRealPlayFragment> = WeakReference(fragment)

        override fun surfaceCreated(holder: SurfaceHolder?) {
            val fragment: EZRealPlayFragment? = fragmentWeakReference.get()
            fragment?.let {
                it.mEZPlayer?.setSurfaceHold(holder)
                it.mRealPlaySh = holder
                if (it.mStatus == RealPlayStatus.STATUS_INIT) {
                    // 开始播放
                    it.startRealPlay()
                }
            }
        }

        override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
            val fragment: EZRealPlayFragment? = fragmentWeakReference.get()
            fragment?.let {
                it.mEZPlayer?.setSurfaceHold(holder)
                it.mRealPlaySh = holder
            }
        }

        override fun surfaceDestroyed(holder: SurfaceHolder?) {
            val fragment: EZRealPlayFragment? = fragmentWeakReference.get()
            fragment?.let {
                it.mEZPlayer?.setSurfaceHold(null)
                it.mRealPlaySh = null
            }
        }

    }

}
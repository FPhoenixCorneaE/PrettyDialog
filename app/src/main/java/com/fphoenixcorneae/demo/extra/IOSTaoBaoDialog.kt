package com.fphoenixcorneae.demo.extra

import android.animation.ObjectAnimator
import android.content.Context
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet
import com.fphoenixcorneae.dialog.base.BottomBaseDialog
import com.fphoenixcorneae.demo.R
import com.fphoenixcorneae.demo.utils.T
import kotlinx.android.synthetic.main.dialog_ios_taobao.*

class IOSTaoBaoDialog : BottomBaseDialog<IOSTaoBaoDialog> {

    constructor(context: Context, animateView: View?) : super(
        context,
        animateView
    ) {
    }

    constructor(context: Context) : super(context) {}

    override fun onCreateView(): View {
        return View.inflate(mContext, R.layout.dialog_ios_taobao, null)
    }

    override fun setUiBeforeShow() {
        mLlWechatFriendCircle!!.setOnClickListener {
            T.showShort(mContext, "朋友圈")
            dismiss()
        }
        mLlWechatFriend!!.setOnClickListener {
            T.showShort(mContext, "微信")
            dismiss()
        }
        mLlQq!!.setOnClickListener {
            T.showShort(mContext, "QQ")
            dismiss()
        }
        mLlSms!!.setOnClickListener {
            T.showShort(mContext, "短信")
            dismiss()
        }
    }

    override fun getWindowInAs(): BaseAnimatorSet? {
        if (mWindowInAs == null) {
            mWindowInAs = WindowsInAs()
        }
        return mWindowInAs
    }

    override fun getWindowOutAs(): BaseAnimatorSet? {
        if (mWindowOutAs == null) {
            mWindowOutAs = WindowsOutAs()
        }
        return mWindowOutAs
    }

    internal inner class WindowsInAs : BaseAnimatorSet() {
        override fun setAnimation(view: View) {
            val rotationX =
                ObjectAnimator.ofFloat(view, "rotationX", 10f, 0f).setDuration(150)
            rotationX.startDelay = 200
            animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.8f).setDuration(350),
                ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.8f).setDuration(350),
                ObjectAnimator.ofFloat(view, "rotationX", 0f, 10f).setDuration(200),
                rotationX,
                ObjectAnimator.ofFloat(
                    view,
                    "translationY",
                    0f,
                    -0.1f * mDisplayMetrics.heightPixels
                ).setDuration(350)
            )
        }
    }

    internal inner class WindowsOutAs : BaseAnimatorSet() {
        override fun setAnimation(view: View) {
            val rotationX =
                ObjectAnimator.ofFloat(view, "rotationX", 10f, 0f).setDuration(150)
            rotationX.startDelay = 200
            animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "scaleX", 0.8f, 1.0f).setDuration(350),
                ObjectAnimator.ofFloat(view, "scaleY", 0.8f, 1.0f).setDuration(350),
                ObjectAnimator.ofFloat(view, "rotationX", 0f, 10f).setDuration(200),
                rotationX,
                ObjectAnimator.ofFloat(
                    view,
                    "translationY",
                    -0.1f * mDisplayMetrics.heightPixels,
                    0f
                ).setDuration(350)
            )
        }
    }
}
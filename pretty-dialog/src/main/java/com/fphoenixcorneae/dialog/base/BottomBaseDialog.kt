package com.fphoenixcorneae.dialog.base

import android.animation.ObjectAnimator
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import com.fphoenixcorneae.animation.BaseAnimatorSet

abstract class BottomBaseDialog<T : BottomBaseDialog<T>> @JvmOverloads constructor(
    context: Context,
    animateView: View? = null
) : BottomTopBaseDialog<T>(context) {
    override val gravity: Int
        get() = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL

    override fun onStart() {
        super.onStart()
        window?.setGravity(Gravity.BOTTOM)
        mFlTop.setPadding(mLeft, mTop, mRight, mBottom)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        showWithAnim()
    }

    override fun dismiss() {
        dismissWithAnim()
    }

    override fun getWindowInAs(): BaseAnimatorSet? {
        if (mWindowInAs == null) {
            mWindowInAs = WindowInAs()
        }
        return mWindowInAs
    }

    override fun getWindowOutAs(): BaseAnimatorSet? {
        if (mWindowOutAs == null) {
            mWindowOutAs = WindowOutAs()
        }
        return mWindowOutAs
    }

    private inner class WindowInAs : BaseAnimatorSet() {
        override fun setAnimation(view: View) {
            val oa1 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.9f)
            val oa2 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.9f)
            animatorSet.playTogether(oa1, oa2)
        }
    }

    private inner class WindowOutAs : BaseAnimatorSet() {
        override fun setAnimation(view: View) {
            val oa1 = ObjectAnimator.ofFloat(view, "scaleX", 0.9f, 1f)
            val oa2 = ObjectAnimator.ofFloat(view, "scaleY", 0.9f, 1f)
            animatorSet.playTogether(oa1, oa2)
        }
    }

    init {
        mAnimateView = animateView
        mInnerShowAnim = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f
        )
        mInnerDismissAnim = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f
        )
    }
}
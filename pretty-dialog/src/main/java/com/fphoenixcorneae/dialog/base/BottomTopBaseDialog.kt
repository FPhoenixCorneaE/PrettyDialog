package com.fphoenixcorneae.dialog.base

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import com.fphoenixcorneae.animation.BaseAnimatorSet

abstract class BottomTopBaseDialog<T : BottomTopBaseDialog<T>>(context: Context) :
    BaseDialog<T>(context) {
    protected var mAnimateView: View? = null
    protected var mWindowInAs: BaseAnimatorSet? = null
    protected var mWindowOutAs: BaseAnimatorSet? = null
    protected var mInnerShowAnim: Animation? = null
    protected var mInnerDismissAnim: Animation? = null
    protected var mInnerAnimDuration: Long = 350
    protected var mIsInnerShowAnim = false
    protected var mIsInnerDismissAnim = false
    protected var mLeft = 0
    protected var mTop = 0
    protected var mRight = 0
    protected var mBottom = 0

    /** set duration for inner animation of mAnimateView(设置animateView内置动画时长)  */
    fun innerAnimDuration(innerAnimDuration: Long): T {
        mInnerAnimDuration = innerAnimDuration
        return this as T
    }

    fun padding(left: Int, top: Int, right: Int, bottom: Int): T {
        mLeft = left
        mTop = top
        mRight = right
        mBottom = bottom
        return this as T
    }

    /** show dialog and mAnimateView with inner show animation(设置dialog和animateView显示动画)  */
    protected fun showWithAnim() {
        if (mInnerShowAnim != null) {
            mInnerShowAnim!!.duration = mInnerAnimDuration
            mInnerShowAnim!!.setAnimationListener(object : AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    mIsInnerShowAnim = true
                }

                override fun onAnimationRepeat(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    mIsInnerShowAnim = false
                }
            })
            mLlControlHeight.startAnimation(mInnerShowAnim)
        }
        if (mAnimateView != null) {
            val windowInAs = getWindowInAs()
            if (windowInAs != null) {
                mWindowInAs = windowInAs
            }
            mWindowInAs?.duration(mInnerAnimDuration)?.playOn(mAnimateView!!)
        }
    }

    /** dismiss dialog and mAnimateView with inner dismiss animation(设置dialog和animateView消失动画)  */
    protected fun dismissWithAnim() {
        if (mInnerDismissAnim != null) {
            mInnerDismissAnim!!.duration = mInnerAnimDuration
            mInnerDismissAnim!!.setAnimationListener(object : AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    mIsInnerDismissAnim = true
                }

                override fun onAnimationRepeat(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    mIsInnerDismissAnim = false
                    superDismiss()
                }
            })
            mLlControlHeight.startAnimation(mInnerDismissAnim)
        } else {
            superDismiss()
        }
        if (mAnimateView != null) {
            val windowOutAs = getWindowOutAs()
            if (windowOutAs != null) {
                mWindowOutAs = windowOutAs
            }
            mWindowOutAs?.duration(mInnerAnimDuration)?.playOn(mAnimateView!!)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return if (mIsInnerDismissAnim || mIsInnerShowAnim) {
            true
        } else super.dispatchTouchEvent(ev)
    }

    override fun onBackPressed() {
        if (mIsInnerDismissAnim || mIsInnerShowAnim) {
            return
        }
        super.onBackPressed()
    }

    abstract fun getWindowInAs(): BaseAnimatorSet?

    abstract fun getWindowOutAs(): BaseAnimatorSet?
}
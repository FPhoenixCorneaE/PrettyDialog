package com.fphoenixcorneae.animation.windows

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 窗口顶部闪开进入动效
 */
class WindowsSlipBottomEnter : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        view.post {
            /* 设置动画中心点:pivotX--->X轴方向动画中心点,pivotY--->Y轴方向动画中心点 */
            view.pivotX = view.measuredWidth / 2.0f
            view.pivotY = 0f
        }
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "rotationX", -5f, 0f)
        )
    }
}
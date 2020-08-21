package com.fphoenixcorneae.animation.windows

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 窗口缩放进入动效
 */
class WindowsScaleEnter : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.9f),
            ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.9f)
        )
    }
}
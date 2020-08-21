package com.fphoenixcorneae.animation.windows

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 窗口缩放退出动效
 */
class WindowsScaleExit : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "scaleX", 0.9f, 1f),
            ObjectAnimator.ofFloat(view, "scaleY", 0.9f, 1f)
        )
    }
}
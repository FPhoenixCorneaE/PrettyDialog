package com.fphoenixcorneae.animation.zoom

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 缩小退出动效
 */
class ZoomOutExit : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 0f),
            ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.3f, 0f),
            ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.3f, 0f)
        )
    }
}
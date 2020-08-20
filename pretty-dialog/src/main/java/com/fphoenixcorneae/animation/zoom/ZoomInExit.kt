package com.fphoenixcorneae.animation.zoom

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 放大退出动效
 */
class ZoomInExit : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "scaleX", 1f, 2f),
            ObjectAnimator.ofFloat(view, "scaleY", 1f, 2f),
            ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        )
    }

    init {
        duration = 400
    }
}
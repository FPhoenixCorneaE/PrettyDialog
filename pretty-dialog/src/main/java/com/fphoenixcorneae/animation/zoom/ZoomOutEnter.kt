package com.fphoenixcorneae.animation.zoom

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 缩小进入动效
 */
class ZoomOutEnter : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f),
            ObjectAnimator.ofFloat(view, "scaleX", 2f, 1f),
            ObjectAnimator.ofFloat(view, "scaleY", 2f, 1f)
        )
    }

    init {
        duration = 400
    }
}
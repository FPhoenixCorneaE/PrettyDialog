package com.fphoenixcorneae.animation.zoom

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 放大进入动效
 */
class ZoomInEnter : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1f),
            ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1f),
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        )
    }
}
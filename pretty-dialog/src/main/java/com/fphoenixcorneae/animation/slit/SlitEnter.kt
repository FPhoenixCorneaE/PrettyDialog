package com.fphoenixcorneae.animation.slit

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 狭缝动效
 */
class SlitEnter : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "rotationY", 90f, 88f, 88f, 45f, 0f),
            ObjectAnimator.ofFloat(view, "alpha", 0f, 0.4f, 0.8f, 1f),
            ObjectAnimator.ofFloat(view, "scaleX", 0f, 0.5f, 0.9f, 0.9f, 1f),
            ObjectAnimator.ofFloat(view, "scaleY", 0f, 0.5f, 0.9f, 0.9f, 1f)
        )
    }

    init {
        duration = 1000
    }
}
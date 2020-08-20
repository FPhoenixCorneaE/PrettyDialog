package com.fphoenixcorneae.animation.attention

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 果冻动效
 */
class Jelly : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "scaleX", 0.3f, 0.5f, 0.9f, 0.8f, 0.9f, 1f),
            ObjectAnimator.ofFloat(view, "scaleY", 0.3f, 0.5f, 0.9f, 0.8f, 0.9f, 1f),
            ObjectAnimator.ofFloat(view, "alpha", 0.2f, 1f)
        )
    }

    init {
        duration = 700
    }
}
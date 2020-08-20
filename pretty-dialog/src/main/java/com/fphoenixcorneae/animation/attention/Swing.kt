package com.fphoenixcorneae.animation.attention

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 秋千摇摆动效
 */
class Swing : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "alpha", 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f),
            ObjectAnimator.ofFloat(view, "rotation", 0f, 10f, -10f, 6f, -6f, 3f, -3f, 0f)
        )
    }

    init {
        duration = 1000
    }
}
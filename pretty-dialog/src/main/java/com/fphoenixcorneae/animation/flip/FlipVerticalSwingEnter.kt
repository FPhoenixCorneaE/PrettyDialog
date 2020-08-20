package com.fphoenixcorneae.animation.flip

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 垂直翻转进入摇摆动效
 */
class FlipVerticalSwingEnter : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "rotationX", -90f, 10f, -10f, 0f),
            ObjectAnimator.ofFloat(view, "alpha", 0.25f, 0.5f, 0.75f, 1f)
        )
    }

    init {
        duration = 1000
    }
}
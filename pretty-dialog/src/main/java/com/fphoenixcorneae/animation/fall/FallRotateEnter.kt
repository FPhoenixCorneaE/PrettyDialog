package com.fphoenixcorneae.animation.fall

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 旋转落下动效
 */
class FallRotateEnter : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "rotationX", 15f, 0f),
            ObjectAnimator.ofFloat(view, "rotation", 15f, 0f),
            ObjectAnimator.ofFloat(view, "scaleX", 1.25f, 1f),
            ObjectAnimator.ofFloat(view, "scaleY", 1.25f, 1f),
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        )
    }

    init {
        duration = 700
    }
}
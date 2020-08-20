package com.fphoenixcorneae.animation.fall

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 落下动效
 */
class FallEnter : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "rotationX", 30f, 15f, 0f),
            ObjectAnimator.ofFloat(view, "scaleX", 2f, 1.5f, 1f),
            ObjectAnimator.ofFloat(view, "scaleY", 2f, 1.5f, 1f),
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        )
    }
}
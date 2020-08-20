package com.fphoenixcorneae.animation.bounce

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 右边进入反弹动效
 */
class BounceRightEnter : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f, 1f, 1f),
            ObjectAnimator.ofFloat(view, "translationX", 360f, -30f, 10f, 0f)
        )
    }

    init {
        duration = 1000
    }
}
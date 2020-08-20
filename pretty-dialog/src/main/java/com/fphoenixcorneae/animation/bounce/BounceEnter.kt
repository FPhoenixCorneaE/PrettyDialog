package com.fphoenixcorneae.animation.bounce

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 中间出现反弹动效
 */
class BounceEnter : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "alpha", 0f, 0.25f, 0.75f, 1f),
            ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1.05f, 0.95f, 1f),
            ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1.05f, 0.95f, 1f)
        )
    }

    init {
        duration = 700
    }
}